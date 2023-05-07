package org.uet.dse.rtlplus.mm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MLinkEnd;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.parser.ast.tgg.AstInvariantCondition;

public class MPattern {

	private MSystemState systemState;
	private List<MObject> objectList;
	private List<MLink> linkList;
	private List<String> conditionList;
	private Map<String, List<AstInvariantCondition>> invariantList;
	private Map<String, List<String>> objMap;

	public MSystemState getSystemState() {
		return systemState;
	}

	public List<MObject> getObjectList() {
		return objectList;
	}

	public List<MLink> getLinkList() {
		return linkList;
	}

	public List<String> getConditionList() {
		return conditionList;
	}

	public Map<String, List<AstInvariantCondition>> getInvariantList() {
		return invariantList;
	}

	public MPattern(MSystemState systemState, List<MObject> objectList, List<MLink> linkList,
			List<String> conditionList) {
		this.systemState = systemState;
		this.objectList = objectList;
		this.linkList = linkList;
		this.conditionList = conditionList;
		createObjectMap();
	}

	public MPattern(MSystemState systemState, List<MObject> objectList, List<MLink> linkList,
			Map<String, List<AstInvariantCondition>> invariantList) {
		this.systemState = systemState;
		this.objectList = objectList;
		this.linkList = linkList;
		this.invariantList = invariantList;
		createObjectMap();
	}

	private void createObjectMap() {
		objMap = new LinkedHashMap<>();
		for (MObject obj : objectList) {
			List<String> objs = objMap.get(obj.cls().name());
			if (objs == null) {
				objs = new ArrayList<>();
				objMap.put(obj.cls().name(), objs);
			}
			objs.add(obj.name());
		}
	}

	public String genPreCondition(boolean hasLineBreaks) {
		List<String> conditions = new ArrayList<>();
		// Links exist
		for (MLink lnk : linkList) {
			for (MLinkEnd end : lnk.linkEnds()) {
				for (MLinkEnd otherEnd : lnk.linkEnds()) {
					if (!end.equals(otherEnd)) {
						// theObjA.roleB->includes(theObjB)
						String con = end.object().name() + "." + otherEnd.associationEnd().nameAsRolename()
								+ "->includes(" + otherEnd.object().name() + ")";
						conditions.add(con);
					}
				}
			}
		}
		// Conditions satisfied
		if (conditionList != null)
			conditions.addAll(conditionList);
		return conditions.stream().collect(Collectors.joining(hasLineBreaks ? " and \n" : " and "));
	}

	public void genPostCondExisting(StringBuilder builder) {
		StringJoiner joiner = new StringJoiner(" and\n");
		for (Entry<String, List<String>> entry : objMap.entrySet()) {
			// Table.allInstances->includesAll(Set{theTableA,theTableB})
			StringJoiner objString = new StringJoiner(", ", entry.getKey() + ".allInstances->includesAll(Set {",
					"})\n");
			for (String objName : entry.getValue()) {
				objString.add(objName);
			}
			joiner.add(objString.toString());
		}
		builder.append(joiner.toString());
	}

	public int genPostCondNew(StringBuilder builder, int level) {
		// Creation of new objects
		for (Entry<String, List<String>> entry : objMap.entrySet()) {
			// Table.allInstances->exists(theTableA,theTableB|
			builder.append("(" + entry.getKey() + ".allInstances - " + entry.getKey() + ".allInstances@pre)->exists("
					+ entry.getValue().stream().collect(Collectors.joining(", ")) + "|\n");
			level++;
		}
		// Existence of new links
		List<String> conditions = new ArrayList<>();
		for (MLink lnk : linkList) {
			for (MLinkEnd end : lnk.linkEnds()) {
				for (MLinkEnd otherEnd : lnk.linkEnds()) {
					if (!end.equals(otherEnd)) {
						// theObjA.roleB->includes(theObjB)
						String con = end.object().name() + "." + otherEnd.associationEnd().nameAsRolename()
								+ "->includes(" + otherEnd.object().name() + ")";
						conditions.add(con);
					}
				}
			}
		}
		// OCL conditions
		if (conditionList != null)
			conditions.addAll(conditionList);
		// Attribute invariants
		if (invariantList != null) {
			for (MObject obj : objectList) {
				List<AstInvariantCondition> invs = invariantList.get(obj.cls().name());
				if (invs != null) {
					for (AstInvariantCondition inv : invs) {
						conditions.add(inv.getCondition().replace("self.", obj.name() + "."));
					}
				}
			}
		}

		builder.append(conditions.stream().collect(Collectors.joining(" and\n")));
		return level;
	}
	
	public String genPostCondNew() {
		StringBuilder builder = new StringBuilder();
		// Existence of new links
		List<String> conditions = new ArrayList<>();
		for (MLink lnk : linkList) {
			for (MLinkEnd end : lnk.linkEnds()) {
				for (MLinkEnd otherEnd : lnk.linkEnds()) {
					if (!end.equals(otherEnd)) {
						// theObjA.roleB->includes(theObjB)
						String con = end.object().name() + "." + otherEnd.associationEnd().nameAsRolename()
								+ "->includes(" + otherEnd.object().name() + ")";
						conditions.add(con);
					}
				}
			}
		}
		// OCL conditions
		if (conditionList != null)
			conditions.addAll(conditionList);
		// Attribute invariants
		if (invariantList != null) {
			for (MObject obj : objectList) {
				List<AstInvariantCondition> invs = invariantList.get(obj.cls().name());
				if (invs != null) {
					for (AstInvariantCondition inv : invs) {
						conditions.add(inv.getCondition().replace("self.", obj.name() + "."));
					}
				}
			}
		}
		
		builder.append(conditions.stream().collect(Collectors.joining(" and\n")));
		return builder.toString();
	}
}
