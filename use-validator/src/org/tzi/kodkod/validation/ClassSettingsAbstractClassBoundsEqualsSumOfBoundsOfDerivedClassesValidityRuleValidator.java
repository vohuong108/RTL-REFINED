package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;

/**
 * Validator for each configuration aspect regarding one class representing a
 * validity rule.
 * 
 * This is violated if for at least one abstract class the minimum number of
 * objects is not equal to the sum of the minimum numbers of objects of directly
 * derived classes or the maximum number of objects is not equal to the sum of
 * the maximum numbers of objects of directly derived classes.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If the minimum number of objects is greater than the sum of the minimum
 * numbers of objects of the derived classes and the maximum number of objects
 * is less than the sum of the maximum numbers of objects of the derived classed
 * set both to the corresponding sum.</li>
 * <li>If the minimum number of objects is greater than the sum of the minimum
 * numbers of objects of the derived classes set the minimum number of objects
 * to the sum.</li>
 * <li>If the maximum number of objects is less than the sum of the maximum
 * numbers of objects of the derived classed set the maximum number of objects
 * to the sum.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class ClassSettingsAbstractClassBoundsEqualsSumOfBoundsOfDerivedClassesValidityRuleValidator
		extends AbstractClassSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(ClassSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		if (config.getCls().isAbstract()) {
			final int minimum = config.getLowerBound();
			final int maximum = config.getUpperBound();
			final String className = config.getCls().name();
			int sumOfMinimumsOfDerivedClasses = 0;
			int sumOfMaximumsOfDerivedClasses = 0;
			final Collection<IClass> childs = config.getCls().children();
			final String[] derivedClassesNames = new String[childs.size()];
			int i = -1;
			for (IClass child : config.getCls().children()) {
				i++;
				derivedClassesNames[i] = child.name();
				final ClassSettings childConfig = config.getSettingsConfiguration().getClassSettings(child);
				sumOfMinimumsOfDerivedClasses += childConfig.getLowerBound();
				sumOfMaximumsOfDerivedClasses += childConfig.getUpperBound();
			}
			List<AbstractFix> fixes = new ArrayList<AbstractFix>();
			if (minimum > sumOfMinimumsOfDerivedClasses && maximum < sumOfMaximumsOfDerivedClasses) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + PropertyEntry.objMin, className + PropertyEntry.objMax,
								String.valueOf(sumOfMinimumsOfDerivedClasses),
								String.valueOf(sumOfMaximumsOfDerivedClasses)),
						true, sumOfMinimumsOfDerivedClasses, sumOfMaximumsOfDerivedClasses, config));
			}
			if (minimum > sumOfMinimumsOfDerivedClasses) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + PropertyEntry.objMin,
								String.valueOf(sumOfMinimumsOfDerivedClasses)),
						maximum >= sumOfMaximumsOfDerivedClasses, sumOfMinimumsOfDerivedClasses, maximum, config));
			}
			if (maximum < sumOfMaximumsOfDerivedClasses) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + PropertyEntry.objMax,
								String.valueOf(sumOfMaximumsOfDerivedClasses)),
						minimum <= sumOfMinimumsOfDerivedClasses, minimum, sumOfMaximumsOfDerivedClasses, config));
			}
			if (!fixes.isEmpty()) {
				return new ValidityRuleViolence[] { new ValidityRuleViolence(
						UIElements
								.AbstractClassAttributeValuesShouldBeGreaterThanOrEqualSumOfDerivedClassAttributeValues(
										className + PropertyEntry.objMin, className + PropertyEntry.objMax,
										String.valueOf(minimum), String.valueOf(maximum),
										String.valueOf(sumOfMinimumsOfDerivedClasses),
										String.valueOf(sumOfMaximumsOfDerivedClasses), derivedClassesNames),
						fixes.toArray(new AbstractFix[fixes.size()])) };
			}
		}
		return new ValidityRuleViolence[0];
	}

}
