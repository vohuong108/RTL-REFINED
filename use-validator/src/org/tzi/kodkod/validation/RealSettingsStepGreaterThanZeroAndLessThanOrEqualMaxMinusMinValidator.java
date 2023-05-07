package org.tzi.kodkod.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;

/**
 * Validator for the configuration aspect regarding the type real representing a
 * validity rule.
 * 
 * This is violated if real type settings are enabled and the real step value is
 * not less than or equal the difference of maximum and minimum real number.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If real step value is less than 0, set it to the difference of maximum
 * and minimum real values.</li>
 * <li>If real step value is greater than the difference of maximum and minimum
 * real values, set the maximum real value to the sum of the difference and the
 * minimum real value.</li>
 * <li>If real step value is greater than the difference of maximum and minimum
 * real values, set the minimum real value to the difference of the maximum real
 * value and the other difference.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class RealSettingsStepGreaterThanZeroAndLessThanOrEqualMaxMinusMinValidator
		extends AbstractRealSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(RealSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		if (config.isEnabled()) {
			final BigDecimal step = new BigDecimal("" + config.getStep());
			final BigDecimal minimum = new BigDecimal("" + config.getMinimum());
			final BigDecimal maximum = new BigDecimal("" + config.getMaximum());
			final BigDecimal maximumStep = maximum.subtract(minimum);
			List<AbstractFix> fixes = new ArrayList<AbstractFix>();
			if (step.compareTo(new BigDecimal("0")) < 0) {
				fixes.add(
						new SetRealSettingsStepFix(config.getSettingsConfiguration(), model,
								UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realStep,
										String.valueOf(maximumStep.doubleValue())),
								true, maximumStep.doubleValue(), config));
			}
			if (step.compareTo(maximumStep) > 0) {
				final double newMax = minimum.add(maximumStep).doubleValue();
				fixes.add(new SetRealSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realValuesMax, String.valueOf(newMax)),
						true, minimum.doubleValue(), newMax));
				final double newMin = maximum.subtract(maximumStep).doubleValue();
				fixes.add(new SetRealSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realValuesMin, String.valueOf(newMin)),
						true, newMin, maximum.doubleValue()));
			}
			if (!fixes.isEmpty()) {
				return new ValidityRuleViolence[] { new ValidityRuleViolence(
						UIElements.RealSettingsStepGreaterThanZeroAndLessThanOrEqualMaxMinusMin(String.valueOf(minimum),
								String.valueOf(maximum), String.valueOf(step), String.valueOf(maximumStep)),
						fixes.toArray(new AbstractFix[fixes.size()])) };
			}
		}
		return new ValidityRuleViolence[0];
	}

}
