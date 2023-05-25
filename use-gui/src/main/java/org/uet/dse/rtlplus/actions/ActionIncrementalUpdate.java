package org.uet.dse.rtlplus.actions;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.main.ViewFrame;
import org.tzi.use.gui.views.CommandView;
import org.tzi.use.main.Session;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.mm.MRuleCollection.TransformationType;
import org.uet.dse.rtlplus.sync.SyncWorkerDialog;

public class ActionIncrementalUpdate implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		MainWindow mainWindow = pluginAction.getParent();
		if ((Main.getTggRuleCollection().getType() == TransformationType.FORWARD
				|| Main.getTggRuleCollection().getType() == TransformationType.BACKWARD)
				&& !Main.syncWindowOpened) {
			Session session = pluginAction.getSession();
			SyncWorkerDialog syncWorker = new SyncWorkerDialog(mainWindow, session);
			//URL url = Main.class.getResource("F:\\Vo Huong\\KLTN\\use-use-frsl\\use-gui\\src\\main\\resources\\images\\delta.png");
			URL url = null;
			try {
				url = new URL("file:/F:/Vo Huong/KLTN/use-use-frsl/use-gui/src/main/resources/images/delta.png");
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
			//the commandView is a view stub to avoid the error with the operation detachModel() that is invoked from vf's view
			CommandView cv = new CommandView(pluginAction.getSession().system());
			ViewFrame vf = new ViewFrame("Model incremental update", cv, "");
			vf.setFrameIcon(new ImageIcon(url));
			vf.addInternalFrameListener(new InternalFrameListener() {
				@Override
				public void internalFrameActivated(InternalFrameEvent arg0) {
				}

				@Override
				public void internalFrameClosed(InternalFrameEvent arg0) {
				}

				@Override
				public void internalFrameClosing(InternalFrameEvent arg0) {
					System.out.println("Unsubscribed from EventBus");
					syncWorker.unsubscribe();
					Main.syncWindowOpened = false;
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
					Main.syncWindowOpened = true;
				}
			});
			vf.setContentPane(syncWorker);
			mainWindow.addNewViewFrame(vf);
		}
		else JOptionPane.showMessageDialog(mainWindow, "This feature is only available for the forward/backward transformation type.");
	}

	public void performActionCustomUI(MainWindow mainWindow, Session session) {
//		MainWindow mainWindow = pluginAction.getParent();
		if ((Main.getTggRuleCollection().getType() == TransformationType.FORWARD
				|| Main.getTggRuleCollection().getType() == TransformationType.BACKWARD)
				&& !Main.syncWindowOpened) {
//			Session session = pluginAction.getSession();
			SyncWorkerDialog syncWorker = new SyncWorkerDialog(mainWindow, session);
			//URL url = Main.class.getResource("F:\\Vo Huong\\KLTN\\use-use-frsl\\use-gui\\src\\main\\resources\\images\\delta.png");
			URL url = null;
			try {
				url = new URL("file:/F:/Vo Huong/KLTN/use-use-frsl/use-gui/src/main/resources/images/delta.png");
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
			//the commandView is a view stub to avoid the error with the operation detachModel() that is invoked from vf's view
//			CommandView cv = new CommandView(pluginAction.getSession().system());
			CommandView cv = new CommandView(session.system());
			ViewFrame vf = new ViewFrame("Model incremental update", cv, "");
			vf.setFrameIcon(new ImageIcon(url));
			vf.addInternalFrameListener(new InternalFrameListener() {
				@Override
				public void internalFrameActivated(InternalFrameEvent arg0) {
				}

				@Override
				public void internalFrameClosed(InternalFrameEvent arg0) {
				}

				@Override
				public void internalFrameClosing(InternalFrameEvent arg0) {
					System.out.println("Unsubscribed from EventBus");
					syncWorker.unsubscribe();
					Main.syncWindowOpened = false;
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
					Main.syncWindowOpened = true;
				}
			});
			vf.setContentPane(syncWorker);
			mainWindow.addNewViewFrame(vf);
		}
		else JOptionPane.showMessageDialog(mainWindow, "This feature is only available for the forward/backward transformation type.");
	}
}
