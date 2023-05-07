package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the step value for type real to a selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetRealSettingsStepFix extends AbstractFix {

	/**
	 * The new step value.
	 */
	private final double newStep;

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
	 * @param newStep
	 *            The new step value.
	 * @param realSettings
	 *            The configuration aspects for type real.
	 */
	protected SetRealSettingsStepFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, double newStep, RealSettings realSettings) {
		super(config, model, explenation, resolvesProblemCompletely);
		this.newStep = newStep;
	}

	@Override
	public void apply() {
		RealSettings realSettings = this.config.getRealTypeSettings();
		realSettings.setStep(newStep);
	}

}
