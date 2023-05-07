package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.IntegerSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator that validates all integer configuration aspects.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractIntegerSettingsValidityRuleValidator extends AbstractValidator {

	@Override
	ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model, List<Expression> invariants) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		return this.getViolations(config.getIntegerTypeSettings(), model);
	}

	/**
	 * Validates an integer configuration aspect.
	 * 
	 * @param config
	 *            The integer configuration aspect.
	 * @param model
	 *            The model.
	 * @return The violations of the validity regarding the definition of this
	 *         validator
	 */
	protected abstract ValidityRuleViolence[] getViolations(IntegerSettings config, IModel model);

}
