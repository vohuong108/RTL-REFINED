package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.IntegerSettings;

/**
 * Validator for the configuration aspect regarding the type integer
 * representing a validity rule.
 * 
 * This is violated if integer type settings are enabled and the not all
 * preferred integer values are contained in the domain defined by minimum and
 * maximum integer number.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Remove all values from the preferred integer values that are not
 * contained in the domain represented by minimum and maximum integer values.
 * </li>
 * <li>Set minimum integer value to lowest value contained in preferred integer
 * values if this value is lower than the original minimum integer value.</li>
 * <li>Set maximum integer value to highest value contained in preferred integer
 * values if this value is higher than the original maximum integer value.</li>
 * <li>If both foregoing fixes are provided, they are also provided as
 * bundle.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class IntegerSettingsPreferredValuesInMinMaxValidator extends AbstractIntegerSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(IntegerSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final int minimum = config.getMinimum();
		final int maximum = config.getMaximum();
		if (config.isEnabled()) {
			Set<Integer> toBeRemoved = new HashSet<Integer>();
			for (Integer bla : config.getValues()) {
				if (bla < minimum || bla > maximum) {
					toBeRemoved.add(bla);
				}
			}
			if (!toBeRemoved.isEmpty()) {
				Integer newMax = null;
				Integer newMin = null;
				for (Integer illegalValue : toBeRemoved) {
					if (newMax == null || illegalValue > newMax) {
						newMax = illegalValue;
					}
					if (newMin == null || illegalValue < newMin) {
						newMin = illegalValue;
					}
				}
				List<AbstractFix> fixes = new ArrayList<AbstractFix>();
				Set<String> invalidValues = new HashSet<String>();
				for (Integer integer : toBeRemoved) {
					invalidValues.add(String.valueOf(integer));
				}
				fixes.add(new RemoveIntegerSettingsPrefferedValuesFix(config.getSettingsConfiguration(), model,
						UIElements.RemoveAllInvalidValues(TypeConstants.INTEGER, invalidValues), true, toBeRemoved));
				boolean newMinNessessary = newMin < minimum;
				boolean newMaxNessessary = newMax > maximum;
				boolean newMinMaxNessessary = newMinNessessary && newMaxNessessary;
				if (newMinMaxNessessary) {
					fixes.add(new SetIntegerSettingsMinMaxFix(config.getSettingsConfiguration(), model,
							UIElements.SetToValue(TypeConstants.INTEGER + PropertyEntry.integerValuesMin,
									TypeConstants.INTEGER + PropertyEntry.integerValuesMax, String.valueOf(newMin),
									String.valueOf(newMax)),
							true, newMin.intValue(), newMax.intValue()));
				}
				if (newMinNessessary) {
					fixes.add(new SetIntegerSettingsMinMaxFix(config.getSettingsConfiguration(), model, UIElements
							.SetToValue(TypeConstants.INTEGER + PropertyEntry.integerValuesMin, String.valueOf(newMin)),
							!newMinMaxNessessary, newMin.intValue(), maximum));
				}
				if (newMaxNessessary) {
					fixes.add(new SetIntegerSettingsMinMaxFix(config.getSettingsConfiguration(), model, UIElements
							.SetToValue(TypeConstants.INTEGER + PropertyEntry.integerValuesMax, String.valueOf(newMax)),
							!newMinMaxNessessary, minimum, newMax.intValue()));
				}
				return new ValidityRuleViolence[] { new ValidityRuleViolence(
						UIElements.AttributeValuesNotValid(TypeConstants.INTEGER,
								TypeConstants.INTEGER + PropertyEntry.integerValuesMin,
								TypeConstants.INTEGER + PropertyEntry.integerValuesMax, String.valueOf(minimum),
								String.valueOf(maximum), invalidValues),
						fixes.toArray(new AbstractFix[fixes.size()])) };
			}
		}
		return new ValidityRuleViolence[0];
	}

}
