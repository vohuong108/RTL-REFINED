package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term for binary "log".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForIntegerLogarithmRealTerm
		extends AbstractRealResultForBinaryIntegerRealArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForIntegerLogarithmRealTerm(IIntegerResultTerm left, IRealResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getPrefixOp() {
		return "log( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForIntegerLogarithmRealTerm(this);
	}
}
