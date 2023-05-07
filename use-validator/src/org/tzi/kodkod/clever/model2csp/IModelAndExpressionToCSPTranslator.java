package org.tzi.kodkod.clever.model2csp;

import java.util.Collection;
import java.util.List;

import org.tzi.kodkod.clever.csp.CSP;
import org.tzi.kodkod.clever.csp.ReferenceAlreadyHoldException;
import org.tzi.kodkod.clever.csp.ReferenceDerivedStringNotUniqueException;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerEqualIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerGreaterThanEqualIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerLessThanEqualIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.AbstractIntegerResultTerm;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForIntegerAdditionIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForIntegerMultiplicationIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForMaxIntegerIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerVariable;
import org.tzi.kodkod.clever.csp2choco.IConstraintToChocoCSPTranslationVisitor;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAssociationEnd;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.impl.Multiplicity;
import org.tzi.kodkod.model.impl.Range;
import org.tzi.use.uml.ocl.expr.Expression;
import org.tzi.use.uml.ocl.expr.ExpressionVisitor;

/**
 * Constructs CSPs for the bounds tightening of UML/OCL models instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
public final class IModelAndExpressionToCSPTranslator {

	/**
	 * Constructs an object.
	 */
	public IModelAndExpressionToCSPTranslator() {

	}

	/**
	 * Constructs a CSP for the bounds tightening of UML/OCL models instance finder
	 * configurations.
	 * 
	 * @param initialBoundsSpecification
	 *            The initial bounds for the CSP.
	 * @param invariants
	 *            The invariants of the UML/OCL model.
	 * @param repsectOclConstraints
	 *            Whether CSP constraints should be derived from the invariant.
	 * @return A CSP for the bounds tightening of UML/OCL models instance finder
	 *         configurations.
	 */
	public CSP translate(final IModelCSPVariablesInitialBoundsSpecification initialBoundsSpecification,
			List<Expression> invariants, boolean repsectOclConstraints) {
		if (initialBoundsSpecification == null) {
			throw new IllegalArgumentException();
		}
		CSP csp = new CSP(true);
		enrichByVariablesForModel(csp, initialBoundsSpecification);
		IModel model = initialBoundsSpecification.getModel();
		enrichByConstraintsForGenreralization(csp, model);
		enrichByConstraintsForAttributes(csp, model);
		enrichByConstraintsForAssociations(csp, model);
		if (repsectOclConstraints) {
			enrichByContraintsForContraints(csp, model, invariants);
		}
		return csp;
	}

	/**
	 * Enriches a CSP for the bounds tightening of UML/OCL models instance finder
	 * configurations with constraints regarding the invariants.
	 * 
	 * Calls
	 * {@link IModelAndExpressionToCSPTranslator#enrichByContraintsForContraints(CSP, Expression, IModel, IdentifierGenerator)}
	 * for each invariant.
	 * 
	 * @param csp
	 *            The CSP for the bounds tightening of UML/OCL models instance
	 *            finder configurations.
	 * @param model
	 *            The UML/OCL model.
	 * @param invariants
	 *            The models invariants.
	 */
	private void enrichByContraintsForContraints(CSP csp, IModel model, List<Expression> invariants) {
		if (csp == null || model == null) {
			throw new IllegalArgumentException();
		}
		if (invariants != null) {
			IdentifierGenerator identifierGenerator = new IdentifierGenerator();
			for (Expression invariant : invariants) {
				enrichByContraintsForContraints(csp, invariant, model, identifierGenerator);
			}
		}
	}

	/**
	 * Enriches the given CSP by constraints for an OCL expression.
	 * <p>
	 * Does nothing if {@code invariant} is {@code null}
	 * <p>
	 * This method currently throws an {@link UnsupportedOperationException},
	 * because the functionality is not yet implemented.
	 * <p>
	 * Use something like
	 * {@code invariant.processWithVisitor(new ExpressionToCSPTranslationVisitor(csp, new MModelToIModelTranslator(model), identifierGenerator));}
	 * to translate OCL constraints to CSP constraints.
	 * {@code ExpressionToCSPTranslationVisitor} should extend
	 * {@link ExpressionVisitor}. Every newly used term type (e.g.
	 * {@link IntegerResultForMaxIntegerIntegerTerm} should be also handled in
	 * {@link IConstraintToChocoCSPTranslationVisitor}.
	 * 
	 * @param csp
	 *            CSP to enrich.
	 * @param invariant
	 *            OCL expression to translate.
	 * @param model
	 *            Model on which the OCL expression is defined.
	 * @param identifierGenerator
	 *            Generator for identifiers.
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code csp} or {@code identifierGenerator} is {@code null}.
	 * @throws IllegalArgumentException
	 *             if arguments are valid (see explenation).
	 */
	private void enrichByContraintsForContraints(CSP csp, Expression invariant, IModel model,
			IdentifierGenerator identifierGenerator) {
		if (invariant == null) {
			return;
		}
		if (csp == null || identifierGenerator == null) {
			throw new IllegalArgumentException();
		}
		// TODO Implement translation of OCL expressions to CSP constraints.
		throw new UnsupportedOperationException(
				"Translation of OCL expressions to CSP constraints is not yet implemented.");
	}

	/**
	 * Enriches a CSP for the bounds tightening of UML/OCL models instance finder
	 * configurations with variables for all model elements. A variable is added for
	 * each class, each attribute of each class (also inherited attributes) and each
	 * association. The initial variable domains are taken from the initial bounds
	 * specification.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param initialBoundsSpecification
	 *            The initial bounds specification.
	 */
	private static void enrichByVariablesForModel(final CSP csp,
			final IModelCSPVariablesInitialBoundsSpecification initialBoundsSpecification) {
		if (csp == null || initialBoundsSpecification == null) {
			throw new IllegalArgumentException();
		}
		IModel model = initialBoundsSpecification.getModel();
		for (IClass iClass : model.classes()) {
			try {
				csp.intVar(new IClassReferenciator(iClass),
						initialBoundsSpecification.getSpecificClassLowerBound(iClass),
						initialBoundsSpecification.getSpecificClassUpperBound(iClass));
			} catch (ReferenceAlreadyHoldException | ReferenceDerivedStringNotUniqueException e) {
				throw new UnsupportedOperationException("Variable for class could not be added to CSP.", e);
			}
			for (IAttribute iAttribute : iClass.allAttributes()) {
				try {
					csp.intVar(new IAttributeForClassReferenciator(new IAttributeForClassReference(iClass, iAttribute)),
							initialBoundsSpecification.getSpecificAttributeLowerBound(iClass, iAttribute),
							initialBoundsSpecification.getSpecificAttributeUpperBound(iClass, iAttribute));
				} catch (ReferenceAlreadyHoldException | ReferenceDerivedStringNotUniqueException e) {
					throw new UnsupportedOperationException("Variable for attribute could not be added to CSP.", e);
				}
			}
		}
		for (IAssociation iAssociation : model.associations()) {
			try {
				csp.intVar(new IAssociationReferenciator(iAssociation),
						initialBoundsSpecification.getSpecificAssociationLowerBound(iAssociation),
						initialBoundsSpecification.getSpecificAssociationUpperBound(iAssociation));
			} catch (ReferenceAlreadyHoldException | ReferenceDerivedStringNotUniqueException e) {
				throw new UnsupportedOperationException("Variable for association could not be added to CSP.", e);
			}
		}
	}

	/**
	 * Enriches a CSP for the bounds tightening of UML/OCL models instance finder
	 * configurations with constraints regarding the generalizations.
	 * 
	 * For each class from that are classes derived, a constraint is added. The
	 * class variable must be equal to the sum of the sub classes variables.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param model
	 *            The UML/OCL model.
	 */
	private static void enrichByConstraintsForGenreralization(final CSP csp, final IModel model) {
		if (csp == null || model == null) {
			throw new IllegalArgumentException();
		}
		for (IClass iClass : model.classes()) {
			final Collection<IClass> children = iClass.children();
			if (children.size() > 0) {
				AbstractIntegerResultTerm right = null;
				for (IClass childIClass : children) {
					if (right == null) {
						right = IModelVariableProvider.getClassVariable(csp, childIClass);
					} else {
						right = new IntegerResultForIntegerAdditionIntegerTerm(right,
								IModelVariableProvider.getClassVariable(csp, childIClass));
					}
				}
				csp.add(new BooleanResultForIntegerEqualIntegerTerm(
						IModelVariableProvider.getClassVariable(csp, iClass), right), "Generalization");
			}
		}
	}

	/**
	 * Enriches a CSP for the bounds tightening of UML/OCL models instance finder
	 * configurations with constraints regarding the attributes.
	 * 
	 * For each class for all attributes (also inherited attributes) a constraint is
	 * added. The class variable must be greater than or equal the attribute
	 * variable.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param model
	 *            The UML/OCL model.
	 */
	private void enrichByConstraintsForAttributes(CSP csp, IModel model) {
		if (csp == null || model == null) {
			throw new IllegalArgumentException();
		}
		for (IClass iClass : model.classes()) {
			IntegerVariable iClassIntegerVariable = IModelVariableProvider.getClassVariable(csp, iClass);
			for (IAttribute iAttribute : iClass.allAttributes()) {
				csp.add(new BooleanResultForIntegerGreaterThanEqualIntegerTerm(iClassIntegerVariable,
						IModelVariableProvider.getAttributeForClassVariable(csp, iClass, iAttribute)), "Attribute");
			}
		}
	}

	/**
	 * Enriches a CSP for the bounds tightening of UML/OCL models instance finder
	 * configurations with constraints regarding the associations.
	 * 
	 * Calls
	 * {@link IModelAndExpressionToCSPTranslator#enrichByConstraintsForAssociation(CSP, IAssociation)}
	 * for each association.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param model
	 *            The UML/OCL model.
	 */
	private void enrichByConstraintsForAssociations(CSP csp, IModel model) {
		if (csp == null || model == null) {
			throw new IllegalArgumentException();
		}
		for (IAssociation iAssociation : model.associations()) {
			enrichByConstraintsForAssociation(csp, iAssociation);
		}
	}

	/**
	 * Enriches a CSP for the bounds tightening of UML/OCL models instance finder
	 * configurations with constraints regarding an association.
	 * 
	 * Constraints are added only for binary associations.
	 * 
	 * For binary associations: For the association the multiplicity lower and upper
	 * bound is computed. For each association end the other associations variable
	 * must be greater than or equal the association ends lower bound times the
	 * other ends class variable. If the association ends upper bound is not
	 * {@link Multiplicity#MANY}, then: For each association end the other
	 * associations variable must be less than or equal the association ends upper
	 * bound times the other ends class variable.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param iAssociation
	 *            The UML/OCL model.
	 */
	private void enrichByConstraintsForAssociation(CSP csp, IAssociation iAssociation) {
		if (csp == null || iAssociation == null) {
			throw new IllegalArgumentException();
		}
		final IntegerVariable iAssociationVariable = IModelVariableProvider.getAssociationVariable(csp, iAssociation);
		final String iAssociationName = iAssociation.name();
		if (iAssociation.associationEnds().size() == 2) {
			for (IAssociationEnd iAssociationEnd : iAssociation.associationEnds()) {
				final Multiplicity m = iAssociationEnd.multiplicity();
				final String iAssociationEndName = iAssociationEnd.name();
				final List<Range> ranges = m.getRanges();
				Integer lowest = null;
				Integer highest = null;
				boolean isMany = false;
				for (Range range : ranges) {
					final int lower = range.getLower();
					if (lowest == null) {
						lowest = lower;
					} else {
						if (lowest > lower) {
							lowest = lower;
						}
					}
					final int upper = range.getUpper();
					if (highest == null) {
						if (upper == Multiplicity.MANY) {
							isMany = true;
						} else {
							highest = upper;
						}
					} else {
						if (upper == Multiplicity.MANY) {
							isMany = true;
						} else {
							if (highest < upper) {
								highest = upper;
							}
						}
					}
				}
				final String comment = "End " + iAssociationEndName + " of binary association " + iAssociationName
						+ " with multiplicity " + m.toString();
				if (lowest == highest) {
					for (IAssociationEnd otherIAssociationEnd : iAssociation.associationEnds()) {
						if (otherIAssociationEnd != iAssociationEnd) {
							final IntegerVariable otherAssociatedClassVariable = IModelVariableProvider
									.getClassVariable(csp, otherIAssociationEnd.associatedClass());
							csp.add(new BooleanResultForIntegerEqualIntegerTerm(iAssociationVariable,
									new IntegerResultForIntegerMultiplicationIntegerTerm(
											IModelVariableProvider.getConstantOrCreate(csp, lowest),
											otherAssociatedClassVariable)),
									comment);
						}
					}
				} else {
					for (IAssociationEnd otherIAssociationEnd : iAssociation.associationEnds()) {
						if (otherIAssociationEnd != iAssociationEnd) {
							final IntegerVariable otherAssociatedClassVariable = IModelVariableProvider
									.getClassVariable(csp, otherIAssociationEnd.associatedClass());
							csp.add(new BooleanResultForIntegerGreaterThanEqualIntegerTerm(iAssociationVariable,
									new IntegerResultForIntegerMultiplicationIntegerTerm(
											IModelVariableProvider.getConstantOrCreate(csp, lowest),
											otherAssociatedClassVariable)),
									comment);
						}
					}
					if (!isMany && iAssociation.associationEnds().size() > 1) {
						IIntegerResultTerm iIntegerResultTerm = null;
						for (IAssociationEnd otherIAssociationEnd : iAssociation.associationEnds()) {
							if (otherIAssociationEnd != iAssociationEnd) {
								final IntegerVariable otherAssociatedClassVariable = IModelVariableProvider
										.getClassVariable(csp, otherIAssociationEnd.associatedClass());
								IntegerResultForIntegerMultiplicationIntegerTerm integerResultForIntegerMultiplicationIntegerTerm = new IntegerResultForIntegerMultiplicationIntegerTerm(
										IModelVariableProvider.getConstantOrCreate(csp, highest),
										otherAssociatedClassVariable);
								if (iIntegerResultTerm == null) {
									iIntegerResultTerm = integerResultForIntegerMultiplicationIntegerTerm;
								} else {
									iIntegerResultTerm = new IntegerResultForIntegerAdditionIntegerTerm(
											iIntegerResultTerm, integerResultForIntegerMultiplicationIntegerTerm);
								}
							}
						}
						csp.add(new BooleanResultForIntegerLessThanEqualIntegerTerm(iAssociationVariable,
								iIntegerResultTerm), comment);
					}
				}
			}
		} else {
			// TODO implement concept for involving n-ary associations in CSP constraint
			// generation.
		}
	}

}
