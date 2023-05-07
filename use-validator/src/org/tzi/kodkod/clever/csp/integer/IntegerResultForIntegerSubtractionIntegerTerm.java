package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for unary "-".
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForIntegerSubtractionIntegerTerm
		extends AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForIntegerSubtractionIntegerTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getInfixOp() {
		return "-";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForIntegerSubtractionIntegerTerm(this);
	}
}
