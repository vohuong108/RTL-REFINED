package org.uet.dse.rtlplus.parser.ast.tgg;

public class AstInvariantCondition {
	private final String condition;
	private String forwardImpl;
	private String backwardImpl;
	
	public AstInvariantCondition(String cond) {
		condition = cond;
	}
	
	public void setForwardImpl(String impl) {
		forwardImpl = impl;
	}
	
	public void setBackwardImpl(String impl) {
		backwardImpl = impl;
	}

	public String getCondition() {
		return condition;
	}

	public String getForwardImpl() {
		return forwardImpl;
	}

	public String getBackwardImpl() {
		return backwardImpl;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[').append(condition).append(']');
		if (forwardImpl != null && backwardImpl != null) {
			sb.append("\n`").append(forwardImpl).append("`\n)")
			.append('`').append(backwardImpl).append('`');
		}
		sb.append('\n');
		return sb.toString();
	}
}
