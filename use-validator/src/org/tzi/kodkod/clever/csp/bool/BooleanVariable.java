package org.tzi.kodkod.clever.csp.bool;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IReferenciator;
import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.IVariableVisitor;

/**
 * Boolean typed variable. The variables domain is of the same type.
 * 
 * @author Jan Prien
 *
 */
public final class BooleanVariable extends AbstractBooleanResultTerm implements IBooleanVariable {

	/**
	 * The initial lower bound of the variables domain.
	 */
	private final boolean initialLowerBound;

	/**
	 * The initial upper bound of the variables domain.
	 */
	private final boolean initialUpperBound;

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
	 */
	public BooleanVariable(IReferenciator<?> refereciator, boolean initialLowerBound, boolean initialUpperBound) {
		if (refereciator == null) {
			throw new IllegalArgumentException();
		}
		this.refereciator = refereciator;
		this.initialLowerBound = initialLowerBound;
		this.initialUpperBound = initialUpperBound;
	}

	public boolean getInitialLowerBound() {
		return initialLowerBound;
	}

	public boolean getInitialUpperBound() {
		return initialUpperBound;
	}

	@Override
	public void processWith(IVariableVisitor iVariableVisitor) {
		iVariableVisitor.visitBooleanVariable(this);
	}

	@Override
	public boolean isConstant() {
		return getInitialLowerBound() == getInitialUpperBound();
	}

	@Override
	public IReferenciator<?> getRefereciator() {
		return refereciator;
	}

	@Override
	public BooleanDomain getDomain() {
		return new BooleanDomain(getInitialLowerBound(), getInitialUpperBound());
	}

	@Override
	public String toRepresentation(Map<IVariable<?>, String> variableOverwriteNames) {
		return getRefereciator().getDerivedString() + "_bv";
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitBooleanVariable(this);
	}

}
