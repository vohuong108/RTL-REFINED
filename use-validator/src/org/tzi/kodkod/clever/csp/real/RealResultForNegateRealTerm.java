package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for unary "-".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForNegateRealTerm extends AbstractRealResultForUnaryRealArgumentTermWithZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForNegateRealTerm(IRealResultTerm value) {
		super(value);
	}

	@Override
	protected String getPrefixOp() {
		return "( - ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForNegateRealTerm(this);
	}
}
