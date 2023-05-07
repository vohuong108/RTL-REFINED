package org.tzi.kodkod.clever.ui.view;

import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.clever.model2csp.IllegalInitialBoundException;
import org.tzi.kodkod.clever.ui.UIElements;
import org.tzi.kodkod.model.iface.IAssociation;

/**
 * Panel for specification of association specific initial bounds for the CSPs
 * for the bounds tightening problem of UML/OCL instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
final class AssociationInitialBoundsPanel extends AbstractInitialBoundPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -944429023285755078L;

	/**
	 * The association.
	 */
	private final IAssociation iAssociation;

	/** {@inheritDoc} */
	AssociationInitialBoundsPanel(AssociationsDefaultInitialBoundsPanel parent, IAssociation iAssociation,
			int initialDefaultLowerBound, int initialDefaultUpperBound) {
		super(parent, UIElements.ModelElements.Association.Title(iAssociation.name()),
				UIElements.ModelElements.Association.BoundSpecification_Tooltip(iAssociation.name()),
				initialDefaultLowerBound, initialDefaultUpperBound, false, true);
		this.iAssociation = iAssociation;
	}

	@Override
	public void enrichIModelCSPVariablesInitialBoundsSpecification(
			IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification) {
		try {
			iModelCSPVariablesInitialBoundsSpecification.setSpecificAssociationBounds(iAssociation,
					getInitialLowerBound(), getInitialUpperBound());
		} catch (IllegalInitialBoundException e) {
			throw new UnsupportedOperationException(e);
		}
	}

}
