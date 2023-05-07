package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.IntegerSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the lower and upper bound values for type integer to a selected
 * value.
 * 
 * @author Jan Prien
 *
 */
final class SetIntegerSettingsMinMaxFix extends AbstractFix {

	/**
	 * The new lower bound value.
	 */
	private final int newMin;

	/**
	 * The new upper bound value.
	 */
	private final int newMax;

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
	 * @param newMin
	 *            The new lower bound value.
	 * @param newMax
	 *            The new upper bound value.
	 */
	protected SetIntegerSettingsMinMaxFix(SettingsConfiguration config, IModel model, String explenation,
			final boolean resolvesProblemCompletely, final int newMin, final int newMax) {
		super(config, model, explenation, resolvesProblemCompletely);
		this.newMin = newMin;
		this.newMax = newMax;
	}

	@Override
	public void apply() {
		IntegerSettings integerSettings = this.config.getIntegerTypeSettings();
		integerSettings.setMinimum(newMin);
		integerSettings.setMaximum(newMax);
	}

}
