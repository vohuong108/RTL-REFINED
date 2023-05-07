package org.tzi.kodkod.comparison;

import org.tzi.kodkod.comparison.ui.UIElements;

/**
 * The types of comparison results.
 * 
 * @author Jan Prien
 *
 */
public enum ComparisonResults {

	/**
	 * Indicates that two configuration aspects can not be compared.
	 */
	NOT_COMPARABLE(UIElements.NOT_COMPARABLE_Descriptor, false, UIElements.NOT_COMPARABLE_DescriptorText),

	/**
	 * Indicates that two configuration aspects can not be compared.
	 */
	INVALID_COMPARISON(UIElements.INVALID_COMPARISON_Descriptor, false, UIElements.INVALID_COMPARISON_DescriptorText),

	/**
	 * Indicates that two configuration aspects were not compared.
	 */
	IGNORED(UIElements.IGNORED_Descriptor, false, UIElements.IGNORED_DescriptorText),

	/**
	 * Indicates that two configuration aspects are not equal.
	 */
	UNEQUAL(UIElements.UNEQUAL_Descriptor, false, UIElements.UNEQUAL_DescriptorText),

	/**
	 * Indicates that two configuration aspects are equal.
	 */
	EQUAL(UIElements.EQUAL_Descriptor, true, UIElements.EQUAL_DescriptorText),

	/**
	 * Indicates that two configuration aspects are related but not equal. This is
	 * when the domain for the first operand contains the domain of the second
	 * operand.
	 */
	LEFT_BROAD_AND_RIGHT_NARROW(UIElements.LEFT_BROAD_AND_RIGHT_NARROW_Descriptor, true,
			UIElements.LEFT_BROAD_AND_RIGHT_NARROW_DescriptorText),

	/**
	 * Indicates that two configuration aspects are related but not equal. This is
	 * when the domain for the second operand contains the domain of the first
	 * operand.
	 */
	LEFT_NARROW_AND_RIGHT_BROAD(UIElements.LEFT_NARROW_AND_RIGHT_BROAD_Descriptor, true,
			UIElements.LEFT_NARROW_AND_RIGHT_BROAD_DescriptorText),

	/**
	 * Indicates that two configuration aspects are similar but not equal. This is
	 * when the domain for the first operand is not partially contained in the
	 * domain of the second operand and does also contain values that are less than
	 * the lowest value the domain of the second operand.
	 */
	LEFT_IS_PRELIMINARY_OVERLAPPING(UIElements.LEFT_IS_OVERLAPPING_Descriptor, false,
			UIElements.LEFT_IS_OVERLAPPING_DescriptorText),

	/**
	 * Indicates that two configuration aspects are similar but not equal. This is
	 * when the domain for the second operand is not partially contained in the
	 * domain of the first operand and does also contain values that are less than
	 * the lowest value the domain of the first operand.
	 */
	RIGHT_IS_PRELIMINARY_OVERLAPPING(UIElements.RIGHT_IS_OVERLAPPING_Descriptor, false,
			UIElements.RIGHT_IS_OVERLAPPING_DescriptorText),

	/**
	 * Indicates that two configuration aspects are different. This is when all
	 * values from the domain for the first operand are less than the lowest value
	 * the domain of the second operand.
	 */
	LEFT_IS_LESS_DISJOINT(UIElements.LEFT_IS_LESS_DISJOINT_Descriptor, false,
			UIElements.LEFT_IS_LESS_DISJOINT_DescriptorText),

	/**
	 * Indicates that two configuration aspects are different. This is when all
	 * values from the domain for the second operand are less than the lowest value
	 * the domain of the first operand.
	 */
	RIGHT_IS_LESS_DISJOINT(UIElements.RIGHT_IS_LESS_DISJOINT_Descriptor, false,
			UIElements.RIGHT_IS_LESS_DISJOINT_DescriptorText);

	/**
	 * The textual symbol.
	 */
	private final String descriptor;

	/**
	 * Whether the result represents an indication that the two operands are related
	 * in terms of the search space they represent.
	 */
	private final boolean indicatesFamilyRelation;

	/**
	 * The textual representation.
	 */
	private final String text;

	/**
	 * Pair of result types.
	 * 
	 * @author Jan Prien
	 *
	 */
	private static class ComparisonResultTypesPair {

		/**
		 * The first result type.
		 */
		private final ComparisonResults left;

		/**
		 * The second result type.
		 */
		private final ComparisonResults right;

		/**
		 * Constructs an object.
		 * 
		 * @param left
		 *            The first result type.
		 * @param right
		 *            The second result type.
		 */
		ComparisonResultTypesPair(final ComparisonResults left, final ComparisonResults right) {
			if (left == null || right == null) {
				throw new IllegalArgumentException();
			}
			this.left = left;
			this.right = right;
		}

	}

	/**
	 * The definition of types that are counterparts for each other.
	 */
	private final static ComparisonResultTypesPair[] counterparts = {
			new ComparisonResultTypesPair(ComparisonResults.NOT_COMPARABLE, ComparisonResults.NOT_COMPARABLE),
			new ComparisonResultTypesPair(ComparisonResults.INVALID_COMPARISON, ComparisonResults.INVALID_COMPARISON),
			new ComparisonResultTypesPair(ComparisonResults.IGNORED, ComparisonResults.IGNORED),
			new ComparisonResultTypesPair(ComparisonResults.EQUAL, ComparisonResults.EQUAL),
			new ComparisonResultTypesPair(ComparisonResults.UNEQUAL, ComparisonResults.UNEQUAL),
			new ComparisonResultTypesPair(ComparisonResults.LEFT_BROAD_AND_RIGHT_NARROW,
					ComparisonResults.LEFT_NARROW_AND_RIGHT_BROAD),
			new ComparisonResultTypesPair(ComparisonResults.LEFT_IS_PRELIMINARY_OVERLAPPING,
					ComparisonResults.RIGHT_IS_PRELIMINARY_OVERLAPPING),
			new ComparisonResultTypesPair(ComparisonResults.LEFT_IS_LESS_DISJOINT,
					ComparisonResults.RIGHT_IS_LESS_DISJOINT), };

	/**
	 * The definition of the types resulting in merging two types.
	 */
	private final static ComparisonResults[][] mergeResults = new ComparisonResults[ComparisonResults
			.values().length][ComparisonResults.values().length];

	static {
		int length = ComparisonResults.values().length;

		// At first only all matrix fields with row >= column are filled. Then the
		// entries are copied to the other fields.

		// If both types equal than the result is that type.
		// All cases where both types equal can be ignored here in the following.
		for (int row = 0; row < length; row++) {
			mergeResults[row][row] = ComparisonResults.values()[row];
		}

		// If one is NOT_COMPARABLE than merge result is NOT_COMPARABLE.
		// All cases where one is NOT_COMPARABLE can be ignored here in the following.
		for (int row = 1; row < length; row++) {
			mergeResults[row][0] = NOT_COMPARABLE;
		}

		// If one is INVALID_COMPARISON than merge result is INVALID_COMPARISON.
		// All cases where one is INVALID_COMPARISON can be ignored here in the
		// following.
		for (int row = 2; row < length; row++) {
			mergeResults[row][1] = INVALID_COMPARISON;
		}

		// If one is IGNORED than merge result is the type of the other.
		// All cases where one is IGNORED can be ignored here in the following.
		for (int row = 3; row < length; row++) {
			mergeResults[row][2] = ComparisonResults.values()[row];
		}

		// If one is UNEQUAL than merge result is UNEQUAL.
		// All cases where one is UNEQUAL can be ignored here in the
		// following.
		for (int row = 4; row < length; row++) {
			mergeResults[row][3] = UNEQUAL;
		}

		// If one is EQUAL than merge result is LEFT_BROAD_AND_RIGHT_NARROW if the other
		// is LEFT_BROAD_AND_RIGHT_NARROW or LEFT_NARROW_AND_RIGHT_BROAD if the other is
		// LEFT_NARROW_AND_RIGHT_BROAD or else UNEQUAL.
		// All cases where one is EQUAL can be ignored here in the
		// following.
		for (int row = 5; row <= 6; row++) {
			mergeResults[row][4] = ComparisonResults.values()[row];
		}
		for (int row = 7; row < length; row++) {
			mergeResults[row][4] = UNEQUAL;
		}

		// In all other cases the merge result is UNEQUAL.
		for (int row = 6; row < length; row++) {
			for (int column = 5; column < row; column++) {
				mergeResults[row][column] = UNEQUAL;
			}
		}

		// Up to here only fields with row >= column are filled.
		// Copies entries to the fields with row < column.
		for (int column = 1; column < length; column++) {
			for (int row = 0; row < column; row++) {
				mergeResults[row][column] = mergeResults[column][row];
			}
		}

		// Validates that each field in the matrix is filled.
		for (int row = 0; row < length; row++) {
			for (int column = 0; column < length; column++) {
				if (mergeResults[row][column] == null) {
					throw new UnsupportedOperationException();
				}
			}
		}
	}

	/**
	 * Constructs an object.
	 * 
	 * @param descriptor
	 *            The textual symbol.
	 * @param indicatesFamilyRelation
	 *            Whether the result represents an indication that the two operands
	 *            are related in terms of the search space they represent.
	 * @param text
	 *            The textual representation.
	 */
	ComparisonResults(final String descriptor, final boolean indicatesFamilyRelation, final String text) {
		if (descriptor == null || text == null) {
			throw new IllegalArgumentException();
		}
		this.descriptor = descriptor;
		this.indicatesFamilyRelation = indicatesFamilyRelation;
		this.text = text;
	}

	String getDescriptor() {
		return this.descriptor;
	}

	boolean indicatesFamilyRelation() {
		return this.indicatesFamilyRelation;
	}

	String getText() {
		return this.text;
	}

	/**
	 * Returns the result of merging two types.
	 * 
	 * @param left
	 *            The first type.
	 * @param right
	 *            The second type.
	 * @return The result of merging two types.
	 */
	static ComparisonResults combine(ComparisonResults left, ComparisonResults right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException();
		}
		return mergeResults[left.ordinal()][right.ordinal()];
	}

	/**
	 * Returns whether types are counterpart of each other.
	 * 
	 * @param left
	 *            The first type.
	 * @param right
	 *            The second type.
	 * @return Whether types are counterpart of each other.
	 */
	static boolean isCounterpartOf(ComparisonResults left, ComparisonResults right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException();
		}
		for (ComparisonResultTypesPair counterpart : counterparts) {
			if (counterpart.left == left && counterpart.right == right
					|| counterpart.left == right && counterpart.right == left) {
				return true;
			}
		}
		return false;
	}

}
