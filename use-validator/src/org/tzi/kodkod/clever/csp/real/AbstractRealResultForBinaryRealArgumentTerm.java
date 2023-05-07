package org.tzi.kodkod.clever.csp.real;

import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a real result on evaluation. The term is about two real terms.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractRealResultForBinaryRealArgumentTerm extends AbstracRealResultTerm {

	/**
	 * The first term, this term is about.
	 */
	private final IRealResultTerm left;

	/**
	 * The second term, this term is about.
	 */
	private final IRealResultTerm right;

	/**
	 * Constructs an object.
	 * 
	 * @param left
	 *            The first term, the term is about.
	 * @param right
	 *            The second term, the term is about.
	 */
	public AbstractRealResultForBinaryRealArgumentTerm(IRealResultTerm left, IRealResultTerm right) {
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

	public IRealResultTerm getRight() {
		return right;
	}
}
