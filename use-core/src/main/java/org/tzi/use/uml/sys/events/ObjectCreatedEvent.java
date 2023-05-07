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

package org.tzi.use.uml.sys.events;

import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.events.tags.EventContext;
import org.tzi.use.uml.sys.events.tags.SystemStructureChangedEvent;

/**
 * Information about the event of an object creation.
 * @author Daniel Gent
 * @author Lars Hamann
 *
 */
public class ObjectCreatedEvent extends Event implements SystemStructureChangedEvent {
	/** The created object */
	private MObject fCreatedObject;
	
	
	/**
	 * Constructs a new object creation event.
	 * @param createdObject The created object
	 */
	public ObjectCreatedEvent(EventContext ctx, MObject createdObject) {
		super(ctx);
		fCreatedObject = createdObject;
	}
	
	
	/**
	 * The created object
	 * @return The created object
	 */
	public MObject getCreatedObject() {
		return fCreatedObject;
	}
	
	
	@Override
	public String toString() {
		return "object creation event";
	}
}
