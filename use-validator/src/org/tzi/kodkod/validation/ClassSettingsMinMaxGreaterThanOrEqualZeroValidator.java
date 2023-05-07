package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.List;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;

/**
 * Validator for each configuration aspect regarding one class representing a
 * validity rule.
 * 
 * This is violated if for at least one class the minimum or maximum number of
 * objects is not greater than or equal 0.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If the minimum and maximum numbers of objects is less than $0$, set both
 * to 0.</li>
 * <li>If the minimum number of objects is less than $0$, set it to 0.</li>
 * <li>If the maximum number of objects is less than $0$, set it to 0.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class ClassSettingsMinMaxGreaterThanOrEqualZeroValidator extends AbstractClassSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(ClassSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final int minimum = config.getLowerBound();
		final int maximum = config.getUpperBound();
		final String className = config.getCls().name();
		List<AbstractFix> fixes = new ArrayList<AbstractFix>();
		if (minimum < 0 && maximum < 0) {
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToValue(className + PropertyEntry.objMin, className + PropertyEntry.objMax, "0"),
					true, 0, 0, config));
		}
		if (minimum < 0) {
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToValue(className + PropertyEntry.objMin, "0"), maximum >= 0, 0, maximum, config));
		}
		if (maximum < 0) {
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToValue(className + PropertyEntry.objMax, "0"), minimum >= 0, minimum, 0, config));
		}
		if (!fixes.isEmpty()) {
			return new ValidityRuleViolence[] { new ValidityRuleViolence(
					UIElements.AttributeValueShouldBeGreaterThanOrEqual(className + PropertyEntry.objMin,
							className + PropertyEntry.objMax, String.valueOf(minimum), String.valueOf(maximum), "0"),
					fixes.toArray(new AbstractFix[fixes.size()])) };
		}
		return new ValidityRuleViolence[0];
	}

}
