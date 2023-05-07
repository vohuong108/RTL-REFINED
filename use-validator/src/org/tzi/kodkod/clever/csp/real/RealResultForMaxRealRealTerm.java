package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "max".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForMaxRealRealTerm
		extends AbstractRealResultForBinaryRealArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForMaxRealRealTerm(IRealResultTerm left, IRealResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getPrefixOp() {
		return "max( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForMaxRealRealTerm(this);
	}
}
