package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.StringSettings;

/**
 * Validator for the configuration aspect regarding the type string representing
 * a validity rule.
 * 
 * This is violated if string type settings are enabled and the minimum number
 * of string values is not less than or equal the maximum number of string
 * values.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set minimum number of different string values to value of maximum number
 * of different string values.</li>
 * <li>Set maximum number of different string values to value of minimum number
 * of different string values.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class StringSettingsMinLessThanOrEqualMaxValidator extends AbstractStringSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(final StringSettings config, final IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		if (config.isEnabled()) {
			final int minimum = config.getLowerBound();
			final int maximum = config.getUpperBound();
			if (minimum > maximum) {
				return new ValidityRuleViolence[] {
						new ValidityRuleViolence(
								UIElements.AttributeValueShouldBeLessThanOrEqual(
										TypeConstants.STRING + PropertyEntry.stringValuesMin,
										TypeConstants.STRING
												+ PropertyEntry.stringValuesMax,
										String.valueOf(minimum), String.valueOf(maximum)),
								new AbstractFix[] {
										new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
												UIElements.SetToValue(
														TypeConstants.STRING + PropertyEntry.stringValuesMin,
														String.valueOf(maximum)),
												true, maximum, maximum, config),
										new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
												UIElements.SetToValue(
														TypeConstants.STRING + PropertyEntry.stringValuesMax,
														String.valueOf(minimum)),
												true, minimum, minimum, config) }) };
			}
		}
		return new ValidityRuleViolence[0];
	}

}
