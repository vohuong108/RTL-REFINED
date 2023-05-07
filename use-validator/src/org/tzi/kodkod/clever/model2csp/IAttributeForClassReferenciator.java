package org.tzi.kodkod.clever.model2csp;

import org.tzi.kodkod.clever.csp.IReferenciator;

/**
 * Wrapper for an UML attribute element ({@link IAttributeForClassReference}).
 * CSP variables must be linked a model element from the UML/OCL model.
 * 
 * @author Jan Prien
 *
 */
public final class IAttributeForClassReferenciator implements IReferenciator<IAttributeForClassReference> {

	/**
	 * The wrapped attribute element.
	 */
	private final IAttributeForClassReference reference;

	/**
	 * Constructs an object.
	 * 
	 * @param iClass
	 *            The wrapped attribute element.
	 */
	public IAttributeForClassReferenciator(IAttributeForClassReference reference) {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		this.reference = reference;
	}

	@Override
	public IAttributeForClassReference getReference() {
		return reference;
	}

	@Override
	public String getDerivedString() {
		return reference.toString();
	}

	@Override
	public boolean referencesSame(IReferenciator<?> referenciator) {
		return referenciator != null && referenciator instanceof IAttributeForClassReferenciator
				&& reference.referencesSame(((IAttributeForClassReferenciator) referenciator).reference);
	}

}
