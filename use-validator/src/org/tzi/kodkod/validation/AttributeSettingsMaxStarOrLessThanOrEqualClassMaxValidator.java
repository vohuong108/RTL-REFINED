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
 * This is violated if for at least one attribute the maximum is not -1 and not
 * less than or equal the number of objects of the owning class.
 * 
 * Provided on invalidity: If the minimum number of defined attributes is less
 * than -1:
 * <ul>
 * <li>Set the maximum number of defined attributes to 0.</li>
 * <li>Set the maximum number of defined attributes to the maximum number of
 * objects of the owning class.</li>
 * <li>Set the maximum number of defined attributes to -1.</li>
 * <li>Set the maximum number of objects of the owning class to the maximum
 * number of defined attributes.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AttributeSettingsMaxStarOrLessThanOrEqualClassMaxValidator
		extends AbstractAttributeSettingsValidityRuleValidator implements IClassSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(ClassSettings parentConfig, AttributeSettings config, IModel model) {
		if (parentConfig == null || config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final int minimum = config.getLowerBound();
		final int maximum = config.getUpperBound();
		final int classMax = parentConfig.getUpperBound();
		final String className = parentConfig.getCls().name();
		final String attributeName = config.getAttribute().name();

		List<AbstractFix> fixes = new ArrayList<AbstractFix>();
		if (maximum > classMax) {
			final int newMaxs[] = { 0, classMax, -1 };
			for (int newMax : newMaxs) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + "_" + attributeName + PropertyEntry.attributeDefValuesMax,
								String.valueOf(newMax)),
						true, minimum, newMax, config));
			}
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToValue(className + PropertyEntry.objMax, String.valueOf(maximum)), true,
					parentConfig.getLowerBound(), maximum, parentConfig));
		}
		if (!fixes.isEmpty()) {
			return new ValidityRuleViolence[] { new ValidityRuleViolence(
					UIElements.AttributeValueShouldBeLessThanOrEqual(
							className + "_" + attributeName + PropertyEntry.attributeDefValuesMax,
							className + PropertyEntry.objMax, String.valueOf(maximum), String.valueOf(classMax)),
					fixes.toArray(new AbstractFix[fixes.size()])) };
		}

		return new ValidityRuleViolence[0];
	}

}
