package org.tzi.kodkod.clever.csp.real;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term with a real result on evaluation. The term is about one real term and
 * one integer term. The term has a specific operator.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractRealResultForBinaryRealIntegerArgumentTermWithInfixOperator
		extends AbstractRealResultForBinaryRealIntegerArgumentTerm {

	/** {@inheritDoc} */
	public AbstractRealResultForBinaryRealIntegerArgumentTermWithInfixOperator(IRealResultTerm left,
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
