package org.tzi.kodkod.validation;

import java.util.Set;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the preferred values for type real to a selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetRealSettingsValuesFix extends AbstractSettingsFix<RealSettings> {

	/**
	 * The new preferred values.
	 */
	private final Set<Double> newValues;

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
	 * @param newValues
	 *            The new preferred values.
	 */
	protected SetRealSettingsValuesFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, RealSettings subconfig, Set<Double> newValues) {
		super(config, model, explenation, resolvesProblemCompletely, subconfig);
		if (newValues == null || newValues.contains(null)) {
			throw new IllegalArgumentException();
		}
		this.newValues = newValues;
	}

	@Override
	public void apply() {
		subConfig.setValues(newValues);
	}

}
