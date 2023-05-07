package org.tzi.kodkod.clever.model2csp;

/**
 * Generates unique identifier. This currently returns an incremented number.
 * 
 * @author Jan Prien
 *
 */
final class IdentifierGenerator {

	/**
	 * The next unique identifier.
	 */
	private int identifierCounter = 0;

	/**
	 * Constructs an object.
	 */
	IdentifierGenerator() {

	}

	/**
	 * Returns an unique identifier
	 * 
	 * @return An unique identifier.
	 */
	protected String generate() {
		identifierCounter++;
		return "" + (identifierCounter - 1);
	}

}
