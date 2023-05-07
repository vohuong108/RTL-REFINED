package org.uet.dse.rtlplus.actions;

import java.util.List;

import javax.swing.JOptionPane;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.matching.IntegrationMatchManager;
import org.uet.dse.rtlplus.matching.Match;
import org.uet.dse.rtlplus.matching.MatchManager;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;

public class ActionIntegrationCheck implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		MainWindow mainWindow = pluginAction.getParent();
		//PrintWriter logWriter = mainWindow.logWriter();
		MSystemState state = pluginAction.getSession().system().state();
		MatchManager manager = null;
		if (Main.getTggRuleCollection().getType() == TransformationType.INTEGRATION) {
			manager = new IntegrationMatchManager(state, false);
			int i = 0;
			while (true) {
				List<Match> matches = manager.findMatches();
				if (matches.isEmpty())
					break;
				boolean success = false;
				for (Match match : matches) {
					// System.out.println(match.toString());
					success = match.run(pluginAction.getSession().system().state(),
							pluginAction.getParent().logWriter());
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
			JOptionPane.showMessageDialog(pluginAction.getParent(), String.format("Completed %d match(es).", i));
		}
		else JOptionPane.showMessageDialog(mainWindow, "This feature is only available for the integration transformation type.");
	}

}
