package org.tzi.kodkod.validation;

/**
 * The violation of a validity rule.
 * 
 * @author Jan Prien
 *
 */
public final class ValidityRuleViolence {

	/**
	 * The textual explanation of the violation.
	 */
	private final String explenation;

	/**
	 * The fixes for the violation.
	 */
	private final AbstractFix[] fixes;

	/**
	 * Constructs an object.
	 * 
	 * @param explenation
	 *            The textual explanation of the violation.
	 * @param fixes
	 *            The fixes for the violation.
	 */
	ValidityRuleViolence(final String explenation, final AbstractFix[] fixes) {
		if (explenation == null) {
			throw new IllegalArgumentException();
		}
		if (fixes != null) {
			for (AbstractFix fix : fixes) {
				if (fix == null) {
					throw new IllegalArgumentException();
				}
			}
		}
		this.explenation = explenation;
		this.fixes = fixes != null ? fixes : new AbstractFix[0];
	}

	public String getExplenation() {
		return explenation;
	}

	public AbstractFix[] getFixes() {
		return fixes;
	}

}
