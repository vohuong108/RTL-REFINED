package org.uet.dse.rtlplus.sync;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.main.Session;
import org.tzi.use.parser.ocl.OCLCompiler;
import org.tzi.use.parser.shell.ShellCommandCompiler;
import org.tzi.use.uml.ocl.expr.Evaluator;
import org.tzi.use.uml.ocl.value.BooleanValue;
import org.tzi.use.uml.ocl.value.ObjectValue;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.ocl.value.VarBindings;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemException;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.uml.sys.events.AttributeAssignedEvent;
import org.tzi.use.uml.sys.events.LinkDeletedEvent;
import org.tzi.use.uml.sys.events.LinkInsertedEvent;
import org.tzi.use.uml.sys.events.ObjectCreatedEvent;
import org.tzi.use.uml.sys.events.ObjectDestroyedEvent;
import org.tzi.use.uml.sys.soil.MStatement;
import org.tzi.use.util.NullWriter;
import org.tzi.use.util.soil.VariableEnvironment;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.matching.BackwardMatchManager;
import org.uet.dse.rtlplus.matching.ForwardMatchManager;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.matching.MatchManager;
import org.uet.dse.rtlplus.mm.MRuleCollection.Side;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;
import org.uet.dse.rtlplus.mm.MTggRule;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class SyncWorker {
	// Search for matches when an object is created
	private Map<String, Set<MTggRule>> rulesForSrcClass;
	private Map<String, Set<MTggRule>> rulesForTrgClass;
	private Map<String, Side> classMap;
	// Collect info when transformations are run
	private boolean running = false;
	// Needed to find matches
	private MSystemState state;
	private PrintWriter logWriter;
	private EventBus eventBus;
	private UseSystemApi api;
	private boolean syncForward = true;
	// Needed for attribute assignment
	private Map<String, Set<String>> corrObjsForSrc;
	private Map<String, Set<String>> corrObjsForTrg;
	private Map<String, Set<String>> forwardCmdsForCorr;
	private Map<String, Set<String>> backwardCmdsForCorr;
	// Correlation object dependencies
	private Map<String, Set<String>> corrObjDependencies;
	private Map<String, OperationEnterEvent> pastTransformations;
	private Map<String, String> transformationForCorrObj;
	// Cache for current transformation
	private OperationEnterEvent event;
	// GUI
	private SyncWorkerDialog dialog;

	public SyncWorker(SyncWorkerDialog dialog, PrintWriter logWriter, Session session) {
		this.dialog = dialog;
		api = UseSystemApi.create(session);
		classMap = Main.getTggRuleCollection().getClassMap();
		state = session.system().state();
		this.logWriter = logWriter;
		eventBus = session.system().getEventBus();
		syncForward = Main.getTggRuleCollection().getType() == TransformationType.FORWARD;
		rulesForSrcClass = Main.getSyncData().getRulesForSrcClass();
		rulesForTrgClass = Main.getSyncData().getRulesForTrgClass();
		corrObjsForSrc = Main.getSyncData().getCorrObjsForSrc();
		corrObjsForTrg = Main.getSyncData().getCorrObjsForTrg();
		forwardCmdsForCorr = Main.getSyncData().getForwardCmdsForCorr();
		backwardCmdsForCorr = Main.getSyncData().getBackwardCmdsForCorr();
		corrObjDependencies = Main.getSyncData().getCorrObjDependencies();
		pastTransformations = Main.getSyncData().getPastTransformations();
		transformationForCorrObj = Main.getSyncData().getTransformationForCorrObj();
	}

	@Subscribe
	public void onOperationEntered(OperationEnterEvent e) {
		running = true;
		// Initialise cache
		event = e;
	}

	@Subscribe
	public void onOperationExited(OperationExitEvent e) {
		if (e.isSuccess()) {
			String transName = Main.getUniqueNameGenerator().generate(e.getOpName());
			Main.getSyncData().addTransformation(transName, event);
		}
		running = false;
	}

	@Subscribe
	public void onObjectCreated(ObjectCreatedEvent e) {
		MObject obj = e.getCreatedObject();
		// System.out.println("Object created: " + obj.name());
		Side side = classMap.get(obj.cls().name());
		if (!running) {
			switch (side) {
			case SOURCE:
				// Find forward matches
				if (dialog != null)
					dialog.findForwardMatches(state, rulesForSrcClass.get(obj.cls().name()), Arrays.asList(obj),
							syncForward);
				else
					runForwardMatches(rulesForSrcClass.get(obj.cls().name()), Arrays.asList(obj), syncForward);
				break;
			case TARGET:
				// Find backward matches
				if (dialog != null)
					dialog.findBackwardMatches(state, rulesForTrgClass.get(obj.cls().name()), Arrays.asList(obj),
							!syncForward);
				else
					runBackwardMatches(rulesForTrgClass.get(obj.cls().name()), Arrays.asList(obj), !syncForward);
				break;
			default:
				break;
			}
		}
	}

	@Subscribe
	public void onObjectDestroyed(ObjectDestroyedEvent e) {
		String objName = e.getDestroyedObject().name();
		System.out.println("Object destroyed: " + objName);
		MObject obj = e.getDestroyedObject();
		if (!running) {
			eventBus.unregister(this);
			Set<String> corrs = null;
			if (classMap.get(obj.cls().name()) == Side.SOURCE && syncForward)
				corrs = corrObjsForSrc.getOrDefault(objName, new HashSet<>());
			else if (classMap.get(obj.cls().name()) == Side.TARGET && !syncForward)
				corrs = corrObjsForTrg.getOrDefault(objName, new HashSet<>());
			if (corrs != null) {
				Set<String> allCorrs = findAllDependencies(corrs, new HashSet<>(corrs));
				Set<String> transToUndo = new HashSet<>();
				for (String corr : allCorrs) {
					String tran = transformationForCorrObj.get(corr);
					if (tran != null)
						transToUndo.add(tran);
				}
				// Undo transformations
				for (String tran : transToUndo) {
					OperationEnterEvent event = pastTransformations.get(tran);
					if (event != null) {
						undoTransformation(event);
						pastTransformations.remove(tran);
					}
				}
			}
			eventBus.register(this);
		}
	}

	private void undoTransformation(OperationEnterEvent event) {
		// System.out.println("Undoing transformation: " + event.getOpName());
		// Delete links
		List<CachedLink> linksToCreate = event.getLinksToCreate();
		if (linksToCreate != null) {
			for (CachedLink lnk : linksToCreate) {
				try {
					api.deleteLink(lnk.getAssociation(), lnk.getLinkedObjects());
				} catch (UseApiException ignored) {
				}
			}
		}
		// Delete objects
		Map<String, String> objectsToCreate = event.getObjectsToCreate();
		if (objectsToCreate != null) {
			for (String objName : objectsToCreate.values()) {
				try {
					api.deleteObject(objName);
					System.out.println("Deleted " + objName);
				} catch (UseApiException ignored) {
				}
			}
		}
		// Delete correlation objects
		Map<String, String> corrObjsToCreate = event.getCorrObjsToCreate();
		if (corrObjsToCreate != null) {
			for (String objName : corrObjsToCreate.values()) {
				try {
					api.deleteObject(objName);
				} catch (UseApiException ignored) {
				}
			}
		}
		// logWriter.println("Transformation undone: " + event.getOpName());
	}

	private Set<String> findAllDependencies(Set<String> corrObjs, Set<String> allDeps) {
		Set<String> nextDeps = new HashSet<>();
		for (String corrObj : corrObjs) {
			Set<String> deps = corrObjDependencies.get(corrObj);
			if (deps != null) {
				for (String dep : deps) {
					if (allDeps.add(dep))
						nextDeps.add(dep);
				}
			}
		}
		if (!nextDeps.isEmpty()) {
			findAllDependencies(nextDeps, allDeps);
		}
		return allDeps;
	}

	@Subscribe
	public void onLinkInserted(LinkInsertedEvent e) {
		if (running) {
			// Link between src/trg and correlation
			List<MObject> objs = e.getParticipants();
			Side s1 = classMap.get(objs.get(0).cls().name());
			if (objs.size() == 2) {
				Side s2 = classMap.get(objs.get(1).cls().name());
				if (s1 == Side.SOURCE && s2 == Side.CORRELATION) {
					Main.getSyncData().addSrcCorrLink(objs.get(0).name(), objs.get(1).name());
				}
				if (s1 == Side.TARGET && s2 == Side.CORRELATION) {
					Main.getSyncData().addTrgCorrLink(objs.get(0).name(), objs.get(1).name());
				}
			}
		} else {
			List<MObject> objects = e.getLink().linkedObjects();
			Side side = classMap.get(objects.get(0).cls().name());
			Set<MTggRule> ruleList = new HashSet<>();
			boolean sameSide = true;
			for (MObject obj : objects) {
				if (classMap.get(obj.cls().name()) != side) {
					sameSide = false;
					break;
				}
				if (side == Side.SOURCE) {
					Collection<MTggRule> rules = rulesForSrcClass.get(obj.cls().name());
					if (rules != null)
						ruleList.addAll(rules);
				} else if (side == Side.TARGET) {
					Collection<MTggRule> rules = rulesForTrgClass.get(obj.cls().name());
					if (rules != null)
						ruleList.addAll(rules);
				}
			}
			if (sameSide) {
				switch (side) {
				case SOURCE:
					if (dialog != null)
						dialog.findForwardMatches(state, ruleList, objects, syncForward);
					else
						runForwardMatches(ruleList, objects, syncForward);
					break;
				case TARGET:
					if (dialog != null)
						dialog.findBackwardMatches(state, ruleList, objects, !syncForward);
					else
						runBackwardMatches(ruleList, objects, !syncForward);
					break;
				default:
					break;
				}
			}
		}
	}

	@Subscribe
	public void onLinkDeleted(LinkDeletedEvent e) {
		if (!running) {
			List<MObject> objects = e.getLink().linkedObjects();
			eventBus.unregister(this);
			checkConsistencyAndUndo(objects.stream().map(it -> it.name()).collect(Collectors.toList()));
			eventBus.register(this);
		}
	}

	@Subscribe
	public void onAttributeAssigned(AttributeAssignedEvent e) {
		if (!running) {
			eventBus.unregister(this);
			// System.out.println("Attribute assignment: " + e.getObject().name() + "." +
			// e.getAttribute().name() + " := " + e.getValue().toStringWithType());
			switch (classMap.get(e.getObject().cls().name())) {
			case SOURCE:
				// System.out.println("Source object changed!");
				Set<String> corrObjs = corrObjsForSrc.get(e.getObject().name());
				if (corrObjs != null) {
					// System.out.println(corrObjs);
					for (String corr : corrObjs) {
						MObject corrObj = state.objectByName(corr);
						if (corrObj != null) {
							Set<String> strs = forwardCmdsForCorr.get(corrObj.cls().name());
							if (strs != null) {
								//System.out.println("Strs = " + strs.toString());
								for (String str : strs) {
									String line = str.replace("self.", corr + ".");
									MStatement statement = ShellCommandCompiler.compileShellCommand(
											state.system().model(), state, state.system().getVariableEnvironment(),
											line, "<input>", logWriter, false);
									if (statement == null) {
										logWriter.println("Cannot parse command: " + line);
									} else
										try {
											state.system().execute(statement);
										} catch (MSystemException exception) {
											logWriter.println("MSystemException: " + exception.getMessage());
										}
								}
							}
						}
					}
				}
				checkConsistencyAndUndo(Arrays.asList(e.getObject().name()));
				if (dialog != null)
					dialog.findForwardMatches(state, rulesForSrcClass.get(e.getObject().cls().name()),
							Arrays.asList(e.getObject()), syncForward);
				else
					runForwardMatches(rulesForSrcClass.get(e.getObject().cls().name()), Arrays.asList(e.getObject()),
							syncForward);
				break;
			case TARGET:
				Set<String> cObjs = corrObjsForTrg.get(e.getObject().name());
				if (cObjs != null) {
					for (String corr : cObjs) {
						MObject corrObj = state.objectByName(corr);
						if (corrObj != null) {
							Set<String> strs = backwardCmdsForCorr.get(corrObj.cls().name());
							if (strs != null) {
								for (String str : strs) {
									String line = str.replace("self.", corr + ".");
									MStatement statement = ShellCommandCompiler.compileShellCommand(
											state.system().model(), state, state.system().getVariableEnvironment(),
											line, "<input>", logWriter, false);
									if (statement == null) {
										logWriter.println("Cannot parse command: " + line);
									} else
										try {
											state.system().execute(statement);
										} catch (MSystemException exception) {
											logWriter.println("MSystemException: " + exception.getMessage());
										}
								}
							}
						}
					}
				}
				checkConsistencyAndUndo(Arrays.asList(e.getObject().name()));
				if (dialog != null)
					dialog.findBackwardMatches(state, rulesForTrgClass.get(e.getObject().cls().name()),
							Arrays.asList(e.getObject()), !syncForward);
				else
					runBackwardMatches(rulesForTrgClass.get(e.getObject().cls().name()), Arrays.asList(e.getObject()),
							!syncForward);
				break;
			default:
				break;
			}
			eventBus.register(this);
		}
	}

	private void checkConsistencyAndUndo(List<String> objects) {
		Set<OperationEnterEvent> transToUndo = new HashSet<>();
		for (OperationEnterEvent e : pastTransformations.values()) {
			if (e.getMatchedObjects().values().containsAll(objects)) {
				MTggRule rule = Main.getTggRuleCollection().getRuleByName(e.getRuleName());
				String postCondition = syncForward? rule.getSrcRule().genPostCond().trim() :
					rule.getTrgRule().genPostCond().trim();
				String invariantCondition = rule.getCorrRule().genPostCond();
				if (!postCondition.isEmpty() && !invariantCondition.isEmpty())
					postCondition += " and " + invariantCondition;
				else
					postCondition += invariantCondition;
				boolean mustUndo = false;
				VariableEnvironment varEnv = state.system().getVariableEnvironment();
				for(Map.Entry<String, String> assignment : e.getMatchedObjects().entrySet()) {
					MObject obj = api.getObject(assignment.getValue());
					// Object does not exist, inconsistency found
					if (obj == null) {
						mustUndo = true;
						break;
					}
					varEnv.assign(assignment.getKey(), new ObjectValue(obj.cls(), obj));
				}
				for(Map.Entry<String, String> assignment : e.getObjectsToCreate().entrySet()) {
					MObject obj = api.getObject(assignment.getValue());
					// Object does not exist, inconsistency found
					if (obj == null) {
						mustUndo = true;
						break;
					}
					varEnv.assign(assignment.getKey(), new ObjectValue(obj.cls(), obj));
				}
				for(Map.Entry<String, String> assignment : e.getCorrObjsToCreate().entrySet()) {
					MObject obj = api.getObject(assignment.getValue());
					// Object does not exist, inconsistency found
					if (obj == null) {
						mustUndo = true;
						break;
					}
					varEnv.assign(assignment.getKey(), new ObjectValue(obj.cls(), obj));
				}
				if (mustUndo)
					transToUndo.add(e);
				else {
					try {
						VarBindings vbs = varEnv.constructVarBindings();
						Evaluator ev = new Evaluator();
 						Value val = ev.eval(OCLCompiler.compileExpression(state.system().model(), postCondition, "rtl", new PrintWriter(new NullWriter()), vbs), state, vbs);
						if (val.isBoolean() && ((BooleanValue) val).isFalse()) {
							Collection<String> corrObjs = e.getCorrObjsToCreate().values();
							if (corrObjs != null) {
								// System.out.println(corrObjs);
								for (String corr : corrObjs) {
									MObject corrObj = state.objectByName(corr);
									if (corrObj != null) {
										Set<String> cmds = syncForward? 
												forwardCmdsForCorr.get(corrObj.cls().name()) :
												backwardCmdsForCorr.get(corrObj.cls().name());
										if (cmds != null) {
											executeAttributeCommands(cmds, corr);
										}
									}
								}
							}
							Value val2 = ev.eval(OCLCompiler.compileExpression(state.system().model(), postCondition, "rtl", new PrintWriter(new NullWriter()), vbs), state, vbs);
							mustUndo = val2.isBoolean() && ((BooleanValue) val2).isFalse();
						}
					}
					catch (Exception exception) {
						mustUndo = true;
					}
				}
				if (mustUndo)
					transToUndo.add(e);
			}
		}
		for (OperationEnterEvent tran : transToUndo) {
			undoTransformation(tran);
		}
	}
	
	private void executeAttributeCommands(Set<String> strs, String corr) {
		for (String str : strs) {
			String line = str.replace("self.", corr + ".");
			MStatement statement = ShellCommandCompiler.compileShellCommand(
					state.system().model(), state,
					state.system().getVariableEnvironment(), line,
					"<input>", logWriter, false);
			if (statement == null) {
				logWriter.println("Cannot parse command: " + line);
			} else
				try {
					state.system().execute(statement);
				} catch (MSystemException exception) {
					logWriter.println(
							"MSystemException: " + exception.getMessage());
				}
		}
	}

	private void runForwardMatches(Collection<MTggRule> ruleList, List<MObject> objects, boolean sync) {
		// System.out.println("Running forward matches");
		if (ruleList != null) {
			List<Match> fMatches = new ArrayList<>();
			do {
				MatchManager fManager = new ForwardMatchManager(state, sync);
				fMatches = fManager.findMatchesForRulesAndObjects(ruleList, objects);
				for (Match match : fMatches) {
					boolean res = match.run(state, logWriter);
					if (res)
						break;
				}
			} while (fMatches.size() > 0);
		}
	}

	private void runBackwardMatches(Collection<MTggRule> ruleList, List<MObject> objects, boolean sync) {
		if (ruleList != null) {
			List<Match> bMatches = new ArrayList<>();
			do {
				MatchManager bManager = new BackwardMatchManager(state, sync);
				bMatches = bManager.findMatchesForRulesAndObjects(ruleList, objects);
				for (Match match : bMatches) {
					boolean res = match.run(state, logWriter);
					if (res)
						break;
				}
			} while (bMatches.size() > 0);
		}
	}
}
