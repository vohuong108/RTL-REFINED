package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.List;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.AssociationSettings;

/**
 * Validator for each configuration aspect regarding one association
 * representing a validity rule.
 * 
 * This is violated if for at least one association the minimum number of links
 * is not greater than or equal to 0.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If the minimum number of links is less than 0, set it to 0.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AssociationSettingsMinGreaterThanOrEqualZeroValidator
		extends AbstractAssociationSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(AssociationSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final int minimum = config.getLowerBound();
		final int maximum = config.getUpperBound();
		final String associationName = config.getAssociation().name();
		List<AbstractFix> fixes = new ArrayList<AbstractFix>();
		if (minimum < 0) {
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToValue(associationName + PropertyEntry.linksMin, "0"), true, 0, maximum, config));
		}
		if (!fixes.isEmpty()) {
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(
							UIElements.AttributeValueShouldBeGreaterThanOrEqual(
									associationName + PropertyEntry.linksMin, String.valueOf(minimum), "0"),
							fixes.toArray(new AbstractFix[fixes.size()])) };
		}
		return new ValidityRuleViolence[0];
	}

}
