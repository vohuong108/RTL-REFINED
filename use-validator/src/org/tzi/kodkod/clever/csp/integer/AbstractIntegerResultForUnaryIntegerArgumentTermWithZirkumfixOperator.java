package org.tzi.kodkod.clever.csp.integer;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a integer result on evaluation. The term is about one integer term.
 * The term has a specific operator.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractIntegerResultForUnaryIntegerArgumentTermWithZirkumfixOperator
		extends AbstractIntegerResultForUnaryIntegerArgumentTerm {

	/** {@inheritDoc} */
	public AbstractIntegerResultForUnaryIntegerArgumentTermWithZirkumfixOperator(IIntegerResultTerm value) {
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
