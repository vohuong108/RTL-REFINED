package org.tzi.kodkod.validation;

import java.util.Set;

import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;

/**
 * Interface for validators that validate configuration aspects for type real.
 * 
 * @author Jan Prien
 *
 */
public interface IRealSettingsValidityRuleValidator {
	/**
	 * Extracts the preferred values from a configuration aspect for type real.
	 * 
	 * @param realSettings
	 *            The configuration aspect for type real.
	 * @return The preferred values.
	 */
	default Set<Double> getValues(RealSettings realSettings) {
		if (realSettings == null) {
			throw new IllegalArgumentException();
		}
		final Set<Double> values = realSettings.getValues();
		return values;
	}

}
