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

package org.uet.dse.rtlplus.diagram.waypoints;

import org.uet.dse.rtlplus.diagram.DiagramOptions;
import org.uet.dse.rtlplus.diagram.EdgeBase;
import org.uet.dse.rtlplus.diagram.NodeBase;

/**
 * Way point attached to the source node
 * @author lhamann
 *
 */
public class SourceWayPoint extends AttachedWayPoint {
	
	public SourceWayPoint(NodeBase source, NodeBase target, EdgeBase edge,
			int id, String edgeName, DiagramOptions opt) {
		super(source, target, edge, id, WayPointType.SOURCE, edgeName, opt);
	}
	
	/* (non-Javadoc)
	 * @see org.tzi.use.gui.views.diagrams.waypoints.AttachedWayPoint#getAttachedNode()
	 */
	@Override
	protected NodeBase getAttachedNode() {
		return this.fEdge.getDrawingSource();
	}
}
