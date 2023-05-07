package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the lower and upper bound values for type real to selected values.
 * 
 * @author Jan Prien
 *
 */
public class SetRealSettingsMinMaxFix extends AbstractFix {

	/**
	 * The new lower bound.
	 */
	private final double newMin;

	/**
	 * The new upper bound.
	 */
	private final double newMax;

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
	 *            The new lower bound.
	 * @param newMax
	 *            The new upper bound.
	 */
	protected SetRealSettingsMinMaxFix(SettingsConfiguration config, IModel model, String explenation,
			final boolean resolvesProblemCompletely, final double newMin, final double newMax) {
		super(config, model, explenation, resolvesProblemCompletely);
		this.newMin = newMin;
		this.newMax = newMax;
	}

	@Override
	public void apply() {
		RealSettings realSettings = this.config.getRealTypeSettings();
		realSettings.setMinimum(newMin);
		realSettings.setMaximum(newMax);
	}

}
