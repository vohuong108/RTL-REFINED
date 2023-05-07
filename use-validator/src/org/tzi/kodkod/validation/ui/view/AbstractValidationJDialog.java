package org.tzi.kodkod.validation.ui.view;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JDialog;

/**
 * The most general window for the functionality for validation of instance
 * finder configurations.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractValidationJDialog extends JDialog implements IApplyFixPerformer, IAppliedFixActionPerformer {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 8171770840530517449L;

	/**
	 * Sub components that can apply fixes.
	 * 
	 * @author Jan Prien
	 *
	 */
	final Set<IApplyFixPerformer> applyFixPerformer = new HashSet<IApplyFixPerformer>();

	/**
	 * The listener for when a fix is applied.
	 *
	 */
	private final Set<IAppliedFixActionListener> appliedFixActionListener = new HashSet<>();

	/**
	 * Constructs an object.
	 * 
	 * @param parent
	 *            The parent window.
	 */
	public AbstractValidationJDialog(JDialog parent) {
		super(parent);
	}

	@Override
	public Set<IApplyFixPerformer> getApplyFixPerformer() {
		return this.applyFixPerformer;
	}

	@Override
	public Set<IAppliedFixActionListener> getIAppliedFixActionListener() {
		return this.appliedFixActionListener;
	}

}
