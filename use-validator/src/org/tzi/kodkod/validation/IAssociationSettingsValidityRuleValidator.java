package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.util.Strings;
import org.tzi.kodkod.model.config.impl.PropertyConfigurationVisitor;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAssociationEnd;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.impl.Multiplicity;
import org.tzi.kodkod.model.impl.Range;
import org.tzi.use.kodkod.plugin.gui.model.data.AssociationSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Interface for validators that validate configuration aspects for
 * associations.
 * 
 * @author Jan Prien
 *
 */
interface IAssociationSettingsValidityRuleValidator extends IInstanceSettingsValdityRuleValidator {

	/**
	 * Extracts all configurations aspects for associations that associate a
	 * selected class.
	 * 
	 * @param settings
	 *            The configuration.
	 * @param iClass
	 *            The associated class.
	 * @return All configurations aspects for associations that associate the class.
	 */
	default Set<AssociationSettings> getAssociationSettings(SettingsConfiguration settings, IClass iClass) {
		if (settings == null || iClass == null) {
			throw new IllegalArgumentException();
		}
		Set<AssociationSettings> ret = new HashSet<>();
		List<AssociationSettings> allAssociationSettings = settings.getAllAssociationSettings();
		for (AssociationSettings associationSettings : allAssociationSettings) {
			if (getAssociatedClasses(associationSettings).contains(iClass)) {
				ret.add(associationSettings);
			}
		}
		return ret;
	}

	/**
	 * Extracts all multiplicities for a class that is associated in an association.
	 * 
	 * @param associationSettings
	 *            The configuration aspect for the association.
	 * @param iClass
	 *            The associated class.
	 * @return All multiplicities for the class that is associated in the
	 *         association.
	 */
	default Set<Multiplicity> getMultiplicities(AssociationSettings associationSettings, IClass iClass) {
		if (associationSettings == null || iClass == null) {
			throw new IllegalArgumentException();
		}
		IAssociation iAssociation = associationSettings.getAssociation();
		List<IAssociationEnd> associationEnds = iAssociation.associationEnds();
		Set<Multiplicity> mulpiplicities = new HashSet<>();
		for (IAssociationEnd associationEnd : associationEnds) {
			IClass associatedClass = associationEnd.associatedClass();
			if (associatedClass == iClass) {
				Multiplicity multiplicity = associationEnd.multiplicity();
				mulpiplicities.add(multiplicity);
			}
		}
		if (mulpiplicities.size() != 0) {
			return mulpiplicities;
		}
		return null;
	}

	/**
	 * Extracts the maximum upper multiplicity bound for a a class that is
	 * associated in an association.
	 * 
	 * @param associationSettings
	 *            The configuration aspect for the association.
	 * @param iClass
	 *            The associated class.
	 * @return The maximum upper multiplicity bound for the a class that is
	 *         associated in the association.
	 */
	default Integer getMaxMaxMultiplicity(AssociationSettings associationSettings, IClass iClass) {
		return getMaxMaxMultiplicity(getMultiplicities(associationSettings, iClass));
	}

	/**
	 * Extracts the minimum lower multiplicity bound for a a class that is
	 * associated in an association.
	 * 
	 * @param associationSettings
	 *            The configuration aspect for the association.
	 * @param iClass
	 *            The associated class.
	 * @return The minimum lower multiplicity bound for the a class that is
	 *         associated in the association.
	 */
	default Integer getMinMinMultiplicity(AssociationSettings associationSettings, IClass iClass) {
		return getMinMinMultiplicity(getMultiplicities(associationSettings, iClass));
	}

	/**
	 * Extracts the maximum lower multiplicity bound for a a class that is
	 * associated in an association.
	 * 
	 * @param associationSettings
	 *            The configuration aspect for the association.
	 * @param iClass
	 *            The associated class.
	 * @return The maximum lower multiplicity bound for the a class that is
	 *         associated in the association.
	 */
	default Integer getMaxMinMultiplicity(AssociationSettings associationSettings, IClass iClass) {
		return getMaxMinMultiplicity(getMultiplicities(associationSettings, iClass));
	}

	/**
	 * Extracts the minimum lower multiplicity bound from selected multiplicities.
	 * 
	 * @param multiplicities
	 *            The multiplicities.
	 * @return The minimum lower multiplicity bound from the multiplicities.
	 */
	default Integer getMinMinMultiplicity(Set<Multiplicity> multiplicities) {
		if (multiplicities == null || multiplicities.size() < 1) {
			return null;
		}
		final int start = Multiplicity.MANY - 1;
		int minimum = start;
		for (Multiplicity multiplicity : multiplicities) {
			if (multiplicity == null) {
				throw new IllegalArgumentException();
			}
			final int multiplicityMinimum = getMinMultiplicity(multiplicity);
			if (minimum == start || minimum == Multiplicity.MANY
					|| multiplicityMinimum != Multiplicity.MANY && minimum > multiplicityMinimum) {
				minimum = multiplicityMinimum;
			}
		}
		return minimum;
	}

	/**
	 * Extracts the maximum lower multiplicity bound from selected multiplicities.
	 * 
	 * @param multiplicities
	 *            The multiplicities.
	 * @return The maximum lower multiplicity bound from the multiplicities.
	 */
	default Integer getMaxMinMultiplicity(Set<Multiplicity> multiplicities) {
		if (multiplicities == null || multiplicities.size() < 1) {
			return null;
		}
		final int start = Multiplicity.MANY - 1;
		int maximum = start;
		for (Multiplicity multiplicity : multiplicities) {
			if (multiplicity == null) {
				throw new IllegalArgumentException();
			}
			final int multiplicityMinimum = getMinMultiplicity(multiplicity);
			if (maximum == start || multiplicityMinimum == Multiplicity.MANY
					|| maximum != Multiplicity.MANY && maximum < multiplicityMinimum) {
				maximum = multiplicityMinimum;
			}
		}
		return maximum;
	}

	/**
	 * Extracts the maximum upper multiplicity bound from selected multiplicities.
	 * 
	 * @param multiplicities
	 *            The multiplicities.
	 * @return The maximum upper multiplicity bound from the multiplicities.
	 */
	default Integer getMaxMaxMultiplicity(Set<Multiplicity> multiplicities) {
		if (multiplicities == null || multiplicities.size() < 1) {
			return null;
		}
		final int start = Multiplicity.MANY - 1;
		int maximum = start;
		for (Multiplicity multiplicity : multiplicities) {
			if (multiplicity == null) {
				throw new IllegalArgumentException();
			}
			final int multiplicityMaximum = getMaxMultiplicity(multiplicity);
			if (maximum == start || multiplicityMaximum == Multiplicity.MANY
					|| maximum != Multiplicity.MANY && maximum < multiplicityMaximum) {
				maximum = multiplicityMaximum;
			}
		}
		return maximum;
	}

	/**
	 * Extracts the upper multiplicity bound from a selected multiplicity.
	 * 
	 * @param multiplicity
	 *            THe multiplicity.
	 * @return The upper multiplicity bound from a the multiplicity.
	 */
	default Integer getMaxMultiplicity(Multiplicity multiplicity) {
		if (multiplicity == null) {
			throw new IllegalArgumentException();
		}
		List<Range> ranges = multiplicity.getRanges();
		if (ranges.size() == 0) {
			throw new UnsupportedOperationException("Mulitplicity does not have a least one range.");
		}
		final int start = Multiplicity.MANY - 1;
		int highest = start;
		for (Range range : ranges) {
			final int maximum = range.getUpper();
			if (maximum != Multiplicity.MANY && maximum < 0) {
				throw new UnsupportedOperationException("Multiplicity upper bound is " + maximum + "but has to be "
						+ Multiplicity.MANY + "or greater than or equal " + 0 + ".");
			}
			if (highest == start || maximum == Multiplicity.MANY || highest != Multiplicity.MANY && highest < maximum) {
				highest = maximum;
			}
		}
		return highest;
	}

	/**
	 * Extracts the lower multiplicity bound from a selected multiplicity.
	 * 
	 * @param multiplicity
	 *            THe multiplicity.
	 * @return The lower multiplicity bound from a the multiplicity.
	 */
	default Integer getMinMultiplicity(Multiplicity multiplicity) {
		if (multiplicity == null) {
			throw new IllegalArgumentException();
		}
		List<Range> ranges = multiplicity.getRanges();
		if (ranges.size() == 0) {
			throw new UnsupportedOperationException("Mulitplicity does not have a least one range.");
		}
		final int start = Multiplicity.MANY - 1;
		int lowest = start;
		for (Range range : ranges) {
			final int minimum = range.getLower();
			if (minimum != Multiplicity.MANY && minimum < 0) {
				throw new UnsupportedOperationException("Multiplicity lower bound is " + minimum + "but has to be "
						+ Multiplicity.MANY + "or greater than or equal " + 0 + ".");
			}
			if (lowest == start || lowest == Multiplicity.MANY || minimum != Multiplicity.MANY && lowest > minimum) {
				lowest = minimum;
			}
		}
		return lowest;
	}

	/**
	 * Extracts the associated classes from a configuration aspect for an
	 * association.
	 * 
	 * @param associationSettings
	 *            The configuration aspect for the association.
	 * @return The associated classes from the configuration aspect for the
	 *         association.
	 */
	default List<IClass> getAssociatedClasses(AssociationSettings associationSettings) {
		if (associationSettings == null) {
			throw new IllegalArgumentException();
		}
		IAssociation association = associationSettings.getAssociation();
		List<IAssociationEnd> associationEnds = association.associationEnds();
		List<IClass> associatedClasses = new ArrayList<IClass>();
		for (IAssociationEnd associationEnd : associationEnds) {
			IClass associatedClass = associationEnd.associatedClass();
			associatedClasses.add(associatedClass);
		}
		return associatedClasses;
	}

	/**
	 * Extracts the number of required links from a configuration aspect for an
	 * association.
	 * 
	 * @param config
	 *            The configuration aspect for the association.
	 * @param size
	 *            The number of objects linked in each required link.
	 * @return The number of required links from the configuration aspect for the
	 *         association.
	 */
	default int getRequiredLinksCardinality(AssociationSettings config, int size) {
		if (config == null) {
			throw new IllegalArgumentException();
		}
		return getRequiredLinks(config, size).asSet().size();
	}

	/**
	 * A tuple of objects that represents an required link.
	 * 
	 * The textual representation starts with "(". Then each string for an object is
	 * listed, separated by an ",". At the end, there is ")". A textual
	 * representation is for example "(a,b,c)".
	 * 
	 * @see PropertyConfigurationVisitor#readComplexElements
	 * 
	 * @author Jan Prien
	 *
	 */
	static class RequiredLink {

		/**
		 * The linked objects.
		 */
		private final String[] elements;

		/**
		 * Constructs an object.
		 * 
		 * @param elements
		 *            The linked objects.
		 */
		public RequiredLink(String[] elements) {
			if (elements == null) {
				throw new IllegalArgumentException();
			}
			this.elements = elements;
		}

		/**
		 * Reads a link from a textual representation.
		 * 
		 * 
		 * @param string
		 *            The textual representation.
		 * @return A link read from the textual representation.
		 */
		static RequiredLink fromString(String string, int size) {
			if (string == null || size < 2) {
				throw new IllegalArgumentException();
			}
			String[] splits = string.split(",");
			if (!string.startsWith("(") || !string.endsWith(")") || splits.length != size) {
				throw new IllegalArgumentException();
			}
			final String[] elements = new String[size];
			int i = -1;
			for (String split : splits) {
				i++;
				if (split.length() < 2) {
					throw new IllegalArgumentException();
				}
				if (i == 0) {
					elements[i] = split.substring(1);
				} else if (i == size - 1) {
					elements[i] = split.substring(1, split.length() - 1);
				} else {
					elements[i] = split.substring(1);
				}
			}
			return new RequiredLink(elements);
		}

		String[] getElements() {
			return elements;
		}

		/**
		 * 
		 * @return The textual representation of the link.
		 */
		String asString() {
			return "(" + Strings.join(",", elements) + ")";
		}
	}

	/**
	 * A set of required links.
	 * 
	 * @author Jan Prien
	 *
	 */
	static class RequiredLinks {

		/**
		 * The set of required links.
		 */
		private final Set<RequiredLink> requiredLinks;

		/**
		 * Constructs an object.
		 */
		RequiredLinks() {
			this.requiredLinks = new HashSet<>();
		}

		/**
		 * Reads links from textual representations.
		 * 
		 * @param strings
		 *            The textual representations of the links.
		 * @param size
		 *            The number of objects in each required link.
		 * @return The set of required links.
		 */
		static RequiredLinks fromStrings(Set<String> strings, int size) {
			if (strings == null) {
				throw new IllegalArgumentException();
			}
			RequiredLinks requiredLinks = new RequiredLinks();
			for (String string : strings) {
				requiredLinks.requiredLinks.add(RequiredLink.fromString(string, size));
			}
			return requiredLinks;
		}

		/**
		 * Reads links a set of required links.
		 * 
		 * @param requiredLinks
		 *            The set of required links.
		 * @return The set of required links.
		 */
		static RequiredLinks fromSet(Set<RequiredLink> requiredLinks) {
			if (requiredLinks == null || requiredLinks.contains(null)) {
				throw new IllegalArgumentException();
			}
			RequiredLinks requiredLinksObj = new RequiredLinks();
			requiredLinksObj.requiredLinks.addAll(requiredLinks);
			return requiredLinksObj;
		}

		/**
		 * 
		 * @return The textual representation of the links.
		 */
		Set<String> asStrings() {
			Set<String> strings = new HashSet<>();
			for (RequiredLink requiredLink : requiredLinks) {
				strings.add(requiredLink.asString());
			}
			return strings;
		}

		/**
		 * 
		 * @return The set of the links.
		 */
		Set<RequiredLink> asSet() {
			return requiredLinks;
		}
	}

	/**
	 * Extracts the required links from a configuration aspect for an association.
	 * 
	 * @param config
	 *            The configuration aspect for an association.
	 * @param size
	 *            The number of objects in each required link.
	 * @return The required links.
	 */
	default RequiredLinks getRequiredLinks(AssociationSettings config, int size) {
		if (config == null) {
			throw new IllegalArgumentException();
		}
		return RequiredLinks.fromStrings(config.getInstanceNames(), size);
	}

}
