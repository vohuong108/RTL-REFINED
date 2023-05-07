package org.tzi.kodkod.clever.ui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.tzi.kodkod.clever.CleverConfigurationGenerator;
import org.tzi.kodkod.clever.GenerationException;
import org.tzi.kodkod.clever.ui.UIElements;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * The panel for the functionality for clever generation of instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
final class GenerationPanel extends AbstractCleverGenerationJPanel
		implements ICleverConfigurationGeneratedActionPerformer, ICleverConfigurationGenerationFailedActionPerformer {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -6538747711261448443L;

	/**
	 * The listener that listen about that a configuration is successfully
	 * generated.
	 */
	private Set<ICleverConfigurationGeneratedActionListener> iCleverConfigurationGenerationActionListener = new HashSet<>();

	/**
	 * The listener that listen about that a configuration generation failed.
	 */
	private Set<ICleverConfigurationGenerationFailedActionListener> iCleverConfigurationGenerationFailedActionListener = new HashSet<>();

	/**
	 * Whether the invariants must be respected in the bounds tightening process.
	 */
	private boolean repsectOclConstraints = false;

	/**
	 * Whether all attribute upper bounds should be set to -1 if they are greater
	 * than or equal to the upper bounds of the attributes owning class.
	 */
	private boolean mandatorizeAttributes = true;

	/**
	 * Whether all attribute lower bounds should be set to -1.
	 */
	private boolean generalizeAttributeUpperBounds = true;

	/**
	 * The instance finder configuration which will be modified with computed
	 * values.
	 */
	private final SettingsConfiguration settingsConfig;

	/**
	 * The UML/OCL model.
	 */
	private final IModel model;

	/**
	 * The invariants of the model.
	 */
	private final List<Expression> invariants;

	/**
	 * The general initial lower bound.
	 */
	private final int initialDefaultLowerBound;

	/**
	 * The general initial upper bound.
	 */
	private final int initialDefaultUpperBound;

	/**
	 * The panel for specification of initial bounds.
	 */
	private InitialBoundsPanel initialBoundsPanel;

	public GenerationPanel(JDialog parent, SettingsConfiguration settingsConfig, IModel model,
			List<Expression> invariants, int initialDefaultLowerBound, int initialDefaultUpperBound) {
		if (parent == null || settingsConfig == null || model == null || invariants == null) {
			throw new IllegalArgumentException();
		}
		this.settingsConfig = settingsConfig;
		this.model = model;
		this.invariants = invariants;
		this.initialDefaultLowerBound = initialDefaultLowerBound;
		this.initialDefaultUpperBound = initialDefaultUpperBound;
		this.initGUI();
	}

	@Override
	void initGUI() {
		this.setLayout(new BorderLayout());
		final GenerationPanel dis = this;
		final JPanel upPanelWrapper = new JPanel(new BorderLayout());
		final JPanel upPanel = new JPanel();
		upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.PAGE_AXIS));
		upPanelWrapper.add(upPanel, BorderLayout.NORTH);

		final JCheckBox repsectOclConstraintsCheckBox = new JCheckBox(
				UIElements.MainWindow_RepsectOclConstraintsCheckBox_Title);
		repsectOclConstraintsCheckBox.setSelected(repsectOclConstraints);
		repsectOclConstraintsCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				// TODO remove this, when IModelAndExpressionToCSPTranslator supports including
				// of OCL constraints.
				// Prevents calling unsupported functionality.
				if (e.getStateChange() == ItemEvent.SELECTED) {
					JOptionPane.showMessageDialog(dis, "This funtionality is currently not supported.",
							"Unsupported funtionality", JOptionPane.ERROR_MESSAGE);
					repsectOclConstraintsCheckBox.setSelected(false);
					return;
				}

				dis.setRepsectOclConstraints(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		final JCheckBox mandatorizeAttributesCheckBox = new JCheckBox(
				UIElements.MainWindow_MandatorizeAttibutesCheckBox_Title);
		mandatorizeAttributesCheckBox.setSelected(mandatorizeAttributes);
		mandatorizeAttributesCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				dis.setMandatorizeAttributes(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		final JCheckBox generalizeAttributeUpperBoundsCheckBox = new JCheckBox(
				UIElements.MainWindow_GeneralizeAttributeUpperBoundsCheckBox_Title);
		generalizeAttributeUpperBoundsCheckBox.setSelected(generalizeAttributeUpperBounds);
		generalizeAttributeUpperBoundsCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				dis.setGeneralizeAttributeUpperBounds(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		upPanel.add(repsectOclConstraintsCheckBox);
		upPanel.add(mandatorizeAttributesCheckBox);
		upPanel.add(generalizeAttributeUpperBoundsCheckBox);

		this.initialBoundsPanel = new InitialBoundsPanel(this.model, this.initialDefaultLowerBound,
				this.initialDefaultUpperBound);
		final JScrollPane midPanel = new JScrollPane(initialBoundsPanel);
		final JPanel downPanel = new JPanel();
		downPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		final JButton generateButton = new JButton(UIElements.MainWindow_GenerationButton_Title);
		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dis.generateCleverConfiguration();
			}
		});
		downPanel.add(generateButton);
		this.add(upPanelWrapper, BorderLayout.PAGE_START);
		this.add(midPanel);
		this.add(downPanel, BorderLayout.PAGE_END);
	}

	protected void setMandatorizeAttributes(boolean b) {
		mandatorizeAttributes = b;
	}

	protected void setGeneralizeAttributeUpperBounds(boolean b) {
		generalizeAttributeUpperBounds = b;
	}

	protected void setRepsectOclConstraints(boolean b) {
		repsectOclConstraints = b;
	}

	/**
	 * Generates a configuration.
	 * 
	 * Informs the listener whether it failed or was successful.
	 */
	protected void generateCleverConfiguration() {
		CleverConfigurationGenerator cleverConfigurationGenerator = new CleverConfigurationGenerator();
		try {
			cleverConfigurationGenerator.generate(
					this.initialBoundsPanel.deriveIModelCSPVariablesInitialBoundsSpecification(), invariants,
					repsectOclConstraints, generalizeAttributeUpperBounds, mandatorizeAttributes, settingsConfig);
		} catch (GenerationException e) {
			this.performConfigurationGenerationFailedAction(e.getMessage());
			return;
		}
		this.performConfigurationGeneratedAction();

	}

	@Override
	public Set<ICleverConfigurationGeneratedActionListener> getICleverConfigurationGenerationActionListener() {
		return iCleverConfigurationGenerationActionListener;
	}

	@Override
	public Set<ICleverConfigurationGenerationFailedActionListener> getICleverConfigurationGenerationFailedActionListener() {
		return iCleverConfigurationGenerationFailedActionListener;
	}

}
