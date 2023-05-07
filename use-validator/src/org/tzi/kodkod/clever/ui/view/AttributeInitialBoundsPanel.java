package org.tzi.kodkod.clever.ui.view;

import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.clever.model2csp.IllegalInitialBoundException;
import org.tzi.kodkod.clever.ui.UIElements;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;

/**
 * Panel for specification of attribute specific initial bounds for the CSPs for
 * the bounds tightening problem of UML/OCL instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
final class AttributeInitialBoundsPanel extends AbstractInitialBoundPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -944429023285755078L;

	/**
	 * The attributes owning class.
	 */
	private final IClass iClass;

	/**
	 * The attribute.
	 */
	private final IAttribute iAttribute;

	/** {@inheritDoc} */
	AttributeInitialBoundsPanel(AttributesDefaultInitialBoundsPanel parent, IClass iClass, IAttribute iAttribute,
			int initialDefaultLowerBound, int initialDefaultUpperBound) {
		super(parent,
				UIElements.ModelElements.Class.Title(iClass.name()) + UIElements.ModelElements.Seperator
						+ UIElements.ModelElements.Attribute.Title(iAttribute.name()),
				UIElements.ModelElements.Attribute.BoundSpecification_Tooltip(iAttribute.name(),
						UIElements.ModelElements.Class.BoundSpecification_TooltipComment(iClass.name())),
				initialDefaultLowerBound, initialDefaultUpperBound, false, true);
		this.iClass = iClass;
		this.iAttribute = iAttribute;
	}

	@Override
	public void enrichIModelCSPVariablesInitialBoundsSpecification(
			IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification) {
		try {
			iModelCSPVariablesInitialBoundsSpecification.setSpecificAttributeBounds(iClass, iAttribute,
					getInitialLowerBound(), getInitialUpperBound());
		} catch (IllegalInitialBoundException e) {
			throw new UnsupportedOperationException(e);
		}

	}

}
