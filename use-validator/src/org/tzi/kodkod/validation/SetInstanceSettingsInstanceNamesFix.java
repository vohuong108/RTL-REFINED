package org.tzi.kodkod.validation;

import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.InstanceSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the preferred/required values for class/attribute/association to a
 * selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetInstanceSettingsInstanceNamesFix extends AbstractFix {

	/**
	 * The new preferred/required values.
	 */
	private Set<String> newInstanceNames;

	/**
	 * The configuration aspect.
	 */
	private InstanceSettings instanceSettings;

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
	 * @param newInstanceNames
	 *            The new preferred/required values.
	 * @param instanceSettings
	 *            The configuration aspect.
	 */
	protected SetInstanceSettingsInstanceNamesFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, Set<String> newInstanceNames, InstanceSettings instanceSettings) {
		super(config, model, explenation, resolvesProblemCompletely);
		if (newInstanceNames == null || newInstanceNames.contains(null) || instanceSettings == null) {
			throw new IllegalArgumentException();
		}
		this.newInstanceNames = new HashSet<>();
		this.newInstanceNames.addAll(newInstanceNames);
		this.instanceSettings = instanceSettings;
	}

	@Override
	public void apply() {
		instanceSettings.setInstanceNames(newInstanceNames);
	}

}
