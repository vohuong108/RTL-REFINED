package org.tzi.kodkod.validation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator that validates all attribute configuration aspects.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractAttributeSettingsValidityRuleValidator
		extends AbstractListInListAttributeValidityRuleValidator<AttributeSettings, ClassSettings>
		implements IAttributeSettingsValidityRuleValidator {

	@Override
	ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model, List<Expression> invariants) {
		if (config == null) {
			throw new IllegalArgumentException();
		}
		List<ClassSettings> listOfClassSettings = config.getAllClassesSettings(); // never null
		Map<ClassSettings, Set<AttributeSettings>> attributeSettingsForClassSettings = new HashMap<ClassSettings, Set<AttributeSettings>>();
		for (ClassSettings classSettings : listOfClassSettings) {
			if (!classSettings.getCls().isAbstract()) {
				Map<IAttribute, AttributeSettings> attributeSettingsForAttributes = classSettings
						.getAttributeSettings();
				Collection<AttributeSettings> attributeSettings = attributeSettingsForAttributes.values();
				Set<AttributeSettings> directAttributeSettings = new HashSet<>();
				for (AttributeSettings potentialDirectAttributeSettings : attributeSettings) {
					if (potentialDirectAttributeSettings.getAttribute().owner() == classSettings.getCls()) {
						directAttributeSettings.add(potentialDirectAttributeSettings);
					}
				}
				attributeSettingsForClassSettings.put(classSettings, directAttributeSettings);
			}
		}
		return this.getViolations(attributeSettingsForClassSettings, model);
	}

}
