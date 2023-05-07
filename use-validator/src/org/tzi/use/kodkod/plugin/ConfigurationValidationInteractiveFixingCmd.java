package org.tzi.use.kodkod.plugin;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedMap;

import org.apache.log4j.Logger;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.AbstractFix;
import org.tzi.kodkod.validation.ValidityRule;
import org.tzi.kodkod.validation.ValidityRuleValidator;
import org.tzi.kodkod.validation.ValidityRuleViolence;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.main.shell.runtime.IPluginShellCmd;

/**
 * CLI plugin for validation and interactive fixing of configurations.
 * 
 * @author Jan Prien
 *
 */
public final class ConfigurationValidationInteractiveFixingCmd extends AbstractConfigurationProcessingCmd {

	/**
	 * The logger.
	 */
	protected static final Logger LOG = Logger.getLogger(ConfigurationValidationInteractiveFixingCmd.class);

	@Override
	public void performCommand(IPluginShellCmd pluginCommand) {
		if (!pluginCommand.getSession().hasSystem()) {
			LOG.error(UIElements.CLI_NoModelLoaded);
			return;
		}

		String[] arguments = pluginCommand.getCmdArgumentList();
		arguments = parseArguments(arguments);
		if (arguments.length < 1) {
			LOG.error(UIElements.CLI_NoConfigFileAndNameArgument);
			return;
		}
		if (arguments.length < 2) {
			LOG.error(UIElements.CLI_NoConfigNameArgument);
			return;
		}
		if (arguments.length > 2) {
			LOG.error(UIElements.CLI_TooMuchArguments);
			return;
		}
		String configFilePath = extractConfigFilePath(arguments, LOG);
		String name = extractName(arguments, LOG);

		initialize(pluginCommand.getSession());
		final IModel model = model();

		LoadedConfigFileManager loadedConfigFileManager = LoadedConfigFileManager.loadEditableState(model,
				configFilePath);
		final Map<String, SettingsConfiguration> configurationsForNames = loadedConfigFileManager.getConfigurations();
		if (configurationsForNames == null) {
			return;
		}
		if (!configurationsForNames.containsKey(name)) {
			LOG.error("No configuration with name \"" + name + "\" is contained in file \"" + configFilePath + "\".");
			return;
		}

		AbstractFix[] fixes = processValidationInteractiveFixing(model, configurationsForNames.get(name), name,
				System.out, LOG);
		while (true) {
			final String msgPart1 = "Please ";
			final String msgPart2 = "choose a fix to apply by its number\n";
			final String msgPart3 = " or ";
			final String msgPart4 = "type \"0\" to save the configuration in the current state and terminate this fixing process\n"
					+ "or \"-1\" to terminate this fixing process without saving.\n";
			if (fixes == null || fixes.length == 0) {
				System.out.print(msgPart1 + msgPart4);
				fixes = new AbstractFix[0];
			} else {
				System.out.print(msgPart1 + msgPart2 + msgPart3 + msgPart4);
			}
			System.out.print("option: ");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int input = scanner.nextInt();
			System.out.println();
			if (input < -1) {
				System.out.println("\"" + input + "\" is no option.");
				continue;
			} else if (input == -1) {
				break;
			} else if (input == 0) {
				try {
					loadedConfigFileManager.saveCurrentState();
				} catch (IOException e) {
					LOG.error("Configuration could not be saved.");
				}
				break;
			} else if (input > fixes.length) {
				System.out.println("\"" + input + "\" is no option.");
				continue;
			} else {
				try {
					fixes[input - 1].apply();
				} catch (Exception e) {
					LOG.error("Fix \"" + fixes[input - 1].getExplenation() + "\" could not be applied.");
				}
				fixes = processValidationInteractiveFixing(model, configurationsForNames.get(name), name, System.out,
						LOG);
			}

		}
	}

	/**
	 * Processes the validation with creating the ouptut.
	 * 
	 * @param model
	 *            The class diagram of the UML/OCL model.
	 * @param settingsConfiguration
	 *            The configuration for the UML/OCL model.
	 * @param name
	 *            The name of the configuraiton.
	 * @param out
	 *            The out stream.
	 * @param log
	 *            The logger.
	 * @return The applicable fixes referenced in the output by their index +1.
	 */
	private AbstractFix[] processValidationInteractiveFixing(IModel model, SettingsConfiguration settingsConfiguration,
			String name, PrintStream out, Logger log) {
		String logString = "Validation results:\n\n";
		SortedMap<ValidityRule, ValidityRuleViolence[]> violationsByValdityRules;
		try {
			violationsByValdityRules = new ValidityRuleValidator().validate(settingsConfiguration, model,
					extractInvariants());
		} catch (Exception e) {
			log.error("Config " + name + " could not be validated.");
			return null;
		}
		if (violationsByValdityRules.size() == 0) {
			logString += "Config " + name + " is valid.\n\n";
		} else {
			final List<AbstractFix> allFixes = new ArrayList<>();
			logString += "Config " + name + " is invalid.\n";
			for (Entry<ValidityRule, ValidityRuleViolence[]> violationsByValdityRule : violationsByValdityRules
					.entrySet()) {
				logString += "    Rule " + violationsByValdityRule.getKey().name() + " is violated.\n";
				for (ValidityRuleViolence violation : violationsByValdityRule.getValue()) {
					logString += "        " + violation.getExplenation() + "\n";
					AbstractFix[] fixes = violation.getFixes();
					if (fixes.length == 1) {
						allFixes.add(fixes[0]);
						final String number = "(" + allFixes.size() + ")";
						logString += "            To get closer to validity perform the following fix:\n";
						logString += number + "                ".substring(number.length())
								+ (fixes[0].resolvesProblemCompletely() ? "[fF]" : "[pF]") + " | "
								+ fixes[0].getExplenation() + "\n";
					} else if (fixes.length > 1) {
						logString += "            To get closer to validity perform one of the following fixes:\n";
						for (AbstractFix fix : fixes) {
							allFixes.add(fix);
							final String number = "(" + allFixes.size() + ")";
							logString += number + "                ".substring(number.length())
									+ (fix.resolvesProblemCompletely() ? "[fF]" : "[pF]") + " | " + fix.getExplenation()
									+ "\n";
						}
					}
				}
			}
			out.println(logString);
			return allFixes.toArray(new AbstractFix[allFixes.size()]);
		}
		out.println(logString);
		return new AbstractFix[0];
	}

	/**
	 * Extracts the configuration name from arguments. The name must be given on
	 * second place.
	 * 
	 * @param arguments
	 *            The arguments.
	 * @param log
	 *            The logger.
	 * @return The name.
	 */
	protected static String extractName(String[] arguments, Logger log) {
		if (arguments == null || arguments.length != 2 || log == null) {
			throw new IllegalArgumentException();
		}
		return arguments[1];
	}
}
