package org.tzi.kodkod.clever.csp.real;

import org.tzi.kodkod.clever.csp.AbstractDomain;

/**
 * Real domain.
 * 
 * @author Jan Prien
 *
 */
public final class RealDomain extends AbstractDomain<Double> {

	/**
	 * The maximum precision of values in the domain.
	 */
	private final Double precison;

	/**
	 * Constructs an object.
	 * 
	 * @param lowerBound
	 *            The lowest value in the domain.
	 * @param upperBound
	 *            The highest value in the domain.
	 * @param precison
	 *            The maximum precision of values in the domain.
	 */
	public RealDomain(Double lowerBound, Double upperBound, Double precison) {
		super(lowerBound, upperBound);
		if (precison == null) {
			throw new IllegalArgumentException();
		}
		this.precison = precison;
	}

	public double getPrecision() {
		return precison;
	}

}
