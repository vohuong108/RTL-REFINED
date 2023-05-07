package org.uet.dse.rtlplus.parser.ast.coevol;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.matching.CoevolutionMatchManager;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.mm.MTggRule;

public class AstRuleSimpleApplication extends AstRuleApplication {

	private String ruleName;
	public AstRuleSimpleApplication(Object object) {
		ruleName = object.toString();
	}
	@Override
	public boolean run(MSystemState state, PrintWriter logWriter) {
		MTggRule rule = Main.getTggRuleCollection().getRuleByName(ruleName);
		if (rule == null) {
			logWriter.print("Cannot find rule: " + ruleName);
			return false;
		}
		CoevolutionMatchManager manager = new CoevolutionMatchManager(state, false);
		List<Match> matches = manager.findMatchesForRules(Arrays.asList(rule));
		if (matches.isEmpty()) {
			logWriter.println("No match found for rule " + ruleName);
			return false;
		}
		else {
			for (Match match : matches) {
				boolean result = match.run(state, logWriter);
				if (result)
					return result;
			}
			logWriter.println("Failed to run " + ruleName);
		}
		return false;
	}

}
