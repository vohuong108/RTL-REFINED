package org.tzi.kodkod.clever.csp.real;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IReferenciator;
import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.IVariableVisitor;

/**
 * Real typed variable. The variables domain is of the same type.
 * 
 * @author Jan Prien
 *
 */
public final class RealVariable implements IRealVariable {

	/**
	 * The initial lower bound of the variables domain.
	 */
	private final double initialLowerBound;

	/**
	 * The initial upper bound of the variables domain.
	 */
	private final double initialUpperBound;

	/**
	 * The maximum precision of the variables domain values.
	 */
	private final double precision;

	/**
	 * The mapping to the element for that the variable is.
	 */
	private final IReferenciator<?> refereciator;

	/**
	 * Constructs an object.
	 * 
	 * @param refereciator
	 *            The mapping to the element for that the variable is.
	 * @param initialLowerBound
	 *            The initial lower bound of the variables domain.
	 * @param initialUpperBound
	 *            The initial upper bound of the variables domain.
	 * @param precision
	 *            The maximum precision of the variables domain values.
	 */
	public RealVariable(IReferenciator<?> refereciator, double initialLowerBound, double initialUpperBound,
			double precision) {
		if (refereciator == null) {
			throw new IllegalArgumentException();
		}
		this.refereciator = refereciator;
		this.initialLowerBound = initialLowerBound;
		this.initialUpperBound = initialUpperBound;
		this.precision = precision;
	}

	public double getInitialLowerBound() {
		return initialLowerBound;
	}

	public double getInitialUpperBound() {
		return initialUpperBound;
	}

	@Override
	public String toRepresentation() {
		return getRefereciator().getDerivedString() + "_rv";
	}

	@Override
	public RealDomain getDomain() {
		return new RealDomain(getInitialLowerBound(), getInitialUpperBound(), precision);
	}

	@Override
	public IReferenciator<?> getRefereciator() {
		return refereciator;
	}

	@Override
	public void processWith(IVariableVisitor iVariableVisitor) {
		iVariableVisitor.visitRealVariable(this);
	}

	@Override
	public String toRepresentation(Map<IVariable<?>, String> variableOverwriteNames) {
		return variableOverwriteNames == null || !variableOverwriteNames.containsKey(this)
				|| variableOverwriteNames.get(this) == null ? toRepresentation() : variableOverwriteNames.get(this);
	}

	@Override
	public boolean isConstant() {
		return getInitialLowerBound() == getInitialUpperBound();
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitRealVariable(this);
	}

}
