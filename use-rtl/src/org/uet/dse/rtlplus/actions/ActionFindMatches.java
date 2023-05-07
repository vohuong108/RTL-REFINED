package org.uet.dse.rtlplus.actions;

import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.main.ViewFrame;
import org.tzi.use.gui.views.CommandView;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.gui.MatchListDialog;
import org.uet.dse.rtlplus.gui.MatchListDialogResult;
import org.uet.dse.rtlplus.matching.BackwardMatchManager;
import org.uet.dse.rtlplus.matching.CoevolutionMatchManager;
import org.uet.dse.rtlplus.matching.ForwardMatchManager;
import org.uet.dse.rtlplus.matching.IntegrationMatchManager;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.matching.MatchManager;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;

import com.google.common.eventbus.EventBus;

public class ActionFindMatches implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		if (Main.getTggRuleCollection().getRuleList().size() == 0) {
			JOptionPane.showMessageDialog(pluginAction.getParent(), "Please open a TGG rule file first.", "No rules available", JOptionPane.ERROR_MESSAGE);
			return;
		}
		MainWindow mainWindow = pluginAction.getParent();
		PrintWriter logWriter = mainWindow.logWriter();
		MSystemState state = pluginAction.getSession().system().state();
		MatchManager manager = null;
		switch (Main.getTggRuleCollection().getType()) {
		case FORWARD:
			manager = new ForwardMatchManager(state, true);
			break;
		case BACKWARD:
			manager = new BackwardMatchManager(state, true);
			break;
		case INTEGRATION:
			manager = new IntegrationMatchManager(state, false);
			break;
		case COEVOLUTION:
			manager = new CoevolutionMatchManager(state, false);
			break;
		default:
			manager = null;
			break;
		}
		List<Match> matches = new ArrayList<>();
		if (Main.getTggRuleCollection().getType() == TransformationType.SYNCHRONIZATION) {
			MatchManager forwardManager = new ForwardMatchManager(state, false);
			matches.addAll(forwardManager.findMatches());
			MatchManager backwardManager = new BackwardMatchManager(state, false);
			matches.addAll(backwardManager.findMatches());
			MatchManager integrationManager = new IntegrationMatchManager(state, false);
			matches.addAll(integrationManager.findMatches());
		}
		else
			matches = manager.findMatches();
		MatchListDialogResult res = new MatchListDialogResult();
		if (matches.isEmpty())
			JOptionPane.showMessageDialog(mainWindow, "No matches were found.");
		else {
			EventBus eventBus = pluginAction.getSession().system().getEventBus();
			URL url = Main.class.getResource("/resources/list.png");
			//the commandView is a view stub to avoid the error with the operation detachModel() that is invoked from vf's view            
			CommandView cv = new CommandView(state.system());
			ViewFrame vf = new ViewFrame("Match list", cv, "");
			vf.setFrameIcon(new ImageIcon(url));
			MatchListDialog dialog = new MatchListDialog(vf, matches, eventBus, state, logWriter, res);
			vf.addInternalFrameListener(new InternalFrameListener() {
				@Override
				public void internalFrameActivated(InternalFrameEvent arg0) {
				}
				@Override
				public void internalFrameClosed(InternalFrameEvent arg0) {	
					if (res.isResult())
						performAction(pluginAction);
				}
				@Override
				public void internalFrameClosing(InternalFrameEvent arg0) {						
				}
				@Override
				public void internalFrameDeactivated(InternalFrameEvent arg0) {						
				}
				@Override
				public void internalFrameDeiconified(InternalFrameEvent arg0) {						
				}
				@Override
				public void internalFrameIconified(InternalFrameEvent arg0) {						
				}
				@Override
				public void internalFrameOpened(InternalFrameEvent arg0) {						
				}
				
			});
			vf.setContentPane(dialog);
			vf.pack();
			mainWindow.addNewViewFrame(vf);
		}
	}

}
