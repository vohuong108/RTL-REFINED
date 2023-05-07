package org.tzi.kodkod.comparison;

import java.util.Set;

/**
 * Merges results of selected type with selected type of documentations.
 * 
 * @author Jan Prien
 *
 * @param <B>
 *            The type of documentations.
 * @param <A>
 *            The type of results.
 */
public abstract class AbstractComparisonResultMerger<B extends AbstractComparisonDocumentation, A extends AbstractComparisonResult<B>> {

	/**
	 * Merges results of selected type with selected type of documentations.
	 * 
	 * @param comparisonResults
	 *            The results to merge
	 * @param left
	 *            The object used as first operand of the binary merge.
	 * @param right
	 *            The object used as second operand of the binary merge.
	 * @return The merged result.
	 */
	public A mergeComparisonResults(final Set<A> comparisonResults, final Object left, final Object right) {
		if (comparisonResults == null || comparisonResults.size() == 0 || comparisonResults.contains(null)) {
			throw new IllegalArgumentException();
		}
		A result = null;
		for (A resultToMerge : comparisonResults) {
			if (resultToMerge == null) {
				throw new IllegalArgumentException();
			}
			if (result == null) {
				result = resultToMerge;
			} else {
				result = result.merge(resultToMerge);
			}
		}
		return result;
	}
}
