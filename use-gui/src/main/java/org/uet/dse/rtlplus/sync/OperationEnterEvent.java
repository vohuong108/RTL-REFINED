package org.uet.dse.rtlplus.sync;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tzi.use.uml.sys.MObject;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;

public class OperationEnterEvent {
	private TransformationType type;
	private Map<String, String> matchedObjects;
	private Map<String, String> objectsToCreate;
	private Map<String, String> corrObjsToCreate;
	private List<CachedLink> linksToCreate;
	private Set<String> matchedCorrObjs;
	private String opName;
	private String ruleName;
	private List<String> commands;

	public OperationEnterEvent(TransformationType type, Map<String, MObject> matchedObjects,
			Map<String, String> objectsToCreate, Map<String, String> corrObjsToCreate, List<CachedLink> linksToCreate,
			Set<String> corrParams, String opName, String ruleName, List<String> commands) {
		super();
		this.type = type;
		this.matchedObjects = new HashMap<>();
		for (Map.Entry<String, MObject> entry : matchedObjects.entrySet()) {
			this.matchedObjects.put(entry.getKey(), entry.getValue().name());
		}
		this.objectsToCreate = objectsToCreate;
		this.corrObjsToCreate = corrObjsToCreate;
		this.linksToCreate = linksToCreate;
		this.matchedCorrObjs = new HashSet<>();
		for (String param : corrParams) {
			matchedCorrObjs.add(matchedObjects.get(param).name());
		}
		this.opName = opName;
		this.ruleName = ruleName;
		this.commands = commands;
	}

	public Map<String, String> getMatchedObjects() {
		return matchedObjects;
	}

	public Set<String> getMatchedCorrObjs() {
		return matchedCorrObjs;
	}

	public TransformationType getType() {
		return type;
	}

	public String getOpName() {
		return opName;
	}

	public Map<String, String> getObjectsToCreate() {
		return objectsToCreate;
	}

	public Map<String, String> getCorrObjsToCreate() {
		return corrObjsToCreate;
	}

	public List<CachedLink> getLinksToCreate() {
		return linksToCreate;
	}

	public String getRuleName() {
		return ruleName;
	}

	public List<String> getCommands() {
		return commands;
	}

	@Override
	public String toString() {
		return opName;
	}

}
