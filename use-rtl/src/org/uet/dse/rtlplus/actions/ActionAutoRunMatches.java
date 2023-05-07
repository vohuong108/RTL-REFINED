package org.uet.dse.rtlplus.actions;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.matching.BackwardMatchManager;
import org.uet.dse.rtlplus.matching.ForwardMatchManager;
import org.uet.dse.rtlplus.matching.IntegrationMatchManager;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.matching.MatchManager;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;

public class ActionAutoRunMatches implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		if (Main.getTggRuleCollection().getRuleList().size() == 0) {
			JOptionPane.showMessageDialog(pluginAction.getParent(), "Please open a TGG rule file first.", "No rules available", JOptionPane.ERROR_MESSAGE);
			return;
		}
		MatchManager manager = null;
		MSystemState state = pluginAction.getSession().system().state();
		switch (Main.getTggRuleCollection().getType()) {
		case FORWARD:
			manager = new ForwardMatchManager(state, true);
			break;
		case BACKWARD:
			manager = new BackwardMatchManager(state, true);
			break;
		case COEVOLUTION:
			JOptionPane.showMessageDialog(pluginAction.getParent(), "Sorry, this feature is not available for co-evolution transformations.", "Feature not available", JOptionPane.ERROR_MESSAGE);
			return;
		case INTEGRATION:
			manager = new IntegrationMatchManager(state, false);
			break;
		case SYNCHRONIZATION:
			break;
		}
		int i = 0;
		while (true) {
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
			if (matches.isEmpty())
				break;
			boolean success = false;
			for (Match match : matches) {
				// System.out.println(match.toString());
				success = match.run(state, pluginAction.getParent().logWriter());
				if (!success) {
					pluginAction.getParent().logWriter().println("Failed to run match");
				} else {
					i++;
					break;
				}
			}
			if (!success)
				break;
		}
		JOptionPane.showMessageDialog(pluginAction.getParent(), String.format("Completed %d match(es).", i), "Operation completed", JOptionPane.INFORMATION_MESSAGE);
	}

}
