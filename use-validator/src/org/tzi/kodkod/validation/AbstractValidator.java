package org.tzi.kodkod.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.type.Type;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Validator for the configuration aspects of the UML/OCL model instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractValidator {

	/**
	 * Validates whether the configuration is valid regarding the definition of this
	 * validator.
	 * 
	 * @param config
	 *            The configuration to validate.
	 * @param model
	 *            The model.
	 * @param invariants
	 *            The invariants of the model.
	 * @return Whether the configuration is valid regarding the definition of this
	 *         validator.
	 */
	boolean isSatisfied(final SettingsConfiguration config, final IModel model, List<Expression> invariants) {
		return getViolations(config, model, invariants).length < 1;
	}

	/**
	 * Validates whether the configuration is valid regarding the definition of this
	 * validator.
	 * 
	 * @param config
	 *            The configuration to validate.
	 * @param model
	 *            The model.
	 * @param invariants
	 *            The invariants of the model.
	 * @return The violations of the validity regarding the definition of this
	 *         validator.
	 */
	abstract ValidityRuleViolence[] getViolations(SettingsConfiguration config, IModel model,
			List<Expression> invariants);

	/**
	 * The supported types in instance finder configurations.
	 * 
	 * @author Jan Prien
	 *
	 */
	protected static enum TypeChecker {

		/**
		 * Real.
		 */
		REAL {
			@Override
			boolean isType(Type type) {
				return type != null && type.isReal();
			}
		},

		/**
		 * String.
		 */
		STRING {
			@Override
			boolean isType(Type type) {
				return type != null && type.isString();
			}
		},

		/**
		 * Integer.
		 */
		INTEGER {
			@Override
			boolean isType(Type type) {
				return type != null && type.isInteger();
			}
		};

		/**
		 * Returns whether the type is equal.
		 * 
		 * @param type
		 *            The type.
		 * @return Whether the type is equal.
		 */
		abstract boolean isType(Type type);

	}

	/**
	 * Finds all class settings that are configured to have instances and at least
	 * one attribute that can be defined.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @param typeChecker
	 *            The type checker.
	 * @return The attributes that are defined for their owning classes that are
	 *         configured to have instances.
	 */
	private Map<ClassSettings, Set<AttributeSettings>> getClassSettingsWithInstancesAndAttributeWithMaxGreaterThanZero(
			final SettingsConfiguration config, final IModel model, final TypeChecker typeChecker) {
		if (config == null || model == null || typeChecker == null) {
			throw new IllegalArgumentException();
		}
		Map<ClassSettings, Set<AttributeSettings>> attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances = new HashMap<ClassSettings, Set<AttributeSettings>>();
		for (ClassSettings classSettings : config.getAllClassesSettings()) {
			if (classSettings.getUpperBound() > 0) {
				for (Entry<IAttribute, AttributeSettings> attributeSettingsForAttribute : classSettings
						.getAttributeSettings().entrySet()) {
					IAttribute attribute = attributeSettingsForAttribute.getKey();
					Type attributeType = attribute.type();
					AttributeSettings attributeSettings = attributeSettingsForAttribute.getValue();
					if (typeChecker.isType(attributeType) && (attributeSettings.getLowerBound() == -1
							|| attributeSettings.getUpperBound() == -1 || attributeSettings.getUpperBound() > 0)) {
						Set<AttributeSettings> newAttributeSettings = new HashSet<>();
						if (attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances
								.containsKey(classSettings)) {
							newAttributeSettings = attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances
									.get(classSettings);
						}
						newAttributeSettings.add(attributeSettings);
						attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances.put(classSettings,
								newAttributeSettings);
					}
				}
			}
		}
		return attributeSettingsWithMaxGreaterThanZeroForClassSettingsWithInstances;
	}

	/**
	 * Finds all class settings that are configured to have instances and at least
	 * one real attribute that can be defined.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return The real attributes that are defined for their owning classes that
	 *         are configured to have instances.
	 */
	protected Map<ClassSettings, Set<AttributeSettings>> getClassSettingsWithInstancesAndRealAttributeWithMaxGreaterThanZero(
			final SettingsConfiguration config, final IModel model) {
		return getClassSettingsWithInstancesAndAttributeWithMaxGreaterThanZero(config, model, TypeChecker.REAL);
	}

	/**
	 * Finds all class settings that are configured to have instances and at least
	 * one string attribute that can be defined.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return The string attributes that are defined for their owning classes that
	 *         are configured to have instances.
	 */
	protected Map<ClassSettings, Set<AttributeSettings>> getClassSettingsWithInstancesAndStringAttributeWithMaxGreaterThanZero(
			final SettingsConfiguration config, final IModel model) {
		return getClassSettingsWithInstancesAndAttributeWithMaxGreaterThanZero(config, model, TypeChecker.STRING);
	}

	/**
	 * Finds all class settings that are configured to have instances and at least
	 * one integer attribute that can be defined.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return The integer attributes that are defined for their owning classes that
	 *         are configured to have instances.
	 */
	protected Map<ClassSettings, Set<AttributeSettings>> getClassSettingsWithInstancesAndIntegerAttributeWithMaxGreaterThanZero(
			final SettingsConfiguration config, final IModel model) {
		return getClassSettingsWithInstancesAndAttributeWithMaxGreaterThanZero(config, model, TypeChecker.INTEGER);
	}

	/**
	 * Finds all class settings that have at least one attribute that can not be
	 * defined.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @param typeChecker
	 *            The type checker.
	 * @return The attributes that can not be defined for their owning classes that
	 *         are configured to have instances.
	 */
	private Map<ClassSettings, Set<AttributeSettings>> getClassSettingsWithIntegerAttributeWithMaxZero(
			SettingsConfiguration config, IModel model, TypeChecker typeChecker) {
		if (config == null || model == null || typeChecker == null) {
			throw new IllegalArgumentException();
		}
		Map<ClassSettings, Set<AttributeSettings>> attributeSettingsWithMaxZeroForClassSettings = new HashMap<ClassSettings, Set<AttributeSettings>>();
		for (ClassSettings classSettings : config.getAllClassesSettings()) {
			for (Entry<IAttribute, AttributeSettings> attributeSettingsForAttribute : classSettings
					.getAttributeSettings().entrySet()) {
				IAttribute attribute = attributeSettingsForAttribute.getKey();
				Type attributeType = attribute.type();
				AttributeSettings attributeSettings = attributeSettingsForAttribute.getValue();
				if (typeChecker.isType(attributeType)
						&& (attributeSettings.getLowerBound() != -1 && attributeSettings.getUpperBound() == 0)) {
					Set<AttributeSettings> newAttributeSettings = new HashSet<>();
					if (attributeSettingsWithMaxZeroForClassSettings.containsKey(classSettings)) {
						newAttributeSettings = attributeSettingsWithMaxZeroForClassSettings.get(classSettings);
					}
					newAttributeSettings.add(attributeSettings);
					attributeSettingsWithMaxZeroForClassSettings.put(classSettings, newAttributeSettings);
					break;
				}
			}
		}
		return attributeSettingsWithMaxZeroForClassSettings;
	}

	/**
	 * Finds all class settings that have at least one real attribute that can not
	 * be defined.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return The real attributes that can not be defined for their owning classes
	 *         that are configured to have instances.
	 */
	protected Map<ClassSettings, Set<AttributeSettings>> getClassSettingsWithRealAttributeWithMaxZero(
			SettingsConfiguration config, IModel model) {
		return getClassSettingsWithIntegerAttributeWithMaxZero(config, model, TypeChecker.REAL);
	}

	/**
	 * Finds all class settings that have at least one string attribute that can not
	 * be defined.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return The string attributes that can not be defined for their owning
	 *         classes that are configured to have instances.
	 */
	protected Map<ClassSettings, Set<AttributeSettings>> getClassSettingsWithStringAttributeWithMaxZero(
			SettingsConfiguration config, IModel model) {
		return getClassSettingsWithIntegerAttributeWithMaxZero(config, model, TypeChecker.STRING);
	}

	/**
	 * Finds all class settings that have at least one integer attribute that can
	 * not be defined.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return The integer attributes that can not be defined for their owning
	 *         classes that are configured to have instances.
	 */
	protected Map<ClassSettings, Set<AttributeSettings>> getClassSettingsWithIntegerAttributeWithMaxZero(
			SettingsConfiguration config, IModel model) {
		return getClassSettingsWithIntegerAttributeWithMaxZero(config, model, TypeChecker.INTEGER);
	}

	/**
	 * Finds all class settings that are configured to have no instances and have at
	 * least one attribute of a specific type.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @param typeChecker
	 *            The type checker.
	 * @return All class settings that are configured to have no instances and have
	 *         at least one attribute of a specific type.
	 */
	private Set<ClassSettings> getClassSettingsWithoutInstancesAndIntegerAttribute(SettingsConfiguration config,
			IModel model, TypeChecker typeChecker) {
		if (config == null || model == null || typeChecker == null) {
			throw new IllegalArgumentException();
		}
		Set<ClassSettings> attributeSettingsWithMaxZeroForClassSettings = new HashSet<ClassSettings>();
		for (ClassSettings classSettings : config.getAllClassesSettings()) {
			if (classSettings.getUpperBound() == 0) {
				for (Entry<IAttribute, AttributeSettings> attributeSettingsForAttribute : classSettings
						.getAttributeSettings().entrySet()) {
					IAttribute attribute = attributeSettingsForAttribute.getKey();
					Type attributeType = attribute.type();
					if (typeChecker.isType(attributeType)) {
						attributeSettingsWithMaxZeroForClassSettings.add(classSettings);
						break;
					}
				}
			}
		}
		return attributeSettingsWithMaxZeroForClassSettings;
	}

	/**
	 * Finds all class settings that are configured to have no instances and have at
	 * least one real attribute.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return All class settings that are configured to have no instances and have
	 *         at least one real attribute.
	 */
	protected Set<ClassSettings> getClassSettingsWithoutInstancesAndRealAttribute(SettingsConfiguration config,
			IModel model) {
		return getClassSettingsWithoutInstancesAndIntegerAttribute(config, model, TypeChecker.REAL);
	}

	/**
	 * Finds all class settings that are configured to have no instances and have at
	 * least one string attribute.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return All class settings that are configured to have no instances and have
	 *         at least one string attribute.
	 */
	protected Set<ClassSettings> getClassSettingsWithoutInstancesAndStringAttribute(SettingsConfiguration config,
			IModel model) {
		return getClassSettingsWithoutInstancesAndIntegerAttribute(config, model, TypeChecker.STRING);
	}

	/**
	 * Finds all class settings that are configured to have no instances and have at
	 * least one integer attribute.
	 * 
	 * @param config
	 *            The configuration.
	 * @param model
	 *            The model.
	 * @return All class settings that are configured to have no instances and have
	 *         at least one integer attribute.
	 */
	protected Set<ClassSettings> getClassSettingsWithoutInstancesAndIntegerAttribute(SettingsConfiguration config,
			IModel model) {
		return getClassSettingsWithoutInstancesAndIntegerAttribute(config, model, TypeChecker.INTEGER);
	}

}
