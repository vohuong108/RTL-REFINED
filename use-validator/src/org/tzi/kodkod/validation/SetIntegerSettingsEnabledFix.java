package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.IntegerSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the enabled value for type integer to a selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetIntegerSettingsEnabledFix extends AbstractSettingsFix<IntegerSettings> {

	/**
	 * The new enabled value.
	 */
	protected final boolean newEnabled;

	/**
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
	 *            The configuration aspect for type integer.
	 * @param newEnabled
	 *            The new enabled value.
	 */
	protected SetIntegerSettingsEnabledFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, IntegerSettings subconfig, boolean newEnabled) {
		super(config, model, explenation, resolvesProblemCompletely, subconfig);
		this.newEnabled = newEnabled;
	}

	@Override
	public void apply() {
		this.subConfig.setEnabled(newEnabled);
	}

}
