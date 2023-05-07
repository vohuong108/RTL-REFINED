package org.tzi.kodkod.clever.csp.integer;

import java.util.Map;

import org.tzi.kodkod.clever.csp.IReferenciator;
import org.tzi.kodkod.clever.csp.ITermVisitor;
import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.IVariableVisitor;

/**
 * Integer typed variable. The variables domain is of the same type.
 * 
 * @author Jan Prien
 *
 */
public final class IntegerVariable extends AbstractIntegerResultTerm implements IIntegerVariable {

	/**
	 * The initial lower bound of the variables domain.
	 */
	private final int initialLowerBound;

	/**
	 * The initial upper bound of the variables domain.
	 */
	private final int initialUpperBound;

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
	public IntegerVariable(IReferenciator<?> refereciator, int initialLowerBound, int initialUpperBound) {
		if (refereciator == null) {
			throw new IllegalArgumentException();
		}
		this.refereciator = refereciator;
		this.initialLowerBound = initialLowerBound;
		this.initialUpperBound = initialUpperBound;
	}

	public int getInitialLowerBound() {
		return initialLowerBound;
	}

	public int getInitialUpperBound() {
		return initialUpperBound;
	}

	@Override
	public String toRepresentation() {
		return getRefereciator().getDerivedString() + "_iv";
	}

	@Override
	public IntegerDomain getDomain() {
		return new IntegerDomain(getInitialLowerBound(), getInitialUpperBound());
	}

	@Override
	public IReferenciator<?> getRefereciator() {
		return refereciator;
	}

	@Override
	public void processWith(IVariableVisitor iVariableVisitor) {
		iVariableVisitor.visitIntegerVariable(this);
	}

	@Override
	public String toRepresentation(Map<IVariable<?>, String> variableOverwriteNames) {
		return variableOverwriteNames == null || !variableOverwriteNames.containsKey(this)
				|| variableOverwriteNames.get(this) == null ? toRepresentation() : variableOverwriteNames.get(this);
	}

	@Override
	public boolean isConstant() {
		return initialLowerBound == initialUpperBound;
	}

	@Override
	public void processWith(ITermVisitor iTermVisitor) {
		iTermVisitor.visitIntegerVariable(this);
	}

}