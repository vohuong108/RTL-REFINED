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
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;

/**
 * Validator for the configuration aspect regarding the type real representing a
 * validity rule.
 * 
 * This is violated when real type settings are disabled but should be enabled.
 * They should be enabled if there is at least one class that is configured to
 * potentially have instances and that has at least one attribute of type real
 * that is configured to potentially be defined.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Enable real type settings.</li>
 * <li>For each real attribute which maximum defined value greater than 0 and
 * whose classes maximum number of instances is not 0 set minimum and maximum
 * number of its owning class to 0.</li>
 * <li>For each real attribute which maximum defined value greater than 0 and
 * whose classes maximum number of instances is not 0 set minimum and maximum
 * number of defined attributes to 0.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class RealSettingsNessessaryValidator extends AbstractRealSettingsValidityRuleValidator {

	@Override
	ValidityRuleViolence[] getViolations(final RealSettings config, final IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		Map<ClassSettings, Set<AttributeSettings>> attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances = this
				.getClassSettingsWithInstancesAndRealAttributeWithMaxGreaterThanZero(config.getSettingsConfiguration(),
						model);
		if (!config.isEnabled() && attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances.size() > 0) {
			List<AbstractFix> fixes = new ArrayList<>();
			fixes.add(new SetRealSettingsEnabledFix(config.getSettingsConfiguration(), model,
					UIElements.Enable(TypeConstants.REAL), true, config, true));

			int classSettingsFixesAmount = 0;
			int attributeSettingsFixesAmount = 0;
			for (Entry<ClassSettings, Set<AttributeSettings>> attributeSettingsWithMaxGreaterThanZeroForAClassSettingsWithInstances : attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances
					.entrySet()) {
				classSettingsFixesAmount++;
				attributeSettingsFixesAmount += attributeSettingsWithMaxGreaterThanZeroForAClassSettingsWithInstances
						.getValue().size();
			}

			for (Entry<ClassSettings, Set<AttributeSettings>> attributeSettingsWithMaxGreaterThanZeroForAClassSettingsWithInstances : attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances
					.entrySet()) {
				final int newClassMin = 0;
				final int newClassMax = 0;
				ClassSettings classSettings = attributeSettingsWithMaxGreaterThanZeroForAClassSettingsWithInstances
						.getKey();
				IClass classClass = classSettings.getCls();
				String className = classClass.name();
				fixes.add(new SetClassSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(className + PropertyEntry.objMin, className + PropertyEntry.objMax,
								String.valueOf(newClassMin), String.valueOf(newClassMax)),
						classSettingsFixesAmount == 1, classSettings, newClassMin, newClassMax));
				for (AttributeSettings attributeSettings : attributeSettingsWithMaxGreaterThanZeroForAClassSettingsWithInstances
						.getValue()) {
					final int newAttributeMin = 0;
					final int newAttributeMax = 0;
					IAttribute attributeAttribute = attributeSettings.getAttribute();
					String attributeName = attributeAttribute.name();
					fixes.add(new SetAttributeSettingsMinMaxFix(config.getSettingsConfiguration(), model,
							UIElements.SetToValue(className + "_" + attributeName + PropertyEntry.attributeDefValuesMin,
									className + "_" + attributeName + PropertyEntry.attributeDefValuesMax,
									String.valueOf(newAttributeMin), String.valueOf(newAttributeMax)),
							attributeSettingsFixesAmount == 1, attributeSettings, newAttributeMin, newAttributeMax));
				}
			}
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(UIElements.TypeSettingsNessessary(TypeConstants.REAL),
							fixes.toArray(new AbstractFix[fixes.size()])) };
		}
		return new ValidityRuleViolence[0];
	}

}
