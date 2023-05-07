/**
 * Package for functionality of cleverly generating instance finder
 * configurations.
 * 
 * Redundant search space removal can be processed for existing configurations
 * with
 * {@link org.tzi.kodkod.clever.CleverConfigurationGenerator#generate(org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification, java.util.List, boolean, boolean, boolean, org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration)}.
 * 
 * The redundant search space removal is achieved by creating a Constraint
 * Satisfaction Problem. CSP variables for all model elements are created. CSP
 * constraints for model aspects are created. This is processed with
 * {@link org.tzi.kodkod.clever.model2csp.IModelAndExpressionToCSPTranslator#translate(org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification, java.util.List, boolean)}.
 * For the domains of the CSP variables are bounds tightening mechanism is
 * applied. This removes redundant search space. The tighter bounds of the CSP
 * domains are transferred back to the model elements, the variables are for.
 * 
 * @author Jan Prien
 *
 */
package org.tzi.kodkod.clever;
