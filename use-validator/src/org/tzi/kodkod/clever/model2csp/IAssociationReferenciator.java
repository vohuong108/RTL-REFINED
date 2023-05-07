package org.tzi.kodkod.clever.model2csp;

import org.tzi.kodkod.clever.csp.IReferenciator;
import org.tzi.kodkod.model.iface.IAssociation;

/**
 * Wrapper for an UML association element ({@link IAssociation}). CSP variables
 * must be linked a model element from the UML/OCL model.
 * 
 * @author Jan Prien
 *
 */
public final class IAssociationReferenciator implements IReferenciator<IAssociation> {

	/**
	 * The wrapped association element.
	 */
	private final IAssociation iAssociation;

	/**
	 * Constructs an object.
	 * 
	 * @param iClass
	 *            The wrapped association element.
	 */
	public IAssociationReferenciator(IAssociation iAssociation) {
		if (iAssociation == null) {
			throw new IllegalArgumentException();
		}
		this.iAssociation = iAssociation;
	}

	@Override
	public IAssociation getReference() {
		return iAssociation;
	}

	@Override
	public String getDerivedString() {
		return "Association." + iAssociation.name();
	}

	@Override
	public boolean referencesSame(IReferenciator<?> referenciator) {
		return referenciator != null && referenciator instanceof IAssociationReferenciator
				&& iAssociation == ((IAssociationReferenciator) referenciator).iAssociation;
	}

}
