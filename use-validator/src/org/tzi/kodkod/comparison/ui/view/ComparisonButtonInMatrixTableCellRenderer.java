package org.tzi.kodkod.comparison.ui.view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Cell renderer of cells in matrix that presents the overview of comparison
 * results.
 * 
 * @author Jan Prien
 *
 */
public class ComparisonButtonInMatrixTableCellRenderer extends JButton
		implements TableCellRenderer, IComparisonButtonInMatrixTableCellLayouter {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 7726032972261280525L;

	/**
	 * Constructs an object.
	 */
	public ComparisonButtonInMatrixTableCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		this.layoutButton(this, value);
		return this;
	}
}