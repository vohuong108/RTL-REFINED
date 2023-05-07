package org.tzi.kodkod.model.impl;

import kodkod.ast.Relation;

import org.tzi.kodkod.model.iface.IModel;
import org.tzi.kodkod.model.iface.IModelElement;

/**
 * Abstract implementation of IModelElement.
 * 
 * @author Hendrik Reitmann
 * 
 */
public abstract class ModelElement implements IModelElement {

	private final String name;
	protected Relation relation;
	protected IModel model;

	ModelElement(IModel model, final String name) {
		this.model = model;
		this.name = name;
		resetConfigurator();
	}	
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public Relation relation() {
		return relation;
	}

	@Override
	public IModel model() {
		return model;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this )
            return true;
    	if (obj == null)
    		return false;
    	
        if (obj instanceof ModelElement)
            return name.equals(((ModelElement) obj).name());
        
        return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
}
