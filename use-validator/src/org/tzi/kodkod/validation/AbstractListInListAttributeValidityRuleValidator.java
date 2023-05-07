package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.tzi.kodkod.model.iface.IModel;

/**
 * Validator for lists of lists configuration aspects of the UML/OCL model
 * instance finder configurations.
 * 
 * @author Jan Prien
 *
 * @param <A>
 *            The type of configuration aspects.
 * @param <B>
 *            The type of the parents of the configuration aspects.
 */
abstract class AbstractListInListAttributeValidityRuleValidator<A, B> extends AbstractValidator {

	/**
	 * Validates whether the configurations in the lists of lists are valid
	 * regarding the definition of this validator.
	 * 
	 * @param configsForParentConfigs
	 *            The lists of configurations for their parents.
	 * @param model
	 *            The model
	 * @return The violations of the validity regarding the definition of this
	 *         validator.
	 */
	protected ValidityRuleViolence[] getViolations(Map<B, Set<A>> configsForParentConfigs, IModel model) {
		if (configsForParentConfigs == null || model == null) {
			throw new IllegalArgumentException();
		}
		List<ValidityRuleViolence> violations = new ArrayList<>();
		for (Entry<B, Set<A>> configsForParentConfig : configsForParentConfigs.entrySet()) {
			if (configsForParentConfig == null) {
				throw new IllegalArgumentException();
			}
			B parentConfig = configsForParentConfig.getKey();
			if (parentConfig == null) {
				throw new IllegalArgumentException();
			}
			Set<A> configs = configsForParentConfig.getValue();
			if (configs == null) {
				throw new IllegalArgumentException();
			}
			for (A config : configs) {
				if (config == null) {
					throw new IllegalArgumentException();
				}
				for (ValidityRuleViolence fix : getViolations(parentConfig, config, model)) {
					violations.add(fix);
				}
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
	protected abstract ValidityRuleViolence[] getViolations(B parentConfig, A config, IModel model);

}
