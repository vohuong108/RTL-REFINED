package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Fix that is provided for an invalidity in a configuration.
 * 
 * This sets the lower bound of items for a collection type attribute to a
 * selected value.
 * 
 * @author Jan Prien
 *
 */
final class SetAttributeSettingsCollectionSizeMinFix extends AbstractFix {

	/**
	 * The new lower bound of items.
	 */
	private int newCollectionSizeMin;

	/**
	 * The configuration aspect.
	 */
	private AttributeSettings attributeSettings;

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
	 * @param newCollectionSizeMin
	 *            The new lower bound of items.
	 */
	protected SetAttributeSettingsCollectionSizeMinFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, int newCollectionSizeMin, AttributeSettings attributeSettings) {
		super(config, model, explenation, resolvesProblemCompletely);
		if (attributeSettings == null) {
			throw new IllegalArgumentException();
		}
		this.newCollectionSizeMin = newCollectionSizeMin;
		this.attributeSettings = attributeSettings;
	}

	@Override
	public void apply() {
		attributeSettings.setCollectionSizeMin(newCollectionSizeMin);
	}

}
