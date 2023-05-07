package org.uet.dse.rtlplus.parser.ast.tgg;

import java.util.ArrayList;
import java.util.List;

import org.tzi.use.uml.mm.MInvalidModelException;
import org.tzi.use.uml.sys.MSystemException;
import org.uet.dse.rtlplus.mm.MRuleCollection;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;
import org.uet.dse.rtlplus.parser.Context;
import org.uet.dse.rtlplus.parser.RTLKeyword;

public class AstRuleCollection {

	private String name;
	private String direction;
	private List<AstTggRule> ruleList = new ArrayList<>(1);

	public String getName() {
		return name;
	}

	public AstRuleCollection(Object _name) {
		name = (String) _name;
	}

	public void addRuleDefinition(AstTggRule rule) {
		ruleList.add(rule);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(RTLKeyword.transformation).append(' ').append(name).append(' ').append(RTLKeyword.direction)
				.append(' ').append(direction).append('\n');
		for (AstTggRule rule : ruleList) {
			sb.append(rule.toString()).append('\n');
		}
		return sb.toString();
	}

	public void setDirection(Object object) {
		direction = (String) object;
	}

	public MRuleCollection gen(Context ctx) throws MInvalidModelException, MSystemException {

		MRuleCollection collection;
		switch (direction) {
		case "forward":
			collection = new MRuleCollection(TransformationType.FORWARD);
			break;
		case "backward":
			collection = new MRuleCollection(TransformationType.BACKWARD);
			break;
		case "integration":
			collection = new MRuleCollection(TransformationType.INTEGRATION);
			break;
		case "synchronization":
			collection = new MRuleCollection(TransformationType.SYNCHRONIZATION);
			break;
		default:
			collection = new MRuleCollection(TransformationType.COEVOLUTION);
			break;
		}
		for (AstTggRule rule : ruleList) {
			collection.addRule(rule.gen(ctx));
		}
		collection.setContext(ctx);
		return collection;
	}
}
