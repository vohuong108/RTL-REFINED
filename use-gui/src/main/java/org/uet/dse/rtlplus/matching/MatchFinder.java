package org.uet.dse.rtlplus.matching;

import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import org.tzi.use.parser.ocl.OCLCompiler;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.mm.MAssociationEnd;
import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.mm.MOperation;
import org.tzi.use.uml.ocl.expr.Evaluator;
import org.tzi.use.uml.ocl.value.BooleanValue;
import org.tzi.use.uml.ocl.value.ObjectValue;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.ocl.value.VarBindings;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MLinkSet;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.util.NullWriter;
import org.tzi.use.util.soil.VariableEnvironment;
import org.uet.dse.rtlplus.mm.MTggRule;

/**
 * Searches the system for objects and links that match the specification. Is
 * quite slow :(
 */

public class MatchFinder {

	private MSystemState systemState;
	private MOperation operation;
	private List<MObject> ruleObjects;
	private List<MLink> ruleLinks;
	private List<Map<String, MObject>> matches;

	// vv.huong
	private MTggRule rule;
	private String type;
	private Map<String, MObject> srgAndTrg;

	public MatchFinder(MSystemState systemState, MOperation operation, List<MObject> ruleObjects) {
		super();
		this.systemState = systemState;
		this.operation = operation;
		this.ruleObjects = ruleObjects;
		this.matches = new ArrayList<Map<String, MObject>>();
	}

	public MatchFinder(MSystemState systemState, MOperation operation, List<MObject> ruleObjects,
			List<MLink> ruleLinks) {
		this(systemState, operation, ruleObjects);
		this.ruleLinks = ruleLinks;
	}

	// vv.huong
	public MatchFinder(MSystemState systemState,
					   MOperation operation,
					   List<MObject> ruleObjects,
					   List<MLink> ruleLinks,
					   MTggRule rule,
					   String type
					   ) {
		this(systemState, operation, ruleObjects);
		this.ruleLinks = ruleLinks;
		this.rule = rule;
		this.type = type;
	}

	// vv.huong
	public MatchFinder(MSystemState systemState,
					   MOperation operation,
					   List<MObject> ruleObjects,
					   MTggRule rule,
					   String type,
					   Map<String, MObject> objs
	) {
		this(systemState, operation, ruleObjects);
		this.rule = rule;
		this.type = type;
		this.srgAndTrg = new HashMap<>(objs);
	}

	/* Start finding a match */
	public List<Map<String, MObject>> run() {
		if (ruleObjects.isEmpty())
			return null;
		// vv.huong
		if("COR".equals(type)) {
			findCorrMatch();
		} else {
			findMatchAtPosition(new LinkedHashMap<String, MObject>(), 0);
		}
		// TODO: this is empty when a match fails because findMatchAtPosition is not
		// called recursively anymore, but not empty when there are actually 0 objects to
		// match because if (...) returns true. Hm.
		return matches;
	}
	
	public List<Map<String, MObject>> run(List<MObject> objs) {
		if (ruleObjects.isEmpty())
			return null;
		findMatchAtPosition(new LinkedHashMap<String, MObject>(), 0, objs);
		return matches;
	}

	private void findMatchAtPosition(Map<String, MObject> objs, int position, List<MObject> objects) {
		// TODO: What to do when there are no objects of a class X?
		// TODO: Improve this by checking for links/conditions
		// TODO: CorrRule?
		// TODO: Filter by object?
		if (position >= ruleObjects.size()) {
			for (MObject object : objects) {
				if (objs.containsValue(object)) {
					Map<String, MObject> objMap = new LinkedHashMap<String, MObject>(objs);
					if (checkLinks(objMap))
						matches.add(objMap);
					matches.add(objMap);
				}
			}
			// System.out.println(matches);
		} else {
			MClass cls = ruleObjects.get(position).cls();
			for (MObject obj : systemState.objectsOfClassAndSubClasses(cls)) {
				String varName = ruleObjects.get(position).name();
				if (!objs.containsValue(obj)) {
					objs.put(varName, obj);
					findMatchAtPosition(objs, position + 1);
					objs.remove(varName);
				}
			}
		}
	}

	private void findMatchAtPosition(Map<String, MObject> objs, int position) {
		// TODO: What to do when there are no objects of a class X?
		// TODO: Improve this by checking for links/conditions
		// TODO: CorrRule?
		// TODO: Filter by object?
		if (position >= ruleObjects.size()) {
			Map<String, MObject> objMap = new LinkedHashMap<String, MObject>(objs);
			// vv.huong
			if (checkLinksAndPreCond(objMap)){
				matches.add(objMap);
				return;
			}

			//System.out.println(matches);
		} else {
			MClass cls = ruleObjects.get(position).cls();

			// vv.huong
			var objectsCls = systemState.objectsOfClassAndSubClasses(cls).stream().sorted(
					(x, y) -> {
						int a = Integer.valueOf(x.name().replaceAll("[^\\d]", ""));
						int b = Integer.valueOf(y.name().replaceAll("[^\\d]", ""));
						return a - b;
					}
			).collect(Collectors.toList());

			for (MObject obj : objectsCls) {
				String varName = ruleObjects.get(position).name();
				if (!objs.containsValue(obj)) {
					objs.put(varName, obj);
					findMatchAtPosition(objs, position + 1);
					// vv.huong
					if (matches.size() == 1 && !"TRG".equals(type)) {
						return;
					}
					objs.remove(varName);
				}
			}
		}
	}

	private boolean checkLinks(Map<String, MObject> objMap) {
		if (ruleLinks != null) {
			boolean hasLinks = true;
			for (MLink lnk : ruleLinks) {
				MObject obj1 = objMap.get(lnk.getLinkEnd(0).object().name());
				MObject obj2 = objMap.get(lnk.getLinkEnd(1).object().name());
				if (obj1 == null || obj2 == null) {
					// System.out.println(lnk.getLinkEnd(0).object().name() + " or " + lnk.getLinkEnd(1).object().name() + " is null!");
					hasLinks = false;
					break;
				}
				if (!systemState.hasLinkBetweenObjects(systemState.system().model().getAssociation(lnk.association().name()), obj1, obj2)) {
					// System.out.println("No assoc " + lnk.association().name() + " between " + obj1.name() + " and " + obj2.name());
					hasLinks = false;
					break;
				}
			}
			return hasLinks;
		} else 
			return true;
	}

	// vv.huong
	private boolean checkLinksAndPreCond(Map<String, MObject> objMap) {
		if (objMap != null) {
			VariableEnvironment varEnv = systemState.system().getVariableEnvironment();
			String preCond = "";

			if ("SRC".equals(type)) {
				preCond = rule.getSrcRule().genPreCondBoth(false);
			} else if ("TRG".equals(type)) {
				preCond = rule.getTrgRule().genPreCondLeft(false);
			}

			if (!preCond.isEmpty()) {

				for (Map.Entry<String, MObject> e : objMap.entrySet()) {
					varEnv.assign(e.getKey(), new ObjectValue(e.getValue().cls(), e.getValue()));
				}

				VarBindings vbs = varEnv.constructVarBindings();
				Evaluator ev = new Evaluator();
				Value val = ev.eval(OCLCompiler.compileExpression(systemState.system().model(), preCond, "rtl", new PrintWriter(new NullWriter()), vbs), systemState, vbs);

				if (val.isBoolean() && ((BooleanValue) val).isFalse()) {
					varEnv.clear();
					return false;
				}
			}

			varEnv.clear();

		}
		if (ruleLinks != null) {
			boolean hasLinks = true;
			for (MLink lnk : ruleLinks) {
				MObject obj1 = objMap.get(lnk.getLinkEnd(0).object().name());
				MObject obj2 = objMap.get(lnk.getLinkEnd(1).object().name());
				if (obj1 == null || obj2 == null) {
					// System.out.println(lnk.getLinkEnd(0).object().name() + " or " + lnk.getLinkEnd(1).object().name() + " is null!");
					hasLinks = false;
					break;
				}
				if (!systemState.hasLinkBetweenObjects(systemState.system().model().getAssociation(lnk.association().name()), obj1, obj2)) {
					// System.out.println("No assoc " + lnk.association().name() + " between " + obj1.name() + " and " + obj2.name());
					hasLinks = false;
					break;
				}
			}
			return hasLinks;
		} else
			return true;
	}

	// vv.huong
	private void findCorrMatch() {
		List<MLink> links = rule.getCorrRule().getLhs().getLinkList();
		Map<String, List<MObject>> corrObjMap = new HashMap<>();

		for (int i = 0; i < links.size(); i+=2) {
			MAssociation assocL = links.get(i).association();
			MLinkSet mLinkSetL = systemState.linksOfAssociation(assocL);
			List<MAssociationEnd> assocEndsL = mLinkSetL.association().associationEnds();
			String nameL = links.get(i).getLinkEnd(0).object().name();
			String nameCorrL = links.get(i).getLinkEnd(1).object().name();

			MAssociation assocR = links.get(i+1).association();
			MLinkSet mLinkSetR = systemState.linksOfAssociation(assocR);
			List<MAssociationEnd> assocEndsR = mLinkSetR.association().associationEnds();
			String nameR = links.get(i+1).getLinkEnd(0).object().name();
			String nameCorrR = links.get(i+1).getLinkEnd(1).object().name();

			if (nameCorrL.equals(nameCorrR)) {
				Set<MObject> mObjL = mLinkSetL
						.select(assocEndsL.get(0), srgAndTrg.get(nameL))
						.stream().map(x -> x.getLinkEnd(1).object()).collect(Collectors.toSet());
				Set<MObject> mObjR = mLinkSetR
						.select(assocEndsR.get(0), srgAndTrg.get(nameR))
						.stream().map(x -> x.getLinkEnd(1).object()).collect(Collectors.toSet());

				List<MObject> result = Sets.intersection(mObjL, mObjR).stream().collect(Collectors.toList());

				if (result.size() > 0) {
					corrObjMap.put(nameCorrR, result);
				} else {
					return;
				}
			}
		}

		genarateCorrMatch(new LinkedHashMap<String, MObject>(), 0, corrObjMap);

	}

	// vv.huong
	private void genarateCorrMatch(Map<String, MObject> objs, int position, Map<String, List<MObject>> data) {
		if (position >= ruleObjects.size()) {
			Map<String, MObject> objMap = new LinkedHashMap<String, MObject>(objs);
			matches.add(objMap);
		} else {
			String clsName = ruleObjects.get(position).name();

			for (MObject obj : data.get(clsName)) {
				String varName = ruleObjects.get(position).name();
				objs.put(varName, obj);
				genarateCorrMatch(objs, position + 1, data);
				objs.remove(varName);
			}
		}
	}
}
