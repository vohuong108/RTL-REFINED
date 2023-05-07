package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "min".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForMinRealRealTerm
		extends AbstractRealResultForBinaryRealArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForMinRealRealTerm(IRealResultTerm left, IRealResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getPrefixOp() {
		return "min( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForMinRealRealTerm(this);
	}
}
