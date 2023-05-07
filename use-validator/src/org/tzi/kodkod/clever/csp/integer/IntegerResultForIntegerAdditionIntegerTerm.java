package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "+".
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForIntegerAdditionIntegerTerm
		extends AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForIntegerAdditionIntegerTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getInfixOp() {
		return "+";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForIntegerAdditionIntegerTerm(this);
	}
}
