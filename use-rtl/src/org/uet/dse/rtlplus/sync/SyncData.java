package org.uet.dse.rtlplus.sync;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tzi.use.uml.sys.MObject;
import org.uet.dse.rtlplus.mm.MRuleCollection;
import org.uet.dse.rtlplus.mm.MTggRule;
import org.uet.dse.rtlplus.parser.ast.tgg.AstInvariantCondition;

public class SyncData {
	private Map<String, Set<MTggRule>> rulesForSrcClass;
	private Map<String, Set<MTggRule>> rulesForTrgClass;
	private Map<String, Set<String>> forwardCmdsForCorr;
	private Map<String, Set<String>> backwardCmdsForCorr;
	private Map<String, Set<String>> corrObjsForSrc;
	private Map<String, Set<String>> corrObjsForTrg;
	private Map<String, Set<String>> corrObjDependencies;
	private Map<String, Set<String>> corrObjReverseDependencies;
	private Map<String, OperationEnterEvent> pastTransformations;
	private Map<String, String> transformationForCorrObj;
	
	public SyncData(MRuleCollection rules) {
		rulesForSrcClass = new HashMap<>();
		rulesForTrgClass = new HashMap<>();
		forwardCmdsForCorr = new HashMap<>();
		backwardCmdsForCorr = new HashMap<>();
		corrObjsForSrc = new HashMap<>();
		corrObjsForTrg = new HashMap<>();
		corrObjDependencies = new HashMap<>();
		corrObjReverseDependencies = new HashMap<>();
		pastTransformations = new LinkedHashMap<>();
		transformationForCorrObj = new HashMap<>();
		for (MTggRule tggRule : rules.getRuleList()) {
			for (MObject obj : tggRule.getSrcRule().getAllObjects()) {
				Set<MTggRule> tggRules = rulesForSrcClass.get(obj.cls().name());
				if (tggRules == null) {
					tggRules = new HashSet<>();
					rulesForSrcClass.put(obj.cls().name(), tggRules);
				}
				tggRules.add(tggRule);

			}
			for (MObject obj : tggRule.getTrgRule().getAllObjects()) {
				Set<MTggRule> tggRules = rulesForTrgClass.get(obj.cls().name());
				if (tggRules == null) {
					tggRules = new HashSet<>();
					rulesForTrgClass.put(obj.cls().name(), tggRules);
				}
				tggRules.add(tggRule);

			}
			for (Map.Entry<String, List<AstInvariantCondition>> entry : tggRule.getCorrRule().getRhs().getInvariantList().entrySet()) {
				String cls = entry.getKey();
				List<AstInvariantCondition> invs = entry.getValue();
				Set<String> fCmds = forwardCmdsForCorr.get(cls);
				if (fCmds == null) {
					fCmds = new HashSet<>(2);
					forwardCmdsForCorr.put(cls, fCmds);
				}
				Set<String> bCmds = backwardCmdsForCorr.get(cls);
				if (bCmds == null) {
					bCmds = new HashSet<>(2);
					backwardCmdsForCorr.put(cls, bCmds);
				}
				for (AstInvariantCondition inv : invs) {
					if (inv.getForwardImpl() != null && inv.getBackwardImpl() != null) {
						fCmds.addAll(Arrays.asList(inv.getForwardImpl().split(";")));
						bCmds.addAll(Arrays.asList(inv.getBackwardImpl().split(";")));
					} else {
						fCmds.add(inv.getCondition().replace("=", ":="));
						String[] parts = inv.getCondition().split("=");
						bCmds.add(parts[1] + ":=" + parts[0]);
					}
				}
			}
		}
	}

	public Map<String, Set<MTggRule>> getRulesForSrcClass() {
		return rulesForSrcClass;
	}

	public Map<String, Set<MTggRule>> getRulesForTrgClass() {
		return rulesForTrgClass;
	}

	public Map<String, Set<String>> getForwardCmdsForCorr() {
		return forwardCmdsForCorr;
	}

	public Map<String, Set<String>> getBackwardCmdsForCorr() {
		return backwardCmdsForCorr;
	}

	public void addSrcCorrLink(String src, String corr) {
		Set<String> corrs = corrObjsForSrc.get(src);
		if (corrs == null) {
			corrs = new HashSet<>();
			corrObjsForSrc.put(src, corrs);
		}
		corrs.add(corr);
	}

	public void addTrgCorrLink(String trg, String corr) {
		Set<String> corrs = corrObjsForTrg.get(trg);
		if (corrs == null) {
			corrs = new HashSet<>();
			corrObjsForTrg.put(trg, corrs);
		}
		corrs.add(corr);
	}

	public Map<String, Set<String>> getCorrObjsForSrc() {
		return corrObjsForSrc;
	}

	public Map<String, Set<String>> getCorrObjsForTrg() {
		return corrObjsForTrg;
	}

	public Map<String, Set<String>> getCorrObjDependencies() {
		return corrObjDependencies;
	}

	public Map<String, Set<String>> getCorrObjReverseDependencies() {
		return corrObjReverseDependencies;
	}

	public void addTransformation(String transName, OperationEnterEvent event) {
		pastTransformations.put(transName, event);
		for (String matchedCorrObj : event.getMatchedCorrObjs()) {
			Set<String> ds = corrObjDependencies.get(matchedCorrObj);
			if (ds == null) {
				ds = new HashSet<>();
				corrObjDependencies.put(matchedCorrObj, ds);
			}
			ds.addAll(event.getCorrObjsToCreate().values());
		}
		for (String obj : event.getCorrObjsToCreate().values()) {
			transformationForCorrObj.put(obj, transName);
		}
	}

	public Map<String, OperationEnterEvent> getPastTransformations() {
		return pastTransformations;
	}

	public Map<String, String> getTransformationForCorrObj() {
		return transformationForCorrObj;
	}
	
	
	
}
