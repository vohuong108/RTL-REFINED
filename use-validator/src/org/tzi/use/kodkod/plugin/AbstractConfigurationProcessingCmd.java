package org.tzi.use.kodkod.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;
import org.tzi.kodkod.model.config.ConfigurationFileManager;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.kodkod.plugin.gui.util.ChangeConfiguration;
import org.tzi.use.main.shell.Shell;
import org.tzi.use.runtime.shell.IPluginShellCmdDelegate;

/**
 * Abstract CLI plugin for processing a configuration.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractConfigurationProcessingCmd extends ConfigurablePlugin implements IPluginShellCmdDelegate {

	/**
	 * Decodes arguments given as string.
	 * 
	 * @param rawArguments
	 *            The arguments.
	 * @return The decoded arguments.
	 */
	protected final String[] parseArguments(String[] rawArguments) {
		List<String> arguments = new ArrayList<String>();
		String currentArgument = null;
		for (String rawArgument : rawArguments) {
			if (rawArgument.startsWith("\\\"")) {
				if (currentArgument != null) {
					return null;
				} else {
					currentArgument = rawArgument.substring(2);
					continue;
				}
			}
			if (rawArgument.endsWith("\\\"") && !rawArgument.endsWith("\\\\\\\"")) {
				if (currentArgument == null) {
					return null;
				} else {
					currentArgument += " " + rawArgument.substring(0, rawArgument.length() - 2);
					arguments.add(unescape(currentArgument));
					currentArgument = null;
					continue;
				}
			}
			if (currentArgument != null) {
				currentArgument += " " + rawArgument;
			} else {
				arguments.add(unescape(rawArgument));
			}

		}
		if (currentArgument != null) {
			return null;
		}
		return arguments.toArray(new String[arguments.size()]);
	}

	/**
	 * Decodes arguments given as string.
	 * 
	 * @param rawString
	 *            The argument
	 * @return The decoded argument.
	 */
	private static String unescape(String rawString) {
		if (rawString == null) {
			return null;
		}
		String string = "";
		String rest = rawString;
		while (rest.length() > 0) {
			String toBeReplaced = "\\\\\\\"";
			if (rest.startsWith(toBeReplaced)) {
				string += "\"";
				rest = rest.substring(toBeReplaced.length());
			} else {
				string += rest.substring(0, 1);
				rest = rest.substring(1);
			}
		}
		return string;
	}

	/**
	 * Extracts the path of the configuration file that must be given as first
	 * argument.
	 * 
	 * @param arguments
	 *            The arguments.
	 * @param log
	 *            A logger.
	 * @return The path of the configuration file.
	 */
	protected static String extractConfigFilePath(String[] arguments, Logger log) {
		if (arguments == null) {
			throw new IllegalArgumentException();
		}
		return arguments[0];
	}

	/**
	 * Extracts the configuration names from arguments.
	 * 
	 * @param arguments
	 *            The arguments
	 * @param log
	 *            The logger.
	 * @return The configuration names.
	 */
	protected static String[] extractNames(String[] arguments, Logger log) {
		if (arguments == null || arguments.length < 1 || log == null) {
			throw new IllegalArgumentException();
		}
		String[] names = new String[arguments.length - 1];
		for (int index = 1; index < arguments.length; index++) {
			names[index - 1] = arguments[index];
		}
		if (names.length == 0) {
			LOG.info("All configs from file will be used.");
		}
		return names;
	}

	/**
	 * Filters arguments configuration names by existence.
	 * 
	 * @param configurationsForNames
	 *            The existent configuration for their names.
	 * @param names
	 *            The arguments configuration names.
	 */
	protected static void filterForNameArguments(Map<String, SettingsConfiguration> configurationsForNames,
			String[] names) {
		if (configurationsForNames == null || names == null || LOG == null) {
			throw new IllegalArgumentException();
		}
		if (names.length != 0) {
			Set<String> toBeRemoved = new HashSet<>();
			for (String nameKey : configurationsForNames.keySet()) {
				boolean remove = true;
				for (String name : names) {
					if (nameKey.equals(name)) {
						remove = false;
						break;
					}
				}
				if (remove) {
					toBeRemoved.add(nameKey);
				}
			}
			if (!(toBeRemoved.size() == configurationsForNames.keySet().size())) {
				for (String name : toBeRemoved) {
					configurationsForNames.remove(name);
				}
			}
		}
	}

	/**
	 * Manager for loaded configuration files.
	 * 
	 * @author Jan Prien
	 *
	 */
	static class LoadedConfigFileManager {

		/**
		 * The logger.
		 */
		protected static final Logger LOG = Logger.getLogger(LoadedConfigFileManager.class);

		/**
		 * The class diagram of the UML/OCL model.
		 */
		private final IModel model;

		/**
		 * The configuration file.
		 */
		private final File file;

		/**
		 * The manager for the configuration file.
		 */
		private final ConfigurationFileManager configManager;

		/**
		 * The loaded configurations from the file for their names.
		 */
		private final Map<String, SettingsConfiguration> configs;

		/**
		 * Constructs an object.
		 * 
		 * @param model
		 *            The class diagram of the UML/OCL model.
		 * @param file
		 *            The configuration file.
		 * @param configManager
		 *            The manager for the configuration file.
		 */
		private LoadedConfigFileManager(IModel model, File file, ConfigurationFileManager configManager) {
			this.model = model;
			this.file = file;
			this.configManager = configManager;
			this.configs = getConfigurations(configManager, model);
		}

		/**
		 * Constructs an object.
		 * 
		 * @param model
		 *            The class diagram of the UML/OCL model.
		 * @param configFilePath
		 *            The path to the configuration file.
		 * @return The manager for loaded configuration files.
		 */
		public static LoadedConfigFileManager generateEditableState(IModel model, String configFilePath) {
			if (model == null || configFilePath == null) {
				throw new IllegalArgumentException();
			}
			return new LoadedConfigFileManager(model,
					new File(Shell.getInstance().getFilenameToOpen(configFilePath.trim(), false)),
					new ConfigurationFileManager(model, new SettingsConfiguration(model)));
		}

		/**
		 * Constructs an object.
		 * 
		 * @param model
		 *            The class diagram of the UML/OCL model.
		 * @param configFilePath
		 *            The path to the configuration file.
		 * @return The manager for loaded configuration files.
		 */
		public static LoadedConfigFileManager loadEditableState(IModel model, String configFilePath) {
			if (model == null || configFilePath == null) {
				throw new IllegalArgumentException();
			}
			String filepath = Shell.getInstance().getFilenameToOpen(configFilePath.trim(), false);
			File file = new File(filepath);
			if (!file.exists() || !file.canRead() || file.isDirectory()) {
				LOG.error("Could not read from file.");
				return null;
			}
			final ConfigurationFileManager configManager;
			try {
				configManager = new ConfigurationFileManager(model, new SettingsConfiguration(model), file);
			} catch (ConfigurationException e) {
				LOG.error("Could not extract configurations from file.");
				return null;
			}
			return new LoadedConfigFileManager(model, file, configManager);
		}

		/**
		 * Loads configurations from a file.
		 * 
		 * @param configManager
		 *            The manager for the configuration file.
		 * @param model
		 *            The class diagram of the UML/OCL model.
		 * @return The loaded configurations for their names.
		 */
		private static Map<String, SettingsConfiguration> getConfigurations(
				final ConfigurationFileManager configManager, final IModel model) {
			final String[] configNames = configManager.getConfigurationNames();
			final Map<String, SettingsConfiguration> configurationsForNames = new TreeMap<String, SettingsConfiguration>();
			for (String configurationName : configNames) {
				final SettingsConfiguration configuration = new SettingsConfiguration(model);
				ChangeConfiguration.toSettings(model, configManager.getConfiguration(configurationName), configuration);
				configurationsForNames.put(configurationName, configuration);
			}
			return configurationsForNames;
		}

		public Map<String, SettingsConfiguration> getConfigurations() {
			return configs;
		}

		/**
		 * Saves the current state of the configurations.
		 * 
		 * @throws IOException
		 *             If the saving fails.
		 */
		public void saveCurrentState() throws IOException {
			for (Entry<String, SettingsConfiguration> configByName : configs.entrySet()) {
				configManager.addOrUpdateConfiguration(configByName.getKey(),
						ChangeConfiguration.toProperties(configByName.getValue(), model));
			}
			configManager.save(file);
		}

	}

}
