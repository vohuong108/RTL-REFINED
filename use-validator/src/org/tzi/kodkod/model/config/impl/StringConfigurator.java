package org.tzi.kodkod.model.config.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import kodkod.instance.TupleFactory;
import kodkod.instance.TupleSet;

import org.tzi.kodkod.model.type.ConfigurableType;
import org.tzi.kodkod.model.type.TypeConstants;

/**
 * Configurator for the string type
 * 
 * @author Hendrik Reitmann
 */
public class StringConfigurator extends TypeConfigurator {

	@Override
	public TupleSet lowerBound(ConfigurableType type, int arity, TupleFactory tupleFactory) {
		int max = Integer.MAX_VALUE;
		if(ranges.size() > 0){
			// limit maximum amount of specific values
			max = ranges.get(0).getUpper();
		}
		
		TupleSet lowerTupleSet = tupleFactory.noneOf(1);
		Iterator<String[]> allValuesIterator = allValues().iterator();
		String[] specific;
		while(lowerTupleSet.size() < max && allValuesIterator.hasNext()) {
			specific = allValuesIterator.next();
			lowerTupleSet.add(tupleFactory.tuple(type.name() + "_" + specific[0]));
		}

		if(ranges.size() > 0){
			int i = allValues().size() + 1;
			while (lowerTupleSet.size() < max) {
				lowerTupleSet.add(tupleFactory.tuple(type.name() + "_string" + i));
				i++;
			}
		}

		return lowerTupleSet;
	}

	@Override
	public TupleSet upperBound(ConfigurableType type, int arity, TupleFactory tupleFactory) {
		TupleSet upperTupleSet = tupleFactory.noneOf(1);
		upperTupleSet.addAll(lowerBound(type, arity, tupleFactory));

		if(ranges.size() > 0){
			int max = ranges.get(0).getUpper();
			int i = allValues().size() + 1;
			while ( upperTupleSet.size() < max ) {
				upperTupleSet.add(tupleFactory.tuple(type.name() + "_string" + i));
				i++;
			}
		}

		return upperTupleSet;
	}

	@Override
	public Set<Object> atoms(ConfigurableType type, List<Object> literals) {
		Set<Object> atoms = new HashSet<Object>();
		atoms.addAll(literals);

		atoms.add(TypeConstants.STRING_TRUE);
		atoms.add(TypeConstants.STRING_FALSE);
		for (String[] specific : allValues()) {
			atoms.add(type.name() + "_" + specific[0]);
		}

		if(ranges.size() > 0){
			int max = ranges.get(0).getUpper();
			int i = allValues().size() + 1;
			int numAdded = allValues().size();
			while (numAdded < max) {
				if (atoms.add(type.name() + "_string" + i)) {
					numAdded++;
				}
				i++;
			}
		}

		return new LinkedHashSet<Object>(atoms);
	}
}
