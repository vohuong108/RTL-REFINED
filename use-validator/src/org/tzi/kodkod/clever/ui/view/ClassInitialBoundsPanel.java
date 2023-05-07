package org.tzi.kodkod.clever.ui.view;

import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.clever.model2csp.IllegalInitialBoundException;
import org.tzi.kodkod.clever.ui.UIElements;
import org.tzi.kodkod.model.iface.IClass;

/**
 * Panel for specification of class specific initial bounds for the CSPs for the
 * bounds tightening problem of UML/OCL instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
final class ClassInitialBoundsPanel extends AbstractInitialBoundPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -944429023285755078L;

	/**
	 * The class.
	 */
	private final IClass iClass;

	/** {@inheritDoc} */
	ClassInitialBoundsPanel(ClassesDefaultInitialBoundsPanel parent, IClass iClass, int initialDefaultLowerBound,
			int initialDefaultUpperBound) {
		super(parent, UIElements.ModelElements.Class.Title(iClass.name()),
				UIElements.ModelElements.Class.BoundSpecification_Tooltip(iClass.name()), initialDefaultLowerBound,
				initialDefaultUpperBound, false, true);
		this.iClass = iClass;
	}

	@Override
	public void enrichIModelCSPVariablesInitialBoundsSpecification(
			IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification) {
		try {
			iModelCSPVariablesInitialBoundsSpecification.setSpecificClassBounds(iClass, getInitialLowerBound(),
					getInitialUpperBound());
		} catch (IllegalInitialBoundException e) {
			throw new UnsupportedOperationException(e);
		}
	}

}
