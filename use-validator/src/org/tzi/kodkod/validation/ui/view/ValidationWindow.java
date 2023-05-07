package org.tzi.kodkod.validation.ui.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.SortedMap;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ValidityRule;
import org.tzi.kodkod.validation.ValidityRuleValidator;
import org.tzi.kodkod.validation.ValidityRuleViolence;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * The window for the functionality for validation of instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
public final class ValidationWindow extends AbstractValidationJDialog implements IApplyFixActionListener {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 343332725979093618L;

	/**
	 * The default window width in pixel.
	 */
	private static final int DEFAULT_WIDTH = 800;

	/**
	 * The default window height in pixel.
	 */
	private static final int DEFAULT_HEIGHT = 600;

	/**
	 * The minimum window width in pixel.
	 */
	private static final int MIN_WIDTH = 800;

	/**
	 * The minimum window height in pixel.
	 */
	private static final int MIN_HEIGHT = 600;

	/**
	 * The parent window.
	 */
	private final JDialog parent;

	/**
	 * The name of the configuration.
	 */
	private final String name;

	/**
	 * The configuration.
	 */
	private final SettingsConfiguration settingsConfig;

	/**
	 * The UML/OCL model.
	 */
	private final IModel model;

	/**
	 * The model invariants.
	 */
	private final List<Expression> invariants;

	/**
	 * The panel for the functionality for validation of instance finder
	 * configurations.
	 */
	private ValidationsPanel validationsPanel;

	/**
	 * Constructs an object.
	 * 
	 * @param parent
	 *            The parent window.
	 * @param name
	 *            The name of the configuration.
	 * @param settingsConfig
	 *            The configuration.
	 * @param model
	 *            The UML/OCL model.
	 * @param invariants
	 *            The model invariants.
	 */
	public ValidationWindow(JDialog parent, String name, SettingsConfiguration settingsConfig, IModel model,
			List<Expression> invariants) {
		super(parent);
		if (name == null || settingsConfig == null || model == null || invariants == null) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
		this.name = name;
		this.settingsConfig = settingsConfig;
		this.model = model;
		this.invariants = invariants;
		this.initGUI();
	}

	@Override
	public void dispose() {
		super.dispose();
		parent.setEnabled(true);
	}

	/**
	 * Initializes the GUI.
	 */
	private void initGUI() {
		try {
			parent.setEnabled(false);
			this.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					super.windowClosing(e);
					parent.setEnabled(true);
				}
			});
			this.setTitle(UIElements.MainWindow_Title);
			this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
			if (parent != null) {
				this.setSize(parent.getSize());
			} else {
				this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			}
			SortedMap<ValidityRule, ValidityRuleViolence[]> violationsByValdityRules = new ValidityRuleValidator()
					.validate(settingsConfig, model, invariants);
			if (violationsByValdityRules.size() == 0) {
				JOptionPane.showMessageDialog(this, UIElements.InfoDialog_Message, UIElements.InfoDialog_Title,
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				return;
			} else {
				this.validationsPanel = new ValidationsPanel(this.name, violationsByValdityRules);
				this.setContentPane(this.validationsPanel);
				this.addApplyFixPerfomer(this.validationsPanel);
				this.addApplyFixActionListener(this);
			}
			this.setLocationRelativeTo(parent);
			this.setVisible(true);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, UIElements.ErrorDialog_Message, UIElements.ErrorDialog_Title,
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	@Override
	public void applyFixActionPerformed() {
		this.setEnabled(false);
		if (this.validationsPanel.getCloseOnFix()) {
			this.dispose();
		} else {
			try {
				SortedMap<ValidityRule, ValidityRuleViolence[]> violationsByValdityRules = new ValidityRuleValidator()
						.validate(settingsConfig, model, invariants);
				if (violationsByValdityRules.size() == 0) {
					this.appliedFixActionPerformed();
					this.dispose();
					JOptionPane.showMessageDialog(this, UIElements.InfoDialog_Message, UIElements.InfoDialog_Title,
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					ValidationsPanel newValidationsPanel = new ValidationsPanel(this.name, violationsByValdityRules,
							false);
					this.setContentPane(newValidationsPanel);
					this.removeApplyFixPerfomer(this.validationsPanel);
					this.addApplyFixPerfomer(newValidationsPanel);
					this.validationsPanel = newValidationsPanel;
					this.addApplyFixActionListener(this);
					this.revalidate();
					this.setEnabled(true);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, UIElements.ErrorDialog_Message, UIElements.ErrorDialog_Title,
						JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
		this.appliedFixActionPerformed();
	}

}
