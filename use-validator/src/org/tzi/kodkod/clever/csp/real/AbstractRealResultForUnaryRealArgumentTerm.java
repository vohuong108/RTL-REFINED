package org.tzi.kodkod.clever.csp.real;

import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;

/**
 * Term with a real result on evaluation. The term is about one real term.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractRealResultForUnaryRealArgumentTerm extends AbstracRealResultTerm {

	/**
	 * The term, this term is about.
	 */
	private final IRealResultTerm value;

	/**
	 * Constructs an object.
	 * 
	 * @param value
	 *            The term, the term is about.
	 */
	public AbstractRealResultForUnaryRealArgumentTerm(IRealResultTerm value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	public IRealResultTerm getValue() {
		return value;
	}

	@Override
	public Set<IVariable<?>> getVariables() {
		return value.getVariables();
	}
}
