package org.tzi.kodkod.comparison.ui.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicLabelUI;

/**
 * A UI delegate for <code>JLabel</code> that rotates the label 90 degree
 * clockwise.
 * 
 * @author Jan Prien
 */
public class VerticalLabelUI extends BasicLabelUI {

	/**
	 * Whether this is rotated clockwise.
	 */
	private boolean clockwise = false;

	private Rectangle verticalViewR = new Rectangle();

	private Rectangle verticalIconR = new Rectangle();

	private Rectangle verticalTextR = new Rectangle();

	/**
	 * Constructs a <code>VerticalLabelUI</code> with the desired rotation.
	 * 
	 * @param clockwise
	 *            true to rotate clockwise, false for counterclockwise
	 */
	public VerticalLabelUI(boolean clockwise) {
		this.clockwise = clockwise;
	}

	/*
	 * Overridden to always return -1, since a vertical label does not have a
	 * meaningful baseline.
	 */
	@Override
	public int getBaseline(JComponent c, int width, int height) {
		super.getBaseline(c, width, height);
		return -1;
	}

	/*
	 * Overridden to always return Component.BaselineResizeBehavior.OTHER, since a
	 * vertical label does not have a meaningful baseline.
	 */
	@Override
	public Component.BaselineResizeBehavior getBaselineResizeBehavior(JComponent c) {
		super.getBaselineResizeBehavior(c);
		return Component.BaselineResizeBehavior.OTHER;
	}

	@Override
	protected String layoutCL(JLabel label, FontMetrics fontMetrics, String text, Icon icon, Rectangle viewR,
			Rectangle iconR, Rectangle textR) {

		verticalViewR = transposeRectangle(viewR, verticalViewR);
		verticalIconR = transposeRectangle(iconR, verticalIconR);
		verticalTextR = transposeRectangle(textR, verticalTextR);

		text = super.layoutCL(label, fontMetrics, text, icon, verticalViewR, verticalIconR, verticalTextR);

		viewR = copyRectangle(verticalViewR, viewR);
		iconR = copyRectangle(verticalIconR, iconR);
		textR = copyRectangle(verticalTextR, textR);
		return text;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics2D g2 = (Graphics2D) g.create();
		if (clockwise) {
			g2.rotate(Math.PI / 2, c.getSize().width / 2, c.getSize().width / 2);
		} else {
			g2.rotate(-Math.PI / 2, c.getSize().height / 2, c.getSize().height / 2);
		}
		super.paint(g2, c);
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		return transposeDimension(super.getPreferredSize(c));
	}

	@Override
	public Dimension getMaximumSize(JComponent c) {
		return transposeDimension(super.getMaximumSize(c));
	}

	@Override
	public Dimension getMinimumSize(JComponent c) {
		return transposeDimension(super.getMinimumSize(c));
	}

	/**
	 * Transposes a dimension.
	 * 
	 * @param from
	 *            The dimension to transpose.
	 * @return The resulting dimension.
	 */
	private Dimension transposeDimension(Dimension from) {
		return new Dimension(from.height, from.width + 5);
	}

	/**
	 * Transposes coordinates of the upper left corner and the width and height of a
	 * {@link Rectangle} to an other.
	 * 
	 * @param from
	 *            The rectangle to transpose.
	 * @param to
	 *            The other rectangle
	 * @return The other rectangle.
	 */
	private Rectangle transposeRectangle(Rectangle from, Rectangle to) {
		if (to == null) {
			to = new Rectangle();
		}
		to.x = from.y;
		to.y = from.x;
		to.width = from.height;
		to.height = from.width;
		return to;
	}

	/**
	 * Copies coordinates of the upper left corner and the width and height of a
	 * {@link Rectangle} to an other.
	 * 
	 * @param from
	 *            The rectangle to transpose.
	 * @param to
	 *            The other rectangle
	 * @return The other rectangle.
	 */
	private Rectangle copyRectangle(Rectangle from, Rectangle to) {
		if (to == null) {
			to = new Rectangle();
		}
		to.x = from.x;
		to.y = from.y;
		to.width = from.width;
		to.height = from.height;
		return to;
	}
}