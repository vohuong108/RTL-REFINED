package org.tzi.kodkod.clever.csp;

/**
 * Mapping for a domain to an element.
 * 
 * @author Jan Prien
 *
 * @param <A>
 *            A specific domain.
 */
public interface IReferenceHolder<A extends AbstractDomain<?>> extends ITerm {

	/**
	 * Returns the mapped element wrapper.
	 * 
	 * @return The mapped element wrapper.
	 */
	public IReferenciator<?> getRefereciator();

	/**
	 * Returns the mapped domain.
	 * 
	 * @return The mapped domain.
	 */
	public A getDomain();

}
