package org.uet.dse.rtlplus.parser.ast.tgg;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemException;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.parser.Context;

public class AstLink {
	
	public List<String> objectList = new ArrayList<>(2);
	public String association;

	public void setAssociation(Object object) {
		association = (String) object;
	}

	public void addObject(Object object) {
		objectList.add((String) object);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append(String.join(",", objectList))
			.append("):").append(association);
		return sb.toString();
	}

	public MLink gen(Context ctx) throws MSystemException {
		MSystemState systemState = ctx.systemState();
		MAssociation asc = ctx.getDiagramModel().getAssociation(association);
		List<MObject> objs = objectList.stream().map(it -> systemState.objectByName(it)).collect(Collectors.toList());
		Set<MLink> lnks = systemState.linkBetweenObjects(asc, objs);
		if (lnks.isEmpty()) 
			return systemState.createLink(asc, objs, null);
	
		else
			return lnks.iterator().next();
	}

}
