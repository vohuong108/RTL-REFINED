package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term for binary "min".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForMinRealIntegerTerm
		extends AbstractRealResultForBinaryRealIntegerArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForMinRealIntegerTerm(IRealResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getPrefixOp() {
		return "min( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForMinRealIntegerTerm(this);
	}
}
