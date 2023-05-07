package org.tzi.kodkod.comparison.ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import org.tzi.kodkod.comparison.ComparisonResult;

/**
 * The panel presenting comparison results as a matrix.
 * 
 * @author Jan Prien
 *
 */
public class ComparisonMatrixPanel extends AbstractComparisonJPanel {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -1530557931063459089L;

	/**
	 * The comparison data.
	 */
	private final MultipleComparisonResult multipleComparisonResultModel;

	/**
	 * Constructs an object.
	 * 
	 * @param multipleComparisonResultModel
	 *            The comparison data.
	 */
	public ComparisonMatrixPanel(final MultipleComparisonResult multipleComparisonResultModel) {
		this.multipleComparisonResultModel = multipleComparisonResultModel;
		this.initGUI();
	}

	@Override
	public void initGUI() {
		this.removeAll();
		this.setLayout(new BorderLayout());

		final String[] sortedNames = multipleComparisonResultModel.getSelectedNames();

		String[][] data = new String[sortedNames.length][sortedNames.length + 1];
		final String[][] tooltips = new String[sortedNames.length][sortedNames.length + 1];
		int rowIndex = -1;
		for (String rowName : sortedNames) {
			rowIndex++;
			data[rowIndex][0] = rowName;

			int columnIndex = 0;
			for (String columnName : sortedNames) {
				columnIndex++;
				ComparisonResult comparisonResult = multipleComparisonResultModel.getComparisonResult(rowName,
						columnName);
				data[rowIndex][columnIndex] = comparisonResult.getDescriptor();
				tooltips[rowIndex][columnIndex] = comparisonResult.getText();
			}
		}
		final List<String> columnNames = new ArrayList<String>();
		columnNames.add("");
		for (String name : sortedNames) {
			columnNames.add(name);
		}
		JTable table = new JTable(data, columnNames.toArray()) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		for (

				int i = 1; i < columnNames.size(); i++) {
			TableColumn tableColumn = table.getColumnModel().getColumn(i);
			tableColumn.setCellRenderer(new ComparisonButtonInMatrixTableCellRenderer());
			tableColumn.setCellEditor(new ComparisonMatrixButtonInTableCellEditor(new JTextField(), tooltips));
			tableColumn.setMinWidth(55);
			tableColumn.setMaxWidth(55);
		}

		TableColumn tableColumn = table.getColumnModel().getColumn(0);
		tableColumn.setMinWidth(300);
		tableColumn.setMaxWidth(300);
		tableColumn.setCellRenderer(new ComparisonMatrixLabelInTableCellRenderer());

		table.setOpaque(false);
		this.add(table, BorderLayout.CENTER);

		Enumeration<?> columns = table.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			((TableColumn) columns.nextElement()).setHeaderRenderer(new VerticalTableHeaderCellRenderer());
		}
		table.getTableHeader().setBackground(Color.WHITE);
		table.getTableHeader().setOpaque(false);
		this.add(table.getTableHeader(), BorderLayout.NORTH);
	}

}
