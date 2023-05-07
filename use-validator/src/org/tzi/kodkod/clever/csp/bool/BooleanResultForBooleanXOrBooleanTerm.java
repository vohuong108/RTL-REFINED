package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary logical "or".
 * 
 * @author Jan Prien
 *
 */
public final class BooleanResultForBooleanXOrBooleanTerm
		extends AbstractBooleanResultForBinaryBooleanArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public BooleanResultForBooleanXOrBooleanTerm(IBooleanResultTerm left, IBooleanResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getOp() {
		return "or";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanResultForBooleanXOrBooleanTerm(this);
	}

}
