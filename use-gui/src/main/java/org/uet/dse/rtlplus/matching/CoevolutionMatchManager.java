package org.uet.dse.rtlplus.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tzi.use.api.UseSystemApi;
import org.tzi.use.uml.mm.MOperation;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.mm.MTggRule;
import org.uet.dse.rtlplus.parser.RTLKeyword;

public class CoevolutionMatchManager extends MatchManager {

	public CoevolutionMatchManager(MSystemState systemState, boolean sync) {
		super(systemState, sync);
	}
	
	@Override
	public List<Match> findMatchForRule(MTggRule rule) {
		List<Match> matches = new ArrayList<>();
		MOperation op = findOperation(rule.getName(), RTLKeyword.coEvolution);
		if (op == null)
			return matches;
		// System.out.println("Finding matches for " + rule.getName());
		// TODO: How to interpret empty result?
		// NULL => No objects, not valid for forward transformation
		// Empty => No matches
		List<Map<String, MObject>> srcMatches = findSourceMatch(rule, op);
		if (srcMatches != null && srcMatches.isEmpty())
			return matches;
		if (srcMatches == null) {
			srcMatches = new ArrayList<Map<String, MObject>>();
			srcMatches.add(new HashMap<String, MObject>());
		}
		// Normal
		for (Map<String, MObject> srcMatch : srcMatches) {
			Map<String, MObject> objs = new HashMap<>(srcMatch);
			List<Map<String, MObject>> trgMatches = findTargetMatch(rule, op);
			if (trgMatches != null && trgMatches.isEmpty())
				return matches;
			if (trgMatches == null) {
				trgMatches = new ArrayList<Map<String, MObject>>();
				trgMatches.add(new HashMap<String, MObject>());
			}
			for (Map<String, MObject> trgMatch : trgMatches) {
				objs.putAll(trgMatch);
				List<Map<String, MObject>> corrMatches = findCorrelationMatch(rule, op);
				if (corrMatches != null && corrMatches.isEmpty())
					return matches;
				if (corrMatches == null) {
					corrMatches = new ArrayList<Map<String, MObject>>();
					corrMatches.add(new HashMap<String, MObject>());
				}
				for (Map<String, MObject> corrMatch : corrMatches) {
					objs.putAll(corrMatch);
					if (validatePreconditions(op, objs))
						matches.add(new CoevolutionMatch(rule, op, new HashMap<>(objs), sync));
				}
			}
		}
		if (Main.matchComparator != null)
			matches.sort(Main.matchComparator);
		return matches;
	}

	private List<Map<String, MObject>> findSourceMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getSrcRule().getNonDeletingObjects();
		List<MLink> ruleLinks = rule.getSrcRule().getNonDeletingLinks();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks);
		return finder.run();
	}

	private List<Map<String, MObject>> findTargetMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getTrgRule().getNonDeletingObjects();
		List<MLink> ruleLinks = rule.getTrgRule().getNonDeletingLinks();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects, ruleLinks);
		return finder.run();
	}

	private List<Map<String, MObject>> findCorrelationMatch(MTggRule rule, MOperation operation) {
		List<MObject> ruleObjects = rule.getCorrRule().getNonDeletingObjects();
		MatchFinder finder = new MatchFinder(systemState, operation, ruleObjects);
		return finder.run();
	}

	@Override
	public List<Match> findMatchesForRuleAndObjects(MTggRule rule, List<MObject> objects) {
		// Not implemented
		return null;
	}

}
