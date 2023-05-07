package org.tzi.kodkod.clever.model2csp;

import org.apache.log4j.Logger;
import org.tzi.kodkod.clever.ui.UIElements;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;

/**
 * Translates an array of strings to an initial bounds specification for the
 * CSPs for the bounds tightening of UML/OCL models instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
public final class CLIUtilForInitialBoundspecification {

	/**
	 * RegEx that defines valid integer bounds as part of valid bounds
	 * specifications, e.g. "[-1,1]" or "[500,1000]" but also "[5,0]" but not
	 * "[03,4]" or "[3,04]".
	 */
	private final static String integerBoundsSpecificationRegex = "\\[[\\-]?[1-9][0-9]*,[\\-]?[1-9][0-9]*\\]";

	/**
	 * RegEx that defines valid seperators as part of bounds specifications.
	 */
	private final static String boundsSpecificationSeperatorRegex = "\\.";

	/**
	 * RegEx that defines valid classes key words as part of bounds specifications.
	 */
	private final static String classesBoundsSpecificationPrefixRegex = UIElements.ModelElements.Class.Title_Plural;

	/**
	 * RegEx that defines valid attributes key words as part of bounds
	 * specifications.
	 */
	private final static String attributesBoundsSpecificationPrefixRegex = UIElements.ModelElements.Attribute.Title_Plural;

	/**
	 * RegEx that defines valid associations key words as part of bounds
	 * specifications.
	 */
	private final static String associationsBoundsSpecificationPrefixRegex = UIElements.ModelElements.Association.Title_Plural;

	/**
	 * RegEx that defines valid class key words as part of bounds specifications.
	 */
	private final static String specificClassBoundsSpecificationPrefixRegex = UIElements.ModelElements.Class.Title;

	/**
	 * RegEx that defines valid attribute key words as part of bounds
	 * specifications.
	 */
	private final static String specificAttributeBoundsSpecificationPrefixRegex = UIElements.ModelElements.Attribute.Title;

	/**
	 * RegEx that defines valid association key words as part of bounds
	 * specifications.
	 */
	private final static String specificAssociationBoundsSpecificationPrefixRegex = UIElements.ModelElements.Association.Title;

	/**
	 * RegEx that defines valid element key words as part of bounds specifications.
	 */
	private final static String elementNameRegex = "[^\\.]+";

	/**
	 * RegEx that defines valid class name key words as part of bounds
	 * specifications.
	 */
	private final static String classNameRegex = elementNameRegex;

	/**
	 * RegEx that defines valid attribute name key words as part of bounds
	 * specifications.
	 */
	private final static String attributeNameRegex = elementNameRegex;

	/**
	 * RegEx that defines valid association name key words as part of bounds
	 * specifications.
	 */
	private final static String associationNameRegex = elementNameRegex;

	/**
	 * Translates an array of strings to an initial bounds specification for the
	 * CSPs for the bounds tightening of UML/OCL models instance finder
	 * configurations.
	 * 
	 * @param arguments
	 *            The array of strings.
	 * @param model
	 *            The UML/OCL model.
	 * @param log
	 *            The logger.
	 * @return
	 */
	public static IModelCSPVariablesInitialBoundsSpecification readInitialBoundsSpecification(String[] arguments,
			IModel model, Logger log) {
		if (arguments == null || model == null || log == null) {
			throw new IllegalArgumentException();
		}
		if (arguments.length < 1) {
			log.error(UIElements.CLI_NoDefaultLowerBoundArgument);
			return null;
		}
		Integer defaultLowerBound;
		try {
			defaultLowerBound = Integer.parseInt(arguments[0]);
		} catch (NumberFormatException e) {
			log.error(UIElements.CLI_InvalidDefaultLowerBoundArgument);
			return null;
		}
		if (defaultLowerBound < 0) {
			log.error(UIElements.CLI_ToTinyDefaultLowerBoundArgument);
			return null;
		}
		if (arguments.length < 2) {
			log.error(UIElements.CLI_NoDefaultUpperBoundArgument);
			return null;
		}
		Integer defaultUpperBound;
		try {
			defaultUpperBound = Integer.parseInt(arguments[1]);
		} catch (NumberFormatException e) {
			log.error(UIElements.CLI_InvalidDefaultUpperBoundArgument);
			return null;
		}
		if (defaultLowerBound > defaultUpperBound) {
			log.error(UIElements.CLI_ToTinyDefaultUpperBoundArgument);
			return null;
		}

		IModelCSPVariablesInitialBoundsSpecification initialBoundsSpecification = new IModelCSPVariablesInitialBoundsSpecification(
				model, defaultLowerBound, defaultUpperBound);

		final String classesBoundsSpecificationRegex = classesBoundsSpecificationPrefixRegex
				+ boundsSpecificationSeperatorRegex + integerBoundsSpecificationRegex;
		final String attributesBoundsSpecificationRegex = attributesBoundsSpecificationPrefixRegex
				+ boundsSpecificationSeperatorRegex + integerBoundsSpecificationRegex;
		final String associationsBoundsSpecificationRegex = associationsBoundsSpecificationPrefixRegex
				+ boundsSpecificationSeperatorRegex + integerBoundsSpecificationRegex;

		final String specificClassBoundsSpecificationRegex = specificClassBoundsSpecificationPrefixRegex
				+ boundsSpecificationSeperatorRegex + classNameRegex + boundsSpecificationSeperatorRegex
				+ integerBoundsSpecificationRegex;
		final String specificAttributeBoundsSpecificationRegex = specificClassBoundsSpecificationPrefixRegex
				+ boundsSpecificationSeperatorRegex + classNameRegex + boundsSpecificationSeperatorRegex
				+ specificAttributeBoundsSpecificationPrefixRegex + boundsSpecificationSeperatorRegex
				+ attributeNameRegex + boundsSpecificationSeperatorRegex + integerBoundsSpecificationRegex;
		final String specificAssociationBoundsSpecificationRegex = specificAssociationBoundsSpecificationPrefixRegex
				+ boundsSpecificationSeperatorRegex + associationNameRegex + boundsSpecificationSeperatorRegex
				+ integerBoundsSpecificationRegex;

		for (int i = 2; i < arguments.length; i++) {
			String argument = arguments[i];
			if (argument.matches(classesBoundsSpecificationRegex)) {
				String[] splits = argument.split(boundsSpecificationSeperatorRegex);
				String integerBoundsSpecification = splits[1];
				String[] integerBoundsSpecificationSplits = integerBoundsSpecification.split(",");
				final int lowerBound;
				try {
					lowerBound = Integer.parseInt(integerBoundsSpecificationSplits[0].substring(1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Class.ArgumentNotContainsValidDefaultLowerBound(argument));
					return null;
				}
				final int upperBound;
				try {
					upperBound = Integer.parseInt(integerBoundsSpecificationSplits[1].substring(0,
							integerBoundsSpecificationSplits[1].length() - 1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Class.ArgumentNotContainsValidDefaultUpperBound(argument));
					return null;
				}
				try {
					initialBoundsSpecification.setDefaultClassesBounds(lowerBound, upperBound);
				} catch (IllegalInitialBoundException e) {
					log.error(UIElements.ModelElements.Class.ArgumentNotContainsValidDefaultBounds(argument,
							e.getMessage()));
					return null;
				}
			} else if (argument.matches(attributesBoundsSpecificationRegex)) {
				String[] splits = argument.split(boundsSpecificationSeperatorRegex);
				String integerBoundsSpecification = splits[1];
				String[] integerBoundsSpecificationSplits = integerBoundsSpecification.split(",");
				final int lowerBound;
				try {
					lowerBound = Integer.parseInt(integerBoundsSpecificationSplits[0].substring(1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Attribute.ArgumentNotContainsValidDefaultLowerBound(argument));
					return null;
				}
				final int upperBound;
				try {
					upperBound = Integer.parseInt(integerBoundsSpecificationSplits[1].substring(0,
							integerBoundsSpecificationSplits[1].length() - 1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Attribute.ArgumentNotContainsValidDefaultUpperBound(argument));
					return null;
				}
				try {
					initialBoundsSpecification.setDefaultAttributesBounds(lowerBound, upperBound);
				} catch (IllegalInitialBoundException e) {
					log.error(UIElements.ModelElements.Attribute.ArgumentNotContainsValidDefaultBounds(argument,
							e.getMessage()));
					return null;
				}
			} else if (argument.matches(associationsBoundsSpecificationRegex)) {
				String[] splits = argument.split(boundsSpecificationSeperatorRegex);
				String integerBoundsSpecification = splits[1];
				String[] integerBoundsSpecificationSplits = integerBoundsSpecification.split(",");
				final int lowerBound;
				try {
					lowerBound = Integer.parseInt(integerBoundsSpecificationSplits[0].substring(1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Association.ArgumentNotContainsValidDefaultLowerBound(argument));
					return null;
				}
				final int upperBound;
				try {
					upperBound = Integer.parseInt(integerBoundsSpecificationSplits[1].substring(0,
							integerBoundsSpecificationSplits[1].length() - 1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Association.ArgumentNotContainsValidDefaultUpperBound(argument));
					return null;
				}

				try {
					initialBoundsSpecification.setDefaultAssociationsBounds(lowerBound, upperBound);
				} catch (IllegalInitialBoundException e) {
					log.error(UIElements.ModelElements.Association.ArgumentNotContainsValidDefaultBounds(argument,
							e.getMessage()));
					return null;
				}
			} else if (argument.matches(specificClassBoundsSpecificationRegex)) {
				String[] splits = argument.split(boundsSpecificationSeperatorRegex);
				String integerBoundsSpecification = splits[2];
				String[] integerBoundsSpecificationSplits = integerBoundsSpecification.split(",");
				final int lowerBound;
				try {
					lowerBound = Integer.parseInt(integerBoundsSpecificationSplits[0].substring(1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Class.ArgumentNotContainsValidLowerBound(argument));
					return null;
				}
				final int upperBound;
				try {
					upperBound = Integer.parseInt(integerBoundsSpecificationSplits[1].substring(0,
							integerBoundsSpecificationSplits[1].length() - 1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Class.ArgumentNotContainsValidUpperBound(argument));
					return null;
				}
				String className = splits[1];
				IClass iClass = null;
				for (IClass potentialIClass : model.classes()) {
					if (className.equals(potentialIClass.name())) {
						iClass = potentialIClass;
						break;
					}
				}
				if (iClass == null) {
					log.error(UIElements.ModelElements.Class.ArgumentNotContainsValidName(argument));
					return null;
				}
				try {
					initialBoundsSpecification.setSpecificClassBounds(iClass, lowerBound, upperBound);
				} catch (IllegalInitialBoundException e) {
					log.error(UIElements.ModelElements.Class.ArgumentNotContainsValidBounds(argument, e.getMessage()));
					return null;
				}
			} else if (argument.matches(specificAttributeBoundsSpecificationRegex)) {
				String[] splits = argument.split(boundsSpecificationSeperatorRegex);
				String integerBoundsSpecification = splits[4];
				String[] integerBoundsSpecificationSplits = integerBoundsSpecification.split(",");
				final int lowerBound;
				try {
					lowerBound = Integer.parseInt(integerBoundsSpecificationSplits[0].substring(1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Attribute.ArgumentNotContainsValidLowerBound(argument));
					return null;
				}
				final int upperBound;
				try {
					upperBound = Integer.parseInt(integerBoundsSpecificationSplits[1].substring(0,
							integerBoundsSpecificationSplits[1].length() - 1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Attribute.ArgumentNotContainsValidUpperBound(argument));
					return null;
				}
				String className = splits[1];
				IClass iClass = null;
				for (IClass potentialIClass : model.classes()) {
					if (className.equals(potentialIClass.name())) {
						iClass = potentialIClass;
						break;
					}
				}
				if (iClass == null) {
					log.error(UIElements.ModelElements.Class.ArgumentNotContainsValidName(argument));
					return null;
				}
				String attributeName = splits[3];
				IAttribute iAttribute = null;
				for (IAttribute potentialIAttribute : iClass.allAttributes()) {
					if (attributeName.equals(potentialIAttribute.name())) {
						iAttribute = potentialIAttribute;
						break;
					}
				}
				if (iAttribute == null) {
					log.error(UIElements.ModelElements.Attribute.ArgumentNotContainsValidName(argument));
					return null;
				}
				try {
					initialBoundsSpecification.setSpecificAttributeBounds(iClass, iAttribute, lowerBound, upperBound);
				} catch (IllegalInitialBoundException e) {
					log.error(UIElements.ModelElements.Attribute.ArgumentNotContainsValidBounds(argument,
							e.getMessage()));
					return null;
				}
			} else if (argument.matches(specificAssociationBoundsSpecificationRegex)) {
				String[] splits = argument.split(boundsSpecificationSeperatorRegex);
				String integerBoundsSpecification = splits[2];
				String[] integerBoundsSpecificationSplits = integerBoundsSpecification.split(",");
				final int lowerBound;
				try {
					lowerBound = Integer.parseInt(integerBoundsSpecificationSplits[0].substring(1));
				} catch (Exception e) {
					log.error(UIElements.ModelElements.Association.ArgumentNotContainsValidLowerBound(argument));
					return null;
				}
				final int upperBound;
				try {
					upperBound = Integer.parseInt(integerBoundsSpecificationSplits[1].substring(0,
							integerBoundsSpecificationSplits[1].length() - 1));

				} catch (Exception e) {
					log.error(UIElements.ModelElements.Association.ArgumentNotContainsValidUpperBound(argument));
					return null;
				}
				String associationName = splits[1];
				IAssociation iAssociation = null;
				for (IAssociation potentialIAssociation : model.associations()) {
					if (associationName.equals(potentialIAssociation.name())) {
						iAssociation = potentialIAssociation;
						break;
					}
				}
				if (iAssociation == null) {
					log.error(UIElements.ModelElements.Association.ArgumentNotContainsValidName(argument));
					return null;
				}
				try {
					initialBoundsSpecification.setSpecificAssociationBounds(iAssociation, lowerBound, upperBound);
				} catch (IllegalInitialBoundException e) {
					log.error(UIElements.ModelElements.Association.ArgumentNotContainsValidBounds(argument,
							e.getMessage()));
					return null;
				}
			} else {
				log.error(UIElements.CLI_InvalidArgument(argument,
						new String[] { classesBoundsSpecificationRegex, attributesBoundsSpecificationRegex,
								associationsBoundsSpecificationRegex, specificClassBoundsSpecificationRegex,
								specificAttributeBoundsSpecificationRegex,
								specificAssociationBoundsSpecificationRegex }));
				return null;
			}
		}
		return initialBoundsSpecification;
	}
}
