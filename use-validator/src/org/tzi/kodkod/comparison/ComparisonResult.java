package org.tzi.kodkod.comparison;

/**
 * A binary comparison result with textual documentation.
 * 
 * @author Jan Prien
 *
 */
public final class ComparisonResult extends AbstractComparisonResult<ComparisonDocumentation> {

	/**
	 * The result type.
	 */
	private final ComparisonResults type;

	/**
	 * Constructs an object.
	 * 
	 * @param left
	 *            The object used as first operand.
	 * @param right
	 *            The object used as second operand.
	 * @param documentation
	 *            The documentation.
	 * @param type
	 *            The result type.
	 */
	public ComparisonResult(final ComparisonResults type, final Object left, final Object right,
			final ComparisonDocumentation documentation) {
		super(left, right, documentation);
		if (type == null) {
			throw new IllegalArgumentException();
		}
		this.type = type;
	}

	@Override
	public String getDescriptor() {
		return this.type.getDescriptor();
	}

	/**
	 * Validates whether the type is the same as a selected one.
	 * 
	 * @param type
	 *            The other type.
	 * @return Whether the type is the same.
	 */
	public boolean isType(final ComparisonResults type) {
		return this.type == type;
	}

	protected ComparisonResults getType() {
		return this.type;
	}

	/**
	 * 
	 * @return The textual representaion of the result.
	 */
	public String getText() {
		return this.getDocumentation().getText();
	}

	@Override
	public boolean indicatesFamilyRelation() {
		return this.type.indicatesFamilyRelation();
	}

	@Override
	public <B extends AbstractComparisonResult<ComparisonDocumentation>> boolean isCounterpartOf(B result) {
		if (!this.getClass().equals(result.getClass())) {
			return false;
		}
		return this.isCounterpartOf((ComparisonResult) result);
	}

	/**
	 * Evaluates whether another binary comparison results validly represents the
	 * result of comparing with inverted operand.
	 * 
	 * @param result
	 *            The other binary comparison result.
	 * @return Whether the other result validly represents the result of comparing
	 *         with inverted operand.
	 */
	private boolean isCounterpartOf(ComparisonResult result) {
		if (result == null) {
			throw new IllegalArgumentException();
		}
		return ComparisonResults.isCounterpartOf(this.type, result.type);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <B extends AbstractComparisonResult<ComparisonDocumentation>> B merge(B resultToMerge) {
		if (!this.getClass().equals(resultToMerge.getClass())) {
			return null;
		}
		return (B) this.merge((ComparisonResult) resultToMerge);

	}

	/**
	 * Merges with another binary comparison result.
	 * 
	 * @param resultToMerge
	 *            The other result.
	 * @return The binary comparison result resulting in merging the results.
	 */
	private ComparisonResult merge(ComparisonResult resultToMerge) {
		if (resultToMerge == null) {
			throw new IllegalArgumentException();
		}
		return new ComparisonResult(ComparisonResults.combine(this.type, resultToMerge.type), this, resultToMerge,
				new MergedComparisonDocumentation(this, resultToMerge));

	}

}
