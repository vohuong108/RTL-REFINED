package org.tzi.kodkod.clever;

/**
 * Exception for error in the process of cleverly generation of instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
public class GenerationException extends Exception {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 2738291570285911828L;

	/**
	 * Constructs an objects.
	 * 
	 * @param message
	 *            The message of the exception.
	 */
	public GenerationException(String message) {
		super(message);
	}

}
