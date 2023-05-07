package org.tzi.kodkod.clever.ui;

import java.util.Arrays;

/**
 * Provider of constant values used in the UIs for the functionality for clever
 * generation of instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
public final class UIElements {

	/**
	 * The GUI clever generation window title.
	 */
	public static final String MainWindow_Title = "Clever configuration generation";

	/**
	 * The GUI error dialog title.
	 */
	public static final String ErrorDialog_Title = "Error";

	/**
	 * The GUI error dialog general message.
	 */
	public static final String ErrorDialog_Message = "An internal error occured.";

	/**
	 * The message for the case that the generation failed.
	 */
	private static final String GenerationFailedBaseMessage = "Could not generate clever configuration.";

	/**
	 * The GUI label for the checkbox to respect ocl constraints.
	 */
	public static final String MainWindow_RepsectOclConstraintsCheckBox_Title = "Include OCL constraints in generation.";

	/**
	 * The GUI label for the checkbox to mandatorize attributes.
	 */
	public static final String MainWindow_MandatorizeAttibutesCheckBox_Title = "Set all attribute lower bounds to -1.";

	/**
	 * The GUI label for the checkbox to set attribute upper bounds unlimited.
	 */
	public static final String MainWindow_GeneralizeAttributeUpperBoundsCheckBox_Title = "Set all attribute upper bounds to -1 if they are larger than or equal the upper bound of their owning class.";

	/**
	 * The GUI value for the generate button.
	 */
	public static final String MainWindow_GenerationButton_Title = "Generate";

	/**
	 * The GUI label of the general initial bounds.
	 */
	public static final String DefalutBoundSpecification_Title = "Default";

	/**
	 * The GUI tooltip of the general initial bounds.
	 */
	public static final String DefalutBoundSpecification_Tooltip = "Default initial bounds";

	/**
	 * The CLI info for the case that no model is loaded.
	 */
	public static final String CLI_NoModelLoaded = "No model loaded.";

	/**
	 * The CLI info for the case that no outpur file is specified.
	 */
	public static final String CLI_NoOutputFileArgument = "No output file is specified.";

	/**
	 * The prefix of short CLI options.
	 */
	private static final String CLI_ShortOptionPrefix = "-";

	/**
	 * The prefix of long CLI options.
	 */
	private static final String CLI_LongOptionPrefix = "--";

	/**
	 * The short CLI option for respecting OCL constraints.
	 */
	public static final String CLI_respectOCLContraintsOption_Short = CLI_ShortOptionPrefix + "roc";

	/**
	 * The long CLI option for respecting OCL constraints.
	 */
	public static final String CLI_respectOCLContraintsOption_Long = CLI_LongOptionPrefix + "respectOclConstraints";

	/**
	 * The short CLI option for mandatorizing attributes.
	 */
	public static final String CLI_mandatorizeAttributesOption_Short = CLI_ShortOptionPrefix + "ma";

	/**
	 * The long CLI option for mandatorizing attributes.
	 */
	public static final String CLI_mandatorizeAttributesOption_Long = CLI_LongOptionPrefix + "mandatorizeAttributes";

	/**
	 * The short CLI option for setting attribute upper bounds unlimited.
	 */
	public static final String CLI_generalizeAttributeUpperBoundsOption_Short = CLI_ShortOptionPrefix + "gab";

	/**
	 * The long CLI option for setting attribute upper bounds unlimited.
	 */
	public static final String CLI_generalizeAttributeUpperBoundsOption_Long = CLI_LongOptionPrefix
			+ "generalizeAttributeUpperBounds";

	/**
	 * The CLI info that the textual initial bounds specification is not valid.
	 */
	public static final String CLI_InitialBoundsSpecificationNotProcessable = "Could not process specified initial bounds specifications.";

	/**
	 * The CLI info that no configuration could be generated.
	 */
	public static final String CLI_GenerationFailed = "Could not generate clever configuration.";

	/**
	 * The CLI info that the no general initial lower bound is specified.
	 */
	public static final String CLI_NoDefaultLowerBoundArgument = "No default lower bound is specified.";

	/**
	 * The CLI info that the no valid general initial lower bound is specified.
	 */
	public static final String CLI_InvalidDefaultLowerBoundArgument = "No valid default lower bound is specified.";

	/**
	 * The CLI info that the general initial lower bound is not greater than or
	 * equal 0.
	 */
	public static final String CLI_ToTinyDefaultLowerBoundArgument = "Default lower bound must be greater than or equal 0.";

	/**
	 * The CLI info that the no general initial upper bound is specified.
	 */
	public static final String CLI_NoDefaultUpperBoundArgument = "No default upper bound is specified.";

	/**
	 * The CLI info that the no valid general initial upper bound is specified.
	 */
	public static final String CLI_InvalidDefaultUpperBoundArgument = "No valid default upper bound is specified.";

	/**
	 * The CLI info that the general initial lower bound is not less than or equal
	 * the general initial upper bound.
	 */
	public static final String CLI_ToTinyDefaultUpperBoundArgument = "Default upper bound must be greater than or equal default lower bound.";

	/**
	 * Returns a text that describes that one argument does not match a specific
	 * form.
	 * 
	 * @param argument
	 *            The argument.
	 * @param validArgumentsPattern
	 *            The expected form.
	 * @return A text that describes that one argument does not match a specific
	 *         form.
	 */
	public static final String CLI_InvalidArgument(String argument, String[] validArgumentsPattern) {
		if (argument == null || validArgumentsPattern == null) {
			throw new IllegalArgumentException();
		}
		return "Argument \"" + argument + "\" does not match " + Arrays.toString(validArgumentsPattern);
	}

	/**
	 * Returns a text that describes that a file could not been saved a specific
	 * location.
	 * 
	 * @param fileDescriptor
	 *            The location.
	 * @return A text that describes that a file could not been saved a specific
	 *         location.
	 */
	public static final String CLI_FileSavingFailed(String fileDescriptor) {
		return "Could not safe clever configuration in file \"" + fileDescriptor + "\".";
	}

	/**
	 * Returns a text that describes that a file was saved a specific location.
	 * 
	 * @param fileDescriptor
	 *            The location.
	 * @return A text that describes that a file was saved a specific location.
	 */
	public static final String CLI_FileSaved(String fileDescriptor) {
		return "Safed clever configuration in file \"" + fileDescriptor + "\".";
	}

	/**
	 * Objects for UI elements regarding model element specific initial bounds
	 * specifications.
	 * 
	 * @author Jan Prien
	 *
	 */
	public enum ModelElements {

		/**
		 * Associations.
		 */
		Association("Association", "Associations"),

		/**
		 * Classes
		 */
		Class("Class", "Classes"),

		/**
		 * Attributes.
		 */
		Attribute("Attribute", "Attributes");

		/**
		 * The label of a model elements type.
		 */
		public final String Title;

		/**
		 * The label of a type of model elements.
		 */
		public final String Title_Plural;

		/**
		 * The separator for labels that allow model element specific initial bounds
		 * specification.
		 */
		public static final String Seperator = ".";

		/**
		 * The base tooltip for initial bounds GUI elements.
		 */
		private static final String BoundSpecification_Tooltip_Base = "Initial bounds for";

		/**
		 * Constructs an UI Element type for a model element type.
		 * 
		 * @param title
		 *            The label of the model elements type.
		 * @param pluralTitle
		 *            The label of the type of model elements.
		 */
		private ModelElements(String title, String pluralTitle) {
			this.Title = title;
			this.Title_Plural = pluralTitle;
		}

		/**
		 * Returns a full label for a model elements bounds specification.
		 * 
		 * @param name
		 *            The actual name of the model element.
		 * @return A full label for a model elements bounds specification.
		 */
		public String Title(String name) {
			if (name == null) {
				throw new IllegalArgumentException();
			}
			return Title + Seperator + name;
		}

		/**
		 * Returns the tooltip text for a model elements bounds specification.
		 * 
		 * @param name
		 *            The actual name of the model element.
		 * @return The tooltip text for a model elements bounds specification.
		 */
		public String BoundSpecification_Tooltip(String name) {
			if (name == null) {
				throw new IllegalArgumentException();
			}
			return BoundSpecification_Tooltip_Base + " " + Title.toLowerCase() + " " + name + ".";
		}

		/**
		 * Returns the tooltip text for a model elements bounds specification.
		 * 
		 * @param name
		 *            The actual name of the model element.
		 * @param comment
		 *            An additional comment.
		 * @return The tooltip text for a model elements bounds specification.
		 */
		public String BoundSpecification_Tooltip(String name, String comment) {
			if (name == null || comment == null) {
				throw new IllegalArgumentException();
			}
			return BoundSpecification_Tooltip_Base + " " + Title.toLowerCase() + " " + name + " (" + comment + ").";
		}

		/**
		 * Returns the comment for a tooltip for a model elements bounds specification.
		 * 
		 * @param name
		 *            The actual name of the model element.
		 * @return The comment for a tooltip for a model elements bounds specification.
		 */
		public String BoundSpecification_TooltipComment(String name) {
			if (name == null) {
				throw new IllegalArgumentException();
			}
			return "of " + Title.toLowerCase() + " " + name;
		}

		/**
		 * 
		 * @return The tooltip text for a model element type bounds specification.
		 */
		public String DefaultBoundSpecification_Tooltip() {
			return BoundSpecification_Tooltip_Base + " " + Title_Plural.toLowerCase() + ".";
		}

		/**
		 * Returns a text describing that a lower bound for a model element type bounds
		 * specification is invalid.
		 * 
		 * @param argument
		 *            The specification of the lower bound.
		 * @return A text describing that a lower bound for a model element type bounds
		 *         specification is invalid.
		 */
		public String ArgumentNotContainsValidDefaultLowerBound(String argument) {
			if (argument == null) {
				throw new IllegalArgumentException();
			}
			return "No valid " + Title_Plural.toLowerCase() + " default lower bound is specified in argument \""
					+ argument + "\".";
		}

		/**
		 * Returns a text describing that a lower bound for a model elements bounds
		 * specification is invalid.
		 * 
		 * @param argument
		 *            The specification of the lower bound.
		 * @return A text describing that a lower bound for a model elements bounds
		 *         specification is invalid.
		 */
		public String ArgumentNotContainsValidLowerBound(String argument) {
			if (argument == null) {
				throw new IllegalArgumentException();
			}
			return "No valid " + Title.toLowerCase() + " default lower bound is specified in argument \"" + argument
					+ "\".";
		}

		/**
		 * Returns a text describing that a upper bound for a model element type bounds
		 * specification is invalid.
		 * 
		 * @param argument
		 *            The specification of the upper bound.
		 * @return A text describing that a upper bound for a model element type bounds
		 *         specification is invalid.
		 */
		public String ArgumentNotContainsValidDefaultUpperBound(String argument) {
			if (argument == null) {
				throw new IllegalArgumentException();
			}
			return "No valid " + Title_Plural.toLowerCase() + " default upper bound is specified in argument \""
					+ argument + "\".";
		}

		/**
		 * Returns a text describing that a upper bound for a model elements bounds
		 * specification is invalid.
		 * 
		 * @param argument
		 *            The specification of the upper bound.
		 * @return A text describing that a upper bound for a model elements bounds
		 *         specification is invalid.
		 */
		public String ArgumentNotContainsValidUpperBound(String argument) {
			if (argument == null) {
				throw new IllegalArgumentException();
			}
			return "No valid " + Title.toLowerCase() + " default upper bound is specified in argument \"" + argument
					+ "\".";
		}

		/**
		 * Returns a text describing that no valid default bounds are specified.
		 * 
		 * @param argument
		 *            The specification of the default bounds.
		 * @param explenation
		 *            The reason for invalidity.
		 * @return A text describing that no valid default bounds are specified.
		 */
		public String ArgumentNotContainsValidDefaultBounds(String argument, String explenation) {
			if (argument == null || explenation == null) {
				throw new IllegalArgumentException();
			}
			return "No valid " + Title_Plural.toLowerCase() + " default bounds are specified in argument \"" + argument
					+ "\". " + explenation;
		}

		/**
		 * Returns a text describing that the name in a bound specification for a model
		 * element is not valid.
		 * 
		 * @param argument
		 *            The specification of the bounds.
		 * @return A text describing that the name in a bound specification for a model
		 *         element is not valid.
		 */
		public String ArgumentNotContainsValidName(String argument) {
			if (argument == null) {
				throw new IllegalArgumentException();
			}
			return "No valid " + Title.toLowerCase() + " name is specified in argument \"" + argument + "\".";
		}

		/**
		 * Returns a text describing that a model element type bounds specification is
		 * invalid.
		 * 
		 * @param argument
		 *            The specification of the bounds.
		 * @param explenation
		 *            The reason for invalidity.
		 * @return A text describing that a model element type bounds specification is
		 *         invalid.
		 */
		public String ArgumentNotContainsValidBounds(String argument, String explenation) {
			if (argument == null || explenation == null) {
				throw new IllegalArgumentException();
			}
			return "No valid " + Title.toLowerCase() + " bounds are specified in argument \"" + argument + "\". "
					+ explenation;
		}

		/**
		 * 
		 * @return A text describing that a lower bound in a model element type bounds
		 *         specification is illegally greater than the upper bound.
		 */
		public String DefaultLowerBoundGreaterThanDefaultUpperBound() {
			return "Default " + Title_Plural.toLowerCase() + " lower bound is illegally greater than default "
					+ Title_Plural.toLowerCase() + " upper bound.";
		}

		/**
		 * 
		 * @return A text describing that a lower bound in a model elements bounds
		 *         specification is illegally greater than the upper bound.
		 */
		public String LowerBoundGreaterThanUpperBound() {
			return Title.toLowerCase() + " lower bound ist illegally greater than " + Title.toLowerCase()
					+ " upper bound.";
		}

	}

	/**
	 * Returns {@link #GenerationFailedBaseMessage} with details.
	 * 
	 * @param details
	 *            The details
	 * @return {@link #GenerationFailedBaseMessage} with details.
	 */
	public static String GenerationFailedBaseMessage(final String details) {
		if (details == null) {
			return GenerationFailedBaseMessage;
		}
		return GenerationFailedBaseMessage + " " + details;
	}

	/**
	 * Constructs no object.
	 */
	private UIElements() {
		throw new UnsupportedOperationException("No instance allowed");
	}
}
