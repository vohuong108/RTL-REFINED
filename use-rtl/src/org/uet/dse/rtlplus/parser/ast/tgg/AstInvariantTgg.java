package org.uet.dse.rtlplus.parser.ast.tgg;

import java.util.ArrayList;
import java.util.List;

public class AstInvariantTgg {
	
	private String name;
	private List<AstInvariantCondition> condList = new ArrayList<>(1);

	public String getName() {
		return name;
	}
	
	public List<AstInvariantCondition> getConditions() {
		return condList;
	}

	public AstInvariantTgg(Object object) {
		name = (String) object;
	}

	public void addCondition(AstInvariantCondition cond) {
		condList.add(cond);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		sb.append(':');
		for (AstInvariantCondition cond : condList) {
			sb.append(cond);
		}
		return sb.toString();
	}
}
