package org.tzi.kodkod.validation;

/**
 * Levels for validity rules.
 * 
 * This provides further classification of validity rules and the significance
 * of corresponding inviolences.
 * 
 * @author Jan Prien
 *
 */
enum ValidityRuleLevel {

	/**
	 * Level that is applied to rules that are only meant to be used internally.
	 */
	INTERNAL,

	/**
	 * Level that is applied to rules whose violations mean that there are missing
	 * configuration aspects. Validity may be achieved by specifying the missing
	 * aspects.
	 */
	INCOMPLETE,

	/**
	 * Level that is applied to rules whose violations mean that there are invalid
	 * configuration aspects. Validity may be achieved by specifying other values
	 * for those aspects.
	 */
	INVALID,

	/**
	 * Level that is applied to rules whose violations mean that there are
	 * contradicting configuration aspects. The contradiction is each in one
	 * configuration aspect. Validity may be achieved by specifying other values for
	 * the aspects.
	 */
	INTER_CONTRADICTION,

	/**
	 * Level that is applied to rules whose violations mean that there are
	 * contradicting configuration aspects. The contradiction is in values of
	 * multiple configuration aspect. Validity may be achieved by specifying other
	 * values for the aspects.
	 */
	INTRA_CONTRADICTION,

	/**
	 * Level that is applied to rules whose violations mean that there are
	 * configuration aspects that represent redundant search space. Validity may be
	 * achieved by specifying other values for those aspects.
	 */
	SOUPERFLUOUS,

	/**
	 * Level that is applied to rules whose violations mean that there are not
	 * optmial configuration aspects. Validity may be achieved by specifying other
	 * values for those aspects.
	 */
	IMPERFECT_PREFERATION;

}
