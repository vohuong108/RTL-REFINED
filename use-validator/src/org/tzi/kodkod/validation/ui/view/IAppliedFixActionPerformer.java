package org.tzi.kodkod.validation.ui.view;

import java.awt.event.ActionEvent;
import java.util.Set;

/**
 * Interface for components that can apply fixes.
 * 
 * @author Jan Prien
 *
 */
public interface IAppliedFixActionPerformer {

	/**
	 * 
	 * @return The listener that is informed when a fix was applied.
	 */
	Set<IAppliedFixActionListener> getIAppliedFixActionListener();

	/**
	 * Add a listener that is informed when a fix was applied.
	 * 
	 * @param appliedFixActionListener
	 */
	public default void addIAppliedFixActionListener(IAppliedFixActionListener appliedFixActionListener) {
		this.getIAppliedFixActionListener().add(appliedFixActionListener);
	}

	/**
	 * Informs all listener when a fix was applied.
	 * 
	 */
	default void appliedFixActionPerformed() {
		ActionEvent e = new ActionEvent(this, 0, "");
		for (IAppliedFixActionListener a : this.getIAppliedFixActionListener()) {
			a.actionPerformed(e);
		}
	}

}
