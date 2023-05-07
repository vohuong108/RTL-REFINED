package org.tzi.kodkod.clever.ui.view;

import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.clever.model2csp.IllegalInitialBoundException;
import org.tzi.kodkod.clever.ui.UIElements;

/**
 * Panel for specification of general association initial bounds for the CSPs
 * for the bounds tightening problem of UML/OCL instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
final class AssociationsDefaultInitialBoundsPanel extends AbstractInitialBoundPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -944429023285755078L;

	/** {@inheritDoc} */
	AssociationsDefaultInitialBoundsPanel(DefaultInitialBoundsPanel parent, int initialDefaultLowerBound,
			int initialDefaultUpperBound) {
		super(parent, UIElements.ModelElements.Association.Title_Plural,
				UIElements.ModelElements.Association.DefaultBoundSpecification_Tooltip(), initialDefaultLowerBound,
				initialDefaultUpperBound, false, true);
	}

	@Override
	public void enrichIModelCSPVariablesInitialBoundsSpecification(
			IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification) {
		try {
			iModelCSPVariablesInitialBoundsSpecification.setDefaultAssociationsBounds(getInitialLowerBound(),
					getInitialUpperBound());
		} catch (IllegalInitialBoundException e) {
			throw new UnsupportedOperationException(e);
		}
	}

}
