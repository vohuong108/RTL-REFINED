package org.tzi.kodkod.clever.csp.bool;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term with a boolean result on evaluation. The term is about a term and an
 * integer term. The term has a specific operator.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractBooleanResultForBinaryRealIntegerArgumentTermWithInfixOperator
		extends AbstractBooleanResultForBinaryRealIntegerArgumentTerm {

	/** {@inheritDoc} */
	public AbstractBooleanResultForBinaryRealIntegerArgumentTermWithInfixOperator(IRealResultTerm left,
			IIntegerResultTerm right) {
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
