package org.uet.dse.rtlplus.actions;

import java.io.PrintWriter;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.tzi.use.config.Options;
import org.tzi.use.gui.util.ExtFileFilter;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.uet.dse.rtlplus.parser.CESCompiler;
import org.uet.dse.rtlplus.parser.ast.coevol.AstCoevolScenario;

public class ActionRunCoevolScenario implements IPluginActionDelegate{

	@Override
	public void performAction(IPluginAction pluginAction) {
		JFileChooser fileChooser = new JFileChooser(Options.getLastDirectory().toString());
		ExtFileFilter filter = new ExtFileFilter("ces", "Co-evolution scenario file");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Select scenario to run");
		int returnVal = fileChooser.showOpenDialog(pluginAction.getParent());
		if (returnVal != JFileChooser.APPROVE_OPTION) 
			return;
		Path path = fileChooser.getCurrentDirectory().toPath();
		Options.setLastDirectory(path);
		AstCoevolScenario scenario = new CESCompiler().complieSpecification(fileChooser.getSelectedFile(), new PrintWriter(System.err));
		boolean res = scenario.execute(pluginAction.getSession().system().state(), pluginAction.getParent().logWriter());
		if (res)
			JOptionPane.showMessageDialog(pluginAction.getParent(), "All matches were successfully executed.", "Success", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(pluginAction.getParent(), "Some matches failed to run. See log window for info.", "Failed to run some matches", JOptionPane.WARNING_MESSAGE);
	}

}
