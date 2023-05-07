package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "/".
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForIntegerDivisionIntegerTerm
		extends AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForIntegerDivisionIntegerTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getInfixOp() {
		return "/";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForIntegerDivisionIntegerTerm(this);
	}

}
