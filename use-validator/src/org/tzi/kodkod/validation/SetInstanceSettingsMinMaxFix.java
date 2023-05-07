package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.InstanceSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the lower and upper bound values for class/attribute/association to
 * selected values.
 * 
 * @author Jan Prien
 *
 */
public class SetInstanceSettingsMinMaxFix extends AbstractFix {

	/**
	 * The new lower bound value.
	 */
	private final int newMin;

	/**
	 * The new upper bound value.
	 */
	private final int newMax;

	/**
	 * The configuration aspect.
	 */
	private final InstanceSettings instanceSettings;

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
	 * @param instanceSettings
	 *            The configuration aspect.
	 */
	protected SetInstanceSettingsMinMaxFix(SettingsConfiguration config, IModel model, String explenation,
			final boolean resolvesProblemCompletely, final int newMin, final int newMax,
			InstanceSettings instanceSettings) {
		super(config, model, explenation, resolvesProblemCompletely);
		if (instanceSettings == null) {
			throw new IllegalArgumentException();
		}
		this.newMin = newMin;
		this.newMax = newMax;
		this.instanceSettings = instanceSettings;
	}

	@Override
	public void apply() {
		instanceSettings.setLowerBound(newMin);
		instanceSettings.setUpperBound(newMax);
	}

}
