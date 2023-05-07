package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "*".
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForIntegerMultiplicationIntegerTerm
		extends AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForIntegerMultiplicationIntegerTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getInfixOp() {
		return "*";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForIntegerMultiplicationIntegerTerm(this);
	}
}