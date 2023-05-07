package org.uet.dse.rtlplus.matching;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.mm.MOperation;
import org.tzi.use.uml.mm.MPrePostCondition;
import org.tzi.use.uml.ocl.expr.Evaluator;
import org.tzi.use.uml.ocl.expr.Expression;
import org.tzi.use.uml.ocl.expr.VarDecl;
import org.tzi.use.uml.ocl.type.TupleType;
import org.tzi.use.uml.ocl.type.Type.VoidHandling;
import org.tzi.use.uml.ocl.value.BooleanValue;
import org.tzi.use.uml.ocl.value.ObjectValue;
import org.tzi.use.uml.ocl.value.TupleValue;
import org.tzi.use.uml.ocl.value.TupleValue.Part;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.util.soil.VariableEnvironment;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.mm.MTggRule;

public abstract class MatchManager {
	protected MSystemState systemState;
	protected boolean sync;

	public MatchManager(MSystemState systemState, boolean sync) {
		super();
		this.systemState = systemState;
		this.sync = sync;
	}

	public MOperation findOperation(String ruleName, String suffix) {
		MClass rc = systemState.system().model().getClass("RuleCollection");
		MOperation op = rc.operation(ruleName + suffix, false);
		if (op == null) {
			return null;
		}
		return op;
	}

	protected boolean validatePreconditions(MOperation op, Map<String, MObject> objs) {
		// System.out.println("========== VALIDATING PRECONDITIONS ============");
		// System.out.println("Objects: " + objs.toString());
		VariableEnvironment varEnv = systemState.system().getVariableEnvironment();
		for (VarDecl varDecl : op.paramList()) {
			if (varDecl.type().isKindOfTupleType(VoidHandling.EXCLUDE_VOID)) {
				TupleType type = (TupleType) varDecl.type();
				List<Part> parts = new ArrayList<>();
				// System.out.println(objs.toString());
				// System.out.println(type.getParts().keySet());
				for (String key : type.getParts().keySet()) {
					parts.add(new TupleValue.Part(0, key, new ObjectValue(objs.get(key).cls(), objs.get(key))));
				}
				TupleValue tuple = new TupleValue(type, parts);
				varEnv.assign(varDecl.name(), tuple);
			}
		}
		// System.out.println(varEnv);
		for (MPrePostCondition pre : op.preConditions()) {
			Expression ex = pre.expression();
			Evaluator eval = new Evaluator();
			try {
				Value valid = eval.eval(ex, systemState, systemState.system().varBindings());
				if (valid.isBoolean() && ((BooleanValue) valid).isFalse()) {
					varEnv.clear();
					// System.out.println("=========== FALSE ==================");
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				varEnv.clear();
				return false;
			}
		}
		varEnv.clear();

		// System.out.println("=========== TRUE: " + objs.toString());
		return true;
	}

	public List<Match> findMatches() {
		return findMatchesForRules(Main.getTggRuleCollection().getRuleList());
	}

	public List<Match> findMatchesForObjects(List<MObject> objects) {
		return findMatchesForRulesAndObjects(Main.getTggRuleCollection().getRuleList(), objects);
	}	
	public abstract List<Match> findMatchesForRuleAndObjects(MTggRule rule, List<MObject> objects);
	
	public List<Match> findMatchesForRulesAndObjects(Collection<MTggRule> ruleList, List<MObject> objects) {
		List<Match> matches = new ArrayList<>();
		if (ruleList != null) {
			for (MTggRule rule : ruleList) {
				List<Match> currentMatches = findMatchesForRuleAndObjects(rule, objects);
				if (currentMatches != null) matches.addAll(currentMatches);
			}
		}
		if (Main.matchComparator != null)
			matches.sort(Main.matchComparator);
		return matches;
	}

	public abstract List<Match> findMatchForRule(MTggRule rule);

	public List<Match> findMatchesForRules(Collection<MTggRule> ruleList) {
		List<Match> matches = new ArrayList<>();
		for (MTggRule rule : ruleList) {
			List<Match> currentMatches = findMatchForRule(rule);
			if (currentMatches != null) matches.addAll(currentMatches);
		}
		if (Main.matchComparator != null)
			matches.sort(Main.matchComparator);
		return matches;
	}
}
