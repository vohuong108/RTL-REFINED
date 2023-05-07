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
 * This is violated if for at least one association the maximum number of links
 * is not greater than or equal to -1.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If the maximum number of links is less than -1, set it to -1.</li>
 * <li>If the maximum number of links is less than -1, set it to 0.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AssociationSettingsMaxStarOrGreaterThanOrEqualZeroValidator
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
		if (maximum < -1) {
			final int newMaxs[] = { -1, 0 };
			for (int newMax : newMaxs) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(associationName + PropertyEntry.linksMax, String.valueOf(newMax)), true,
						minimum, newMax, config));
			}
		}
		if (!fixes.isEmpty()) {
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(
							UIElements.AttributeValueShouldBeGreaterThanOrEqual(
									associationName + PropertyEntry.linksMax, String.valueOf(maximum), "-1"),
							fixes.toArray(new AbstractFix[fixes.size()])) };

		}
		return new ValidityRuleViolence[0];
	}

}
