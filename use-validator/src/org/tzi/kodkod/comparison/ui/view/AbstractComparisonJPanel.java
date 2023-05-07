package org.tzi.kodkod.comparison.ui.view;

import javax.swing.JPanel;

/**
 * The most abstract panel.
 * 
 * @author Jan Prien
 *
 */
public abstract class AbstractComparisonJPanel extends JPanel implements IComparisonGUIComponent {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 7056264570601407314L;

	@Override
	public void reinitializeGUI() {
		this.initGUI();
		this.revalidate();
		this.repaint();
	}

}
