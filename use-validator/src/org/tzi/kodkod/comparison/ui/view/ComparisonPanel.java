package org.tzi.kodkod.comparison.ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * The panel for the functionality for clever generation of instance finder
 * configurations.
 * 
 * @author Jan Prien
 *
 */
public class ComparisonPanel extends AbstractComparisonJPanel implements ActionListener {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 7273233479558856401L;

	/**
	 * The comparison data.
	 */
	private final MultipleComparisonResult multipleComparisonResultModel;

	/**
	 * The panel for selecting configurations to compare.
	 */
	private ComparisonPickerPanel comparisonPickerPanel;

	/**
	 * The panel presenting comparison results as a matrix.
	 */
	private ComparisonMatrixPanel comparisonMatrixPanel;

	/**
	 * Constructs an object.
	 * 
	 * @param configurationsForNames
	 *            The configurations to be compared, identified by their names.
	 * @throws Exception
	 *             If comparison fails.
	 */
	public ComparisonPanel(final Map<String, SettingsConfiguration> configurationsForNames) throws Exception {
		this.multipleComparisonResultModel = new MultipleComparisonResult(configurationsForNames);
		if (!multipleComparisonResultModel.isValidRegardingCounterparts()) {
			throw new UnsupportedOperationException();
		}
		this.initGUI();
	}

	@Override
	public void initGUI() {
		this.removeAll();
		this.setLayout(new BorderLayout());

		this.comparisonMatrixPanel = new ComparisonMatrixPanel(this.multipleComparisonResultModel);
		JScrollPane comparisonMatrixPanelInScrollPane = new JScrollPane(this.comparisonMatrixPanel);
		this.comparisonPickerPanel = new ComparisonPickerPanel(this.multipleComparisonResultModel);
		this.comparisonPickerPanel.addApplySelectionListener(this);
		JSplitPane horizontalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.comparisonPickerPanel,
				comparisonMatrixPanelInScrollPane);
		horizontalSplitPane.setOneTouchExpandable(true);
		this.add(horizontalSplitPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.comparisonPickerPanel.getApplySelectionButton()) {
			this.comparisonMatrixPanel.reinitializeGUI();
		}
	}

}
