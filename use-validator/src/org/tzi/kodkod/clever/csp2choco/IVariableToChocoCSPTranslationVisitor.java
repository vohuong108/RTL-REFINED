package org.tzi.kodkod.clever.csp2choco;

import org.tzi.kodkod.clever.csp.IVariable;
import org.tzi.kodkod.clever.csp.IVariableVisitor;
import org.tzi.kodkod.clever.csp.bool.BooleanVariable;
import org.tzi.kodkod.clever.csp.choco.ChocoCSP;
import org.tzi.kodkod.clever.csp.integer.IntegerVariable;
import org.tzi.kodkod.clever.csp.real.RealVariable;

/**
 * Visitor for Variables ({@link IVariable}, that creates variables on a choco
 * CSP for variables in a CSP.
 * 
 * @author Jan Prien
 *
 */
public final class IVariableToChocoCSPTranslationVisitor implements IVariableVisitor {

	/**
	 * The choco CSP structure which gets enriched on visiting variables.
	 */
	private final ChocoCSP chocoCsp;

	/**
	 * Creates and object.
	 * 
	 * @param chocoCsp
	 *            The choco CSP structure which gets enriched on visiting variables.
	 */
	IVariableToChocoCSPTranslationVisitor(ChocoCSP chocoCsp) {
		if (chocoCsp == null) {
			throw new IllegalArgumentException();
		}
		this.chocoCsp = chocoCsp;
	}

	@Override
	public void visitIntegerVariable(IntegerVariable integerVariable) {
		chocoCsp.getIntVar(integerVariable);
	}

	@Override
	public void visitBooleanVariable(BooleanVariable booleanVariable) {
		chocoCsp.getBoolVar(booleanVariable);
	}

	@Override
	public void visitRealVariable(RealVariable realVariable) {
		chocoCsp.getRealVar(realVariable);
	}

}
