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
 * This is violated if real type settings are enabled and the modulo of
 * difference of maximum and minimum real and the real step value is not 0. This
 * means the maximum real number must be reachable from the minimum real number
 * in steps of the real step value.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If there exists a lower value than the maximum real value for that modulo
 * of difference of this value and minimum real and the real step value is 0 and
 * this value is greater than the minimum real value, set the maximum real value
 * to this value.</li>
 * <li>Set the maximum real value to the next higher value for that modulo of
 * difference of this value and minimum real and the real step value is 0.</li>
 * <li>Set the real step value to the difference of the maximum and minimum real
 * value.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class RealSettingsMaxReachableInStepFromMinValidator extends AbstractRealSettingsValidityRuleValidator {

	@Override
	ValidityRuleViolence[] getViolations(RealSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		if (config.isEnabled()) {
			final BigDecimal step = new BigDecimal("" + config.getStep());
			final BigDecimal minimum = new BigDecimal("" + config.getMinimum());
			final BigDecimal maximum = new BigDecimal("" + config.getMaximum());
			final BigDecimal maximumStep = maximum.subtract(minimum);
			if (step.compareTo(new BigDecimal("0")) < 0 || minimum.compareTo(maximum) > 1) {
				throw new UnsupportedOperationException();
			}
			BigDecimal nextLowerMax = minimum;
			while (nextLowerMax.compareTo(maximum) < 0) {
				nextLowerMax = nextLowerMax.add(step);
			}
			if (nextLowerMax.compareTo(maximum) > 0) {
				nextLowerMax = nextLowerMax.subtract(step);
			}
			boolean maxReachable = nextLowerMax.compareTo(maximum) == 0;
			final BigDecimal nextHigherMax = nextLowerMax.add(step);
			List<AbstractFix> fixes = new ArrayList<AbstractFix>();
			if (!maxReachable) {
				if (nextLowerMax.compareTo(new BigDecimal("" + minimum)) > 0) {
					fixes.add(new SetRealSettingsMinMaxFix(config.getSettingsConfiguration(), model,
							UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realValuesMax,
									String.valueOf(nextLowerMax.doubleValue())),
							true, minimum.doubleValue(), nextLowerMax.doubleValue()));
				}
				fixes.add(new SetRealSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realValuesMax,
								String.valueOf(nextHigherMax.doubleValue())),
						true, minimum.doubleValue(), nextHigherMax.doubleValue()));
				fixes.add(
						new SetRealSettingsStepFix(config.getSettingsConfiguration(), model,
								UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realStep,
										String.valueOf(maximumStep.doubleValue())),
								true, maximumStep.doubleValue(), config));
			}
			if (!fixes.isEmpty()) {
				return new ValidityRuleViolence[] { new ValidityRuleViolence(
						UIElements.RealSettingsMaxReachableInStepFromMin(String.valueOf(minimum),
								String.valueOf(maximum), String.valueOf(step)),
						fixes.toArray(new AbstractFix[fixes.size()])) };
			}
		}
		return new ValidityRuleViolence[0];
	}

}
