package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.Settings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Most general type of a fix that is provided for an invalidity in a
 * configuration regarding a type of configuration aspects.
 * 
 * @author Jan Prien
 * 
 * @param <A>
 *            The type of the configuration aspect.
 *
 */
abstract class AbstractSettingsFix<A extends Settings> extends AbstractFix {

	/**
	 * The configuration aspect.
	 */
	protected final A subConfig;

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
	 *            The configuration aspect.
	 */
	protected AbstractSettingsFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, A subconfig) {
		super(config, model, explenation, resolvesProblemCompletely);
		if (subconfig == null || subconfig.getSettingsConfiguration() == null
				|| subconfig.getSettingsConfiguration() != config) {
			throw new IllegalArgumentException();
		}
		this.subConfig = subconfig;
	}

}
