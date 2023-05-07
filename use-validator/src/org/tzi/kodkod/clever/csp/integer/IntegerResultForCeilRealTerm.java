package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term for unary "ceil" that gives the next lower integer value than the real
 * value on evaluation.
 * 
 * @author Jan Prien
 *
 */
public class IntegerResultForCeilRealTerm extends AbstractIntegerResultForUnaryRealArgumentTermWithZirkumfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForCeilRealTerm(IRealResultTerm value) {
		super(value);
	}

	@Override
	protected String getPrefixOp() {
		return "ceil( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForCeilRealTerm(this);
	}

}
