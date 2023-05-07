package org.tzi.use.runtime.gui.impl;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.main.Session;
import org.tzi.use.runtime.IPlugin;
import org.tzi.use.runtime.IPluginRuntime;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.tzi.use.runtime.gui.IPluginActionDescriptor;
import org.tzi.use.runtime.impl.PluginRuntime;
import org.tzi.use.util.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This class provides the abstract behaviour for Plugin Action Proxies. It is
 * mandatory to connect the Plugin Action Proxy with the implemented Plugin
 * Action in the Plugins.
 * 
 * @author Roman Asendorf
 */
public abstract class PluginAction extends AbstractAction implements
		IPluginAction {

	private final IPluginActionDescriptor pluginActionDescriptor;

	private IPluginActionDelegate pluginActionDelegate;

	private final Session session;

	private final MainWindow parent;

	/**
	 * Constructor to create a Plugin Action Proxy with the given Plugin Action
	 * Descriptor, in the current Session, a parent Window.
	 * 
	 * @param pluginActionDescriptor
	 *            The Plugin Action Descriptor object
	 * @param session
	 *            The Session object
	 * @param parent
	 *            The Window object
	 * @param name
	 *            The Plugin Action Proxy's name
	 * @param icon
	 *            The Plugin Action Proxy's icon
	 */
	public PluginAction(IPluginActionDescriptor pluginActionDescriptor,
			Session session, MainWindow parent, String name, ImageIcon icon) {
		super(name, icon);
		this.pluginActionDescriptor = pluginActionDescriptor;
		this.session = session;
		this.parent = parent;
	}

	private IPluginActionDelegate getPluginActionDelegate() {
		if (this.pluginActionDelegate == null) {
			this.pluginActionDelegate = createActionDelegate();
		}

		return this.pluginActionDelegate;
	}

	public void actionPerformed(ActionEvent event) {
		this.getPluginActionDelegate().performAction(this);
	}

	/**
	 * <p>If the system state or session changes this operation
	 * should be called to "ask" the action if it is executable at the moment.</p>
	 * <p>This is used to enable or disable GUI elements.</p>
	 */
	public void calculateEnabled() {
		boolean shouldBeEnabled = this.getPluginActionDelegate().shouldBeEnabled(this);
		this.setEnabled(shouldBeEnabled);
	}

	/**
	 * Method to connect with the Plugin Action in the Plugin.
	 *
	 * @throws RuntimeException if plugin or delegate action could not be loaded
	 * @return The Plugin's Action from the Plugin.
	 */
	private IPluginActionDelegate createActionDelegate() {
		IPlugin thePlugin = this.pluginActionDescriptor.getParent().getPluginClass();

		if (thePlugin == null) {
			Log.debug("No main plugin class found! Running ActionDelegate directly.");
		} else {
			try {
				IPluginRuntime pluginRuntime = PluginRuntime.getInstance();
				Log.debug("Plugin not started yet, starting now...");
				thePlugin.run(pluginRuntime);
			} catch (Exception e) {
				String msg = "The plugin [" + thePlugin.getName() + "] could not be started! ";
				Log.error(msg + e);
				throw new RuntimeException(msg, e);
			}
		}

		this.pluginActionDelegate = this.pluginActionDescriptor.getActionClass();

		return this.pluginActionDelegate;
	}

	public MainWindow getParent() {
		return this.parent;
	}

	public Session getSession() {
		return this.session;
	}
}
