package org.uet.dse.rtlplus.actions;

import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.uet.dse.rtlplus.gui.TestDialog;

public class ActionTestWithCT implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		TestDialog dialog = new TestDialog(pluginAction.getParent(), pluginAction.getSession());
		dialog.setVisible(true);
	}

}
