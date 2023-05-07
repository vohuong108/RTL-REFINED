package org.tzi.kodkod.validation;

import java.util.HashSet;
import java.util.Set;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Bundle of fixes that are provided for one invalidity in a configuration.
 * 
 * @author Jan Prien
 *
 * @param <A>
 *            The type of each bundled fix.
 */
abstract class AbstractMultipleFix<A extends AbstractFix> extends AbstractFix {

	/**
	 * The set of bundled fixes.
	 */
	private Set<A> partialFixes;

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
	 *            The set of bundled fixes.
	 */
	protected AbstractMultipleFix(SettingsConfiguration config, IModel model, String explenation,
			boolean resolvesProblemCompletely, Set<A> partialFixes) {
		super(config, model, explenation, resolvesProblemCompletely);
		if (partialFixes == null || partialFixes.contains(null)) {
			throw new IllegalArgumentException();
		}
		this.partialFixes = new HashSet<>();
		this.partialFixes.addAll(partialFixes);
	}

	@Override
	public void apply() {
		for (A partialFix : this.partialFixes) {
			partialFix.apply();
		}
	}

}
