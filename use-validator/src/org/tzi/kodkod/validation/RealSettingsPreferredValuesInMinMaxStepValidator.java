package org.tzi.kodkod.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;

/**
 * Validator for the configuration aspect regarding the type real representing a
 * validity rule.
 * 
 * This is violated if real type settings are enabled and not all preferred real
 * values are contained in the domain defined by minimum and maximum real
 * numbers and the real step value.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set minimum real value to lowest value contained in preferred real values
 * if this value is lower than the original minimum real value.</li>
 * <li>Remove all values from the preferred real values that are lower than the
 * minimum real value.</li>
 * <li>Set maximum real value to highest value contained in preferred real
 * values if this value is higher than the original maximum real value.</li>
 * <li>Remove all values from the preferred real values that are higher than the
 * maximum real value.</li>
 * <li>Remove all values from the preferred real values that are greater than or
 * equal to the minimum real value and less than or equal to the maximum real
 * value but are not contained in the domain represented by minimum and maximum
 * real value and the real step value.</li>
 * <li>Remove all values from the preferred real values that are not contained
 * in the domain represented by minimum and maximum real value and the real step
 * value.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class RealSettingsPreferredValuesInMinMaxStepValidator extends AbstractRealSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(RealSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		if (config.isEnabled()) {
			final BigDecimal step = new BigDecimal("" + config.getStep());
			final BigDecimal minimum = new BigDecimal("" + config.getMinimum());
			final BigDecimal maximum = new BigDecimal("" + config.getMaximum());
			Set<Double> valuesTooLow = new HashSet<Double>();
			Set<Double> valuesTooHigh = new HashSet<Double>();
			Set<Double> valuesNotReachable = new HashSet<Double>();
			final Set<Double> values = this.getValues(config);
			for (Double value : values) {
				final BigDecimal v = new BigDecimal("" + value);
				if (minimum.compareTo(v) > 0) {
					valuesTooLow.add(value);
				} else if (maximum.compareTo(v) < 0) {
					valuesTooHigh.add(value);
				} else if (minimum.compareTo(v) != 0 && maximum.compareTo(v) != 0) {
					BigDecimal nextLowerMax = minimum;
					while (nextLowerMax.compareTo(v) < 0) {
						nextLowerMax = nextLowerMax.add(step);
					}
					if (nextLowerMax.compareTo(v) != 0) {
						valuesNotReachable.add(value);
					}
				}
			}
			List<AbstractFix> fixes = new ArrayList<AbstractFix>();
			final boolean valuesTooLowExist = valuesTooLow.size() != 0;
			final boolean valuesTooHighExist = valuesTooHigh.size() != 0;
			final boolean valuesNotReachableExist = valuesNotReachable.size() != 0;
			if (valuesTooLowExist || valuesTooHighExist || valuesNotReachableExist) {
				if (valuesTooLowExist) {
					final boolean removingTooLowValuesFixes = !valuesTooHighExist && !valuesNotReachableExist;
					final Set<Double> newValues = new HashSet<>();
					newValues.addAll(values);
					newValues.removeAll(valuesTooLow);
					final Set<String> invalidValues = new HashSet<>();
					for (Double valueTooLow : valuesTooLow) {
						invalidValues.add(String.valueOf(valueTooLow));
					}
					fixes.add(new SetRealSettingsValuesFix(config.getSettingsConfiguration(), model,
							UIElements.RemoveValuesThatAreLessThan(TypeConstants.REAL, invalidValues,
									TypeConstants.REAL + PropertyEntry.realValuesMin,
									String.valueOf(minimum.doubleValue())),
							removingTooLowValuesFixes, config, newValues));
					BigDecimal newMinimum = minimum;
					for (Double value : valuesTooLow) {
						final BigDecimal v = new BigDecimal("" + value);
						if (newMinimum.compareTo(v) > 0) {
							newMinimum = v;
						}
					}
					fixes.add(new SetRealSettingsMinMaxFix(config.getSettingsConfiguration(), model,
							UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realValuesMin,
									String.valueOf(newMinimum.doubleValue())),
							removingTooLowValuesFixes, newMinimum.doubleValue(), config.getMaximum()));
				}
				if (valuesTooHighExist) {
					final boolean removingTooHighValuesFixes = !valuesTooLowExist && !valuesNotReachableExist;
					final Set<Double> newValues = new HashSet<>();
					newValues.addAll(values);
					newValues.removeAll(valuesTooHigh);
					final Set<String> invalidValues = new HashSet<>();
					for (Double valueTooHigh : valuesTooHigh) {
						invalidValues.add(String.valueOf(valueTooHigh));
					}
					fixes.add(new SetRealSettingsValuesFix(config.getSettingsConfiguration(), model,
							UIElements.RemoveValuesThatAreGreaterThan(TypeConstants.REAL, invalidValues,
									TypeConstants.REAL + PropertyEntry.realValuesMax,
									String.valueOf(minimum.doubleValue())),
							removingTooHighValuesFixes, config, newValues));
					BigDecimal newMaximum = maximum;
					for (Double value : valuesTooHigh) {
						final BigDecimal v = new BigDecimal("" + value);
						if (newMaximum.compareTo(v) < 0) {
							newMaximum = v;
						}
					}
					fixes.add(new SetRealSettingsMinMaxFix(config.getSettingsConfiguration(), model,
							UIElements.SetToValue(TypeConstants.REAL + PropertyEntry.realValuesMax,
									String.valueOf(newMaximum.doubleValue())),
							removingTooHighValuesFixes, config.getMinimum(), newMaximum.doubleValue()));
				}
				if (valuesNotReachableExist) {
					final boolean removingNotReachableFixes = !valuesTooLowExist && !valuesTooHighExist;
					final Set<Double> newValues = new HashSet<>();
					newValues.addAll(values);
					newValues.removeAll(valuesNotReachable);
					final Set<String> invalidValues = new HashSet<>();
					for (Double valueNotReachable : valuesNotReachable) {
						invalidValues.add(String.valueOf(valueNotReachable));
					}
					fixes.add(new SetRealSettingsValuesFix(config.getSettingsConfiguration(), model,
							UIElements.RemoveValuesThatAreUnreachable(TypeConstants.REAL, invalidValues),
							removingNotReachableFixes, config, newValues));
				}
				if (valuesTooLowExist && valuesTooHighExist || valuesTooLowExist && valuesNotReachableExist
						|| valuesTooHighExist && valuesNotReachableExist) {
					final Set<Double> newValues = new HashSet<>();
					newValues.addAll(values);
					final Set<String> invalidValues = new HashSet<>();
					for (Double valueTooHigh : valuesTooHigh) {
						invalidValues.add(String.valueOf(valueTooHigh));
					}
					if (valuesTooLowExist) {
						newValues.removeAll(valuesTooLow);
						for (Double valueTooLow : valuesTooLow) {
							invalidValues.add(String.valueOf(valueTooLow));
						}
					}
					if (valuesTooHighExist) {
						newValues.removeAll(valuesTooHigh);
						for (Double valueTooHigh : valuesTooHigh) {
							invalidValues.add(String.valueOf(valueTooHigh));
						}
					}
					if (valuesNotReachableExist) {
						newValues.removeAll(valuesNotReachable);
						for (Double valueNotReachable : valuesNotReachable) {
							invalidValues.add(String.valueOf(valueNotReachable));
						}
					}
					fixes.add(new SetRealSettingsValuesFix(config.getSettingsConfiguration(), model,
							UIElements.RealSettingsPreferredValuesRemoveAllInvalidValues(invalidValues), true, config,
							newValues));
				}
			}
			if (!fixes.isEmpty()) {
				return new ValidityRuleViolence[] { new ValidityRuleViolence(
						UIElements.RealSettingsPreferredValuesInMinMaxStep(String.valueOf(minimum.doubleValue()),
								String.valueOf(maximum.doubleValue()), String.valueOf(step.doubleValue())),
						fixes.toArray(new AbstractFix[fixes.size()])) };
			}
		}
		return new ValidityRuleViolence[0];

	}

}
