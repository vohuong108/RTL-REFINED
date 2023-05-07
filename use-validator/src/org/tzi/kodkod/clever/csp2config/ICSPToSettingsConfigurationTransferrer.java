package org.tzi.kodkod.clever.csp2config;

import org.tzi.kodkod.clever.csp.ICSP;
import org.tzi.kodkod.clever.csp.integer.IntegerDomain;
import org.tzi.kodkod.clever.model2csp.IAssociationReferenciator;
import org.tzi.kodkod.clever.model2csp.IAttributeForClassReference;
import org.tzi.kodkod.clever.model2csp.IAttributeForClassReferenciator;
import org.tzi.kodkod.clever.model2csp.IClassReferenciator;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.use.kodkod.plugin.gui.model.data.AssociationSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.AttributeSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.ClassSettings;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * Transferrer of domains of a variable of a CSP to model elements configuration
 * aspects.
 * 
 * @author Jan Prien
 *
 */
public final class ICSPToSettingsConfigurationTransferrer {

	/**
	 * Constructs an object.
	 */
	public ICSPToSettingsConfigurationTransferrer() {

	}

	/**
	 * Transfers for each model elements configuration aspect the domains of a
	 * variable of a CSP to the aspect.
	 * 
	 * @param csp
	 *            The CSP.
	 * @param config
	 *            The configuration.
	 * @throws TransferException
	 *             If for a model elements configuration aspect the domains of a
	 *             variable of a CSP can not be transferred
	 */
	public void transfer(final ICSP csp, final SettingsConfiguration config) throws TransferException {
		/*
		 * This is implemented for a version of the configurations, where only settings
		 * for non inherited attributes per class can be specified. E.g the abstract
		 * class "Object" has an attribute named "id". Class "Table" extends class
		 * "Object", so the attribute "id" is only inherited. Settings for this
		 * attribute are done on the class "Object" and are applied for all inheriting
		 * classes of "Object".
		 * 
		 * This could be handled differently. Per class could be specific settings for
		 * all attributes of the class. Also for inherited attributes.
		 */
		for (ClassSettings classSettings : config.getAllClassesSettings()) {
			IClass iClass = classSettings.getCls();
			IntegerDomain iClassIntegerDomain = csp.getDomain(new IClassReferenciator(iClass));
			if (iClassIntegerDomain == null) {
				throw new TransferException("CSP has no domain for class \"" + iClass.name() + "\".");
			}
			classSettings.setLowerBound(iClassIntegerDomain.getLowerBound());
			classSettings.setUpperBound(iClassIntegerDomain.getUpperBound());
			for (AttributeSettings attributeSettings : classSettings.getAttributeSettings().values()) {
				IAttribute iAttribute = attributeSettings.getAttribute();
				IntegerDomain iAttributeIntegerDomain = csp.getDomain(
						new IAttributeForClassReferenciator(new IAttributeForClassReference(iClass, iAttribute)));
				if (iAttributeIntegerDomain == null) {
					throw new TransferException("CSP has no domain for attribute \"" + iAttribute.name()
							+ "\" of class \"" + iClass.name() + "\".");
				}
				attributeSettings.setLowerBound(iAttributeIntegerDomain.getLowerBound());
				attributeSettings.setUpperBound(iAttributeIntegerDomain.getUpperBound());
			}
		}
		for (AssociationSettings associationSettings : config.getAllAssociationSettings()) {
			IAssociation iAssociation = associationSettings.getAssociation();
			IntegerDomain iAssociationIntegerDomain = csp.getDomain(new IAssociationReferenciator(iAssociation));
			if (iAssociationIntegerDomain == null) {
				throw new TransferException("CSP has no domain for association \"" + iAssociation.name() + "\".");
			}
			associationSettings.setLowerBound(iAssociationIntegerDomain.getLowerBound());
			associationSettings.setUpperBound(iAssociationIntegerDomain.getUpperBound());
		}
	}

}
