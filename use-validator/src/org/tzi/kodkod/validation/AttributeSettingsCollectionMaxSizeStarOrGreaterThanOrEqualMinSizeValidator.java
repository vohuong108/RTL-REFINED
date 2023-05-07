package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;

/**
 * Validator for each configuration aspect regarding one attribute representing
 * a validity rule.
 * 
 * This is violated if for at least one attribute of collection type the maximum
 * number of contained elements in defined attributes is not -1 and not greater
 * than or equal to the minimum number of contained elements in defined
 * attributes.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set the maximum number of contained elements to the minimum number of
 * contained elements.</li>
 * <li>Set the maximum number of contained elements to -1.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AttributeSettingsCollectionMaxSizeStarOrGreaterThanOrEqualMinSizeValidator
		extends AbstractAttributeSettingsValidityRuleValidator implements IClassSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(ClassSettings parentConfig, AttributeSettings config, IModel model) {
		if (parentConfig == null || config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final boolean isCollection = this.isCollection(config);
		final int collectionSizeMin = config.getCollectionSizeMin();
		final int collectionSizeMax = config.getCollectionSizeMax();
		if (isCollection && collectionSizeMax != -1 && collectionSizeMax < collectionSizeMin) {
			final String className = parentConfig.getCls().name();
			final String attributeName = config.getAttribute().name();
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(
							UIElements.AttributeValueShouldBeNumberOrGreaterThanOrEqual(
									className + "_" + attributeName + PropertyEntry.attributeColSizeMax,
									className
											+ "_" + attributeName + PropertyEntry.attributeColSizeMin,
									String.valueOf(collectionSizeMax), String.valueOf(collectionSizeMin), "-1"),
							new AbstractFix[] {
									new SetAttributeSettingsCollectionSizeMaxFix(config.getSettingsConfiguration(),
											model,
											UIElements.SetToValue(
													className + "_" + attributeName + PropertyEntry.attributeColSizeMax,
													String.valueOf(collectionSizeMin)),
											true, collectionSizeMin, config),
									new SetAttributeSettingsCollectionSizeMaxFix(config.getSettingsConfiguration(),
											model,
											UIElements.SetToValue(
													className + "_" + attributeName + PropertyEntry.attributeColSizeMax,
													"-1"),
											true, -1, config) }) };
		}
		return new ValidityRuleViolence[0];
	}

}
