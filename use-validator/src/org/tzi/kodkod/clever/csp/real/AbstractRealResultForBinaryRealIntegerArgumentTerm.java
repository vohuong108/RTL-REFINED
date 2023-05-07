package org.tzi.kodkod.clever.csp.real;

import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;

/**
 * Term with a real result on evaluation. The term is about one real term and
 * one integer term.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractRealResultForBinaryRealIntegerArgumentTerm extends AbstracRealResultTerm {

	/**
	 * The first term, this term is about.
	 */
	private final IRealResultTerm left;

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
	public AbstractRealResultForBinaryRealIntegerArgumentTerm(IRealResultTerm left, IIntegerResultTerm right) {
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

	public IRealResultTerm getLeft() {
		return left;
	}

	public IIntegerResultTerm getRight() {
		return right;
	}
}
