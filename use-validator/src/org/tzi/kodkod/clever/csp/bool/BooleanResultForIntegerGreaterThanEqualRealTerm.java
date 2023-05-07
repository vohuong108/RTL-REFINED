package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term for binary ">=".
 * 
 * @author Jan Prien
 *
 */
public final class BooleanResultForIntegerGreaterThanEqualRealTerm
		extends AbstractBooleanResultForBinaryIntegerRealArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public BooleanResultForIntegerGreaterThanEqualRealTerm(IIntegerResultTerm left, IRealResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getOp() {
		return ">=";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanResultForIntegerGreaterThanEqualRealTerm(this);
	}

}
