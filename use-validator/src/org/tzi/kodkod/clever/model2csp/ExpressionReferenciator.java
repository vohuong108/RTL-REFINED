package org.tzi.kodkod.clever.model2csp;

import org.tzi.kodkod.clever.csp.IReferenciator;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Wrapper for an OCL epression element ({@link IClass}). CSP variables must be
 * linked a model element from the UML/OCL model.
 * 
 * @author Jan Prien
 *
 */
final class ExpressionReferenciator implements IReferenciator<Expression> {

	/**
	 * The identifier for the wrapped expression element.
	 */
	private final String identifier;

	/**
	 * The wrapped class element.
	 */
	private final Expression reference;

	/**
	 * Constructs an object.
	 * 
	 * @param identifier
	 *            The identifier for the wrapped expression element.
	 * @param reference
	 *            The wrapped class element.
	 */
	public ExpressionReferenciator(String identifier, Expression reference) {
		if (identifier == null || reference == null) {
			throw new IllegalArgumentException();
		}
		this.identifier = identifier;
		this.reference = reference;
	}

	@Override
	public Expression getReference() {
		return reference;
	}

	@Override
	public String getDerivedString() {
		return identifier + "(" + reference.toString() + ")";
	}

	@Override
	public boolean referencesSame(IReferenciator<?> referenciator) {
		return referenciator != null && referenciator instanceof ExpressionReferenciator
				&& reference == ((ExpressionReferenciator) referenciator).reference;
	}

}
