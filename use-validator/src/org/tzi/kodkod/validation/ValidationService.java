package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Service for functionality regarding validation of validity rules.
 * 
 * @author Jan Prien
 *
 */
public final class ValidationService {

	/**
	 * Constructs no object.
	 */
	private ValidationService() {
		throw new UnsupportedOperationException("No instance allowed");
	}

	/**
	 * Validates whether type integer configuration aspects are disabled but should
	 * be enabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model
	 * @return Whether type integer configuration aspects are disabled but should be
	 *         enabled.
	 */
	public static boolean integerSettingsMustBeEnabled(final SettingsConfiguration config, final IModel model,
			List<Expression> invariants) {
		return new IntegerSettingsNessessaryValidator().isSatisfied(config, model, invariants);
	}

	/**
	 * Validates whether type string configuration aspects are enabled but should be
	 * disabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model
	 * @return Whether type string configuration aspects are enabled but should be
	 *         disabled.
	 */
	public static boolean integerSettingsMustBeDisabled(final SettingsConfiguration config, final IModel model,
			List<Expression> invariants) {
		return new IntegerSettingsUnnessessaryValidator().isSatisfied(config, model, invariants);
	}

	/**
	 * Validates whether type string configuration aspects are disabled but should
	 * be enabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model
	 * @return
	 */
	public static boolean stringSettingsMustBeEnabled(final SettingsConfiguration config, final IModel model,
			List<Expression> invariants) {
		return new StringSettingsNessessaryValidator().isSatisfied(config, model, invariants);
	}

	/**
	 * Validates whether type string configuration aspects are enabled but should be
	 * disabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model
	 * @return Whether type string configuration aspects are enabled but should be
	 *         disabled.
	 */
	public static boolean stringSettingsMustBeDisabled(final SettingsConfiguration config, final IModel model,
			List<Expression> invariants) {
		return new StringSettingsUnnessessaryValidator().isSatisfied(config, model, invariants);
	}

	/**
	 * Validates whether type real configuration aspects are disabled but should be
	 * enabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model
	 * @return Whether type real configuration aspects are disabled but should be
	 *         enabled.
	 */
	public static boolean realSettingsMustBeEnabled(final SettingsConfiguration config, final IModel model,
			List<Expression> invariants) {
		return new RealSettingsNessessaryValidator().isSatisfied(config, model, invariants);
	}

	/**
	 * Validates whether type real configuration aspects are enabled but should be
	 * disabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param model
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model
	 * @return Whether type real configuration aspects are enabled but should be
	 *         disabled.
	 */
	public static boolean realSettingsMustBeDisabled(final SettingsConfiguration config, final IModel model,
			List<Expression> invariants) {
		return new RealSettingsUnnessessaryValidator().isSatisfied(config, model, invariants);
	}

}
