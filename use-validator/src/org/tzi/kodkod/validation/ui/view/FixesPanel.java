package org.tzi.kodkod.validation.ui.view;

import javax.swing.BoxLayout;

import org.tzi.kodkod.validation.AbstractFix;

/**
 * Panel that lists fixes.
 * 
 * @author Jan Prien
 *
 */
final class FixesPanel extends AbstractValidationJPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1966084911722091839L;

	/**
	 * The listed fixes.
	 */
	private final AbstractFix[] fixes;

	/**
	 * Constructs an object.
	 * 
	 * @param fixes
	 *            The listed fixes.
	 */
	public FixesPanel(AbstractFix[] fixes) {
		if (fixes == null || fixes.length < 1) {
			throw new IllegalArgumentException();
		}
		this.fixes = fixes;
		this.initGUI();

	}

	@Override
	void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		for (AbstractFix fix : fixes) {
			FixPanel fixPanel = new FixPanel(fix);
			this.add(fixPanel);
			this.addApplyFixPerfomer(fixPanel);
		}
	}

}
