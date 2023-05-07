package org.tzi.kodkod.clever.csp.integer;

import java.util.Set;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.real.IRealResultTerm;

/**
 * Term with a integer result on evaluation. The term is about one real term.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractIntegerResultForUnaryRealArgumentTerm extends AbstractIntegerResultTerm {

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
	public AbstractIntegerResultForUnaryRealArgumentTerm(IRealResultTerm value) {
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
