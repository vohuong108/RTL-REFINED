package org.tzi.kodkod.clever.csp.bool;

import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a boolean result on evaluation. The term is about one boolean term.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractBooleanResultForUnaryBooleanArgumentTerm extends AbstractBooleanResultTerm {

	/**
	 * The term, this term is about.
	 */
	private final IBooleanResultTerm value;

	/**
	 * Constructs an object.
	 * 
	 * @param value
	 *            The term, the term is about.
	 */
	public AbstractBooleanResultForUnaryBooleanArgumentTerm(IBooleanResultTerm value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	public IBooleanResultTerm getValue() {
		return value;
	}

	@Override
	public Set<IVariable<?>> getVariables() {
		return value.getVariables();
	}
}
