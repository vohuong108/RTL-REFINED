package org.tzi.kodkod.comparison.ui.view;

import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Model for an instance finder configuration and its name.
 * 
 * @author Jan Prien
 *
 */
public class ComparedSelection {

	/**
	 * The name of the instance finder configuration.
	 */
	private final String name;

	/**
	 * The instance finder configuration.
	 */
	private final SettingsConfiguration settingsConfiguration;

	/**
	 * COnstructs an object.
	 * 
	 * @param name
	 *            The name of the instance finder configuration.
	 * @param settingsConfiguration
	 *            The instance finder configuration.
	 */
	public ComparedSelection(final String name, final SettingsConfiguration settingsConfiguration) {
		if (name == null || settingsConfiguration == null) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.settingsConfiguration = settingsConfiguration;
	}

	public String getName() {
		return name;
	}

	public SettingsConfiguration getSettingsConfiguration() {
		return settingsConfiguration;
	}

}
