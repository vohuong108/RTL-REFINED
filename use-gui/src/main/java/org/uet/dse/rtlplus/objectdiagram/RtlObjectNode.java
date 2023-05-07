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

// $Id: ObjectNode.java 5494 2015-02-05 12:59:25Z lhamann $

package org.uet.dse.rtlplus.objectdiagram;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.tzi.use.gui.views.diagrams.objectdiagram.ObjDiagramOptions;
import org.tzi.use.gui.views.diagrams.objectdiagram.ObjectNode;
import org.tzi.use.gui.views.diagrams.util.Util;
import org.tzi.use.uml.sys.MObject;
import org.uet.dse.rtlplus.mm.MRuleCollection;

/**
 * A node representing an object.
 * 
 * @author Fabian Gutsche
 * @author Lars Hamann
 */
public class RtlObjectNode extends ObjectNode {

	private final RtlObjectDiagramView fParent;
	private MRuleCollection.Side side;
	private boolean matched = false;
	private static Color matchedColor = new Color(90, 255, 164);

	public RtlObjectNode(MObject obj, MRuleCollection.Side side, RtlObjectDiagramView parent, ObjDiagramOptions opt) {
		super(obj, parent, opt);
		this.side = side;
		fParent = parent;
	}
	
	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	/**
	 * Draws a box with an underlined label.
	 */
	@Override
	protected void onDraw(Graphics2D g) {
		Rectangle2D.Double currentBounds = getRoundedBounds();

		if (!Util.enlargeRectangle(currentBounds, 10).intersects(g.getClipBounds())) {
			return;
		}

		double x = currentBounds.getX();
		int y = (int) currentBounds.getY();

		int labelWidth = g.getFontMetrics().stringWidth(fLabel);

		if (isSelected()) {
			g.setColor(fOpt.getNODE_SELECTED_COLOR());
		} else {
			if (matched)
				g.setColor(matchedColor);
			else
				g.setColor(fOpt.getNODE_COLOR());
		}
		g.fill(currentBounds);
		g.setColor(fOpt.getNODE_FRAME_COLOR());
		g.draw(currentBounds);

		x = (currentBounds.getCenterX() - labelWidth / 2);
		y = (int) currentBounds.getY() + g.getFontMetrics().getAscent() + 2;

		//g.setColor(fOpt.getColor(DiagramOptions.NODE_LABEL_COLOR));
		g.drawString(fLabelA.getIterator(), Math.round(x), y);

		if (fOpt.isShowAttributes()) {
			// compartment divider
			Line2D.Double lineAttrDivider = new Line2D.Double(currentBounds.getX(), y + 3, currentBounds.getMaxX(),
					y + 3);
			g.draw(lineAttrDivider);
			x = currentBounds.getX() + 5;
			y += 3;
			for (int i = 0; i < fAttributes.size(); i++) {
				y += g.getFontMetrics().getHeight();
				g.drawString(fValues[i], Math.round(x), y);
			}
		}

		if (fOpt.isShowStates()) {
			// compartment divider
			Line2D.Double lineAttrDivider = new Line2D.Double(currentBounds.getX(), y + 3, currentBounds.getMaxX(),
					y + 3);
			g.draw(lineAttrDivider);
			x = currentBounds.getX() + 5;
			y += 3;
			for (int i = 0; i < fStateValues.length; i++) {
				y += g.getFontMetrics().getHeight();
				g.drawString(fStateValues[i], Math.round(x), y);
			}
		}
	}

	public boolean checkNewPositition(double d1, double d2) {
		RtlSwimlane swimlane = fParent.getDiagram().getSwimlane(); 
		return ( ( swimlane.getSide(d1) == side) 
				&& (swimlane.getSide(d2) == side) );
	}
}
