/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2004 Mark Richters, University of Bremen
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

// $Id: Multiplicity.java 2432 2011-08-23 11:13:01Z lhamann $

package org.uet.dse.rtlplus.diagram;

import org.tzi.use.gui.views.diagrams.util.Direction;
import org.tzi.use.uml.mm.MAssociationEnd;
import org.uet.dse.rtlplus.diagram.waypoints.WayPoint;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Represents a Multiplicity node in a diagram.
 * 
 * @version $ProjectVersion: 0.393 $
 * @author Fabian Gutsche
 */
public final class Multiplicity extends EdgeProperty {
	MAssociationEnd fAssocEnd;

	Multiplicity(MAssociationEnd assocEnd, NodeBase source, NodeBase target, EdgeBase edge, WayPoint sourceNode,
			WayPoint targetNode, DiagramOptions opt, int side) {
		super(source, sourceNode, target, targetNode, false);

		fAssocEnd = assocEnd;
		fName = fAssocEnd.multiplicity().toString();

		fAssoc = fAssocEnd.association();
		fEdge = edge;
		fOpt = opt;
		fSide = side;

		source.addPositionChangedListener(new PositionChangedListener<PlaceableNode>() {
			@Override
			public void positionChanged(PlaceableNode source, Point2D newPosition, double deltaX, double deltaY) {
				Multiplicity.this.calculatePosition(deltaX, deltaY);
			}
		});
	}

	@Override
	protected void onFirstDraw(Graphics2D g) {
		setRectangleSize(g);
		super.onFirstDraw(g);
	}

	@Override
	protected Point2D getDefaultPosition() {
		Direction targetLocation = Direction.getDirection(sourceWayPoint.getCenter(), targetWayPoint.getCenter());
		Point2D.Double result = new Point2D.Double();

		// simple approximation of multiplicity placement
		double fn1H = fSource.getHeight() / 2;

		if (targetLocation.isLocatedSouth()) {
			result.y = sourceWayPoint.getY() + fn1H + 15;
		} else {
			result.y = sourceWayPoint.getY() - fn1H - 10;
		}

		if (targetLocation.isLocatedEast()) {
			result.x = sourceWayPoint.getX() - getBounds().getWidth() - 2;
		} else {
			result.x = sourceWayPoint.getX() + 2;
		}

		return result;
	}

	/**
	 * Draws a multiplicity on a binary edge.
	 */
	@Override
	protected void onDraw(Graphics2D g) {
		setColor(g);

		if (isSelected()) {
			drawSelected(g);
		}

		drawTextCentered(g);

		resetColor(g);
	}

	@Override
	public String getStoreType() {
		return "multiplicity";
	}

	@Override
	protected String getStoreKind() {
		return fSide == SOURCE_SIDE ? "source" : "target";
	}

	private boolean resizeAllowed = true;

	@Override
	public void setResizeAllowed(boolean allowed) {
		resizeAllowed = allowed;
	}

	@Override
	public boolean getResizeAllowed() {
		return resizeAllowed;
	}
}
