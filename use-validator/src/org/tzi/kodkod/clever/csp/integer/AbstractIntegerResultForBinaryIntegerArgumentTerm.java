package org.tzi.kodkod.clever.csp.integer;

import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a integer result on evaluation. The term is about two integer
 * terms.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractIntegerResultForBinaryIntegerArgumentTerm extends AbstractIntegerResultTerm {

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
	public AbstractIntegerResultForBinaryIntegerArgumentTerm(IIntegerResultTerm left, IIntegerResultTerm right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException();
		}
		this.left = left;
		this.right = right;
	}

	@Override
	public Set<IVariable<?>> getVariables() {
		Set<IVariable<?>> variables = new HashSet<>();
		variables.addAll(getLeft().getVariables());
		variables.addAll(getRight().getVariables());
		return variables;
	}

	public IIntegerResultTerm getLeft() {
		return left;
	}

	public IIntegerResultTerm getRight() {
		return right;
	}

}
