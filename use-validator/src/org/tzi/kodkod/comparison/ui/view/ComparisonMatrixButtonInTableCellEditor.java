package org.tzi.kodkod.comparison.ui.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.tzi.kodkod.comparison.ui.UIElements;

/**
 * Cell editor of cells in matrix that presents the overview of comparison
 * results.
 * 
 * @author Jan Prien
 *
 */
public class ComparisonMatrixButtonInTableCellEditor extends DefaultCellEditor
		implements IComparisonButtonInMatrixTableCellLayouter {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -5041954960826190618L;

	/**
	 * The button the cell represents.
	 */
	private JButton button;

	/**
	 * The textual value of the cell.
	 */
	private String label;

	/**
	 * Whether this cell was already clicked on once.
	 */
	private boolean clicked;

	/**
	 * The row an column the cell has in the table that represents the matrix.
	 */
	private int row, col;

	/**
	 * The table that represents the matrix.
	 */
	private JTable table;

	/**
	 * Whether the cell is clickable.
	 */
	private boolean clickable;

	/**
	 * The comparison details of all comparison results in the matrix.
	 */
	private final String[][] tooltips;

	/**
	 * Constructs an object.
	 * 
	 * @param textField
	 *            The textual value of the cell.
	 * @param tooltips
	 *            The comparison details of all comparison results in the matrix.
	 */
	public ComparisonMatrixButtonInTableCellEditor(final JTextField textField, final String[][] tooltips) {
		super(textField);
		this.tooltips = tooltips;

		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.table = table;
		this.row = row;
		this.col = column;
		this.clickable = false;
		this.clicked = true;

		layoutButton(this.button, value);
		this.label = this.button.getText();
		this.clickable = !"".equals(this.label);

		return this.button;
	}

	@Override
	public Object getCellEditorValue() {
		if (this.clicked && this.clickable) {
			String text = (String) this.table.getValueAt(this.row, 0);
			text += " ";
			text += this.table.getValueAt(this.row, this.col);
			text += " ";
			text += this.table.getColumnName(this.col);
			text += "\n";
			JTextArea textArea = new JTextArea(text + this.tooltips[this.row][this.col]);
			textArea.setLineWrap(false);
			textArea.setWrapStyleWord(false);
			textArea.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize(new Dimension(800, 600));
			JOptionPane.showMessageDialog(this.button, scrollPane, UIElements.ComparisonDetailsWindow_Title,
					JOptionPane.PLAIN_MESSAGE);
		}
		clicked = false;
		return new String(this.label);
	}

	@Override
	public boolean stopCellEditing() {
		clicked = false;
		return super.stopCellEditing();
	}

}
