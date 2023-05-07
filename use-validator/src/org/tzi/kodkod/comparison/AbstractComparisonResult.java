package org.tzi.kodkod.comparison;

/**
 * A binary comparison result with textual documentation.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractComparisonResult<A extends AbstractComparisonDocumentation> {

	/**
	 * The documentation.
	 */
	private final A documentation;

	/**
	 * The object used as first operand.
	 */
	private final Object left;

	/**
	 * The object used as second operand.
	 */
	private final Object right;

	/**
	 * Constructs an object.
	 * 
	 * @param left
	 *            The object used as first operand.
	 * @param right
	 *            The object used as second operand.
	 * @param documentation
	 *            The documentation.
	 */
	public AbstractComparisonResult(final Object left, final Object right, final A documentation) {
		if (left == null || right == null || documentation == null) {
			throw new IllegalArgumentException();
		}
		this.left = left;
		this.right = right;
		this.documentation = documentation;
	}

	/**
	 * 
	 * @return The symbol representing the type if the result.
	 */
	public abstract String getDescriptor();

	/**
	 * Evaluates whether another binary comparison results validly represents the
	 * result of comparing with inverted operand.
	 * 
	 * @param result
	 *            The other binary comparison result.
	 * @return Whether the other result validly represents the result of comparing
	 *         with inverted operand.
	 */
	public abstract <B extends AbstractComparisonResult<A>> boolean isCounterpartOf(B result);

	/**
	 * Merges with another binary comparison result.
	 * 
	 * @param resultToMerge
	 *            The other result.
	 * @return The binary comparison result resulting in merging the results.
	 */
	protected abstract <B extends AbstractComparisonResult<A>> B merge(B resultToMerge);

	/**
	 * 
	 * @return Whether the results represents an indication that the two operand are
	 *         related in terms of the search space they represent.
	 */
	public abstract boolean indicatesFamilyRelation();

	protected A getDocumentation() {
		return documentation;
	}

	public Object getLeft() {
		return left;
	}

	public Object getRight() {
		return right;
	}

}
