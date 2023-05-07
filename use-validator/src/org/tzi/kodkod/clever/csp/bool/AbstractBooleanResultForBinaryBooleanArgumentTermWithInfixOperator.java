package org.tzi.kodkod.clever.csp.bool;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a boolean result on evaluation. The term is about two boolean
 * terms. The term has a specific operator.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractBooleanResultForBinaryBooleanArgumentTermWithInfixOperator
		extends AbstractBooleanResultForBinaryBooleanArgumentTerm {

	/** {@inheritDoc} */
	public AbstractBooleanResultForBinaryBooleanArgumentTermWithInfixOperator(IBooleanResultTerm left,
			IBooleanResultTerm right) {
		super(left, right);
	}

	@Override
	public String toRepresentation(Map<IVariable<?>, String> variableOverwriteNames) {
		return "( " + getLeft().toRepresentation(variableOverwriteNames) + " " + getOp() + " "
				+ getRight().toRepresentation(variableOverwriteNames) + " )";
	}

	/**
	 * Returns the textual representation of the operator.
	 * 
	 * @return The textual representation of the operator.
	 */
	protected abstract String getOp();

}
