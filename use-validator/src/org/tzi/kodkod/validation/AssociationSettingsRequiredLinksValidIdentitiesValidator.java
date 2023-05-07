package org.tzi.kodkod.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.validation.ui.UIElements;
import org.tzi.use.kodkod.plugin.gui.model.data.AssociationSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Validator for each configuration aspect regarding one association
 * representing a validity rule.
 * 
 * This is violated if not all object names contained in the preferred links are
 * contained in the preferred object names.
 * 
 * Provided on invalidity:
 * <ul>
 * <li>Remove all required links containing instance names that are not
 * contained in the preferred instance names of the corresponding classes.</li>
 * <li>If there are ten or less links to be removed, provide a removal of each.
 * </li>
 * <li>For each class the preferred instance names do not contain corresponding
 * instance names from the required links add the missing instance names.</li>
 * <li>If there are more than one class with the foregoing fix, provide also a
 * bundled fix of those.</li>
 * </ul>
 * 
 * @author Jan Prien
 *
 */
final class AssociationSettingsRequiredLinksValidIdentitiesValidator
		extends AbstractAssociationSettingsValidityRuleValidator implements IClassSettingsValidityRuleValidator {

	/**
	 * The maximum number of invalid required links to be removed. If there are less
	 * than this number, an individual removal is provided.
	 */
	public static final int MAX_SINGLE_INVALID_REMOVAL_FIXES = 10;

	@Override
	protected ValidityRuleViolence[] getViolations(AssociationSettings config, IModel model) {
		if (config == null) {
			throw new IllegalArgumentException();
		}
		SettingsConfiguration settingsConfig = config.getSettingsConfiguration();

		String associationName = config.getAssociation().name();

		final List<IClass> associatedClasses = this.getAssociatedClasses(config);
		if (associatedClasses.size() < 2) {
			throw new UnsupportedOperationException();
		}

		final Set<RequiredLink> requiredLinks = this.getRequiredLinks(config, associatedClasses.size()).asSet();

		List<Set<String>> preferredIdentities = new ArrayList<>();
		for (IClass associatedClass : associatedClasses) {
			preferredIdentities.add(this.getPreferredIdentities(settingsConfig, associatedClass));
		}

		List<Set<String>> missingPreferredIdentitiesForAssociatedClasses = new ArrayList<>();
		for (@SuppressWarnings("unused")
		IClass associatedClass : associatedClasses) {
			missingPreferredIdentitiesForAssociatedClasses.add(new HashSet<>());
		}

		final Set<RequiredLink> invalidLinks = new HashSet<>();

		for (RequiredLink requiredLink : requiredLinks) {
			boolean validRequiredLink = true;
			int i = -1;
			for (String element : requiredLink.getElements()) {
				i++;
				boolean elementMissing = true;
				for (String prefferedIdentity : preferredIdentities.get(i)) {
					if (prefferedIdentity.equals(element)) {
						elementMissing = false;
					}
				}
				if (elementMissing) {
					validRequiredLink = false;
					missingPreferredIdentitiesForAssociatedClasses.get(i).add(element);
				}
			}
			if (!validRequiredLink) {
				invalidLinks.add(requiredLink);
			}
		}

		List<AbstractFix> fixes = new ArrayList<AbstractFix>();

		if (invalidLinks.size() > 0) {
			final Set<RequiredLink> validRequiredLinks = new HashSet<>();
			validRequiredLinks.addAll(requiredLinks);
			validRequiredLinks.removeAll(invalidLinks);
			fixes.add(new SetInstanceSettingsInstanceNamesFix(settingsConfig, model,
					UIElements.RemoveAllInvalidValues(associationName, RequiredLinks.fromSet(invalidLinks).asStrings()),
					true, RequiredLinks.fromSet(validRequiredLinks).asStrings(), config));
		}

		if (invalidLinks.size() > 1 && invalidLinks.size() <= MAX_SINGLE_INVALID_REMOVAL_FIXES) {
			for (RequiredLink invalidRequiredLink : invalidLinks) {
				final Set<RequiredLink> newRequiredLinks = new HashSet<>();
				newRequiredLinks.addAll(requiredLinks);
				newRequiredLinks.remove(invalidRequiredLink);
				fixes.add(new SetInstanceSettingsInstanceNamesFix(settingsConfig, model,
						UIElements.RemoveInvalidValue(associationName, invalidRequiredLink.asString()), false,
						RequiredLinks.fromSet(newRequiredLinks).asStrings(), config));
			}
		}

		String violationExplenation = "";
		if (invalidLinks.size() > 0) {
			final Set<SetInstanceSettingsInstanceNamesFix> partialFixes = new HashSet<SetInstanceSettingsInstanceNamesFix>();
			int i = -1;
			String explenation = "Add all missing classes values. Add";
			Map<ClassSettings, Set<String>> missingPreferredIdentitiesForClasses = new HashMap<>();
			for (Set<String> missingPreferredIdentities : missingPreferredIdentitiesForAssociatedClasses) {
				i++;
				final ClassSettings classSettings = settingsConfig.getClassSettings(associatedClasses.get(i));
				Set<String> missingPreferredIdentitiesForClass = missingPreferredIdentitiesForClasses
						.get(classSettings);
				if (missingPreferredIdentitiesForClass == null) {
					missingPreferredIdentitiesForClass = new HashSet<>();
				}
				missingPreferredIdentitiesForClass.addAll(missingPreferredIdentities);
				missingPreferredIdentitiesForClasses.put(classSettings, missingPreferredIdentitiesForClass);
			}
			for (ClassSettings classSettings : missingPreferredIdentitiesForClasses.keySet()) {
				Set<String> missingPreferredIdentities = missingPreferredIdentitiesForClasses.get(classSettings);

				if (missingPreferredIdentities.size() > 0) {
					final String className = classSettings.getCls().name();
					final Set<String> validPreferredIdentities = new HashSet<>();
					validPreferredIdentities.addAll(classSettings.getInstanceNames());
					validPreferredIdentities.addAll(missingPreferredIdentities);
					final String missingValues = String.join(",", missingPreferredIdentities);
					final String partialExplenation = " missing values (" + missingValues + ") to " + className;
					SetInstanceSettingsInstanceNamesFix partialFix = new SetInstanceSettingsInstanceNamesFix(
							settingsConfig, model, "Add " + partialExplenation + ".",
							missingPreferredIdentitiesForClasses.size() == 1, validPreferredIdentities, classSettings);
					fixes.add(partialFix);
					partialFixes.add(partialFix);
					if (partialFixes.size() > 1) {
						explenation += " and add";
					}
					explenation += partialExplenation;
					if (violationExplenation.length() > 0) {
						violationExplenation += " and ";
					}
					violationExplenation += className
							+ " does not contain values contained in required link definitions (" + missingValues + ")";
				}
			}
			explenation += ".";
			if (partialFixes.size() > 1) {
				fixes.add(new SetMultipleInstanceSettingsInstanceNamesFix(settingsConfig, model, explenation, true,
						partialFixes));
			}
		}
		violationExplenation += ".";

		if (!fixes.isEmpty()) {
			return new ValidityRuleViolence[] {
					new ValidityRuleViolence(violationExplenation, fixes.toArray(new AbstractFix[fixes.size()])) };
		}
		return new ValidityRuleViolence[0];
	}

}
