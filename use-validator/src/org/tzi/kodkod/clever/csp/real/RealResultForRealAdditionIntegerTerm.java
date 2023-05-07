package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term for binary "+".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForRealAdditionIntegerTerm
		extends AbstractRealResultForBinaryRealIntegerArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public RealResultForRealAdditionIntegerTerm(IRealResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getInfixOp() {
		return "+";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForRealAdditionIntegerTerm(this);
	}
}
