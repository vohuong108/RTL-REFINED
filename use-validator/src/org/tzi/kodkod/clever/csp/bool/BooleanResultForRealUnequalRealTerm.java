package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term for binary "!=".
 * 
 * @author Jan Prien
 *
 */
public final class BooleanResultForRealUnequalRealTerm
		extends AbstractBooleanResultForBinaryRealArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public BooleanResultForRealUnequalRealTerm(IRealResultTerm left, IRealResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getOp() {
		return "!=";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanResultForRealUnequalRealTerm(this);
	}
}
