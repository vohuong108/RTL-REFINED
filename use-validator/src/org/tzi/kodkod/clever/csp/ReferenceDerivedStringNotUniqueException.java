package org.tzi.kodkod.clever.csp;

/**
 * Exception for the case that a CSP already contains a variable that is mapped
 * to an element with the same textual representation as the elements textual
 * representation for that a variable should be created on the CSP.
 * 
 * @author Jan Prien
 *
 */
public final class ReferenceDerivedStringNotUniqueException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 2444209243605886064L;

}
