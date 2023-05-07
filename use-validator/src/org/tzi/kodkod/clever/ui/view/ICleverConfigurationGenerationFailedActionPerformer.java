package org.tzi.kodkod.clever.ui.view;

import java.util.Set;

/**
 * Interface for components that generate instance finder configurations and can
 * inform about that a configuration generation failed.
 * 
 * @author Jan Prien
 *
 */
interface ICleverConfigurationGenerationFailedActionPerformer {

	/**
	 * 
	 * @return The set of listener that listen about that a configuration generation
	 *         failed.
	 */
	Set<ICleverConfigurationGenerationFailedActionListener> getICleverConfigurationGenerationFailedActionListener();

	/**
	 * Adds a listener.
	 * 
	 * @param iCleverConfigurationGenerationFailedActionListener
	 *            The listener.
	 */
	public default void addICleverConfigurationGenerationFailedActionListener(
			ICleverConfigurationGenerationFailedActionListener iCleverConfigurationGenerationFailedActionListener) {
		this.getICleverConfigurationGenerationFailedActionListener()
				.add(iCleverConfigurationGenerationFailedActionListener);
	}

	/**
	 * Informs all listener about that a configuration generation failed.
	 */
	default void performConfigurationGenerationFailedAction(String message) {
		for (ICleverConfigurationGenerationFailedActionListener a : this
				.getICleverConfigurationGenerationFailedActionListener()) {
			a.configurationGenerationFailedActionPerformed(message);
		}
	}
}
