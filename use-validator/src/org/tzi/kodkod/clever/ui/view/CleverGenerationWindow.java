package org.tzi.kodkod.clever.ui.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.tzi.kodkod.clever.ui.UIElements;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * The window for the functionality for clever generation of instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
public final class CleverGenerationWindow extends JDialog implements ICleverConfigurationGeneratedActionPerformer,
		ICleverConfigurationGeneratedActionListener, ICleverConfigurationGenerationFailedActionListener {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -4446714162571812637L;

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
	 * The panel for the functionality for clever generation of instance finder
	 * configurations.
	 */
	private GenerationPanel generationPanel;

	/**
	 * The listener that listen about that a configuration is successfully
	 * generated.
	 */
	private Set<ICleverConfigurationGeneratedActionListener> iCleverConfigurationGenerationActionListener = new HashSet<>();

	/**
	 * The general initial lower bound.
	 */
	private final int initialDefaultLowerBound;

	/**
	 * The general initial upper bound.
	 */
	private final int initialDefaultUpperBound;

	/**
	 * Constructs an object.
	 * 
	 * @param parent
	 *            The parent window.
	 * @param settingsConfig
	 *            The instance finder configuration which will be modified with
	 *            computed values.
	 * @param model
	 *            The UML/OCL model.
	 * @param invariants
	 *            The invariants of the model.
	 * @param initialDefaultLowerBound
	 *            The general initial lower bound.
	 * @param initialDefaultUpperBound
	 *            The general initial upper bound.
	 */
	public CleverGenerationWindow(JDialog parent, SettingsConfiguration settingsConfig, IModel model,
			List<Expression> invariants, int initialDefaultLowerBound, int initialDefaultUpperBound) {
		super(parent);
		if (parent == null || settingsConfig == null || model == null || invariants == null) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
		this.settingsConfig = settingsConfig;
		this.model = model;
		this.invariants = invariants;
		this.initialDefaultLowerBound = initialDefaultLowerBound;
		this.initialDefaultUpperBound = initialDefaultUpperBound;
		this.initGUI();
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
			this.generationPanel = new GenerationPanel(this, settingsConfig, model, invariants,
					initialDefaultLowerBound, initialDefaultUpperBound);
			this.setContentPane(this.generationPanel);
			this.generationPanel.addICleverConfigurationGenerationActionListener(this);
			this.generationPanel.addICleverConfigurationGenerationFailedActionListener(this);
			this.setLocationRelativeTo(parent);
			this.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, UIElements.ErrorDialog_Message, UIElements.ErrorDialog_Title,
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	@Override
	public Set<ICleverConfigurationGeneratedActionListener> getICleverConfigurationGenerationActionListener() {
		return this.iCleverConfigurationGenerationActionListener;
	}

	@Override
	public void configurationGeneratedActionPerformed() {
		this.setEnabled(false);
		this.dispose();
		this.performConfigurationGeneratedAction();
		parent.setEnabled(true);
	}

	@Override
	public void configurationGenerationFailedActionPerformed(String message) {
		this.setEnabled(false);
		JOptionPane.showMessageDialog(this, UIElements.GenerationFailedBaseMessage(message),
				UIElements.ErrorDialog_Title, JOptionPane.ERROR_MESSAGE);
		this.dispose();
		parent.setEnabled(true);
	}

}
