package org.uet.dse.rtlplus.objectdiagram;

import java.util.Collection;

import org.tzi.use.uml.sys.MObject;

public class MatchSelectedEvent {
	private Collection<MObject> matchedObjects;

	public MatchSelectedEvent(Collection<MObject> matchedObjects) {
		super();
		this.matchedObjects = matchedObjects;
	}

	public Collection<MObject> getMatchedObjects() {
		return matchedObjects;
	}
}
