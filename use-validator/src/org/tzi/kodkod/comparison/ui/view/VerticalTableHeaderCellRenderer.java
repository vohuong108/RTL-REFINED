package org.tzi.kodkod.comparison.ui.view;

/**
 * A renderer for a JTableHeader with text rotated 90 degree counterclockwise.
 * <P>
 * Extends {@link DefaultTableHeaderCellRenderer}.
 * 
 * @see VerticalLabelUI
 * 
 * @author Jan Prien
 */
public class VerticalTableHeaderCellRenderer extends DefaultTableHeaderCellRenderer {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -1494630103275005960L;

	/**
	 * Constructs a <code>VerticalTableHeaderCellRenderer</code>.
	 * <P>
	 * The horizontal and vertical alignments and text positions are set as
	 * appropriate to a vertical table header cell.
	 */
	public VerticalTableHeaderCellRenderer() {
		setHorizontalAlignment(LEFT);
		setHorizontalTextPosition(CENTER);
		setVerticalAlignment(CENTER);
		setVerticalTextPosition(TOP);
		setUI(new VerticalLabelUI(false));
	}

}