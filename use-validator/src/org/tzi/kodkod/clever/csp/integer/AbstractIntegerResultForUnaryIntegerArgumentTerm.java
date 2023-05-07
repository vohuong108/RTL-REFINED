package org.tzi.kodkod.clever.csp.integer;

import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a integer result on evaluation. The term is about one integer term.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractIntegerResultForUnaryIntegerArgumentTerm extends AbstractIntegerResultTerm {

	/**
	 * The term, this term is about.
	 */
	private final IIntegerResultTerm value;

	/**
	 * Constructs an object.
	 * 
	 * @param value
	 *            The term, the term is about.
	 */
	public AbstractIntegerResultForUnaryIntegerArgumentTerm(IIntegerResultTerm value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	public IIntegerResultTerm getValue() {
		return value;
	}

	@Override
	public Set<IVariable<?>> getVariables() {
		return value.getVariables();
	}

}
