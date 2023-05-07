package org.tzi.kodkod.clever.ui.view;

/**
 * Interface for components that listen about that a configuration generation
 * failed.
 * 
 * @author Jan Prien
 *
 */
interface ICleverConfigurationGenerationFailedActionListener {

	/**
	 * This is called when a configuration generation failed and this listener is
	 * informed.
	 */
	public void configurationGenerationFailedActionPerformed(String message);

}
