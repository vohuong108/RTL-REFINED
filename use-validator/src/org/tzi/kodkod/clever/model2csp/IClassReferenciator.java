package org.tzi.kodkod.clever.model2csp;

import org.tzi.kodkod.clever.csp.IReferenciator;
import org.tzi.kodkod.model.iface.IClass;

/**
 * Wrapper for an UML class element ({@link IClass}). CSP variables must be
 * linked a model element from the UML/OCL model.
 * 
 * @author Jan Prien
 *
 */
public final class IClassReferenciator implements IReferenciator<IClass> {

	/**
	 * The wrapped class element.
	 */
	private final IClass iClass;

	/**
	 * Constructs an object.
	 * 
	 * @param iClass
	 *            The wrapped class element.
	 */
	public IClassReferenciator(IClass iClass) {
		if (iClass == null) {
			throw new IllegalArgumentException();
		}
		this.iClass = iClass;
	}

	@Override
	public IClass getReference() {
		return iClass;
	}

	@Override
	public String getDerivedString() {
		return "Class." + iClass.name();
	}

	@Override
	public boolean referencesSame(IReferenciator<?> referenciator) {
		return referenciator != null && referenciator instanceof IClassReferenciator
				&& iClass == ((IClassReferenciator) referenciator).iClass;
	}

}
