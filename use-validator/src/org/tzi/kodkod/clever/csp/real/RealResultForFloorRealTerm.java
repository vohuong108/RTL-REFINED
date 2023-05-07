package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.ITermVisitor;

/**
 * Term for unary "floor".
 * 
 * @author Jan Prien
 *
 */
public final class RealResultForFloorRealTerm extends AbstractRealResultForUnaryRealArgumentTermWithZirkumfixOperator {

	/** {@inheritDoc} */
	public RealResultForFloorRealTerm(IRealResultTerm value) {
		super(value);
	}

	@Override
	protected String getPrefixOp() {
		return "floor( ";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealResultForFloorRealTerm(this);
	}

}
