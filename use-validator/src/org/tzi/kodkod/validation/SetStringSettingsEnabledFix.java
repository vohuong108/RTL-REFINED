package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.kodkod.plugin.gui.model.data.StringSettings;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the enabled value for type string to a selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetStringSettingsEnabledFix extends AbstractSettingsFix<StringSettings> {

	/**
	 * The new enabled value.
	 */
	protected final boolean newEnabled;

	/**
	 * 
	 * Constructs an object.
	 * 
	 * @param config
	 *            The configuration that gets modified when applying the fix.
	 * @param model
	 *            The model for which the configuration is.
	 * @param explenation
	 *            The textual explanation of the fix.
	 * @param resolvesProblemCompletely
	 *            Whether the problem is resolved completely or only partially.
	 * @param subconfig
	 *            The configuration aspect for type string.
	 * @param newEnabled
	 *            The new enabled value.
	 */
	protected SetStringSettingsEnabledFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, StringSettings subconfig, boolean newEnabled) {
		super(config, model, explenation, resolvesProblemCompletely, subconfig);
		this.newEnabled = newEnabled;
	}

	@Override
	public void apply() {
		this.subConfig.setEnabled(newEnabled);
	}

}
