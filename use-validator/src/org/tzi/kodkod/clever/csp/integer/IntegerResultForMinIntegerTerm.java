package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "min".
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForMinIntegerTerm
		extends AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForMinIntegerTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getPrefixOp() {
		return "min( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForMinIntegerTerm(this);
	}
}
