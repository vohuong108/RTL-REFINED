package org.tzi.kodkod.clever.csp;

public interface IConstraint extends ITerm {

	default void processWith(IConstraintVisitor iConstraintVisitor) {
		processWith((ITermVisitor) iConstraintVisitor);
	}

}
