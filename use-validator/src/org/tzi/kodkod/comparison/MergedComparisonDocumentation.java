package org.tzi.kodkod.comparison;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Merged comparison result for multiple compared aspects with textual
 * documentation.
 * 
 * @author Jan Prien
 *
 */
final class MergedComparisonDocumentation extends ComparisonDocumentation {

	/**
	 * The documentations for types of comparison results.
	 */
	private final Map<ComparisonResults, Set<SingleComparisonDocumentation>> documentationsForTypes;

	/**
	 * Constructs an object.
	 * 
	 * @param comparisonResult1
	 *            The result as first operand to merge.
	 * @param comparisonResult2
	 *            The result as second operand to merge.
	 */
	public MergedComparisonDocumentation(final ComparisonResult comparisonResult1,
			final ComparisonResult comparisonResult2) {
		this.documentationsForTypes = new HashMap<ComparisonResults, Set<SingleComparisonDocumentation>>();
		for (ComparisonResults configurationResultsTypes : ComparisonResults.values()) {
			this.documentationsForTypes.put(configurationResultsTypes, new HashSet<>());
		}
		this.addOrAddAll(comparisonResult1.getType(), comparisonResult1.getDocumentation());
		this.addOrAddAll(comparisonResult2.getType(), comparisonResult2.getDocumentation());
	}

	/**
	 * Adds a documentation for a type of result.
	 * 
	 * @param type
	 *            The type.
	 * @param documentation
	 *            The documentation.
	 */
	private void addOrAddAll(final ComparisonResults type, final ComparisonDocumentation documentation) {
		if (type == null || documentation == null) {
			throw new IllegalArgumentException();
		}
		if (documentation instanceof SingleComparisonDocumentation) {
			this.add(type, documentation);
		} else if (documentation instanceof MergedComparisonDocumentation) {
			this.addAll((MergedComparisonDocumentation) documentation);
		} else {
			throw new UnsupportedOperationException("Type " + documentation.getClass().toString() + " of "
					+ documentation.toString() + " could not be handled.");
		}
	}

	/**
	 * Adds a documentation for a type of result.
	 * 
	 * @param type
	 *            The type.
	 * @param documentation
	 *            The documentation.
	 */
	private void add(final ComparisonResults type, final ComparisonDocumentation documentation) {
		if (type == null || documentation == null) {
			throw new IllegalArgumentException();
		}

		Set<SingleComparisonDocumentation> documentationsOfType = this.documentationsForTypes.get(type);
		if (documentationsOfType == null) {
			documentationsOfType = new HashSet<>();
		}
		documentationsOfType.add((SingleComparisonDocumentation) documentation);
		this.documentationsForTypes.put(type, documentationsOfType);
	}

	/**
	 * Adds all documentations for types of result from another documentation.
	 * 
	 * @param type
	 *            The type.
	 * @param documentation
	 *            The documentation.
	 */
	private void addAll(final MergedComparisonDocumentation documentation) {
		if (documentation == null) {
			throw new IllegalArgumentException();
		}
		for (Entry<ComparisonResults, Set<SingleComparisonDocumentation>> documentationsForTypes : ((MergedComparisonDocumentation) documentation).documentationsForTypes
				.entrySet()) {
			ComparisonResults type = documentationsForTypes.getKey();
			Set<SingleComparisonDocumentation> documentationsOfType = this.documentationsForTypes.get(type);
			if (documentationsOfType == null) {
				documentationsOfType = new HashSet<>();
			}
			documentationsOfType.addAll(documentationsForTypes.getValue());
			this.documentationsForTypes.put(type, documentationsOfType);
		}
	}

	@Override
	String getText() {
		String text = new String();
		for (ComparisonResults type : ComparisonResults.values()) {
			Set<SingleComparisonDocumentation> documentations = documentationsForTypes.get(type);
			if (!documentations.isEmpty()) {
				text += getText(type.getText(), documentations);
			}
		}
		return text;
	}

	/**
	 * Returns the textual representations for all documentations for a type of
	 * result.
	 * 
	 * @param resultClassification
	 *            The textual grouping for the type.
	 * @param comparisonDocumentations
	 *            The documentations for the type.
	 * @return The textual representations for the documentations for the type.
	 */
	private String getText(final String resultClassification,
			final Set<SingleComparisonDocumentation> comparisonDocumentations) {
		String text = resultClassification + "\n";
		for (SingleComparisonDocumentation comparisonDocumentation : comparisonDocumentations) {
			String comparisonDocumentationText = comparisonDocumentation.getText();
			comparisonDocumentationText = removeNewlineSuffixes(comparisonDocumentationText);
			comparisonDocumentationText = insertedTabsAfterNewlines("   ", comparisonDocumentationText);
			text += comparisonDocumentationText + "\n";
		}
		return text;
	}

	/**
	 * Removes "\n" from the end of a string.
	 * 
	 * @param text
	 *            The string.
	 * @return The string without "\n" at the end.
	 */
	private static String removeNewlineSuffixes(final String text) {
		if (text == null) {
			return null;
		}
		String result = text;
		while (result.endsWith("\n")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * Insert a string after each "\n" in a string.
	 * 
	 * @param tab
	 *            The string to insert.
	 * @param text
	 *            The string to modify.
	 * @return The mofified string.
	 */
	private static String insertedTabsAfterNewlines(final String tab, final String text) {
		if (text == null) {
			return null;
		}
		String result = text;
		String[] lines = result.split("\n");
		result = "";
		for (String line : lines) {
			result += tab + line + "\n";
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

}
