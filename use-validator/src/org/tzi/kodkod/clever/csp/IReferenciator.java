package org.tzi.kodkod.clever.csp;

/**
 * Wrapper for an element.
 * 
 * @author Jan Prien
 *
 * @param <A>
 *            The type of the element to be wrapped.
 */
public interface IReferenciator<A> {

	/**
	 * Returns the wrapped element.
	 * 
	 * @return The wrapped element.
	 */
	public A getReference();

	/**
	 * Returns the textual representation for the wrapped element.
	 * 
	 * @return
	 */
	public String getDerivedString();

	/**
	 * Return whether an other wrapper references the same element.
	 * 
	 * @param referenciator
	 *            The other wrapper.
	 * @return Whether an other wrapper references the same element.
	 */
	public boolean referencesSame(IReferenciator<?> referenciator);

}
