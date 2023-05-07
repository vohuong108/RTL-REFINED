package org.uet.dse.rtlplus.testing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.util.Pair;
import org.uet.dse.rtlplus.sync.OperationEnterEvent;

public class TestResult implements TableModel {
	
	private List<Result> results;
	private String[] columnNames;
	private boolean isForward;
	private List<Pair<String>> srcTermList;
	private List<Pair<String>> trgTermList;
	
	
	public TestResult(List<Pair<String>> srcTermList, List<Pair<String>> trgTermList, List<MSystemState> systemStates,
			List<LinkedHashMap<ClassifyingTerm, Value>> termSolutions, List<List<Value>> otherTermSolutions, boolean forward, 
			List<List<String>> termEvalLogs, List<List<String>> otherTermEvalLogs, List<List<OperationEnterEvent>> transformations, List<Mapping> mappings) {
		results = new ArrayList<>();
		for (int i = 0; i < systemStates.size(); i++) {
			results.add(new Result(systemStates.get(i), termSolutions.get(i), otherTermSolutions.get(i),
					termEvalLogs.get(i), otherTermEvalLogs.get(i), transformations.get(i), mappings));
		}
		columnNames = forward? new String[] {"Source CTs", "Target CTs", "Result"} : new String[] {"Target CTs", "Source CTs", "Result"};
		isForward = forward;
		this.srcTermList = srcTermList;
		this.trgTermList = trgTermList;
		
	}
	
	public List<Pair<String>> getSrcTermList() {
		return srcTermList;
	}

	public List<Pair<String>> getTrgTermList() {
		return trgTermList;
	}
	
	public List<Pair<String>> getLeftTermList() {
		if (isForward)
			return srcTermList;
		else return trgTermList;
	}

	public List<Pair<String>> getRightTermList() {
		if (isForward)
			return trgTermList;
		else return srcTermList;
	}
	
	public List<Result> getResults() {
		return results;
	}
	
	public boolean isForward() {
		return isForward;
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
		return 3;
	}

	@Override
	public String getColumnName(int arg0) {
		return columnNames[arg0];
	}

	@Override
	public int getRowCount() {
		return results.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Result res = results.get(row);
		switch(col) {
		case 0:
			return res.getTermSolutionString();
		case 1:
			return res.getOtherTermSolutionString();
		default:
			return res.getResult().getFinalResult().toString();
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
