package org.uet.dse.rtlplus.parser.ast.coevol;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.tzi.use.uml.sys.MSystemState;

public class AstCoevolScenario {
	
	private List<AstRuleApplication> applications = new ArrayList<>();

	public void addRuleApplication(AstRuleApplication ruleApplication1) {
		applications.add(ruleApplication1);
	}

	public boolean execute(MSystemState state, PrintWriter logWriter) {
		for (AstRuleApplication app : applications) {
			boolean result = app.run(state, logWriter);
			if (!result) 
				return result;
		}
		return true;
	}

}
