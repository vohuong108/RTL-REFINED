package org.tzi.kodkod.comparison.ui.view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * A default cell renderer for a JTableHeader.
 * 
 * @author Jan Prien
 */
public class DefaultTableHeaderCellRenderer extends DefaultTableCellRenderer {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 5377953789428550472L;

	/**
	 * Constructs a <code>DefaultTableHeaderCellRenderer</code>.
	 * <P>
	 * The horizontal alignment and text position are set as appropriate to a table
	 * header cell, and the opaque property is set to false.
	 */
	public DefaultTableHeaderCellRenderer() {
		setHorizontalAlignment(CENTER);
		setHorizontalTextPosition(LEFT);
		setVerticalAlignment(BOTTOM);
		setOpaque(false);
	}

	/**
	 * Returns the default table header cell renderer.
	 * <P>
	 * Subclasses may override this method to provide custom content or formatting.
	 *
	 * @param table
	 *            the <code>JTable</code>.
	 * @param value
	 *            the value to assign to the header cell
	 * @param isSelected
	 *            This parameter is ignored.
	 * @param hasFocus
	 *            This parameter is ignored.
	 * @param row
	 *            This parameter is ignored.
	 * @param column
	 *            the column of the header cell to render
	 * @return the default table header cell renderer
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		JTableHeader tableHeader = table.getTableHeader();
		if (tableHeader != null) {
			setForeground(tableHeader.getForeground());
		}
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		return this;
	}
}