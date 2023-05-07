package org.tzi.kodkod.validation.ui.view;

import javax.swing.JButton;

import org.tzi.kodkod.validation.AbstractFix;

/**
 * Button for applying a fix.
 * 
 * @author Jan Prien
 *
 */
final class ApplyFixButton extends JButton {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -2459834970673479216L;

	/**
	 * The fix that is applied on clicking the button.
	 */
	private final AbstractFix fix;

	/**
	 * Constructs an object.
	 * 
	 * @param string
	 *            The text of the button.
	 * @param fix
	 *            The fix that is applied on clicking the button.
	 */
	public ApplyFixButton(String string, AbstractFix fix) {
		super(string);
		if (fix == null) {
			throw new IllegalArgumentException();
		}
		this.fix = fix;
	}

	public AbstractFix getFix() {
		return this.fix;
	}

}
