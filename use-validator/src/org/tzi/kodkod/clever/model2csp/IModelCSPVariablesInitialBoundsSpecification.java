package org.tzi.kodkod.clever.model2csp;

import java.util.HashMap;
import java.util.Map;

import org.tzi.kodkod.clever.ui.UIElements;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;

/**
 * Initial bounds specification for CSPs for the bounds tightening of UML/OCL
 * models instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
public final class IModelCSPVariablesInitialBoundsSpecification {

	/**
	 * The general (fallback) lower bound. This is used for all model elements, for
	 * that no specific bounds are given.
	 */
	private final int defaultLowerBound;

	/**
	 * The general (fallback) upper bound. This is used for all model elements, for
	 * that no specific bounds are given.
	 */
	private final int defaultUpperBound;

	/**
	 * The UML/OCL model.
	 */
	private final IModel model;

	/**
	 * The general (fallback) lower bound for class elements. This is used for all
	 * class elements, for that no specific bounds are given.
	 */
	private Integer defaultClassLowerBound = null;

	/**
	 * The general (fallback) upper bound for class elements. This is used for all
	 * class elements, for that no specific bounds are given.
	 */
	private Integer defaultClassUpperBound = null;

	/**
	 * The general (fallback) lower bound for attribute elements. This is used for
	 * all attribute elements, for that no specific bounds are given.
	 */
	private Integer defaultAttributeLowerBound = null;

	/**
	 * The general (fallback) upper bound for attribute elements. This is used for
	 * all attribute elements, for that no specific bounds are given.
	 */
	private Integer defaultAttributeUpperBound = null;

	/**
	 * The general (fallback) lower bound for association elements. This is used for
	 * all association elements, for that no specific bounds are given.
	 */
	private Integer defaultAssociationsLowerBound = null;

	/**
	 * The general (fallback) upper bound for association elements. This is used for
	 * all association elements, for that no specific bounds are given.
	 */
	private Integer defaultAssociationsUpperBound = null;

	/**
	 * The lower bounds for class elements.
	 */
	private Map<IClass, Integer> specificClassLowerBounds = new HashMap<>();

	/**
	 * The upper bounds for class elements.
	 */
	private Map<IClass, Integer> specificClassUpperBounds = new HashMap<>();

	/**
	 * The lower bounds for attribute elements.
	 */
	private Map<IClass, Map<IAttribute, Integer>> specificAttributeOfClassLowerBounds = new HashMap<>();

	/**
	 * The upper bounds for attribute elements.
	 */
	private Map<IClass, Map<IAttribute, Integer>> specificAttributeOfClassUpperBounds = new HashMap<>();

	/**
	 * The lower bounds for association elements.
	 */
	private Map<IAssociation, Integer> specificAssociationLowerBounds = new HashMap<>();

	/**
	 * The upper bounds for association elements.
	 */
	private Map<IAssociation, Integer> specificAssociationUpperBounds = new HashMap<>();

	/**
	 * Constructs an object.
	 * 
	 * @param model
	 *            The UML/OCL model.
	 * @param defaultLowerBound
	 *            The general (fallback) lower bound for class elements. This is
	 *            used for all class elements, for that no specific bounds are
	 *            given. This must be at least 0
	 * @param defaultUpperBound
	 *            The general (fallback) upper bound. This is used for all model
	 *            elements, for that no specific bounds are given. This must be at
	 *            least the lower bound.
	 */
	public IModelCSPVariablesInitialBoundsSpecification(final IModel model, final int defaultLowerBound,
			final int defaultUpperBound) {
		if (model == null || defaultLowerBound < 0 || defaultLowerBound > defaultUpperBound) {
			throw new IllegalArgumentException();
		}
		this.defaultLowerBound = defaultLowerBound;
		this.defaultUpperBound = defaultUpperBound;
		this.model = model;
	}

	public int getDefaultLowerBound() {
		return defaultLowerBound;
	}

	public int getDefaultUpperBound() {
		return defaultUpperBound;
	}

	public IModel getModel() {
		return model;
	}

	public Integer getDefaultClassLowerBound() {
		return defaultClassLowerBound;
	}

	/**
	 * Sets {@link #defaultClassLowerBound} and {@link #defaultClassUpperBound}.
	 * {@link #defaultClassUpperBound} must be greater than or equal
	 * {@link #defaultClassLowerBound}.
	 * 
	 * @param defaultClassLowerBound
	 *            New {@link #defaultClassLowerBound}
	 * @param defaultClassUpperBound
	 *            New {@link #defaultClassUpperBound}
	 * @throws IllegalInitialBoundException
	 *             If not {@link #defaultClassLowerBound} is less than or equal
	 *             {@link #defaultClassUpperBound}.
	 */
	public void setDefaultClassesBounds(Integer defaultClassLowerBound, Integer defaultClassUpperBound)
			throws IllegalInitialBoundException {
		if (defaultClassLowerBound == null || defaultClassUpperBound == null) {
			throw new IllegalArgumentException();
		}
		if (defaultClassLowerBound > defaultClassUpperBound) {
			throw new IllegalInitialBoundException(
					UIElements.ModelElements.Class.DefaultLowerBoundGreaterThanDefaultUpperBound());
		}
		this.defaultClassLowerBound = defaultClassLowerBound;
		this.defaultClassUpperBound = defaultClassUpperBound;
	}

	public Integer getDefaultClassUpperBound() {
		return defaultClassUpperBound;
	}

	public Integer getDefaultAttributeLowerBound() {
		return defaultAttributeLowerBound;
	}

	/**
	 * Sets {@link #defaultAttributeLowerBound} and
	 * {@link #defaultAttributeUpperBound}. {@link #defaultAttributeUpperBound} must
	 * be greater than or equal {@link #defaultAttributeLowerBound}.
	 * 
	 * @param defaultAttributeLowerBound
	 *            New {@link #defaultAttributeLowerBound}
	 * @param defaultAttributeUpperBound
	 *            New {@link #defaultAttributeUpperBound}
	 * @throws IllegalInitialBoundException
	 *             If not {@link #defaultAttributeLowerBound} is less than or equal
	 *             {@link #defaultAttributeUpperBound}.
	 */
	public void setDefaultAttributesBounds(Integer defaultAttributeLowerBound, Integer defaultAttributeUpperBound)
			throws IllegalInitialBoundException {
		if (defaultAttributeLowerBound == null || defaultAttributeUpperBound == null) {
			throw new IllegalArgumentException();
		}
		if (defaultAttributeLowerBound > defaultAttributeUpperBound) {
			throw new IllegalInitialBoundException(
					UIElements.ModelElements.Attribute.DefaultLowerBoundGreaterThanDefaultUpperBound());
		}
		this.defaultAttributeLowerBound = defaultAttributeLowerBound;
		this.defaultAttributeUpperBound = defaultAttributeUpperBound;
	}

	public Integer getDefaultAttributeUpperBound() {
		return defaultAttributeUpperBound;
	}

	public Integer getDefaultAssociationLowerBound() {
		return defaultAssociationsLowerBound;
	}

	/**
	 * Sets {@link #defaultAssociationsLowerBound} and
	 * {@link #defaultAssociationsUpperBound}.
	 * {@link #defaultAssociationsUpperBound} must be greater than or equal
	 * {@link #defaultAssociationsLowerBound}.
	 * 
	 * @param defaultAssociationsLowerBound
	 *            New {@link #defaultAssociationsLowerBound}
	 * @param defaultAssociationsUpperBound
	 *            New {@link #defaultAssociationsUpperBound}
	 * @throws IllegalInitialBoundException
	 *             If not {@link #defaultAssociationsLowerBound} is less than or
	 *             equal {@link #defaultAssociationsUpperBound}.
	 */
	public void setDefaultAssociationsBounds(Integer defaultAssociationsLowerBound,
			Integer defaultAssociationsUpperBound) throws IllegalInitialBoundException {
		if (defaultAssociationsLowerBound == null || defaultAssociationsUpperBound == null) {
			throw new IllegalArgumentException();
		}
		if (defaultAssociationsLowerBound > defaultAssociationsUpperBound) {
			throw new IllegalInitialBoundException(
					UIElements.ModelElements.Association.DefaultLowerBoundGreaterThanDefaultUpperBound());
		}
		this.defaultAssociationsLowerBound = defaultAssociationsLowerBound;
		this.defaultAssociationsUpperBound = defaultAssociationsUpperBound;
	}

	public Integer getDefaultAssociationUpperBound() {
		return defaultAssociationsUpperBound;
	}

	/**
	 * Sets the bounds for a specific class element.
	 * 
	 * @param iClass
	 *            The class element.
	 * @param specificClassLowerBound
	 *            The lower bound.
	 * @param specificClassUpperBound
	 *            The upper bound.
	 * @throws IllegalInitialBoundException
	 *             If the lower bound is not less than or equal the upper bound.
	 */
	public void setSpecificClassBounds(IClass iClass, Integer specificClassLowerBound, Integer specificClassUpperBound)
			throws IllegalInitialBoundException {
		if (specificClassLowerBound == null || specificClassUpperBound == null || iClass == null
				|| !this.model.classes().contains(iClass)) {
			throw new IllegalArgumentException();
		}
		if (specificClassLowerBound > specificClassUpperBound) {
			throw new IllegalInitialBoundException(UIElements.ModelElements.Class.LowerBoundGreaterThanUpperBound());
		}
		this.specificClassLowerBounds.put(iClass, specificClassLowerBound);
		this.specificClassUpperBounds.put(iClass, specificClassUpperBound);
	}

	/**
	 * Sets the bounds for a specific attribute element.
	 * 
	 * @param iClass
	 *            The attributes owning class element.
	 * @param iAttribute
	 *            The attribute element.
	 * @param specificAttributeLowerBound
	 *            The lower bound.
	 * @param specificAttributeUpperBound
	 *            The upper bound.
	 * @throws IllegalInitialBoundException
	 *             If the lower bound is not less than or equal the upper bound.
	 */
	public void setSpecificAttributeBounds(IClass iClass, IAttribute iAttribute, Integer specificAttributeLowerBound,
			Integer specificAttributeUpperBound) throws IllegalInitialBoundException {
		if (specificAttributeLowerBound == null || specificAttributeUpperBound == null || iClass == null
				|| iAttribute == null || !iClass.allAttributes().contains(iAttribute)) {
			throw new IllegalArgumentException();
		}
		if (specificAttributeLowerBound > specificAttributeUpperBound) {
			throw new IllegalInitialBoundException(
					UIElements.ModelElements.Attribute.LowerBoundGreaterThanUpperBound());
		}
		Map<IAttribute, Integer> specificAttributeLowerBounds = this.specificAttributeOfClassLowerBounds.get(iClass);
		if (specificAttributeLowerBounds == null) {
			specificAttributeLowerBounds = new HashMap<>();
			specificAttributeOfClassLowerBounds.put(iClass, specificAttributeLowerBounds);
		}
		specificAttributeLowerBounds.put(iAttribute, specificAttributeLowerBound);
		Map<IAttribute, Integer> specificAttributeUpperBounds = this.specificAttributeOfClassUpperBounds.get(iClass);
		if (specificAttributeUpperBounds == null) {
			specificAttributeUpperBounds = new HashMap<>();
			specificAttributeOfClassUpperBounds.put(iClass, specificAttributeUpperBounds);
		}
		specificAttributeUpperBounds.put(iAttribute, specificAttributeUpperBound);
	}

	/**
	 * Sets the bounds for a specific association element.
	 * 
	 * @param iAssociation
	 *            The association element.
	 * @param specificAssociationLowerBound
	 *            The lower bound.
	 * @param specificAssociationUpperBound
	 *            The upper bound.
	 * @throws IllegalInitialBoundException
	 *             If the lower bound is not less than or equal the upper bound.
	 */
	public void setSpecificAssociationBounds(IAssociation iAssociation, Integer specificAssociationLowerBound,
			Integer specificAssociationUpperBound) throws IllegalInitialBoundException {
		if (specificAssociationLowerBound == null || specificAssociationUpperBound == null || iAssociation == null
				|| !this.model.associations().contains(iAssociation)) {
			throw new IllegalArgumentException();
		}
		if (specificAssociationLowerBound > specificAssociationUpperBound) {
			throw new IllegalInitialBoundException(
					UIElements.ModelElements.Association.LowerBoundGreaterThanUpperBound());
		}
		this.specificAssociationLowerBounds.put(iAssociation, specificAssociationLowerBound);
		this.specificAssociationUpperBounds.put(iAssociation, specificAssociationUpperBound);
	}

	/**
	 * Returns the lower bound for a class element. Returns the general class
	 * elements lower bound if no specific bound is given. Returns the general lower
	 * bound if no general class elements lower bound is given.
	 * 
	 * @param iClass
	 *            The class element.
	 * @return The lower bound.
	 */
	public Integer getSpecificClassLowerBound(IClass iClass) {
		if (iClass == null || !this.model.classes().contains(iClass)) {
			throw new IllegalArgumentException();
		}
		Integer bound = specificClassLowerBounds.get(iClass);
		if (bound == null) {
			bound = defaultClassLowerBound;
			if (bound == null) {
				bound = defaultLowerBound;
			}
		}
		return bound;
	}

	/**
	 * Returns the upper bound for a class element. Returns the general class
	 * elements upper bound if no specific bound is given. Returns the general upper
	 * bound if no general class elements upper bound is given.
	 * 
	 * @param iClass
	 *            The class element.
	 * @return The upper bound.
	 */
	public Integer getSpecificClassUpperBound(IClass iClass) {
		if (iClass == null || !this.model.classes().contains(iClass)) {
			throw new IllegalArgumentException();
		}
		Integer bound = specificClassUpperBounds.get(iClass);
		if (bound == null) {
			bound = defaultClassUpperBound;
			if (bound == null) {
				bound = defaultUpperBound;
			}
		}
		return bound;
	}

	/**
	 * Returns the lower bound for a attribute element. Returns the general
	 * attribute elements lower bound if no specific bound is given. Returns the
	 * general lower bound if no general attribute elements lower bound is given.
	 * 
	 * @param iClass
	 *            The attributes owning class element.
	 * @param iAttribute
	 *            The attribute element.
	 * @return The lower bound.
	 */
	public Integer getSpecificAttributeLowerBound(IClass iClass, IAttribute iAttribute) {
		if (iClass == null || iAttribute == null || !iClass.allAttributes().contains(iAttribute)) {
			throw new IllegalArgumentException();
		}
		Integer bound = null;
		Map<IAttribute, Integer> specificAttributeLowerBounds = specificAttributeOfClassLowerBounds.get(iClass);
		if (specificAttributeLowerBounds != null) {
			bound = specificAttributeLowerBounds.get(iAttribute);
		}
		if (bound == null) {
			bound = defaultAttributeLowerBound;
			if (bound == null) {
				bound = defaultLowerBound;
			}
		}

		return bound;
	}

	/**
	 * Returns the upper bound for a attribute element. Returns the general
	 * attribute elements upper bound if no specific bound is given. Returns the
	 * general upper bound if no general attribute elements upper bound is given.
	 * 
	 * @param iClass
	 *            The attributes owning class element.
	 * @param iAttribute
	 *            The attribute element.
	 * @return The upper bound.
	 */
	public Integer getSpecificAttributeUpperBound(IClass iClass, IAttribute iAttribute) {
		if (iClass == null || iAttribute == null || !iClass.allAttributes().contains(iAttribute)) {
			throw new IllegalArgumentException();
		}
		Integer bound = null;
		Map<IAttribute, Integer> specificAttributeUpperBounds = specificAttributeOfClassUpperBounds.get(iClass);
		if (specificAttributeUpperBounds != null) {
			bound = specificAttributeUpperBounds.get(iAttribute);
		}
		if (bound == null) {
			bound = defaultAttributeUpperBound;
			if (bound == null) {
				bound = defaultUpperBound;
			}
		}
		return bound;
	}

	/**
	 * Returns the lower bound for a association element. Returns the general
	 * association elements lower bound if no specific bound is given. Returns the
	 * general lower bound if no general association elements lower bound is given.
	 * 
	 * @param iAssociation
	 *            The association element.
	 * @return The lower bound.
	 */
	public Integer getSpecificAssociationLowerBound(IAssociation iAssociation) {
		if (iAssociation == null || !this.model.associations().contains(iAssociation)) {
			throw new IllegalArgumentException();
		}
		Integer bound = specificAssociationLowerBounds.get(iAssociation);
		if (bound == null) {
			bound = defaultAssociationsLowerBound;
			if (bound == null) {
				bound = defaultLowerBound;
			}
		}
		return bound;
	}

	/**
	 * Returns the upper bound for a association element. Returns the general
	 * association elements upper bound if no specific bound is given. Returns the
	 * general upper bound if no general association elements upper bound is given.
	 * 
	 * @param iAssociation
	 *            The association element.
	 * @return The upper bound.
	 */
	public Integer getSpecificAssociationUpperBound(IAssociation iAssociation) {
		if (iAssociation == null || !this.model.associations().contains(iAssociation)) {
			throw new IllegalArgumentException();
		}
		Integer bound = specificAssociationUpperBounds.get(iAssociation);
		if (bound == null) {
			bound = defaultAssociationsUpperBound;
			if (bound == null) {
				bound = defaultUpperBound;
			}
		}
		return bound;
	}

	@Override
	public String toString() {
		String string = "";
		for (IClass iClass : model.classes()) {
			string += UIElements.ModelElements.Class.Title + UIElements.ModelElements.Seperator + iClass.name() + ".["
					+ getSpecificClassLowerBound(iClass) + "," + getSpecificClassUpperBound(iClass) + "]\n";
		}
		for (IClass iClass : model.classes()) {
			for (IAttribute iAttribute : iClass.allAttributes()) {
				string += UIElements.ModelElements.Class.Title + UIElements.ModelElements.Seperator
						+ iAttribute.owner().name() + UIElements.ModelElements.Seperator
						+ UIElements.ModelElements.Attribute.Title + UIElements.ModelElements.Seperator
						+ iAttribute.name() + ".[" + getSpecificAttributeLowerBound(iClass, iAttribute) + ","
						+ getSpecificAttributeUpperBound(iClass, iAttribute) + "]\n";
			}
		}
		for (IAssociation iAssociation : model.associations()) {
			string += UIElements.ModelElements.Association.Title + UIElements.ModelElements.Seperator
					+ iAssociation.name() + ".[" + getSpecificAssociationLowerBound(iAssociation) + ","
					+ getSpecificAssociationUpperBound(iAssociation) + "]\n";
		}
		return string;
	}

}
