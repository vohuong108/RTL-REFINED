package org.uet.dse.rtlplus.actions;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.main.Session;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.uet.dse.rtlplus.gui.RTLParserParameter;

public class ActionOpenRTL implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		Session fSession = pluginAction.getSession();
		MainWindow fMainWindow = pluginAction.getParent();
		RTLParserParameter fParamForm = new RTLParserParameter(fSession, fMainWindow);
		fParamForm.setResizable(true);
        fParamForm.setVisible(true);
	}
}
