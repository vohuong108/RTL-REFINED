package org.uet.dse.rtlplus.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.tzi.kodkod.KodkodModelValidatorConfiguration;
import org.tzi.kodkod.KodkodSolver;
import org.tzi.kodkod.helper.LogMessages;
import org.tzi.kodkod.model.config.impl.ModelConfigurator;
import org.tzi.kodkod.model.config.impl.PropertyConfigurationVisitor;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeAtoms;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.kodkod.UseCTScrollingKodkodModelValidator;
import org.tzi.use.kodkod.plugin.PluginModelFactory;
import org.tzi.use.kodkod.plugin.USECommentFilterReader;
import org.tzi.use.kodkod.solution.ObjectDiagramCreator;
import org.tzi.use.kodkod.solution.ObjectDiagramCreator.ErrorType;
import org.tzi.use.kodkod.transform.TransformationException;
import org.tzi.use.kodkod.transform.enrich.ModelEnricher;
import org.tzi.use.kodkod.transform.ocl.DefaultExpressionVisitor;
import org.tzi.use.main.Session;
import org.tzi.use.parser.ocl.OCLCompiler;
import org.tzi.use.parser.use.USECompiler;
import org.tzi.use.uml.mm.MAttribute;
import org.tzi.use.uml.mm.MModel;
import org.tzi.use.uml.mm.ModelFactory;
import org.tzi.use.uml.ocl.expr.Evaluator;
import org.tzi.use.uml.ocl.expr.Expression;
import org.tzi.use.uml.ocl.type.Type.VoidHandling;
import org.tzi.use.uml.ocl.value.BooleanValue;
import org.tzi.use.uml.ocl.value.IntegerValue;
import org.tzi.use.uml.ocl.value.UndefinedValue;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.ocl.value.VarBindings;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystem;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.util.Pair;
import org.tzi.use.util.StringUtil;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.RTLLoader;
import org.uet.dse.rtlplus.matching.BackwardMatchManager;
import org.uet.dse.rtlplus.matching.ForwardMatchManager;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.matching.MatchManager;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;
import org.uet.dse.rtlplus.sync.OperationEnterEvent;

import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.IntExpression;
import kodkod.ast.Node;
import kodkod.ast.Relation;
import kodkod.ast.Variable;
import kodkod.engine.Solution;
import kodkod.instance.TupleSet;

public class CtTester extends SwingWorker<TestResult, Void> {

	private Session session;
	private File srcModel;
	private File trgModel;
	private String tggName;
	private File propFile;
	private String srcTerms;
	private String trgTerms;
	private ProgressMonitor monitor;
	private int bitwidth;
	private File mappingFile;
	private String error;

	public CtTester(Session session, File srcModel, File trgModel, String tggName, File propFile, String srcTerms,
			String trgTerms, ProgressMonitor monitor, int bitwidth) {
		super();
		this.session = session;
		this.srcModel = srcModel;
		this.trgModel = trgModel;
		this.tggName = tggName;
		this.propFile = propFile;
		this.srcTerms = srcTerms;
		this.trgTerms = trgTerms;
		this.monitor = monitor;
		this.bitwidth = bitwidth;
	}
	
	public CtTester(Session session, File srcModel, File trgModel, String tggName, File propFile, String srcTerms,
			String trgTerms, ProgressMonitor monitor, int bitwidth, File mappingFile) {
		super();
		this.session = session;
		this.srcModel = srcModel;
		this.trgModel = trgModel;
		this.tggName = tggName;
		this.propFile = propFile;
		this.srcTerms = srcTerms;
		this.trgTerms = trgTerms;
		this.monitor = monitor;
		this.bitwidth = bitwidth;
		this.mappingFile = mappingFile;
	}

	@Override
	protected TestResult doInBackground() throws Exception {
		setProgress(20);
		RTLLoader loader = new RTLLoader(session, srcModel, trgModel, tggName, new PrintWriter(System.out));
		boolean res = loader.run();
		if (!res) {
			error = "Error when loading metamodels or rules. Please verify your syntax and try again.";
			return null;
		}
		TransformationType type = Main.getTggRuleCollection().getType();
		if (type != TransformationType.FORWARD && type != TransformationType.BACKWARD) {
			error = "The transformation type should be forward or backward, but is actually " + type.name();
			return null;
		}

		Session newSession = new Session();
		UseCTScrollingKodkodModelValidator validator = null;
		boolean readResult = true;
		IModel iModel = null;
		MSystem newSystem = null;
		List<ClassifyingTerm> terms = new ArrayList<>();
		List<Expression> otherTerms = new ArrayList<>();
		MModel newModel;

		setProgress(30);
		BufferedReader reader = new BufferedReader(new USECommentFilterReader(new FileReader(srcTerms)));
		List<Pair<String>> srcTermList = new ArrayList<>();
		if (type == TransformationType.FORWARD) {
			newModel = USECompiler.compileSpecification(new FileInputStream(srcModel), srcModel.getName(),
					new PrintWriter(System.err), new ModelFactory());
			newSystem = new MSystem(newModel);
			newSession.setSystem(newSystem);
			validator = new UseCTScrollingKodkodModelValidator(newSession);
			PluginModelFactory.INSTANCE.registerForSession(newSession);
			iModel = PluginModelFactory.INSTANCE.getModel(newModel);
			readResult = readTerms(reader, srcTermList, terms, newModel, iModel, validator);
		} else
			readResult = readTerms(reader, srcTermList, otherTerms, session.system().model());
		if (srcTermList.isEmpty()) {
			error = "There are no source classifying terms.";
			return null;
		}
		if (!readResult)
			return null;

		setProgress(40);
		BufferedReader reader2 = new BufferedReader(new USECommentFilterReader(new FileReader(trgTerms)));
		List<Pair<String>> trgTermList = new ArrayList<>();
		if (type == TransformationType.BACKWARD) {
			newModel = USECompiler.compileSpecification(new FileInputStream(trgModel), trgModel.getName(),
					new PrintWriter(System.err), new ModelFactory());
			newSystem = new MSystem(newModel);
			newSession.setSystem(newSystem);
			validator = new UseCTScrollingKodkodModelValidator(newSession);
			PluginModelFactory.INSTANCE.registerForSession(newSession);
			iModel = PluginModelFactory.INSTANCE.getModel(newModel);
			readResult = readTerms(reader2, trgTermList, terms, newModel, iModel, validator);
		} else
			readResult = readTerms(reader2, trgTermList, otherTerms, session.system().model());
		if (trgTermList.isEmpty()) {
			error = "There are no target classifying terms.";
			return null;
		}
		if (!readResult)
			return null;

		setProgress(50);
		HierarchicalINIConfiguration hierarchicalINIConfiguration = new HierarchicalINIConfiguration();
		try {
			hierarchicalINIConfiguration.load(new USECommentFilterReader(new FileReader(propFile)));
		} catch (IOException ex) {
			error = ex.getMessage();
			return null;
		}
		Configuration config = getConfig(hierarchicalINIConfiguration);
		iModel.reset();
		PropertyConfigurationVisitor newConfigurationVisitor = new PropertyConfigurationVisitor(config,
				new PrintWriter(System.out));
		iModel.accept(newConfigurationVisitor);
		if (newConfigurationVisitor.containErrors()) {
			error = LogMessages.configurationError;
			return null;
		}
		
		setProgress(55);
		List<Mapping> mappings = new ArrayList<>();
		if (mappingFile != null)
			readMappings(mappingFile, mappings, terms.size(), otherTerms.size());
		
		ModelEnricher enricher = KodkodModelValidatorConfiguration.INSTANCE.getModelEnricher();
		enricher.enrichModel(newSystem, iModel);

		setProgress(60);
		boolean running = true;
		KodkodModelValidatorConfiguration configuration = KodkodModelValidatorConfiguration.INSTANCE;
		configuration.setBitwidth(bitwidth);
		List<Map<Relation, TupleSet>> solutions = new ArrayList<Map<Relation, TupleSet>>();
		List<LinkedHashMap<ClassifyingTerm, Value>> termSolutions = new ArrayList<>();
		List<List<String>> termEvalLogs = new ArrayList<>();
		List<MSystemState> systemStates = new ArrayList<>();
		List<List<Value>> otherTermSolutions = new ArrayList<>();
		List<List<String>> otherTermEvalLogs = new ArrayList<>();
		List<List<OperationEnterEvent>> transformations = new ArrayList<>(terms.size());
		do {
			if (monitor.isCanceled()) {
				error = "The process has been cancelled.";
				return null;
			}
			if (termSolutions.size() > 0) {
				Formula f = genClassifyingTermFormula(termSolutions, terms, iModel);
				((ModelConfigurator) iModel.getConfigurator()).setSolutionFormula(f);
			}
			KodkodSolver solver = new KodkodSolver();
			try {
				Solution solution = solver.solve(iModel);
				switch (solution.outcome()) {
				case SATISFIABLE:
				case TRIVIALLY_SATISFIABLE:
					newSession.reset();
					try {
						ObjectDiagramCreator creator = new ObjectDiagramCreator(iModel, newSession);
						creator.create(solution.instance().relationTuples());
						if (creator.hasDiagramErrors() == ErrorType.STRUCTURE) {
							error = "The created object diagram has structural errors!";
							return null;
						}
					} catch (UseApiException e) {
						error = "Error when creating objects: " + e.getMessage();
						return null;
					}
					solutions.add(solution.instance().relationTuples());
					Evaluator eval = new Evaluator();
					LinkedHashMap<ClassifyingTerm, Value> solutionMap = new LinkedHashMap<>(terms.size());
					List<String> solutionLogs = new ArrayList<>(terms.size());
					for (ClassifyingTerm term : terms) {
						StringWriter sr = new StringWriter();
						PrintWriter pr = new PrintWriter(sr);
						Value kodkodEval = evaluateKodkod(term, solver.evaluator());
						Value useEval = eval.eval(term.oclExpression(), newSession.system().state(), newSession.system().varBindings(), pr);
						if (kodkodEval != null && !kodkodEval.equals(useEval)) {
							error = "Kodkod and USE evaluate classifying term " + StringUtil.inQuotes(term.name())
									+ " differently. Kodkod: " + kodkodEval + "; USE: " + useEval;
							return null;
						}
						solutionMap.put(term, useEval);
						solutionLogs.add(sr.toString());
					}
					termSolutions.add(solutionMap);
					termEvalLogs.add(solutionLogs);

					// Copy system state and transform model
					session.reset();
					boolean copyRes = copySystemState(newSession.system(), session);
					if (!copyRes)
						return null;
					MatchManager manager = (type == TransformationType.FORWARD)
							? new ForwardMatchManager(session.system().state(), true)
							: new BackwardMatchManager(session.system().state(), true);
					OperationCollector collector = new OperationCollector();
					collector.subscribe(session.system().getEventBus());
					List<Match> matches;
					do {
						boolean canSucceed = false;
						matches = manager.findMatches();
						for (Match match : matches) {
							boolean result = match.run(session.system().state(), new PrintWriter(System.out));
							if (result) {
								canSucceed = true;
								break;
							}
						}
						if (!canSucceed)
							break;
					} while (matches.size() > 0);
					transformations.add(collector.getEventList());
					collector.unsubscribe(session.system().getEventBus());
					systemStates.add(session.system().state());
					List<Value> vals = new ArrayList<>(otherTerms.size());
					List<String> logs = new ArrayList<>(otherTerms.size());
					for (Expression ex : otherTerms) {
						StringWriter sr = new StringWriter();
						PrintWriter pr = new PrintWriter(sr);
						Value val = eval.eval(ex, session.system().state(), session.system().varBindings(), pr);
						vals.add(val);
						logs.add(sr.toString());
					}
					otherTermSolutions.add(vals);
					otherTermEvalLogs.add(logs);
					break;
				case UNSATISFIABLE:
				case TRIVIALLY_UNSATISFIABLE:
					running = false;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				error = "Error while solving model: " + e.getMessage();
				return null;
			}
		} while (running);
		if (termSolutions.isEmpty()) {
			error = "No solutions were found!";
			return null;
		}
		if (termSolutions.size() == systemStates.size() && systemStates.size() == otherTermSolutions.size()) {
			return new TestResult(srcTermList, trgTermList, systemStates, termSolutions, otherTermSolutions,
					type == TransformationType.FORWARD, termEvalLogs, otherTermEvalLogs, transformations, mappings);
		}
		error = String.format("List size mismatch: termSolutions = %d, systemStates = %d, otherTermSolutions = %d",
				systemStates.size(), termSolutions.size(), otherTermSolutions.size());
		return null;
	}

	private boolean copySystemState(MSystem fromSys, Session toSes) {
		MSystemState state = fromSys.state();
		UseSystemApi api = UseSystemApi.create(toSes);
		try {
			api.createObject("RuleCollection", "rc");
			for (MObject obj : state.allObjects()) {
				api.createObject(obj.cls().name(), obj.name());
			}
			for (MLink lnk : state.allLinks()) {
				api.createLink(lnk.association().name(), lnk.linkedObjects().stream().map(it -> it.name())
						.collect(Collectors.toList()).toArray(new String[lnk.linkedObjectsAsArray().length]));
			}
			for (MObject obj : state.allObjects()) {
				for (Entry<MAttribute, Value> entry : obj.state(state).attributeValueMap().entrySet()) {
					api.setAttributeValue(obj.name(), entry.getKey().name(), entry.getValue().toString());
				}
			}
			return true;
		} catch (UseApiException e) {
			error = "Error when copying system state: " + e.getMessage();
			return false;
		}
	}

	private Formula genClassifyingTermFormula(List<LinkedHashMap<ClassifyingTerm, Value>> termSolutions,
                                              List<ClassifyingTerm> classifyingTerms, IModel iModel) {
		Formula f = null;

		for (Map<ClassifyingTerm, Value> solutions : termSolutions) {
			Formula currSolution = null;

			for (ClassifyingTerm ct : classifyingTerms) {
				Value value = solutions.get(ct);
				Formula solFormula = encodeSolutionValue(ct.getKodkodExpr(), value, iModel);
				currSolution = (currSolution == null) ? solFormula : currSolution.and(solFormula);
			}

			f = (f == null) ? currSolution.not() : f.and(currSolution.not());
		}
		return f;
	}

	private Formula encodeSolutionValue(kodkod.ast.Node exp, Value value, IModel iModel) {
		if (exp instanceof kodkod.ast.Expression) {
			if (value instanceof IntegerValue) {
				return ((kodkod.ast.Expression) exp)
						.eq(IntConstant.constant(((IntegerValue) value).value()).toExpression());
			} else if (value instanceof BooleanValue) {
				Map<String, kodkod.ast.Expression> typeLiterals = iModel.typeFactory().booleanType().typeLiterals();
				if (((BooleanValue) value).value()) {
					return ((kodkod.ast.Expression) exp).eq(typeLiterals.get(TypeConstants.BOOLEAN_TRUE));
				} else {
					return ((kodkod.ast.Expression) exp).eq(typeLiterals.get(TypeConstants.BOOLEAN_FALSE));
				}
			} else if (value instanceof UndefinedValue) {
				TypeAtoms typeLiteral;
				if (value.type().isKindOfCollection(VoidHandling.INCLUDE_VOID)) {
					typeLiteral = iModel.typeFactory().undefinedSetType();
				} else {
					typeLiteral = iModel.typeFactory().undefinedType();
				}
				return ((kodkod.ast.Expression) exp).eq(typeLiteral.expression());
			} else {
				throw new TransformationException("Unsupported expression type found. (" + exp.getClass().toString()
						+ " --- " + value.getClass().toString() + ")");
			}
		} else if (exp instanceof Formula) {
			return ((BooleanValue) value).value() ? (Formula) exp : ((Formula) exp).not();
		} else if (exp instanceof IntExpression) {
			return ((IntExpression) exp).eq(IntConstant.constant(((IntegerValue) value).value()));
		}
		throw new TransformationException("Unsupported expression type found. (" + exp.getClass().toString() + " --- "
				+ value.getClass().toString() + ")");
	}

	private boolean readTerms(BufferedReader reader, List<Pair<String>> terms, List<Expression> otherTerms,
			MModel model) {
		do {
			try {
				String name = reader.readLine();
				if (name == null)
					break;
				name = name.trim();
				if (name.isEmpty())
					name = "Term " + Integer.toString(terms.size() + 1);
				String line = reader.readLine();
				if (line == null)
					break;
				line = line.trim();
				if (line.isEmpty())
					break;
				// Error checking
				StringWriter err = new StringWriter();
				Expression result = OCLCompiler.compileExpression(model, line, "<classifying term>",
						new PrintWriter(err), new VarBindings());
				if (result == null) {
					error = err.toString();
					return false;
				}
				if (!result.type().isTypeOfBoolean()) {
					error = "The expression:\n" + line + "\n must result in type 'Boolean'";
					return false;
				}
				otherTerms.add(result);
				Pair<String> term = new Pair<>();
				term.first = name;
				term.second = line;
				terms.add(term);
			} catch (IOException ignored) {
				error = ignored.getMessage();
				return false;
			}
		} while (true);
		return true;
	}

	private boolean readTerms(BufferedReader reader, List<Pair<String>> terms, List<ClassifyingTerm> cts, MModel model,
			IModel iModel, UseCTScrollingKodkodModelValidator validator) {
		do {
			try {
				String name = reader.readLine();
				if (name == null)
					break;
				name = name.trim();
				if (name.isEmpty())
					name = "Term " + Integer.toString(terms.size() + 1);
				String line = reader.readLine();
				if (line == null)
					break;
				line = line.trim();
				if (line.isEmpty())
					break;
				// Error checking
				StringWriter err = new StringWriter();
				Expression result = OCLCompiler.compileExpression(model, line, "<classifying term>",
						new PrintWriter(err), new VarBindings());
				if (result == null) {
					error = err.toString();
					return false;
				}
				if (!result.type().isTypeOfBoolean()) {
					error = "The expression:\n" + line + "\n must result in type 'Boolean'";
					return false;
				}
				Pair<String> term = new Pair<>();
				term.first = name;
				term.second = line;
				terms.add(term);
				Node obsTermKodkod;
				try {
					DefaultExpressionVisitor ev = new DefaultExpressionVisitor(iModel, new HashMap<String, Node>(),
							new HashMap<String, IClass>(), new HashMap<String, Variable>(), new ArrayList<String>());
					result.processWithVisitor(ev);
					obsTermKodkod = (Node) ev.getObject();
					ClassifyingTerm ct = new ClassifyingTerm(name, result, obsTermKodkod);
					cts.add(ct);
				} catch (TransformationException ex) {
					error = "The expression cannot be transformed by the model validator. Reason: " + ex.getMessage();
					return false;
				}

			} catch (IOException ignored) {
				error = ignored.getMessage();
				return false;
			}
		} while (true);
		return true;
	}

	private Configuration getConfig(HierarchicalINIConfiguration hierarchicalINIConfiguration) {
		if (hierarchicalINIConfiguration.getSections().isEmpty()) {
			return hierarchicalINIConfiguration.getSection(null);
		} else {
			String section = hierarchicalINIConfiguration.getSections().iterator().next();
			return hierarchicalINIConfiguration.getSection(section);
		}
	}

	protected Value evaluateKodkod(ClassifyingTerm ct, kodkod.engine.Evaluator evaluatorEnginge) {
		Value kodkodEval = null;
		if (evaluatorEnginge != null) {
			if (ct.getKodkodExpr() instanceof kodkod.ast.Expression) {
				TupleSet res = evaluatorEnginge.evaluate((kodkod.ast.Expression) ct.getKodkodExpr());
				// result should always be a single value of Integer, Undefined, Undefined_Set,
				// Boolean_True or Boolean_False
				if (res.arity() == 1) {
					Object intermRes = evaluatorEnginge.instance().universe().atom(res.iterator().next().index());

					if (intermRes instanceof Integer) {
						kodkodEval = IntegerValue.valueOf((int) intermRes);
					} else if (intermRes instanceof String) {
						switch ((String) intermRes) {
						case TypeConstants.UNDEFINED:
						case TypeConstants.UNDEFINED_SET:
							kodkodEval = UndefinedValue.instance;
							break;
						case TypeConstants.BOOLEAN_TRUE:
							kodkodEval = BooleanValue.TRUE;
							break;
						case TypeConstants.BOOLEAN_FALSE:
							kodkodEval = BooleanValue.FALSE;
							break;
						}
					}
				} else {
					System.err.println("Arity of kodkod result is not one!" + StringUtil.NEWLINE + "Classifying term "
							+ StringUtil.inQuotes(ct.name()) + " yields: " + res.toString());
				}
			} else if (ct.getKodkodExpr() instanceof IntExpression) {
				int res = evaluatorEnginge.evaluate((IntExpression) ct.getKodkodExpr());
				kodkodEval = IntegerValue.valueOf(res);
			} else if (ct.getKodkodExpr() instanceof Formula) {
				boolean res = evaluatorEnginge.evaluate((Formula) ct.getKodkodExpr());
				kodkodEval = BooleanValue.get(res);
			}
		}
		return kodkodEval;
	}
	
	private boolean readMappings(File mappingFile, List<Mapping> mappings, int numLeftTerms, int numRightTerms) {
		try (BufferedReader reader = new BufferedReader(new FileReader(mappingFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Mapping m = Mapping.fromString(line, numLeftTerms, numRightTerms);
				if (m != null)
					mappings.add(m);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = e.getMessage();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = e.getMessage();
			return false;
		}
		return true;
	}

	@Override
	protected void done() {
		if (error != null) {
			JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
