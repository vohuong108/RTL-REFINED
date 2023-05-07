package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.type.Type;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;

/**
 * Interface for validators that validate configuration aspects for attributes.
 * 
 * @author Jan Prien
 *
 */
interface IAttributeSettingsValidityRuleValidator {

	/**
	 * Extracts whether an association is of an collection type from a configuration
	 * aspect for an association.
	 * 
	 * @param attributeSettings
	 *            The configuration aspect for an association.
	 * @return Whether the association is of an collection type.
	 */
	default boolean isCollection(AttributeSettings attributeSettings) {
		if (attributeSettings == null) {
			throw new IllegalArgumentException();
		}
		final IAttribute iAttribute = attributeSettings.getAttribute();
		final Type type = iAttribute.type();
		return type.isCollection();
	}

}
