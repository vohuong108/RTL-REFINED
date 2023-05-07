package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term for binary "max".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForMaxRealIntegerTerm
		extends AbstractRealResultForBinaryRealIntegerArgumentTermWithInfixAndZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForMaxRealIntegerTerm(IRealResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getPrefixOp() {
		return "max( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForMaxRealIntegerTerm(this);
	}
}
