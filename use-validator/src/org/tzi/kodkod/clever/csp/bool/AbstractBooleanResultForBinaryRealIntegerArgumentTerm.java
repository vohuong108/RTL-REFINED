package org.tzi.kodkod.clever.csp.bool;

import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term with a boolean result on evaluation. The term is about a real term and
 * an integer term.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractBooleanResultForBinaryRealIntegerArgumentTerm extends AbstractBooleanResultTerm {

	/**
	 * The first term, this term is about.
	 */
	private final IRealResultTerm left;

	/**
	 * The first term, this term is about.
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
	public AbstractBooleanResultForBinaryRealIntegerArgumentTerm(IRealResultTerm left, IIntegerResultTerm right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException();
		}
		this.left = left;
		this.right = right;
	}

	public IIntegerResultTerm getRight() {
		return right;
	}

	public IRealResultTerm getLeft() {
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
