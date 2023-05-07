package org.tzi.kodkod.clever.csp;

/**
 * Domain for a CSP variable. This domains are ranges of discrete values.
 * 
 * @author Jan Prien
 *
 * @param <A>
 *            The type of the values in the domain.
 */
public abstract class AbstractDomain<A> {

	/**
	 * The lowest value in the domain.
	 */
	private final A lowerBound;

	/**
	 * The highest value in the domain.
	 */
	private final A upperBound;

	/**
	 * Constructs an object.
	 * 
	 * @param lowerBound
	 *            The lowest value in the domain.
	 * @param upperBound
	 *            The highest value in the domain.
	 */
	public AbstractDomain(A lowerBound, A upperBound) {
		if (lowerBound == null || upperBound == null) {
			throw new IllegalArgumentException();
		}
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public A getLowerBound() {
		return lowerBound;
	}

	public A getUpperBound() {
		return upperBound;
	}

	@Override
	public String toString() {
		return "[" + getLowerBound() + "," + getUpperBound() + "]";
	}
}
