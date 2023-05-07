package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Most general type of a fix that is provided for an invalidity in a
 * configuration.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractFix {

	/**
	 * The configuration that gets modified when applying the fix.
	 */
	protected final SettingsConfiguration config;

	/**
	 * The model for which the configuration is.
	 */
	protected final IModel model;

	/**
	 * The textual explanation of the fix.
	 */
	protected final String explenation;

	/**
	 * Whether the problem is resolved completely or only partially. This is not
	 * interpreted as that the validity rule is not violated anymore if this fix is
	 * applied. This only means that the evaluation object, e.g. a class will not
	 * violate the rule anymore if this fix is applied.
	 */
	private final boolean resolvesProblemCompletely;

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
	 */
	protected AbstractFix(final SettingsConfiguration config, final IModel model, final String explenation,
			final boolean resolvesProblemCompletely) {
		if (config == null || model == null || explenation == null) {
			throw new IllegalArgumentException();
		}
		this.config = config;
		this.model = model;
		this.explenation = explenation;
		this.resolvesProblemCompletely = resolvesProblemCompletely;
	}

	public String getExplenation() {
		return this.explenation;
	}

	/**
	 * Applies the fix on the configuration.
	 */
	abstract public void apply();

	public boolean resolvesProblemCompletely() {
		return resolvesProblemCompletely;
	}
}
