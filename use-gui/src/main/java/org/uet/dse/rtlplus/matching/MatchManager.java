package org.uet.dse.rtlplus.matching;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
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
					parts.add(new Part(0, key, new ObjectValue(objs.get(key).cls(), objs.get(key))));
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

	public List<Match> findMatches(UseSystemApi api) {
		return findMatchesForRules(Main.getTggRuleCollection().getRuleList(), api);
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

	public List<Match> findMatchesForRules(Collection<MTggRule> ruleList, UseSystemApi api) {
		List<Match> matches = new ArrayList<>();
		for (MTggRule rule : ruleList) {
			// vv.huong
			String ruleName = rule.getName();

			try {
				if ("JobReq2JobCol".equals(ruleName)){
					Value val = api.evaluate("JR2JC.allInstances()->size() = 1");
					if (val.isBoolean() && ((BooleanValue) val).isTrue()) {
						continue;
					}
				} else if ("reagent2trough".equals(ruleName)) {
					Value val = api.evaluate("Reagent.allInstances()->size() = Trough.allInstances()->size()");
					if (val.isBoolean() && ((BooleanValue) val).isTrue()) {
						continue;
					}
				} else if ("Sample2NewTubeRunner".equals(ruleName)) {
					Value val = api.evaluate("S2TR.allInstances()->size().mod(16) <> 0" +
							" or " +
							"S2TR.allInstances()->size() = Sample.allInstances()->size()");
					if (val.isBoolean() && ((BooleanValue) val).isTrue()) {
						continue;
					}
				} else if ("Sample2ExistTubeRunner".equals(ruleName)) {
					Value val = api.evaluate("S2TR.allInstances()->size().mod(16) = 0" +
							" or " +
							"S2TR.allInstances()->size() = Sample.allInstances()->size()");
					if (val.isBoolean() && ((BooleanValue) val).isTrue()) {
						continue;
					}
				} else if ("InitNewMicroplate".equals(ruleName)) {
					Value val = api.evaluate("S2MP.allInstances()->size().mod(96) <> 0" +
							" or " +
							"S2MP.allInstances()->size() = Sample.allInstances()->size()");
					if (val.isBoolean() && ((BooleanValue) val).isTrue()) {
						continue;
					}
				} else if ("SampleAssignExistMicroplate".equals(ruleName)) {
					Value val = api.evaluate("S2MP.allInstances()->size().mod(96) = 0" +
							" or " +
							"S2MP.allInstances()->size() = Sample.allInstances()->size()");
					if (val.isBoolean() && ((BooleanValue) val).isTrue()) {
						continue;
					}
				} else if ("DistributeSample_New".equals(ruleName)) {
					Value val1 = api.evaluate("DistributeSample.allInstances()->forAll(e | e.ds2ltj.ltj.tips->size() = Sample.allInstances()->size())");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
					Value val2 = api.evaluate("DistributeSample.allInstances()->exists(e | e.ds2ltj.ltj.tips->size().mod(8) <> 0)");
					if (val2.isBoolean() && ((BooleanValue) val2).isTrue()) {
						continue;
					}
				} else if ("DistributeSample_Exist".equals(ruleName)) {
					Value val1 = api.evaluate("DistributeSample.allInstances()->forAll(e | e.ds2ltj.ltj.tips->size() = Sample.allInstances()->size())");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
//					Value val2 = api.evaluate("DistributeSample.allInstances()->forAll(e | e.ds2ltj.ltj.tips->size().mod(8) = 0)");
//					if (val2.isBoolean() && ((BooleanValue) val2).isTrue()) {
//						continue;
//					}
				} else if ("IncubateJob_New_1".equals(ruleName)) {
					Value val1 = api.evaluate("Incubate.allInstances()->forAll(e | e.i2ij.ij->size() = Microplate.allInstances()->size())");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}

					Value val2 = api.evaluate("Incubate.allInstances()->exists(e | let s = e.i2ij.ij->size() in s > 0 and s <> Microplate.allInstances()->size())");
					if (val2.isBoolean() && ((BooleanValue) val2).isTrue()) {
						continue;
					}
				} else if ("IncubateJob_New_2".equals(ruleName)) {
					Value val1 = api.evaluate("Incubate.allInstances()->forAll(e | e.i2ij.ij->size() = Microplate.allInstances()->size())");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
				} else if ("WashJob_New".equals(ruleName)) {
					Value val1 = api.evaluate("Wash.allInstances()->forAll(e | e.w2wj.wj->size() = Microplate.allInstances()->size())");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
				} else if ("AddReagent_New".equals(ruleName)) {
					Value val1 = api.evaluate("AddReagent.allInstances()->forAll(e | e.ar2ltj.ltj.tips->size() = Sample.allInstances()->size())");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
					Value val2 = api.evaluate("AddReagent.allInstances()->exists(e | e.ar2ltj.ltj.tips->size().mod(8) <> 0)");
					if (val2.isBoolean() && ((BooleanValue) val2).isTrue()) {
						continue;
					}
				} else if ("AddReagent_Exist".equals(ruleName)) {
					Value val1 = api.evaluate("AddReagent.allInstances()->forAll(e | e.ar2ltj.ltj.tips->size() = Sample.allInstances()->size())");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
				} else if ("Distribute_IncubateJob".equals(ruleName)) {
					Value val1 = api.evaluate("DistributeSample.allInstances()->forAll(e | let list = e.ds2ltj.ltj in list.forAll(i | i.next->size() = 1))");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
				} else if ("AddReagent_IncubateJob".equals(ruleName)) {
					Value val1 = api.evaluate("AddReagent.allInstances()->forAll(e | let list = e.ar2ltj.ltj in list.forAll(i | i.next->size() = 1))");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
				} else if ("WashJob_AddReagent".equals(ruleName)) {
					Value val1 = api.evaluate("Wash.allInstances()->forAll(e | e.w2wj.wj.next->size() = e.next.oclAsType(AddReagent).ar2ltj.ltj->size())");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
				} else if ("Incubate_Distribute".equals(ruleName)) {
					Value val1 = api.evaluate("Incubate.allInstances()->forAll(e | if e.next.oclIsTypeOf(DistributeSample) then e.i2ij.ij.next->size() = e.next.oclAsType(DistributeSample).ds2ltj.ltj->size() else true endif)");
					if (val1.isBoolean() && ((BooleanValue) val1).isTrue()) {
						continue;
					}
				}


			} catch (Exception e) {
				System.err.println("Error in findMatchesForRules: " + e.getMessage());
			}
			
			List<Match> currentMatches = findMatchForRule(rule);
			if (currentMatches != null) matches.addAll(currentMatches);

			System.out.println("RUN 1 RULE");
			break;
		}
		if (Main.matchComparator != null)
			matches.sort(Main.matchComparator);
		return matches;
	}
}
