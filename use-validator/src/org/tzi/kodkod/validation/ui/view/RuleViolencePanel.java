package org.tzi.kodkod.validation.ui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.tzi.kodkod.validation.ValidityRuleViolence;

/**
 * Panel that presents a rule violation.
 * 
 * @author Jan Prien
 *
 */
final class RuleViolencePanel extends AbstractValidationJPanel {

	/**
	 * The maximum with of violation explanation.
	 */
	private static final int MAX_VIOLATION_EXPLENATION_WIDTH = 750;

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -6539430183981619105L;

	/**
	 * The rule violation.
	 */
	private final ValidityRuleViolence validityRuleViolence;

	/**
	 * Constructs an object.
	 * 
	 * @param validityRuleViolence
	 *            The rule violation.
	 */
	public RuleViolencePanel(ValidityRuleViolence validityRuleViolence) {
		if (validityRuleViolence == null) {
			throw new IllegalArgumentException();
		}
		this.validityRuleViolence = validityRuleViolence;
		this.initGUI();
	}

	@Override
	void initGUI() {
		String validityRuleViolenceExplenation = validityRuleViolence.getExplenation();
		if (validityRuleViolenceExplenation == null) {
			throw new IllegalArgumentException();
		}
		this.setLayout(new BorderLayout());
		final JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		final JLabel validityRuleViolenceExplenationLabel = new JLabel("<html><p style=\"width:"
				+ MAX_VIOLATION_EXPLENATION_WIDTH + "px\">" + validityRuleViolenceExplenation + "</p></html>",
				SwingConstants.LEFT);
		upPanel.add(validityRuleViolenceExplenationLabel);
		this.add(upPanel, BorderLayout.PAGE_START);
		if (validityRuleViolence.getFixes().length > 0) {
			FixesPanel fixesPanel = new FixesPanel(validityRuleViolence.getFixes());
			this.add(fixesPanel, BorderLayout.CENTER);
			this.addApplyFixPerfomer(fixesPanel);
		}
	}

}
