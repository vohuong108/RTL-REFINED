package org.tzi.kodkod.clever.ui.view;

/**
 * Interface for components that listen about that a configuration is
 * successfully generated.
 * 
 * @author Jan Prien
 *
 */
public interface ICleverConfigurationGeneratedActionListener {

	/**
	 * This is called when a configuration is successfully generated and this
	 * listener is informed.
	 */
	public void configurationGeneratedActionPerformed();
}
