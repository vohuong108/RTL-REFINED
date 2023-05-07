package org.tzi.kodkod.clever.ui.view;

import java.util.Set;

/**
 * Interface for components that generate instance finder configurations and can
 * inform about that a configuration is successfully generated.
 * 
 * @author Jan Prien
 *
 */
public interface ICleverConfigurationGeneratedActionPerformer {

	/**
	 * 
	 * @return The set of listener that listen about that a configuration is
	 *         successfully generated.
	 */
	Set<ICleverConfigurationGeneratedActionListener> getICleverConfigurationGenerationActionListener();

	/**
	 * Adds a listener.
	 * 
	 * @param iCleverConfigurationGenerationActionListener
	 *            The listener.
	 */
	public default void addICleverConfigurationGenerationActionListener(
			ICleverConfigurationGeneratedActionListener iCleverConfigurationGenerationActionListener) {
		this.getICleverConfigurationGenerationActionListener().add(iCleverConfigurationGenerationActionListener);
	}

	/**
	 * Informs all listener about that a configuration is successfully generated.
	 */
	default void performConfigurationGeneratedAction() {
		for (ICleverConfigurationGeneratedActionListener a : this.getICleverConfigurationGenerationActionListener()) {
			a.configurationGeneratedActionPerformed();
		}
	}

}
