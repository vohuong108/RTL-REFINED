package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term for unary "absolute" that gives the integer value for a real value on
 * evaluation.
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForAbsoluteIntegerTerm
		extends AbstractIntegerResultForUnaryRealArgumentTermWithZirkumfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForAbsoluteIntegerTerm(IRealResultTerm value) {
		super(value);
	}

	@Override
	protected String getPrefixOp() {
		return "absolute( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForAbsoluteIntegerTerm(this);
	}
}
