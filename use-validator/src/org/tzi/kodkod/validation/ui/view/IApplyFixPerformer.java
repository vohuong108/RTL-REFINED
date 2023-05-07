package org.tzi.kodkod.validation.ui.view;

import java.util.Set;

/**
 * Interface for components that can apply fixes.
 * 
 * @author Jan Prien
 *
 */
interface IApplyFixPerformer {

	/**
	 * 
	 * @return The sub component that can apply fixes.
	 */
	Set<IApplyFixPerformer> getApplyFixPerformer();

	/**
	 * Removes a components that can apply fixes.
	 * 
	 * @param applyFixPerformer
	 *            The components that can apply fixes.
	 */
	default void removeApplyFixPerfomer(final IApplyFixPerformer applyFixPerformer) {
		this.getApplyFixPerformer().remove(applyFixPerformer);
	}

	/**
	 * Adds a components that can apply fixes.
	 * 
	 * @param applyFixPerformer
	 *            The components that can apply fixes.
	 */
	default void addApplyFixPerfomer(final IApplyFixPerformer applyFixPerformer) {
		this.getApplyFixPerformer().add(applyFixPerformer);
	}

	/**
	 * Adds a listener that is informed when a fix will be applied.
	 * 
	 * @param applyFixActionListener
	 *            The lsitener.
	 */
	public default void addApplyFixActionListener(IApplyFixActionListener applyFixActionListener) {
		for (IApplyFixPerformer p : getApplyFixPerformer()) {
			p.addApplyFixActionListener(applyFixActionListener);
		}
	}

}
