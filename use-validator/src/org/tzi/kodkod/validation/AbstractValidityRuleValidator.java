package org.tzi.kodkod.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Links the implementations of the validation of validity rule to the rules.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractValidityRuleValidator {

	/**
	 * Validated whether a rule is not violated.
	 * 
	 * @param config
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The UML class diagram of the UML/OCL model.
	 * @param rule
	 *            The rule.
	 * @param invariants
	 *            The invariant of the UML/OCL model.
	 * @return Whether a rule is not violated.
	 */
	boolean isSatisfied(final SettingsConfiguration config, final IModel model, ValidityRule rule,
			List<Expression> invariants) {
		final AbstractValidator validator = this.getValidator(rule);
		if (validator == null || rule.level == ValidityRuleLevel.INTERNAL) {
			throw new UnsupportedOperationException();
		}
		return validator.isSatisfied(config, model, invariants);

	}

	/**
	 * Computes the violations for a rule.
	 * 
	 * @param config
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The UML class diagram of the UML/OCL model.
	 * @param rule
	 *            The rule.
	 * @param invariants
	 *            The invariant of the UML/OCL model.
	 * @return The violations for a rule.
	 */
	public ValidityRuleViolence[] getViolations(final SettingsConfiguration config, final IModel model,
			ValidityRule rule, List<Expression> invariants) {
		final AbstractValidator validator = this.getValidator(rule);
		if (validator == null || rule.level == ValidityRuleLevel.INTERNAL) {
			throw new UnsupportedOperationException();
		}
		return validator.getViolations(config, model, invariants);
	};

	/**
	 * Returns the linked implementation of the validation of a rule.
	 * 
	 * @param rule
	 *            The rule.
	 * @return The linked implementation of the validation.
	 */
	protected abstract AbstractValidator getValidator(ValidityRule rule);

	/**
	 * Validates a configuration.
	 * 
	 * @param config
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The UML class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariant of the UML/OCL model.
	 * @return The violations of validity rules for the violated rules.
	 * @throws Exception
	 *             If the validation fails.
	 */
	public SortedMap<ValidityRule, ValidityRuleViolence[]> validate(final SettingsConfiguration config,
			final IModel model, List<Expression> invariants) throws Exception {
		SortedMap<ValidityRule, ValidityRuleViolence[]> violationsByValdityRules = new TreeMap<>();
		ValidityRule[] violations = getViolatedRules(config, model, invariants);
		for (ValidityRule violation : violations) {
			if (violationsByValdityRules.containsKey(violation)) {
				throw new UnsupportedOperationException();
			}
			violationsByValdityRules.put(violation, getViolations(config, model, violation, invariants));
		}
		return violationsByValdityRules;
	}

	/**
	 * Validates a configuration regarding all rules.
	 * 
	 * @param config
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The UML class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariant of the UML/OCL model.
	 * @return The violated rules.
	 * @throws Exception
	 */
	private ValidityRule[] getViolatedRules(final SettingsConfiguration config, final IModel model,
			List<Expression> invariants) throws Exception {
		return ValidityRule.sort(getViolatedRules(config, model, ValidityRule.SETTINGS_VALID, invariants));
	}

	/**
	 * Validates a configuration regarding a selected rule.
	 * 
	 * @param config
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The UML class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariant of the UML/OCL model.
	 * @param rule
	 *            The rule.
	 * @return The violated rules.
	 */
	private ValidityRule[] getViolatedRules(SettingsConfiguration config, IModel model, ValidityRule rule,
			List<Expression> invariants) {
		final Set<ValidityRule> unsatisfied = new HashSet<>();
		for (ValidityRule conditionallyDueToInviolationRule : rule.conditionallyDueToInviolations) {
			if (!unsatisfied.contains(conditionallyDueToInviolationRule)) {
				for (ValidityRule unsatisfiedConditionallyDueToInviolationRule : getViolatedRules(config, model,
						conditionallyDueToInviolationRule, invariants)) {
					unsatisfied.add(unsatisfiedConditionallyDueToInviolationRule);
				}
			}
		}
		if (unsatisfied.isEmpty() && rule.level != ValidityRuleLevel.INTERNAL
				&& !isSatisfied(config, model, rule, invariants)) {
			return new ValidityRule[] { rule };
		}
		return unsatisfied.toArray(new ValidityRule[unsatisfied.size()]);
	}

}
