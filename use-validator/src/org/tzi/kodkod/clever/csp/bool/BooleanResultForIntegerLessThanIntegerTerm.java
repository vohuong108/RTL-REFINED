package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term for binary "<".
 * 
 * @author Jan Prien
 *
 */
public final class BooleanResultForIntegerLessThanIntegerTerm
		extends AbstractBooleanResultForBinaryIntegerArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public BooleanResultForIntegerLessThanIntegerTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getOp() {
		return "<";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanResultForIntegerLessThanIntegerTerm(this);
	}
}
