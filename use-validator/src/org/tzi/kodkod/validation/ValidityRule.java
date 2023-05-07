package org.tzi.kodkod.validation;

/**
 * The set of validity rules. The applicability of rules may depend on other
 * rules to be not violated. These dependencies are expressed here.
 * 
 * @author Jan Prien
 *
 */
public enum ValidityRule {

	/**
	 * This is violated when integer type settings are disabled but should be
	 * enabled. They should be enabled if there is at least one class that is
	 * configured to potentially have instances and that has at least one attribute
	 * of type integer that is configured to potentially be defined.
	 */
	INTEGER_SETTINGS_NESSESSARY(ValidityRuleLevel.INCOMPLETE, null),

	/**
	 * This is violated when integer type settings are enabled but should be
	 * disabled. They should be disabled if there is not at least one class that is
	 * configured to potentially have instances and that has at least one attribute
	 * of type integer that is configured to potentially be defined.
	 */
	INTEGER_SETTINGS_UNNESSESSARY(ValidityRuleLevel.SOUPERFLUOUS, null),

	/**
	 * This is violated if integer type settings are enabled and the minimum integer
	 * number is not less than or equal the maximum integer number.
	 */
	INTEGER_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX(ValidityRuleLevel.INTRA_CONTRADICTION,
			new ValidityRule[] { INTEGER_SETTINGS_NESSESSARY, INTEGER_SETTINGS_UNNESSESSARY }),

	/**
	 * This is violated if integer type settings are enabled and the not all
	 * preferred integer values are contained in the domain defined by minimum and
	 * maximum integer number.
	 */
	INTEGER_SETTINGS_PREFERRED_VALUES_IN_MIN_MAX(ValidityRuleLevel.IMPERFECT_PREFERATION,
			new ValidityRule[] { INTEGER_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX }),

	/**
	 * Is only not violated if implicitly all rules regarding configuration aspects
	 * for integer type are not violated.
	 */
	INTEGER_SETTINGS_VALID(ValidityRuleLevel.INTERNAL,
			new ValidityRule[] { INTEGER_SETTINGS_PREFERRED_VALUES_IN_MIN_MAX }),

	/**
	 * This is violated when string type settings are disabled but should be
	 * enabled. They should be enabled if there is at least one class that is
	 * configured to potentially have instances and that has at least one attribute
	 * of type string that is configured to potentially be defined.
	 */
	STRING_SETTINGS_NESSESSARY(ValidityRuleLevel.INCOMPLETE, null),

	/**
	 * This is violated when string type settings are enabled but should be
	 * disabled. They should be disabled if there is not at least one class that is
	 * configured to potentially have instances and that has at least one attribute
	 * of type sting that is configured to potentially be defined.
	 */
	STRING_SETTINGS_UNNESSESSARY(ValidityRuleLevel.SOUPERFLUOUS, null),

	/**
	 * This is violated if string type settings are enabled and the minimum and
	 * maximum number of string values is not less than or equal 0.
	 */
	STRING_SETTINGS_MIN_MAX_GREATER_THAN_OR_EQUAL_ZERO(ValidityRuleLevel.INVALID,
			new ValidityRule[] { STRING_SETTINGS_NESSESSARY, STRING_SETTINGS_UNNESSESSARY }),

	/**
	 * This is violated if string type settings are enabled and the minimum number
	 * of string values is not less than or equal the maximum number of string
	 * values.
	 */
	STRING_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX(ValidityRuleLevel.INTRA_CONTRADICTION,
			new ValidityRule[] { STRING_SETTINGS_MIN_MAX_GREATER_THAN_OR_EQUAL_ZERO }),

	/**
	 * This is violated if string type settings are enabled and the preferred values
	 * contain more elements than the maximum number of string values specifies.
	 */
	STRING_SETTINGS_PREFERRED_VALUES_CARDINALITY_LESS_THAN_OR_EQUAL_MAX(ValidityRuleLevel.SOUPERFLUOUS,
			new ValidityRule[] { STRING_SETTINGS_MIN_MAX_GREATER_THAN_OR_EQUAL_ZERO }),

	/**
	 * Is only not violated if implicitly all rules regarding configuration aspects
	 * for string type are not violated.
	 */
	STRING_SETTINGS_VALID(ValidityRuleLevel.INTERNAL, new ValidityRule[] { STRING_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX,
			STRING_SETTINGS_PREFERRED_VALUES_CARDINALITY_LESS_THAN_OR_EQUAL_MAX }),

	/**
	 * This is violated when real type settings are disabled but should be enabled.
	 * They should be enabled if there is at least one class that is configured to
	 * potentially have instances and that has at least one attribute of type real
	 * that is configured to potentially be defined.
	 */
	REAL_SETTINGS_NESSESSARY(ValidityRuleLevel.INCOMPLETE, null),

	/**
	 * This is violated when real type settings are enabled but should be disabled.
	 * They should be disabled if there is not at least one class that is configured
	 * to potentially have instances and that has at least one attribute of type
	 * real that is configured to potentially be defined.
	 */
	REAL_SETTINGS_UNNESSESSARY(ValidityRuleLevel.SOUPERFLUOUS, null),

	/**
	 * This is violated if real type settings are enabled and the minimum real
	 * number is not less than or equal the maximum real number.
	 */
	REAL_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX(ValidityRuleLevel.INTRA_CONTRADICTION,
			new ValidityRule[] { REAL_SETTINGS_NESSESSARY, REAL_SETTINGS_UNNESSESSARY }),

	/**
	 * This is violated if real type settings are enabled and the real step value is
	 * not less than or equal the difference of maximum and minimum real number.
	 */
	REAL_SETTINGS_STEP_GREATER_THAN_ZERO_AND_LESS_THAN_OR_EQUAL_MAX_MINUS_MIN(ValidityRuleLevel.INVALID,
			new ValidityRule[] { REAL_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX }),

	/**
	 * This is violated if real type settings are enabled and the quotient of
	 * difference of maximum and minimum real and the real step value is not 0. This
	 * means the maximum real number must be reachable from the minimum real number
	 * in steps of the real step value.
	 */
	REAL_SETTINGS_MAX_REACHABLE_IN_STEPS_FROM_MIN(ValidityRuleLevel.SOUPERFLUOUS,
			new ValidityRule[] { REAL_SETTINGS_STEP_GREATER_THAN_ZERO_AND_LESS_THAN_OR_EQUAL_MAX_MINUS_MIN }),

	/**
	 * This is violated if real type settings are enabled and not all preferred real
	 * values are contained in the domain defined by minimum and maximum real
	 * numbers and the real step value.
	 */
	REAL_SETTINGS_PREFERRED_VALUES_IN_MIN_MAX_STEP(ValidityRuleLevel.IMPERFECT_PREFERATION,
			new ValidityRule[] { REAL_SETTINGS_MAX_REACHABLE_IN_STEPS_FROM_MIN }),

	/**
	 * Is only not violated if implicitly all rules regarding configuration aspects
	 * for real type are not violated.
	 */
	REAL_SETTINGS_VALID(ValidityRuleLevel.INTERNAL,
			new ValidityRule[] { REAL_SETTINGS_PREFERRED_VALUES_IN_MIN_MAX_STEP }),

	/**
	 * Is only not violated if implicitly all rules regarding all configuration
	 * aspects for types are not violated.
	 */
	TYPE_SETTINGS_VALID(ValidityRuleLevel.INTERNAL,
			new ValidityRule[] { INTEGER_SETTINGS_VALID, STRING_SETTINGS_VALID, REAL_SETTINGS_VALID }),

	/**
	 * This is violated if for a class the minimum and maximum number of objects is
	 * not greater than or equal 0.
	 */
	CLASS_SETTINGS_MIN_MAX_GREATER_THAN_OR_EQUAL_ZERO(ValidityRuleLevel.INVALID, null),

	/**
	 * This is violated if for a class the minimum number of objects is not less
	 * than or equal the maximum number of objects.
	 */
	CLASS_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX(ValidityRuleLevel.INTRA_CONTRADICTION,
			new ValidityRule[] { CLASS_SETTINGS_MIN_MAX_GREATER_THAN_OR_EQUAL_ZERO }),

	/**
	 * This is violated if for at least one class the preferred values contain more
	 * elements than the maximum number of objects specifies.
	 */
	CLASS_SETTINGS_PREFERRED_VALUES_CARDINALITY_LESS_THAN_OR_EQUALS_MAX(ValidityRuleLevel.IMPERFECT_PREFERATION,
			new ValidityRule[] { CLASS_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX }),

	/**
	 * This is violated if for at least one abstract class the minimum number of
	 * objects is not equal to the sum of the minimum numbers of objects of directly
	 * derived classes or the maximum number of objects is not equal to the sum of
	 * the maximum numbers of objects of directly derived classes.
	 */
	CLASS_SETTINGS_ABSTRACT_CLASS_BOUNDS_EQUALS_SUM_OF_BOUNDS_OF_DERIVED_CLASSES(ValidityRuleLevel.INVALID,
			new ValidityRule[] { CLASS_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX }),

	/**
	 * This is violated if for at least one association the maximum number of links
	 * is not greater than or equal to -1.
	 */
	ASSOCIATION_SETTINGS_MAX_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO(ValidityRuleLevel.INVALID, null),

	/**
	 * This is violated if for at least one association the minimum number of links
	 * is not greater than or equal to 0.
	 */
	ASSOCIATION_SETTINGS_MIN_GREATER_THAN_OR_EQUAL_ZERO(ValidityRuleLevel.INVALID, null),

	/**
	 * This is violated if for at least one association the maximum number of links
	 * is not -1 and not greater than or equal to the minimum number of links.
	 */
	ASSOCIATION_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX_WHEN_MAX_NOT_STAR(ValidityRuleLevel.INTRA_CONTRADICTION,

			new ValidityRule[] { ASSOCIATION_SETTINGS_MAX_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO,
					ASSOCIATION_SETTINGS_MIN_GREATER_THAN_OR_EQUAL_ZERO }),

	/**
	 * This is violated if not all object names contained in the preferred links are
	 * contained in the preferred object names.
	 */
	ASSOCIATION_SETTINGS_REQUIRED_LINKS_VALID_IDENTITIES(ValidityRuleLevel.INTRA_CONTRADICTION,

			new ValidityRule[] { ASSOCIATION_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX_WHEN_MAX_NOT_STAR }),

	/**
	 * This is violated if for at least one association the number of required links
	 * is not equal to the minimum and maximum number of links.
	 */
	ASSOCIATION_SETTINGS_REQUIRED_LINKS_CARDINALITY_LESS_THAN_OR_EQUAL_MIN_LINKS_AND_MAX_LINKS(
			ValidityRuleLevel.INTRA_CONTRADICTION,

			new ValidityRule[] { ASSOCIATION_SETTINGS_REQUIRED_LINKS_VALID_IDENTITIES }),

	/**
	 * Is only not violated if implicitly all rules regarding configuration aspects
	 * for associations are not violated.
	 */
	ASSOCIATION_SETTINGS_VALID(ValidityRuleLevel.INTERNAL, new ValidityRule[] {
			ASSOCIATION_SETTINGS_REQUIRED_LINKS_CARDINALITY_LESS_THAN_OR_EQUAL_MIN_LINKS_AND_MAX_LINKS }),

	/**
	 * Is only not violated if implicitly all rules regarding configuration aspects
	 * for classes are not violated.
	 */
	CLASS_SETTINGS_VALID(ValidityRuleLevel.INTERNAL,
			new ValidityRule[] { CLASS_SETTINGS_PREFERRED_VALUES_CARDINALITY_LESS_THAN_OR_EQUALS_MAX,
					CLASS_SETTINGS_ABSTRACT_CLASS_BOUNDS_EQUALS_SUM_OF_BOUNDS_OF_DERIVED_CLASSES }),

	/**
	 * This is violated if for at least one attribute the minimum number of defined
	 * attributes is not greater than or equal to $-1$.
	 */
	ATTRIBUTE_SETTINGS_MIN_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO(ValidityRuleLevel.INVALID, null),

	/**
	 * This is violated if for at least one attribute the maximum number of defined
	 * attributes is not greater than or equal to -1.
	 */
	ATTRIBUTE_SETTINGS_MAX_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO(ValidityRuleLevel.INVALID, null),

	/**
	 * This is violated if for at least one attribute the minimum number of defined
	 * attributes is not less than or equal to the maximum number of defined
	 * attributes.
	 */
	ATTRIBUTE_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX(ValidityRuleLevel.INTRA_CONTRADICTION,
			new ValidityRule[] { ATTRIBUTE_SETTINGS_MIN_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO,
					ATTRIBUTE_SETTINGS_MAX_STAR_OR_GREATER_THAN_OR_EQUAL_ZERO }),

	/**
	 * This is violated if for at least one attribute the maximum is not -1 and not
	 * less than or equal the number of objects of the owning class.
	 */
	ATTRIBUTE_SETTINGS_MAX_STAR_OR_LESS_THAN_OR_EQUAL_CLASS_MAX(ValidityRuleLevel.INTRA_CONTRADICTION,
			new ValidityRule[] { CLASS_SETTINGS_VALID, ATTRIBUTE_SETTINGS_MIN_LESS_THAN_OR_EQUAL_MAX }),

	/**
	 * This is violated if for at least one attribute of collection type the minimum
	 * number of contained elements in defined attributes is not greater than or
	 * equal to 0.
	 */
	ATTRIBUTE_SETTINGS_COLLECTION_MIN_SIZE_GREATER_THAN_OR_EQUAL_ZERO(ValidityRuleLevel.INVALID, null),

	/**
	 * This is violated if for at least one attribute of collection type the maximum
	 * number of contained elements in defined attributes is not -1 and not greater
	 * than or equal to the minimum number of contained elements in defined
	 * attributes.
	 */
	ATTRIBUTE_SETTINGS_COLLECTION_MAX_SIZE_STAR_OR_GREATER_THAN_OR_EQUAL_MIN_SIZE(ValidityRuleLevel.INVALID,
			new ValidityRule[] { ATTRIBUTE_SETTINGS_COLLECTION_MIN_SIZE_GREATER_THAN_OR_EQUAL_ZERO }),

	/**
	 * Is only not violated if implicitly all rules regarding configuration aspects
	 * for classes and their attributes are not violated.
	 */
	ATTRIBUTE_SETTINGS_VALID(ValidityRuleLevel.INTERNAL,
			new ValidityRule[] { ATTRIBUTE_SETTINGS_MAX_STAR_OR_LESS_THAN_OR_EQUAL_CLASS_MAX,
					ATTRIBUTE_SETTINGS_COLLECTION_MAX_SIZE_STAR_OR_GREATER_THAN_OR_EQUAL_MIN_SIZE }),

	/**
	 * This is violated if for at one invariant is configured to be negated while it
	 * is not configured to be activated.
	 */
	INVARIANT_SETTINGS_ONLY_NEGATE_WHEN_ACTIVE(ValidityRuleLevel.INTRA_CONTRADICTION, null),

	/**
	 * Is only not violated if implicitly all rules regarding configuration aspects
	 * for invariants are not violated.
	 */
	INVARIANT_SETTINGS_VALID(ValidityRuleLevel.INTERNAL,
			new ValidityRule[] { INVARIANT_SETTINGS_ONLY_NEGATE_WHEN_ACTIVE }),

	SETTINGS_UNTIGHTENABLE_BOUNDS(ValidityRuleLevel.SOUPERFLUOUS,
			new ValidityRule[] { CLASS_SETTINGS_VALID, ATTRIBUTE_SETTINGS_VALID, ASSOCIATION_SETTINGS_VALID }),

	/**
	 * Is only not violated if implicitly all rules are not violated.
	 */
	SETTINGS_VALID(ValidityRuleLevel.INTERNAL,
			new ValidityRule[] { TYPE_SETTINGS_VALID, CLASS_SETTINGS_VALID, ATTRIBUTE_SETTINGS_VALID,
					ASSOCIATION_SETTINGS_VALID, INVARIANT_SETTINGS_VALID, SETTINGS_UNTIGHTENABLE_BOUNDS });

	// The following code checks consistency of the structure
	/*
	 * static { Set<ValidityRule> bla =
	 * getConditionallyDueToInviolationsRecursivly(SETTINGS_VALID); if (bla.size()
	 * != ValidityRule.values().length) { throw new UnsupportedOperationException();
	 * } }
	 * 
	 * 
	 * private static Set<ValidityRule>
	 * getConditionallyDueToInviolationsRecursivly(final ValidityRule rule) { final
	 * Set<ValidityRule> conditionallyDueToInviolationsRules = new HashSet<>();
	 * conditionallyDueToInviolationsRules.add(rule); for (ValidityRule
	 * conditionallyDueToInviolationsRule : rule.conditionallyDueToInviolations) {
	 * conditionallyDueToInviolationsRules.add(conditionallyDueToInviolationsRule);
	 * conditionallyDueToInviolationsRules
	 * .addAll(getConditionallyDueToInviolationsRecursivly(
	 * conditionallyDueToInviolationsRule)); } return
	 * conditionallyDueToInviolationsRules; }
	 */

	/**
	 * The level of the rule.
	 */
	final ValidityRuleLevel level;

	/**
	 * The dependency to be only meaningful on these rules not being violated.
	 */
	final ValidityRule[] conditionallyDueToInviolations;

	/**
	 * Constructs an object.
	 * 
	 * @param level
	 *            The level of the rule.
	 * @param conditionallyDueToInviolations
	 *            The dependency to be only meaningful on these rules not being
	 *            violated.
	 */
	private ValidityRule(final ValidityRuleLevel level, final ValidityRule[] conditionallyDueToInviolations) {
		if (level == null) {
			throw new IllegalArgumentException();
		}
		if (conditionallyDueToInviolations != null) {
			for (ValidityRule rule : conditionallyDueToInviolations) {
				if (rule == null) {
					throw new IllegalArgumentException();
				}
			}
		}
		this.level = level;
		this.conditionallyDueToInviolations = conditionallyDueToInviolations != null ? conditionallyDueToInviolations
				: new ValidityRule[0];
	}

	/**
	 * Sorts rules ascending by their ordinal value. If rules have the same ordinal,
	 * they are ordered like in the argument.
	 * 
	 * @param rules
	 *            The list of rules.
	 * @return The ordered list of rules.
	 */
	static ValidityRule[] sort(ValidityRule[] rules) {
		if (rules == null) {
			throw new IllegalArgumentException();
		}
		ValidityRule[] sorted = new ValidityRule[rules.length];
		for (int i = 0; i < sorted.length; i++) {
			ValidityRule highest = null;
			for (int i2 = 0; i2 < rules.length; i2++) {
				ValidityRule potential = rules[i2];
				boolean alreadySorted = false;
				for (int i3 = 0; i3 < sorted.length; i3++) {
					alreadySorted = alreadySorted || sorted[i3] == potential;
				}
				if (!alreadySorted) {
					if (highest == null || highest.ordinal() < potential.ordinal()) {
						highest = potential;
					} else {
					}
				} else {
				}
			}
			sorted[i] = highest;
		}
		return sorted;
	}

}
