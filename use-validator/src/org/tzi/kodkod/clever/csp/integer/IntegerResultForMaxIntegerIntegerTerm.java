package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "max".
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForMaxIntegerIntegerTerm
		extends AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForMaxIntegerIntegerTerm(AbstractIntegerResultTerm left, AbstractIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getPrefixOp() {
		return "max( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForMaxIntegerIntegerTerm(this);
	}
}
