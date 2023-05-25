package org.uet.dse.rtlplus.sync;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.main.ViewFrame;
import org.tzi.use.gui.views.CommandView;
import org.tzi.use.main.Session;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.gui.MatchListDialog;
import org.uet.dse.rtlplus.gui.MatchListDialogResult;
import org.uet.dse.rtlplus.matching.BackwardMatchManager;
import org.uet.dse.rtlplus.matching.ForwardMatchManager;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.matching.MatchManager;
import org.uet.dse.rtlplus.mm.MTggRule;

import com.google.common.eventbus.EventBus;

@SuppressWarnings("serial")
public class SyncWorkerDialog extends JPanel {
	private EventBus eventBus;
	private SyncWorker worker;
	private MainWindow mainWindow;
	private PrintWriter logWriter;

	public SyncWorkerDialog(MainWindow parent, Session session) {
		mainWindow = parent;
		logWriter = parent.logWriter();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		JLabel label1 = new JLabel(
				"<html>Keep this window open to track incremental changes and update the model accordingly.</html>");
		add(label1);
		eventBus = session.system().getEventBus();
		worker = new SyncWorker(this, parent.logWriter(), session);
		eventBus.register(worker);
	}
	
	public void unsubscribe() {
		eventBus.unregister(worker);
	}
	
	void findForwardMatches(MSystemState state, Collection<MTggRule> ruleList, List<MObject> objects, boolean sync) {
		MatchListDialogResult res = new MatchListDialogResult();
		MatchManager fManager = new ForwardMatchManager(state, sync);
		List<Match> fMatches = fManager.findMatchesForRulesAndObjects(ruleList, objects);
		if (fMatches.isEmpty()) {
			logWriter.println("No forward matches available.");
		} else {
//			URL url = Main.class.getResource("/resources/list.png");
			URL url = null;
			try {
				url = new URL("file:/F:/Vo Huong/KLTN/use-use-frsl/use-gui/src/main/resources/images/list.png");
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
			//the commandView is a view stub to avoid the error with the operation detachModel() that is invoked from vf's view            
			CommandView cv = new CommandView(state.system());
			ViewFrame vf = new ViewFrame("New matches found!", cv, "");
			vf.setFrameIcon(new ImageIcon(url));
			MatchListDialog dialog = new MatchListDialog(vf, fMatches, eventBus, state, logWriter, res);
			vf.setContentPane(dialog);
			vf.pack();
			mainWindow.addNewViewFrame(vf);
		}
	}

	void findBackwardMatches(MSystemState state, Collection<MTggRule> ruleList, List<MObject> objects, boolean sync) {
		MatchListDialogResult res = new MatchListDialogResult();
		MatchManager bManager = new BackwardMatchManager(state, sync);
		List<Match> bMatches = bManager.findMatchesForRulesAndObjects(ruleList, objects);
		if (bMatches.isEmpty()) {
			logWriter.println("No backward matches available.");
		} else {
			URL url = Main.class.getResource("/resources/list.png");
			//the commandView is a view stub to avoid the error with the operation detachModel() that is invoked from vf's view            
			CommandView cv = new CommandView(state.system());
			ViewFrame vf = new ViewFrame("New matches found!", cv, "");
			vf.setFrameIcon(new ImageIcon(url));
			MatchListDialog dialog = new MatchListDialog(vf, bMatches, eventBus, state, logWriter, res);
			vf.setContentPane(dialog);
			vf.pack();
			mainWindow.addNewViewFrame(vf);
		}
	}

}
