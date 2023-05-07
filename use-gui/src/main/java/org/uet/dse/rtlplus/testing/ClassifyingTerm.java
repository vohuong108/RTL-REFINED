package org.uet.dse.rtlplus.testing;

import org.tzi.use.uml.ocl.expr.Expression;
import kodkod.ast.Node;

public class ClassifyingTerm {
	private final String name;
	private final Expression oclExpr;
	private final Node kodkodExpr;
	
	public ClassifyingTerm(String name, Expression oclExpr, Node kodkodExpr) {
		this.name = name;
		this.oclExpr = oclExpr;
		this.kodkodExpr = kodkodExpr;
	}

	public String name() {
		return name;
	}
	
	public Expression oclExpression() {
		return oclExpr;
	}

	public Node kodkodExpression() {
		return getKodkodExpr();
	}

	public Node getKodkodExpr() {
		return kodkodExpr;
	}
}