package org.tzi.kodkod.clever.csp2choco;

import org.chocosolver.solver.variables.BoolVar;
import org.tzi.kodkod.clever.csp.CSP;
import org.tzi.kodkod.clever.csp.IConstraint;
import org.tzi.kodkod.clever.csp.bool.IBooleanResultTerm;
import org.tzi.kodkod.clever.csp.choco.ChocoCSP;

/**
 * Translator for internal CSP structure to wrapped choco CSP structure.
 * 
 * @author Jan Prien
 *
 */
public final class CSPToChocoCSPTranslator {

	/**
	 * Constructs an object.
	 */
	public CSPToChocoCSPTranslator() {

	}

	/**
	 * Translates a CSP to a CSP in the wrapped choco CSP structure.
	 * 
	 * @param csp
	 *            The CSP to translate.
	 * @return The translated CSP in the wrapped choco CSP structure.
	 * @throws TranslationException
	 *             If the translation fails.
	 */
	public ChocoCSP translate(final CSP csp) throws TranslationException {
		if (csp == null) {
			throw new IllegalArgumentException();
		}
		ChocoCSP chocoCsp = new ChocoCSP();
		csp.processVariables(new IVariableToChocoCSPTranslationVisitor(chocoCsp));
		IConstraintToChocoCSPTranslationVisitor iConstraintToChocoCSPTranslationVisitor = new IConstraintToChocoCSPTranslationVisitor(
				chocoCsp);
		csp.processConstraints(iConstraintToChocoCSPTranslationVisitor);
		for (IConstraint iConstraint : csp.getConstraints()) {
			try {
				BoolVar boolVar = iConstraintToChocoCSPTranslationVisitor.getBoolVar((IBooleanResultTerm) iConstraint);
				chocoCsp.getModel().addClauseTrue(boolVar);
			} catch (Exception e) {
				throw new TranslationException();
			}
		}
		return chocoCsp;
	}

}
