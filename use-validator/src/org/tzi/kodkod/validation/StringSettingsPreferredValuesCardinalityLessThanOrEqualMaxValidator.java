package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.StringSettings;

/**
 * Validator for the configuration aspect regarding the type string representing
 * a validity rule.
 * 
 * This is violated if string type settings are enabled and the preferred values
 * contain more elements than the maximum number of string values specifies.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set maximum number of different string values to size of preferred string
 * values.</li>
 * <li>Remove the last preferred string values so that the size is equal to the
 * maximum number of different string values.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class StringSettingsPreferredValuesCardinalityLessThanOrEqualMaxValidator
		extends AbstractStringSettingsValidityRuleValidator {

	@Override
	ValidityRuleViolence[] getViolations(StringSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		if (config.isEnabled()) {
			final Set<String> instanceNames = config.getInstanceNames();
			final int instanceNamesCardinality = instanceNames.size();
			final int minimum = config.getLowerBound();
			final int maximum = config.getUpperBound();
			if (instanceNamesCardinality > maximum) {
				return new ValidityRuleViolence[] { new ValidityRuleViolence(
						UIElements.MaxShouldBeGreaterThanOrEqualPreferredValuesCardinality(
								TypeConstants.STRING + PropertyEntry.stringValuesMax, TypeConstants.STRING,
								String.valueOf(maximum), String.valueOf(instanceNamesCardinality)),
						new AbstractFix[] {
								new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
										UIElements.SetToValue(TypeConstants.STRING + PropertyEntry.stringValuesMax,
												String.valueOf(instanceNamesCardinality)),
										true, minimum, instanceNamesCardinality, config),
								new SetInstanceSettingsInstanceNamesFix(config.getSettingsConfiguration(), model,
										UIElements.RemoveLastValues(TypeConstants.STRING,
												instanceNamesCardinality - maximum),
										true, new HashSet<>(new ArrayList<String>(instanceNames).subList(0, maximum)),
										config) }) };
			}
		}
		return new ValidityRuleViolence[0];
	}

}
