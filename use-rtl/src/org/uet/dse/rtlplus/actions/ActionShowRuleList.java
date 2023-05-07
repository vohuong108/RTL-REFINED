package org.uet.dse.rtlplus.actions;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.main.ViewFrame;
import org.tzi.use.gui.views.CommandView;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.gui.RTLRuleTree;

public class ActionShowRuleList implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		MainWindow parent = pluginAction.getParent();
		if (Main.getTggRuleCollection().getRuleList().size() == 0) {
			JOptionPane.showMessageDialog(parent, "No rules available.");
		} else {
			URL url = Main.class.getResource("/resources/rtl.png");
			//the commandView is a view stub to avoid the error with the operation detachModel() that is invoked from vf's view            
			CommandView cv = new CommandView(pluginAction.getSession().system());
			ViewFrame fViewFrame = new ViewFrame("Transformation rules", cv, "");
			fViewFrame.setFrameIcon(new ImageIcon(url));
			fViewFrame.setContentPane(new RTLRuleTree(Main.getTggRuleCollection(), parent));
			fViewFrame.pack();
			parent.addNewViewFrame(fViewFrame);
		}
	}
}
