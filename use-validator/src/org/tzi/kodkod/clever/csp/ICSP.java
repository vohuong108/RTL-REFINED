package org.tzi.kodkod.clever.csp;

import java.util.Set;

/**
 * Structure of a Constraint Satisfaction Problem (CSP). A CSP consists of
 * variables and constraints. Each variable has a domain. The constraints are
 * defined on the variables.
 * 
 * @author Jan Prien
 *
 */
public interface ICSP {

	/**
	 * Returns all elements that are mapped with the variables.
	 */
	public Set<?> getReferences();

	/**
	 * Returns the domain of a variable that is mapped to a selected element.
	 * 
	 * @param referenciator
	 *            The mapping the variable must also represent.
	 * @return The domain of a variable that is mapped to the selected element.
	 */
	public <A extends AbstractDomain<?>> A getDomain(IReferenciator<?> iReferenciator);

}
