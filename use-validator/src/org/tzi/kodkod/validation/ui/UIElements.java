package org.tzi.kodkod.validation.ui;

import java.util.List;
import java.util.Set;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.type.TypeConstants;

/**
 * Provider of constant values used in the UIs for the functionality for
 * validation of instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
public final class UIElements {

	/**
	 * The GUI validation window title.
	 */
	public static final String MainWindow_Title = "Configuration validation";

	/**
	 * The GUI main window text.
	 */
	public static final String MainWindow_Text = "The configuration is not valid due to the violations of the following rules:";

	/**
	 * The GUI error dialog title.
	 */
	public static final String ErrorDialog_Title = "Error";

	/**
	 * The GUI info dialog title for when a configuration is no invalid..
	 */
	public static final String InfoDialog_Title = "Info";

	/**
	 * The GUI info dialog message for when a configuration is no invalid..
	 */
	public static final String InfoDialog_Message = "The configuration is valid.";

	/**
	 * The GUI error dialog general message.
	 */
	public static final String ErrorDialog_Message = "An internal error occured.";

	/**
	 * The GUI iterative fixing process checkbox label.
	 */
	public static final String MainWindow_CloseWindowAfterFixApplyCheckBox_Title = "Close window after applying fix";

	/**
	 * The label for full fixes.
	 */
	public static final String FullFixLabel = "FF";

	/**
	 * The label for partial fixes.
	 */
	public static final String PartialFixLabel = "PF";

	/**
	 * The GUI tooltip for partial and full fixes.
	 */
	public static final String FullFixPartialFixLabel_Tooltip = "Indicates whether applying the fix would resolve the violation completely ("
			+ FullFixLabel + ") or not (" + PartialFixLabel + ").";

	/**
	 * The GUI value for the button that applies a fix.
	 */
	public static final String FixButton_Title = "Fix";

	/**
	 * The GUI tooltip for the button that applies a fix.
	 */
	public static final String FixButton_Tooltip = "Applies this fix.";

	/**
	 * The CLI info for the case that no model is loaded.
	 */
	public static final String CLI_NoModelLoaded = "No model loaded.";

	/**
	 * The CLI info for the case that no configuration file is specified.
	 */
	public static final String CLI_NoConfigFileArgument = "No configuration file is specified.";

	/**
	 * The CLI info for the case that no configuration file and name is specified.
	 */
	public static final String CLI_NoConfigFileAndNameArgument = "No configuration file and name is specified.";

	/**
	 * The CLI info for the case that no configuration name is specified.
	 */
	public static final String CLI_NoConfigNameArgument = "No configuration name is specified.";

	/**
	 * The CLI info for the case that too much arguments are given.
	 */
	public static final String CLI_TooMuchArguments = "Too much arguments are given.";

	/**
	 * Returns the information that names are specified more than once.
	 * 
	 * @param duplicatedNames
	 *            The names specified more than once.
	 * @return The information that names are specified more than once.
	 */
	public static String CLI_MultipleNamesArguments(List<String> duplicatedNames) {
		if (duplicatedNames == null) {
			throw new IllegalArgumentException();
		}
		return "The following names, which will be processed only once, are specified more than once: "
				+ duplicatedNames;
	}

	/**
	 * Returns the information that invalid names are specified.
	 * 
	 * @param notContainedNames
	 *            The invalid names.
	 * @return The information that invalid names are specified.
	 */
	public static String CLI_UncontainedNamesArgumentss(List<String> notContainedNames) {
		if (notContainedNames == null) {
			throw new IllegalArgumentException();
		}
		return "The following names, which will be ignored further on, are not contained in the file: "
				+ notContainedNames;
	}

	/**
	 * The CLI info that there are names specified but no valid name is specified.
	 */
	public static final String CLI_NotEnoughValidNameArguments = "Multiple names are specified but not at least one valid name is contained.";

	/**
	 * The label for a class.
	 */
	public static final String Class = "Class";

	/**
	 * The label for an association.
	 */
	public static final String Association = "Association";

	/**
	 * The label for an attribute.
	 */
	public static final String Attribute = "Attribute";

	/**
	 * Returns the text that describes a fix in form of setting two configuration
	 * aspects to specific values.
	 * 
	 * @param configAttribute1
	 *            The first configuration aspect.
	 * @param configAttribute2
	 *            The second configuration aspect.
	 * @param value1
	 *            The value that should be set on the first configuration aspect.
	 * @param value2
	 *            The value that should be set on the second configuration aspect.
	 * @return The text that describes a fix in form of setting two configuration
	 *         aspects to specific values.
	 */
	public static final String SetToValue(String configAttribute1, String configAttribute2, String value1,
			String value2) {
		if (configAttribute1 == null || configAttribute2 == null || value1 == null || value2 == null) {
			throw new IllegalArgumentException();
		}
		return "Set " + configAttribute1 + " to " + value1 + " and " + configAttribute2 + " to " + value2 + ".";
	}

	/**
	 * Returns the text that describes a fix in form of setting one configuration
	 * aspects to the value of another configuration aspect.
	 * 
	 * @param configAttribute1
	 *            The configuration aspect to modify.
	 * @param configAttribute2
	 *            The configuration aspect with the value.
	 * @param value2
	 *            The value that should be set on the configuration aspects.
	 * @return The text that describes a fix in form of setting one configuration
	 *         aspects to the value of another configuration aspect.
	 */

	public static final String SetToAttributeValue(String configAttribute1, String configAttribute2, String value2) {
		if (configAttribute1 == null || configAttribute2 == null || value2 == null) {
			throw new IllegalArgumentException();
		}
		return "Set " + configAttribute1 + " to " + configAttribute2 + " (" + value2 + ").";
	}

	/**
	 * Returns the text that describes a fix in form of setting two configuration
	 * aspects to specific values.
	 * 
	 * @param configAttribute1
	 *            The first configuration aspect.
	 * @param configAttribute2
	 *            The second configuration aspect.
	 * @param value
	 *            The value that should be set on the configuration aspects.
	 * @return The text that describes a fix in form of setting two configuration
	 *         aspects to specific values.
	 */
	public static final String SetToValue(String configAttribute1, String configAttribute2, String value) {
		if (configAttribute1 == null || configAttribute2 == null || value == null) {
			throw new IllegalArgumentException();
		}
		return "Set " + configAttribute1 + " and " + configAttribute2 + " to " + value + ".";
	}

	/**
	 * Returns the text that describes a fix in form of setting a configuration
	 * aspect to specific values.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @param value
	 *            The value.
	 * @return The text that describes a fix in form of setting a configuration
	 */
	public static final String SetToValue(String configAttribute, String value) {
		if (configAttribute == null || value == null) {
			throw new IllegalArgumentException();
		}
		return "Set " + configAttribute + " to " + value + ".";
	}

	/**
	 * Returns the text that describes a fix in form of disabling a configuration
	 * aspect.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @return The text that describes a fix in form of disabling a configuration
	 *         aspect.
	 */
	public static final String Disable(String configAttribute) {
		if (configAttribute == null) {
			throw new IllegalArgumentException();
		}
		return "Disable settings for " + configAttribute + ".";
	}

	/**
	 * Returns the text that describes a fix in form of enabling a configuration
	 * aspect.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @return The text that describes a fix in form of enabling a configuration
	 *         aspect.
	 */
	public static final String Enable(String configAttribute) {
		if (configAttribute == null) {
			throw new IllegalArgumentException();
		}
		return "Enable settings for " + configAttribute + ".";
	}

	/**
	 * Returns the text that describes an invalidity. The values of two
	 * configuration aspects should be set to the number of required links.
	 * 
	 * @param configAttribute1
	 *            The first configuration aspect.
	 * @param configAttribute2
	 *            The second configuration aspect.
	 * @param value1
	 *            The value of the first configuration aspect.
	 * @param value2
	 *            The value of the second configuration aspect.
	 * @param value3
	 *            The number of required links.
	 * @return The text that describes an invalidity. The values of two
	 *         configuration aspects should be set to the number of required links.
	 */
	public static final String AssociationSettingsRequiredLinksCardinalityEqualsMinLinksAndMaxLinks(
			String configAttribute1, String configAttribute2, String value1, String value2, String value3) {
		if (configAttribute1 == null || configAttribute2 == null || value1 == null || value2 == null
				|| value3 == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " (" + value1 + ") and " + configAttribute2 + " (" + value2
				+ ") should be at least the amount of required links (" + value3 + ").";
	}

	/**
	 * Returns the text that describes an invalidity. The configuration aspect
	 * should be disabled.
	 * 
	 * @param type
	 *            The configuration aspect.
	 * @return The text that describes an invalidity. The configuration aspect
	 *         should be disabled.
	 */
	public static final String TypeSettingsUnnessessary(String type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		return "Settings for " + type + " should be disabled. There is no class which have an attribute of type " + type
				+ " and is configured to be able to have instances and to have values for that attribute.";
	}

	/**
	 * Returns the text that describes an invalidity. The configuration aspect
	 * should be enabled.
	 * 
	 * @param type
	 *            The configuration aspect.
	 * @return The text that describes an invalidity. The configuration aspect
	 *         should be enabled.
	 */
	public static final String TypeSettingsNessessary(String type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		return "Settings for " + type
				+ " should be enabled. There is at least one class which have an attribute of type " + type
				+ " and is configured to be able to have instances and to have values for that attribute.";
	}

	/**
	 * The text that describes that bounds tightening failed.
	 */
	public static final String SettingsUntightenableBounds_Uncomputeable = "Tighter bounds are not computable with the current settings.";

	/**
	 * Returns the text that describes an invalidity. The configuration aspect
	 * should have tighter bounds.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @param descriptor
	 *            The configuration aspects descriptor.
	 * @return The text that describes an invalidity. The configuration aspect
	 *         should have tighter bounds.
	 */
	public static String SettingsUntightenableBounds_TighterBounds(String configAttribute, String descriptor) {
		if (configAttribute == null || descriptor == null) {
			throw new IllegalArgumentException();
		}
		return "Tighter bounds are computed for " + descriptor + " " + configAttribute
				+ " based on the other settings.";
	}

	/**
	 * Returns the text that describes an invalidity. The configuration aspect
	 * should be less than or equal a specific value.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @param value1
	 *            The value of the configuration aspect.
	 * @param value2
	 *            The value that the configuration aspect should be less than or
	 *            equal.
	 * @return The text that describes an invalidity. The configuration aspects
	 *         should be less than or equal a specific value.
	 */
	public static String AttributeValueShouldBeLessThanOrEqual(String configAttribute, String value1, String value2) {
		if (configAttribute == null || value1 == null || value2 == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute + " (" + value1 + ") should be less than or equal " + value2 + ".";
	}

	/**
	 * Returns the text that describes an invalidity. The configuration aspect
	 * should be less than or equal an other configuration aspect.
	 * 
	 * @param configAttribute1
	 *            The configuration aspect that should be less than or equal.
	 * @param configAttribute2
	 *            The other configuration aspect.
	 * @param value1
	 *            The value of the first configuration aspect.
	 * @param value2
	 *            The value of the second configuration aspect.
	 * @return The text that describes an invalidity. The configuration aspect
	 *         should be less than or equal an other configuration aspect.
	 */
	public static String AttributeValueShouldBeLessThanOrEqual(String configAttribute1, String configAttribute2,
			String value1, String value2) {
		if (configAttribute1 == null || configAttribute2 == null || value1 == null || value2 == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " (" + value1 + ") should be less than or equal " + configAttribute2 + " (" + value2
				+ ").";
	}

	/**
	 * Returns the text that describes an invalidity. The configuration aspect
	 * should be less than or equal an other configuration aspect when both are
	 * greater than a specific value.
	 * 
	 * @param configAttribute1
	 *            The configuration aspect that should be less than or equal.
	 * @param configAttribute2
	 *            The other configuration aspect.
	 * @param value1
	 *            The value of the first configuration aspect.
	 * @param value2
	 *            The value of the second configuration aspect.
	 * @param value3
	 *            The third value.
	 * @return The text that describes an invalidity. The configuration aspect
	 *         should be less than or equal an other configuration aspect when both
	 *         are greater than a specific value.
	 */
	public static String AttributeValueShouldBeLessThanOrEqualWhenBothGreaterThanOrEqual(String configAttribute1,
			String configAttribute2, String value1, String value2, String value3) {
		if (configAttribute1 == null || configAttribute2 == null || value1 == null || value2 == null
				|| value3 == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " (" + value1 + ") should be less than or equal " + configAttribute2 + " (" + value2
				+ ") when both are greater than or equal " + value3 + ".";
	}

	/**
	 * Returns the text that describes an invalidity. The configuration aspect
	 * should be a specific value or greater than or equal an other configuration
	 * aspect.
	 * 
	 * @param configAttribute1
	 *            The configuration aspect that should be greater than or equal.
	 * @param configAttribute2
	 *            The other configuration aspect.
	 * @param value1
	 *            The value of the first configuration aspect.
	 * @param value2
	 *            The value of the second configuration aspect.
	 * @param number
	 *            The third value.
	 * @return The text that describes an invalidity. The configuration aspect
	 *         should be a specific number or greater than or equal an other
	 *         configuration aspect.
	 */
	public static String AttributeValueShouldBeNumberOrGreaterThanOrEqual(String configAttribute1,
			String configAttribute2, String value1, String value2, String number) {
		if (configAttribute1 == null || configAttribute2 == null || value1 == null || value2 == null
				|| number == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " (" + value1 + ") should be " + number + " or greater than or equal "
				+ configAttribute2 + " (" + value2 + ").";
	}

	/**
	 * Returns the text that describes an invalidity. The configuration aspect
	 * should be greater than or equal a specific value.
	 * 
	 * @param configAttribute
	 *            The configuration aspect that should be greater than or equal.
	 * @param value1
	 *            The value of the configuration aspect.
	 * @param value2
	 *            The other value.
	 * @return The text that describes an invalidity. The configuration aspect
	 *         should be greater than or equal a specific value.
	 */
	public static String AttributeValueShouldBeGreaterThanOrEqual(String configAttribute, String value1,
			String value2) {
		if (configAttribute == null || value1 == null || value2 == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute + " (" + value1 + ") should be greater than or equal " + value2 + ".";
	}

	/**
	 * Returns the text that describes an invalidity. The configuration aspects
	 * should be greater than or equal a specific value.
	 * 
	 * @param configAttribute1
	 *            The first configuration aspect.
	 * @param configAttribute2
	 *            The second configuration aspect.
	 * @param value1
	 *            The value of the first configuration aspect.
	 * @param value2
	 *            The value of the second configuration aspect.
	 * @param value3
	 *            The value the configurations aspects should be greater than or
	 *            equal to.
	 * @return The text that describes an invalidity. The configuration aspects
	 *         should be greater than or equal a specific value.
	 */
	public static String AttributeValueShouldBeGreaterThanOrEqual(String configAttribute1, String configAttribute2,
			String value1, String value2, String value3) {
		if (configAttribute1 == null || configAttribute2 == null || value1 == null || value2 == null
				|| value3 == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " (" + value1 + ") and " + configAttribute2 + " (" + value2
				+ ") should be greater than or equal " + value3 + ".";
	}

	/**
	 * Returns the text that describes an invalidity. One configuration aspects
	 * should be less greater than or equal a specific value. Another configuration
	 * aspects should be greater greater than or equal a specific value. The
	 * configuration aspects are for an abstract class. The configuration aspects
	 * must have other values, because the bounds of the abstract class must be at
	 * least the sum of the bounds of the derived classes.
	 * 
	 * @param configAttribute1
	 *            The first configuration aspect.
	 * @param configAttribute2
	 *            The second configuration aspect.
	 * @param value1
	 *            The value of the first configuration aspect.
	 * @param value2
	 *            The value of the second configuration aspect.
	 * @param value3
	 *            The value the first configuration aspect should be less than or
	 *            equal to.
	 * @param value4
	 *            The value the second configuration aspect should be greater than
	 *            or equal to.
	 * @param derivedClassesNames
	 *            The derived classes.
	 * @return The text that describes the invalidity.
	 */
	public static String AbstractClassAttributeValuesShouldBeGreaterThanOrEqualSumOfDerivedClassAttributeValues(
			String configAttribute1, String configAttribute2, String value1, String value2, String value3,
			String value4, String[] derivedClassesNames) {
		if (configAttribute1 == null || configAttribute2 == null || value1 == null || value2 == null || value3 == null
				|| value4 == null || derivedClassesNames == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " (" + value1 + ") should be less than or equal " + value3 + " and "
				+ configAttribute2 + " (" + value2 + ") should be greater than or equal " + value4
				+ " because the lower bound of an abstract " + Class
				+ " needs to be less than or equal the sum of the lower bounds of derived Classes and the upper bound needs to be greater than or equal the sum of the upper bounds of the derived Classes ("
				+ String.join(",", derivedClassesNames) + ").";
	}

	/**
	 * Returns the text that describes an invalidity. One configuration aspect
	 * should be greater than or equal an other configuration aspect when the other
	 * configuration aspect is no -1.
	 * 
	 * @param minConfigAttribute
	 *            The configuration aspect that should be greater than or equal the
	 *            other.
	 * @param maxConfigAttribute
	 *            The other configuration aspect.
	 * @param minValue
	 *            The value of the first configuration aspect.
	 * @param maxValue
	 *            The value of the second configuration aspect.
	 * @return The text that describes an invalidity.
	 */
	public static String MinLessThanOrEqualMaxWhenMaxNotStar(String minConfigAttribute, String maxConfigAttribute,
			String minValue, String maxValue) {
		if (minConfigAttribute == null || maxConfigAttribute == null || minValue == null || maxValue == null) {
			throw new IllegalArgumentException();
		}
		return minConfigAttribute + " (" + minValue + ") should be greater than or equal " + maxConfigAttribute + " ("
				+ maxValue + ") when " + maxConfigAttribute + " is not -1.";
	}

	/**
	 * Returns the text that describes an invalidity. One configuration aspect
	 * should be greater than or equal than the cardinality of an list typed
	 * configuration aspect.
	 * 
	 * @param maxConfigAttribute
	 *            The configuration aspect that should be greater than or equal to
	 *            the others cardinality.
	 * @param preferredValuesConfigAttribute
	 *            The list typed configuration aspect.
	 * @param maxValue
	 *            The value of the first configuration aspect.
	 * @param preferredValuesCardinality
	 *            The cardinality of the second configuration aspect.
	 * @return The text that describes an invalidity. One configuration aspect
	 *         should be greater than or equal than the cardinality of an list typed
	 *         configuration aspect.
	 */
	public static String MaxShouldBeGreaterThanOrEqualPreferredValuesCardinality(String maxConfigAttribute,
			String preferredValuesConfigAttribute, String maxValue, String preferredValuesCardinality) {
		if (maxConfigAttribute == null || preferredValuesConfigAttribute == null || maxValue == null
				|| preferredValuesCardinality == null) {
			throw new IllegalArgumentException();
		}
		return maxConfigAttribute + " (" + maxValue + ") should be greater than or equal cardinality of "
				+ preferredValuesConfigAttribute + " (" + preferredValuesCardinality + ").";
	}

	/**
	 * Returns the text that describes a fix in form of removing the last values
	 * from a list typed configuration aspect.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @param number
	 *            The number of values to remove.
	 * @return The text that describes a fix in form of removing the last values
	 *         from a list typed configuration aspect.
	 */
	public static String RemoveLastValues(String configAttribute, int number) {
		if (configAttribute == null || number < 0) {
			throw new IllegalArgumentException();
		}
		return "Remove the last " + number + " " + (number > 1 ? "values" : "value") + " from " + configAttribute + ".";
	}

	/**
	 * Returns the text that describes a fix in form of removing all values from a
	 * list typed configuration aspect.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @return The text that describes a fix in form of removing all values from a
	 *         list typed configuration aspect.
	 */
	public static String RemoveAllValues(String configAttribute) {
		if (configAttribute == null) {
			throw new IllegalArgumentException();
		}
		return "Remove all values from " + configAttribute + ".";
	}

	/**
	 * Returns the text that describes a fix in form of removing all invalid values
	 * from a list typed configuration aspect.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @param invalidValues
	 *            The invalid values.
	 * @return The text that describes a fix in form of removing all invalid values
	 *         from a list typed configuration aspect.
	 */
	public static String RemoveAllInvalidValues(String configAttribute, Set<String> invalidValues) {
		if (configAttribute == null || invalidValues == null || invalidValues.contains(null)) {
			throw new IllegalArgumentException();
		}
		return "Remove from " + configAttribute + " all invalid values (" + String.join(",", invalidValues) + ").";
	}

	/**
	 * Returns the text that describes a fix in form of removing an invalid values
	 * from a list typed configuration aspect.
	 * 
	 * @param configAttribute
	 *            The configuration aspect.
	 * @param invalidValue
	 *            The invalid value.
	 * @return The text that describes a fix in form of removing an invalid values
	 *         from a list typed configuration aspect.
	 */
	public static String RemoveInvalidValue(String configAttribute, String invalidValue) {
		if (configAttribute == null || invalidValue == null) {
			throw new IllegalArgumentException();
		}
		return "Remove invalid value " + invalidValue + " from " + configAttribute + " .";
	}

	/**
	 * Returns the text that describes a fix in form of removing invalid values from
	 * a list typed configuration aspect. Values of a configuration aspects are
	 * invalid if they are less than the value of another configuration aspect.
	 * 
	 * @param configAttribute1
	 *            The first configuration aspect.
	 * @param invalidValues
	 *            The invalid values.
	 * @param configAttribute2
	 *            The second configuration aspect.
	 * @param value
	 *            The value of the second configuration aspect.
	 * @return The text that describes a fix in form of removing invalid values from
	 *         a list typed configuration aspect.
	 */
	public static String RemoveValuesThatAreLessThan(String configAttribute1, Set<String> invalidValues,
			String configAttribute2, String value) {
		if (configAttribute1 == null || invalidValues == null || invalidValues.contains(null)
				|| configAttribute2 == null || value == null) {
			throw new IllegalArgumentException();
		}
		return "Remove all values from " + configAttribute1 + " that are less than " + configAttribute2 + " (" + value
				+ "). Values {" + String.join(",", invalidValues) + "} are not valid.";
	}

	/**
	 * Returns the text that describes a fix in form of removing invalid values from
	 * a list typed configuration aspect. Values of a configuration aspects are
	 * invalid if they are greater than the value of another configuration aspect.
	 * 
	 * @param configAttribute1
	 *            The first configuration aspect.
	 * @param invalidValues
	 *            The invalid values.
	 * @param configAttribute2
	 *            The second configuration aspect.
	 * @param value
	 *            The value of the second configuration aspect.
	 * @return The text that describes a fix in form of removing invalid values from
	 *         a list typed configuration aspect.
	 */
	public static String RemoveValuesThatAreGreaterThan(String configAttribute1, Set<String> invalidValues,
			String configAttribute2, String value) {
		if (configAttribute1 == null || invalidValues == null || invalidValues.contains(null)
				|| configAttribute2 == null || value == null) {
			throw new IllegalArgumentException();
		}
		return "Remove all values from " + configAttribute1 + " that are greater than " + configAttribute2 + " ("
				+ value + "). Values {" + String.join(",", invalidValues) + "} are not valid.";
	}

	/**
	 * Returns the text that describes a fix in form of removing unreachable values
	 * from a list typed configuration aspect.
	 * 
	 * @param configAttribute1
	 *            The configuration aspect.
	 * @param invalidValues
	 *            The invalid values.
	 * @return The text that describes a fix in form of removing unreachable values
	 *         from a list typed configuration aspect.
	 */
	public static String RemoveValuesThatAreUnreachable(String configAttribute1, Set<String> invalidValues) {
		if (configAttribute1 == null || invalidValues == null || invalidValues.contains(null)) {
			throw new IllegalArgumentException();
		}
		return "Remove all values from " + configAttribute1 + " that are unreachable. Values {"
				+ String.join(",", invalidValues) + "} are not valid.";
	}

	/**
	 * Returns the text that describes an invalidity. One list typed configuration
	 * aspects values are not valid regarding to two other configuration aspects.
	 * 
	 * @param configAttribute
	 *            The list typed configuration aspect.
	 * @param configAttribute1
	 *            The first configuration aspect.
	 * @param configAttribute2
	 *            The second configuration aspect.
	 * @param value1
	 *            The value of the first configuration aspect.
	 * @param value2
	 *            The value of the second configuration aspect.
	 * @param invalidValues
	 *            The invalid values.
	 * @return The text that describes an invalidity. One list typed configuration
	 *         aspects values are not valid regarding to two other configuration
	 *         aspects.
	 */
	public static String AttributeValuesNotValid(String configAttribute, String configAttribute1,
			String configAttribute2, String value1, String value2, Set<String> invalidValues) {
		if (configAttribute == null || configAttribute1 == null || configAttribute2 == null || value1 == null
				|| value2 == null || invalidValues == null || invalidValues.contains(null)) {
			throw new IllegalArgumentException();
		}
		return "Values of " + configAttribute + " are not valid regarding " + configAttribute1 + " (" + value1
				+ ") and " + configAttribute2 + " (" + value2 + "). Values {" + String.join(",", invalidValues)
				+ "} are not valid.";
	}

	/**
	 * Returns the text that describes an invalidity. An invariant should be negated
	 * when it is not activated.
	 * 
	 * @param invariant
	 *            The invariants name.
	 * @return The text that describes an invalidity. An invariant should be negated
	 *         when it is not activated.
	 */
	public static String InvariantShouldBeNegatedIfNotActivated(String invariant) {
		if (invariant == null) {
			throw new IllegalArgumentException();
		}
		return "Invariant " + invariant + " should not be negated when it is not activated.";
	}

	/**
	 * Returns the text that describes a fix in form of enabling an invariant.
	 * 
	 * @param invariant
	 *            The invariants name.
	 * @return The text that describes a fix in form of enabling an invariant.
	 */
	public static String EnableInvariant(String invariant) {
		if (invariant == null) {
			throw new IllegalArgumentException();
		}
		return "Activate " + invariant + ".";
	}

	/**
	 * Returns the text that describes a fix in form of unnegate an invariant.
	 * 
	 * @param invariant
	 *            The invariants name.
	 * @return The text that describes a fix in form of unnegate an invariant.
	 */
	public static String UnnegateInvariant(String invariant) {
		if (invariant == null) {
			throw new IllegalArgumentException();
		}
		return "Unnegate " + invariant + ".";
	}

	/**
	 * Returns the text that describes a fix in form of removing all invalid real
	 * values.
	 * 
	 * @param invalidValues
	 *            The invalid values.
	 * @return The text that describes a fix in form of removing all invalid real
	 *         values.
	 */
	public static String RealSettingsPreferredValuesRemoveAllInvalidValues(Set<String> invalidValues) {
		if (invalidValues == null || invalidValues.contains(null)) {
			throw new IllegalArgumentException();
		}
		return "Remove all values that are less than " + TypeConstants.REAL + PropertyEntry.realValuesMin
				+ " or that are greater than " + TypeConstants.REAL + PropertyEntry.realValuesMax
				+ " or that are not reachable. Values {" + String.join(",", invalidValues) + "} are not valid.";
	}

	/**
	 * Returns the text that describes an invalidity. Real values must no be
	 * invalid.
	 * 
	 * @param realMin
	 *            The minimum real value.
	 * @param realMax
	 *            The maximum real value.
	 * @param realStep
	 *            The real step value.
	 * @return The text that describes an invalidity. Real values must no be
	 *         invalid.
	 */
	public static String RealSettingsPreferredValuesInMinMaxStep(String realMin, String realMax, String realStep) {
		if (realMin == null || realMax == null || realStep == null) {
			throw new IllegalArgumentException();
		}
		return TypeConstants.REAL + " should not contain values that are less than " + TypeConstants.REAL
				+ PropertyEntry.realValuesMin + " (" + realMin + ") or that are greater than " + TypeConstants.REAL
				+ PropertyEntry.realValuesMax + " (" + realMax + ") or that are not reachable from "
				+ TypeConstants.REAL + PropertyEntry.realValuesMin + " in steps of " + TypeConstants.REAL
				+ PropertyEntry.realStep + " (" + realStep + ").";
	}

	/**
	 * Returns the text that describes an invalidity. Real step value must be
	 * greater than 0 but less than or equal the difference between maximum and
	 * minimum real value.
	 * 
	 * @param realMin
	 *            The minimum real value.
	 * @param realMax
	 *            The maximum real value.
	 * @param realStep
	 *            The real step value.
	 * @param maximumStep
	 *            The difference between maximum and minimum real value.
	 * @return The text that describes an invalidity. Real step value must be
	 *         greater than 0 but less than or equal the difference between maximum
	 *         and minimum real value.
	 */
	public static String RealSettingsStepGreaterThanZeroAndLessThanOrEqualMaxMinusMin(String realMin, String realMax,
			String realStep, String maximumStep) {
		if (realMin == null || realMax == null || realStep == null || maximumStep == null) {
			throw new IllegalArgumentException();
		}
		return TypeConstants.REAL + PropertyEntry.realStep + " (" + realStep
				+ ") should be greater than 0 and less than or equal " + TypeConstants.REAL
				+ PropertyEntry.realValuesMax + " (" + realMax + ") minus " + TypeConstants.REAL
				+ PropertyEntry.realValuesMin + " (" + realMin + ") (" + maximumStep + ").";
	}

	/**
	 * Returns the text that describes an invalidity. Maximum real value must be
	 * must be reachable from minimum real value in steps of real step value.
	 * 
	 * @param realMin
	 *            The minimum real value.
	 * @param realMax
	 *            The maximum real value.
	 * @param realStep
	 *            The real step value.
	 * @return The text that describes an invalidity. Maximum real value must be
	 *         must be reachable from minimum real value in steps of real step
	 *         value.
	 */
	public static String RealSettingsMaxReachableInStepFromMin(String realMin, String realMax, String realStep) {
		if (realMin == null || realMax == null || realStep == null) {
			throw new IllegalArgumentException();
		}
		return TypeConstants.REAL + PropertyEntry.realValuesMax + " (" + realMax + ") should be reachable from "
				+ TypeConstants.REAL + PropertyEntry.realValuesMin + " (" + realMin + ") in steps of "
				+ TypeConstants.REAL + PropertyEntry.realStep + " (" + realStep + ").";
	}

	/**
	 * Constructs no object.
	 */
	private UIElements() {
		throw new UnsupportedOperationException("No instance allowed");
	}
}
