package org.tzi.kodkod.comparison.ui;

import org.tzi.kodkod.comparison.ComparisonResults;

/**
 * Provider of constant values used in the UIs for the functionality for
 * comparing instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
public final class UIElements {

	/**
	 * The GUI comparison window title.
	 */
	public static final String MainWindow_Title = "Configuration comparison";

	/**
	 * The GUI error dialog title.
	 */
	public static final String ErrorDialog_Title = "Error";

	/**
	 * The GUI error dialog general message.
	 */
	public static final String ErrorDialog_Message = "An internal error occured.";

	/**
	 * The GUI comparison details window title.
	 */
	public static final String ComparisonDetailsWindow_Title = "Comparison details";

	/**
	 * The GUI value of the button to apply a selection.
	 */
	public static final String ApplySelectionButton_Title = "Apply selection";

	/**
	 * The GUI tooltip of the button to apply a selection.
	 */
	public static final String ApplySelectionButton_Tooltip = "Applies selection";

	/**
	 * The GUI value of the button to expand the selection options.
	 */
	public static final String SelectionOptionsButton_Title = "Selection options";

	/**
	 * The GUI tooltip of the button to expand the selection options.
	 */
	public static final String SelectionOptionsButton_Tooltip = "Shows selection options.";

	/**
	 * The GUI value of the option select all elements.
	 */
	public static final String SelectAllOption_Title = "Select all";
	/**
	 * The GUI value of the option select no elements.
	 */
	public static final String SelectNoneOption_Title = "Select none";

	/**
	 * The GUI base value of the option select all elements from a family.
	 */
	public static final String SelectFamilyButton_BaseTitle = "Select family:";

	/**
	 * The GUI tooltip of the option select all elements from a family.
	 */
	public static final String SelectFamilyButton_Tooltip = "Sets selection to the family members.";

	/**
	 * The CLI information that no model is loaded.
	 */
	public static final String CLI_NoModelLoaded = "No model loaded.";

	/**
	 * The CLI information that no configuration file is specified.
	 */
	public static final String CLI_NoConfigFileArgument = "No configuration file is specified.";

	/**
	 * The CLI information that no valid number of configuration names is specified.
	 */

	public static final String CLI_OnlyOneConfigNameArgument = "Only one configuration name is specified. None or at least two must be specified.";

	/**
	 * The CLI information that comparison failed.
	 */
	public static final String CLI_ComparisonFailed = "Configurations could not be compared.";

	/**
	 * Returns the CLI information that names are specified more than once.
	 * 
	 * @param details
	 *            The informations about which names are specified more than once.
	 * @return The CLI information that names are specified more than once.
	 */
	public static final String CLI_DuplicatedNames(String details) {
		if (details == null) {
			throw new IllegalArgumentException();
		}
		return "The following names, which will be processed only once, are specified more than once: " + details;
	}

	/**
	 * Returns the CLI information that names invalid and ignored.
	 * 
	 * @param details
	 *            The informations about which names are invalid.
	 * @return The CLI information that names invalid and ignored.
	 */
	public static final String CLI_InvalidNames(String details) {
		if (details == null) {
			throw new IllegalArgumentException();
		}
		return "The following names, which will be ignored further on, are not contained in the file: " + details;
	}

	/**
	 * The CLI information that not at least two valid names are specified.
	 */
	public static final String CLI_InvalidNames = "Multiple names are specified but not at least two valid names are contained.";

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#NOT_COMPARABLE}.
	 */
	public static final String NOT_COMPARABLE_Descriptor = "!?";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#NOT_COMPARABLE}.
	 */
	public static final String NOT_COMPARABLE_DescriptorText = "Comparisons that classify as not comparable";

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#INVALID_COMPARISON}.
	 */
	public static final String INVALID_COMPARISON_Descriptor = "!!";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#INVALID_COMPARISON}.
	 */
	public static final String INVALID_COMPARISON_DescriptorText = "Comparisons that are not done because of invalidity";

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#IGNORED}.
	 */
	public static final String IGNORED_Descriptor = "..";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#IGNORED}.
	 */
	public static final String IGNORED_DescriptorText = "Comparisons that are ignored";

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#UNEQUAL}.
	 */
	public static final String UNEQUAL_Descriptor = "!=";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#UNEQUAL}.
	 */
	public static final String UNEQUAL_DescriptorText = "Comparisons that classify as unequal";

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#UNEQUAL}.
	 * 
	 * @param configAttribute
	 *            The textual representation of the configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#UNEQUAL}.
	 */
	public static final String UNEQUAL_Details(String configAttribute, String details) {
		if (configAttribute == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute + " classifies unequality " + details + ".";
	}

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#UNEQUAL}.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the lefts configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the rights configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#UNEQUAL}.
	 */
	public static final String UNEQUAL_Details(String configAttribute1, String configAttribute2, String details) {
		if (configAttribute1 == null || configAttribute2 == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " and " + configAttribute2 + " classify unequality " + details + ".";
	}

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#EQUAL}.
	 */
	public static final String EQUAL_Descriptor = "==";

	/**
	 * The base text for the comparison result type {@link ComparisonResults#EQUAL}.
	 */
	public static final String EQUAL_DescriptorText = "Comparisons that classify as equal";

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#EQUAL}.
	 * 
	 * @param configAttribute
	 *            The textual representation of the configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#EQUAL}.
	 */
	public static final String EQUAL_Details(String configAttribute, String details) {
		if (configAttribute == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute + " classifies equality " + details + ".";
	}

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#EQUAL}.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the lefts configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the rights configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#EQUAL}.
	 */
	public static final String EQUAL_Details(String configAttribute1, String configAttribute2, String details) {
		if (configAttribute1 == null || configAttribute2 == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " and " + configAttribute2 + " classify equality " + details + ".";
	}

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW}.
	 */
	public static final String LEFT_BROAD_AND_RIGHT_NARROW_Descriptor = ">=";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW}.
	 */
	public static final String LEFT_BROAD_AND_RIGHT_NARROW_DescriptorText = "Comparisons that classify as left is broad";

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW}.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the lefts configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the rights configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW}.
	 */
	public static final String LEFT_BROAD_AND_RIGHT_NARROW_Details(String configAttribute1, String configAttribute2,
			String details) {
		if (configAttribute1 == null || configAttribute2 == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " and " + configAttribute2 + " classify as left is broad " + details + ".";
	}

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD}.
	 */
	public static final String LEFT_NARROW_AND_RIGHT_BROAD_Descriptor = "<=";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD}.
	 */
	public static final String LEFT_NARROW_AND_RIGHT_BROAD_DescriptorText = "Comparisons that classify as right is broad";

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD_DescriptorText}.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the lefts configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the rights configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING}.
	 */
	public static final String LEFT_NARROW_AND_RIGHT_BROAD_Details(String configAttribute1, String configAttribute2,
			String details) {
		if (configAttribute1 == null || configAttribute2 == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " and " + configAttribute2 + " classify as right is broad " + details + ".";
	}

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING}.
	 */
	public static final String LEFT_IS_OVERLAPPING_Descriptor = "lO";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING}.
	 */
	public static final String LEFT_IS_OVERLAPPING_DescriptorText = "Comparisons that classify as left prelimary overlapping";

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING}.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the lefts configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the rights configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING}.
	 */
	public static final String LEFT_IS_OVERLAPPING_Details(String configAttribute1, String configAttribute2,
			String details) {
		if (configAttribute1 == null || configAttribute2 == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " and " + configAttribute2 + " classify as left is prelimary overlapping " + details
				+ ".";
	}

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING}.
	 */
	public static final String RIGHT_IS_OVERLAPPING_Descriptor = "rO";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING}.
	 */
	public static final String RIGHT_IS_OVERLAPPING_DescriptorText = "Comparisons that classify as right prelimary overlapping";

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING}.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the lefts configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the rights configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING}.
	 */
	public static final String RIGHT_IS_OVERLAPPING_Details(String configAttribute1, String configAttribute2,
			String details) {
		if (configAttribute1 == null || configAttribute2 == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " and " + configAttribute2 + " classify as right is prelimary overlapping " + details
				+ ".";
	}

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#LEFT_IS_LESS_DISJOINT}.
	 */
	public static final String LEFT_IS_LESS_DISJOINT_Descriptor = "lD";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#LEFT_IS_LESS_DISJOINT}.
	 */
	public static final String LEFT_IS_LESS_DISJOINT_DescriptorText = "Comparisons that classify as left disjoint";

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#LEFT_IS_LESS_DISJOINT}.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the lefts configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the rights configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#LEFT_IS_LESS_DISJOINT}.
	 */
	public static final String LEFT_IS_LESS_DISJOINT_Details(String configAttribute1, String configAttribute2,
			String details) {
		if (configAttribute1 == null || configAttribute2 == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " and " + configAttribute2 + " classify as left disjoint " + details + ".";
	}

	/**
	 * The representation for the comparison result type
	 * {@link ComparisonResults#RIGHT_IS_LESS_DISJOINT}.
	 */
	public static final String RIGHT_IS_LESS_DISJOINT_Descriptor = "rD";

	/**
	 * The base text for the comparison result type
	 * {@link ComparisonResults#RIGHT_IS_LESS_DISJOINT}.
	 */
	public static final String RIGHT_IS_LESS_DISJOINT_DescriptorText = "Comparisons that classify as right disjoint";

	/**
	 * Returns details for the comparison result type
	 * {@link ComparisonResults#RIGHT_IS_LESS_DISJOINT}.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the lefts configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the rights configuration aspect.
	 * @param details
	 *            The details of the configuration aspects.
	 * @return The details for the comparison result type
	 *         {@link ComparisonResults#RIGHT_IS_LESS_DISJOINT}.
	 */
	public static final String RIGHT_IS_LESS_DISJOINT_Details(String configAttribute1, String configAttribute2,
			String details) {
		if (configAttribute1 == null || configAttribute2 == null || details == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute1 + " and " + configAttribute2 + " classify as right disjoint " + details + ".";
	}

	/**
	 * The label for invariant configuration aspects.
	 */
	public static final String InvariantSettingsDescriptor = "Invariant settings";

	/**
	 * The label for class configuration aspects.
	 */
	public static final String ClassSettingsDescriptor = "Class settings";

	/**
	 * The label for association configuration aspects.
	 */
	public static final String AssociationSettingsDescriptor = "Association settings";

	/**
	 * 
	 * Returns the label for attribute configuration aspects.
	 *
	 * @param className
	 *            The attributes owning class.
	 * @return The label for attribute configuration aspects.
	 */
	public static String AttributeSettingsDescriptor(String className) {
		if (className == null) {
			throw new IllegalArgumentException();
		}
		return "Attribute settings for class " + className;
	}

	/**
	 * Returns the information that the lists of configuration aspects have
	 * different sizes.
	 * 
	 * @param settingsDescriptor
	 *            The textual representation of the configuration aspect.
	 * @param leftSize
	 *            The size of the list of the lefts configuration aspect.
	 * @param rightSize
	 *            The size of the list of the rights configuration aspect.
	 * @return The information that the lists of configuration aspects have
	 *         different sizes.
	 */
	public static String ListOfSettingsHasDifferentSizes(String settingsDescriptor, int leftSize, int rightSize) {
		if (settingsDescriptor == null) {
			throw new IllegalArgumentException();
		}
		return settingsDescriptor + " have different sizes. Left list has size " + leftSize + ". Right list has size "
				+ rightSize + ".";
	}

	/**
	 * Returns the information that the configuration aspects from a list of
	 * configuration aspects would be used twice for comparison.
	 * 
	 * @param settingsDescriptor
	 *            The textual representation of the configuration aspect.
	 * @param settingsSubjectName
	 *            An additional information about the configuration aspect.
	 * @return The information that the configuration aspects from a list of
	 *         configuration aspects would be used twice for comparison.
	 */
	public static String SettingsFromRightListWouldBeUsedTwice(String settingsDescriptor, String settingsSubjectName) {
		if (settingsDescriptor == null || settingsSubjectName == null) {
			throw new IllegalArgumentException();
		}
		return settingsDescriptor + " for " + settingsSubjectName
				+ " in right list of settings would be used twice for comparison. That means that left list contains at least two settings for this.";
	}

	/**
	 * Returns the information that the configuration aspects from a list of
	 * configuration aspects would be used twice for comparison.
	 * 
	 * @param settingsDescriptor
	 *            The textual representation of the configuration aspect.
	 * @param settingsSubjectName
	 *            An additional information about the configuration aspect.
	 * @return The information that the configuration aspects from a list of
	 *         configuration aspects would be used twice for comparison.
	 */
	public static String SettingsFromLeftListHasNoCoresnpondInRightList(String settingsDescriptor,
			String settingsSubjectName) {
		if (settingsDescriptor == null || settingsSubjectName == null) {
			throw new IllegalArgumentException();
		}
		return settingsDescriptor + " for " + settingsSubjectName
				+ " in left list of settings has no correspond in right list.";
	}

	/**
	 * Returns the information that the configuration aspects from a list of
	 * configuration aspects is not used for comparison.
	 * 
	 * @param settingsDescriptor
	 *            The textual representation of the configuration aspect.
	 * @return The information that the configuration aspects from a list of
	 *         configuration aspects is not used for comparison.
	 */
	public static String SettingsRightListNotFullyUsed(String settingsDescriptor) {
		if (settingsDescriptor == null) {
			throw new IllegalArgumentException();
		}
		return settingsDescriptor + " in right list of invariant settings were not fully used for comparison.";
	}

	/**
	 * Returns the information that the configuration aspects is ignored.
	 * 
	 * @param configAttribute
	 *            The textual representation of the configuration aspect.
	 * @return The information that the configuration aspects is ignored.
	 */
	public static String ConfigAttributeIgnored(String configAttribute) {
		if (configAttribute == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute + " is ignored.";
	}

	/**
	 * Returns the information that the configuration aspects compared are not
	 * equally enabled or disabled.
	 * 
	 * @param configAttribute
	 *            The textual representation of the configuration aspect.
	 * @param leftEnabled
	 *            Whether the left configuration aspect is enabled.
	 * @param rightEnabled
	 *            Whether the right configuration aspect is enabled.
	 * @return The information that the configuration aspects compared are not
	 *         equally enabled or disabled.
	 */
	public static String LeftEnabledUnequalsRightEnabled(String configAttribute, boolean leftEnabled,
			boolean rightEnabled) {
		if (configAttribute == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute + " enabled value on left is " + leftEnabled + " while right is " + rightEnabled + ".";
	}

	/**
	 * Returns the information that the configuration aspects compared are bit
	 * disabled.
	 * 
	 * @param configAttribute
	 *            The textual representation of the configuration aspect.
	 * @return The information that the configuration aspects compared are bit
	 *         disabled.
	 */
	public static String EnabledBothFalse(String configAttribute) {
		if (configAttribute == null) {
			throw new IllegalArgumentException();
		}
		return configAttribute + " enabled value is both false.";
	}

	/**
	 * Returns the information that the configuration aspects comparison details.
	 * 
	 * @param configAttribute
	 *            The textual representation of the configuration aspect.
	 * @param leftValue
	 *            The value of the left configuration aspect.
	 * @param rightValue
	 *            The value of the right configuration aspect.
	 * @return The information that the configuration aspects comparison details.
	 */
	public static String ComparisonDetails(String configAttribute, String leftValue, String rightValue) {
		if (configAttribute == null || leftValue == null || rightValue == null) {
			throw new IllegalArgumentException();
		}
		return "( left: " + configAttribute + "=" + leftValue + " ; right: " + configAttribute + "=" + rightValue + ")";
	}

	/**
	 * Returns the information that the configuration aspects comparison details.
	 * 
	 * @param configAttribute1
	 *            The textual representation of the first configuration aspect.
	 * @param leftValue1
	 *            The value of the left first configuration aspect.
	 * @param rightValue1
	 *            The value of the right first configuration aspect.
	 * @param configAttribute2
	 *            The textual representation of the second configuration aspect.
	 * @param leftValue2
	 *            The value of the left second configuration aspect.
	 * @param rightValue2
	 *            The value of the right second configuration aspect.
	 * @return TThe value of the left first configuration aspect. comparison
	 *         details.
	 */
	public static String ComparisonDetails(String configAttribute1, String leftValue1, String rightValue1,
			String configAttribute2, String leftValue2, String rightValue2) {
		if (configAttribute1 == null || leftValue1 == null || rightValue1 == null || configAttribute2 == null
				|| leftValue2 == null || rightValue2 == null) {
			throw new IllegalArgumentException();
		}
		return "( left: [ " + configAttribute1 + "=" + leftValue1 + " ; " + configAttribute2 + "=" + leftValue2
				+ " ] ; right: [ " + configAttribute1 + "=" + rightValue1 + " ; " + configAttribute2 + "=" + rightValue2
				+ " ] )";
	}

	/**
	 * Constructs no object.
	 */
	private UIElements() {
		throw new UnsupportedOperationException("No instance allowed");
	}

	/**
	 * Returns the information that a list of configuration aspects is empty.
	 * 
	 * @param settingsDescriptor
	 *            The textual representation of the configuration aspect.
	 * @return The information that a list of configuration aspects is empty.
	 */
	public static String ListOfSettingsIsEmpty(String settingsDescriptor) {
		if (settingsDescriptor == null) {
			throw new IllegalArgumentException();
		}
		return settingsDescriptor + " are empty.";
	}

}
