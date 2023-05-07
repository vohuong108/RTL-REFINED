package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary logical "implies".
 * 
 * @author Jan Prien
 *
 */
public final class BooleanResultForBooleanImpliesBooleanTerm
		extends AbstractBooleanResultForBinaryBooleanArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public BooleanResultForBooleanImpliesBooleanTerm(IBooleanResultTerm left, IBooleanResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getOp() {
		return "implies";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanResultForBooleanImpliesBooleanTerm(this);
	}

}
