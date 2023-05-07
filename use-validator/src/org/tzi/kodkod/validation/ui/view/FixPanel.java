package org.tzi.kodkod.validation.ui.view;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import org.tzi.kodkod.validation.AbstractFix;
import org.tzi.kodkod.validation.ui.UIElements;

/**
 * Panel that presents a fix.
 * 
 * @author Jan Prien
 *
 */
final class FixPanel extends AbstractValidationJPanel {

	/**
	 * The maximum with of violation explanation.
	 */
	private static final int MAX_VIOLATION_EXPLENATION_WIDTH = 700;

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 8288768394540845100L;

	/**
	 * The presented fix.
	 */
	private final AbstractFix fix;

	/**
	 * The button which can apply a fix.
	 */
	private ApplyFixButton applyFixButton;

	/**
	 * Constructs an object.
	 * 
	 * @param fix
	 *            The presented fix.
	 */
	public FixPanel(AbstractFix fix) {
		if (fix == null) {
			throw new IllegalArgumentException();
		}
		this.fix = fix;
		this.initGUI();
	}

	@Override
	public void addApplyFixActionListener(IApplyFixActionListener applyFixActionListener) {
		for (IApplyFixPerformer p : getApplyFixPerformer()) {
			p.addApplyFixActionListener(applyFixActionListener);
		}
		this.applyFixButton.addActionListener(applyFixActionListener);
	}

	@Override
	void initGUI() {
		final String fixExplenation = fix.getExplenation();
		if (fixExplenation == null) {
			throw new IllegalArgumentException();
		}
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel resolvesProblemCompletelyLabel = new JLabel(
				fix.resolvesProblemCompletely() ? UIElements.FullFixLabel : UIElements.PartialFixLabel);
		resolvesProblemCompletelyLabel.setToolTipText(UIElements.FullFixPartialFixLabel_Tooltip);
		this.add(resolvesProblemCompletelyLabel);
		this.applyFixButton = new ApplyFixButton(UIElements.FixButton_Title, fix);
		this.applyFixButton.setToolTipText(UIElements.FixButton_Tooltip);
		this.add(applyFixButton);
		JLabel explanationLabel = new JLabel("<html><p style=\"width:" + MAX_VIOLATION_EXPLENATION_WIDTH + "px\">"
				+ fix.getExplenation() + "</p></html>");
		this.add(explanationLabel);
	}

}
