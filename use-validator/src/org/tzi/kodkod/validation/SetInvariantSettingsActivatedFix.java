package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.InvariantSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the active value for an invariant to a selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetInvariantSettingsActivatedFix extends AbstractFix {

	/**
	 * The new active value for the invariant.
	 */
	private final boolean newActive;

	/**
	 * The configuration aspect for the invariant.
	 */
	private final InvariantSettings invariantSettings;

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
	 * @param newActive
	 *            The new active value for the invariant.
	 * @param invariantSettings
	 *            The configuration aspect for the invariant.
	 */
	protected SetInvariantSettingsActivatedFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, boolean newActive, InvariantSettings invariantSettings) {
		super(config, model, explenation, resolvesProblemCompletely);
		if (invariantSettings == null) {
			throw new IllegalArgumentException();
		}
		this.newActive = newActive;
		this.invariantSettings = invariantSettings;
	}

	@Override
	public void apply() {
		invariantSettings.setActive(newActive);
	}

}
