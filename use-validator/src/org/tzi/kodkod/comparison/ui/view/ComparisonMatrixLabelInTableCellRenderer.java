package org.tzi.kodkod.comparison.ui.view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Cell renderer of header cells in matrix that presents the overview of
 * comparison results.
 * 
 * @author Jan Prien
 *
 */
public class ComparisonMatrixLabelInTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		c.setBackground(UIManager.getColor("Button.background"));
		return c;
	}
}