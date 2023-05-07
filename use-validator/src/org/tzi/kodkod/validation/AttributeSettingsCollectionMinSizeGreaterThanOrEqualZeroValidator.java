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
 * This is violated if for at least one attribute of collection type the minimum
 * number of contained elements in defined attributes is not greater than or
 * equal to 0.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set the minimum number of contained elements to 0.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AttributeSettingsCollectionMinSizeGreaterThanOrEqualZeroValidator
		extends AbstractAttributeSettingsValidityRuleValidator implements IClassSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(ClassSettings parentConfig, AttributeSettings config, IModel model) {
		if (parentConfig == null || config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final boolean isCollection = this.isCollection(config);
		final int collectionSizeMin = config.getCollectionSizeMin();
		if (isCollection && collectionSizeMin < 0) {
			final String className = parentConfig.getCls().name();
			final String attributeName = config.getAttribute().name();
			return new ValidityRuleViolence[] { new ValidityRuleViolence(
					UIElements.AttributeValueShouldBeGreaterThanOrEqual(
							className + "_" + attributeName + PropertyEntry.attributeColSizeMin,
							String.valueOf(collectionSizeMin), "0"),
					new AbstractFix[] {
							new SetAttributeSettingsCollectionSizeMinFix(config.getSettingsConfiguration(), model,
									UIElements.SetToValue(
											className + "_" + attributeName + PropertyEntry.attributeColSizeMin, "0"),
									true, 0, config) }) };
		}
		return new ValidityRuleViolence[0];
	}

}
