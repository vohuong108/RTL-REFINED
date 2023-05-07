package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;

/**
 * Validator for each configuration aspect regarding one class representing a
 * validity rule.
 * 
 * This is violated if for at least one class the minimum number of objects is
 * not less than or equal the maximum number of objects.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set minimum number of objects to value of maximum number of objects.</li>
 * <li>Set maximum number of objects to value of minimum number of objects.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class ClassSettingsMinLessThanOrEqualMaxValidator extends AbstractClassSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(ClassSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final int minimum = config.getLowerBound();
		final int maximum = config.getUpperBound();
		final String className = config.getCls().name();
		if (minimum > maximum) {
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(
							UIElements.AttributeValueShouldBeLessThanOrEqual(className + PropertyEntry.objMin,
									className + PropertyEntry.objMax, String.valueOf(minimum), String.valueOf(maximum)),
							new AbstractFix[] {
									new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
											UIElements.SetToValue(className + PropertyEntry.objMin,
													String.valueOf(maximum)),
											true, maximum, maximum, config),
									new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
											UIElements.SetToValue(className + PropertyEntry.objMax,
													String.valueOf(minimum)),
											true, minimum, minimum, config) }) };
		}
		return new ValidityRuleViolence[0];
	}

}
