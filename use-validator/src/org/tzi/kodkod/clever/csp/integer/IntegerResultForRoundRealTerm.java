package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term for unary "round".
 * 
 * @author Jan Prien
 *
 */
public class IntegerResultForRoundRealTerm extends AbstractIntegerResultForUnaryRealArgumentTermWithZirkumfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForRoundRealTerm(IRealResultTerm value) {
		super(value);
	}

	@Override
	protected String getPrefixOp() {
		return "round( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForRoundRealTerm(this);
	}
}
