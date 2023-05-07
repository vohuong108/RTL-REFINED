package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for binary "%".
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForIntegerModuloIntegerTerm
		extends AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForIntegerModuloIntegerTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	protected String getInfixOp() {
		return "%";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForIntegerModuloIntegerTerm(this);
	}
}
