package org.tzi.kodkod.clever.model2csp;

/**
 * Exception for the case that in an initial bounds configuration
 * ({@link IModelCSPVariablesInitialBoundsSpecification}) inapropriate values
 * are to be aded.
 * 
 * @author Jan Prien
 *
 */
public final class IllegalInitialBoundException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -7435883317991660373L;

	/**
	 * Constructs an object.
	 * 
	 * @param msg
	 *            The message.
	 */
	public IllegalInitialBoundException(final String msg) {
		super(msg);
	}

}
