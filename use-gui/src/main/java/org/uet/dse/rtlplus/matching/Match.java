package org.uet.dse.rtlplus.matching;

import java.io.PrintWriter;
import java.util.Map;

import org.tzi.use.uml.mm.MOperation;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.mm.MTggRule;

public abstract class Match {

	protected MOperation operation;
	protected MTggRule rule;
	protected Map<String, MObject> objectList;
	protected boolean sync;

	public Match(MTggRule rule, MOperation operation, Map<String, MObject> objectList, boolean sync) {
		super();
		this.operation = operation;
		this.rule = rule;
		this.objectList = objectList;
		this.sync = sync;
	}
	
	public abstract boolean run(MSystemState systemState, PrintWriter logWriter);
	
	public abstract int getNewObjectsNum();
	public abstract int getNewLinksNum();
	public abstract int getNewCorrsNum();
	
	public String toString() {
		return operation.name() + ": " + objectList.toString();
	}
	
	public MTggRule getRule() {
		return rule;
	}
	
	public Map<String, MObject> getObjectList() {
		return objectList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objectList == null) ? 0 : objectList.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + (sync ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (objectList == null) {
			if (other.objectList != null)
				return false;
		} else if (!objectList.equals(other.objectList))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (sync != other.sync)
			return false;
		return true;
	}
	
}
