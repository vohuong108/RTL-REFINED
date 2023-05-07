package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.List;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;

/**
 * Validator for each configuration aspect regarding one attribute representing
 * a validity rule.
 * 
 * This is violated if for at least one attribute the minimum number of defined
 * attributes is not -1 and is also not less than or equal to the maximum number
 * of defined attributes.
 * 
 * Provided on invalidity: If the maximum number of defined attributes is not
 * than -1 and the minimum number of defined attributes is greater than the
 * maximum number of defined attributes:
 * <ul>
 * <li>Set the minimum number of defined attributes to 0.</li>
 * <li>Set the maximum number of defined attributes to -1.</li>
 * <li>Set the minimum number of defined attributes to the maximum number of
 * defined attributes.</li>
 * <li>Set the maximum number of defined attributes to the minimum number of
 * defined attributes.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AttributeSettingsMinLessThanOrEqualMaxValidator extends AbstractAttributeSettingsValidityRuleValidator
		implements IClassSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(ClassSettings parentConfig, AttributeSettings config, IModel model) {
		if (parentConfig == null || config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final int minimum = config.getLowerBound();
		final int maximum = config.getUpperBound();
		final String className = parentConfig.getCls().name();
		final String attributeName = config.getAttribute().name();

		List<AbstractFix> fixes = new ArrayList<AbstractFix>();
		if (minimum >= 0 && maximum >= 0 && minimum > maximum) {
			final int newMins[] = { -1, 0, maximum };
			for (int newMin : newMins) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + "_" + attributeName + PropertyEntry.attributeDefValuesMin,
								String.valueOf(newMin)),
						true, newMin, maximum, config));
			}

			final int newMaxs[] = { minimum, -1 };
			for (int newMax : newMaxs) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + "_" + attributeName + PropertyEntry.attributeDefValuesMax,
								String.valueOf(newMax)),
						true, minimum, newMax, config));
			}

		}
		if (!fixes.isEmpty()) {
			return new ValidityRuleViolence[] { new ValidityRuleViolence(
					UIElements.AttributeValueShouldBeLessThanOrEqualWhenBothGreaterThanOrEqual(
							className + "_" + attributeName + "_" + PropertyEntry.attributeDefValuesMin,
							className + "_" + attributeName + PropertyEntry.attributeDefValuesMax,
							String.valueOf(minimum), String.valueOf(maximum), "0"),
					fixes.toArray(new AbstractFix[fixes.size()])) };
		}
		return new ValidityRuleViolence[0];
	}

}
