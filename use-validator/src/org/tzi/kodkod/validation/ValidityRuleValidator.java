package org.tzi.kodkod.validation;

/**
 * Links the implementations of the validation of validity rule to the rules.
 * 
 * @author Jan Prien
 *
 */
public class ValidityRuleValidator extends AbstractValidityRuleValidator {

	@Override
	protected AbstractValidator getValidator(ValidityRule rule) {
		if (rule == null) {
			throw new IllegalArgumentException();
		}
		switch (rule) {
		case INTEGER_SETTINGS_NESSESSARY:
			return new IntegerSettingsNessessaryValidator();
		case INTEGER_SETTINGS_UNNESSESSARY:
			return new IntegerSettingsUnnessessaryValidator();
		case INTEGER_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX:
			return new IntegerSettingsMinLessThanOrEqualMaxValidator();
		case INTEGER_SETTINGS_PREFERRED_VALUES_IN_MIN_MAX:
			return new IntegerSettingsPreferredValuesInMinMaxValidator();
		case STRING_SETTINGS_NESSESSARY:
			return new StringSettingsNessessaryValidator();
		case STRING_SETTINGS_UNNESSESSARY:
			return new StringSettingsUnnessessaryValidator();
		case STRING_SETTINGS_MIN_MAX_GREATER_THAN_OR_EQUAL_ZERO:
			return new StringSettingsMinMaxGreaterThanOrEqualZeroValidator();
		case STRING_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX:
			return new StringSettingsMinLessThanOrEqualMaxValidator();
		case STRING_SETTINGS_PREFERRED_VALUES_CARDINALITY_LESS_THAN_OR_EQUAL_MAX:
			return new StringSettingsPreferredValuesCardinalityLessThanOrEqualMaxValidator();
		case REAL_SETTINGS_NESSESSARY:
			return new RealSettingsNessessaryValidator();
		case REAL_SETTINGS_UNNESSESSARY:
			return new RealSettingsUnnessessaryValidator();
		case REAL_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX:
			return new RealSettingsMinLessThanOrEqualMaxValidator();
		case REAL_SETTINGS_STEP_GREATER_THAN_ZERO_AND_LESS_THAN_OR_EQUAL_MAX_MINUS_MIN:
			return new RealSettingsStepGreaterThanZeroAndLessThanOrEqualMaxMinusMinValidator();
		case REAL_SETTINGS_MAX_REACHABLE_IN_STEPS_FROM_MIN:
			return new RealSettingsMaxReachableInStepFromMinValidator();
		case REAL_SETTINGS_PREFERRED_VALUES_IN_MIN_MAX_STEP:
			return new RealSettingsPreferredValuesInMinMaxStepValidator();
		case CLASS_SETTINGS_MIN_MAX_GREATER_THAN_OR_EQUAL_ZERO:
			return new ClassSettingsMinMaxGreaterThanOrEqualZeroValidator();
		case CLASS_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX:
			return new ClassSettingsMinLessThanOrEqualMaxValidator();
		case CLASS_SETTINGS_PREFERRED_VALUES_CARDINALITY_LESS_THAN_OR_EQUALS_MAX:
			return new ClassSettingsPreferredValuesCardinalityLessThanOrEqualsMaxValidator();
		case CLASS_SETTINGS_ABSTRACT_CLASS_BOUNDS_EQUALS_SUM_OF_BOUNDS_OF_DERIVED_CLASSES:
			return new ClassSettingsAbstractClassBoundsEqualsSumOfBoundsOfDerivedClassesValidityRuleValidator();
		case ASSOCIATION_SETTINGS_MAX_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO:
			return new AssociationSettingsMaxStarOrGreaterThanOrEqualZeroValidator();
		case ASSOCIATION_SETTINGS_MIN_GREATER_THAN_OR_EQUAL_ZERO:
			return new AssociationSettingsMinGreaterThanOrEqualZeroValidator();
		case ASSOCIATION_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX_WHEN_MAX_NOT_STAR:
			return new AssociationSettingsMinLessThanOrEqualMaxWhenMaxNotStarValidator();
		case ASSOCIATION_SETTINGS_REQUIRED_LINKS_VALID_IDENTITIES:
			return new AssociationSettingsRequiredLinksValidIdentitiesValidator();
		case ASSOCIATION_SETTINGS_REQUIRED_LINKS_CARDINALITY_LESS_THAN_OR_EQUAL_MIN_LINKS_AND_MAX_LINKS:
			return new AssociationSettingsRequiredLinksCardinalityLessThanOrEqualMinLinksAndMaxLinksValidator();
		case ATTRIBUTE_SETTINGS_MIN_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO:
			return new AttributeSettingsMinStarOrGreaterThanOrEqualZeroValidator();
		case ATTRIBUTE_SETTINGS_MAX_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO:
			return new AttributeSettingsMaxStarOrGreaterThanOrEqualZeroValidator();
		case ATTRIBUTE_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX:
			return new AttributeSettingsMinLessThanOrEqualMaxValidator();
		case ATTRIBUTE_SETTINGS_MAX_STAR_OR_LESS_THAN_OR_EQUAL_CLASS_MAX:
			return new AttributeSettingsMaxStarOrLessThanOrEqualClassMaxValidator();
		case ATTRIBUTE_SETTINGS_COLLECTION_MIN_SIZE_GREATER_THAN_OR_EQUAL_ZERO:
			return new AttributeSettingsCollectionMinSizeGreaterThanOrEqualZeroValidator();
		case ATTRIBUTE_SETTINGS_COLLECTION_MAX_SIZE_STAR_OR_GREATER_THAN_OR_EQUAL_MIN_SIZE:
			return new AttributeSettingsCollectionMaxSizeStarOrGreaterThanOrEqualMinSizeValidator();
		case INVARIANT_SETTINGS_ONLY_NEGATE_WHEN_ACTIVE:
			return new InvariantSettingsOnlyNegateWhenActiveValidator();
		case SETTINGS_UNTIGHTENABLE_BOUNDS:
			return new SettingsUntightenableBoundsValidator();
		default:
			throw new UnsupportedOperationException("No validator defined for rule " + rule + ".");
		}

	}

}
