package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.IntegerSettings;

/**
 * Validator for the configuration aspect regarding the type integer
 * representing a validity rule.
 * 
 * This is violated if integer type settings are enabled and the minimum integer
 * number is not less than or equal the maximum integer number.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set minimum integer value to value of maximum integer value.</li>
 * <li>Set maximum integer value to value of minimum integer value.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class IntegerSettingsMinLessThanOrEqualMaxValidator extends AbstractIntegerSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(final IntegerSettings config, final IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final int minimum = config.getMinimum();
		final int maximum = config.getMaximum();
		if (config.isEnabled() && minimum > maximum) {
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(
							UIElements.AttributeValueShouldBeLessThanOrEqual(
									TypeConstants.INTEGER + PropertyEntry.integerValuesMin,
									TypeConstants.INTEGER
											+ PropertyEntry.integerValuesMax,
									String.valueOf(minimum), String.valueOf(maximum)),
							new AbstractFix[] {
									new SetIntegerSettingsMinMaxFix(config.getSettingsConfiguration(), model,
											UIElements.SetToValue(
													TypeConstants.INTEGER + PropertyEntry.integerValuesMin,
													String.valueOf(maximum)),
											true, maximum, maximum),
									new SetIntegerSettingsMinMaxFix(config.getSettingsConfiguration(), model,
											UIElements.SetToValue(
													TypeConstants.INTEGER + PropertyEntry.integerValuesMax,
													String.valueOf(minimum)),
											true, minimum, minimum) }) };
		}
		return new ValidityRuleViolence[0];
	}

}
