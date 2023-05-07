package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary logical "and".
 * 
 * @author Jan Prien
 *
 */
public final class BooleanResultForBooleanAndBooleanTerm
		extends AbstractBooleanResultForBinaryBooleanArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public BooleanResultForBooleanAndBooleanTerm(IBooleanResultTerm left, IBooleanResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getOp() {
		return "and";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanResultForBooleanAndBooleanTerm(this);
	}

}
