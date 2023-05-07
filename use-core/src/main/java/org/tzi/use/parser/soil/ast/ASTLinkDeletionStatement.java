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

package org.tzi.use.parser.soil.ast;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.runtime.Token;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.sys.soil.MLinkDeletionStatement;
import org.tzi.use.uml.sys.soil.MRValue;
import org.tzi.use.util.StringUtil;
import org.tzi.use.util.soil.exceptions.CompilationFailedException;


/**
 * AST class representing a link deletion node: <code>delete (x, y) from Z</code>.
 * @author Daniel Gent
 *
 */
public class ASTLinkDeletionStatement extends ASTStatement {
	/**
	 * The name of the association to insert a link into
	 */
	private String fAssociationName;
	
	/** 
	 * The ASTRValues of the participating objects
	 */
	private List<ASTRValue> fParticipants;
	
	/**
	 * The List of the provided qualifiers
	 */
	private List<List<ASTRValue>> qualifierValues;
	
	/**
	 * Constructs a new AST node.
	 * @param associationName Name of the association to delete the link from.
	 * @param participants The participating instances.
	 * @param qualifierValues The <code>MRValue</code>s of the qualifiers. Can be <code>null</code>.
	 */
	public ASTLinkDeletionStatement(
			Token start,
			String associationName,
			List<ASTRValue> participants,
			List<List<ASTRValue>> qualifierValues) {
		super(start);
		fAssociationName = associationName;
		fParticipants = participants;
		this.qualifierValues = qualifierValues;
	}
	
	/**
	 * Name of the association to insert the link into.
	 * @return The association name
	 */
	public String getAssociationName() {
		return fAssociationName;
	}
	
	
	/**
	 * The participating instances.
	 * @return The <code>ASTRValue</code> nodes of participating instances.
	 */
	public List<ASTRValue> getParticipants() {
		return fParticipants;
	}

	
	@Override
	protected MLinkDeletionStatement generateStatement() throws CompilationFailedException {
		
		// generate association
		MAssociation association = 
			getAssociationSafe(fAssociationName);
				
		// generate participants
		List<MRValue> participants = 
			generateAssociationParticipants(
					association, 
					fParticipants);
		
		List<List<MRValue>> qualifierRValues;
		if (this.qualifierValues == null || this.qualifierValues.isEmpty()) {
			qualifierRValues = Collections.emptyList();
		} else {
			qualifierRValues = new ArrayList<List<MRValue>>();
			
			for (List<ASTRValue> endQualifierValues : this.qualifierValues ) {
				List<MRValue> endQualifierRValues;
				
				if (endQualifierValues == null || endQualifierValues.isEmpty()) {
					endQualifierRValues = Collections.emptyList();
				} else {
					endQualifierRValues = new ArrayList<MRValue>();
					
					for (ASTRValue value : endQualifierValues) {
						endQualifierRValues.add(value.generate(this));
					}
				}
				qualifierRValues.add(endQualifierRValues);
			}
		}
		
		return new MLinkDeletionStatement(association, participants, qualifierRValues);
	}


	@Override
	protected void printTree(StringBuilder prelude, PrintWriter target) {
		target.println(prelude + "[LINK DELETION]");
	}


	@Override
	public String toString() {
		StringBuilder sB = new StringBuilder();
		sB.append("delete ");
		sB.append("(");
		
		StringUtil.fmtSeqWithSubSeq(sB, fParticipants, ",", qualifierValues, ",", "{", "}");
		
		sB.append(") ");
		sB.append("from ");
		sB.append(fAssociationName);
		
		return sB.toString();
	}
}
