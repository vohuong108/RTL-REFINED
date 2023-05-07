package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "^".
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForIntegerExponentiationIntegerTerm
		extends AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForIntegerExponentiationIntegerTerm(IIntegerResultTerm basis, IIntegerResultTerm exponent) {
		super(basis, exponent);
	}

	@Override
	protected String getPrefixOp() {
		return "( ";
	}

	@Override
	protected String getInfixOp() {
		return " ^ ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForIntegerExponentiationIntegerTerm(this);
	}
}
