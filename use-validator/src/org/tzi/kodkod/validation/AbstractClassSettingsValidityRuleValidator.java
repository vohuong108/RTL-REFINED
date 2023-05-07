package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator that validates all class configuration aspects.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractClassSettingsValidityRuleValidator extends
		AbstractListAttributeValidityRuleValidator<ClassSettings> implements IClassSettingsValidityRuleValidator {

	@Override
	ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model, List<Expression> invariants) {
		return getViolations(config.getAllClassesSettings(), model);
	}

}
