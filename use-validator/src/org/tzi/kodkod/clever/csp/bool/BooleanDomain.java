package org.tzi.kodkod.clever.csp.bool;

import org.tzi.kodkod.clever.csp.AbstractDomain;

/**
 * Boolean domain. <code>false</code>} is less than <code>true</code>.
 * 
 * @author Jan Prien
 *
 */
public final class BooleanDomain extends AbstractDomain<Boolean> {

	/** {@inheritDoc} */
	public BooleanDomain(Boolean lowerBound, Boolean upperBound) {
		super(lowerBound, upperBound);
		if (lowerBound && !upperBound) {
			throw new IllegalArgumentException();
		}
	}

}
