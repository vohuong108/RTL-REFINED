package org.tzi.kodkod.clever.csp;

import org.tzi.kodkod.clever.csp.bool.BooleanVariable;
import org.tzi.kodkod.clever.csp.integer.IntegerVariable;
import org.tzi.kodkod.clever.csp.real.RealVariable;

/**
 * Visitor for Variables ({@link IVariable}. The visitor must implement a
 * specific handling for selected types of varibles.
 * 
 * @author Jan Prien
 *
 */
public interface IVariableVisitor {

	void visitIntegerVariable(IntegerVariable integerVariable);

	void visitBooleanVariable(BooleanVariable booleanVariable);

	void visitRealVariable(RealVariable realVariable);

}
