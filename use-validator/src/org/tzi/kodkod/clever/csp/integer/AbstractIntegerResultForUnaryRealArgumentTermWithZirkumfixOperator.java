package org.tzi.kodkod.clever.csp.integer;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term with a integer result on evaluation. The term is about one real term.
 * The term has a specific operator.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractIntegerResultForUnaryRealArgumentTermWithZirkumfixOperator
		extends AbstractIntegerResultForUnaryRealArgumentTerm {

	/** {@inheritDoc} */
	public AbstractIntegerResultForUnaryRealArgumentTermWithZirkumfixOperator(IRealResultTerm value) {
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
