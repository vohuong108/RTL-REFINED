package org.tzi.kodkod.validation;

import java.util.List;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;
import org.tzi.use.uml.ocl.expr.Expression;

/**
 * Service for functionality regarding fixes of violations of validity rules.
 * 
 * @author Jan Prien
 *
 */
public final class FixService {

	/**
	 * Constructs no object
	 */
	private FixService() {

	}

	/**
	 * Enables type string configuration aspects if they are disabled but should be
	 * enabled. Disables type string configuration aspects if they are enabled but
	 * should be disabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param iModel
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model.
	 */
	public static void fixStringSettingsEnabled(final SettingsConfiguration configuration, final IModel iModel,
			List<Expression> invariants) {
		final boolean stringSettingsMustBeEnabled = !ValidationService.stringSettingsMustBeEnabled(configuration,
				iModel, invariants);
		final boolean stringSettingsMustBeDisabled = !ValidationService.stringSettingsMustBeDisabled(configuration,
				iModel, invariants);
		if (stringSettingsMustBeEnabled && stringSettingsMustBeDisabled) {
			throw new UnsupportedOperationException();
		}
		if (stringSettingsMustBeEnabled) {
			configuration.getStringTypeSettings().setEnabled(true);
		}
		if (stringSettingsMustBeDisabled) {
			configuration.getStringTypeSettings().setEnabled(false);
		}
	}

	/**
	 * Enables type integer configuration aspects if they are disabled but should be
	 * enabled. Disables type integer configuration aspects if they are enabled but
	 * should be disabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param iModel
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model.
	 */
	public static void fixIntegerSettingsEnabled(final SettingsConfiguration configuration, final IModel iModel,
			List<Expression> invariants) {
		final boolean integerSettingsMustBeEnabled = !ValidationService.integerSettingsMustBeEnabled(configuration,
				iModel, invariants);
		final boolean integerSettingsMustBeDisabled = !ValidationService.integerSettingsMustBeDisabled(configuration,
				iModel, invariants);
		if (integerSettingsMustBeEnabled && integerSettingsMustBeDisabled) {
			throw new UnsupportedOperationException();
		}
		if (integerSettingsMustBeEnabled) {
			configuration.getIntegerTypeSettings().setEnabled(true);
		}
		if (integerSettingsMustBeDisabled) {
			configuration.getIntegerTypeSettings().setEnabled(false);
		}
	}

	/**
	 * Enables type real configuration aspects if they are disabled but should be
	 * enabled. Disables type real configuration aspects if they are enabled but
	 * should be disabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param iModel
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model.
	 */
	public static void fixRealSettingsEnabled(final SettingsConfiguration configuration, final IModel iModel,
			List<Expression> invariants) {
		final boolean realSettingsMustBeEnabled = !ValidationService.realSettingsMustBeEnabled(configuration, iModel,
				invariants);
		final boolean realSettingsMustBeDisabled = !ValidationService.realSettingsMustBeDisabled(configuration, iModel,
				invariants);
		if (realSettingsMustBeEnabled && realSettingsMustBeDisabled) {
			throw new UnsupportedOperationException();
		}
		if (realSettingsMustBeEnabled) {
			configuration.getRealTypeSettings().setEnabled(true);
		}
		if (realSettingsMustBeDisabled) {
			configuration.getRealTypeSettings().setEnabled(false);
		}
	}

	/**
	 * Enables all type specific configuration aspects if they are disabled but
	 * should be enabled. Disables all type specific configuration aspects if they
	 * are enabled but should be disabled.
	 * 
	 * @param configuration
	 *            The configuration for the UML/OCL model.
	 * @param iModel
	 *            The class diagram of the UML/OCL model.
	 * @param invariants
	 *            The invariants of the UML/OCL model.
	 */
	public static void fixTypeSettingsEnabled(final SettingsConfiguration configuration, final IModel iModel,
			List<Expression> invariants) {
		fixIntegerSettingsEnabled(configuration, iModel, invariants);
		fixStringSettingsEnabled(configuration, iModel, invariants);
		fixRealSettingsEnabled(configuration, iModel, invariants);
	}
}
