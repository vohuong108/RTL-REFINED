package org.uet.dse.rtlplus.matching;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.mm.MOperation;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;

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

	/* Start finding a match */
	public List<Map<String, MObject>> run() {
		if (ruleObjects.isEmpty())
			return null;
		findMatchAtPosition(new LinkedHashMap<String, MObject>(), 0);
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
			if (checkLinks(objMap))
				matches.add(objMap);
			//System.out.println(matches);
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
}
