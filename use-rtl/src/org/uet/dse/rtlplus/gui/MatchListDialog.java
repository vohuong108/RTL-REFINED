package org.uet.dse.rtlplus.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.tzi.use.gui.main.ViewFrame;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.objectdiagram.MatchSelectedEvent;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

@SuppressWarnings("serial")
public class MatchListDialog extends JPanel {
	private JList<Match> list;
	private JButton btn;
	private List<Match> matches;
	private EventBus eventBus;
	private Match selectedMatch;
	private ViewFrame viewFrame;
	private JLabel label;

	public MatchListDialog(ViewFrame vf, List<Match> matches, EventBus eventBus, MSystemState state,
			PrintWriter logWriter, MatchListDialogResult res) {
		super();
		DefaultListModel<Match> model = new DefaultListModel<>();
		res.setResult(false);
		this.matches = matches;
		this.eventBus = eventBus;
		viewFrame = vf;
		eventBus.register(this);
		for (Match match : matches) {
			model.addElement(match);
		}
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		list = new JList<>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(list), BorderLayout.CENTER);
		btn = new JButton("Run");
		btn.setMnemonic('R');
		btn.setEnabled(false);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				res.setResult(true);
				selectedMatch.run(state, logWriter);
				eventBus.post(new MatchRunEvent());
				eventBus.post(new MatchSelectedEvent(new ArrayList<MObject>(0)));
			}
		});
		add(btn, BorderLayout.PAGE_END);
		label = new JLabel(String.format("<html>Found %d match(es). Click on a match to view matched objects, then click Run to execute it.</html>", matches.size()));
		add(label, BorderLayout.PAGE_START);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					selectedMatch = list.getSelectedValue();
					if (selectedMatch != null) {
						label.setText(String.format("<html>Number of objects to create: %d<br>Number of links to create: %d<br>Number of correspondence objects to create: %d</html>", selectedMatch.getNewObjectsNum(), selectedMatch.getNewLinksNum(), selectedMatch.getNewCorrsNum()));
						eventBus.post(new MatchSelectedEvent(selectedMatch.getObjectList().values()));
						btn.setEnabled(true);
					} else {
						label.setText("");
						eventBus.post(new MatchSelectedEvent(new ArrayList<>()));
						btn.setEnabled(false);
					}
				}
			}
		});
	}

	@Subscribe
	public void onMatchRun(MatchRunEvent e) {
		try {
			viewFrame.setClosed(true);
		} catch (PropertyVetoException e1) {
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		eventBus.unregister(this);
		super.finalize();
	}

	private static class MatchRunEvent {
	}

}
