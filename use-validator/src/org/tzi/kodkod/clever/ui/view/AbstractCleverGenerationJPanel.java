package org.tzi.kodkod.clever.ui.view;

import javax.swing.JPanel;

/**
 * The most abstract panel.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractCleverGenerationJPanel extends JPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 2753879132660308672L;

	/**
	 * Initializes the panel.
	 */
	abstract void initGUI();

	/**
	 * Reinitializes the panel.
	 */
	void reinitializeGUI() {
		this.initGUI();
		this.revalidate();
		this.repaint();
	}

}
