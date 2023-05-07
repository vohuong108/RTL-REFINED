package org.uet.dse.rtlplus.mm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;
import org.uet.dse.rtlplus.parser.RTLKeyword;

public class MTggRule {

	private String name;
	private MRule srcRule;
	private MRule trgRule;
	private MCorrRule corrRule;
	private List<String> srcLnkCons;
	private List<String> trgLnkCons;
	private String html;

	public MTggRule(String _name, MRule _srcRule, MRule _trgRule, MCorrRule _corrRule, List<String> _srcLnkCons,
			List<String> _trgLnkCons, String _str) {
		name = _name;
		srcRule = _srcRule;
		trgRule = _trgRule;
		corrRule = _corrRule;
		srcLnkCons = _srcLnkCons;
		trgLnkCons = _trgLnkCons;
		html = _str;
	}

	public String getName() {
		return name;
	}

	public MRule getSrcRule() {
		return srcRule;
	}

	public MRule getTrgRule() {
		return trgRule;
	}

	public MCorrRule getCorrRule() {
		return corrRule;
	}

	public String getHtml() {
		return html;
	}

	public void genForwardTransformation(StringBuilder ops, StringBuilder cons) {
		// Operations
		StringBuilder sb = new StringBuilder();
		sb.append(name + RTLKeyword.forwardTransform + " (");
		// matchSR: Tuple (obj1: cls1, obj2: cls2)
		String sr = srcRule.genMatchBoth();
		if (!sr.isEmpty())
			sb.append("\n    matchSR: Tuple(").append(sr).append(")");
		// matchCL
		String cl = corrRule.genMatchLeft();
		if (!cl.isEmpty()) {
			if (!sr.isEmpty())
				sb.append(',');
			sb.append("\n    matchCL: Tuple(").append(cl).append(")");
		}
		// matchTL
		String tl = trgRule.genMatchLeft();
		if (!tl.isEmpty()) {
			if (!sr.isEmpty() || !cl.isEmpty())
				sb.append(',');
			sb.append("\n    matchTL: Tuple(").append(tl).append(")");
		}
		sb.append(")\n");
		ops.append(sb);
		// Constraints
		String letString = new StringBuilder().append(srcRule.genLetBoth("matchSR"))
				.append(corrRule.genLetLeft("matchCL")).append(trgRule.genLetLeft("matchTL")).toString();
		// Pre-conditions
		cons.append("\ncontext RuleCollection::").append(sb)
				.append("pre " + name + RTLKeyword.forwardTransform + "_pre: \n").append(letString);
		String srcPre = srcRule.genPreCondBoth(true);
		String trgPre = trgRule.genPreCondLeft(true);
		String trgPreRight = trgRule.genPreCondRight();
		String corrPre = corrRule.genPreCondLeft(true);
		String srcLnkPre = srcLnkCons.stream().collect(Collectors.joining(" and\n"));
		String result = Arrays.asList(srcPre, trgPre, trgPreRight, corrPre, srcLnkPre).stream().filter(it -> !it.isEmpty())
				.collect(Collectors.joining(" and\n"));
		if (result.isEmpty())
			cons.append("true\n");
		else
			cons.append(result);
		// Post-conditions
		cons.append("\n\npost " + name + RTLKeyword.forwardTransform + "_post:\n").append(letString);
		// Existing objects
		StringBuilder sb1 = new StringBuilder();
		int trgDepth = trgRule.genPostCond(sb1, 0);
		// New objects
		StringBuilder sb2 = new StringBuilder();
		int corrDepth = corrRule.genPostCond(sb2, trgDepth);
		if (sb2.length() == 0)
			sb2.append("true\n");
		if (sb1.length() > 2) {
			if (sb1.charAt(sb1.length() - 2) == '|')
				sb1.append(sb2);
			else
				sb1.append(" and ").append(sb2);
		} else
			sb1.append(sb2);
		cons.append(sb1);
		for (int i = 0; i < corrDepth; i++)
			cons.append(')');
		cons.append('\n');
	}

	public void genBackwardTransformation(StringBuilder ops, StringBuilder cons) {
		// Operations
		StringBuilder sb = new StringBuilder();
		sb.append(name + RTLKeyword.backwardTransform + " (");
		// matchSL: Tuple (obj1: cls1, obj2: cls2)
		String sl = srcRule.genMatchLeft();
		if (!sl.isEmpty())
			sb.append("\n    matchSL: Tuple(").append(sl).append(")");
		// matchCL
		String cl = corrRule.genMatchLeft();
		if (!cl.isEmpty()) {
			if (!sl.isEmpty())
				sb.append(',');
			sb.append("\n    matchCL: Tuple(").append(cl).append(")");
		}
		// matchTR
		String tr = trgRule.genMatchBoth();
		if (!tr.isEmpty()) {
			if (!sl.isEmpty() || !cl.isEmpty())
				sb.append(',');
			sb.append("\n    matchTR: Tuple(").append(tr).append(")");
		}
		sb.append(")\n");
		ops.append(sb);
		String letString = new StringBuilder().append(srcRule.genLetLeft("matchSL"))
				.append(corrRule.genLetLeft("matchCL")).append(trgRule.genLetBoth("matchTR")).toString();
		// Pre-conditions
		cons.append("\ncontext RuleCollection::").append(sb)
				.append("pre " + name + RTLKeyword.backwardTransform + "_pre: \n").append(letString);
		String srcCond = srcRule.genPreCondLeft(true);
		String srcPreRight = srcRule.genPreCondRight();
		String trgCond = trgRule.genPreCondBoth(true);
		String corrCond = corrRule.genPreCondLeft(true);
		String trgLnkPre = trgLnkCons.stream().collect(Collectors.joining(" and\n"));
		String result = Arrays.asList(srcCond, srcPreRight, trgCond, corrCond, trgLnkPre).stream().filter(it -> !it.isEmpty())
				.collect(Collectors.joining(" and\n"));
		if (result.isEmpty())
			cons.append("true\n");
		else
			cons.append(result);
		// Post-conditions
		cons.append("\n\npost " + name + RTLKeyword.backwardTransform + "_post:\n").append(letString);
		// Existing objects
		StringBuilder sb1 = new StringBuilder();
		int srcDepth = srcRule.genPostCond(sb1, 0);
		// New objects
		StringBuilder sb2 = new StringBuilder();
		int corrDepth = corrRule.genPostCond(sb2, srcDepth);
		if (sb2.length() == 0)
			sb2.append("true\n");
		if (sb1.length() > 2) {
			if (sb1.charAt(sb1.length() - 2) == '|')
				sb1.append(sb2);
			else
				sb1.append(" and ").append(sb2);
		} else
			sb1.append(sb2);
		cons.append(sb1);
		for (int i = 0; i < corrDepth; i++)
			cons.append(')');
		cons.append('\n');
	}

	public void genIntegration(StringBuilder ops, StringBuilder cons) {
		StringBuilder sb = new StringBuilder();
		sb.append(name + RTLKeyword.integration + " (");
		// matchSL: Tuple (obj1: cls1, obj2: cls2)
		String sr = srcRule.genMatchBoth();
		if (!sr.isEmpty())
			sb.append("\n    matchSR: Tuple(").append(sr).append(")");
		// matchCL
		String cl = corrRule.genMatchLeft();
		if (!cl.isEmpty()) {
			if (!sr.isEmpty())
				sb.append(',');
			sb.append("\n    matchCL: Tuple(").append(cl).append(")");
		}
		// matchTR
		String tr = trgRule.genMatchBoth();
		if (!tr.isEmpty()) {
			if (!sr.isEmpty() || !tr.isEmpty())
				sb.append(',');
			sb.append("\n    matchTR: Tuple(").append(tr).append(")");
		}
		sb.append(")\n");
		ops.append(sb);
		String letString = new StringBuilder().append(srcRule.genLetBoth("matchSR"))
				.append(corrRule.genLetLeft("matchCL")).append(trgRule.genLetBoth("matchTR")).toString();
		// Pre-conditions
		cons.append("\ncontext RuleCollection::").append(sb).append("pre " + name + RTLKeyword.integration + "_pre: \n")
				.append(letString);
		String srcCond = srcRule.genPreCondBoth(true);
		String trgCond = trgRule.genPreCondBoth(true);
		String corrCond = corrRule.genPreCondLeft(true);
		String srcLnkPre = srcLnkCons.stream().collect(Collectors.joining(" and\n"));
		String trgLnkPre = trgLnkCons.stream().collect(Collectors.joining(" and\n"));
		String result = Arrays.asList(srcCond, trgCond, corrCond, srcLnkPre, trgLnkPre).stream()
				.filter(it -> !it.isEmpty()).collect(Collectors.joining(" and\n"));
		if (result.isEmpty())
			cons.append("true\n");
		else
			cons.append(result);
		// Post-conditions
		cons.append("\n\npost " + name + RTLKeyword.integration + "_post:\n").append(letString);
		// New objects
		StringBuilder sb2 = new StringBuilder();
		int corrDepth = corrRule.genPostCond(sb2, 0);
		if (sb2.length() == 0)
			sb2.append("true\n");
		if (sb2.length() > 2 && sb2.charAt(sb2.length() - 2) == '|')
			sb2.append("true\n");
		cons.append(sb2);
		for (int i = 0; i < corrDepth; i++)
			cons.append(')');
		cons.append('\n');
	}
	
	public void genCoevolution(StringBuilder ops, StringBuilder cons) {
		// Operations
		StringBuilder sb = new StringBuilder();
		sb.append(name + RTLKeyword.coEvolution + " (");
		// matchSL: Tuple (obj1: cls1, obj2: cls2)
		String sl = srcRule.genMatchLeft();
		if (!sl.isEmpty())
			sb.append("\n    matchSL: Tuple(").append(sl).append(")");
		// matchCL
		String cl = corrRule.genMatchLeft();
		if (!cl.isEmpty()) {
			if (!sl.isEmpty())
				sb.append(',');
			sb.append("\n    matchCL: Tuple(").append(cl).append(")");
		}
		// matchTL
		String tl = trgRule.genMatchLeft();
		if (!tl.isEmpty()) {
			if (!sl.isEmpty() || !cl.isEmpty())
				sb.append(',');
			sb.append("\n    matchTL: Tuple(").append(tl).append(")");
		}
		sb.append(")\n");
		ops.append(sb);
		// Constraints
		String letString = new StringBuilder().append(srcRule.genLetLeft("matchSL"))
				.append(corrRule.genLetLeft("matchCL")).append(trgRule.genLetLeft("matchTL")).toString();
		// Pre-conditions
		cons.append("\ncontext RuleCollection::").append(sb)
				.append("pre " + name + RTLKeyword.coEvolution + "_pre: \n").append(letString);
		String srcPre = srcRule.genPreCondLeft(true);
		String srcPreRight = srcRule.genPreCondRight();
		String trgPre = trgRule.genPreCondLeft(true);
		String trgPreRight = trgRule.genPreCondRight();
		String corrPre = corrRule.genPreCondLeft(true);
		String result = Arrays.asList(srcPre, srcPreRight, trgPre, trgPreRight, corrPre).stream().filter(it -> !it.isEmpty())
				.collect(Collectors.joining(" and\n"));
		if (result.isEmpty())
			cons.append("true\n");
		else
			cons.append(result);
		// Post-conditions
		cons.append("\n\npost " + name + RTLKeyword.coEvolution + "_post:\n").append(letString);
		// Existing objects - left
		StringBuilder sb1 = new StringBuilder();
		int trgDepth = srcRule.genPostCond(sb1, 0);
		// Existing objects - right
		StringBuilder sb3 = new StringBuilder();
		int srcDepth = trgRule.genPostCond(sb3, trgDepth);
		if (sb3.length() == 0)
			sb3.append("true\n");
		if (sb1.length() > 2) {
			if (sb1.charAt(sb1.length() - 2) == '|')
				sb1.append(sb3);
			else
				sb1.append(" and ").append(sb3);
		} else
			sb1.append(sb3);
		// New objects
		StringBuilder sb2 = new StringBuilder();
		int corrDepth = corrRule.genPostCond(sb2, srcDepth);
		if (sb2.length() == 0)
			sb2.append("true\n");
		if (sb1.length() > 2) {
			if (sb1.charAt(sb1.length() - 2) == '|')
				sb1.append(sb2);
			else
				sb1.append(" and ").append(sb2);
		} else
			sb1.append(sb2);
		cons.append(sb1);
		for (int i = 0; i < corrDepth; i++)
			cons.append(')');
		cons.append('\n');
	}

	public MSystemState getSystemStateLHS() {
		return srcRule.lhs.getSystemState();
	}

	public MSystemState getSystemStateRHS() {
		return srcRule.rhs.getSystemState();
	}

	public List<MLink> getNonDeletingLinks() {
		List<MLink> links = new ArrayList<>();
		links.addAll(srcRule.getNonDeletingLinks());
		links.addAll(trgRule.getNonDeletingLinks());
		links.addAll(corrRule.getNonDeletingLinks());
		return links;
	}

	public List<MLink> getNewLinks() {
		List<MLink> links = new ArrayList<>();
		links.addAll(srcRule.getNewLinks());
		links.addAll(trgRule.getNewLinks());
		links.addAll(corrRule.getNewLinks());
		return links;
	}
	
	public List<String> genCorrCreationCommands(TransformationType type, MSystemState systemState) {
		List<String> commands;
		switch(type) {
		case FORWARD:
			commands = srcRule.genLetCommandsRight("matchSR");
			commands.addAll(trgRule.genLetCommandsLeft("matchTL"));
			commands.addAll(corrRule.genCreationCommands("matchCL", systemState));
			return commands;
		case BACKWARD:
			commands = trgRule.genLetCommandsRight("matchTR");
			commands.addAll(srcRule.genLetCommandsLeft("matchSL"));
			commands.addAll(corrRule.genCreationCommands("matchCL", systemState));
			return commands;
		case INTEGRATION:
			commands = srcRule.genLetCommandsRight("matchSR");
			commands.addAll(trgRule.genLetCommandsRight("matchTR"));
			commands.addAll(corrRule.genCreationCommands("matchCL", systemState));
			return commands;
		// co-evolution
		default:
			commands = srcRule.genLetCommandsLeft("matchSL");
			commands.addAll(srcRule.genLetCommandsLeft("matchSL"));
			commands.addAll(corrRule.genCreationCommands("matchCL", systemState));
			return commands;
		}
	}

	public String toString() {
		return name;
	}

}
