package org.tzi.kodkod.clever.csp.bool;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a boolean result on evaluation. The term is about one boolean term.
 * The term has a specific operator.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractBooleanResultForUnaryBooleanArgumentTermWithZirkumfixOperator
		extends AbstractBooleanResultForUnaryBooleanArgumentTerm {

	/** {@inheritDoc} */
	public AbstractBooleanResultForUnaryBooleanArgumentTermWithZirkumfixOperator(IBooleanResultTerm value) {
		super(value);
	}

	@Override
	public String toRepresentation(Map<IVariable<?>, String> variableOverwriteNames) {
		return getPrefixOp() + getValue().toRepresentation(variableOverwriteNames) + getSuffixOp();
	}

	/**
	 * Returns the prefix part of the textual representation of the operator.
	 * 
	 * @return The textual representation of the operator.
	 */
	protected abstract String getPrefixOp();

	/**
	 * Returns the suffix part of the textual representation of the operator.
	 * 
	 * @return The textual representation of the operator.
	 */
	protected String getSuffixOp() {
		return " )";
	}

}
