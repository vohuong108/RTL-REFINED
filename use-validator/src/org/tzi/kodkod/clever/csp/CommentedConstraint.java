package org.tzi.kodkod.clever.csp;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * Wrapper for constraints. Supports specifying a description for an
 * {@link IConstraint}
 * 
 * @author Jan Prien
 *
 */
final class CommentedConstraint {

	/**
	 * The constraint to be commented.
	 */
	private final IConstraint iConstraint;

	/**
	 * The comment to the constraint.
	 */
	private final String comment;

	/**
	 * Constructs an object.
	 * 
	 * @param iConstraint
	 *            The constraint to be commented.
	 * @param comment
	 *            The comment to the constraint.
	 */
	public CommentedConstraint(IConstraint iConstraint, String comment) {
		if (iConstraint == null) {
			throw new IllegalArgumentException();
		}
		this.iConstraint = iConstraint;
		this.comment = comment;
	}

	public IConstraint getIConstraint() {
		return iConstraint;
	}

	public String getComment() {
		return comment;
	}

	/**
	 * Gets the comment in a specific textual length. Cuts the overflowing text if
	 * it is too long. Fills the text with spaces if it is too short.
	 * 
	 * @param length
	 *            The length the comment must be.
	 * @return The comment, cutted or extended to the length.
	 */
	public String getComment(int length) {
		if (length < 0) {
			throw new IllegalArgumentException();
		}
		String comment = this.comment == null ? "" : StringUtils.abbreviate(this.comment, length);
		while (comment.length() < length) {
			comment += " ";
		}
		return comment;
	}

	/**
	 * Returns the constraints without comments.
	 * 
	 * @param commentedConstraints
	 *            The commented constraints.
	 * @return The constrains without comments.
	 */
	public static Set<IConstraint> constraints(Set<CommentedConstraint> commentedConstraints) {
		Set<IConstraint> iConstraints = new HashSet<>();
		for (CommentedConstraint commentedConstraint : commentedConstraints) {
			iConstraints.add(commentedConstraint.getIConstraint());
		}
		return iConstraints;
	}
}
