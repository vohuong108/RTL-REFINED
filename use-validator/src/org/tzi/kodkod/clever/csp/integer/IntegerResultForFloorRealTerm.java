package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term for unary "floor" that gives the next higher integer value than the real
 * value on evaluation.
 * 
 * @author Jan Prien
 *
 */
public final class IntegerResultForFloorRealTerm
		extends AbstractIntegerResultForUnaryRealArgumentTermWithZirkumfixOperator {

	/** {@inheritDoc} */
	public IntegerResultForFloorRealTerm(IRealResultTerm value) {
		super(value);
	}

	@Override
	protected String getPrefixOp() {
		return "floor( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerResultForFloorRealTerm(this);
	}

}
