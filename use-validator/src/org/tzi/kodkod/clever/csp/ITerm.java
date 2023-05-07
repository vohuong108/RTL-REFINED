package org.tzi.kodkod.clever.csp;

import java.util.Map;
import java.util.Set;

/**
 * Term on variables.
 * 
 * @author Jan Prien
 *
 */
public interface ITerm {

	/**
	 * Returns the included variables.
	 * 
	 * @return The included variables.
	 */
	public Set<IVariable<?>> getVariables();

	/**
	 * Returns a textual representation.
	 * 
	 * @return A textual representation.
	 */
	public default String toRepresentation() {
		return toRepresentation(null);
	}

	/**
	 * Returns a textual representation. Instead of textual representations of
	 * variables, selected representations are used.
	 * 
	 * @param variableOverwriteNames
	 *            The representations for variables.
	 * @return A textual representation.
	 */
	public String toRepresentation(Map<IVariable<?>, String> variableOverwriteNames);

	/**
	 * Lets the visitor visit the term.
	 * 
	 * @param iTermVisitor
	 *            The visitor.
	 */
	void processWith(ITermVisitor iTermVisitor);

}
