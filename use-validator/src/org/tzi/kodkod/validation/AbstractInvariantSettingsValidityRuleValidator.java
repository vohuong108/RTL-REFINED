package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.InvariantSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator that validates all invariant configuration aspects.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractInvariantSettingsValidityRuleValidator extends
		AbstractListAttributeValidityRuleValidator<InvariantSettings> implements IInvariantValidityRuleValidator {

	@Override
	ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model, List<Expression> invariants) {
		return getViolations(config.getAllInvariantsSettings(), model);
	}

}
