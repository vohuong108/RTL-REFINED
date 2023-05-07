package org.tzi.kodkod.clever.model2csp;

import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;

/**
 * Wrapper for an UML attribute element. With the current data structure
 * ({@link IAttribute}) for UML attributes, one attribute object is linked from
 * multiple classes. For the CSPs for the bounds tightening of UML/OCL models
 * instance finder configurations, for each class for each attribute an own
 * instance is needed. This class can be used for those instances.
 * 
 * @author Jan Prien
 *
 */
public final class IAttributeForClassReference {

	/**
	 * The attribute owning class element.
	 */
	private final IClass iClass;

	/**
	 * The wrapped attribute element.
	 */
	private final IAttribute iAttribute;

	/**
	 * Constructs an object.
	 * 
	 * @param iClass
	 *            The attribute owning class element.
	 * @param iAttribute
	 *            The wrapped attribute element.
	 */
	public IAttributeForClassReference(IClass iClass, IAttribute iAttribute) {
		if (iClass == null || iAttribute == null) {
			throw new IllegalArgumentException();
		}
		this.iClass = iClass;
		this.iAttribute = iAttribute;
	}

	public IClass getIClass() {
		return iClass;
	}

	public IAttribute getIAttribute() {
		return iAttribute;
	}

	@Override
	public String toString() {
		return "Class." + iClass.name() + ".Attribute." + iAttribute.name();
	}

	/**
	 * Return whether an other wrapper wraps the same elements.
	 * 
	 * @param reference
	 *            The other wrapper.
	 * @return Whether an other wrapper wraps the same elements.
	 */
	public boolean referencesSame(IAttributeForClassReference reference) {
		return reference != null && iClass == reference.iClass && iAttribute == reference.iAttribute;
	}

}
