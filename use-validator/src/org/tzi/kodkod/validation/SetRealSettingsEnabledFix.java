package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the enabled value for type real to a selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetRealSettingsEnabledFix extends AbstractSettingsFix<RealSettings> {

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
	 *            The configuration aspects for type real.
	 * @param newEnabled
	 *            The new enabled value.
	 */
	protected SetRealSettingsEnabledFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, RealSettings subconfig, boolean newEnabled) {
		super(config, model, explenation, resolvesProblemCompletely, subconfig);
		this.newEnabled = newEnabled;
	}

	@Override
	public void apply() {
		this.subConfig.setEnabled(newEnabled);
	}

}
