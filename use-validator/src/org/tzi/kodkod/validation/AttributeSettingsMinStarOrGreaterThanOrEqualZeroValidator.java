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
 * attributes is not greater than or equal to -1.
 * 
 * Provided on invalidity: If the minimum number of defined attributes is less
 * than -1:
 * <ul>
 * <li>Set it to -1.</li>
 * <li>Set it to 0.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AttributeSettingsMinStarOrGreaterThanOrEqualZeroValidator
		extends AbstractAttributeSettingsValidityRuleValidator implements IClassSettingsValidityRuleValidator {

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
		if (minimum < -1) {
			final int newMins[] = { -1, 0 };
			for (int newMin : newMins) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + "_" + attributeName + PropertyEntry.attributeDefValuesMin,
								String.valueOf(newMin)),
						true, newMin, maximum, config));
			}
		}
		if (!fixes.isEmpty()) {
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(UIElements.AttributeValueShouldBeGreaterThanOrEqual(
							className + "_" + attributeName + PropertyEntry.attributeDefValuesMin,
							String.valueOf(minimum), "-1"), fixes.toArray(new AbstractFix[fixes.size()])) };
		}
		return new ValidityRuleViolence[0];
	}

}
