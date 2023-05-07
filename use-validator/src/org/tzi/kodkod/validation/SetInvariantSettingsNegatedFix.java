package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.InvariantSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the negate value for an invariant to a selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetInvariantSettingsNegatedFix extends AbstractFix {

	/**
	 * The new negate value for the invariant.
	 */
	private final boolean newNegate;

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
	 * @param newNegate
	 *            The new negate value for the invariant.
	 * @param invariantSettings
	 *            The configuration aspect for the invariant.
	 */
	protected SetInvariantSettingsNegatedFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, boolean newNegate, InvariantSettings invariantSettings) {
		super(config, model, explenation, resolvesProblemCompletely);
		if (invariantSettings == null) {
			throw new IllegalArgumentException();
		}
		this.newNegate = newNegate;
		this.invariantSettings = invariantSettings;
	}

	@Override
	public void apply() {
		invariantSettings.setNegate(newNegate);
	}

}
