package org.tzi.kodkod.clever.csp.integer;

import org.tzi.kodkod.clever.csp.AbstractDomain;

/**
 * Integer domain.
 * 
 * @author Jan Prien
 *
 */
public final class IntegerDomain extends AbstractDomain<Integer> {

	/** {@inheritDoc} */
	public IntegerDomain(Integer lowerBound, Integer upperBound) {
		super(lowerBound, upperBound);
		if (lowerBound > upperBound) {
			throw new IllegalArgumentException();
		}
	}

}
