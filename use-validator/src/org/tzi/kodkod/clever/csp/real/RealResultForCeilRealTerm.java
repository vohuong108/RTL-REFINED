package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for unary "ceil".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForCeilRealTerm extends AbstractRealResultForUnaryRealArgumentTermWithZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForCeilRealTerm(IRealResultTerm value) {
		super(value);
	}

	@Override
	protected String getPrefixOp() {
		return "ceil( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForCeilRealTerm(this);
	}

}
