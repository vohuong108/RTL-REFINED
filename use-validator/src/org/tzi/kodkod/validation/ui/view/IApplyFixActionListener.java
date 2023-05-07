package org.tzi.kodkod.validation.ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for components that can apply fixes.
 * 
 * @author Jan Prien
 *
 */
interface IApplyFixActionListener extends ActionListener {

	@Override
	default void actionPerformed(ActionEvent e) {
		if (e.getSource().getClass() == ApplyFixButton.class) {
			((ApplyFixButton) e.getSource()).getFix().apply();
			applyFixActionPerformed();
		}
	}

	/**
	 * Actions processed when a fix will be applied.
	 */
	void applyFixActionPerformed();

}
