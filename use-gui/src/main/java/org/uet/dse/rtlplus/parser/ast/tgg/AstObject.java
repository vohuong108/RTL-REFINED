package org.uet.dse.rtlplus.parser.ast.tgg;

import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MObjectState;
import org.tzi.use.uml.sys.MSystemException;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.parser.Context;

public class AstObject {
	
	private String name;
	private String className;
	
	public AstObject(Object object, Object object2) {
		name = (String) object;
		className = (String) object2;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(':').append(className);
		return sb.toString();
	}

	public MObject gen(Context ctx) throws MSystemException {
		MSystemState systemState = ctx.systemState();
		MClass cls = ctx.getDiagramModel().getClass(className);
		MObject existingObj = systemState.objectByName(name);
		if (existingObj == null) {
			return systemState.createObject(cls, name);
		}
		else {
			MObjectState objState = new MObjectState(existingObj);
			systemState.restoreObject(objState);
			return objState.object();
		}
	}

}
