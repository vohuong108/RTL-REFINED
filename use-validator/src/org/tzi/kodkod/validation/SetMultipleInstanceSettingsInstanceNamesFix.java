package org.tzi.kodkod.validation;

import java.util.Set;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the preferred/required values for classes/attributes/associations
 * to selected values.
 * 
 * @author Jan Prien
 *
 */
final class SetMultipleInstanceSettingsInstanceNamesFix
		extends AbstractMultipleFix<SetInstanceSettingsInstanceNamesFix> {

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
	 * @param partialFixes
	 *            The individual fixes.
	 */
	protected SetMultipleInstanceSettingsInstanceNamesFix(SettingsConfiguration config, IModel model,
			String explenation, boolean resolvesProblemCompletely,
			Set<SetInstanceSettingsInstanceNamesFix> partialFixes) {
		super(config, model, explenation, resolvesProblemCompletely, partialFixes);
	}

}
