package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator that validates all real configuration aspects.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractRealSettingsValidityRuleValidator extends AbstractValidator
		implements IRealSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model,
			List<Expression> invariants) {
		return getViolations(config.getRealTypeSettings(), model);
	}

	/**
	 * Validates an real configuration aspect.
	 * 
	 * @param config
	 *            The real configuration aspect.
	 * @param model
	 *            The model.
	 * @return The violations of the validity regarding the definition of this
	 *         validator
	 */
	abstract ValidityRuleViolence[] getViolations(RealSettings config, IModel model);

}
