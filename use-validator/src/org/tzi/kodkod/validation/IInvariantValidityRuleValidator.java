package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IInvariant;
import org.tzi.use.kodkod.plugin.gui.model.data.InvariantSettings;

/**
 * Interface for validators that validate configuration aspects for invariants.
 * 
 * @author Jan Prien
 *
 */
interface IInvariantValidityRuleValidator {

	/**
	 * Extracts the name of an invariant from a configuration aspect for an
	 * invariant.
	 * 
	 * @param invariantSettings
	 *            The configuration aspect for an invariant.
	 * @return The name of the invariant
	 */
	default String getInvariantName(InvariantSettings invariantSettings) {
		if (invariantSettings == null) {
			throw new IllegalArgumentException();
		}
		final IInvariant iInvariant = invariantSettings.getInvariant();
		final String invariantName = iInvariant.name();
		return invariantName;
	}

}
