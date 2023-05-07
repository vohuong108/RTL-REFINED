package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.kodkod.plugin.gui.model.data.StringSettings;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator that validates all string configuration aspects.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractStringSettingsValidityRuleValidator extends AbstractValidator
		implements IStringSettingsValidityRuleValidator {

	@Override
	ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model, List<Expression> invariants) {
		return getViolations(config.getStringTypeSettings(), model);
	}

	/**
	 * Validates an string configuration aspect.
	 * 
	 * @param config
	 *            The string configuration aspect.
	 * @param model
	 *            The model.
	 * @return The violations of the validity regarding the definition of this
	 *         validator
	 */
	abstract ValidityRuleViolence[] getViolations(StringSettings config, IModel model);

}
