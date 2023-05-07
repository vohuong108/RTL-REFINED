package org.uet.dse.rtlplus.gui;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.util.Pair;

@SuppressWarnings("serial")
public class CtValueTable extends JTable {
	
	private List<Pair<String>> termList;
	
	public CtValueTable(List<Pair<String>> termList) {
		super();
		this.termList = termList;
		setModel(new CtValueTableModel(termList, null));
	}
	
	public void setResultList(List<Value> resultList) {
		setModel(new CtValueTableModel(termList, resultList));
	}
	
	@Override
	public String getToolTipText(MouseEvent event) {
		Point p = event.getPoint();
		int row = rowAtPoint(p);
		int column = columnAtPoint(p);
		if (row >= 0 && column == 0) 
			return termList.get(row).second;
		else return null;
	}

	@Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return component;
     }

	private static class CtValueTableModel implements TableModel {
		
		private static String[] columnNames = new String[] {"Term", "Value"};
		private List<Pair<String>> termList;
		private List<Value> resultList = null;
		
		public CtValueTableModel(List<Pair<String>> termList, List<Value> resultList) {
			super();
			this.termList = termList;
			this.resultList = resultList;
		}

		@Override
		public void addTableModelListener(TableModelListener arg0) {
		}

		@Override
		public Class<?> getColumnClass(int arg0) {
			return String.class;
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public String getColumnName(int arg0) {
			return columnNames[arg0];
		}

		@Override
		public int getRowCount() {
			return termList.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			if (col == 0)
				return termList.get(row).first;
			else {
				if (resultList == null)
					return "";
				else return resultList.get(row);
			}
		}

		@Override
		public boolean isCellEditable(int arg0, int arg1) {
			return false;
		}

		@Override
		public void removeTableModelListener(TableModelListener arg0) {			
		}

		@Override
		public void setValueAt(Object arg0, int arg1, int arg2) {			
		}
		
	}

}
