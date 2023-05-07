package org.tzi.use.kodkod.plugin;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.tzi.kodkod.clever.CleverConfigurationGenerator;
import org.tzi.kodkod.clever.GenerationException;
import org.tzi.kodkod.clever.model2csp.CLIUtilForInitialBoundspecification;
import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.clever.ui.UIElements;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.main.shell.runtime.IPluginShellCmd;

/**
 * CLI plugin for clever generation of configurations.
 * 
 * @author Jan Prien
 *
 */
public class CleverConfigurationGenerationCmd extends AbstractConfigurationProcessingCmd {

	/**
	 * The logger.
	 */
	protected static final Logger LOG = Logger.getLogger(ConfigurationValidationCmd.class);

	/**
	 * CLI flag.
	 * 
	 * @author Jan Prien
	 *
	 */
	private final class CLIFlag {

		/**
		 * The short key.
		 */
		private final String shortFlag;

		/**
		 * The long key.
		 */
		private final String longFlag;

		/**
		 * The value.
		 */
		private boolean value;

		/**
		 * Constructs an object.
		 * 
		 * @param shortFlag
		 *            The short key.
		 * @param longFlag
		 *            The long key.
		 * @param defaultValue
		 *            The value.
		 */
		public CLIFlag(String shortFlag, String longFlag, boolean defaultValue) {
			if (shortFlag == null || longFlag == null) {
				throw new IllegalArgumentException();
			}
			this.shortFlag = shortFlag;
			this.longFlag = longFlag;
			this.value = defaultValue;
		}

		public void setValue(boolean value) {
			this.value = value;
		}

		public boolean getValue() {
			return value;
		}

		/**
		 * Returns whether the flags are equal.
		 * 
		 * @param flag
		 *            The other flag.
		 * @return Whether the flags are equal.
		 */
		public boolean is(String flag) {
			return shortFlag.equals(flag) || longFlag.equals(flag);
		}

	}

	/**
	 * Manager for CLI flags.
	 * 
	 * @author Jan Prien
	 *
	 */
	private final class CLIFlagManager {

		/**
		 * The CLI flags.
		 */
		private Set<CLIFlag> cliFlags = new LinkedHashSet<>();

		/**
		 * Constructs an object.
		 */
		public CLIFlagManager() {

		}

		/**
		 * Adds af flag.
		 * 
		 * @param cliFlag
		 *            The flag.
		 * @return The flag.
		 */
		public CLIFlag add(CLIFlag cliFlag) {
			if (cliFlag == null) {
				throw new IllegalArgumentException();
			}
			cliFlags.add(cliFlag);
			return cliFlag;
		}

		/**
		 * Consumes arguments. Modifies the values of the flags.
		 * 
		 * @param arguments
		 *            The arguments.
		 * @return The arguments.
		 */
		public String[] consume(String[] arguments) {
			if (arguments == null) {
				throw new IllegalArgumentException();
			}
			if (arguments.length > 0) {
				String argument = arguments[0];
				for (CLIFlag cliFlag : cliFlags) {
					if (cliFlag.is(argument)) {
						cliFlag.setValue(true);
						return consume(Arrays.copyOfRange(arguments, 1, arguments.length));
					}
				}
			}
			return arguments;
		}

	}

	@Override
	public void performCommand(IPluginShellCmd pluginCommand) {
		if (!pluginCommand.getSession().hasSystem()) {
			LOG.error(UIElements.CLI_NoModelLoaded);
			return;
		}

		String[] arguments = pluginCommand.getCmdArgumentList();
		arguments = parseArguments(arguments);
		if (arguments.length < 1) {
			LOG.error(UIElements.CLI_NoOutputFileArgument);
			return;
		}
		final String configFilePath = extractConfigFilePath(arguments, LOG);

		CLIFlagManager cliFlagManager = new CLIFlagManager();
		CLIFlag repsectOclConstraintsFlag = cliFlagManager
				.add(new CLIFlag(UIElements.CLI_respectOCLContraintsOption_Short,
						UIElements.CLI_respectOCLContraintsOption_Long, false));
		CLIFlag generelizeAttributeUpperBoundsFlag = cliFlagManager
				.add(new CLIFlag(UIElements.CLI_generalizeAttributeUpperBoundsOption_Short,
						UIElements.CLI_generalizeAttributeUpperBoundsOption_Long, false));
		CLIFlag mandatorizeAttributesFlag = cliFlagManager
				.add(new CLIFlag(UIElements.CLI_mandatorizeAttributesOption_Short,
						UIElements.CLI_mandatorizeAttributesOption_Long, false));
		String[] unhandledArguments = cliFlagManager.consume(Arrays.copyOfRange(arguments, 1, arguments.length));

		// TODO remove this, when IModelAndExpressionToCSPTranslator supports including
		// of OCL constraints.
		// Prevents calling unsupported functionality.
		if (repsectOclConstraintsFlag.getValue()) {
			LOG.warn("Including OCL constraints in generation is currently not supported.");
			return;
		}

		initialize(pluginCommand.getSession());
		final IModel model = model();

		IModelCSPVariablesInitialBoundsSpecification initialBoundsSpecification = CLIUtilForInitialBoundspecification
				.readInitialBoundsSpecification(unhandledArguments, model, LOG);
		if (initialBoundsSpecification == null) {
			LOG.error(UIElements.CLI_InitialBoundsSpecificationNotProcessable);
			return;
		}
		LoadedConfigFileManager loadedConfigFileManager = LoadedConfigFileManager.generateEditableState(model,
				configFilePath);
		final Map<String, SettingsConfiguration> configurationsForNames = loadedConfigFileManager.getConfigurations();
		if (configurationsForNames == null || configurationsForNames.size() != 1) {
			throw new UnsupportedOperationException();
		}
		SettingsConfiguration config = configurationsForNames.get(configurationsForNames.keySet().toArray()[0]);
		final CleverConfigurationGenerator cleverConfigurationGenerator = new CleverConfigurationGenerator();
		try {
			cleverConfigurationGenerator.generate(initialBoundsSpecification, extractInvariants(),
					repsectOclConstraintsFlag.getValue(), generelizeAttributeUpperBoundsFlag.getValue(),
					mandatorizeAttributesFlag.getValue(), config);
		} catch (GenerationException e) {
			LOG.error(UIElements.CLI_GenerationFailed + " " + e.getMessage());
			return;
		}
		try {
			loadedConfigFileManager.saveCurrentState();
		} catch (IOException e) {
			LOG.error(UIElements.CLI_FileSavingFailed(configFilePath));
			return;
		}
		LOG.error(UIElements.CLI_FileSaved(configFilePath));

	}

}
