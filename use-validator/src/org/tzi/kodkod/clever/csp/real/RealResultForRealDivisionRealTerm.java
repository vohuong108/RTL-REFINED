package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "/".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForRealDivisionRealTerm
		extends AbstractRealResultForBinaryRealArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public RealResultForRealDivisionRealTerm(IRealResultTerm left, IRealResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getInfixOp() {
		return "/";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForRealDivisionRealTerm(this);
	}
}
