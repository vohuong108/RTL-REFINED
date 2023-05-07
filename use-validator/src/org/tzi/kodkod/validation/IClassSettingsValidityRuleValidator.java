package org.tzi.kodkod.validation;

import java.util.Set;

import org.tzi.kodkod.model.iface.IClass;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Interface for validators that validate configuration aspects for clases.
 * 
 * @author Jan Prien
 *
 */
interface IClassSettingsValidityRuleValidator extends IInstanceSettingsValdityRuleValidator {

	/**
	 * Extracts the upper bound for a selected class from a configuration.
	 * 
	 * @param settings
	 *            The configuration.
	 * @param iClass
	 *            The class.
	 * @return The upper bound for the class.
	 */
	default Integer getClassMax(SettingsConfiguration settings, IClass iClass) {
		if (settings == null || iClass == null) {
			throw new IllegalArgumentException();
		}
		ClassSettings classSettings = settings.getClassSettings(iClass);
		return classSettings.getUpperBound();
	}

	/**
	 * Extracts the preferred instance names for a selected class from a
	 * configuration.
	 * 
	 * @param config
	 *            The configuration.
	 * @param iClass
	 *            The class.
	 * @return The preferred instance names for the class.
	 */
	default Set<String> getPreferredIdentities(SettingsConfiguration config, IClass iClass) {
		if (config == null || iClass == null) {
			throw new IllegalArgumentException();
		}
		ClassSettings classSettings = config.getClassSettings(iClass);
		return classSettings.getInstanceNames();
	}

}
