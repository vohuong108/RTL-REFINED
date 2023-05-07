package org.uet.dse.rtlplus.parser.ast.tgg;

import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemException;
import org.uet.dse.rtlplus.mm.MPattern;
import org.uet.dse.rtlplus.mm.MRule;
import org.uet.dse.rtlplus.parser.Context;

public class AstRule {

	private AstPattern lhs;
	private AstPattern rhs;
	
	public AstRule(AstPattern _lhs, AstPattern _rhs) {
		lhs = _lhs;
		rhs = _rhs;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(\n").append(lhs.toString())
			.append("){\n").append(rhs.toString())
			.append("\n}");
		return sb.toString();
	}

	public MRule gen(Context ctx) {
		ctx.setSystemState(ctx.getLhsState());
		try {
			MPattern left = lhs.gen(ctx);
			// Copy the LHS state to the RHS state because the rules are non-deleting
			ctx.setSystemState(ctx.getRhsState());
			for (MObject obj : ctx.getLhsState().allObjects()) {
				if (!ctx.getRhsState().hasObjectWithName(obj.name()))
					ctx.getRhsState().createObject(obj.cls(), obj.name());
			}
			for (MLink lnk : ctx.getLhsState().allLinks()) {
				if (!ctx.getRhsState().hasLink(lnk.association(), lnk.linkedObjects(), null)) 
					ctx.getRhsState().createLink(lnk.association(), lnk.linkedObjects(), null);
			}
			MPattern right = rhs.gen(ctx);
			return new MRule(left, right);
		} catch (MSystemException e) {
			e.printStackTrace();
		}
		return null;
	}

}
