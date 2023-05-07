package org.tzi.kodkod.clever.csp.real;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term with a real result on evaluation. The term is about two integer terms.
 * The term has a specific operator.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractRealResultForBinaryIntegerArgumentTermWithInfixAndZirkumfixOperator
		extends AbstractRealResultForBinaryIntegerArgumentTerm {

	/** {@inheritDoc} */
	public AbstractRealResultForBinaryIntegerArgumentTermWithInfixAndZirkumfixOperator(IIntegerResultTerm left,
			IIntegerResultTerm right) {
		super(left, right);
	}

	@Override
	public String toRepresentation(Map<IVariable<?>, String> variableOverwriteNames) {
		return getPrefixOp() + getLeft().toRepresentation(variableOverwriteNames) + getInfixOp()
				+ getRight().toRepresentation(variableOverwriteNames) + getSuffixOp();
	}

	/**
	 * Returns the prefix part of the textual representation of the operator.
	 * 
	 * @return The textual representation of the operator.
	 */
	protected abstract String getPrefixOp();

	/**
	 * Returns the infix part of the textual representation of the operator.
	 * 
	 * @return The textual representation of the operator.
	 */
	protected String getInfixOp() {
		return " , ";
	}

	/**
	 * Returns the suffix part of the textual representation of the operator.
	 * 
	 * @return The textual representation of the operator.
	 */
	protected String getSuffixOp() {
		return " )";
	}

}
