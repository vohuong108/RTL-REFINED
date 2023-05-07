package org.tzi.kodkod.model.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.IntExpression;
import kodkod.ast.Relation;
import kodkod.instance.Tuple;
import kodkod.instance.TupleFactory;
import kodkod.instance.TupleSet;

import org.apache.log4j.Logger;
import org.tzi.kodkod.helper.PrintHelper;
import org.tzi.kodkod.model.iface.IAssociation;
import org.tzi.kodkod.model.iface.IAssociationEnd;
import org.tzi.kodkod.model.iface.IClass;
import org.tzi.kodkod.model.impl.Multiplicity;
import org.tzi.kodkod.model.type.TypeConstants;

/**
 * Configurator for associations.
 * 
 * @author Hendrik Reitmann
 * 
 */
public class AssociationConfigurator extends Configurator<IAssociation> {

	private int min, max;

	private static final Logger LOG = Logger.getLogger(AssociationConfigurator.class);

	private boolean unboundedNumberOfLinks = true;

	@Override
	public TupleSet lowerBound(IAssociation association, int arity, TupleFactory tupleFactory) {
		TupleSet lower = tupleFactory.noneOf(arity);

		List<IAssociationEnd> allAssociationEnds = new ArrayList<IAssociationEnd>(association.associationEnds());
		if (association.associationClass() != null) {
			allAssociationEnds.add(0, association.associationClass());
		}

		List<String> atoms;

		for (String[] specific : specificValues) {
			atoms = new ArrayList<String>();
			for (int i = 0; i < arity; i++) {
				/*
				 * FIXME Automatic Diagram Extraction and properties file create different models.
				 * One adds the class name for links and the other does not. There might also be
				 * further clashes regarding attribute definitions and so on.
				 */
				if(tupleFactory.universe().contains(allAssociationEnds.get(i).associatedClass().name() + "_" + specific[i])){
					atoms.add(allAssociationEnds.get(i).associatedClass().name() + "_" + specific[i]);
				} else {
					atoms.add(specific[i]);
				}
			}
			lower.add(tupleFactory.tuple(atoms));
		}

		return lower;
	}

	@Override
	public TupleSet upperBound(IAssociation association, int arity, TupleFactory tupleFactory) {
		TupleSet upper = null;

		boolean hasZeroOneEnd = false;
		TupleSet current;
		IClass associationClass = association.associationClass();
		
		for (IAssociationEnd associationEnd : association.associationEnds()) {
			current = tupleFactory.noneOf(1);
			current.addAll(getAssociatedClassUpperBound(tupleFactory, associationEnd.associatedClass()));
			if (associationEnd.multiplicity().isZeroOne() && association.isBinaryAssociation()) {
				if(associationClass == null){
					current.add(tupleFactory.tuple(TypeConstants.UNDEFINED));
				}
				hasZeroOneEnd = true;
			}
			if (upper == null) {
				upper = current;
			} else {
				upper = upper.product(current);
			}
		}
		
		// remove possible all-null tuple [Undefined, Undefined, ...]
		Tuple t = tupleFactory.tuple(TypeConstants.UNDEFINED);
		for(int i = 1; i < upper.arity(); i++){
			t = t.product(tupleFactory.tuple(TypeConstants.UNDEFINED));
		}
		upper.remove(t);

		if (associationClass != null) {
			TupleSet associationClassTuples = associationClass.upperBound(tupleFactory);
			upper = associationClassTuples.product(upper);
			
			/*
			 * create undefined tuples for objects linked with 0..1 association
			 * ends so that the navigation explicitly results in Undefined
			 */
			if (hasZeroOneEnd && association.isBinaryAssociation()) {
				int rolepositionInTuple = 1;
				TupleSet assocClause;
				final TupleSet undefinedTupleSet = tupleFactory.noneOf(1);
				undefinedTupleSet.add(tupleFactory.tuple(TypeConstants.UNDEFINED));
				
				for (IAssociationEnd associationEnd : association.associationEnds()) {
					if(associationEnd.multiplicity().isZeroOne()){
						rolepositionInTuple++;
						continue;
					}
					
					assocClause = undefinedTupleSet;

					// index 0 is the linkobject itself
					for(int i = 1; i < upper.arity(); i++) {
						if(i == rolepositionInTuple){
							assocClause = assocClause.product(getAssociatedClassUpperBound(tupleFactory, associationEnd.associatedClass()));
						}
						else {
							assocClause = assocClause.product(undefinedTupleSet);
						}
					}
					
					upper.addAll(assocClause);
					rolepositionInTuple++;
				}
			}
		}

		return upper;
	}

	/**
	 * Returns the upper bound of the given class.
	 * 
	 * @param tupleFactory
	 * @param associatedClass
	 * @return
	 */
	private TupleSet getAssociatedClassUpperBound(TupleFactory tupleFactory, IClass associatedClass) {
		if (associatedClass.existsInheritance()) {
			return associatedClass.inheritanceUpperBound(tupleFactory);
		} else {
			return associatedClass.upperBound(tupleFactory);
		}
	}

	@Override
	public Formula constraints(IAssociation association) {
		return numberOfLinks(association);
	}

	@Override
	public void setSpecificValues(Collection<String[]> specificValues) {
		super.setSpecificValues(specificValues);
		setLimits(specificValues.size(), specificValues.size());
	}

	/**
	 * Sets the number of links.
	 * 
	 * @param min
	 * @param max
	 */
	public void setLimits(int min, int max) {
		if (min >= specificValues.size()) {
			this.min = min;
		}

		if (max >= specificValues.size() && max >= min) {
			this.max = max;
		} else if (max <= min) {
			if (max == -1) {
				this.max = max;
			} else {
				this.max = min;
			}
		}
		if (max != -1) {
			unboundedNumberOfLinks = false;
		} else {
			unboundedNumberOfLinks = true;
		}
	}

	/**
	 * Creates the formula for the number of links.
	 * 
	 * @param association
	 * @return
	 */
	private Formula numberOfLinks(IAssociation association) {
		Relation relation = association.relation();
		Relation undefined = association.model().typeFactory().undefinedType().relation();
		IntExpression numberOfLinks = relation.count();

		Formula formula = null;
		if (association.isBinaryAssociation()) {
			Multiplicity leftMultiplicity = association.associationEnds().get(0).multiplicity();
			Multiplicity rightMultiplicity = association.associationEnds().get(1).multiplicity();
			if (leftMultiplicity.isZeroOne() && !rightMultiplicity.isZeroOne()) {
				numberOfLinks = Expression.UNIV.difference(undefined).join(relation).count();

			} else if (!leftMultiplicity.isZeroOne() && rightMultiplicity.isZeroOne()) {
				numberOfLinks = relation.join(Expression.UNIV.difference(undefined)).count();

			} else if (leftMultiplicity.isZeroOne() && rightMultiplicity.isZeroOne()) {
				numberOfLinks = Expression.UNIV.difference(undefined).join(relation).difference(undefined).count();
			}
		}

		Formula minFormula = Formula.TRUE;
		if (min != 0) {

			if (min == max) {
				formula = numberOfLinks.eq(IntConstant.constant(min));
			}
			minFormula = numberOfLinks.gte(IntConstant.constant(min));
		}

		Formula maxFormula = Formula.TRUE;
		if (!unboundedNumberOfLinks) {
			maxFormula = numberOfLinks.lte(IntConstant.constant(max));
		}

		if (formula == null) {
			formula = minFormula.and(maxFormula);
		}

		LOG.debug("Quantity of " + association.name() + ": " + PrintHelper.prettyKodkod(formula));

		return formula;
	}
}
