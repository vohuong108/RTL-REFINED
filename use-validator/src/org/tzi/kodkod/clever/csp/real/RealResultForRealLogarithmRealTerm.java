package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "log".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForRealLogarithmRealTerm
		extends AbstractRealResultForBinaryRealArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForRealLogarithmRealTerm(IRealResultTerm left, IRealResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getPrefixOp() {
		return "log( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForRealLogarithmRealTerm(this);
	}
}
