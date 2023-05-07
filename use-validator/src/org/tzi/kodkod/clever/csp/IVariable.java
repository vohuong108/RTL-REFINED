package org.tzi.kodkod.clever.csp;

import java.util.HashSet;
import java.util.Set;

/**
 * A Constraint Satisfaction Problem (CSP) variable.
 * 
 * A variable is an unary term. It is mapped to an element, for that it
 * represents a variable.
 * 
 * @author Jan Prien
 *
 * @param <A>
 *            The type of the domain.
 */
public interface IVariable<A extends AbstractDomain<?>> extends IReferenceHolder<A>, ITerm {

	@SuppressWarnings("unchecked")
	@Override
	public default Set<IVariable<?>> getVariables() {
		Set<IVariable<?>> values = new HashSet<>();
		values.add((IVariable<AbstractDomain<?>>) this);
		return values;
	}

	/**
	 * Lets the visitor visit the variable.
	 * 
	 * @param IVariableVisitor
	 *            The visitor.
	 */
	public void processWith(IVariableVisitor iVariableVisitor);

	/**
	 * Returns whether it is a constant.
	 * 
	 * @return Whether it is a constant.
	 */
	public boolean isConstant();
}
