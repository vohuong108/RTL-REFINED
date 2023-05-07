package org.tzi.kodkod.clever.model2csp;

import org.tzi.kodkod.clever.csp.IReferenciator;

/**
 * Wrapper for an UML integer value.
 * 
 * @author Jan Prien
 *
 */
final class IntegerReferenciator implements IReferenciator<Integer> {

	/**
	 * The wrapped integer value.
	 */
	private final Integer integer;

	/**
	 * Constructs an object.
	 * 
	 * @param integer
	 *            The wrapper intger value.
	 */
	IntegerReferenciator(Integer integer) {
		if (integer == null) {
			throw new IllegalArgumentException();
		}
		this.integer = integer;
	}

	@Override
	public Integer getReference() {
		return integer;
	}

	@Override
	public String getDerivedString() {
		return "" + integer;
	}

	@Override
	public boolean referencesSame(IReferenciator<?> referenciator) {
		return referenciator != null && referenciator instanceof IntegerReferenciator
				&& integer.equals(((IntegerReferenciator) referenciator).integer);
	}

}
