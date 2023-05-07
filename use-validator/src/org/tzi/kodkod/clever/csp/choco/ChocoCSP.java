package org.tzi.kodkod.clever.csp.choco;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.RealVar;
import org.tzi.kodkod.clever.csp.AbstractDomain;
import org.tzi.kodkod.clever.csp.ICSP;
import org.tzi.kodkod.clever.csp.IReferenceHolder;
import org.tzi.kodkod.clever.csp.IReferenciator;
import org.tzi.kodkod.clever.csp.bool.BooleanDomain;
import org.tzi.kodkod.clever.csp.bool.BooleanVariable;
import org.tzi.kodkod.clever.csp.integer.IntegerDomain;
import org.tzi.kodkod.clever.csp.integer.IntegerVariable;
import org.tzi.kodkod.clever.csp.real.RealDomain;
import org.tzi.kodkod.clever.csp.real.RealVariable;

/**
 * Wrapper for a choco CSP model ({@link Model}) as an CSP.
 * 
 * @author Jan Prien
 *
 */
public final class ChocoCSP implements ICSP {

	/**
	 * The choco CSP model.
	 */
	private final Model model;

	/**
	 * The integer variables of the CSP.
	 */
	private final Map<IntegerVariable, IntVar> intVarsForIntegerVariables = new HashMap<>();

	/**
	 * The real variables of the CSP.
	 */
	private final Map<RealVariable, RealVar> realVarsForRealVariables = new HashMap<>();

	/**
	 * The boolean variables of the CSP.
	 */
	private final Map<BooleanVariable, BoolVar> boolVarsForBooleanVariables = new HashMap<>();

	/**
	 * Constructs an object.
	 */
	public ChocoCSP() {
		this.model = new Model();
	}

	/**
	 * Adds an integer variable. The domain given as a variable is used as initial
	 * domain. Values are copied. Modifications on the integer variable to adapt do
	 * not modify the created integer variables domain.
	 * 
	 * @param integerVariable
	 *            The integer variable to add.
	 * @return The added integer variable.
	 */
	public IntVar getIntVar(IntegerVariable integerVariable) {
		if (integerVariable == null) {
			throw new IllegalArgumentException();
		}
		IntVar intVar = intVarsForIntegerVariables.get(integerVariable);
		if (intVar == null) {
			final IntegerDomain integerDomain = integerVariable.getDomain();
			intVar = model.intVar(integerVariable.toRepresentation(), integerDomain.getLowerBound(),
					integerDomain.getUpperBound(), true);
			intVarsForIntegerVariables.put(integerVariable, intVar);
		}
		return intVar;
	}

	/**
	 * Adds an real variable. The domain given as a variable is used as initial
	 * domain. Values are copied. Modifications on the real variable to adapt do not
	 * modify the created real variables domain.
	 * 
	 * @param realVariable
	 *            The real variable to add.
	 * @return The added real variable.
	 */
	public RealVar getRealVar(RealVariable realVariable) {
		if (realVariable == null) {
			throw new IllegalArgumentException();
		}
		RealVar realVar = realVarsForRealVariables.get(realVariable);
		if (realVar == null) {
			final RealDomain realDomain = realVariable.getDomain();
			realVar = model.realVar(realVariable.toRepresentation(), realDomain.getLowerBound(),
					realDomain.getUpperBound(), realDomain.getPrecision());
			realVarsForRealVariables.put(realVariable, realVar);
		}
		return realVar;
	}

	/**
	 * Adds an boolean variable. The domain given as a variable is used as initial
	 * domain. Values are copied. Modifications on the boolean variable to adapt do
	 * not modify the created boolean variables domain.
	 * 
	 * @param booleanVariable
	 *            The boolean variable to add.
	 * @return The added boolean variable.
	 */
	public BoolVar getBoolVar(BooleanVariable booleanVariable) {
		if (booleanVariable == null) {
			throw new IllegalArgumentException();
		}
		BoolVar boolVar = boolVarsForBooleanVariables.get(booleanVariable);
		if (boolVar == null) {
			final BooleanDomain booleanDomain = booleanVariable.getDomain();
			final boolean lowerBound = booleanDomain.getLowerBound();
			final boolean upperBound = booleanDomain.getUpperBound();
			final String representation = booleanVariable.toRepresentation();
			if (lowerBound == upperBound) {
				boolVar = model.boolVar(representation, lowerBound);
			} else {
				boolVar = model.boolVar(representation);
			}
			boolVarsForBooleanVariables.put(booleanVariable, boolVar);
		}
		return boolVar;
	}

	public Model getModel() {
		return model;
	}

	@Override
	public String toString() {
		return model.toString();
	}

	/**
	 * Returns all the mappings of variables to other elements.
	 * 
	 * @return All the mappings of variables to other elements.
	 */
	public Set<IReferenceHolder<?>> getReferenceHolder() {
		final Set<IReferenceHolder<?>> referenceHolders = new HashSet<>();
		referenceHolders.addAll(intVarsForIntegerVariables.keySet());
		referenceHolders.addAll(realVarsForRealVariables.keySet());
		referenceHolders.addAll(boolVarsForBooleanVariables.keySet());
		return referenceHolders;
	}

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
	 * Tightens the domain of the variables based on their actual domains and the
	 * constraints on them.
	 * 
	 * @throws ContradictionException
	 *             If there is a contradiction in the constraints with the given
	 *             domains of the variables.
	 */
	public void propagate() throws ContradictionException {
		model.getSolver().propagate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A extends AbstractDomain<?>> A getDomain(IReferenciator<?> iReferenciator) {
		final Set<IReferenceHolder<?>> referenceHolders = getReferenceHolder();
		for (IReferenceHolder<?> abstractCSPReferenceHolder : referenceHolders) {
			IReferenciator<?> referenciator2 = abstractCSPReferenceHolder.getRefereciator();
			if (iReferenciator.referencesSame(referenciator2)) {
				if (abstractCSPReferenceHolder instanceof IntegerVariable) {
					IntVar intVar = intVarsForIntegerVariables.get(abstractCSPReferenceHolder);
					return (A) new IntegerDomain(intVar.getLB(), intVar.getUB());
				} else if (abstractCSPReferenceHolder instanceof BooleanVariable) {
					BoolVar boolVar = boolVarsForBooleanVariables.get(abstractCSPReferenceHolder);
					return (A) new BooleanDomain(boolVar.getLB() > 0, boolVar.getUB() > 0);
				} else if (abstractCSPReferenceHolder instanceof RealVariable) {
					RealVar realVar = realVarsForRealVariables.get(abstractCSPReferenceHolder);
					return (A) new RealDomain(realVar.getLB(), realVar.getUB(),
							((RealDomain) abstractCSPReferenceHolder.getDomain()).getPrecision());
				} else {
					throw new UnsupportedOperationException("Type " + abstractCSPReferenceHolder.getClass().toString()
							+ " of variable " + abstractCSPReferenceHolder.toString() + " can not be handled");
				}
			}
		}
		return null;
	}

}
