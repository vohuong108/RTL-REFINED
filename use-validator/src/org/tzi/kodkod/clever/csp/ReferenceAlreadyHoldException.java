package org.tzi.kodkod.clever.csp;

/**
 * Exception for the case that a CSP already contains a variable that is mapped
 * to the same element as the element for that a variable should be created on
 * the CSP.
 * 
 * @author Jan Prien
 *
 */
public final class ReferenceAlreadyHoldException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -3975889960386299035L;

}
