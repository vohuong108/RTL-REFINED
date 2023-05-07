package org.tzi.kodkod.validation.ui.view;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

/**
 * The most general panel for the functionality for validation of instance
 * finder configurations.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractValidationJPanel extends JPanel implements IApplyFixPerformer {

	/**
	 * The default window width in pixel.
	 */
	private static final long serialVersionUID = 2197295552826970824L;

	/**
	 * Sub components that can apply fixes.
	 * 
	 * @author Jan Prien
	 *
	 */
	final Set<IApplyFixPerformer> applyFixPerformer = new HashSet<IApplyFixPerformer>();

	@Override
	public Set<IApplyFixPerformer> getApplyFixPerformer() {
		return this.applyFixPerformer;
	}

	/**
	 * Initializes the GUI.
	 */
	abstract void initGUI();

	/**
	 * Reinitializes the GUI.
	 */
	void reinitializeGUI() {
		this.initGUI();
		this.revalidate();
		this.repaint();
	}

}
