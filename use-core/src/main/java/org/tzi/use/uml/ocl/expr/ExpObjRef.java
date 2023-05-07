/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2010 Mark Richters, University of Bremen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

// $Id$

package org.tzi.use.uml.ocl.expr;

import org.tzi.use.uml.ocl.value.ObjectValue;
import org.tzi.use.uml.sys.MObject;


/**
 * Expression for a named object like <code>@ada</code>.
 * @author Daniel Gent
 * @author Lars Hamann
 */
public class ExpObjRef extends Expression {
	/** The object */
	private MObject fObject;
	
	
	/**
	 * Constructs a new object reference expression node.
	 * @param object The referenced object
	 */
	public ExpObjRef(MObject object) {
		super(object.cls());
		fObject = object;
	}

	/**
	 * Returns the {@link MObject} object of this expression.
	 * @return the object
	 */
	public MObject getObject(){
		return fObject;
	}
	
	@Override
	public ObjectValue eval(EvalContext ctx) {
		ctx.enter(this);
		ObjectValue result  = fObject.value();
		ctx.exit(this, result);
		return result;
	}
	

	@Override
	public StringBuilder toString(StringBuilder sb) {
		return sb.append("@")
		  		 .append(fObject.name());
	}


	/* (non-Javadoc)
	 * @see org.tzi.use.uml.ocl.expr.Expression#processWithVisitor(org.tzi.use.uml.ocl.expr.ExpressionVisitor)
	 */
	@Override
	public void processWithVisitor(ExpressionVisitor visitor) {
		visitor.visitObjRef(this);
	}


	/* (non-Javadoc)
	 * @see org.tzi.use.uml.ocl.expr.Expression#childExpressionRequiresPreState()
	 */
	@Override
	protected boolean childExpressionRequiresPreState() {
		return false;
	}
}
