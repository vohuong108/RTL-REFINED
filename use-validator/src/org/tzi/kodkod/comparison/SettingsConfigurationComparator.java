package org.tzi.kodkod.comparison;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.tzi.kodkod.comparison.ui.UIElements;
import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.type.TypeConstants;
import org.tzi.use.kodkod.plugin.gui.model.data.AssociationSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.IntegerSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.InvariantSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.OptionSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.RealSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.kodkod.plugin.gui.model.data.StringSettings;

/**
 * Compares configurations.
 * 
 * @author Jan Prien
 *
 */
public final class SettingsConfigurationComparator extends AbstractSettingsConfigurationComparator<ComparisonResult> {

	/**
	 * The functionality to merge results.
	 */
	private final ComparisonResultMerger resultMerger;

	/**
	 * Constructs an object.
	 */
	public SettingsConfigurationComparator() {
		this.resultMerger = new ComparisonResultMerger();
	}

	@Override
	public ComparisonResult compareSettingsConfigurations(final SettingsConfiguration leftConfiguration,
			final SettingsConfiguration rightConfiguration) throws Exception {
		if (leftConfiguration == null || rightConfiguration == null) {
			throw new IllegalArgumentException();
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();
		comparisonResults.add(compareIntegerSettings(leftConfiguration.getIntegerTypeSettings(),
				rightConfiguration.getIntegerTypeSettings()));
		comparisonResults.add(compareStringSettings(leftConfiguration.getStringTypeSettings(),
				rightConfiguration.getStringTypeSettings()));
		comparisonResults.add(
				compareRealSettings(leftConfiguration.getRealTypeSettings(), rightConfiguration.getRealTypeSettings()));
		comparisonResults.add(
				compareOptionSettings(leftConfiguration.getOptionSettings(), rightConfiguration.getOptionSettings()));
		comparisonResults.add(compareClassSettings(leftConfiguration.getAllClassesSettings(),
				rightConfiguration.getAllClassesSettings()));
		comparisonResults.add(compareAssociationSettings(leftConfiguration.getAllAssociationSettings(),
				rightConfiguration.getAllAssociationSettings()));
		comparisonResults.add(compareInvariantSettings(leftConfiguration.getAllInvariantsSettings(),
				rightConfiguration.getAllInvariantsSettings()));
		return resultMerger.mergeComparisonResults(comparisonResults, leftConfiguration, rightConfiguration);
	}

	/**
	 * Compares a set of configuration aspects for invariants.
	 * 
	 * Always returns merged comparison result. This includes the merged result for
	 * each configuration aspect. This includes
	 * {@link ComparisonResults#NOT_COMPARABLE} if the sets have not the same size
	 * or there is one aspect in one list without a comparison partner.
	 * 
	 * @param leftAllInvariantsSettings
	 *            The set of configuration aspects used as first operand for binary
	 *            comparison.
	 * @param rightAllInvariantsSettings
	 *            The set of configuration aspects used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareInvariantSettings(final List<InvariantSettings> leftAllInvariantsSettings,
			final List<InvariantSettings> rightAllInvariantsSettings) {
		if (leftAllInvariantsSettings == null || rightAllInvariantsSettings == null
				|| leftAllInvariantsSettings.contains(null) || rightAllInvariantsSettings.contains(null)) {
			throw new IllegalArgumentException();
		}
		if (leftAllInvariantsSettings.size() != rightAllInvariantsSettings.size()) {
			return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllInvariantsSettings,
					rightAllInvariantsSettings,
					new SingleComparisonDocumentation(
							UIElements.ListOfSettingsHasDifferentSizes(UIElements.InvariantSettingsDescriptor,
									leftAllInvariantsSettings.size(), rightAllInvariantsSettings.size())));
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();
		final List<InvariantSettings> handeledRightInvariantSettings = new ArrayList<>();
		for (InvariantSettings leftInvariantSettings : leftAllInvariantsSettings) {
			ComparisonResult comparisonResult = null;
			for (InvariantSettings rightInvariantSettings : rightAllInvariantsSettings) {
				if (leftInvariantSettings.getInvariant() == rightInvariantSettings.getInvariant()) {
					if (handeledRightInvariantSettings.contains(rightInvariantSettings)) {
						// multiple InvariantSettings for same IAttribute in leftAllInvariantsSettings
						// found
						return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllInvariantsSettings,
								rightAllInvariantsSettings,
								new SingleComparisonDocumentation(UIElements.SettingsFromRightListWouldBeUsedTwice(
										UIElements.InvariantSettingsDescriptor,
										rightInvariantSettings.getInvariant().name())));
					} else {
						handeledRightInvariantSettings.add(rightInvariantSettings);
						comparisonResult = compareInvariantSettings(leftInvariantSettings, rightInvariantSettings);
						break;
					}
				}
			}
			if (comparisonResult == null) {
				return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllInvariantsSettings,
						rightAllInvariantsSettings,
						new SingleComparisonDocumentation(UIElements.SettingsFromLeftListHasNoCoresnpondInRightList(
								UIElements.InvariantSettingsDescriptor, leftInvariantSettings.getInvariant().name())));
			}
			comparisonResults.add(comparisonResult);
		}
		if (!handeledRightInvariantSettings.containsAll(rightAllInvariantsSettings)) {
			// not all InvariantSettings in rightAllInvariantsSettings were used for
			// comparison
			return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllInvariantsSettings,
					rightAllInvariantsSettings, new SingleComparisonDocumentation(
							UIElements.SettingsRightListNotFullyUsed(UIElements.InvariantSettingsDescriptor)));
		}
		if (comparisonResults.size() == 0) {
			return new ComparisonResult(ComparisonResults.EQUAL, leftAllInvariantsSettings, rightAllInvariantsSettings,
					new SingleComparisonDocumentation(
							UIElements.ListOfSettingsIsEmpty(UIElements.InvariantSettingsDescriptor)));
		}
		return resultMerger.mergeComparisonResults(comparisonResults, leftAllInvariantsSettings,
				rightAllInvariantsSettings);
	}

	/**
	 * Compares two configuration aspects for invariants.
	 * 
	 * Always returns merged comparison result. This includes
	 * {@link ComparisonResults#NOT_COMPARABLE} for the negate, active values.
	 * 
	 * @param leftInvariantSettings
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param rightInvariantSettings
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareInvariantSettings(final InvariantSettings leftInvariantSettings,
			final InvariantSettings rightInvariantSettings) {
		if (leftInvariantSettings == null || rightInvariantSettings == null
				|| leftInvariantSettings.getInvariant() != rightInvariantSettings.getInvariant()) {
			throw new IllegalArgumentException();
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();
		comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftInvariantSettings,
				rightInvariantSettings, new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(
						leftInvariantSettings.getInvariant().name() + PropertyEntry.INVARIANT_NEGATE))));
		comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftInvariantSettings,
				rightInvariantSettings, new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(
						leftInvariantSettings.getInvariant().name() + PropertyEntry.INVARIANT_ACTIVE))));
		comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftInvariantSettings,
				rightInvariantSettings, new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(
						leftInvariantSettings.getInvariant().name() + PropertyEntry.INVARIANT_INACTIVE))));
		return resultMerger.mergeComparisonResults(comparisonResults, leftInvariantSettings, rightInvariantSettings);
	}

	/**
	 * Compares a set of configuration aspects for associations.
	 * 
	 * Always returns merged comparison result. This includes the merged result for
	 * each configuration aspect. This includes
	 * {@link ComparisonResults#NOT_COMPARABLE} if the sets have not the same size
	 * or there is one aspect in one list without a comparison partner.
	 * 
	 * @param leftAllAssociationSettings
	 *            The set of configuration aspects used as first operand for binary
	 *            comparison.
	 * @param rightAllAssociationSettings
	 *            The set of configuration aspects used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareAssociationSettings(final List<AssociationSettings> leftAllAssociationSettings,
			final List<AssociationSettings> rightAllAssociationSettings) {
		if (leftAllAssociationSettings == null || rightAllAssociationSettings == null
				|| leftAllAssociationSettings.contains(null) || rightAllAssociationSettings.contains(null)) {
			throw new IllegalArgumentException();
		}
		if (leftAllAssociationSettings.size() != rightAllAssociationSettings.size()) {
			return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllAssociationSettings,
					rightAllAssociationSettings,
					new SingleComparisonDocumentation(
							UIElements.ListOfSettingsHasDifferentSizes(UIElements.AssociationSettingsDescriptor,
									leftAllAssociationSettings.size(), rightAllAssociationSettings.size())));
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();
		final List<AssociationSettings> handeledRightAssociationSettings = new ArrayList<>();
		for (AssociationSettings leftAssociationSettings : leftAllAssociationSettings) {
			ComparisonResult comparisonResult = null;
			for (AssociationSettings rightAssociationSettings : rightAllAssociationSettings) {
				if (leftAssociationSettings.getAssociation() == rightAssociationSettings.getAssociation()) {
					if (handeledRightAssociationSettings.contains(rightAssociationSettings)) {
						// multiple InvariantSettings for same IAttribute in leftAllInvariantsSettings
						// found
						return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllAssociationSettings,
								rightAllAssociationSettings,
								new SingleComparisonDocumentation(UIElements.SettingsFromRightListWouldBeUsedTwice(
										UIElements.AssociationSettingsDescriptor,
										rightAssociationSettings.getAssociation().name())));
					} else {
						handeledRightAssociationSettings.add(rightAssociationSettings);
						comparisonResult = compareAssociationSettings(leftAssociationSettings,
								rightAssociationSettings);
						break;
					}
				}
			}
			if (comparisonResult == null) {
				return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllAssociationSettings,
						rightAllAssociationSettings,
						new SingleComparisonDocumentation(UIElements.SettingsFromLeftListHasNoCoresnpondInRightList(
								UIElements.AssociationSettingsDescriptor,
								leftAssociationSettings.getAssociation().name())));
			}
			comparisonResults.add(comparisonResult);
		}
		if (!handeledRightAssociationSettings.containsAll(rightAllAssociationSettings)) {
			// not all AssociationSettings in rightAllAssociationSettings were used for
			// comparison
			return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllAssociationSettings,
					rightAllAssociationSettings, new SingleComparisonDocumentation(
							UIElements.SettingsRightListNotFullyUsed(UIElements.AssociationSettingsDescriptor)));
		}
		if (comparisonResults.size() == 0) {
			return new ComparisonResult(ComparisonResults.EQUAL, leftAllAssociationSettings,
					rightAllAssociationSettings, new SingleComparisonDocumentation(
							UIElements.ListOfSettingsIsEmpty(UIElements.AssociationSettingsDescriptor)));
		}
		return resultMerger.mergeComparisonResults(comparisonResults, leftAllAssociationSettings,
				rightAllAssociationSettings);
	}

	/**
	 * Compares two configuration aspects for associations.
	 * 
	 * Always returns merged comparison result. This includes
	 * {@link ComparisonResults#IGNORED} for the the required links. This also
	 * includes:
	 * <ul>
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} when one of the minimum link
	 * numbers is less than $0$ or one of maximum links specifications is less than
	 * -1 or the minimum is greater than the maximum for a configuration when
	 * maximum is not -1.
	 * <li>{@link ComparisonResults#EQUAL} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and both minimum and minimum as
	 * well as maximum and maximum link numbers are equal.
	 * <li>{@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum link number of the
	 * first configuration is less than minimum link number of the second
	 * configuration and maximum link number of the first configuration is -1 or
	 * greater than maximum link number of the second configuration and maximum link
	 * number of the second configuration is not -1.
	 * <li>{@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum link number of the
	 * second configuration is less than minimum link number of the first
	 * configuration and maximum link number of the second configuration is -1 or
	 * greater than maximum link number of the first configuration and maximum link
	 * number of the first configuration is not -1.
	 * <li>{@link ComparisonResults#LEFT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and maximum link number of the
	 * first configuration is not -1 and less than minimum link number of the second
	 * configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and maximum link number of the
	 * second configuration is not -1 and less than minimum link number of the first
	 * configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum link number of the
	 * first configuration is less than minimum link number of the second
	 * configuration and maximum link number of the first configuration is not -1
	 * and less than maximum link number of the second configuration if that is not
	 * -1 and is also greater than or equal the minimum link number of the second
	 * configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum link number of the
	 * second configuration is less than minimum link number of the first
	 * configuration and maximum link number of the second configuration is not -1
	 * and less than maximum link number of the first configuration if that is not
	 * -1 and is also greater than or equal the minimum link number of the first
	 * configuration.
	 * <li>{@link ComparisonResults#UNEQUAL} elsewise.
	 * </ul>
	 * 
	 * @param leftAssociationSettings
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param rightAssociationSettings
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareAssociationSettings(AssociationSettings leftAssociationSettings,
			AssociationSettings rightAssociationSettings) {
		if (leftAssociationSettings == null || rightAssociationSettings == null
				|| leftAssociationSettings.getAssociation() != rightAssociationSettings.getAssociation()) {
			throw new IllegalArgumentException();
		}
		final String associationName = leftAssociationSettings.getAssociation().name();
		final Set<ComparisonResult> comparisonResults = new HashSet<>();

		comparisonResults.add(new SettingsConfigurationIntervalComparator().compareMinMax(leftAssociationSettings,
				rightAssociationSettings, associationName, PropertyEntry.linksMin, PropertyEntry.linksMax,
				leftAssociationSettings.getLowerBound(), leftAssociationSettings.getUpperBound(),
				rightAssociationSettings.getLowerBound(), rightAssociationSettings.getUpperBound(), false, false,
				true));
		comparisonResults
				.add(new ComparisonResult(ComparisonResults.IGNORED, leftAssociationSettings, rightAssociationSettings,
						new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(associationName))));

		return resultMerger.mergeComparisonResults(comparisonResults, leftAssociationSettings,
				rightAssociationSettings);
	}

	/**
	 * Compares a set of configuration aspects for classes.
	 * 
	 * Always returns merged comparison result. This includes the merged result for
	 * each configuration aspect. This includes
	 * {@link ComparisonResults#NOT_COMPARABLE} if the sets have not the same size
	 * or there is one aspect in one list without a comparison partner.
	 * 
	 * @param leftAllClassesSettings
	 *            The set of configuration aspects used as first operand for binary
	 *            comparison.
	 * @param rightAllClassesSettings
	 *            The set of configuration aspects used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareClassSettings(final List<ClassSettings> leftAllClassesSettings,
			final List<ClassSettings> rightAllClassesSettings) {
		if (leftAllClassesSettings == null || rightAllClassesSettings == null) {
			throw new IllegalArgumentException();
		}
		if (leftAllClassesSettings.size() != rightAllClassesSettings.size()) {
			return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllClassesSettings,
					rightAllClassesSettings,
					new SingleComparisonDocumentation(
							UIElements.ListOfSettingsHasDifferentSizes(UIElements.ClassSettingsDescriptor,
									leftAllClassesSettings.size(), rightAllClassesSettings.size())));
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();
		final List<ClassSettings> handeledRightClassesSettings = new ArrayList<>();
		for (ClassSettings leftClassSettings : leftAllClassesSettings) {
			ComparisonResult comparisonResult = null;
			for (ClassSettings rightClassSettings : rightAllClassesSettings) {
				if (leftClassSettings.getCls() == rightClassSettings.getCls()) {
					if (handeledRightClassesSettings.contains(rightClassSettings)) {
						// multiple ClassSettings for same IClass in leftAllClassesSettings found
						return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllClassesSettings,
								rightAllClassesSettings,
								new SingleComparisonDocumentation(UIElements.SettingsFromRightListWouldBeUsedTwice(
										UIElements.ClassSettingsDescriptor, rightClassSettings.getCls().name())));
					} else {
						handeledRightClassesSettings.add(rightClassSettings);
						comparisonResult = compareClassSettings(leftClassSettings, rightClassSettings);
						break;
					}
				}
			}
			if (comparisonResult == null) {
				return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllClassesSettings,
						rightAllClassesSettings,
						new SingleComparisonDocumentation(UIElements.SettingsFromLeftListHasNoCoresnpondInRightList(
								UIElements.ClassSettingsDescriptor, leftClassSettings.getCls().name())));
			}
			comparisonResults.add(comparisonResult);
		}
		if (!handeledRightClassesSettings.containsAll(rightAllClassesSettings)) {
			// not all ClassSettings in rightAllClassesSettings were used for comparison
			return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftAllClassesSettings,
					rightAllClassesSettings, new SingleComparisonDocumentation(
							UIElements.SettingsRightListNotFullyUsed(UIElements.ClassSettingsDescriptor)));
		}
		if (comparisonResults.size() == 0) {
			return new ComparisonResult(ComparisonResults.EQUAL, leftAllClassesSettings, rightAllClassesSettings,
					new SingleComparisonDocumentation(
							UIElements.ListOfSettingsIsEmpty(UIElements.ClassSettingsDescriptor)));
		}
		return resultMerger.mergeComparisonResults(comparisonResults, leftAllClassesSettings, rightAllClassesSettings);
	}

	/**
	 * Compares two configuration aspects for classes.
	 * 
	 * Always returns merged comparison result. This includes:
	 * <ul>
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} when one of the minimum or
	 * maximum object numbers is less than $0$ or the minimum is greater than the
	 * maximum for a configuration.
	 * <li>{@link ComparisonResults#EQUAL} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and both minimum and minimum as
	 * well as maximum and maximum object numbers are equal.
	 * <li>{@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum object number of the
	 * first configuration is less than minimum object number of the second
	 * configuration and maximum object number of the first configuration is greater
	 * than maximum object number of the second configuration.
	 * <li>{@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum object number of the
	 * first configuration is greater than minimum object number of the second
	 * configuration and maximum object number of the first configuration is less
	 * than maximum object number of the second configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and maximum object number of the
	 * first configuration is less than minimum object number of the second
	 * configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and maximum object number of the
	 * second configuration is less than minimum object number of the first
	 * configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum object number of the
	 * first configuration is less than minimum object number of the second
	 * configuration and maximum object number of the first configuration is less
	 * than maximum object number of the second configuration and is also greater
	 * than or equal the minimum object number of the second configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum object number of the
	 * second configuration is less than minimum object number of the first
	 * configuration and maximum object number of the second configuration is less
	 * than maximum object number of the first configuration and is also greater
	 * than or equal the minimum object number of the second configuration.
	 * <li>{@link ComparisonResults#UNEQUAL} elsewise.
	 * </ul>
	 * 
	 * @param leftClassSettings
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param rightClassSettings
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareClassSettings(final ClassSettings leftClassSettings,
			final ClassSettings rightClassSettings) {
		if (leftClassSettings == null || rightClassSettings == null
				|| leftClassSettings.getCls() != rightClassSettings.getCls()) {
			throw new IllegalArgumentException();
		}
		final String className = leftClassSettings.getCls().name();
		final Set<ComparisonResult> comparisonResults = new HashSet<>();

		comparisonResults.add(new SettingsConfigurationIntervalComparator().compareMinMax(leftClassSettings,
				rightClassSettings, className, PropertyEntry.objMin, PropertyEntry.objMax,
				leftClassSettings.getLowerBound(), leftClassSettings.getUpperBound(),
				rightClassSettings.getLowerBound(), rightClassSettings.getUpperBound(), false, false, true));
		comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftClassSettings, rightClassSettings,
				new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(className))));

		final List<IAttribute> handeledRightAttributes = new ArrayList<>();
		for (Entry<IAttribute, AttributeSettings> leftAttributeSettingsForAttribute : leftClassSettings
				.getAttributeSettings().entrySet()) {
			if (rightClassSettings.getAttributeSettings().containsKey(leftAttributeSettingsForAttribute.getKey())) {
				handeledRightAttributes.add(leftAttributeSettingsForAttribute.getKey());
				comparisonResults.add(compareAttributeSettings(className, leftAttributeSettingsForAttribute.getValue(),
						rightClassSettings.getAttributeSettings().get(leftAttributeSettingsForAttribute.getKey())));
			} else {
				return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftClassSettings, rightClassSettings,
						new SingleComparisonDocumentation(UIElements.SettingsFromLeftListHasNoCoresnpondInRightList(
								UIElements.AttributeSettingsDescriptor(className),
								leftAttributeSettingsForAttribute.getKey().name())));
			}
		}
		if (!handeledRightAttributes.containsAll(rightClassSettings.getAttributeSettings().keySet())) {
			// not all AttributeSettings in rightClassSettings were used for comparison
			return new ComparisonResult(ComparisonResults.NOT_COMPARABLE, leftClassSettings, rightClassSettings,
					new SingleComparisonDocumentation(UIElements
							.SettingsRightListNotFullyUsed(UIElements.AttributeSettingsDescriptor(className))));
		}
		return resultMerger.mergeComparisonResults(comparisonResults, leftClassSettings, rightClassSettings);
	}

	/**
	 * Compares two configuration aspects for attributes.
	 * 
	 * Always returns merged comparison result. This includes
	 * {@link ComparisonResults#IGNORED} for the whole aspects.
	 * 
	 * @param className
	 * @param leftAttributeSettings
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param rightAttributeSettings
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareAttributeSettings(final String className,
			final AttributeSettings leftAttributeSettings, final AttributeSettings rightAttributeSettings) {
		if (leftAttributeSettings == null || rightAttributeSettings == null
				|| leftAttributeSettings.getAttribute() != rightAttributeSettings.getAttribute()) {
			throw new IllegalArgumentException();
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();

		comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftAttributeSettings,
				rightAttributeSettings, new SingleComparisonDocumentation(UIElements
						.ConfigAttributeIgnored(className + "_" + leftAttributeSettings.getAttribute().name()))));

		return resultMerger.mergeComparisonResults(comparisonResults, leftAttributeSettings, rightAttributeSettings);
	}

	/**
	 * Compares two configuration aspects for options.
	 * 
	 * Always returns merged comparison result. This includes
	 * {@link ComparisonResults#IGNORED} for aggregationcyclefreeness and
	 * forbiddensharing .
	 * 
	 * @param leftOptionSettings
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param rightOptionSettings
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareOptionSettings(final OptionSettings leftOptionSettings,
			final OptionSettings rightOptionSettings) {
		if (leftOptionSettings == null || rightOptionSettings == null) {
			throw new IllegalArgumentException();
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();
		comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftOptionSettings, rightOptionSettings,
				new SingleComparisonDocumentation(
						UIElements.ConfigAttributeIgnored(PropertyEntry.aggregationcyclefreeness))));
		comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftOptionSettings, rightOptionSettings,
				new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(PropertyEntry.forbiddensharing))));

		return resultMerger.mergeComparisonResults(comparisonResults, leftOptionSettings, rightOptionSettings);
	}

	/**
	 * Compares two configuration aspects for type real.
	 * 
	 * Always returns merged comparison result. This includes:
	 * <ul>
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} when only one of the real
	 * number configurations is enabled or the minimum real number is greater than
	 * the maximum real number for a configuration or the one of the step values is
	 * less than or equal $0$.
	 * <li>{@link ComparisonResults#UNEQUAL} when the step values are not equal or
	 * the comparison has no other result.
	 * <li>{@link ComparisonResults#EQUAL} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and step values are equal and
	 * both of the real number configurations are enabled and both minimum and
	 * minimum as well as maximum and maximum number of real number.
	 * <li>{@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and step values are equal and
	 * minimum real number of the first configuration is less than minimum real
	 * number of the second configuration and maximum real number of the first
	 * configuration is greater than maximum real number of the second
	 * configuration.
	 * <li>{@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and not step values are equal
	 * and minimum real number of the first configuration is greater than minimum
	 * real number of the second configuration and maximum real number of the first
	 * configuration is less than maximum real number of the second configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and not step values are equal
	 * and maximum real number of the first configuration is less than minimum real
	 * number of the second configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and not step values are equal
	 * and maximum real number of the second configuration is less than minimum real
	 * number of the first configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and not step values are equal
	 * and minimum real number of the first configuration is less than minimum real
	 * number of the second configuration and maximum real number of the first
	 * configuration is less than maximum real number of the second configuration
	 * and is also greater than or equal the minimum real number of the second
	 * configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and not step values are equal
	 * and minimum real number of the first configuration is less than minimum real
	 * number of the second configuration and maximum real number of the first
	 * configuration is less than maximum real number of the second configuration
	 * and is also greater than or equal the minimum real number of the second
	 * configuration.
	 * </ul>
	 * 
	 * @param leftRealTypeSettings
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param rightRealTypeSettings
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareRealSettings(final RealSettings leftRealTypeSettings,
			final RealSettings rightRealTypeSettings) {
		if (leftRealTypeSettings == null || rightRealTypeSettings == null) {
			throw new IllegalArgumentException();
		}
		if (leftRealTypeSettings.isEnabled() != rightRealTypeSettings.isEnabled()) {
			return new ComparisonResult(ComparisonResults.INVALID_COMPARISON, leftRealTypeSettings,
					rightRealTypeSettings,
					new SingleComparisonDocumentation(UIElements.LeftEnabledUnequalsRightEnabled(TypeConstants.REAL,
							leftRealTypeSettings.isEnabled(), rightRealTypeSettings.isEnabled())));
		}
		if (!leftRealTypeSettings.isEnabled() && !rightRealTypeSettings.isEnabled()) {
			return new ComparisonResult(ComparisonResults.EQUAL, leftRealTypeSettings, rightRealTypeSettings,
					new SingleComparisonDocumentation(UIElements.EnabledBothFalse(TypeConstants.REAL)));
		}

		final Set<ComparisonResult> comparisonResults = new HashSet<>();

		final double leftStep = leftRealTypeSettings.getStep();
		final double rightStep = rightRealTypeSettings.getStep();
		final String stepDetails = UIElements.ComparisonDetails(TypeConstants.REAL + PropertyEntry.realStep,
				String.valueOf(leftStep), String.valueOf(rightStep));

		if (leftStep != rightStep) {
			comparisonResults.add(new ComparisonResult(ComparisonResults.UNEQUAL, leftRealTypeSettings,
					rightRealTypeSettings, new SingleComparisonDocumentation(
							UIElements.UNEQUAL_Details(TypeConstants.REAL + PropertyEntry.realStep, stepDetails))));
			comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftRealTypeSettings,
					rightRealTypeSettings, new SingleComparisonDocumentation(
							UIElements.ConfigAttributeIgnored(TypeConstants.REAL + PropertyEntry.realValuesMin))));
			comparisonResults.add(new ComparisonResult(ComparisonResults.IGNORED, leftRealTypeSettings,
					rightRealTypeSettings, new SingleComparisonDocumentation(
							UIElements.ConfigAttributeIgnored(TypeConstants.REAL + PropertyEntry.realValuesMax))));
		} else {
			comparisonResults.add(new ComparisonResult(ComparisonResults.EQUAL, leftRealTypeSettings,
					rightRealTypeSettings, new SingleComparisonDocumentation(
							UIElements.EQUAL_Details(TypeConstants.REAL + PropertyEntry.realStep, stepDetails))));
			final double leftMin = leftRealTypeSettings.getMinimum();
			final double leftMax = leftRealTypeSettings.getMaximum();
			final double rightMin = rightRealTypeSettings.getMinimum();
			final double rightMax = rightRealTypeSettings.getMaximum();
			final String minMaxDetails = UIElements.ComparisonDetails(TypeConstants.REAL + PropertyEntry.realValuesMin,
					String.valueOf(leftMin), String.valueOf(rightMin), TypeConstants.REAL + PropertyEntry.realValuesMax,
					String.valueOf(leftMax), String.valueOf(rightMax));

			if (leftMin > leftMax) {
				comparisonResults.add(new ComparisonResult(ComparisonResults.INVALID_COMPARISON, leftRealTypeSettings,
						rightRealTypeSettings,
						new SingleComparisonDocumentation(TypeConstants.REAL + PropertyEntry.realValuesMax
								+ " of left is less than " + TypeConstants.REAL + PropertyEntry.realValuesMin + ".")));
			} else if (rightMin > rightMax) {
				comparisonResults.add(new ComparisonResult(ComparisonResults.INVALID_COMPARISON, leftRealTypeSettings,
						rightRealTypeSettings,
						new SingleComparisonDocumentation(TypeConstants.REAL + PropertyEntry.realValuesMax
								+ " of right is less than " + TypeConstants.REAL + PropertyEntry.realValuesMin + ".")));
			} else if (leftMin == rightMin && leftMax == rightMax) {
				comparisonResults
						.add(new ComparisonResult(ComparisonResults.EQUAL, leftRealTypeSettings, rightRealTypeSettings,
								new SingleComparisonDocumentation(
										UIElements.EQUAL_Details(TypeConstants.REAL + PropertyEntry.realValuesMin,
												TypeConstants.REAL + PropertyEntry.realValuesMax, minMaxDetails))));
			} else if (leftMin <= rightMin && leftMax >= rightMax) {
				comparisonResults.add(new ComparisonResult(ComparisonResults.LEFT_BROAD_AND_RIGHT_NARROW,
						leftRealTypeSettings, rightRealTypeSettings,
						new SingleComparisonDocumentation(UIElements.LEFT_BROAD_AND_RIGHT_NARROW_Details(
								TypeConstants.REAL + PropertyEntry.realValuesMin,
								TypeConstants.REAL + PropertyEntry.realValuesMax, minMaxDetails))));
			} else if (leftMin >= rightMin && leftMax <= rightMax) {
				comparisonResults.add(new ComparisonResult(ComparisonResults.LEFT_NARROW_AND_RIGHT_BROAD,
						leftRealTypeSettings, rightRealTypeSettings,
						new SingleComparisonDocumentation(UIElements.LEFT_NARROW_AND_RIGHT_BROAD_Details(
								TypeConstants.REAL + PropertyEntry.realValuesMin,
								TypeConstants.REAL + PropertyEntry.realValuesMax, minMaxDetails))));
			} else if (leftMax < rightMin) {
				comparisonResults.add(new ComparisonResult(ComparisonResults.LEFT_IS_LESS_DISJOINT,
						leftRealTypeSettings, rightRealTypeSettings,
						new SingleComparisonDocumentation(UIElements.LEFT_IS_LESS_DISJOINT_Details(
								TypeConstants.REAL + PropertyEntry.realValuesMin,
								TypeConstants.REAL + PropertyEntry.realValuesMax, minMaxDetails))));
			} else if (rightMax < leftMin) {
				comparisonResults.add(new ComparisonResult(ComparisonResults.RIGHT_IS_LESS_DISJOINT,
						leftRealTypeSettings, rightRealTypeSettings,
						new SingleComparisonDocumentation(UIElements.RIGHT_IS_LESS_DISJOINT_Details(
								TypeConstants.REAL + PropertyEntry.realValuesMin,
								TypeConstants.REAL + PropertyEntry.realValuesMax, minMaxDetails))));
			} else if (leftMin < rightMin && leftMax >= rightMin && leftMax <= rightMax) {
				comparisonResults
						.add(new ComparisonResult(ComparisonResults.LEFT_IS_PRELIMINARY_OVERLAPPING,
								leftRealTypeSettings, rightRealTypeSettings,
								new SingleComparisonDocumentation(UIElements.LEFT_IS_OVERLAPPING_Details(
										TypeConstants.REAL + PropertyEntry.realValuesMin,
										TypeConstants.REAL + PropertyEntry.realValuesMax, minMaxDetails))));
			} else if (rightMin < leftMin && rightMax >= leftMin && rightMax <= leftMax) {
				comparisonResults.add(new ComparisonResult(ComparisonResults.RIGHT_IS_PRELIMINARY_OVERLAPPING,
						leftRealTypeSettings, rightRealTypeSettings,
						new SingleComparisonDocumentation(UIElements.RIGHT_IS_OVERLAPPING_Details(
								TypeConstants.REAL + PropertyEntry.realValuesMin,
								TypeConstants.REAL + PropertyEntry.realValuesMax, minMaxDetails))));
			} else {
				comparisonResults.add(
						new ComparisonResult(ComparisonResults.UNEQUAL, leftRealTypeSettings, rightRealTypeSettings,
								new SingleComparisonDocumentation(
										UIElements.UNEQUAL_Details(TypeConstants.REAL + PropertyEntry.realValuesMin,
												TypeConstants.REAL + PropertyEntry.realValuesMax, minMaxDetails))));
			}
		}
		comparisonResults
				.add(new ComparisonResult(ComparisonResults.IGNORED, leftRealTypeSettings, rightRealTypeSettings,
						new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(TypeConstants.REAL))));

		return resultMerger.mergeComparisonResults(comparisonResults, leftRealTypeSettings, rightRealTypeSettings);
	}

	/**
	 * Compares two configuration aspects for type string.
	 * 
	 * Always returns merged comparison result. This includes:
	 * <ul>
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} when only one of the
	 * different string values configurations is enabled or one of the minimum or
	 * maximum number of different string values is less than $0$ or the minimum is
	 * greater than the maximum for a configuration.
	 * <li>{@link ComparisonResults#EQUAL} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and both of the different string
	 * values configurations are enabled and both minimum and minimum as well as
	 * maximum and maximum number of different string values are equal.
	 * <li>{@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum number of different
	 * string values of the first configuration is less than minimum number of
	 * different string values of the second configuration and maximum number of
	 * different string values of the first configuration is greater than maximum
	 * number of different string values of the second configuration.
	 * <li>{@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum number of different
	 * string values of the first configuration is greater than minimum number of
	 * different string values of the second configuration and maximum number of
	 * different string values of the first configuration is less than maximum
	 * number of different string values of the second configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and maximum number of different
	 * string values of the first configuration is less than minimum number of
	 * different string values of the second configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and maximum number of different
	 * string values of the second configuration is less than minimum number of
	 * different string values of the first configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum number of different
	 * string values of the first configuration is less than minimum number of
	 * different string values of the second configuration and maximum number of
	 * different string values of the first configuration is less than maximum
	 * number of different string values of the second configuration and is also
	 * greater than or equal the minimum number of different string values of the
	 * second configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum number of different
	 * string values of the second configuration is less than minimum number of
	 * different string values of the first configuration and maximum number of
	 * different string values of the second configuration is less than maximum
	 * number of different string values of the first configuration and is also
	 * greater than or equal the minimum number of different string values of the
	 * second configuration.
	 * <li>{@link ComparisonResults#UNEQUAL} elsewise.
	 * </ul>
	 * 
	 * @param leftStringTypeSettings
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param rightStringTypeSettings
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareStringSettings(final StringSettings leftStringTypeSettings,
			final StringSettings rightStringTypeSettings) {
		if (leftStringTypeSettings == null || rightStringTypeSettings == null) {
			throw new IllegalArgumentException();
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();

		comparisonResults.add(new SettingsConfigurationIntervalComparator().compareMinMax(leftStringTypeSettings,
				rightStringTypeSettings, TypeConstants.STRING, PropertyEntry.stringValuesMin,
				PropertyEntry.stringValuesMax, leftStringTypeSettings.isEnabled(), rightStringTypeSettings.isEnabled(),
				leftStringTypeSettings.getLowerBound(), leftStringTypeSettings.getUpperBound(),
				rightStringTypeSettings.getLowerBound(), rightStringTypeSettings.getUpperBound(), false, false, false));
		comparisonResults
				.add(new ComparisonResult(ComparisonResults.IGNORED, leftStringTypeSettings, rightStringTypeSettings,
						new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(TypeConstants.STRING))));

		return resultMerger.mergeComparisonResults(comparisonResults, leftStringTypeSettings, rightStringTypeSettings);
	}

	/**
	 * Compares two configuration aspects for type integer.
	 * 
	 * Always returns merged comparison result. This includes:
	 * <ul>
	 * <li>{@link ComparisonResults#INVALID_COMPARISON} when only one of the integer
	 * number configurations is enabled or the minimum integer number is greater
	 * than the maximum integer number for a configuration.
	 * <li>{@link ComparisonResults#EQUAL} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and both of the integer number
	 * configurations are enabled and both minimum and minimum as well as maximum
	 * and maximum integer numbers are equal.
	 * <li>{@link ComparisonResults#LEFT_BROAD_AND_RIGHT_NARROW} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum integer number of
	 * the first configuration is less than minimum integer number of the second
	 * configuration and maximum integer number of the first configuration is
	 * greater than maximum integer number of the second configuration.
	 * <li>{@link ComparisonResults#LEFT_NARROW_AND_RIGHT_BROAD} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum integer number of
	 * the first configuration is greater than minimum integer number of the second
	 * configuration and maximum integer number of the first configuration is less
	 * than maximum integer number of the second configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and maximum integer number of
	 * the first configuration is less than minimum integer number of the second
	 * configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_LESS_DISJOINT} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and maximum integer number of
	 * the second configuration is less than minimum integer number of the first
	 * configuration.
	 * <li>{@link ComparisonResults#LEFT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum integer number of
	 * the first configuration is less than minimum integer number of the second
	 * configuration and maximum integer number of the first configuration is less
	 * than maximum integer number of the second configuration and is also greater
	 * than or equal the minimum integer number of the second configuration.
	 * <li>{@link ComparisonResults#RIGHT_IS_PRELIMINARY_OVERLAPPING} when not
	 * {@link ComparisonResults#INVALID_COMPARISON} and minimum integer number of
	 * the second configuration is less than minimum integer number of the first
	 * configuration and maximum integer number of the second configuration is less
	 * than maximum integer number of the first configuration and is also greater
	 * than or equal the minimum integer number of the second configuration.
	 * <li>{@link ComparisonResults#UNEQUAL} elsewise.
	 * </ul>
	 * 
	 * @param leftIntegerTypeSettings
	 *            The configuration aspect used as first operand for binary
	 *            comparison.
	 * @param rightIntegerTypeSettings
	 *            The configuration aspect used as second operand for binary
	 *            comparison.
	 * @return The comparison result.
	 */
	private ComparisonResult compareIntegerSettings(final IntegerSettings leftIntegerTypeSettings,
			final IntegerSettings rightIntegerTypeSettings) {
		if (leftIntegerTypeSettings == null || rightIntegerTypeSettings == null) {
			throw new IllegalArgumentException();
		}
		final Set<ComparisonResult> comparisonResults = new HashSet<>();

		comparisonResults.add(new SettingsConfigurationIntervalComparator().compareMinMax(leftIntegerTypeSettings,
				rightIntegerTypeSettings, TypeConstants.INTEGER, PropertyEntry.integerValuesMin,
				PropertyEntry.integerValuesMax, leftIntegerTypeSettings.isEnabled(),
				rightIntegerTypeSettings.isEnabled(), leftIntegerTypeSettings.getMinimum(),
				leftIntegerTypeSettings.getMaximum(), rightIntegerTypeSettings.getMinimum(),
				rightIntegerTypeSettings.getMaximum(), true, true, false));
		comparisonResults
				.add(new ComparisonResult(ComparisonResults.IGNORED, leftIntegerTypeSettings, rightIntegerTypeSettings,
						new SingleComparisonDocumentation(UIElements.ConfigAttributeIgnored(TypeConstants.INTEGER))));

		return resultMerger.mergeComparisonResults(comparisonResults, leftIntegerTypeSettings,
				rightIntegerTypeSettings);
	}

}
