package org.uet.dse.rtlplus.parser.ast.tgg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.tzi.use.uml.mm.MInvalidModelException;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemException;
import org.uet.dse.rtlplus.mm.MCorrLink;
import org.uet.dse.rtlplus.mm.MPattern;
import org.uet.dse.rtlplus.parser.Context;

public class AstCorr {
	
	private List<AstCorrLink> linkList = new ArrayList<>(1);
	private List<AstInvariantTgg> invList = new ArrayList<>(1);

	public void addCorrLink(AstCorrLink _corrLnk) {
		linkList.add(_corrLnk);
	}

	public void addInv(AstInvariantTgg _inv) {
		invList.add(_inv);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (AstCorrLink lnk : linkList) {
			sb.append(lnk.toString()).append('\n');
		}
		for (AstInvariantTgg inv : invList) {
			sb.append(inv.toString()).append('\n');
		}
		return sb.toString();
	}

	public MPattern gen(Context ctx) throws MInvalidModelException, MSystemException {
		List<MObject> objs = new ArrayList<>();
		List<MLink> lnks = new ArrayList<>();
		Map<String, List<AstInvariantCondition>> invs = new LinkedHashMap<>();
		for (AstCorrLink corrLink : linkList) {
			MCorrLink lnk = corrLink.gen(ctx);
			objs.add(lnk.getObj());
			lnks.add(lnk.getSrcLink());
			lnks.add(lnk.getTrgLink());
		}
		for (AstInvariantTgg inv : invList) {
			invs.put(inv.getName(), inv.getConditions());
		}
		return new MPattern(ctx.systemState(), objs, lnks, invs);
	}

	public MPattern gen(Context ctx, List<String> srcLnkCons, List<String> trgLnkCons) throws MInvalidModelException, MSystemException {
		List<MObject> objs = new ArrayList<>();
		List<MLink> lnks = new ArrayList<>();
		Map<String, List<AstInvariantCondition>> invs = new LinkedHashMap<>();
		for (AstCorrLink corrLink : linkList) {
			MCorrLink lnk = corrLink.gen(ctx, srcLnkCons, trgLnkCons);
			objs.add(lnk.getObj());
			lnks.add(lnk.getSrcLink());
			lnks.add(lnk.getTrgLink());
		}
		for (AstInvariantTgg inv : invList) {
			invs.put(inv.getName(), inv.getConditions());
		}
		return new MPattern(ctx.systemState(), objs, lnks, invs);
	}
}
