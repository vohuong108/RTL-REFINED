package org.uet.dse.rtlplus.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tzi.use.api.UseSystemApi;
import org.tzi.use.main.Session;
import org.tzi.use.uml.mm.MOperation;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.mm.MTggRule;
import org.uet.dse.rtlplus.parser.RTLKeyword;

public class ForwardMatchManager extends MatchManager {

	public ForwardMatchManager(MSystemState systemState, boolean sync) {
		super(systemState, sync);
	}
	
	@Override
	public List<Match> findMatchForRule(MTggRule rule) {
		List<Match> matches = new ArrayList<>();
		MOperation op = findOperation(rule.getName(), RTLKeyword.forwardTransform);
		if (op == null)
			return matches;
		// System.out.println("Finding matches for " + rule.getName());
		// TODO: How to interpret empty result?
		// NULL => No objects, not valid for forward transformation
		// Empty => No matches
		List<? extends Map<String, MObject>> srcMatches = findSourceMatch(rule, op);
		if (srcMatches == null || srcMatches.isEmpty())
			return matches;
		// Normal
		for (Map<String, MObject> srcMatch : srcMatches) {
			// System.out.println("Source: " + srcMatch.toString());
			Map<String, MObject> objs = new HashMap<>(srcMatch);
			List<? extends Map<String, MObject>> trgMatches = findTargetMatch(rule, op);
			if (trgMatches == null) {
				if (validatePreconditions(op, objs))
					matches.add(new ForwardMatch(rule, op, new HashMap<>(objs), sync));
			} else if (trgMatches.isEmpty())
				return matches;
			else {
				for (Map<String, MObject> trgMatch : trgMatches) {
					// System.out.println("Target: " + trgMatch.toString());
					objs.putAll(trgMatch);
					// vv.huong
//					List<? extends Map<String, MObject>> corrMatches = findCorrelationMatch(rule, op);
					List<? extends Map<String, MObject>> corrMatches = findCorrelationMatchWithSrcTrg(rule, op, objs);
					if (corrMatches == null) {
						if (validatePreconditions(op, objs))
							matches.add(new ForwardMatch(rule, op, new HashMap<>(objs), sync));
					} else {
						for (Map<String, MObject> corrMatch : corrMatches) {
							// System.out.println("Corr: " + corrMatch.toString());
							objs.putAll(corrMatch);
							if (validatePreconditions(op, objs)) {
								matches.add(new ForwardMatch(rule, op, new HashMap<>(objs), sync));
								break;
							}
						}
					}

					// vv.huong
					if (matches.size() == 1) {
						break;
					}
				}
			}

			// vv.huong
			if (matches.size() == 1) {
				break;
			}
		}
		if (Main.matchComparator != null)
			matches.sort(Main.matchComparator);
		return matches;
	}

	private List<? extends Map<String, MObject>> findSourceMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getSrcRule().getAllObjects();
		List<MLink> ruleLinks = rule.getSrcRule().getAllLinks();
//		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks);
		// vv.huong
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks, rule, "SRC");

		return finder.run();
	}
	
	private List<? extends Map<String, MObject>> findSourceMatch(MTggRule rule, MOperation operation, List<MObject> objects) {
		List<MObject> ruleObjects = rule.getSrcRule().getAllObjects();
		List<MLink> ruleLinks = rule.getSrcRule().getAllLinks();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks);
		return finder.run(objects);
	}

	private List<? extends Map<String, MObject>> findTargetMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getTrgRule().getNonDeletingObjects();
		List<MLink> ruleLinks = rule.getTrgRule().getNonDeletingLinks();
//		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks);
		// vv.huong
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks, rule, "TRG");
		return finder.run();
	}

	private List<? extends Map<String, MObject>> findCorrelationMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getCorrRule().getNonDeletingObjects();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects);
		return finder.run();
	}

	// vv.huong
	private List<? extends Map<String, MObject>> findCorrelationMatchWithSrcTrg(
			MTggRule rule, MOperation operation, Map<String, MObject> objs) {
		List<MObject> ruleObjects = rule.getCorrRule().getNonDeletingObjects();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, rule, "COR", objs);
		return finder.run();
	}

	@Override
	public List<Match> findMatchesForRuleAndObjects(MTggRule rule, List<MObject> objects) {
		List<Match> matches = new ArrayList<>();
		MOperation op = findOperation(rule.getName(), RTLKeyword.forwardTransform);
		if (op == null)
			return matches;
		// System.out.println("Finding matches for " + rule.getName());
		// TODO: How to interpret empty result?
		// NULL => No objects, not valid for forward transformation
		// Empty => No matches
		List<? extends Map<String, MObject>> srcMatches = findSourceMatch(rule, op, objects);
		if (srcMatches == null || srcMatches.isEmpty())
			return matches;
		// Normal
		for (Map<String, MObject> srcMatch : srcMatches) {
			Map<String, MObject> objs = new HashMap<>(srcMatch);
			List<? extends Map<String, MObject>> trgMatches = findTargetMatch(rule, op);
			if (trgMatches == null) {
				if (validatePreconditions(op, objs))
					matches.add(new ForwardMatch(rule, op, new HashMap<>(objs), sync));
			} else if (trgMatches.isEmpty())
				return matches;
			else {
				for (Map<String, MObject> trgMatch : trgMatches) {
					objs.putAll(trgMatch);
					List<? extends Map<String, MObject>> corrMatches = findCorrelationMatch(rule, op);
					if (corrMatches == null) {
						if (validatePreconditions(op, objs))
							matches.add(new ForwardMatch(rule, op, new HashMap<>(objs), sync));
					} else if (corrMatches.isEmpty())
						return matches;
					else {
						for (Map<String, MObject> corrMatch : corrMatches) {
							objs.putAll(corrMatch);
							if (validatePreconditions(op, objs))
								matches.add(new ForwardMatch(rule, op, new HashMap<>(objs), sync));
						}
					}
				}
			}
		}
		if (Main.matchComparator != null)
			matches.sort(Main.matchComparator);
		return matches;
	}

}
