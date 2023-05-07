package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.AssociationSettings;

/**
 * Validator for each configuration aspect regarding one association
 * representing a validity rule.
 * 
 * This is violated if for at least one association the number of required links
 * is less than or equal to the minimum and maximum number of links.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>If minimum and maximum numbers of links are less than the size of
 * required links, set both to the size of required links.</li>
 * <li>If minimum number of links is less than the size of required links, set
 * the minimum number of links to the size of required links.</li>
 * <li>If maximum number of links is less than the size of required links, set
 * the maximum number of links to the size of required links.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AssociationSettingsRequiredLinksCardinalityLessThanOrEqualMinLinksAndMaxLinksValidator
		extends AbstractAssociationSettingsValidityRuleValidator {

	@Override
	protected ValidityRuleViolence[] getViolations(AssociationSettings config, IModel model) {
		if (config == null || model == null) {
			throw new IllegalArgumentException();
		}
		final int minimum = config.getLowerBound();
		final int maximum = config.getUpperBound();
		final int requiredLinksCardinality = this.getRequiredLinksCardinality(config,
				this.getAssociatedClasses(config).size());
		final String associationName = config.getAssociation().name();
		final Set<String> instanceNames = config.getInstanceNames();

		if ((minimum >= 0 && requiredLinksCardinality > minimum)
				|| (maximum >= 0 && requiredLinksCardinality > maximum)) {
			List<AbstractFix> fixes = new ArrayList<AbstractFix>();
			if (minimum >= 0 && minimum < requiredLinksCardinality && maximum >= 0
					&& maximum < requiredLinksCardinality) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(associationName + PropertyEntry.linksMin,
								associationName + PropertyEntry.linksMax, String.valueOf(requiredLinksCardinality)),
						true, requiredLinksCardinality, requiredLinksCardinality, config));
			}
			if (minimum >= 0 && minimum < requiredLinksCardinality) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(associationName + PropertyEntry.linksMin,
								String.valueOf(requiredLinksCardinality)),
						maximum >= requiredLinksCardinality, requiredLinksCardinality, maximum, config));
				if (minimum > 0) {
					final Set<String> newInstanceNames = new HashSet<>(
							new ArrayList<String>(instanceNames).subList(0, minimum));
					fixes.add(new SetInstanceSettingsInstanceNamesFix(config.getSettingsConfiguration(), model,
							UIElements.RemoveLastValues(associationName, requiredLinksCardinality - minimum), true,
							newInstanceNames, config));
				}
			}
			if (maximum >= 0 && maximum < requiredLinksCardinality) {
				fixes.add(new SetInstanceSettingsMinMaxFix(config.getSettingsConfiguration(), model,
						UIElements.SetToValue(associationName + PropertyEntry.linksMax,
								String.valueOf(requiredLinksCardinality)),
						minimum >= requiredLinksCardinality, minimum, requiredLinksCardinality, config));
				if (minimum >= 0 && minimum < maximum && maximum > 0) {
					final Set<String> newInstanceNames = new HashSet<>(
							new ArrayList<String>(instanceNames).subList(0, maximum));
					fixes.add(new SetInstanceSettingsInstanceNamesFix(config.getSettingsConfiguration(), model,
							UIElements.RemoveLastValues(associationName, requiredLinksCardinality - maximum), false,
							newInstanceNames, config));
				}
			}
			fixes.add(new SetInstanceSettingsInstanceNamesFix(config.getSettingsConfiguration(), model,
					UIElements.RemoveAllValues(associationName), true, new HashSet<String>(), config));
			if (!fixes.isEmpty()) {
				return new ValidityRuleViolence[] { new ValidityRuleViolence(
						UIElements.AssociationSettingsRequiredLinksCardinalityEqualsMinLinksAndMaxLinks(
								associationName + PropertyEntry.linksMin, associationName + PropertyEntry.linksMax,
								String.valueOf(minimum), String.valueOf(maximum),
								String.valueOf(requiredLinksCardinality)),
						fixes.toArray(new AbstractFix[fixes.size()])) };

			}
		}
		return new ValidityRuleViolence[0];
	}

}
