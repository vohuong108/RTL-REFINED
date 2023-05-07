package org.tzi.kodkod.comparison.ui.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.tzi.kodkod.comparison.ComparisonResult;
import org.tzi.kodkod.comparison.SettingsConfigurationComparator;
import org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration;

/**
 * The strucure to represent data of comparison results.
 * 
 * @author Jan Prien
 *
 */
public class MultipleComparisonResult {

	/**
	 * The configurations to potentially compare.
	 */
	private final ComparedSelection[] comparedSelections;

	/**
	 * The results of compariosn of the configurations.
	 */
	private final ComparisonResult[][] comparisonResults;

	/**
	 * The specifaction which configurations from {@link #comparedSelections} should
	 * be compared.
	 */
	private final Boolean[] selected;

	/**
	 * Constructs an object.
	 * 
	 * Compares the given configurations. Initially all configurations are compared.
	 * 
	 * @param configurationsForNames
	 *            The configurations that should be compared.
	 * @throws Exception
	 */
	public MultipleComparisonResult(final Map<String, SettingsConfiguration> configurationsForNames) throws Exception {
		List<ComparedSelection> comparedSelections = new ArrayList<>();
		for (Entry<String, SettingsConfiguration> configurationForName : configurationsForNames.entrySet()) {
			comparedSelections
					.add(new ComparedSelection(configurationForName.getKey(), configurationForName.getValue()));
		}
		final int comparedSelectionsCount = comparedSelections.size();

		this.comparedSelections = comparedSelections.toArray(new ComparedSelection[comparedSelectionsCount]);

		this.comparisonResults = new ComparisonResult[comparedSelectionsCount][comparedSelectionsCount];
		for (int rowIndex = 0; rowIndex < comparedSelectionsCount; rowIndex++) {
			final ComparedSelection rowComparedSelections = this.comparedSelections[rowIndex];
			for (int columnIndex = 0; columnIndex < comparedSelectionsCount; columnIndex++) {
				final ComparedSelection columnComparedSelections = this.comparedSelections[columnIndex];
				ComparisonResult comparisonResult = new SettingsConfigurationComparator().compareSettingsConfigurations(
						rowComparedSelections.getSettingsConfiguration(),
						columnComparedSelections.getSettingsConfiguration());
				this.comparisonResults[rowIndex][columnIndex] = comparisonResult;
			}
		}

		this.selected = new Boolean[comparedSelectionsCount];
		this.selectAll();
	}

	/**
	 * 
	 * @return Whether each comparison has the same result with inverted roles.
	 */
	public boolean isValidRegardingCounterparts() {
		for (int row = 0; row < comparedSelections.length; row++) {
			for (int column = 0; column < comparedSelections.length; column++) {
				if (!comparisonResults[row][column].isCounterpartOf(comparisonResults[column][row])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @return The names of the compared selections.
	 */
	public String[] getNames() {
		final int size = comparedSelections.length;
		final String[] names = new String[size];
		for (int index = 0; index < size; index++) {
			names[index] = comparedSelections[index].getName();
		}
		return names;
	}

	/**
	 * 
	 * @param name
	 *            The name of a configuration.
	 * @return The index of the configuration in {@link #comparedSelections}
	 */
	public int indexOf(final String name) {
		int index = -1;
		for (ComparedSelection comparedSelection : this.comparedSelections) {
			index++;
			if (comparedSelection.getName().equals(name)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param rowName
	 *            The name of a configuration.
	 * @param columnName
	 *            The name of a configuration.
	 * @return The result of the comparison of the configuration(s).
	 */
	public ComparisonResult getComparisonResult(String rowName, String columnName) {
		final int rowIndex = indexOf(rowName);
		final int columnIndex = indexOf(columnName);
		if (rowIndex < 0 || columnIndex < 0) {
			throw new IllegalArgumentException();
		}
		return comparisonResults[rowIndex][columnIndex];

	}

	/**
	 * 
	 * @return The indices of compared configurations.
	 */
	public Integer[] getSelectedIndexes() {
		final int size = comparedSelections.length;
		final List<Integer> selectedIndexes = new ArrayList<>();
		for (int index = 0; index < size; index++) {
			if (selected[index]) {
				selectedIndexes.add(index);
			}
		}
		return selectedIndexes.toArray(new Integer[selectedIndexes.size()]);
	}

	/**
	 * 
	 * @return The names of compared configuration.
	 */
	public String[] getSelectedNames() {
		final int size = comparedSelections.length;
		final List<String> selectedNames = new ArrayList<>();
		for (int index = 0; index < size; index++) {
			if (selected[index]) {
				selectedNames.add(comparedSelections[index].getName());
			}
		}
		return selectedNames.toArray(new String[selectedNames.size()]);
	}

	/**
	 * Sets whether a configuration is to be compared.
	 * 
	 * @param name
	 *            The name of the configuration.
	 * @param selected
	 *            Whether the configuration is to be compared.
	 * @return Whether a configuration is set to be compared.
	 */
	public boolean setSelected(final String name, final boolean selected) {
		final int size = comparedSelections.length;
		for (int index = 0; index < size; index++) {
			if (comparedSelections[index].getName().equals(name)) {
				this.selected[index] = selected;
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets whether configurations are to be compared. Sets all other configurations
	 * to not be compared.
	 * 
	 * @param selectedIndices
	 *            The indices of the configurations.
	 * @return Whether configurations are set to be compared.
	 */
	public boolean setExclusivelySelected(int[] selectedIndices) {
		if (selectedIndices != null) {
			int[] toBeSelectedIndices = new int[selectedIndices.length];
			for (int indexOfToBeSelected = 0; indexOfToBeSelected < selectedIndices.length; indexOfToBeSelected++) {
				int toBeSelectedIndex = selectedIndices[indexOfToBeSelected];
				if (toBeSelectedIndex >= 0 && toBeSelectedIndex < this.selected.length) {
					toBeSelectedIndices[indexOfToBeSelected] = toBeSelectedIndex;
				} else {
					return false;
				}
			}
			for (int indexOfSelected = 0; indexOfSelected < this.selected.length; indexOfSelected++) {
				boolean selected = false;
				for (int indexOfToBeSelected : toBeSelectedIndices) {
					selected = selected || indexOfToBeSelected == indexOfSelected;
				}
				this.selected[indexOfSelected] = selected;
			}
			return true;
		}
		return false;

	}

	/**
	 * Returns the families of configurations based on the comparison results.
	 * 
	 * @return
	 */
	public List<Set<String>> getFamilies() {
		List<Set<String>> families = new ArrayList<>();
		for (int index = 0; index < this.comparedSelections.length; index++) {
			String leftName = this.comparedSelections[index].getName();
			Set<String> leftFamily = getFirstListThatContains(families, leftName);
			for (int index2 = 0; index2 < this.comparedSelections.length; index2++) {
				if (index != index2) {
					String rightName = this.comparedSelections[index2].getName();
					Set<String> rightFamily = getFirstListThatContains(families, rightName);
					if (comparisonResults[index][index2].indicatesFamilyRelation()) {
						if (leftFamily == null && rightFamily == null) {
							Set<String> newFamily = new HashSet<String>();
							newFamily.add(leftName);
							newFamily.add(rightName);
							families.add(newFamily);
						} else if (rightFamily == null) {
							leftFamily.add(rightName);
						} else if (leftFamily == null) {
							rightFamily.add(leftName);
						} else if (leftFamily != rightFamily) {
							leftFamily.addAll(rightFamily);
							families.remove(rightFamily);
						}
					}
				}
			}
		}
		return families;
	}

	/**
	 * Returns the first set of list of strings that contains a string.
	 * 
	 * @param sets
	 *            The set of list of strings.
	 * @param string
	 *            The string.
	 * @return The first set of list of strings that contains the string.
	 */
	private Set<String> getFirstListThatContains(final List<Set<String>> sets, final String string) {
		if (sets != null) {
			for (Set<String> set : sets) {
				if (set.contains(string)) {
					return set;
				}
			}
		}
		return null;
	}

	/**
	 * Sets whether configurations are to be compared. Sets all other configurations
	 * to not be compared.
	 * 
	 * @param selectedNames
	 *            The names of the configurations.
	 * @return Whether configurations are set to be compared.
	 */
	public boolean setExclusivelySelected(String[] selectedNames) {
		int[] selectedIndices = new int[selectedNames.length];
		for (int index = 0; index < selectedIndices.length; index++) {
			int indexOfName = indexOf(selectedNames[index]);
			if (indexOfName > 0) {
				selectedIndices[index] = indexOfName;
			} else {
				return false;
			}
		}
		this.setExclusivelySelected(selectedIndices);
		return true;
	}

	/**
	 * Sets all configurations to not be compared.
	 */
	public void unselectAll() {
		for (int index = 0; index < this.selected.length; index++) {
			this.selected[index] = false;
		}
	}

	/**
	 * Sets all configurations to be compared.
	 */
	public void selectAll() {
		for (int index = 0; index < this.selected.length; index++) {
			this.selected[index] = true;
		}
	}

}
