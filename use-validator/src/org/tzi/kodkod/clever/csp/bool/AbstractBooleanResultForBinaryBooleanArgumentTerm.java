package org.tzi.kodkod.clever.csp.bool;

import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a boolean result on evaluation. The term is about two boolean
 * terms.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractBooleanResultForBinaryBooleanArgumentTerm extends AbstractBooleanResultTerm {

	/**
	 * The first term, this term is about.
	 */
	private final IBooleanResultTerm left;

	/**
	 * The second term, this term is about.
	 */
	private final IBooleanResultTerm right;

	/**
	 * Constructs an object.
	 * 
	 * @param left
	 *            The first term, the term is about.
	 * @param right
	 *            The second term, the term is about.
	 */
	public AbstractBooleanResultForBinaryBooleanArgumentTerm(IBooleanResultTerm left, IBooleanResultTerm right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException();
		}
		this.left = left;
		this.right = right;
	}

	public IBooleanResultTerm getRight() {
		return right;
	}

	public IBooleanResultTerm getLeft() {
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
