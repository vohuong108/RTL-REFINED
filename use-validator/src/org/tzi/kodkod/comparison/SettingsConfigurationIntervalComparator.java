package org.tzi.kodkod.comparison;

import org.tzi.kodkod.comparison.ui.UIElements;

/**
 * Provides generic comparison of configurations.
 * 
 * @author Jan Prien
 *
 */
final class SettingsConfigurationIntervalComparator {

	/**
	 * Compares the lower and upper bound values of two configuration aspects.
	 * 
	 * Returns {@link ComparisonResults#INVALID_COMPARISON} if the enabled values
	 * differ. Returns {@link ComparisonResults#EQUAL} if both are disabled. Else
	 * wise the result of
	 * {@link #compareMinMax(Object, Object, String, String, String, int, int, int, int, boolean, boolean, boolean)}.
	 * 
	 * @param left
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param right
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @param attribute
	 *            The textual representation of the aspect with lower and upper
	 *            bound.
	 * @param minName
	 *            The textual representation of the lower bound aspect.
	 * @param maxName
	 *            The textual representation of the upper bound aspect.
	 * @param leftEnabled
	 *            Whether the aspect with lower and upper bound of the first operand
	 *            is enabled.
	 * @param rightEnabled
	 *            Whether the aspect with lower and upper bound of the second
	 *            operand is enabled.
	 * @param leftMin
	 *            The value of the lower bound of the first operand.
	 * @param leftMax
	 *            The value of the upper bound of the first operand.
	 * @param rightMin
	 *            The value of the lower bound of the second operand.
	 * @param rightMax
	 *            The value of the upper bound of the second operand.
	 * @param minLowerZeroAllowed
	 *            Whether the lower bound is allowed to be less than 0.
	 * @param maxLowerZeroAllowed
	 *            Whether the upper bound is allowed to be less than 0.
	 * @param maxStarAllowed
	 *            Whether the lower bound is not allowed to be less than 0 but -1 as
	 *            unlimited.
	 * @return The comparison result.
	 */
	ComparisonResult compareMinMax(final Object left, final Object right, String attribute, final String minName,
			final String maxName, final boolean leftEnabled, final boolean rightEnabled, final int leftMin,
			final int leftMax, final int rightMin, final int rightMax, final boolean minLowerZeroAllowed,
			final boolean maxLowerZeroAllowed, final boolean maxStarAllowed) {
		if (leftEnabled != rightEnabled) {
			return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
					new SingleComparisonDocumentation(
							UIElements.LeftEnabledUnequalsRightEnabled(attribute, leftEnabled, rightEnabled)));
		}
		if (!leftEnabled && !rightEnabled) {
			return new ComparisonResult(ComparisonResults.EQUAL, left, right,
					new SingleComparisonDocumentation(UIElements.EnabledBothFalse(attribute)));
		}
		return compareMinMax(left, right, attribute, minName, maxName, leftMin, leftMax, rightMin, rightMax,
				minLowerZeroAllowed, maxLowerZeroAllowed, maxStarAllowed);
	}

	/**
	 * Compares the lower and upper bound values of two configuration aspects.
	 * 
	 * This returns:
	 * <ul>
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} if the lower bound is not
	 * allowed to be 0 but a lower bound is less than zero.
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} if the upper bound is not
	 * allowed to be 0 but an upper bound is less than zero.
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} if the upper bound is not
	 * allowed to be 0 but a lower bound is less than zero and not -1 for unlimited.
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} if a lower bound is less
	 * than an upper bound.
	 * <li>{@link ComparisonResults#EQUAL} when both lower and upper bounds equal.
	 * <li>{@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW} when the domain for
	 * the first operand contains the domain of the second operand.
	 * <li>{@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD} when the domain for
	 * the second operand contains the domain of the first operand.
	 * <li>{@link ComparisonResults#LEFT_IS_LESS_DISJOINT} when all values from the
	 * domain for the first operand are less than the lowest value the domain of the
	 * second operand.
	 * <li>{@link ComparisonResults#RIGHT_IS_LESS_DISJOINT} when all values from the
	 * domain for the second operand are less than the lowest value the domain of
	 * the first operand.
	 * <li>{@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING} when the domain
	 * for the first operand is not partially contained in the domain of the second
	 * operand and does also contain values that are less than the lowest value the
	 * domain of the second operand.
	 * <li>{@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING} when the
	 * domain for the second operand is not partially contained in the domain of the
	 * first operand and does also contain values that are less than the lowest
	 * value the domain of the first operand.
	 * <li>{@link ComparisonResults#UNEQUAL} else wise.
	 * </ul>
	 * The first applying result is chosen from this list.
	 * 
	 * @param left
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param right
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @param attribute
	 *            The textual representation of the aspect with lower and upper
	 *            bound.
	 * @param minName
	 *            The textual representation of the lower bound aspect.
	 * @param maxName
	 *            The textual representation of the upper bound aspect.
	 * @param leftMin
	 *            The value of the lower bound of the first operand.
	 * @param leftMax
	 *            The value of the upper bound of the first operand.
	 * @param rightMin
	 *            The value of the lower bound of the second operand.
	 * @param rightMax
	 *            The value of the upper bound of the second operand.
	 * @param minLowerZeroAllowed
	 *            Whether the lower bound is allowed to be less than 0.
	 * @param maxLowerZeroAllowed
	 *            Whether the upper bound is allowed to be less than 0.
	 * @param maxStarAllowed
	 *            Whether the lower bound is not allowed to be less than 0 but -1 as
	 *            unlimited.
	 * @return The comparison result.
	 */
	ComparisonResult compareMinMax(final Object left, final Object right, String attribute, final String minName,
			final String maxName, final int leftMin, final int leftMax, final int rightMin, final int rightMax,
			final boolean minLowerZeroAllowed, final boolean maxLowerZeroAllowed, final boolean maxStarAllowed) {
		if (maxLowerZeroAllowed && maxStarAllowed || !minLowerZeroAllowed && maxLowerZeroAllowed) {
			throw new IllegalArgumentException();
		}
		if (!minLowerZeroAllowed) {
			if (leftMin < 0) {
				return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
						new SingleComparisonDocumentation(attribute + minName + " of left is less than 0."));
			} else if (rightMin < 0) {
				return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
						new SingleComparisonDocumentation(attribute + minName + " of right is less than 0."));
			}
		}
		if (!maxLowerZeroAllowed) {
			if (maxStarAllowed) {
				if (leftMax < -1) {
					return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
							new SingleComparisonDocumentation(attribute + maxName + " of left is less than -1."));
				} else if (rightMax < -1) {
					return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
							new SingleComparisonDocumentation(attribute + maxName + " of right is less than -1."));
				}
			} else {
				if (leftMax < 0) {
					return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
							new SingleComparisonDocumentation(attribute + maxName + " of left is less than 0."));
				} else if (rightMax < 0) {
					return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
							new SingleComparisonDocumentation(attribute + maxName + " of right is less than 0."));
				}
			}
		}
		if (!minLowerZeroAllowed && !maxLowerZeroAllowed) {
			if (leftMax > -1 && leftMax < leftMin) {
				return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
						new SingleComparisonDocumentation(
								attribute + maxName + " of left is less than " + attribute + minName + "."));
			} else if (rightMax > -1 && rightMax < rightMin) {
				return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
						new SingleComparisonDocumentation(
								attribute + maxName + " of right is less than " + attribute + minName + "."));
			}
		} else if (minLowerZeroAllowed && !maxLowerZeroAllowed || minLowerZeroAllowed && maxLowerZeroAllowed) {
			if (leftMin > leftMax) {
				return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
						new SingleComparisonDocumentation(
								attribute + maxName + " of left is less than " + attribute + minName + "."));
			}
			if (rightMin > rightMax) {
				return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, left, right,
						new SingleComparisonDocumentation(
								attribute + maxName + " of right is less than " + attribute + minName + "."));
			}
		}
		final String details = UIElements.ComparisonDetails(attribute + minName, String.valueOf(leftMin),
				String.valueOf(rightMin), attribute + maxName, String.valueOf(leftMax), String.valueOf(rightMax));
		if (leftMin == rightMin && leftMax == rightMax) {
			return new ComparisonResult(ComparisonResults.EQUAL, left, right, new SingleComparisonDocumentation(
					UIElements.EQUAL_Details(attribute + minName, attribute + maxName, details)));
		} else if (isLeftMinLessThanOrEqual(leftMin, rightMin)
				&& isLeftMaxHigherThanOrEqual(leftMax, rightMax, maxLowerZeroAllowed, maxStarAllowed)) {
			return new ComparisonResult(ComparisonResults.LEFT_BROAD_AND_RIGHT_NARROW, left, right,
					new SingleComparisonDocumentation(UIElements
							.LEFT_BROAD_AND_RIGHT_NARROW_Details(attribute + minName, attribute + maxName, details)));
		} else if (isLeftMinLessThanOrEqual(rightMin, leftMin)
				&& isLeftMaxHigherThanOrEqual(rightMax, leftMax, maxLowerZeroAllowed, maxStarAllowed)) {
			return new ComparisonResult(ComparisonResults.LEFT_NARROW_AND_RIGHT_BROAD, left, right,
					new SingleComparisonDocumentation(UIElements
							.LEFT_NARROW_AND_RIGHT_BROAD_Details(attribute + minName, attribute + maxName, details)));
		} else if (isLeftDisjoint(leftMax, rightMin, maxStarAllowed)) {
			return new ComparisonResult(ComparisonResults.LEFT_IS_LESS_DISJOINT, left, right,
					new SingleComparisonDocumentation(UIElements.LEFT_IS_LESS_DISJOINT_Details(attribute + minName,
							attribute + maxName, details)));
		} else if (isLeftDisjoint(rightMax, leftMin, maxStarAllowed)) {
			return new ComparisonResult(ComparisonResults.RIGHT_IS_LESS_DISJOINT, left, right,
					new SingleComparisonDocumentation(UIElements.RIGHT_IS_LESS_DISJOINT_Details(attribute + minName,
							attribute + maxName, details)));
		} else if (isLeftPrelimaryOverlapping(leftMin, leftMax, rightMin, rightMax, maxStarAllowed)) {
			return new ComparisonResult(ComparisonResults.LEFT_IS_PRELIMINARY_OVERLAPPING, left, right,
					new SingleComparisonDocumentation(
							UIElements.LEFT_IS_OVERLAPPING_Details(attribute + minName, attribute + maxName, details)));
		} else if (isLeftPrelimaryOverlapping(rightMin, rightMax, leftMin, leftMax, maxStarAllowed)) {
			return new ComparisonResult(ComparisonResults.RIGHT_IS_PRELIMINARY_OVERLAPPING, left, right,
					new SingleComparisonDocumentation(UIElements.RIGHT_IS_OVERLAPPING_Details(attribute + minName,
							attribute + maxName, details)));
		}
		return new ComparisonResult(ComparisonResults.UNEQUAL, left, right, new SingleComparisonDocumentation(
				UIElements.UNEQUAL_Details(attribute + minName, attribute + maxName, details)));
	}

	/**
	 * Returns whether one value is less than or equal an other value.
	 * 
	 * @param leftMin
	 *            The first value.
	 * @param rightMin
	 *            The second value.
	 * @return Whether the first value is less than or equal an second value.
	 */
	private boolean isLeftMinLessThanOrEqual(final int leftMin, final int rightMin) {
		return leftMin <= rightMin;
	}

	/**
	 * Returns whether an upper bound is a value that is less than all other values
	 * in the domain with a selected value as lowest.
	 * 
	 * @param leftMax
	 *            The upper bound.
	 * @param rightMin
	 *            The lowest value of the domain.
	 * @param maxStarAllowed
	 *            Whether the upper bound may be unlimited with value -1.
	 * @return Whether the upper bound is a value that is less than all other values
	 *         in the domain with the lowest value.
	 */
	private static boolean isLeftDisjoint(final int leftMax, final int rightMin, boolean maxStarAllowed) {
		if (maxStarAllowed && leftMax < -1) {
			throw new IllegalArgumentException();
		}
		return !(maxStarAllowed && leftMax == -1) && (leftMax < rightMin);
	}

	/**
	 * Returns whether to domains are left overlapping. The lower bound of the first
	 * domain must be less than the lower bound of the second domain. The upper
	 * bound if the first domain must be greater than or equal to the lower of the
	 * second domain and less than or equal to the upper bound of the second domain.
	 * 
	 * @param leftMin
	 *            The lower bound of the first domain.
	 * @param leftMax
	 *            The upper bound of the first domain.
	 * @param rightMin
	 *            The lower bound of the second domain.
	 * @param rightMax
	 *            The upper bound of the second domain.
	 * @param maxStarAllowed
	 *            Whether the upper bounds may be unlimited with value -1.
	 * @return Whether to domains are left overlapping.
	 */
	private static boolean isLeftPrelimaryOverlapping(final int leftMin, final int leftMax, final int rightMin,
			final int rightMax, boolean maxStarAllowed) {
		if (maxStarAllowed && (leftMax < -1 || rightMax < -1)) {
			throw new IllegalArgumentException();
		}
		return leftMin < rightMin && ((maxStarAllowed && ((leftMax == -1 && rightMax != -1)
				|| (leftMax != -1 && leftMax >= rightMin && (rightMax == -1 || leftMax <= rightMax))))
				|| (!maxStarAllowed && leftMax >= rightMin && leftMax <= rightMax));
	}

	/**
	 * Returns whether an upper bound is greater than or equal an other upper bound.
	 * 
	 * @param leftMax
	 *            The first upper bound.
	 * @param rightMax
	 *            The second upper bound
	 * @param maxLowerZeroAllowed
	 *            Whether the upper bound is allowed to be less than 0.
	 * @param maxStarAllowed
	 *            Whether the upper bounds must be 0 or greater an may be unlimited
	 *            with value -1.
	 * @return Whether the first upper bound is greater than or equal the second
	 *         upper bound.
	 */
	private boolean isLeftMaxHigherThanOrEqual(final int leftMax, final int rightMax, final boolean maxLowerZeroAllowed,
			final boolean maxStarAllowed) {
		if (maxLowerZeroAllowed && maxStarAllowed || maxStarAllowed && (leftMax < -1 || rightMax < -1)
				|| !maxLowerZeroAllowed && ((maxStarAllowed && (leftMax < -1 || rightMax < -1))
						|| (!maxStarAllowed && (leftMax < 0 || rightMax < 0)))) {
			throw new IllegalArgumentException();
		}
		return !maxLowerZeroAllowed && (leftMax == -1 || rightMax != -1 && leftMax >= rightMax)
				|| maxLowerZeroAllowed && leftMax >= rightMax;
	}

}
