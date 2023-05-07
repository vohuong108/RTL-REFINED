package org.tzi.kodkod.clever;

import java.util.List;

import org.apache.log4j.Logger;
import org.chocosolver.solver.exception.ContradictionException;
import org.tzi.kodkod.clever.csp.CSP;
import org.tzi.kodkod.clever.csp.ICSP;
import org.tzi.kodkod.clever.csp.choco.ChocoCSP;
import org.tzi.kodkod.clever.csp.integer.IntegerDomain;
import org.tzi.kodkod.clever.csp2choco.CSPToChocoCSPTranslator;
import org.tzi.kodkod.clever.csp2config.ICSPToSettingsConfigurationTransferrer;
import org.tzi.kodkod.clever.csp2config.TransferException;
import org.tzi.kodkod.clever.model2csp.IAssociationReferenciator;
import org.tzi.kodkod.clever.model2csp.IAttributeForClassReference;
import org.tzi.kodkod.clever.model2csp.IAttributeForClassReferenciator;
import org.tzi.kodkod.clever.model2csp.IClassReferenciator;
import org.tzi.kodkod.clever.model2csp.IModelAndExpressionToCSPTranslator;
import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.FixService;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Generator for configurations.
 * 
 * @see #generate(IModelCSPVariablesInitialBoundsSpecification, List, boolean,
 *      boolean, boolean, SettingsConfiguration)
 * 
 * @author Jan Prien
 *
 */
public final class CleverConfigurationGenerator {

	/**
	 * Logger for the whole class.
	 */
	protected static final Logger LOG = Logger.getLogger(CleverConfigurationGenerator.class);

	/**
	 * Constructs an object.
	 */
	public CleverConfigurationGenerator() {

	}

	/**
	 * Tightens the bounds of an instance finder configuration. This only removes
	 * redundant search space. The configuration may be modified with this.
	 * 
	 * @param iModelCSPVariablesInitialBoundsSpecification
	 *            An initial bounds specification. This contains the model.
	 * @param invariants
	 *            The invariants of the model.
	 * @param repsectOclConstraints
	 *            Whether the invariants must be respected in the bounds tightening
	 *            process.
	 * @param generalizeAttributeUpperBound
	 *            Whether all attribute upper bounds should be set to -1 if they are
	 *            greater than or equal to the upper bounds of the attributes owning
	 *            class.
	 * @param mandatorizeAttributes
	 *            Whether all attribute lower bounds should be set to -1.
	 * @param configuration
	 *            The configuration whose bounds will be tightened.
	 * @throws GenerationException
	 *             When bounds tightening fails.
	 */
	public void generate(IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification,
			List<Expression> invariants, boolean repsectOclConstraints, boolean generalizeAttributeUpperBounds,
			boolean mandatorizeAttributes, SettingsConfiguration configuration) throws GenerationException {
		if (iModelCSPVariablesInitialBoundsSpecification == null || invariants == null || configuration == null) {
			throw new IllegalArgumentException();
		}

		LOG.debug("Initial bounds:\n" + iModelCSPVariablesInitialBoundsSpecification.toString());

		// Generate a CSP with initial domains for variables derived from initial bounds
		// for model elements.
		LOG.debug("Generate CSP...");
		final IModelAndExpressionToCSPTranslator cspTranslator = new IModelAndExpressionToCSPTranslator();
		final CSP csp = cspTranslator.translate(iModelCSPVariablesInitialBoundsSpecification, invariants,
				repsectOclConstraints);
		LOG.debug("CSP:\n" + csp.toString(false));

		// Translate CSP to problem for external solver.
		LOG.debug("Translate to choco CSP...");
		final CSPToChocoCSPTranslator chocoTranslator = new CSPToChocoCSPTranslator();
		ChocoCSP chocoCsp;
		try {
			chocoCsp = chocoTranslator.translate(csp);
		} catch (Exception e) {
			LOG.debug(e);
			throw new GenerationException("CSP could not be translated to problem for external solver.");
		}
		LOG.debug("Choco CSP (before propagation):\n" + chocoCsp.toString());

		// Solve (CSP) problem with external solver.
		LOG.debug("Propagate...");
		try {
			chocoCsp.propagate();
		} catch (ContradictionException e) {
			LOG.debug("Contradictions:\n" + e.fillInStackTrace());
			throw new GenerationException("CSP could not be solved because of contradictions.");
		}
		LOG.debug("Choco CSP (after propagation):\n" + chocoCsp.toString());

		LOG.debug("Domains of model elements:\n"
				+ getModelElements(iModelCSPVariablesInitialBoundsSpecification.getModel(), chocoCsp));

		// Interpret tightened domains for CSP variables as clever bounds for model
		// elements.
		LOG.debug("Transfer to config...");
		final ICSPToSettingsConfigurationTransferrer cspToConfigTransferrer = new ICSPToSettingsConfigurationTransferrer();
		try {
			cspToConfigTransferrer.transfer(chocoCsp, configuration);
		} catch (TransferException e) {
			// This should not occur.
			throw new UnsupportedOperationException("CSP result could not be transferred to configuration.");
		}

		LOG.debug("Adjusting config...");
		if (generalizeAttributeUpperBounds) {
			generalizeAttributeUpperBounds(configuration);
		}
		if (mandatorizeAttributes) {
			mandatorizeAttributes(configuration);
		}
		// Enable/Disable settings for types if necessary.
		try {
			FixService.fixTypeSettingsEnabled(configuration, iModelCSPVariablesInitialBoundsSpecification.getModel(),
					invariants);
		} catch (Exception e) {
			throw new UnsupportedOperationException("Adjustments on configuration could not be applied.");
		}

	}

	/**
	 * Sets all attribute lower bounds to -1.
	 * 
	 * @param configuration
	 *            The configuration whose attribute lower bounds will be set to -1.
	 */
	private void mandatorizeAttributes(SettingsConfiguration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException();
		}
		for (ClassSettings classSettings : configuration.getAllClassesSettings()) {
			for (AttributeSettings attributeSettings : classSettings.getAttributeSettings().values()) {
				attributeSettings.setLowerBound(-1);
			}
		}
	}

	/**
	 * Sets all attribute upper bounds to -1 if the upper bounds of the owning class
	 * are less than or equal the original attribute upper bound.
	 * 
	 * @param configuration
	 *            The configuration whose attribute upper bound may be edited.
	 */
	private void generalizeAttributeUpperBounds(SettingsConfiguration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException();
		}
		for (ClassSettings classSettings : configuration.getAllClassesSettings()) {
			final int classSettingsUpperBound = classSettings.getUpperBound();
			for (AttributeSettings attributeSettings : classSettings.getAttributeSettings().values()) {
				if (classSettingsUpperBound <= attributeSettings.getUpperBound()) {
					attributeSettings.setUpperBound(-1);
				}
			}
		}
	}

	/**
	 * Creates a textual representation for model elements CSP variable values.
	 * 
	 * @param model
	 *            The model defining the model elements.
	 * @param icsp
	 *            The CSP, which has variables for all model elements.
	 * @return A textual representation for the models elements CSP variable values.
	 */
	private String getModelElements(IModel model, ICSP icsp) {
		if (model == null || icsp == null) {
			throw new IllegalArgumentException();
		}
		String string = "";
		for (IClass iClass : model.classes()) {
			IntegerDomain iClassIntegerDomain = icsp.getDomain(new IClassReferenciator(iClass));
			string += "Class " + iClass.name() + " : [" + iClassIntegerDomain.getLowerBound() + ","
					+ iClassIntegerDomain.getUpperBound() + "]\n";
			for (IAttribute iAttribute : iClass.allAttributes()) {
				IntegerDomain iAttributeIntegerDomain = icsp.getDomain(
						new IAttributeForClassReferenciator(new IAttributeForClassReference(iClass, iAttribute)));
				string += "Class " + iClass.name() + " Attribute " + iAttribute.name() + " : ["
						+ iAttributeIntegerDomain.getLowerBound() + "," + iAttributeIntegerDomain.getUpperBound()
						+ "]\n";

			}
		}
		for (IAssociation iAssociation : model.associations()) {
			IntegerDomain iAssociationIntegerDomain = icsp.getDomain(new IAssociationReferenciator(iAssociation));
			string += "Association " + iAssociation.name() + " : [" + iAssociationIntegerDomain.getLowerBound() + ","
					+ iAssociationIntegerDomain.getUpperBound() + "]\n";

		}
		return string;
	}

}
