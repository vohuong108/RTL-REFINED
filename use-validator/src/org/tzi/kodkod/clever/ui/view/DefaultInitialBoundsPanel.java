package org.tzi.kodkod.clever.ui.view;

import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.clever.ui.UIElements;

/**
 * Panel for specification of general initial bounds for the CSPs for the bounds
 * tightening problem of UML/OCL instance finder configurations.
 * 
 * @author Jan Prien
 *
 */
final class DefaultInitialBoundsPanel extends AbstractInitialBoundPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -2614666298255373076L;

	/** {@inheritDoc} */
	DefaultInitialBoundsPanel(int initialDefaultLowerBound, int initialDefaultUpperBound) {
		super(null, UIElements.DefalutBoundSpecification_Title, UIElements.DefalutBoundSpecification_Tooltip,
				initialDefaultLowerBound, initialDefaultUpperBound, true, false);
	}

	@Override
	public void enrichIModelCSPVariablesInitialBoundsSpecification(
			IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification) {
		throw new UnsupportedOperationException();
	}

}
