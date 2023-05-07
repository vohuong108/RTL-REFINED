package org.tzi.kodkod.validation;

import java.util.Set;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.IntegerSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This removes selected values from the preferred values for type integer.
 * 
 * @author Jan Prien
 *
 */
final class RemoveIntegerSettingsPrefferedValuesFix extends AbstractFix {

	/**
	 * The values to remove.
	 */
	private final Set<Integer> toBeRemoved;

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
	 * @param toBeRemoved
	 *            The values to remove.
	 */
	public RemoveIntegerSettingsPrefferedValuesFix(final SettingsConfiguration config, final IModel model,
			final String explenation, final boolean resolvesProblemCompletely, final Set<Integer> toBeRemoved) {
		super(config, model, explenation, resolvesProblemCompletely);
		this.toBeRemoved = toBeRemoved;
	}

	@Override
	public void apply() {
		IntegerSettings integerSettings = this.config.getIntegerTypeSettings();
		Set<Integer> newValues = integerSettings.getValues();
		newValues.removeAll(toBeRemoved);
		integerSettings.setValues(newValues);
	}

}
