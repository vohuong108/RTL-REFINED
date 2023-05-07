package org.tzi.kodkod.validation.ui.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import org.tzi.kodkod.validation.ValidityRule;
import org.tzi.kodkod.validation.ValidityRuleViolence;

/**
 * Panel that lists rule violations.
 * 
 * @author Jan Prien
 *
 */
final class RuleViolencesPanel extends AbstractValidationJPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -6544077013093337516L;

	/**
	 * The rule.
	 */
	private final ValidityRule violation;

	/**
	 * The rule violations.
	 */
	private final ValidityRuleViolence[] validityRuleViolences;

	/**
	 * Constructs an object.
	 * 
	 * @param violation
	 *            The rule.
	 * @param validityRuleViolences
	 *            The rule violations.
	 */
	public RuleViolencesPanel(ValidityRule violation, ValidityRuleViolence[] validityRuleViolences) {
		if (violation == null || validityRuleViolences == null || validityRuleViolences.length < 1) {
			throw new IllegalArgumentException();
		}
		this.violation = violation;
		this.validityRuleViolences = validityRuleViolences;
		this.initGUI();

	}

	@Override
	void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), violation.name()));
		for (ValidityRuleViolence validityRuleViolence : validityRuleViolences) {
			RuleViolencePanel ruleViolencePanel = new RuleViolencePanel(validityRuleViolence);
			this.add(ruleViolencePanel);
			this.addApplyFixPerfomer(ruleViolencePanel);
		}
	}

}
