/**
 * Package for functionality for deriving CSPs
 * ({@link org.tzi.kodkod.clever.csp.CSP}) for bounds tightening of UML/OCL
 * models ({@link org.tzi.kodkod.model.iface.IModel}).
 * 
 * For the translation an initial bounds specification
 * ({@link org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification})
 * is needed. The translation can be processed with
 * {@link org.tzi.kodkod.clever.model2csp.IModelAndExpressionToCSPTranslator#translate(IModelCSPVariablesInitialBoundsSpecification, java.util.List, boolean)}.
 * 
 * @author Jan Prien
 *
 */
package org.tzi.kodkod.clever.model2csp;