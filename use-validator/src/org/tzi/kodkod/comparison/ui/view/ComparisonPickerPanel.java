package org.tzi.kodkod.comparison.ui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.tzi.kodkod.comparison.ui.UIElements;

/**
 * The panel for selecting configurations to compare.
 * 
 * @author Jan Prien
 *
 */
public class ComparisonPickerPanel extends AbstractComparisonJPanel implements ListSelectionListener {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 7040172007163089675L;

	/**
	 * The button that starts the comparison process with the configurations whose
	 * names are selected.
	 */
	private JButton applySelectionButton;

	/**
	 * The comparison data.
	 */
	private final MultipleComparisonResult multipleComparisonResultModel;

	/**
	 * The list selection of configuration names.
	 */
	private JList<String> jlist = null;

	/**
	 * The indexes of configuration names in the list selection of configuration
	 * names that are initially selected.
	 */
	private Integer[] initiallySelectedIndexes;

	/**
	 * Constructs an object.
	 * 
	 * @param multipleComparisonResultModel
	 *            The comparison data.
	 */
	public ComparisonPickerPanel(final MultipleComparisonResult multipleComparisonResultModel) {
		this.multipleComparisonResultModel = multipleComparisonResultModel;
		this.initiallySelectedIndexes = multipleComparisonResultModel.getSelectedIndexes();
		this.initGUI();
	}

	/**
	 * Adds an listener that is informed about the comparison needs to be processed
	 * again with the configurations whose names are selected.
	 * 
	 * @param applySelectionButtonListener
	 */
	public void addApplySelectionListener(final ActionListener applySelectionButtonListener) {
		this.applySelectionButton.addActionListener(applySelectionButtonListener);
	}

	@Override
	public void initGUI() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		final String[] sortedNames = multipleComparisonResultModel.getNames();
		this.jlist = new JList<>(sortedNames);
		this.setToInitiallySelectedIndexes();
		this.jlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.add(new JScrollPane(this.jlist), BorderLayout.CENTER);
		this.jlist.addListSelectionListener(this);

		this.applySelectionButton = new JButton(UIElements.ApplySelectionButton_Title);

		JPopupMenu popupMenu = new JPopupMenu();
		ComparisonPickerPanel dis = this;
		JMenuItem menuItemSelectAll = new JMenuItem(UIElements.SelectAllOption_Title);
		menuItemSelectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dis.selectAll();
			}
		});
		popupMenu.add(menuItemSelectAll);
		JMenuItem menuItemSelectNothing = new JMenuItem(UIElements.SelectNoneOption_Title);
		menuItemSelectNothing.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dis.selectNothing();
			}
		});
		popupMenu.add(menuItemSelectNothing);

		List<Set<String>> families = this.multipleComparisonResultModel.getFamilies();
		for (Set<String> family : families) {
			String text = "<html>" + UIElements.SelectFamilyButton_BaseTitle + "<br>";
			for (String member : family) {
				text += "- " + member + "<br>";
			}
			JMenuItem menuItemFamilyMember = new JMenuItem(text);
			menuItemFamilyMember.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dis.select(family);
				}
			});
			menuItemFamilyMember.setToolTipText(UIElements.SelectFamilyButton_Tooltip);
			popupMenu.add(menuItemFamilyMember);
		}

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton selectionOptions = new JButton(UIElements.SelectionOptionsButton_Title);
		selectionOptions.setToolTipText(UIElements.SelectionOptionsButton_Tooltip);
		selectionOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				popupMenu.show(selectionOptions, selectionOptions.getBounds().x,
						selectionOptions.getBounds().y + selectionOptions.getBounds().height);
			}
		});
		buttonPanel.add(selectionOptions);

		this.validateEnableApplySelectionButton();
		buttonPanel.add(applySelectionButton);

		this.add(buttonPanel, BorderLayout.SOUTH);

	}

	/**
	 * Sets the selected configuration names and unselects all other.
	 * 
	 * @param family
	 *            The configuration names that should be selected.
	 */
	protected void select(Set<String> family) {
		int[] selectedIndices = new int[family.size()];
		int index = -1;
		for (String member : family) {
			index++;
			selectedIndices[index] = this.multipleComparisonResultModel.indexOf(member);
		}
		this.jlist.setSelectedIndices(selectedIndices);
	}

	/**
	 * Sets all configuration names unselected.
	 */
	protected void selectNothing() {
		this.jlist.clearSelection();
	}

	/**
	 * Sets all configuration names selected.
	 */
	protected void selectAll() {

		int maxSelectedIndex = jlist.getVisibleRowCount();
		int[] selectedIndices = new int[maxSelectedIndex + 1];
		for (int index = 0; index < selectedIndices.length; index++) {
			selectedIndices[index] = index;
		}
		this.jlist.setSelectedIndices(selectedIndices);
	}

	/**
	 * Sets the configuration names selected that are identified by
	 * {@link #initiallySelectedIndexes}.
	 */
	private void setToInitiallySelectedIndexes() {
		int[] selectedIndices = new int[this.initiallySelectedIndexes.length];
		for (int index = 0; index < this.initiallySelectedIndexes.length; index++) {
			selectedIndices[index] = this.initiallySelectedIndexes[index];
		}
		this.jlist.setSelectedIndices(selectedIndices);
	}

	public JButton getApplySelectionButton() {
		return this.applySelectionButton;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == this.jlist) {
			this.multipleComparisonResultModel.setExclusivelySelected(this.jlist.getSelectedIndices());
			this.validateEnableApplySelectionButton();

		}
	}

	/**
	 * Validates whether {@link #applySelectionButton} should be enabled and sets
	 * its enabled value. The button should only be enabled when at least one
	 * configuration name is selected.
	 */
	private void validateEnableApplySelectionButton() {
		this.applySelectionButton.setEnabled(this.jlist.getSelectedIndices().length > 0);
	}

}
