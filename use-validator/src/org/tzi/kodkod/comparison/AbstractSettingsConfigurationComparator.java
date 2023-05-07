package org.tzi.kodkod.comparison;

import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Compares configurations.
 * 
 * @author Jan Prien
 *
 * @param <A>
 *            The type of results.
 */
public abstract class AbstractSettingsConfigurationComparator<A extends AbstractComparisonResult<?>> {

	/**
	 * Compares configurations.
	 * 
	 * @param leftConfiguration
	 *            The configuration used as first operand of binary comparison.
	 * @param rightConfiguration
	 *            The configuration used as second operand of binary comparison.
	 * @return The comparison result.
	 * @throws Exception
	 *             If the comparison fails.
	 */
	public abstract A compareSettingsConfigurations(final SettingsConfiguration leftConfiguration,
			final SettingsConfiguration rightConfiguration) throws Exception;

}
