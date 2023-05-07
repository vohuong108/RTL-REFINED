package org.uet.dse.rtlplus.sync;

public class CachedLink {
	private String association;
	private String[] linkedObjects;
	public String getAssociation() {
		return association;
	}
	public String[] getLinkedObjects() {
		return linkedObjects;
	}
	public CachedLink(String association, String[] linkedObjects) {
		super();
		this.association = association;
		this.linkedObjects = linkedObjects;
	}
	
	@Override
	public String toString() {
		return association + "(" + String.join(", ", linkedObjects) + ")";
	}
}
