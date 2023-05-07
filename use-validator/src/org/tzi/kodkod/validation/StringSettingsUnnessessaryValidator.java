package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.StringSettings;

/**
 * Validator for the configuration aspect regarding the type string representing
 * a validity rule.
 * 
 * This is violated when string type settings are enabled but should be
 * disabled. They should be disabled if there is not at least one class that is
 * configured to potentially have instances and that has at least one attribute
 * of type sting that is configured to potentially be defined.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Disable string type settings.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class StringSettingsUnnessessaryValidator extends AbstractStringSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(StringSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		Map<ClassSettings, Set<AttributeSettings>> attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances = this
				.getClassSettingsWithInstancesAndStringAttributeWithMaxGreaterThanZero(
						config.getSettingsConfiguration(), model);
		if (config.isEnabled() && attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances.size() == 0) {
			List<AbstractFix> fixes = new ArrayList<>();
			fixes.add(new SetStringSettingsEnabledFix(config.getSettingsConfiguration(), model,
					UIElements.Disable(TypeConstants.STRING), true, config, false));

			Set<ClassSettings> classSettingsWithoutInstancesAndIntegerAttribute = this
					.getClassSettingsWithoutInstancesAndStringAttribute(config.getSettingsConfiguration(), model);

			Map<ClassSettings, Set<AttributeSettings>> attributeSettingsWithMaxZeroForClassSettings = this
					.getClassSettingsWithStringAttributeWithMaxZero(config.getSettingsConfiguration(), model);

			int classSettingsFixesAmount = classSettingsWithoutInstancesAndIntegerAttribute.size();
			int attributeSettingsFixesAmount = 0;
			for (Entry<ClassSettings, Set<AttributeSettings>> entry : attributeSettingsWithMaxZeroForClassSettings
					.entrySet()) {
				attributeSettingsFixesAmount += entry.getValue().size();
			}

			for (ClassSettings classSettings : classSettingsWithoutInstancesAndIntegerAttribute) {
				IClass classClass = classSettings.getCls();
				String className = classClass.name();
				final int newClassMax = 1;
				fixes.add(new SetClassSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + PropertyEntry.objMax, String.valueOf(newClassMax)),
						classSettingsFixesAmount == 1 && attributeSettingsFixesAmount == 0, classSettings,
						classSettings.getLowerBound(), newClassMax));
			}

			for (Entry<ClassSettings, Set<AttributeSettings>> entry : attributeSettingsWithMaxZeroForClassSettings
					.entrySet()) {
				IClass classClass = entry.getKey().getCls();
				String className = classClass.name();
				for (AttributeSettings attributeSettings : entry.getValue()) {
					final int newAttributeMax = 1;
					IAttribute attributeAttribute = attributeSettings.getAttribute();
					String attributeName = attributeAttribute.name();
					fixes.add(new SetAttributeSettingsMinMaxFix(config.getSettingsConfiguration(), model,
							UIElements.SetToValue(className + "_" + attributeName + PropertyEntry.attributeDefValuesMax,
									String.valueOf(newAttributeMax)),
							classSettingsFixesAmount == 0 && attributeSettingsFixesAmount == 1, attributeSettings,
							attributeSettings.getLowerBound(), newAttributeMax));
				}
			}

			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(UIElements.TypeSettingsUnnessessary(TypeConstants.STRING),
							fixes.toArray(new AbstractFix[fixes.size()])) };
		}
		return new ValidityRuleViolence[0];
	}

}
