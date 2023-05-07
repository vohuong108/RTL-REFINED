package org.uet.dse.rtlplus.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tzi.use.uml.mm.MOperation;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.mm.MTggRule;
import org.uet.dse.rtlplus.parser.RTLKeyword;

public class IntegrationMatchManager extends MatchManager {

	public IntegrationMatchManager(MSystemState systemState, boolean sync) {
		super(systemState, sync);
	}

	@Override
	public List<Match> findMatchesForObjects(List<MObject> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Match> findMatchForRule(MTggRule rule) {
		List<Match> matches = new ArrayList<>();
		MOperation op = findOperation(rule.getName(), RTLKeyword.integration);
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
			Map<String, MObject> objs = new HashMap<>(srcMatch);
			List<? extends Map<String, MObject>> trgMatches = findTargetMatch(rule, op);
			if (trgMatches == null) {
				if (validatePreconditions(op, objs))
					matches.add(new IntegrationMatch(rule, op, new HashMap<>(objs), sync));
			} else if (trgMatches.isEmpty())
				return matches;
			else {
				for (Map<String, MObject> trgMatch : trgMatches) {
					objs.putAll(trgMatch);
					List<? extends Map<String, MObject>> corrMatches = findCorrelationMatch(rule, op);
					if (corrMatches == null) {
						if (validatePreconditions(op, objs))
							matches.add(new IntegrationMatch(rule, op, new HashMap<>(objs), sync));
					} else if (corrMatches.isEmpty())
						return matches;
					else {
						for (Map<String, MObject> corrMatch : corrMatches) {
							objs.putAll(corrMatch);
							if (validatePreconditions(op, objs))
								matches.add(new IntegrationMatch(rule, op, new HashMap<>(objs), sync));
						}
					}
				}
			}
		}
		if (Main.matchComparator != null)
			matches.sort(Main.matchComparator);
		return matches;
	}

	private List<? extends Map<String, MObject>> findSourceMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getSrcRule().getAllObjects();
		List<MLink> ruleLinks = rule.getSrcRule().getAllLinks();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks);
		return finder.run();
	}

	private List<? extends Map<String, MObject>> findTargetMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getTrgRule().getAllObjects();
		List<MLink> ruleLinks = rule.getTrgRule().getAllLinks();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks);
		return finder.run();
	}

	private List<? extends Map<String, MObject>> findCorrelationMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getCorrRule().getNonDeletingObjects();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects);
		return finder.run();
	}

	@Override
	public List<Match> findMatchesForRuleAndObjects(MTggRule rules, List<MObject> objects) {
		// Not implemented
		return new ArrayList<>(0);
	}

}
