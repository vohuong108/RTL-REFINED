package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the lower and upper bound for an attribute to selected values.
 * 
 * @author Jan Prien
 *
 */
final class SetAttributeSettingsMinMaxFix extends AbstractFix {

	/**
	 * The new lower bound.
	 */
	private final int newMin;

	/**
	 * The new upper bound.
	 */
	private final int newMax;

	/**
	 * The configuration aspect for the attribute.
	 */
	private final AttributeSettings attributeSettings;

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
	 * @param attributeSettings
	 *            The configuration aspect for the attribute.
	 * @param newMin
	 *            The new lower bound.
	 * @param newMax
	 *            The new upper bound.
	 */
	protected SetAttributeSettingsMinMaxFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, final AttributeSettings attributeSettings, final int newMin,
			final int newMax) {
		super(config, model, explenation, resolvesProblemCompletely);
		if (attributeSettings == null) {
			throw new IllegalArgumentException();
		}
		this.attributeSettings = attributeSettings;
		this.newMin = newMin;
		this.newMax = newMax;
	}

	@Override
	public void apply() {
		attributeSettings.setLowerBound(newMin);
		attributeSettings.setUpperBound(newMax);
	}

}
