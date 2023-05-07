package org.tzi.kodkod.validation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.InvariantSettings;

/**
 * Validator for each configuration aspect regarding one invariant representing
 * a validity rule.
 * 
 * This is violated if for at one invariant is configured to be negated while it
 * is not configured to be activated.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Set the invariant activated.</li>
 * <li>Set the invariant unnegated.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class InvariantSettingsOnlyNegateWhenActiveValidator extends AbstractInvariantSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(InvariantSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final String invariantName = this.getInvariantName(config);
		if (config.isNegate() && !config.isActive()) {
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(UIElements.InvariantShouldBeNegatedIfNotActivated(invariantName),
							new AbstractFix[] {
									new SetInvariantSettingsActivatedFix(config.getSettingsConfiguration(), model,
											UIElements.EnableInvariant(invariantName), true, true, config),
									new SetInvariantSettingsNegatedFix(config.getSettingsConfiguration(), model,
											UIElements.UnnegateInvariant(invariantName), true, false, config) }) };
		}
		return new ValidityRuleViolence[0];
	}

}
