package org.uet.dse.rtlplus.actions;

import java.beans.PropertyVetoException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.main.ViewFrame;
import org.tzi.use.gui.views.CommandView;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.objectdiagram.RtlObjectDiagramView;

public class ActionShowObjectDiagram implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		MainWindow mainWindow = pluginAction.getParent();
		if (Main.getTggRuleCollection().getRuleList().size() == 0) {
			JOptionPane.showMessageDialog(mainWindow, "Plese load some TGG rules first.", "No transformation rules",  JOptionPane.WARNING_MESSAGE);
		}
		else {
			URL url = Main.class.getResource("/resources/diagram.png");
			//the commandView is a view stub to avoid the error with the operation detachModel() that is invoked from vf's view            
			CommandView cv = new CommandView(pluginAction.getSession().system());
			ViewFrame vf = new ViewFrame("RTL object diagram", cv, "");
			vf.setFrameIcon(new ImageIcon("file:\\F:\\Vo Huong\\KLTN\\use-use-frsl\\use-gui\\src\\main\\resources\\images\\diagram.png"));
			vf.addInternalFrameListener(new InternalFrameListener() {
				@Override
				public void internalFrameActivated(InternalFrameEvent arg0) {
				}
				@Override
				public void internalFrameClosed(InternalFrameEvent arg0) {					
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
					try {
						arg0.getInternalFrame().setMaximum(true);
					} catch (PropertyVetoException e) {
					} 					
				}
				
			});
			RtlObjectDiagramView odv = new RtlObjectDiagramView(mainWindow, Main.getTggRuleCollection().getClassMap(), pluginAction.getSession().system());
			vf.setContentPane(odv);
			vf.pack();
			mainWindow.addNewViewFrame(vf);
		}
	}

}
