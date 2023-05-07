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
 * is not -1 and not greater than or equal to the minimum number of links.
 * 
 * Provided on invalidity: ul>
 * <li>Set the minimum number of links to 0.</li>
 * <li>Set the maximum number of links to -1.</li>
 * <li>Set the minimum number of links to the maximum number of links.</li>
 * <li>Set the maximum number of links to the minimum number of links.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AssociationSettingsMinLessThanOrEqualMaxWhenMaxNotStarValidator
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
		if (maximum > -1 && minimum > maximum) {
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToValue(associationName + PropertyEntry.linksMin, "0"), true, 0, maximum, config));
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToValue(associationName + PropertyEntry.linksMax, "-1"), true, minimum, -1, config));
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToAttributeValue(associationName + PropertyEntry.linksMin,
							associationName + PropertyEntry.linksMax, String.valueOf(maximum)),
					true, maximum, maximum, config));
			fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
					UIElements.SetToAttributeValue(associationName + PropertyEntry.linksMax,
							associationName + PropertyEntry.linksMin, String.valueOf(minimum)),
					true, minimum, minimum, config));
		}
		if (!fixes.isEmpty()) {
			return new ValidityRuleViolence[] { new ValidityRuleViolence(
					UIElements.MinLessThanOrEqualMaxWhenMaxNotStar(associationName + PropertyEntry.linksMin,
							associationName + PropertyEntry.linksMax, String.valueOf(minimum), String.valueOf(maximum)),
					fixes.toArray(new AbstractFix[fixes.size()])) };
		}
		return new ValidityRuleViolence[0];
	}

}
