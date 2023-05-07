package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.AssociationSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator that validates all association configuration aspects.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractAssociationSettingsValidityRuleValidator
		extends AbstractListAttributeValidityRuleValidator<AssociationSettings>
		implements IAssociationSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model,
			List<Expression> invariants) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		return getViolations(config.getAllAssociationSettings(), model);
	}

}
