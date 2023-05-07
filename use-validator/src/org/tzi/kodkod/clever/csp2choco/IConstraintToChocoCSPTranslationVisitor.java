package org.tzi.kodkod.clever.csp2choco;

import java.util.HashMap;
import java.util.Map;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.tzi.kodkod.clever.csp.IConstraint;
import org.tzi.kodkod.clever.csp.IConstraintVisitor;
import org.tzi.kodkod.clever.csp.bool.AbstractBooleanResultForBinaryIntegerArgumentTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForBooleanAndBooleanTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForBooleanImpliesBooleanTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForBooleanOrBooleanTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForBooleanXOrBooleanTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerEqualIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerEqualRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerGreaterThanEqualIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerGreaterThanEqualRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerGreaterThanIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerGreaterThanRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerLessThanEqualIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerLessThanEqualRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerLessThanIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerLessThanRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerUnequalIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForIntegerUnequalRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForNegateBooleanTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealEqualRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealGreaterThanEqualIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealGreaterThanEqualRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealGreaterThanIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealGreaterThanRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealLessThanEqualIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealLessThanEqualRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealLessThanIntegerTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealLessThanRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanResultForRealUnequalRealTerm;
import org.tzi.kodkod.clever.csp.bool.BooleanVariable;
import org.tzi.kodkod.clever.csp.bool.IBooleanResultTerm;
import org.tzi.kodkod.clever.csp.choco.ChocoCSP;
import org.tzi.kodkod.clever.csp.integer.AbstractIntegerResultForBinaryIntegerArgumentTerm;
import org.tzi.kodkod.clever.csp.integer.IIntegerResultTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForAbsoluteIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForCeilRealTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForFloorRealTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForIntegerAdditionIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForIntegerDivisionIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForIntegerExponentiationIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForIntegerModuloIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForIntegerMultiplicationIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForIntegerSubtractionIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForMaxIntegerIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForMinIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForNegateIntegerTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerResultForRoundRealTerm;
import org.tzi.kodkod.clever.csp.integer.IntegerVariable;
import org.tzi.kodkod.clever.csp.real.RealResultForCeilRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForFloorRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForIntegerLogarithmIntegerTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForIntegerLogarithmRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForMaxRealIntegerTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForMaxRealRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForMinRealIntegerTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForMinRealRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForNegateRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealAdditionIntegerTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealAdditionRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealDivisionIntegerTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealDivisionRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealLogarithmIntegerTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealLogarithmRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealMultiplicationIntegerTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealMultiplicationRealTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealSubtractionIntegerTerm;
import org.tzi.kodkod.clever.csp.real.RealResultForRealSubtractionRealTerm;
import org.tzi.kodkod.clever.csp.real.RealVariable;

/**
 * Visitor for constraints ({@link IConstraint}), that translate visited
 * constraints to a choco CSP. The visitor implements a specific handling for
 * selected types of constraint parts.
 * 
 * @author Jan Prien
 *
 */
public final class IConstraintToChocoCSPTranslationVisitor implements IConstraintVisitor {

	/**
	 * The choco CSP structure which gets enriched on visiting constraints.
	 */
	private final ChocoCSP chocoCsp;

	/**
	 * The already handled parts of constraints, for that already integer variables
	 * are generated.
	 */
	private Map<IIntegerResultTerm, IntVar> intVarsForIIntegerResultTerms;

	/**
	 * The already handled parts of constraints, for that already boolean variables
	 * are generated.
	 */
	private Map<IBooleanResultTerm, BoolVar> boolVarsForIBooleanResultTerms;

	/**
	 * Constructs an object.
	 * 
	 * @param chocoCsp
	 *            The choco CSP structure which gets enriched on visiting
	 *            constraints.
	 */
	IConstraintToChocoCSPTranslationVisitor(ChocoCSP chocoCsp) {
		if (chocoCsp == null) {
			throw new IllegalArgumentException();
		}
		this.chocoCsp = chocoCsp;
		reset();
	}

	/**
	 * Removes all generated variables for already visits constraints.
	 */
	private void reset() {
		intVarsForIIntegerResultTerms = new HashMap<IIntegerResultTerm, IntVar>();
		boolVarsForIBooleanResultTerms = new HashMap<IBooleanResultTerm, BoolVar>();
	}

	/**
	 * Handles and integer result term for that already an integer variable must
	 * have been created.
	 * 
	 * @param iIntegerResultTerm
	 *            The term.
	 * @return The already created integer variable for the integer result term.
	 */
	private IntVar visitIIntegerResultTerm(IIntegerResultTerm iIntegerResultTerm) {
		if (iIntegerResultTerm == null) {
			throw new IllegalArgumentException();
		}
		iIntegerResultTerm.processWith(this);
		if (!intVarsForIIntegerResultTerms.containsKey(iIntegerResultTerm)) {
			throw new UnsupportedOperationException(); // TOOD
		}
		return intVarsForIIntegerResultTerms.get(iIntegerResultTerm);
	}

	/**
	 * Handles and boolean result term for that already an boolean variable must
	 * have been created.
	 * 
	 * @param iBooleanResultTerm
	 *            The term.
	 * @return The already created variable for the term.
	 */
	private BoolVar visitIBooleanResultTerm(IBooleanResultTerm iBooleanResultTerm) {
		iBooleanResultTerm.processWith(this);
		if (!boolVarsForIBooleanResultTerms.containsKey(iBooleanResultTerm)) {
			throw new UnsupportedOperationException(); // TOOD
		}
		return boolVarsForIBooleanResultTerms.get(iBooleanResultTerm);
	}

	/**
	 * Returns the variable generated for a term. If there was not already a
	 * variable generated for the term, a new variable is generated.
	 * 
	 * @param iBooleanResultTerm
	 *            The term.
	 * @return The variable generated for a term.
	 */
	private BoolVar getOrCreateBoolVar(IBooleanResultTerm iBooleanResultTerm) {
		if (iBooleanResultTerm == null) {
			throw new IllegalArgumentException();
		}
		if (!boolVarsForIBooleanResultTerms.containsKey(iBooleanResultTerm)) {
			boolVarsForIBooleanResultTerms.put(iBooleanResultTerm,
					chocoCsp.getModel().boolVar(iBooleanResultTerm.toRepresentation()));

		}
		return boolVarsForIBooleanResultTerms.get(iBooleanResultTerm);
	}

	/**
	 * Returns the variable generated for a term.
	 * 
	 * @param iBooleanResultTerm
	 *            The term.
	 * @return The variable generated for a term. <code>null</code> if no such
	 *         variable exists.
	 */
	public BoolVar getBoolVar(IBooleanResultTerm iBooleanResultTerm) {
		if (iBooleanResultTerm == null) {
			throw new IllegalArgumentException();
		}
		return boolVarsForIBooleanResultTerms.get(iBooleanResultTerm);
	}

	/**
	 * Returns the variable generated for a term. If there was not already a
	 * variable generated for the term, a new variable is generated.
	 * 
	 * @param iBooleanResultTerm
	 *            The term.
	 * @return The variable generated for a term.
	 */
	private IntVar getOrCreateIntVar(IIntegerResultTerm iIntegerResultTerm, int initialLowerBound,
			int initialUpperBound) {
		if (iIntegerResultTerm == null) {
			throw new IllegalArgumentException();
		}
		if (!intVarsForIIntegerResultTerms.containsKey(iIntegerResultTerm)) {
			intVarsForIIntegerResultTerms.put(iIntegerResultTerm, chocoCsp.getModel()
					.intVar(iIntegerResultTerm.toRepresentation(), initialLowerBound, initialUpperBound, true));
		}
		return intVarsForIIntegerResultTerms.get(iIntegerResultTerm);
	}

	/**
	 * Comparison operators. This is a typed representation of chocos textual
	 * representations.
	 * 
	 * @author Jan Prien
	 *
	 */
	private enum ComparisonOperation {

		/**
		 * Equality operator.
		 */
		EQUALITY("="),

		/**
		 * Inequality operator.
		 */
		INEQUALITY("!="),

		/**
		 * Less than operator.
		 */
		LESSTHAN("<"),

		/**
		 * Less than equals operator.
		 */
		LESSTHANOREQUALITY("<="),

		/**
		 * Greater than operator.
		 */
		GREATERTHAN(">"),

		/**
		 * Greater than equals operator.
		 */
		GREATERTHANOREQUALITY(">=");

		/**
		 * Chocos textual representation.
		 */
		public final String operator;

		/**
		 * Constructs an object.
		 * 
		 * @param operator
		 *            Chocos textual representation.
		 */
		private ComparisonOperation(final String operator) {
			if (operator == null) {
				throw new IllegalArgumentException();
			}
			this.operator = operator;
		}

	}

	/**
	 * Adds constraints to the choco CSP for a comparison operator the two terms in
	 * a boolean result term. For the two terms already variables must have been
	 * added to the choco CSP.
	 * 
	 * @param abstractBooleanResultForBinaryIntegerArgumentTerm
	 *            The boolean result term.
	 * @param operation
	 *            The operation.
	 */
	public void visitBooleanResultForIntegerArithmIntegerTerm(
			AbstractBooleanResultForBinaryIntegerArgumentTerm abstractBooleanResultForBinaryIntegerArgumentTerm,
			ComparisonOperation operation) {
		if (abstractBooleanResultForBinaryIntegerArgumentTerm == null || operation == null) {
			throw new IllegalArgumentException();
		}
		IntVar intVarForLeftIIntegerResultTerm = visitIIntegerResultTerm(
				abstractBooleanResultForBinaryIntegerArgumentTerm.getLeft());
		IntVar intVarForRightIIntegerResultTerm = visitIIntegerResultTerm(
				abstractBooleanResultForBinaryIntegerArgumentTerm.getRight());
		BoolVar boolVar = getOrCreateBoolVar(abstractBooleanResultForBinaryIntegerArgumentTerm);
		Model model = chocoCsp.getModel();
		model.reification(boolVar,
				model.arithm(intVarForLeftIIntegerResultTerm, operation.operator, intVarForRightIIntegerResultTerm));
	}

	/**
	 * Arithmetic operators. This is a typed representation of chocos textual
	 * representations.
	 * 
	 * @author Jan Prien
	 *
	 */
	private enum ArithmOperation {

		/**
		 * Division operator.
		 */
		DIVISION("/"),

		/**
		 * Multiplication operator.
		 */
		MULTIPLICATION("*"),

		/**
		 * Addtion operator.
		 */
		ADDITION("+"),

		/**
		 * Substraction operator.
		 */
		SUBSTRACTION("-");

		/**
		 * Chocos textual representation.
		 */
		public final String operator;

		/**
		 * Constructs an object.
		 * 
		 * @param operator
		 *            Chocos textual representation.
		 */
		private ArithmOperation(final String operator) {
			if (operator == null) {
				throw new IllegalArgumentException();
			}
			this.operator = operator;
		}

	}

	/**
	 * Adds constraints to the choco CSP for an arithmetic operator the two terms in
	 * a integer result term. For the two terms already variables must have been
	 * added to the choco CSP.
	 * 
	 * @param abstractIntegerResultForBinaryIntegerArgumentTerm
	 *            The integer result term.
	 * @param operation
	 *            The arithmetic operation.
	 */
	private void visitIntegerResultForIntegerArithmIntegerTerm(
			AbstractIntegerResultForBinaryIntegerArgumentTerm abstractIntegerResultForBinaryIntegerArgumentTerm,
			ArithmOperation operation) {
		if (abstractIntegerResultForBinaryIntegerArgumentTerm == null || operation == null) {
			throw new IllegalArgumentException();
		}
		IntVar intVarForLeftIIntegerResultTerm = visitIIntegerResultTerm(
				abstractIntegerResultForBinaryIntegerArgumentTerm.getLeft());
		IntVar intVarForRightIIntegerResultTerm = visitIIntegerResultTerm(
				abstractIntegerResultForBinaryIntegerArgumentTerm.getRight());
		Model model = chocoCsp.getModel();
		IntVar intVar = getOrCreateIntVar(abstractIntegerResultForBinaryIntegerArgumentTerm,
				getMin(intVarForLeftIIntegerResultTerm, intVarForRightIIntegerResultTerm, operation),
				getMax(intVarForLeftIIntegerResultTerm, intVarForRightIIntegerResultTerm, operation));
		model.arithm(intVar, ComparisonOperation.EQUALITY.operator, intVarForLeftIIntegerResultTerm, operation.operator,
				intVarForRightIIntegerResultTerm).post();
		model.getClauseConstraint();
	}

	/**
	 * Returns the maximum value resulting in the operation on values of the
	 * domains. If the domains contain values that are greater than that, the
	 * highest value of the domains is returned.
	 * 
	 * @param leftIntVar
	 *            The domain used as first operands.
	 * @param rightIntVar
	 *            The domain used as second operands.
	 * @param operation
	 *            The arithmetic operator.
	 * @return The maximum value resulting in the operation on values of the
	 *         domains. If the domains contain values that are greater than that,
	 *         the highest value of the domains is returned.
	 */
	private int getMax(IntVar leftIntVar, IntVar rightIntVar, ArithmOperation operation) {
		if (leftIntVar == null || rightIntVar == null) {
			throw new IllegalArgumentException();
		}
		if (leftIntVar == null || rightIntVar == null) {
			throw new IllegalArgumentException();
		}
		final int leftIntVarUpperBound = leftIntVar.getUB();
		final int leftIntVarLowerBound = leftIntVar.getLB();
		final int rightIntVarUpperBound = rightIntVar.getUB();
		final int rightIntVarLowerBound = rightIntVar.getLB();
		final int[][] pairs = new int[][] { new int[] { leftIntVarUpperBound, rightIntVarUpperBound },
				new int[] { leftIntVarUpperBound, rightIntVarLowerBound },
				new int[] { leftIntVarLowerBound, rightIntVarUpperBound },
				new int[] { leftIntVarLowerBound, rightIntVarLowerBound } };
		int max = Integer.MIN_VALUE;
		switch (operation) {
		case DIVISION:
			for (int[] pair : pairs) {
				try {
					final int result = pair[0] / pair[1];
					if (result > max) {
						max = result;
					}
				} catch (Exception e) {

				}
			}
			if (max == Integer.MAX_VALUE) {
				max = 0;
			}
			break;
		case MULTIPLICATION:
			for (int[] pair : pairs) {
				try {
					final int result = pair[0] * pair[1];
					if (result > max) {
						max = result;
					}
				} catch (Exception e) {
					max = Integer.MAX_VALUE;
				}
			}
			break;
		case ADDITION:
			for (int[] pair : pairs) {
				try {
					final int result = pair[0] + pair[1];
					if (result > max) {
						max = result;
					}
				} catch (Exception e) {
					max = Integer.MAX_VALUE;
				}
			}
			break;
		case SUBSTRACTION:
			for (int[] pair : pairs) {
				try {
					final int result = pair[0] - pair[1];
					if (result > max) {
						max = result;
					}
				} catch (Exception e) {
					max = Integer.MAX_VALUE;
				}
			}
			break;
		default:
			throw new UnsupportedOperationException();
		}
		if (leftIntVarUpperBound > max) {
			max = leftIntVarUpperBound;
		}
		if (rightIntVarUpperBound > max) {
			max = rightIntVarUpperBound;
		}
		return max;
	}

	/**
	 * Returns the minimum value resulting in the operation on values of the
	 * domains. If the domains contain values that are less than that, the lowest
	 * value of the domains is returned.
	 * 
	 * @param leftIntVar
	 *            The domain used as first operands.
	 * @param rightIntVar
	 *            The domain used as second operands.
	 * @param operation
	 *            The arithmetic operator.
	 * @return The minimum value resulting in the operation on values of the
	 *         domains. If the domains contain values that are less than that, the
	 *         lowest value of the domains is returned.
	 */
	private int getMin(IntVar leftIntVar, IntVar rightIntVar, ArithmOperation operation) {
		if (leftIntVar == null || rightIntVar == null) {
			throw new IllegalArgumentException();
		}
		if (leftIntVar == null || rightIntVar == null) {
			throw new IllegalArgumentException();
		}
		final int leftIntVarUpperBound = leftIntVar.getUB();
		final int leftIntVarLowerBound = leftIntVar.getLB();
		final int rightIntVarUpperBound = rightIntVar.getUB();
		final int rightIntVarLowerBound = rightIntVar.getLB();
		final int[][] pairs = new int[][] { new int[] { leftIntVarUpperBound, rightIntVarUpperBound },
				new int[] { leftIntVarUpperBound, rightIntVarLowerBound },
				new int[] { leftIntVarLowerBound, rightIntVarUpperBound },
				new int[] { leftIntVarLowerBound, rightIntVarLowerBound } };
		int min = Integer.MAX_VALUE;
		switch (operation) {
		case DIVISION:
			for (int[] pair : pairs) {
				try {
					final int result = pair[0] / pair[1];
					if (result < min) {
						min = result;
					}
				} catch (Exception e) {

				}
			}
			if (min == Integer.MAX_VALUE) {
				min = 0;
			}
			break;
		case MULTIPLICATION:
			for (int[] pair : pairs) {
				try {
					final int result = pair[0] * pair[1];
					if (result < min) {
						min = result;
					}
				} catch (Exception e) {
					min = Integer.MIN_VALUE;
				}
			}
			break;
		case ADDITION:
			for (int[] pair : pairs) {
				try {
					final int result = pair[0] + pair[1];
					if (result < min) {
						min = result;
					}
				} catch (Exception e) {
					min = Integer.MIN_VALUE;
				}
			}
			break;
		case SUBSTRACTION:
			for (int[] pair : pairs) {
				try {
					final int result = pair[0] - pair[1];
					if (result < min) {
						min = result;
					}
				} catch (Exception e) {
					min = Integer.MIN_VALUE;
				}
			}
			break;
		default:
			throw new UnsupportedOperationException();
		}
		if (leftIntVarLowerBound < min) {
			min = leftIntVarLowerBound;
		}
		if (rightIntVarLowerBound < min) {
			min = rightIntVarLowerBound;
		}
		return min;
	}

	@Override
	public void visitBooleanResultForBooleanAndBooleanTerm(
			BooleanResultForBooleanAndBooleanTerm booleanResultForBooleanAndBooleanTerm) {
		if (booleanResultForBooleanAndBooleanTerm == null) {
			throw new IllegalArgumentException();
		}
		BoolVar boolVarForLeftIBooleanResultTerm = visitIBooleanResultTerm(
				booleanResultForBooleanAndBooleanTerm.getLeft());
		BoolVar intVarForRightIIntegerResultTerm = visitIBooleanResultTerm(
				booleanResultForBooleanAndBooleanTerm.getRight());
		BoolVar boolVar = getOrCreateBoolVar(booleanResultForBooleanAndBooleanTerm);
		Model model = chocoCsp.getModel();
		model.addClausesBoolAndEqVar(boolVarForLeftIBooleanResultTerm, intVarForRightIIntegerResultTerm, boolVar);
	}

	@Override
	public void visitBooleanResultForBooleanImpliesBooleanTerm(
			BooleanResultForBooleanImpliesBooleanTerm booleanResultForBooleanImpliesBooleanTerm) {
		if (booleanResultForBooleanImpliesBooleanTerm == null) {
			throw new IllegalArgumentException();
		}
		BoolVar boolVarForLeftIBooleanResultTerm = visitIBooleanResultTerm(
				booleanResultForBooleanImpliesBooleanTerm.getLeft());
		BoolVar intVarForRightIIntegerResultTerm = visitIBooleanResultTerm(
				booleanResultForBooleanImpliesBooleanTerm.getRight());
		BoolVar boolVar = getOrCreateBoolVar(booleanResultForBooleanImpliesBooleanTerm);
		Model model = chocoCsp.getModel();
		model.addClausesBoolOrEqVar(model.boolNotView(boolVarForLeftIBooleanResultTerm),
				intVarForRightIIntegerResultTerm, boolVar);
		model.getClauseConstraint();
	}

	@Override
	public void visitBooleanResultForBooleanOrBooleanTerm(
			BooleanResultForBooleanOrBooleanTerm booleanResultForBooleanOrBooleanTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForBooleanXOrBooleanTerm(
			BooleanResultForBooleanXOrBooleanTerm booleanResultForBooleanXOrBooleanTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForIntegerEqualIntegerTerm(
			BooleanResultForIntegerEqualIntegerTerm booleanResultForIntegerEqualIntegerTerm) {
		visitBooleanResultForIntegerArithmIntegerTerm(booleanResultForIntegerEqualIntegerTerm,
				ComparisonOperation.EQUALITY);
	}

	@Override
	public void visitBooleanResultForIntegerEqualRealTerm(
			BooleanResultForIntegerEqualRealTerm booleanResultForIntegerEqualRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForIntegerGreaterThanEqualIntegerTerm(
			BooleanResultForIntegerGreaterThanEqualIntegerTerm booleanResultForIntegerGreaterThanEqualIntegerTerm) {
		visitBooleanResultForIntegerArithmIntegerTerm(booleanResultForIntegerGreaterThanEqualIntegerTerm,
				ComparisonOperation.GREATERTHANOREQUALITY);
	}

	@Override
	public void visitBooleanResultForIntegerGreaterThanEqualRealTerm(
			BooleanResultForIntegerGreaterThanEqualRealTerm booleanResultForIntegerGreaterThanEqualRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForIntegerGreaterThanIntegerTerm(
			BooleanResultForIntegerGreaterThanIntegerTerm booleanResultForIntegerGreaterThanIntegerTerm) {
		visitBooleanResultForIntegerArithmIntegerTerm(booleanResultForIntegerGreaterThanIntegerTerm,
				ComparisonOperation.GREATERTHAN);
	}

	@Override
	public void visitBooleanResultForIntegerGreaterThanRealTerm(
			BooleanResultForIntegerGreaterThanRealTerm booleanResultForIntegerGreaterThanRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanVariable(BooleanVariable booleanVariable) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerVariable(IntegerVariable integerVariable) {
		if (integerVariable == null) {
			throw new IllegalArgumentException();
		}
		IntVar intVarForLeftIIntegerResultTerm = chocoCsp.getIntVar(integerVariable);
		intVarsForIIntegerResultTerms.put(integerVariable, intVarForLeftIIntegerResultTerm);
	}

	@Override
	public void visitRealVariable(RealVariable realVariable) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForIntegerLessThanEqualIntegerTerm(
			BooleanResultForIntegerLessThanEqualIntegerTerm booleanResultForIntegerLessThanEqualIntegerTerm) {
		visitBooleanResultForIntegerArithmIntegerTerm(booleanResultForIntegerLessThanEqualIntegerTerm,
				ComparisonOperation.LESSTHANOREQUALITY);
	}

	@Override
	public void visitBooleanResultForIntegerLessThanEqualRealTerm(
			BooleanResultForIntegerLessThanEqualRealTerm booleanResultForIntegerLessThanEqualRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForIntegerLessThanIntegerTerm(
			BooleanResultForIntegerLessThanIntegerTerm booleanResultForIntegerLessThanIntegerTerm) {
		visitBooleanResultForIntegerArithmIntegerTerm(booleanResultForIntegerLessThanIntegerTerm,
				ComparisonOperation.LESSTHAN);
	}

	@Override
	public void visitBooleanResultForIntegerLessThanRealTerm(
			BooleanResultForIntegerLessThanRealTerm booleanResultForIntegerLessThanRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForIntegerUnequalIntegerTerm(
			BooleanResultForIntegerUnequalIntegerTerm booleanResultForIntegerUnequalIntegerTerm) {
		visitBooleanResultForIntegerArithmIntegerTerm(booleanResultForIntegerUnequalIntegerTerm,
				ComparisonOperation.INEQUALITY);
	}

	@Override
	public void visitBooleanResultForIntegerUnequalRealTerm(
			BooleanResultForIntegerUnequalRealTerm booleanResultForIntegerUnequalRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForNegateBooleanTerm(
			BooleanResultForNegateBooleanTerm booleanResultForNegateBooleanTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealEqualRealTerm(
			BooleanResultForRealEqualRealTerm booleanResultForRealEqualRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealGreaterThanEqualIntegerTerm(
			BooleanResultForRealGreaterThanEqualIntegerTerm booleanResultForRealGreaterThanEqualIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealGreaterThanEqualRealTerm(
			BooleanResultForRealGreaterThanEqualRealTerm booleanResultForRealGreaterThanEqualRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealGreaterThanIntegerTerm(
			BooleanResultForRealGreaterThanIntegerTerm booleanResultForRealGreaterThanIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealGreaterThanRealTerm(
			BooleanResultForRealGreaterThanRealTerm booleanResultForRealGreaterThanRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealLessThanEqualIntegerTerm(
			BooleanResultForRealLessThanEqualIntegerTerm booleanResultForRealLessThanEqualIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForIntegerModuloIntegerTerm(
			IntegerResultForIntegerModuloIntegerTerm integerResultForIntegerModuloIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForIntegerSubtractionIntegerTerm(
			IntegerResultForIntegerSubtractionIntegerTerm integerResultForIntegerSubtractionIntegerTerm) {
		visitIntegerResultForIntegerArithmIntegerTerm(integerResultForIntegerSubtractionIntegerTerm,
				ArithmOperation.SUBSTRACTION);
	}

	@Override
	public void visitIntegerResultForMaxIntegerIntegerTerm(
			IntegerResultForMaxIntegerIntegerTerm integerResultForMaxIntegerIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForAbsoluteIntegerTerm(
			IntegerResultForAbsoluteIntegerTerm integerResultForAbsoluteIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForMinIntegerTerm(IntegerResultForMinIntegerTerm integerResultForMinIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForNegateIntegerTerm(
			IntegerResultForNegateIntegerTerm integerResultForNegateIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForFloorRealTerm(RealResultForFloorRealTerm realResultForFloorRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForCeilRealTerm(RealResultForCeilRealTerm realResultForCeilRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForIntegerLogarithmRealTerm(
			RealResultForIntegerLogarithmRealTerm realResultForIntegerLogarithmRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForIntegerLogarithmIntegerTerm(
			RealResultForIntegerLogarithmIntegerTerm realResultForIntegerLogarithmIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForMaxRealRealTerm(RealResultForMaxRealRealTerm realResultForMaxRealRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForMinRealIntegerTerm(RealResultForMinRealIntegerTerm realResultForMinRealIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealDivisionIntegerTerm(
			RealResultForRealDivisionIntegerTerm realResultForRealDivisionIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForNegateRealTerm(RealResultForNegateRealTerm realResultForNegateRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForIntegerExponentiationIntegerTerm(
			IntegerResultForIntegerExponentiationIntegerTerm integerResultForIntegerExponentiationIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForMaxRealIntegerTerm(RealResultForMaxRealIntegerTerm realResultForMaxRealIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealAdditionIntegerTerm(
			RealResultForRealAdditionIntegerTerm realResultForRealAdditionIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealAdditionRealTerm(
			RealResultForRealAdditionRealTerm realResultForRealAdditionRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealDivisionRealTerm(
			RealResultForRealDivisionRealTerm realResultForRealDivisionRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealLogarithmIntegerTerm(
			RealResultForRealLogarithmIntegerTerm realResultForRealLogarithmIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealLogarithmRealTerm(
			RealResultForRealLogarithmRealTerm realResultForRealLogarithmRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForRoundRealTerm(IntegerResultForRoundRealTerm integerResultForRoundRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForFloorRealTerm(IntegerResultForFloorRealTerm integerResultForFloorRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealUnequalRealTerm(
			BooleanResultForRealUnequalRealTerm booleanResultForRealUnequalRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealLessThanEqualRealTerm(
			BooleanResultForRealLessThanEqualRealTerm booleanResultForRealLessThanEqualRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitBooleanResultForRealLessThanRealTerm(
			BooleanResultForRealLessThanRealTerm booleanResultForRealLessThanRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForIntegerMultiplicationIntegerTerm(
			IntegerResultForIntegerMultiplicationIntegerTerm integerResultForIntegerMultiplicationIntegerTerm) {
		visitIntegerResultForIntegerArithmIntegerTerm(integerResultForIntegerMultiplicationIntegerTerm,
				ArithmOperation.MULTIPLICATION);
	}

	@Override
	public void visitBooleanResultForRealLessThanIntegerTerm(
			BooleanResultForRealLessThanIntegerTerm booleanResultForRealLessThanIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForIntegerDivisionIntegerTerm(
			IntegerResultForIntegerDivisionIntegerTerm integerResultForIntegerDivisionIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForCeilRealTerm(IntegerResultForCeilRealTerm integerResultForCeilRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitIntegerResultForIntegerAdditionIntegerTerm(
			IntegerResultForIntegerAdditionIntegerTerm integerResultForIntegerAdditionIntegerTerm) {
		visitIntegerResultForIntegerArithmIntegerTerm(integerResultForIntegerAdditionIntegerTerm,
				ArithmOperation.ADDITION);
	}

	@Override
	public void visitRealResultForMinRealRealTerm(RealResultForMinRealRealTerm realResultForMinRealRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealMultiplicationIntegerTerm(
			RealResultForRealMultiplicationIntegerTerm realResultForRealMultiplicationIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealMultiplicationRealTerm(
			RealResultForRealMultiplicationRealTerm realResultForRealMultiplicationRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealSubtractionIntegerTerm(
			RealResultForRealSubtractionIntegerTerm realResultForRealSubtractionIntegerTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void visitRealResultForRealSubtractionRealTerm(
			RealResultForRealSubtractionRealTerm realResultForRealSubtractionRealTerm) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
