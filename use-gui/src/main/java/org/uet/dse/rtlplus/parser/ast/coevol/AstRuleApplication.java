package org.uet.dse.rtlplus.parser.ast.coevol;

import java.io.PrintWriter;

import org.tzi.use.uml.sys.MSystemState;

public abstract class AstRuleApplication {

	public abstract boolean run(MSystemState state, PrintWriter logWriter);

}
