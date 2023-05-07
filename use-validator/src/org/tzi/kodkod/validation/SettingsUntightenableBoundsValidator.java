package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.List;

import org.tzi.kodkod.clever.CleverConfigurationGenerator;
import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.clever.model2csp.IllegalInitialBoundException;
import org.tzi.kodkod.model.config.impl.PropertyEntry;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.AssociationSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator for all configuration aspects representing a validity rule.
 * 
 * This is violated if the model elements configuration aspects represent a
 * search space containing redundant search space.
 * 
 * Multiple fixes are provided for each model element this is violated for:
 * <ul>
 * <li>For classes:
 * <ul>
 * <li>If minimum number of objects is less than the optimised minimum number of
 * objects, set it to the optimised value.</li>
 * <li>If maximum number of objects is greater than the optimised maximum number
 * of objects, set it to the optimised value.</li>
 * <li>If minimum number of objects is less than the optimised minimum number of
 * objects and the maximum number of objects is greater than the optimised
 * maximum number of objects, set both to their corresponding optimised value.
 * </li>
 * </ul>
 * <li>For attributes:
 * <ul>
 * <li>If minimum number of defined attributes is less than the optimised
 * minimum number of defined attributes, set it to the optimised value.</li>
 * <li>If maximum number of defined attributes is greater than the optimised
 * maximum number of defined attributes, set it to the optimised value.</li>
 * <li>If minimum number of defined attributes is less than the optimised
 * minimum number of defined attributes and the maximum number of defined
 * attributes is greater than the optimised maximum number of defined
 * attributes, set both to their corresponding optimised value.</li>
 * </ul>
 * <li>For associations:
 * <ul>
 * <li>If minimum number of links is less than the optimised minimum number of
 * links, set it to the optimised value.</li>
 * <li>If maximum number of links is greater than the optimised maximum number
 * of links, set it to the optimised value.</li>
 * <li>If minimum number of links is less than the optimised minimum number of
 * links and the maximum number of links is greater than the optimised maximum
 * number of links, set both to their corresponding optimised value.</li>
 * </ul>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class SettingsUntightenableBoundsValidator extends AbstractValidator {

	@Override
	ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model, List<Expression> invariants) {
		final SettingsConfiguration configCopy = createCopy(config, model);
		CleverConfigurationGenerator cleverConfigurationGenerator = new CleverConfigurationGenerator();
		try {
			cleverConfigurationGenerator.generate(
					generateIModelCSPVariablesInitialBoundsSpecification(configCopy, model), invariants, false, false,
					false, configCopy);
		} catch (Exception e) {
			return new ValidityRuleViolence[] { new ValidityRuleViolence(
					UIElements.SettingsUntightenableBounds_Uncomputeable, new AbstractFix[0]) };
		}
		List<ValidityRuleViolence> violations = new ArrayList<ValidityRuleViolence>();
		for (ClassSettings classSettings : config.getAllClassesSettings()) {
			IClass iClass = classSettings.getCls();
			String className = iClass.name();
			ClassSettings copyClassSettings = configCopy.getClassSettings(iClass);
			List<AbstractFix> classFixes = new ArrayList<AbstractFix>();
			final boolean tighterClassSettingsLowerBounds = copyClassSettings.getLowerBound() > classSettings
					.getLowerBound();
			final boolean tighterClassSettingsUpperBounds = copyClassSettings.getUpperBound() < classSettings
					.getUpperBound();
			if (tighterClassSettingsLowerBounds) {
				final int newClassMin = copyClassSettings.getLowerBound();
				classFixes.add(new SetClassSettingsMinMaxFix(config, model,
						UIElements.SetToValue(className + PropertyEntry.objMin, String.valueOf(newClassMin)),
						!tighterClassSettingsUpperBounds, classSettings, newClassMin, classSettings.getUpperBound()));
			}
			if (tighterClassSettingsUpperBounds) {
				final int newClassMax = copyClassSettings.getUpperBound();
				classFixes.add(new SetClassSettingsMinMaxFix(config, model,
						UIElements.SetToValue(className + PropertyEntry.objMax, String.valueOf(newClassMax)),
						!tighterClassSettingsLowerBounds, classSettings, classSettings.getLowerBound(), newClassMax));
			}
			if (tighterClassSettingsLowerBounds && tighterClassSettingsUpperBounds) {
				final int newClassMin = copyClassSettings.getLowerBound();
				final int newClassMax = copyClassSettings.getUpperBound();
				classFixes.add(new SetClassSettingsMinMaxFix(config, model,
						UIElements.SetToValue(className + PropertyEntry.objMin, className + PropertyEntry.objMax,
								String.valueOf(newClassMin), String.valueOf(newClassMax)),
						true, classSettings, newClassMin, newClassMax));
			}
			if (!classFixes.isEmpty()) {
				violations.add(new ValidityRuleViolence(
						UIElements.SettingsUntightenableBounds_TighterBounds(className, UIElements.Class.toLowerCase()),
						classFixes.toArray(new AbstractFix[classFixes.size()])));
			}
			for (AttributeSettings attributeSettings : classSettings.getAttributeSettings().values()) {
				if (attributeSettings.getLowerBound() != -1) {
					IAttribute iAttribute = attributeSettings.getAttribute();
					String attributeName = iAttribute.name();
					AttributeSettings copyAttributeSettings = copyClassSettings.getAttributeSettings().get(iAttribute);
					List<AbstractFix> attributeFixes = new ArrayList<AbstractFix>();
					final boolean tighterAttributeSettingsLowerBounds = copyAttributeSettings
							.getLowerBound() > attributeSettings.getLowerBound();
					final boolean tighterAttributeSettingsUpperBounds = copyAttributeSettings
							.getUpperBound() < attributeSettings.getUpperBound();
					if (tighterAttributeSettingsLowerBounds) {
						final int newAttributeMin = copyAttributeSettings.getLowerBound();
						attributeFixes.add(new SetAttributeSettingsMinMaxFix(config, model,
								UIElements.SetToValue(attributeName + PropertyEntry.attributeDefValuesMin,
										String.valueOf(newAttributeMin)),
								!tighterAttributeSettingsUpperBounds, attributeSettings, newAttributeMin,
								attributeSettings.getUpperBound()));
					}
					if (tighterAttributeSettingsUpperBounds && attributeSettings.getUpperBound() != -1) {
						final int newAttributeMax = copyAttributeSettings.getUpperBound();
						attributeFixes.add(new SetAttributeSettingsMinMaxFix(config, model,
								UIElements.SetToValue(attributeName + PropertyEntry.attributeDefValuesMax,
										String.valueOf(newAttributeMax)),
								!tighterAttributeSettingsLowerBounds, attributeSettings,
								attributeSettings.getLowerBound(), newAttributeMax));
					}
					if (tighterAttributeSettingsLowerBounds && tighterAttributeSettingsUpperBounds
							&& attributeSettings.getUpperBound() != -1) {
						final int newAttributeMin = copyAttributeSettings.getLowerBound();
						final int newAttributeMax = copyAttributeSettings.getUpperBound();
						attributeFixes.add(new SetAttributeSettingsMinMaxFix(config, model,
								UIElements.SetToValue(attributeName + PropertyEntry.attributeDefValuesMin,
										attributeName + PropertyEntry.attributeDefValuesMax,
										String.valueOf(newAttributeMin), String.valueOf(newAttributeMax)),
								true, attributeSettings, newAttributeMin, newAttributeMax));
					}
					if (!attributeFixes.isEmpty()) {
						violations.add(new ValidityRuleViolence(
								UIElements.SettingsUntightenableBounds_TighterBounds(attributeName,
										UIElements.Attribute.toLowerCase()),
								attributeFixes.toArray(new AbstractFix[attributeFixes.size()])));
					}
				}
			}
		}
		for (AssociationSettings associationSettings : config.getAllAssociationSettings()) {
			IAssociation iAssociation = associationSettings.getAssociation();
			String associationName = iAssociation.name();
			AssociationSettings copyAssociationSettings = configCopy.getAssociationSettings(iAssociation);
			List<AbstractFix> associationFixes = new ArrayList<AbstractFix>();
			final boolean tighterAssociationSettingsLowerBounds = copyAssociationSettings
					.getLowerBound() > associationSettings.getLowerBound();
			final boolean tighterAssociationSettingsUpperBounds = copyAssociationSettings
					.getUpperBound() < associationSettings.getUpperBound();
			if (tighterAssociationSettingsLowerBounds) {
				final int newAssociationMin = copyAssociationSettings.getLowerBound();
				associationFixes.add(new SetAssociationSettingsMinMaxFix(config, model,
						UIElements.SetToValue(associationName + PropertyEntry.linksMin,
								String.valueOf(newAssociationMin)),
						!tighterAssociationSettingsUpperBounds, associationSettings, newAssociationMin,
						associationSettings.getUpperBound()));
			}
			if (tighterAssociationSettingsUpperBounds) {
				final int newAssociationMax = copyAssociationSettings.getUpperBound();
				associationFixes.add(new SetAssociationSettingsMinMaxFix(config, model,
						UIElements.SetToValue(associationName + PropertyEntry.linksMax,
								String.valueOf(newAssociationMax)),
						!tighterAssociationSettingsLowerBounds, associationSettings,
						associationSettings.getLowerBound(), newAssociationMax));
			}
			if (tighterAssociationSettingsLowerBounds && tighterAssociationSettingsUpperBounds) {
				final int newAssociationMin = copyAssociationSettings.getLowerBound();
				final int newAssociationMax = copyAssociationSettings.getUpperBound();
				associationFixes.add(new SetAssociationSettingsMinMaxFix(config, model,
						UIElements.SetToValue(associationName + PropertyEntry.linksMin,
								associationName + PropertyEntry.linksMax, String.valueOf(newAssociationMin),
								String.valueOf(newAssociationMax)),
						true, associationSettings, newAssociationMin, newAssociationMax));
			}
			if (!associationFixes.isEmpty()) {
				violations.add(new ValidityRuleViolence(
						UIElements.SettingsUntightenableBounds_TighterBounds(associationName,
								UIElements.Association.toLowerCase()),
						associationFixes.toArray(new AbstractFix[associationFixes.size()])));
			}

		}
		return violations.toArray(new ValidityRuleViolence[violations.size()]);
	}

	private SettingsConfiguration createCopy(SettingsConfiguration config, IModel model) {
		SettingsConfiguration copy = new SettingsConfiguration(model);
		for (ClassSettings classSettings : config.getAllClassesSettings()) {
			ClassSettings copyClassSettings = copy.getClassSettings(classSettings.getCls());
			copyClassSettings.setLowerBound(classSettings.getLowerBound());
			copyClassSettings.setUpperBound(classSettings.getUpperBound());
			for (AttributeSettings attributeSettings : classSettings.getAttributeSettings().values()) {
				AttributeSettings copyAttributeSettings = copyClassSettings.getAttributeSettings()
						.get(attributeSettings.getAttribute());
				if (attributeSettings.getLowerBound() == -1) {
					copyAttributeSettings.setLowerBound(copyClassSettings.getLowerBound());
					copyAttributeSettings.setUpperBound(copyClassSettings.getUpperBound());
				} else if (attributeSettings.getUpperBound() == -1) {
					copyAttributeSettings.setLowerBound(attributeSettings.getLowerBound());
					copyAttributeSettings.setUpperBound(
							Integer.max(attributeSettings.getLowerBound(), copyClassSettings.getUpperBound()));
				} else {
					copyAttributeSettings.setLowerBound(attributeSettings.getLowerBound());
					copyAttributeSettings.setUpperBound(attributeSettings.getUpperBound());
				}
			}
		}
		for (AssociationSettings associationSettings : config.getAllAssociationSettings()) {
			AssociationSettings copyAssociationSettings = copy
					.getAssociationSettings(associationSettings.getAssociation());
			copyAssociationSettings.setLowerBound(associationSettings.getLowerBound());
			if (associationSettings.getUpperBound() == -1) {
				copyAssociationSettings.setUpperBound(Integer.MAX_VALUE - 1); // TODO Specify handling of tightening for
																				// association settings without upper
																				// bound
			} else {
				copyAssociationSettings.setUpperBound(associationSettings.getUpperBound());
			}
		}
		return copy;
	}

	private IModelCSPVariablesInitialBoundsSpecification generateIModelCSPVariablesInitialBoundsSpecification(
			SettingsConfiguration config, IModel model) throws IllegalInitialBoundException {
		int minLowerBound = Integer.MAX_VALUE;
		int maxUpperBound = Integer.MIN_VALUE;
		for (ClassSettings classSettings : config.getAllClassesSettings()) {
			if (minLowerBound > classSettings.getLowerBound()) {
				minLowerBound = classSettings.getLowerBound();
			}
			if (maxUpperBound < classSettings.getUpperBound()) {
				maxUpperBound = classSettings.getUpperBound();
			}
			for (AttributeSettings attributeSettings : classSettings.getAttributeSettings().values()) {
				if (minLowerBound > attributeSettings.getLowerBound()) {
					minLowerBound = attributeSettings.getLowerBound();
				}
				if (maxUpperBound < attributeSettings.getUpperBound()) {
					maxUpperBound = attributeSettings.getUpperBound();
				}
			}
		}
		for (AssociationSettings associationSettings : config.getAllAssociationSettings()) {
			if (minLowerBound > associationSettings.getLowerBound()) {
				minLowerBound = associationSettings.getLowerBound();
			}
			if (maxUpperBound < associationSettings.getUpperBound()) {
				maxUpperBound = associationSettings.getUpperBound();
			}
		}
		IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification = new IModelCSPVariablesInitialBoundsSpecification(
				model, minLowerBound, maxUpperBound);
		for (ClassSettings classSettings : config.getAllClassesSettings()) {
			iModelCSPVariablesInitialBoundsSpecification.setSpecificClassBounds(classSettings.getCls(),
					classSettings.getLowerBound(), classSettings.getUpperBound());
			for (AttributeSettings attributeSettings : classSettings.getAttributeSettings().values()) {
				iModelCSPVariablesInitialBoundsSpecification.setSpecificAttributeBounds(classSettings.getCls(),
						attributeSettings.getAttribute(), attributeSettings.getLowerBound(),
						attributeSettings.getUpperBound());
			}
		}
		for (AssociationSettings associationSettings : config.getAllAssociationSettings()) {
			iModelCSPVariablesInitialBoundsSpecification.setSpecificAssociationBounds(
					associationSettings.getAssociation(), associationSettings.getLowerBound(),
					associationSettings.getUpperBound());
		}
		return iModelCSPVariablesInitialBoundsSpecification;
	}

}
