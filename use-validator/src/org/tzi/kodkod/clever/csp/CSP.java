package org.tzi.kodkod.clever.csp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.tzi.kodkod.clever.csp.integer.IntegerVariable;

/**
 * Structure of a Constraint Satisfaction Problem (CSP). A CSP consists of
 * variables and constraints. Each variable has a domain. The constraints are
 * defined on the variables.
 * 
 * This structure only supports integer variables.
 * 
 * @author Jan Prien
 *
 */
public class CSP implements ICSP {

	/**
	 * The integer variables.
	 */
	private final Set<IntegerVariable> integerVariables = new HashSet<IntegerVariable>();

	/**
	 * The commented constraints.
	 */
	private final Set<CommentedConstraint> commentedConstraints = new HashSet<CommentedConstraint>();

	/**
	 * Whether the textual representations of all variables must be unique.
	 */
	private final boolean uniqueDerivedString;

	/**
	 * Constructs an object.
	 * 
	 * @param uniqueDerivedString
	 *            Whether the textual representations of all variables must be
	 *            unique.
	 */
	public CSP(boolean uniqueDerivedString) {
		this.uniqueDerivedString = uniqueDerivedString;
	}

	/**
	 * Returns all variables.
	 * 
	 * @return All variables.
	 */
	private Set<IVariable<?>> getVariables() {
		final Set<IVariable<?>> variables = new HashSet<>();
		variables.addAll(integerVariables);
		return variables;
	}

	/**
	 * Returns all constraints.
	 * 
	 * @return All constraints.
	 */
	public Set<IConstraint> getConstraints() {
		return CommentedConstraint.constraints(commentedConstraints);
	}

	/**
	 * Returns all the mappings of variables to other elements.
	 * 
	 * @return All the mappings of variables to other elements.
	 */
	public Set<IReferenceHolder<?>> getReferenceHolder() {
		final Set<IReferenceHolder<?>> referenceHolders = new HashSet<>();
		referenceHolders.addAll(integerVariables);
		return referenceHolders;
	}

	/**
	 * Returns the integer variable that is mapped to a selected element.
	 * 
	 * @param referenciator
	 *            The mapping the variable must also represent.
	 * @return The integer variable that is mapped to the selected element.
	 */
	public IntegerVariable getIntegerVariableThatReferencesSame(IReferenciator<?> referenciator) {
		for (IntegerVariable integerVariable : integerVariables) {
			IReferenciator<?> referenciator2 = integerVariable.getRefereciator();
			if (referenciator.referencesSame(referenciator2)) {
				return integerVariable;
			}
		}
		return null;
	}

	/**
	 * Returns all elements that are mapped with the variables.
	 */
	@Override
	public Set<?> getReferences() {
		final Set<IReferenceHolder<?>> referenceHolders = getReferenceHolder();
		final Set<Object> references = new HashSet<>();
		for (IReferenceHolder<?> abstractCSPReferenceHolder : referenceHolders) {
			references.add(abstractCSPReferenceHolder.getRefereciator().getReference());
		}
		return references;
	}

	/**
	 * Returns the domain of a variable that is mapped to a selected element.
	 * 
	 * @param referenciator
	 *            The mapping the variable must also represent.
	 * @return The domain of a variable that is mapped to the selected element.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <A extends AbstractDomain<?>> A getDomain(IReferenciator<?> referenciator) {
		final Set<IReferenceHolder<?>> referenceHolders = getReferenceHolder();
		for (IReferenceHolder<?> abstractCSPReferenceHolder : referenceHolders) {
			IReferenciator<?> referenciator2 = abstractCSPReferenceHolder.getRefereciator();
			if (referenciator.referencesSame(referenciator2)) {
				return (A) abstractCSPReferenceHolder.getDomain();
			}
		}
		return null;
	}

	/**
	 * Returns whether already a variable is mapped to a given element.
	 * 
	 * @param referenciator
	 *            The mapping a variable must have.
	 * @return Whether already a variable is mapped to a given element.
	 */
	private boolean referenceAlreadyHold(IReferenciator<?> referenciator) {
		final Set<IReferenceHolder<?>> referenceHolders = getReferenceHolder();

		for (IReferenceHolder<?> referenceHolder : referenceHolders) {
			if (referenceHolder.getRefereciator().referencesSame(referenciator)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns whether already an element, a variable is mapped to, has the same
	 * textual representation as a given element.
	 * 
	 * @param refereciator
	 *            The mapping that textual representation an element mapped to one
	 *            variable must have.
	 * @return Whether already an element, a variable is mapped to, has the same
	 *         textual representation as a given element.
	 */
	private boolean referenceDerivedStringUnique(IReferenciator<?> refereciator) {
		final Set<IReferenceHolder<?>> referenceHolders = getReferenceHolder();
		for (IReferenceHolder<?> referenceHolder : referenceHolders) {
			if (referenceHolder.getRefereciator().getDerivedString().equals(refereciator.getDerivedString())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds an integer variable.
	 * 
	 * @param refereciator
	 *            The element the variable should be mapped to.
	 * @param initialLowerBound
	 *            The lowest value in the integer domain of the variable.
	 * @param initialUpperBound
	 *            The highest value in the integer domain of the variable.
	 * @return The added variable.
	 * @throws ReferenceAlreadyHoldException
	 *             If the is already an element mapped by an variable that that is
	 *             the same as the element the variable should be mapped to.
	 * @throws ReferenceDerivedStringNotUniqueException
	 *             If there is already an element mapped by an variable that has the
	 *             same textual representation as the element the variable should be
	 *             mapped to.
	 */
	public IntegerVariable intVar(IReferenciator<?> refereciator, int initialLowerBound, int initialUpperBound)
			throws ReferenceAlreadyHoldException, ReferenceDerivedStringNotUniqueException {
		if (referenceAlreadyHold(refereciator)) {
			throw new ReferenceAlreadyHoldException();
		}
		if (uniqueDerivedString && !referenceDerivedStringUnique(refereciator)) {
			throw new ReferenceDerivedStringNotUniqueException();
		}
		IntegerVariable integerVariable = new IntegerVariable(refereciator, initialLowerBound, initialUpperBound);
		integerVariables.add(integerVariable);
		return integerVariable;
	}

	public boolean add(IConstraint iConstraint, String comment) {
		if (iConstraint == null) {
			return false;
		}
		final Set<IVariable<?>> variables = getVariables();
		final Set<IVariable<?>> constraintValues = iConstraint.getVariables();
		for (final IVariable<?> constraintValue : constraintValues) {
			if (!variables.contains(constraintValue)) {
				return false;
			}
		}
		return commentedConstraints.add(new CommentedConstraint(iConstraint, comment));
	}

	@Override
	public String toString() {
		return toString(false);
	}

	/**
	 * Constructs a textual representation.
	 * 
	 * @param readableNames
	 *            Whether variables should have derived names or simple numbers.
	 * @return A textual representation.
	 */
	public String toString(boolean readableNames) {
		Set<IVariable<?>> variables = getVariables();
		Map<IVariable<?>, String> variableOverwriteNames = null;
		if (readableNames) {
			variableOverwriteNames = new HashMap<IVariable<?>, String>();
			int vi = 0;
			for (IVariable<?> variable : variables) {
				if (variable.isConstant() && variable.getRefereciator().getDerivedString()
						.equals(variable.getDomain().getLowerBound().toString())) {
					variableOverwriteNames.put(variable, variable.getDomain().getLowerBound().toString());
				} else {
					vi++;
					variableOverwriteNames.put(variable, "v" + vi);
				}
			}
		}
		String tab = "  ";
		String representation = "";
		if (variables.size() > 0) {
			representation = representation + "Variables:\n";
			for (IVariable<?> variable : variables) {
				representation = representation + tab + variable.toRepresentation(variableOverwriteNames) + ": "
						+ variable.getDomain().toString()
						+ (readableNames ? " (" + variable.toRepresentation() + ")" : "") + "\n";
			}
		}
		if (commentedConstraints.size() > 0) {
			representation = representation + "Constraints:\n";
			for (final CommentedConstraint commentedConstraint : commentedConstraints) {
				representation = representation + tab + commentedConstraint.getComment(64) + " | "
						+ commentedConstraint.getIConstraint().toRepresentation(variableOverwriteNames) + "\n";
			}
		}
		return representation;
	}

	/**
	 * Lets the visitor visit each variable.
	 * 
	 * @param iVariableVisitor
	 *            The visitor.
	 */
	public void processVariables(IVariableVisitor iVariableVisitor) {
		for (IVariable<?> iVariable : getVariables()) {
			iVariable.processWith(iVariableVisitor);
		}
	}

	/**
	 * Lets the visitor visit each constraint.
	 * 
	 * @param iConstraintVisitor
	 *            The visitor.
	 */
	public void processConstraints(IConstraintVisitor iConstraintVisitor) {
		for (IConstraint iConstraint : getConstraints()) {
			iConstraint.processWith(iConstraintVisitor);
		}
	}

}
