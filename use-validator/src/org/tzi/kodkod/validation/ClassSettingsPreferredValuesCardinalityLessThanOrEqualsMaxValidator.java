package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;

/**
 * Validator for each configuration aspect regarding one class representing a
 * validity rule.
 * 
 * This is violated if for at least one class the preferred values contain more
 * elements than the maximum number of objects specifies.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If the minimum and maximum numbers of objects is less than $0$, set both
 * to 0.</li>
 * <li>Set maximum number objects to size of preferred object names.</li>
 * <li>Remove the last preferred objects names so that the size is equal to the
 * maximum number of objects.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class ClassSettingsPreferredValuesCardinalityLessThanOrEqualsMaxValidator
		extends AbstractClassSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(ClassSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final Set<String> instanceNames = config.getInstanceNames();
		final int instanceNamesCardinality = instanceNames.size();
		final int minimum = config.getLowerBound();
		final int maximum = config.getUpperBound();
		final String className = config.getCls().name();
		if (instanceNamesCardinality > maximum) {
			return new ValidityRuleViolence[] { new ValidityRuleViolence(
					UIElements.MaxShouldBeGreaterThanOrEqualPreferredValuesCardinality(className + PropertyEntry.objMax,
							className, String.valueOf(maximum), String.valueOf(instanceNamesCardinality)),
					new AbstractFix[] {
							new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
									UIElements.SetToValue(className + PropertyEntry.objMax,
											String.valueOf(instanceNamesCardinality)),
									true, minimum, instanceNamesCardinality, config),
							new SetInstanceSettingsInstanceNamesFix(config.getSettingsConfiguration(), model,
									UIElements.RemoveLastValues(className, instanceNamesCardinality - maximum), true,
									new HashSet<>(new ArrayList<String>(instanceNames).subList(0, maximum)),
									config) }) };
		}
		return new ValidityRuleViolence[0];
	}

}
