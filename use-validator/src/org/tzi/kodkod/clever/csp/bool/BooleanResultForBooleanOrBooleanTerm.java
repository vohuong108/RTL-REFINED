package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary logical "xor".
 * 
 * @author Jan Prien
 *
 */
public final class BooleanResultForBooleanOrBooleanTerm
		extends AbstractBooleanResultForBinaryBooleanArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public BooleanResultForBooleanOrBooleanTerm(IBooleanResultTerm left, IBooleanResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getOp() {
		return "xor";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanResultForBooleanOrBooleanTerm(this);
	}
}
