package org.tzi.kodkod.validation.ui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.tzi.kodkod.validation.ValidityRule;
import org.tzi.kodkod.validation.ValidityRuleViolence;
import org.tzi.kodkod.validation.ui.UIElements;

/**
 * The panel for the functionality for validation of instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
final class ValidationsPanel extends AbstractValidationJPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -1556059700227021203L;

	/**
	 * The violations of validity rules.
	 */
	private final SortedMap<ValidityRule, ValidityRuleViolence[]> violationsByValdityRules;

	/**
	 * The default value for {@link #closeOnFix}.
	 */
	private static final boolean DEFAULT_closeOnFix = true;

	/**
	 * Whether the window for validation should close after applying a fix.
	 */
	private boolean closeOnFix;

	/**
	 * Constructs an object.
	 * 
	 * @param name
	 *            The name of the configuration
	 * @param violationsByValdityRules
	 *            The violations of validity rules.
	 */
	ValidationsPanel(String name, SortedMap<ValidityRule, ValidityRuleViolence[]> violationsByValdityRules) {
		if (name == null || violationsByValdityRules == null || violationsByValdityRules.size() < 1) {
			throw new IllegalArgumentException();
		}
		try {
			if (violationsByValdityRules.containsKey(null) || violationsByValdityRules.containsValue(null)) {
				throw new IllegalArgumentException();
			}
		} catch (NullPointerException e) {
			// Do nothing.
		}
		this.violationsByValdityRules = violationsByValdityRules;
		this.closeOnFix = DEFAULT_closeOnFix;
		this.initGUI();
	}

	/**
	 * Constructs an object.
	 * 
	 * @param name
	 *            The name of the configuration
	 * @param violationsByValdityRules
	 *            The violations of validity rules.
	 * @param closeOnFix
	 *            Whether the window for validation should close after applying a
	 *            fix.
	 */
	ValidationsPanel(String name, SortedMap<ValidityRule, ValidityRuleViolence[]> violationsByValdityRules,
			boolean closeOnFix) {
		if (name == null || violationsByValdityRules == null || violationsByValdityRules.size() < 1) {
			throw new IllegalArgumentException();
		}
		try {
			if (violationsByValdityRules.containsKey(null) || violationsByValdityRules.containsValue(null)) {
				throw new IllegalArgumentException();
			}
		} catch (NullPointerException e) {
			// Do nothing.
		}
		this.violationsByValdityRules = violationsByValdityRules;
		this.closeOnFix = closeOnFix;
		this.initGUI();
	}

	boolean getCloseOnFix() {
		return this.closeOnFix;
	}

	private void setCloseOnFix(boolean closeOnFix) {
		this.closeOnFix = closeOnFix;
	}

	@Override
	void initGUI() {
		this.setLayout(new BorderLayout());
		final JPanel upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upPanel.add(new JLabel(UIElements.MainWindow_Text));
		final JPanel violationsPanel = new JPanel();
		violationsPanel.setLayout(new BoxLayout(violationsPanel, BoxLayout.PAGE_AXIS));
		final JPanel violationsPanelWrapper = new JPanel(new BorderLayout());
		violationsPanelWrapper.add(violationsPanel, BorderLayout.NORTH);
		final JScrollPane midPanel = new JScrollPane(violationsPanelWrapper);
		final JPanel downPanel = new JPanel();
		downPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		final JCheckBox closeOnFix = new JCheckBox(UIElements.MainWindow_CloseWindowAfterFixApplyCheckBox_Title);
		closeOnFix.setSelected(this.getCloseOnFix());
		closeOnFix.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				setCloseOnFix(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		downPanel.add(closeOnFix);
		this.add(upPanel, BorderLayout.PAGE_START);
		this.add(midPanel);
		this.add(downPanel, BorderLayout.PAGE_END);
		for (Entry<ValidityRule, ValidityRuleViolence[]> violationsByValdityRule : violationsByValdityRules
				.entrySet()) {
			RuleViolencesPanel ruleViolencesPanel = new RuleViolencesPanel(violationsByValdityRule.getKey(),
					violationsByValdityRule.getValue());
			violationsPanel.add(ruleViolencesPanel);
			this.addApplyFixPerfomer(ruleViolencesPanel);
		}

	}

}
