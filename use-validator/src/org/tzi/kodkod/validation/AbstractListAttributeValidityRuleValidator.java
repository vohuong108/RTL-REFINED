package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.List;

import org.tzi.kodkod.model.iface.IModel;

/**
 * Validator for lists of configuration aspects of the UML/OCL model instance
 * finder configurations.
 * 
 * @author Jan Prien
 *
 * @param <A>
 *            The type of configuration aspects.
 */
abstract class AbstractListAttributeValidityRuleValidator<A> extends AbstractValidator {

	/**
	 * Validates whether the configurations are valid regarding the definition of
	 * this validator.
	 * 
	 * @param configs
	 *            The list of configurations.
	 * @param model
	 *            The model
	 * @return The violations of the validity regarding the definition of this
	 *         validator.
	 */
	protected ValidityRuleViolence[] getViolations(List<A> configs, IModel model) {
		if (configs == null || model == null) {
			throw new IllegalArgumentException();
		}
		List<ValidityRuleViolence> violations = new ArrayList<>();
		for (A config : configs) {
			if (config == null) {
				throw new IllegalArgumentException();
			}
			for (ValidityRuleViolence violence : getViolations(config, model)) {
				violations.add(violence);
			}
		}
		return violations.toArray(new ValidityRuleViolence[violations.size()]);
	}

	/**
	 * Validates whether a configuration aspect is valid regarding the definition of
	 * this validator.
	 * 
	 * @param config
	 *            The configuration to validate.
	 * @param model
	 *            The model.
	 * @return The violations of the validity regarding the definition of this
	 *         validator.
	 */
	protected abstract ValidityRuleViolence[] getViolations(A config, IModel model);

}
