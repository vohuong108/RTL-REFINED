package org.tzi.kodkod.clever.csp.integer;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a integer result on evaluation. The term is about two integer
 * terms. The term has a specific operator.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixOperator
		extends AbstractIntegerResultForBinaryIntegerArgumentTerm {

	/** {@inheritDoc} */
	public AbstractIntegerResultForBinaryIntegerArgumentTermWithInfixOperator(IIntegerResultTerm left,
			IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	public String toRepresentation(Map<IVariable<?>, String> variableOverwriteNames) {
		return "( " + getLeft().toRepresentation(variableOverwriteNames) + " " + getInfixOp() + " "
				+ getRight().toRepresentation(variableOverwriteNames) + " )";
	}

	/**
	 * Returns the infix part of the textual representation of the operator.
	 * 
	 * @return The textual representation of the operator.
	 */
	protected abstract String getInfixOp();

}
