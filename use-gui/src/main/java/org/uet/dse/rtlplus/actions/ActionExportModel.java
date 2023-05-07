package org.uet.dse.rtlplus.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.tzi.use.config.Options;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.tzi.use.uml.mm.MAttribute;
import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.mm.MRuleCollection.Side;

public class ActionExportModel implements IPluginActionDelegate {

	@Override
	public void performAction(IPluginAction pluginAction) {
		int choice = JOptionPane.showOptionDialog(pluginAction.getParent(), 
				"Select a model below", "Export model", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
				null, new String[] {"Source model", "Target model"}, null);
		if (choice == JOptionPane.CLOSED_OPTION)
			return;
		String path = Options.getLastDirectory().toString();
		JFileChooser chooser = new JFileChooser(path);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setDialogTitle("Save SOIL script to");
		int returnVal = chooser.showSaveDialog(pluginAction.getParent());
		if (returnVal != JFileChooser.APPROVE_OPTION)
			return;
		File selectedFile = chooser.getSelectedFile();
		Options.setLastDirectory(selectedFile.getParentFile().toPath());
		MSystemState state = pluginAction.getSession().system().state();
		List<MObject> objList = new ArrayList<>();
		List<MLink> linkList = new ArrayList<>();
		Map<String, Side> classMap = Main.getTggRuleCollection().getClassMap();
		if (choice == 0) {
			for (MClass cls : Main.getTggRuleCollection().getSourceModel().classes()) {
				objList.addAll(state.objectsOfClass(cls));
			}
			for (MLink lnk : state.allLinks()) {
				if (lnk.linkedObjects().stream().allMatch(it -> classMap.get(it.cls().name()) == Side.SOURCE))
					linkList.add(lnk);
			}
		} else {
			for (MClass cls : Main.getTggRuleCollection().getTargetModel().classes()) {
				objList.addAll(state.objectsOfClass(cls));
			}
			for (MLink lnk : state.allLinks()) {
				if (lnk.linkedObjects().stream().allMatch(it -> classMap.get(it.cls().name()) == Side.TARGET))
					linkList.add(lnk);
			}
		}
		try {
			PrintWriter writer = new PrintWriter(selectedFile);
			for (MObject obj : objList) {
				writer.println("!new " + obj.cls().name() + "('" + obj.name() + "')");
			}
			for (MLink lnk : linkList) {
				writer.println("!insert" + lnk.linkedObjects().stream().map(it -> it.name()).collect(Collectors.joining(", ", " (", ") ")) + "into " + lnk.association().name());
			}
			for (MObject obj : objList) {
				for (Map.Entry<MAttribute, Value> entry : obj.state(state).attributeValueMap().entrySet()) {
					writer.println("!" + obj.name() + "." + entry.getKey().name() + " := " + entry.getValue().toString());
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(pluginAction.getParent(), "An error occurred during the export: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(pluginAction.getParent(), "Export complete.");
	}

}
