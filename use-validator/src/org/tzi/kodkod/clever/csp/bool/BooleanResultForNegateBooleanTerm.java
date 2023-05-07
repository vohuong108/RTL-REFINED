package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for unary logical "not".
 * 
 * @author Jan Prien
 *
 */
public final class BooleanResultForNegateBooleanTerm
		extends AbstractBooleanResultForUnaryBooleanArgumentTermWithZirkumfixOperator {

	/** {@inheritDoc} */
	public BooleanResultForNegateBooleanTerm(IBooleanResultTerm value) {
		super(value);
	}

	@Override
	protected String getPrefixOp() {
		return "not( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanResultForNegateBooleanTerm(this);
	}
}
