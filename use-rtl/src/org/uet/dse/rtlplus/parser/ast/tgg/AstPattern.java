package org.uet.dse.rtlplus.parser.ast.tgg;

import java.util.ArrayList;
import java.util.List;

import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemException;
import org.uet.dse.rtlplus.mm.MPattern;
import org.uet.dse.rtlplus.parser.Context;

public class AstPattern {
	private List<AstObject> objList = new ArrayList<>();
	private List<AstLink> lnkList = new ArrayList<>();
	private List<String> condList = new ArrayList<>();

	public void addObject(AstObject obj) {
		objList.add(obj);
	}

	public void addLink(AstLink lnk) {
		lnkList.add(lnk);
	}

	public void addCondition(String cond) {
		condList.add(cond);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (AstObject obj : objList) {
			sb.append(obj.toString()).append('\n');
		}
		for (AstLink lnk : lnkList) {
			sb.append(lnk.toString()).append('\n');
		}
		for (String cond : condList) {
			sb.append('[').append(cond).append(']').append('\n');
		}
		return sb.toString();
	}

	public MPattern gen(Context ctx) throws MSystemException {
		List<MObject> objects = new ArrayList<>();
		List<MLink> links = new ArrayList<>();
		for (AstObject obj : objList) {
			objects.add(obj.gen(ctx));
		}
		for (AstLink lnk : lnkList) {
			links.add(lnk.gen(ctx));
		}
		return new MPattern(ctx.systemState(), objects, links, condList);
	}

}
