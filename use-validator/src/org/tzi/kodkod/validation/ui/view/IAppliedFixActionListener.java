package org.tzi.kodkod.validation.ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for components that can apply fixes.
 * 
 * @author Jan Prien
 *
 */
public interface IAppliedFixActionListener extends ActionListener {

	@Override
	public default void actionPerformed(ActionEvent e) {
		appliedFixActionPerformed();
	}

	/**
	 * Adds a listener that is informed when a fix was applied.
	 * 
	 * @param applyFixActionListener
	 *            The listener.
	 */
	public void appliedFixActionPerformed();

}
