package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.List;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.StringSettings;

/**
 * Validator for the configuration aspect regarding the type string representing
 * a validity rule.
 * 
 * This is violated if string type settings are enabled and the minimum and
 * maximum number of string values is less than or equal 0.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If minimum and maximum numbers of different string values are less than
 * 0, set both to 0.</li>
 * <li>If minimum number of different string values is less than 0, set it to
 * 0.</li>
 * <li>If maximum number of different string values is less than 0, set it to
 * 0.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class StringSettingsMinMaxGreaterThanOrEqualZeroValidator extends AbstractStringSettingsValidityRuleValidator {

	@Override
	ValidityRuleViolence[] getViolations(StringSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		if (config.isEnabled()) {
			final int minimum = config.getLowerBound();
			final int maximum = config.getUpperBound();
			List<AbstractFix> fixes = new ArrayList<AbstractFix>();
			if (minimum < 0 && maximum < 0) {
				fixes.add(
						new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
								UIElements.SetToValue(TypeConstants.STRING + PropertyEntry.stringValuesMin,
										TypeConstants.STRING + PropertyEntry.stringValuesMax, "0"),
								true, 0, 0, config));
			}
			if (minimum < 0) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(TypeConstants.STRING + PropertyEntry.stringValuesMin, "0"), maximum >= 0,
						0, maximum, config));
			}
			if (maximum < 0) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(TypeConstants.STRING + PropertyEntry.stringValuesMax, "0"), minimum >= 0,
						minimum, 0, config));
			}
			if (!fixes.isEmpty()) {
				return new ValidityRuleViolence[] { new ValidityRuleViolence(UIElements
						.AttributeValueShouldBeGreaterThanOrEqual(TypeConstants.STRING + PropertyEntry.stringValuesMin,
								TypeConstants.STRING + PropertyEntry.stringValuesMax, String.valueOf(minimum),
								String.valueOf(maximum), "0"),
						fixes.toArray(new AbstractFix[fixes.size()])) };
			}
		}
		return new ValidityRuleViolence[0];
	}

}
