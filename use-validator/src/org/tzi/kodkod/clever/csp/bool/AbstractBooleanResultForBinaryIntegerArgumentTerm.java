package org.tzi.kodkod.clever.csp.bool;

import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term with a boolean result on evaluation. The term is about two integer
 * terms.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractBooleanResultForBinaryIntegerArgumentTerm extends AbstractBooleanResultTerm {

	/**
	 * The first term, this term is about.
	 */
	private final IIntegerResultTerm left;

	/**
	 * The second term, this term is about.
	 */
	private final IIntegerResultTerm right;

	/**
	 * Constructs an object.
	 * 
	 * @param left
	 *            The first term, the term is about.
	 * @param right
	 *            The second term, the term is about.
	 */
	public AbstractBooleanResultForBinaryIntegerArgumentTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException();
		}
		this.left = left;
		this.right = right;
	}

	public IIntegerResultTerm getRight() {
		return right;
	}

	public IIntegerResultTerm getLeft() {
		return left;
	}

	@Override
	public Set<IVariable<?>> getVariables() {
		Set<IVariable<?>> variables = new HashSet<>();
		variables.addAll(getLeft().getVariables());
		variables.addAll(getRight().getVariables());
		return variables;
	}
}
