package org.tzi.kodkod.comparison.ui.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.tzi.kodkod.comparison.ui.UIElements;
import org.tzi.kodkod.model.config.ConfigurationFileManager;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.kodkod.plugin.gui.util.ChangeConfiguration;

/**
 * The window for the functionality for comparing instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
public final class ComparisonWindow extends JDialog implements IComparisonGUIComponent {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -44476771991355185L;

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
	 * The configurations to be compared, identified by their names.
	 */
	private final Map<String, SettingsConfiguration> configurationsForNames;

	/**
	 * Constructs an object.
	 * 
	 * @param parent
	 *            The parent window.
	 * @param nameOfCurrentlyLoadedConfig
	 *            The name of the potentially modified configuration.
	 * @param currentlyLoadedConfig
	 *            The potentially modified configuration.
	 * @param configManager
	 *            The manager for configurations that contains all other
	 *            configurations to be compared.
	 * @param model
	 *            The UML/OCL model.
	 */
	public ComparisonWindow(final JDialog parent, final String nameOfCurrentlyLoadedConfig,
			final SettingsConfiguration currentlyLoadedConfig, final ConfigurationFileManager configManager,
			final IModel model) {
		super(parent);
		this.parent = parent;
		this.configurationsForNames = getConfigurations(nameOfCurrentlyLoadedConfig, currentlyLoadedConfig,
				configManager, model);
		this.initGUI();
	}

	/**
	 * Extracts the configurations from an manager for configurations while using
	 * the modified version of one specific configuration instead of the managed
	 * one.
	 * 
	 * @param nameOfCurrentlyLoadedConfig
	 *            The name of the potentially modified configuration.
	 * @param currentlyLoadedConfig
	 *            The potentially modified configuration.
	 * @param configManager
	 *            The manager for configurations that contains all other
	 *            configurations to be compared.
	 * @param model
	 *            The UML/OCL model.
	 * @return
	 */
	private static Map<String, SettingsConfiguration> getConfigurations(final String nameOfCurrentlyLoadedConfig,
			final SettingsConfiguration currentlyLoadedConfig, final ConfigurationFileManager configManager,
			final IModel model) {
		final String[] configNames = exclude(new String[] { nameOfCurrentlyLoadedConfig },
				configManager.getConfigurationNames());
		final Map<String, SettingsConfiguration> configurationsForNames = new HashMap<String, SettingsConfiguration>();
		configurationsForNames.put(nameOfCurrentlyLoadedConfig, currentlyLoadedConfig);
		for (String configurationName : configNames) {
			final SettingsConfiguration configuration = new SettingsConfiguration(model);
			ChangeConfiguration.toSettings(model, configManager.getConfiguration(configurationName), configuration);
			configurationsForNames.put(configurationName, configuration);
		}
		return configurationsForNames;
	}

	/**
	 * Constructs an array containing all strings from one array but not the strings
	 * from an other array.
	 * 
	 * @param exlcuded
	 *            The strings to be excluded.
	 * @param all
	 *            The strings that should be used when not excluded.
	 * @return An array containing all strings from one array but not the strings
	 *         from an other array.
	 */
	private static String[] exclude(final String[] exlcuded, final String[] all) {
		ArrayList<String> reduced = new ArrayList<String>();
		if (exlcuded != null) {
			for (String one : all) {
				boolean add = true;
				for (String excludedName : exlcuded) {
					if ((one == null && excludedName == null) || (one != null && one.equals(excludedName))) {
						add = false;
					}
				}
				if (add) {
					reduced.add(one);
				}
			}
		}
		return reduced.toArray(new String[] {});
	}

	@Override
	public void dispose() {
		super.dispose();
		parent.setEnabled(true);
	}

	@Override
	public void initGUI() {
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
			this.setContentPane(new ComparisonPanel(this.configurationsForNames));
			this.setLocationRelativeTo(parent);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, UIElements.ErrorDialog_Message, UIElements.ErrorDialog_Title,
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	@Override
	public void reinitializeGUI() {
		this.initGUI();
		this.revalidate();
		this.repaint();
	}

}
