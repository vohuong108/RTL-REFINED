package org.tzi.kodkod.clever.ui.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAttribute;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.iface.IModel;

/**
 * The panel for specification of initial bounds.
 * 
 * @author Jan Prien
 *
 */
final class InitialBoundsPanel extends AbstractCleverGenerationJPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1369590963311791425L;

	/**
	 * The UML/OCL model.
	 */
	private final IModel model;

	/**
	 * The general initial lower bound.
	 */
	private final int initialDefaultLowerBound;

	/**
	 * The general initial upper bound.
	 */
	private final int initialDefaultUpperBound;

	/**
	 * The panel for the most specific initial bounds specification. The panels for
	 * more precise initial bounds specifications are children in the parent child
	 * hierarchy on this object.
	 */
	private DefaultInitialBoundsPanel defaultInitialBoundsPanel;

	/**
	 * The panel for the general class initial bounds specification. The panels for
	 * more precise initial bounds specifications are children in the parent child
	 * hierarchy on this object.
	 */
	private ClassesDefaultInitialBoundsPanel classesDefaultInitialBoundsPanel;

	/**
	 * The panel for the general attribute initial bounds specification. The panels
	 * for more precise initial bounds specifications are children in the parent
	 * child hierarchy on this object.
	 */
	private AttributesDefaultInitialBoundsPanel attributesDefaultInitialBoundsPanel;

	/**
	 * The panel for the general association initial bounds specification. The
	 * panels for more precise initial bounds specifications are children in the
	 * parent child hierarchy on this object.
	 */
	private AssociationsDefaultInitialBoundsPanel associationsDefaultInitialBoundsPanel;

	/**
	 * The panels for the class specific initial bounds specification. The panels
	 * for more precise initial bounds specifications are children in the parent
	 * child hierarchy on this object.
	 */
	private List<ClassInitialBoundsPanel> classInitialBoundsPanels;

	/**
	 * The panels for the attribute specific initial bounds specification. The
	 * panels for more precise initial bounds specifications are children in the
	 * parent child hierarchy on this object.
	 */
	private List<AttributeInitialBoundsPanel> attributeInitialBoundsPanels;

	/**
	 * The panels for the association specific initial bounds specification. The
	 * panels for more precise initial bounds specifications are children in the
	 * parent child hierarchy on this object.
	 */
	private List<AssociationInitialBoundsPanel> associationInitialBoundsPanels;

	/**
	 * Constructs an object.
	 * 
	 * @param model
	 *            The UML/OCL model.
	 * @param initialDefaultLowerBound
	 *            The general initial lower bound.
	 * @param initialDefaultUpperBound
	 *            The general initial upper bound.
	 */
	public InitialBoundsPanel(IModel model, int initialDefaultLowerBound, int initialDefaultUpperBound) {
		if (model == null) {
			throw new IllegalArgumentException();
		}
		this.model = model;
		this.initialDefaultLowerBound = initialDefaultLowerBound;
		this.initialDefaultUpperBound = initialDefaultUpperBound;
		this.initGUI();
	}

	@Override
	void initGUI() {
		this.setLayout(new BorderLayout());
		final JPanel initialBoundsPanel = new JPanel();
		initialBoundsPanel.setLayout(new BoxLayout(initialBoundsPanel, BoxLayout.PAGE_AXIS));
		this.add(initialBoundsPanel, BorderLayout.NORTH);

		defaultInitialBoundsPanel = new DefaultInitialBoundsPanel(initialDefaultLowerBound, initialDefaultUpperBound);
		initialBoundsPanel.add(defaultInitialBoundsPanel);
		classesDefaultInitialBoundsPanel = new ClassesDefaultInitialBoundsPanel(defaultInitialBoundsPanel,
				initialDefaultLowerBound, initialDefaultUpperBound);
		initialBoundsPanel.add(classesDefaultInitialBoundsPanel);
		attributesDefaultInitialBoundsPanel = new AttributesDefaultInitialBoundsPanel(defaultInitialBoundsPanel,
				initialDefaultLowerBound, initialDefaultUpperBound);
		initialBoundsPanel.add(attributesDefaultInitialBoundsPanel);
		associationsDefaultInitialBoundsPanel = new AssociationsDefaultInitialBoundsPanel(defaultInitialBoundsPanel,
				initialDefaultLowerBound, initialDefaultUpperBound);
		initialBoundsPanel.add(associationsDefaultInitialBoundsPanel);
		classInitialBoundsPanels = new ArrayList<>();
		for (IClass iClass : model.classes()) {
			ClassInitialBoundsPanel classInitialBoundsPanel = new ClassInitialBoundsPanel(
					classesDefaultInitialBoundsPanel, iClass, initialDefaultLowerBound, initialDefaultUpperBound);
			classInitialBoundsPanels.add(classInitialBoundsPanel);
			initialBoundsPanel.add(classInitialBoundsPanel);
		}
		attributeInitialBoundsPanels = new ArrayList<>();
		for (IClass iClass : model.classes()) {
			for (IAttribute iAttribute : iClass.allAttributes()) {
				AttributeInitialBoundsPanel attributeInitialBoundsPanel = new AttributeInitialBoundsPanel(
						attributesDefaultInitialBoundsPanel, iClass, iAttribute, initialDefaultLowerBound,
						initialDefaultUpperBound);
				attributeInitialBoundsPanels.add(attributeInitialBoundsPanel);
				initialBoundsPanel.add(attributeInitialBoundsPanel);
			}
		}
		associationInitialBoundsPanels = new ArrayList<>();
		for (IAssociation iAssociation : model.associations()) {
			AssociationInitialBoundsPanel associationInitialBoundsPanel = new AssociationInitialBoundsPanel(
					associationsDefaultInitialBoundsPanel, iAssociation, initialDefaultLowerBound,
					initialDefaultUpperBound);
			associationInitialBoundsPanels.add(associationInitialBoundsPanel);
			initialBoundsPanel.add(associationInitialBoundsPanel);
		}
	}

	/**
	 * Creates an initial bounds specification object with the specifiactions in the
	 * GUI.
	 * 
	 * @return An initial bounds specification object with the specifiactions in the
	 *         GUI.
	 */
	public IModelCSPVariablesInitialBoundsSpecification deriveIModelCSPVariablesInitialBoundsSpecification() {
		IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification = new IModelCSPVariablesInitialBoundsSpecification(
				model, defaultInitialBoundsPanel.getInitialLowerBound(),
				defaultInitialBoundsPanel.getInitialUpperBound());
		Set<AbstractInitialBoundPanel> initialBoundPanels = new HashSet<>();
		initialBoundPanels.add(classesDefaultInitialBoundsPanel);
		initialBoundPanels.add(attributesDefaultInitialBoundsPanel);
		initialBoundPanels.add(associationsDefaultInitialBoundsPanel);
		initialBoundPanels.addAll(classInitialBoundsPanels);
		initialBoundPanels.addAll(attributeInitialBoundsPanels);
		initialBoundPanels.addAll(associationInitialBoundsPanels);
		for (AbstractInitialBoundPanel initialBoundPanel : initialBoundPanels) {
			initialBoundPanel
					.enrichIModelCSPVariablesInitialBoundsSpecification(iModelCSPVariablesInitialBoundsSpecification);
		}
		return iModelCSPVariablesInitialBoundsSpecification;
	}

}
