package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.OptionSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator that validates all option configuration aspects.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractOptionSettingsValidityRuleValidator extends AbstractValidator {

	@Override
	ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model, List<Expression> invariants) {
		return getViolations(config.getOptionSettings(), model);
	}

	/**
	 * Validates an option configuration aspect.
	 * 
	 * @param config
	 *            The option configuration aspect.
	 * @param model
	 *            The model.
	 * @return The violations of the validity regarding the definition of this
	 *         validator
	 */
	abstract ValidityRuleViolence[] getViolations(OptionSettings config, IModel model);

}
