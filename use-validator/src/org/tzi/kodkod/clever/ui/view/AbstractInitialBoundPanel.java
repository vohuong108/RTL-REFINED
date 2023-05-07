package org.tzi.kodkod.clever.ui.view;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.tzi.kodkod.clever.model2csp.IModelCSPVariablesInitialBoundsSpecification;

/**
 * Panel for specification of initial bounds for one configuration aspect of the
 * CSPs for the bounds tightening problem of UML/OCL instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
abstract class AbstractInitialBoundPanel extends AbstractCleverGenerationJPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 5097677662958772891L;

	/**
	 * The textual representation of the configuration aspect.
	 */
	private final String title;

	/**
	 * The tooltip on this panel.
	 */
	private final String tooltipText;

	/**
	 * The initial lower bound.
	 */
	private int initialLowerBound;

	/**
	 * The initial upper bound.
	 */
	private int initialUpperBound;

	/**
	 * Whether the specification are initially enabled.
	 */
	private boolean boundSettingEna;

	/**
	 * Whether the specification can be disabled.
	 */
	private final boolean deactivatable;

	/**
	 * The JSpinner for the lower bound specification. The value can be from 0 up to
	 * the value of {@link #initialUpperBoundSpinner}.
	 */
	private JSpinner initialLowerBoundSpinner;

	/**
	 * The JSpinner for the upper bound specification. The value must be at least
	 * the value from {@link #initialLowerBoundSpinner}.
	 */
	private JSpinner initialUpperBoundSpinner;

	/**
	 * The panel for specification of more general initial bounds for one
	 * configuration aspect of the CSPs for the bounds tightening problem of UML/OCL
	 * instance finder configurations. If this specification is not enabled and the
	 * specified values of the parent change, then the values are adapted.
	 */
	private final AbstractInitialBoundPanel parent;

	/**
	 * The panel for specification of more precise initial bounds for one
	 * configuration aspect of the CSPs for the bounds tightening problem of UML/OCL
	 * instance finder configurations. If the specified values change, then the
	 * values are adapted on the children, if they are not enabled.
	 */
	private final Set<AbstractInitialBoundPanel> children = new HashSet<>();

	/**
	 * Constructs an object.
	 * 
	 * Adds the object as child on the parent.
	 * 
	 * @param parent
	 *            The panel for specification of more general initial bounds
	 *            ({@link #parent}).
	 * @param title
	 *            The textual representation of the configuration aspect.
	 * @param tooltipText
	 *            The tooltip on the panel.
	 * @param initialDefaultLowerBound
	 *            The initial lower bound.
	 * @param initialDefaultUpperBound
	 *            The initial upper bound.
	 * @param enabled
	 *            Whether the specification are initially enabled.
	 * @param deactivatable
	 *            Whether the specification can be disabled.
	 */
	public AbstractInitialBoundPanel(AbstractInitialBoundPanel parent, String title, String tooltipText,
			int initialDefaultLowerBound, int initialDefaultUpperBound, boolean enabled, boolean deactivatable) {
		if (title == null || tooltipText == null || initialDefaultLowerBound < 0
				|| initialDefaultLowerBound > initialDefaultUpperBound || !enabled && !deactivatable
				|| deactivatable && parent == null) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
		this.title = title;
		this.tooltipText = tooltipText;
		this.initialLowerBound = initialDefaultLowerBound;
		this.initialUpperBound = initialDefaultUpperBound;
		this.boundSettingEna = enabled;
		this.deactivatable = deactivatable;
		if (parent != null) {
			parent.addChild(this);
		}
		this.initGUI();
	}

	@Override
	void initGUI() {
		final AbstractInitialBoundPanel dis = this;
		this.setToolTipText(tooltipText);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		if (deactivatable) {
			final JCheckBox enabledCheckbox = new JCheckBox();
			enabledCheckbox.setSelected(boundSettingEna);
			this.add(enabledCheckbox);
			enabledCheckbox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					dis.setBoundSettingEnabled(e.getStateChange() == ItemEvent.SELECTED);
				}
			});
		}
		initialLowerBoundSpinner = new JSpinner(new SpinnerNumberModel(initialLowerBound, 0, initialUpperBound, 1));
		((JSpinner.DefaultEditor) initialLowerBoundSpinner.getEditor()).getTextField()
				.setColumns(("" + Integer.MAX_VALUE).length());
		initialUpperBoundSpinner = new JSpinner(
				new SpinnerNumberModel(initialUpperBound, initialLowerBound, Integer.MAX_VALUE, 1));
		((JSpinner.DefaultEditor) initialUpperBoundSpinner.getEditor()).getTextField()
				.setColumns(("" + Integer.MAX_VALUE).length());
		initialLowerBoundSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				dis.updateBounds((int) initialLowerBoundSpinner.getValue(), dis.initialUpperBound);

			}
		});
		initialUpperBoundSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				dis.updateBounds(dis.initialLowerBound, (int) initialUpperBoundSpinner.getValue());
			}
		});
		this.add(new JLabel("["));
		this.add(initialLowerBoundSpinner);
		this.add(new JLabel(","));
		this.add(initialUpperBoundSpinner);
		this.add(new JLabel("] " + title));
		this.setBoundSettingEnabled(boundSettingEna);

	}

	/**
	 * Sets whether the specification is enabled. When it gets enabled or disabled,
	 * the JSpiner gets enabled or disabled and the specification is updated with
	 * the current values ({@link #updateBounds(int, int)}). This may cause updates
	 * in the children ({@link #processParentUpdateBounds()}). This can only get
	 * disabled if it is deativatable ({@link #deactivatable}).
	 * 
	 * @param newBoundSettingEnabled
	 *            The new enabled value.
	 */
	protected void setBoundSettingEnabled(boolean newBoundSettingEnabled) {
		if (newBoundSettingEnabled) {
			initialLowerBoundSpinner.setEnabled(true);
			initialUpperBoundSpinner.setEnabled(true);
			boundSettingEna = true;
			this.updateBounds(initialLowerBound, initialUpperBound);
		}
		if (!newBoundSettingEnabled && deactivatable) {
			initialLowerBoundSpinner.setEnabled(false);
			initialUpperBoundSpinner.setEnabled(false);
			boundSettingEna = false;
			this.updateBounds(parent.initialLowerBound, parent.initialUpperBound);
		}
	}

	/**
	 * Updates the specification.
	 * 
	 * Informs the children about the update ({@link #processParentUpdateBounds()}).
	 * 
	 * @param initialLowerBound
	 *            The new lower bound.
	 * @param initialUpperBound
	 *            The new upper bound.
	 */
	private void updateBounds(int initialLowerBound, int initialUpperBound) {
		if (initialLowerBound < 0 || initialLowerBound > initialUpperBound) {
			throw new IllegalArgumentException();
		}
		this.initialLowerBound = initialLowerBound;
		this.initialUpperBound = initialUpperBound;
		this.initialLowerBoundSpinner.setValue(initialLowerBound);
		((SpinnerNumberModel) initialLowerBoundSpinner.getModel()).setMaximum(initialUpperBound);
		this.initialUpperBoundSpinner.setValue(initialUpperBound);
		((SpinnerNumberModel) initialUpperBoundSpinner.getModel()).setMinimum(initialLowerBound);
		for (AbstractInitialBoundPanel child : children) {
			child.processParentUpdateBounds();
		}
	}

	/**
	 * Adapts the changed specification on the parent if this is not enabled. This
	 * is called by the parent if the specification on the parent is changed.
	 */
	private void processParentUpdateBounds() {
		if (!boundSettingEna) {
			updateBounds(parent.getInitialLowerBound(), parent.getInitialUpperBound());
		}
	}

	/**
	 * Adds a child.
	 * 
	 * @param child
	 *            The child to be added.
	 */
	public void addChild(AbstractInitialBoundPanel child) {
		if (child == null) {
			return;
		}
		children.add(child);
	}

	public int getInitialLowerBound() {
		return initialLowerBound;
	}

	public int getInitialUpperBound() {
		return initialUpperBound;
	}

	/**
	 * Adapts the specified bounds to the initial bounds specification of the CSPs
	 * for the bounds tightening problem of UML/OCL instance finder configurations.
	 * 
	 * @param iModelCSPVariablesInitialBoundsSpecification
	 *            The initial bounds specification.
	 */
	public abstract void enrichIModelCSPVariablesInitialBoundsSpecification(
			IModelCSPVariablesInitialBoundsSpecification iModelCSPVariablesInitialBoundsSpecification);

}
