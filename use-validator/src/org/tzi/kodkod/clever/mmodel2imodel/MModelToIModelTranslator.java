package org.tzi.kodkod.clever.mmodel2imodel;

import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAssociationEnd;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.iface.IModelElement;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.mm.MAttribute;
import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.mm.MClassifier;

/**
 * Translator for translation of properties of model structures.
 * 
 * @author Jan Prien
 *
 */
public final class MModelToIModelTranslator {

	/**
	 * The model from which the translation results a read.
	 */
	private final IModel model;

	/**
	 * Constructs an object.
	 * 
	 * @param model
	 *            The model from which the translation results a read.
	 */
	public MModelToIModelTranslator(IModel model) {
		if (model == null) {
			throw new IllegalArgumentException();
		}
		this.model = model;
	}

	/**
	 * Translates an {@code MClassifier} to an {@code IModelElement} from
	 * {@code MModelToIModelTranslator#model}
	 * 
	 * @param mClassifier
	 *            The property to translate.
	 * @return The translated property.
	 * @throws NoMappingFoundException
	 *             If in {@code MModelToIModelTranslator#model} the property can not
	 *             be found.
	 */
	public IModelElement getIModelElement(MClassifier mClassifier) throws NoMappingFoundException {
		if (mClassifier instanceof MAssociation) {
			IModelElement ret = model.getAssociation(mClassifier.name());
			if (ret == null) {
				throw new NoMappingFoundException();
			}
			return ret;
		} else if (mClassifier instanceof MClass) {
			IModelElement ret = model.getClass(mClassifier.name());
			if (ret == null) {
				throw new NoMappingFoundException();
			}
			return ret;
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * Translates a {@code MAttribute} to an {@code IAttribute} from a class.
	 * 
	 * @param iClass
	 *            The class for the {@code IAttribute}.
	 * @param mAttribute
	 *            The attribute to translate.
	 * @return The attribute.
	 * @throws NoMappingFoundException
	 *             If in {@code IAttribute} the attribute can not be found.
	 */
	public IAttribute getIAttribute(final IClass iClass, MAttribute mAttribute) throws NoMappingFoundException {
		IAttribute iAttribute = iClass.getAttribute(mAttribute.name());
		if (iAttribute == null) {
			throw new NoMappingFoundException();
		}
		return iAttribute;
	}

	/**
	 * Translates class name to a class from {@code MModelToIModelTranslator#model}
	 * 
	 * @param iClassName
	 *            The name of the class.
	 * @return The class.
	 * @throws NoMappingFoundException
	 *             If in {@code MModelToIModelTranslator#model} the class name can
	 *             not be found.
	 */
	public IClass getIClass(final String iClassName) throws NoMappingFoundException {
		IClass iClass = model.getClass(iClassName);
		if (iClass == null) {
			throw new NoMappingFoundException();
		}
		return iClass;
	}

	/**
	 * Translates association end to an {@code IAssociation}.
	 * 
	 * @param iClass
	 *            The class of the assiation end.
	 * @param destination
	 *            The name of the association end.
	 * @return The association.
	 * @throws NoMappingFoundException
	 *             If in {@code MModelToIModelTranslator#model} the association can
	 *             not be found.
	 */
	public IAssociation getIAssociation(IClass iClass, String destination) throws NoMappingFoundException {
		for (IAssociation iAssociation : model.associations()) {
			for (IAssociationEnd iAssociationEnd : iAssociation.associationEnds()) {
				if (iClass == iAssociationEnd.associatedClass() && destination.equals(iAssociationEnd.name())) {
					return iAssociation;
				}
			}
		}
		throw new NoMappingFoundException();
	}

}
