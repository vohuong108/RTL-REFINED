package org.tzi.kodkod.comparison;

/**
 * Comparison result for atomary comparison with textual documentation.
 * 
 * @author Jan Prien
 *
 */
final class SingleComparisonDocumentation extends ComparisonDocumentation {

	/**
	 * The textual representation of the result.
	 */
	private final String text;

	/**
	 * Constructs an object.
	 * 
	 * @param text
	 *            The textual representation of the result.
	 */
	SingleComparisonDocumentation(String text) {
		this.text = text;
	}

	@Override
	String getText() {
		return text;
	}

}
