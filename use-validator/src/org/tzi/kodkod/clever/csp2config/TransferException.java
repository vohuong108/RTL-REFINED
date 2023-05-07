package org.tzi.kodkod.clever.csp2config;

/**
 * Exception for the case that for a model elements configuration aspect the
 * domains of a variable of a CSP can not be transferred.
 * 
 * 
 * @author Jan Prien
 *
 */
public final class TransferException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 6030127014782341438L;

	/**
	 * Creates an object.
	 * 
	 * @param message
	 *            The message.
	 */
	public TransferException(String message) {
		super(message);
	}

}
