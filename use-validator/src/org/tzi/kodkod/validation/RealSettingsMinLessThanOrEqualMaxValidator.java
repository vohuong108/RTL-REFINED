package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;

/**
 * Validator for the configuration aspect regarding the type real representing a
 * validity rule.
 * 
 * This is violated if real type settings are enabled and the minimum real
 * number is not less than or equal the maximum real number.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set minimum real value to value of maximum real value.</li>
 * <li>Set maximum real value to value of minimum real value.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
public class RealSettingsMinLessThanOrEqualMaxValidator extends AbstractRealSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(final RealSettings config, final IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final double minimum = config.getMinimum();
		final double maximum = config.getMaximum();
		if (config.isEnabled() && minimum > maximum) {
			return new ValidityRuleViolence[] { new ValidityRuleViolence(
					UIElements.AttributeValueShouldBeLessThanOrEqual(TypeConstants.REAL + PropertyEntry.realValuesMin,
							TypeConstants.REAL + PropertyEntry.realValuesMax, String.valueOf(minimum),
							String.valueOf(maximum)),
					new AbstractFix[] {
							new SetRealSettingsMinMaxFix(config.getSettingsConfiguration(), model,
									UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realValuesMin,
											String.valueOf(maximum)),
									true, maximum, maximum),
							new SetRealSettingsMinMaxFix(config.getSettingsConfiguration(), model,
									UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realValuesMax,
											String.valueOf(minimum)),
									true, minimum, minimum) }) };
		}
		return new ValidityRuleViolence[0];
	}

}
