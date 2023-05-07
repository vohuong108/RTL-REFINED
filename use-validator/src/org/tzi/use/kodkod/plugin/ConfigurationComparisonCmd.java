package org.tzi.use.kodkod.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.tzi.kodkod.comparison.ComparisonResult;
import org.tzi.kodkod.comparison.SettingsConfigurationComparator;
import org.tzi.kodkod.comparison.ui.UIElements;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.main.shell.runtime.IPluginShellCmd;

/**
 * CLI plugin for comparison of configuration.
 * 
 * @author Jan Prien
 *
 */
public class ConfigurationComparisonCmd extends AbstractConfigurationProcessingCmd {

	/**
	 * The logger.
	 */
	protected static final Logger LOG = Logger.getLogger(ConfigurationComparisonCmd.class);

	@Override
	public void performCommand(IPluginShellCmd pluginCommand) {
		if (!pluginCommand.getSession().hasSystem()) {
			LOG.error(UIElements.CLI_NoModelLoaded);
			return;
		}

		String[] arguments = pluginCommand.getCmdArgumentList();
		arguments = parseArguments(arguments);
		if (arguments == null || !validateArguments(arguments, LOG)) {
			return;
		}
		String configFilePath = extractConfigFilePath(arguments, LOG);
		String[] names = extractNames(arguments, LOG);

		initialize(pluginCommand.getSession());
		final IModel model = model();

		final Map<String, SettingsConfiguration> configurationsForNames = LoadedConfigFileManager
				.loadEditableState(model, configFilePath).getConfigurations();
		if (configurationsForNames == null) {
			return;
		}
		if (!validateNameArguments(configurationsForNames, names, LOG)) {
			return;
		}
		filterForNameArguments(configurationsForNames, names);
		Object[][] comparisons;
		try {
			comparisons = processComparisons(configurationsForNames);
		} catch (Exception e) {
			LOG.error(UIElements.CLI_ComparisonFailed);
			return;
		}
		final String log = generateLog(comparisons);
		LOG.info(log);

	}

	/**
	 * Validates arguments in terms of this plugin. This must have one or more than
	 * two arguments.
	 * 
	 * @param arguments
	 *            The arguments.
	 * @param log
	 *            The logger.
	 * @return Whther the arguments are valid.
	 */
	private boolean validateArguments(String[] arguments, Logger log) {
		if (arguments == null || log == null) {
			throw new IllegalArgumentException();
		}
		if (arguments.length < 1) {
			LOG.error(UIElements.CLI_NoConfigFileArgument);
			return false;
		} else if (arguments.length == 2) {
			LOG.error(UIElements.CLI_OnlyOneConfigNameArgument);
			return false;
		}
		return true;
	}

	/**
	 * Validates configuration names given as arguments.
	 * 
	 * @param configurationsForNames
	 *            The existing configurations for their names.
	 * @param names
	 *            The configuration names given as arguments.
	 * @param LOG
	 *            The logger.
	 * @return Whether at least two different valid configurations names are given.
	 */
	private static boolean validateNameArguments(Map<String, SettingsConfiguration> configurationsForNames,
			String[] names, final Logger LOG) {
		if (configurationsForNames == null || names == null || LOG == null) {
			throw new IllegalArgumentException();
		}
		if (names.length != 0) {
			List<String> validNames = new ArrayList<>();
			List<String> namesWithoutDuplicates = new ArrayList<>();
			List<String> duplicatedNames = new ArrayList<>();
			for (String name : names) {
				if (!namesWithoutDuplicates.contains(name)) {
					namesWithoutDuplicates.add(name);
				} else {
					duplicatedNames.add(name);
				}
			}
			List<String> notContainedNames = new ArrayList<>();
			for (String name : names) {
				if (!configurationsForNames.containsKey(name)) {
					notContainedNames.add(name);
				} else if (!validNames.contains(name)) {
					validNames.add(name);
				}
			}
			if (!duplicatedNames.isEmpty()) {
				LOG.warn(UIElements.CLI_DuplicatedNames(Arrays.toString(duplicatedNames.toArray())));
			}
			if (!notContainedNames.isEmpty()) {
				LOG.warn(UIElements.CLI_InvalidNames(Arrays.toString(notContainedNames.toArray())));
			}
			if (validNames.size() < 2) {
				LOG.error(UIElements.CLI_InvalidNames);
				return false;
			}
		}
		return true;
	}

	/**
	 * Processes the comparison of all configurations pairwise but not with inverted
	 * roles.
	 * 
	 * @param configurationsForNames
	 *            The configurations for their names.
	 * @return The first operand, second operand and result for each processed
	 *         comparison.
	 * @throws Exception
	 *             If the comparison fails.
	 */
	private static Object[][] processComparisons(final Map<String, SettingsConfiguration> configurationsForNames)
			throws Exception {
		Object[][] comparisons = new Object[binomi(configurationsForNames.size(), 2)][3];
		int comparisonsIndex = -1;
		int leftIndex = -1;
		for (Entry<String, SettingsConfiguration> leftConfigurationForName : configurationsForNames.entrySet()) {
			leftIndex++;
			String leftName = leftConfigurationForName.getKey();
			int rightIndex = -1;
			for (Entry<String, SettingsConfiguration> rightConfigurationForName : configurationsForNames.entrySet()) {
				rightIndex++;
				String rightName = rightConfigurationForName.getKey();
				boolean alreadyCompared = false;
				for (int index = 0; index <= comparisonsIndex; index++) {
					alreadyCompared = alreadyCompared
							|| comparisons[index][0].equals(leftName) && comparisons[index][1].equals(rightName)
							|| comparisons[index][0].equals(rightName) && comparisons[index][1].equals(leftName);
				}
				if (leftIndex != rightIndex && !alreadyCompared) {
					comparisonsIndex++;
					comparisons[comparisonsIndex][0] = leftName;
					comparisons[comparisonsIndex][1] = rightName;
					comparisons[comparisonsIndex][2] = new SettingsConfigurationComparator()
							.compareSettingsConfigurations(leftConfigurationForName.getValue(),
									rightConfigurationForName.getValue());
				}
			}
		}
		return comparisons;
	}

	/**
	 * Generates the output for processed comparison.
	 * 
	 * @param comparisons
	 *            The first operand, second operand and result for each processed
	 *            comparison.
	 * @return The CLI output.
	 */
	private static String generateLog(final Object[][] comparisons) {
		int longestNameLeft = 0;
		int longestNameRight = 0;
		int longestDescriptorLength = 0;
		for (int index = 0; index < comparisons.length; index++) {
			int leftNameLength = ((String) comparisons[index][0]).length();
			if (leftNameLength > longestNameLeft) {
				longestNameLeft = leftNameLength;
			}
			int rightNameLength = ((String) comparisons[index][1]).length();
			if (rightNameLength > longestNameRight) {
				longestNameRight = rightNameLength;
			}
			int descriptorLength = ((ComparisonResult) comparisons[index][2]).getDescriptor().length();
			if (descriptorLength > longestDescriptorLength) {
				longestDescriptorLength = descriptorLength;
			}
		}
		int longestNumLength = (comparisons.length + " ").length();
		String details = "Comparison Results:\n\nDetails:\n\n";
		String overview = "Overview:\n\n";
		for (int index = 0; index < comparisons.length; index++) {
			int num = index + 1;
			String leftName = (String) comparisons[index][0];
			String rightName = (String) comparisons[index][1];
			ComparisonResult result = (ComparisonResult) comparisons[index][2];
			details += num + ". " + leftName + " " + result.getDescriptor() + " " + rightName + " : \n"
					+ result.getText() + "\n";
			overview += String.format(
					"| %" + longestNumLength + "s | " + "%-" + longestDescriptorLength + "s | " + "%-" + longestNameLeft
							+ "s | " + "%-" + longestNameRight + "s |\n",
					num, result.getDescriptor(), leftName, rightName);
		}
		List<Set<String>> families = getFamilies(comparisons);
		if (families.size() > 0) {
			overview += "\nThe comparison shows " + families.size() + (families.size() != 1 ? " families" : " family")
					+ " with more than two members:\n";
			int num = 0;
			for (Set<String> family : families) {
				num++;
				overview += num + ": " + family + "\n";
			}
		}
		return details + "\n" + overview;
	}

	/**
	 * Computes the binomial coefficient.
	 * 
	 * @param n
	 *            n
	 * @param k
	 *            k
	 * @return The binomial coefficient.
	 */
	private static int binomi(int n, int k) {
		if (n < k) {
			throw new IllegalArgumentException();
		}
		if ((n == k) || (k == 0))
			return 1;
		else
			return binomi(n - 1, k) + binomi(n - 1, k - 1);
	}

	/**
	 * Computes the families of configurations (names) that are related regarding
	 * the search space the represent.
	 * 
	 * @param comparisons
	 *            The first operand, second operand and result for each processed
	 *            comparison.
	 * @return The families.
	 */
	private static List<Set<String>> getFamilies(final Object[][] comparisons) {
		List<Set<String>> families = new ArrayList<>();
		for (int index = 0; index < comparisons.length; index++) {
			String leftName = (String) comparisons[index][0];
			Set<String> leftFamily = getFirstListThatContains(families, leftName);
			String rightName = (String) comparisons[index][1];
			Set<String> rightFamily = getFirstListThatContains(families, rightName);
			if (((ComparisonResult) comparisons[index][2]).indicatesFamilyRelation()) {
				if (leftFamily == null && rightFamily == null) {
					Set<String> newFamily = new HashSet<String>();
					newFamily.add(leftName);
					newFamily.add(rightName);
					families.add(newFamily);
				} else if (rightFamily == null) {
					leftFamily.add(rightName);
				} else if (leftFamily == null) {
					rightFamily.add(leftName);
				} else if (leftFamily != rightFamily) {
					leftFamily.addAll(rightFamily);
					families.remove(rightFamily);
				}
			}
		}
		return families;
	}

	/**
	 * Returns the first set of list of strings that contains a string.
	 * 
	 * @param sets
	 *            The set of list of strings.
	 * @param string
	 *            The string.
	 * @return The first set of list of strings that contains the string.
	 */
	private static Set<String> getFirstListThatContains(final List<Set<String>> sets, final String string) {
		if (sets != null) {
			for (Set<String> set : sets) {
				if (set.contains(string)) {
					return set;
				}
			}
		}
		return null;
	}

}
