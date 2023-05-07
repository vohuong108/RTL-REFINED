package org.uet.dse.rtlplus.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.main.Session;
import org.tzi.use.uml.mm.MAttribute;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.objectdiagram.MatchSelectedEvent;
import org.uet.dse.rtlplus.objectdiagram.SystemResetEvent;
import org.uet.dse.rtlplus.sync.CachedLink;
import org.uet.dse.rtlplus.sync.OperationEnterEvent;
import org.uet.dse.rtlplus.testing.Mapping;
import org.uet.dse.rtlplus.testing.Result;
import org.uet.dse.rtlplus.testing.TestResult;

import com.google.common.eventbus.EventBus;

@SuppressWarnings("serial")
public class TestResultDialog extends JPanel {

	private MainWindow parent;

	public TestResultDialog(MainWindow parent, Session session, TestResult result) {
		super();
		this.parent = parent;
		EventBus eventBus = session.system().getEventBus();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JTable table = new JTable() {
			@Override
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component component = super.prepareRenderer(renderer, row, column);
		        int rendererWidth = component.getPreferredSize().width;
		        TableColumn tableColumn = getColumnModel().getColumn(column);
		        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
		        return component;
		     }
		};
		table.setModel(result);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Font font = new Font("Monospaced", Font.PLAIN, 12);

		JLabel srcLabel = new JLabel("Source classifying terms");
		srcLabel.setAlignmentX(CENTER_ALIGNMENT);
		CtValueTable srcTable = new CtValueTable(result.getSrcTermList());
		srcTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JTextArea leftTerm = new JTextArea(2, 30);
		leftTerm.setLineWrap(true);
		leftTerm.setWrapStyleWord(true);
		leftTerm.setEditable(false);
		leftTerm.setFont(font);
		JTextArea leftLog = new JTextArea(3, 30);
		leftLog.setLineWrap(true);
		leftLog.setWrapStyleWord(true);
		leftLog.setEditable(false);
		leftLog.setFont(font);
		JScrollPane leftLogScroll = new JScrollPane(leftLog);

		JLabel trgLabel = new JLabel("Target classifying terms");
		trgLabel.setAlignmentX(CENTER_ALIGNMENT);
		CtValueTable trgTable = new CtValueTable(result.getTrgTermList());
		trgTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JTextArea rightTerm = new JTextArea(2, 20);
		rightTerm.setLineWrap(true);
		rightTerm.setWrapStyleWord(true);
		rightTerm.setEditable(false);
		rightTerm.setFont(font);
		JTextArea rightLog = new JTextArea(3, 20);
		rightLog.setLineWrap(true);
		rightLog.setWrapStyleWord(true);
		rightLog.setEditable(false);
		rightLog.setFont(font);
		JScrollPane rightLogScroll = new JScrollPane(rightLog);	

		Container thisLabel = result.isForward() ? srcLabel : trgLabel;
		Container otherLabel = result.isForward() ? trgLabel : srcLabel;
		CtValueTable thisTable = result.isForward() ? srcTable : trgTable;
		CtValueTable otherTable = result.isForward() ? trgTable : srcTable;
		
		Container leftPane = Box.createVerticalBox();
		leftPane.add(Box.createVerticalStrut(10));
		leftPane.add(table.getTableHeader());
		leftPane.add(table);
		leftPane.add(Box.createVerticalStrut(10));
		leftPane.add(thisLabel);
		leftPane.add(Box.createVerticalStrut(5));
		leftPane.add(thisTable.getTableHeader());
		leftPane.add(thisTable);
		leftPane.add(leftTerm);
		leftPane.add(leftLogScroll);
		leftPane.add(Box.createVerticalStrut(10));
		leftPane.add(otherLabel);
		leftPane.add(Box.createVerticalStrut(5));
		leftPane.add(otherTable.getTableHeader());
		leftPane.add(otherTable);
		leftPane.add(rightTerm);
		leftPane.add(rightLogScroll);
		leftPane.add(Box.createVerticalStrut(10));
		JScrollPane leftScrollPane = new JScrollPane(leftPane);
		
		JLabel validationLabel = new JLabel("Validation result");
		validationLabel.setAlignmentX(CENTER_ALIGNMENT);
		JTextArea validationResult = new JTextArea(3, 20);
		validationResult.setEditable(false);
		
		JLabel transLabel = new JLabel("Executed transformations");
		transLabel.setAlignmentX(CENTER_ALIGNMENT);
		DefaultListModel<OperationEnterEvent> model = new DefaultListModel<>();
		JList<OperationEnterEvent> transList = new JList<>();
		transList.setModel(model); 
		JScrollPane transListScroll = new JScrollPane(transList);
		JTextArea transLog = new JTextArea(6, 20);
		transLog.setLineWrap(true);
		transLog.setWrapStyleWord(true);
		transLog.setEditable(false);
		JScrollPane transLogScroll = new JScrollPane(transLog);
		transList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				OperationEnterEvent event = transList.getSelectedValue();
				if (event != null) {
					List<String> objNames = new ArrayList<>(event.getMatchedObjects().values());
					List<String> newObjNames = new ArrayList<>(event.getObjectsToCreate().values());
					newObjNames.addAll(event.getCorrObjsToCreate().values());
					List<MObject> objs = objNames.stream().map((it) -> session.system().state().objectByName(it)).collect(Collectors.toList());
					List<MObject> newObjs = newObjNames.stream().map((it) -> session.system().state().objectByName(it)).collect(Collectors.toList());
					objs.addAll(newObjs);
					eventBus.post(new MatchSelectedEvent(objs));
					transLog.setText(event.getCommands().stream().skip(1).collect(Collectors.joining("\n", "Executed commands:\n", "")));
				} else {
					eventBus.post(new MatchSelectedEvent(new ArrayList<MObject>(0)));
					transLog.setText("");
				}
			}
		});
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				session.system().reset();
				eventBus.post(new SystemResetEvent());
				int index = table.getSelectedRow();
				leftTerm.setText("");
				leftLog.setText("");
				rightTerm.setText("");
				rightLog.setText("");
				model.removeAllElements();
				transLog.setText("");
				validationLabel.setText("Validation result");
				validationResult.setText("");
				if (index > -1) {
					Result res = result.getResults().get(index);
					copySystemState(res.getState(), session);
					thisTable.setResultList(res.getTermSolution());
					otherTable.setResultList(res.getOtherTermSolution());
					for (ListSelectionListener listener : ((DefaultListSelectionModel) thisTable.getSelectionModel()).getListSelectionListeners()) {
						thisTable.getSelectionModel().removeListSelectionListener(listener);
					}
					thisTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent arg0) {
							if (thisTable.getSelectedRow() != -1) {
								leftTerm.setText(result.getLeftTermList().get(thisTable.getSelectedRow()).second);
								leftLog.setText(result.getResults().get(index).getTermEvalLogs().get(thisTable.getSelectedRow()));
							} else {
								leftTerm.setText("");
								leftLog.setText("");
							}
						}
					});
					for (ListSelectionListener listener : ((DefaultListSelectionModel) otherTable.getSelectionModel()).getListSelectionListeners()) {
						otherTable.getSelectionModel().removeListSelectionListener(listener);
					}
					otherTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent arg0) {
							if (otherTable.getSelectedRow() != -1) {
								rightTerm.setText(result.getRightTermList().get(otherTable.getSelectedRow()).second);
								rightLog.setText(result.getResults().get(index).getOtherTermEvalLogs().get(otherTable.getSelectedRow()));
							} else {
								rightTerm.setText("");
								rightLog.setText("");
							}
						}
					});
					for (OperationEnterEvent e : result.getResults().get(index).getTransformations()) {
						model.addElement(e);
					}
					validationLabel.setText("Validation result: " + res.getResult().getFinalResult().toString());
					StringBuilder sb = new StringBuilder();
					sb.append(String.format("Matched %s pattern(s).\n", res.getResult().getMatchedMappings().size()));
					for (Entry<Mapping, Boolean> entry : res.getResult().getMatchedMappings().entrySet()) {
						sb.append(entry.getKey().toString())
							.append(" : ")
							.append(entry.getValue()? "pass" : "fail")
							.append("\n");
					}
					validationResult.setText(sb.toString());
				}
			}
		});
		
		
		Container rightPane = Box.createVerticalBox();
		rightPane.add(Box.createVerticalStrut(10));
		rightPane.add(validationLabel);
		rightPane.add(Box.createVerticalStrut(5));
		rightPane.add(validationResult);
		rightPane.add(Box.createVerticalStrut(10));
		rightPane.add(transLabel);
		rightPane.add(Box.createVerticalStrut(5));
		rightPane.add(transListScroll);
		rightPane.add(transLogScroll);
		rightPane.add(Box.createVerticalStrut(10));
		JScrollPane rightScrollPane = new JScrollPane(rightPane);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				leftScrollPane, rightScrollPane);
		add(splitPane);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				splitPane.setDividerLocation(0.5);
			}
		});
	}

	private boolean copySystemState(MSystemState state, Session toSes) {
		UseSystemApi api = UseSystemApi.create(toSes);
		try {
			for (MObject obj : state.allObjects()) {
				api.createObject(obj.cls().name(), obj.name());
			}
			for (MLink lnk : state.allLinks()) {
				api.createLink(lnk.association().name(), lnk.linkedObjects().stream().map(it -> it.name())
						.collect(Collectors.toList()).toArray(new String[lnk.linkedObjectsAsArray().length]));
			}
			for (MObject obj : state.allObjects()) {
				for (Entry<MAttribute, Value> entry : obj.state(state).attributeValueMap().entrySet()) {
					api.setAttributeValue(obj.name(), entry.getKey().name(), entry.getValue().toString());
				}
			}
			return true;
		} catch (UseApiException e) {
			parent.logWriter().println("Error when copying system state: " + e.getMessage());
			return false;
		}
	}

}
