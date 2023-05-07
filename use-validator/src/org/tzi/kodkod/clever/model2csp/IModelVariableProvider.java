package org.tzi.kodkod.clever.model2csp;

import org.tzi.kodkod.clever.csp.CSP;
import org.tzi.kodkod.clever.csp.IReferenceHolder;
import org.tzi.kodkod.clever.csp.ReferenceAlreadyHoldException;
import org.tzi.kodkod.clever.csp.ReferenceDerivedStringNotUniqueException;
import org.tzi.kodkod.clever.csp.integer.IntegerVariable;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Manages variables for CSPs for the bounds tightening of UML/OCL models
 * instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
final class IModelVariableProvider {

	/**
	 * The default lower bound for integer variables in CSP. Used for creating
	 * integer variables.
	 */
	private final static int defaultIntVarInitialLowerBound = -100000;

	/**
	 * The default upper bound for integer variables in CSP. Used for creating
	 * integer variables.
	 */
	private final static int defaultIntVarInitialUpperBound = 100000;

	/**
	 * Constructs an object.
	 */
	private IModelVariableProvider() {

	}

	/**
	 * Returns the existing integer variable for an OCL expression. If there does
	 * not exists a variable for the expression in the CSP, then the variable is
	 * created and added and returned. A created variable has the
	 * {@link #defaultIntVarInitialLowerBound} and
	 * {@link #defaultIntVarInitialUpperBound} as initial bounds.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param expression
	 *            The OCL expression.
	 * @param identifierGenerator
	 *            The generator for identifiers. This is used if a new variable must
	 *            be generated.
	 * @param identifierPrefix
	 *            The prefix of the identifier of a created variable. This is used
	 *            if a new variable must be generated.
	 * @return The integer variable from the CSP for the expression.
	 * @throws ReferenceAlreadyHoldException
	 *             If a new variable has been created but could not be added because
	 *             there was already a variable for the expression.
	 * @throws ReferenceDerivedStringNotUniqueException
	 *             If a new variable has been created but could not be added because
	 *             there was already a variable in the CSP with the same textual
	 *             representation.
	 */
	protected static IntegerVariable getVariableOrCreate(CSP csp, Expression expression,
			IdentifierGenerator identifierGenerator, String identifierPrefix)
			throws ReferenceAlreadyHoldException, ReferenceDerivedStringNotUniqueException {
		if (csp == null || expression == null || identifierGenerator == null || identifierPrefix == null) {
			throw new IllegalArgumentException();
		}
		IntegerVariable integerVariable = csp
				.getIntegerVariableThatReferencesSame(new ExpressionReferenciator("test", expression));
		if (integerVariable == null) {
			integerVariable = csp.intVar(
					new ExpressionReferenciator(identifierPrefix + identifierGenerator.generate(), expression),
					defaultIntVarInitialLowerBound, defaultIntVarInitialUpperBound);
		}
		return integerVariable;
	}

	/**
	 * Returns the integer variable that represents a constant integer value. If
	 * there does not exist such a variable in the CSP, a new variable is created,
	 * added and returned.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param constant
	 *            The constant integer value.
	 * @return The integer variable from the CSP for the constant integer value.
	 */
	protected static IntegerVariable getConstantOrCreate(CSP csp, int constant) {
		IntegerReferenciator integerReferenciator = new IntegerReferenciator(constant);
		IntegerVariable integerVariable = csp.getIntegerVariableThatReferencesSame(integerReferenciator);
		if (integerVariable == null) {
			try {
				integerVariable = csp.intVar(integerReferenciator, constant, constant);
			} catch (ReferenceAlreadyHoldException | ReferenceDerivedStringNotUniqueException e) {
				throw new UnsupportedOperationException();
			}
		}
		return integerVariable;
	}

	/**
	 * Creates and adds an integer variable to a CSP. The integer variable is mapped
	 * to an OCL expression.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param expression
	 *            The OCL expression.
	 * @param lowerBound
	 *            The lower bound.
	 * @param upperBound
	 *            The upper bound.
	 * @param identifierGenerator
	 *            The generator for identifiers.
	 * @param identifierPrefix
	 *            The prefix of the identifier of the created variable.
	 * @return The integer variable that was created and added to the CSP.
	 * @throws ReferenceAlreadyHoldException
	 *             If a new variable has been created but could not be added because
	 *             there was already a variable for the expression.
	 * @throws ReferenceDerivedStringNotUniqueException
	 *             If a new variable has been created but could not be added because
	 *             there was already a variable in the CSP with the same textual
	 *             representation.
	 */
	protected static IntegerVariable createVariable(CSP csp, Expression expression, int lowerBound, int upperBound,
			IdentifierGenerator identifierGenerator, String identifierPrefix)
			throws ReferenceAlreadyHoldException, ReferenceDerivedStringNotUniqueException {
		return csp.intVar(new ExpressionReferenciator(identifierPrefix + identifierGenerator.generate(), expression),
				lowerBound, upperBound);
	}

	/**
	 * Creates and adds an integer variable to a CSP. The integer variable is mapped
	 * to an OCL expression.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param expression
	 *            The OCL expression.
	 * @param lowerAndUpperBound
	 *            The lower and upper bound.
	 * @param identifierGenerator
	 *            The generator for identifiers.
	 * @param identifierPrefix
	 *            The prefix of the identifier of the created variable.
	 * @return The integer variable that was created and added to the CSP.
	 * @throws ReferenceAlreadyHoldException
	 *             If a new variable has been created but could not be added because
	 *             there was already a variable for the expression.
	 * @throws ReferenceDerivedStringNotUniqueException
	 *             If a new variable has been created but could not be added because
	 *             there was already a variable in the CSP with the same textual
	 *             representation.
	 */
	protected static IntegerVariable createVariable(CSP csp, Expression expression, int lowerAndUpperBound,
			IdentifierGenerator identifierGenerator, String identifierPrefix)
			throws ReferenceAlreadyHoldException, ReferenceDerivedStringNotUniqueException {
		return createVariable(csp, expression, lowerAndUpperBound, lowerAndUpperBound, identifierGenerator,
				identifierPrefix);
	}

	/**
	 * Returns the integer variable from the CSP for the class element or
	 * <code>null</code> if no such variable exits.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param iClass
	 *            The class element.
	 * @return The integer variable from the CSP for the class element or
	 *         <code>null</code> if no such variable exits.
	 */
	protected static IntegerVariable getClassVariable(CSP csp, IClass iClass) {
		for (IReferenceHolder<?> referenceHolder : csp.getReferenceHolder()) {
			if (referenceHolder instanceof IntegerVariable
					&& referenceHolder.getRefereciator() instanceof IClassReferenciator
					&& referenceHolder.getRefereciator().getReference() instanceof IClass
					&& referenceHolder.getRefereciator().getReference() == iClass) {
				return (IntegerVariable) referenceHolder;
			}
		}
		return null;
	}

	/**
	 * Returns the integer variable from the CSP for the attribute element or
	 * <code>null</code> if no such variable exits.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param iClass
	 *            The attributes owning class element.
	 * @param iAttribute
	 *            The attribute element.
	 * @return The integer variable from the CSP for the attribute element or
	 *         <code>null</code> if no such variable exits.
	 */
	protected static IntegerVariable getAttributeForClassVariable(CSP csp, IClass iClass, IAttribute iAttribute) {
		for (IReferenceHolder<?> referenceHolder : csp.getReferenceHolder()) {
			if (referenceHolder instanceof IntegerVariable
					&& referenceHolder.getRefereciator() instanceof IAttributeForClassReferenciator
					&& referenceHolder.getRefereciator().getReference() instanceof IAttributeForClassReference
					&& ((IAttributeForClassReference) referenceHolder.getRefereciator().getReference())
							.getIClass() == iClass
					&& ((IAttributeForClassReference) referenceHolder.getRefereciator().getReference())
							.getIAttribute() == iAttribute) {
				return (IntegerVariable) referenceHolder;
			}
		}
		return null;
	}

	/**
	 * Returns the integer variable from the CSP for the association element or
	 * <code>null</code> if no such variable exits.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param iAssociation
	 *            The association element.
	 * @return The integer variable from the CSP for the association element or
	 *         <code>null</code> if no such variable exits.
	 */
	protected static IntegerVariable getAssociationVariable(CSP csp, IAssociation iAssociation) {
		for (IReferenceHolder<?> referenceHolder : csp.getReferenceHolder()) {
			if (referenceHolder instanceof IntegerVariable
					&& referenceHolder.getRefereciator() instanceof IAssociationReferenciator
					&& referenceHolder.getRefereciator().getReference() instanceof IAssociation
					&& referenceHolder.getRefereciator().getReference() == iAssociation) {
				return (IntegerVariable) referenceHolder;
			}
		}
		return null;
	}

}
