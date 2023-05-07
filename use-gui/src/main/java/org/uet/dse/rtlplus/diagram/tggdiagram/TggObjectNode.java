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

// $Id: ObjectNode.java 61 2008-04-11 11:52:15Z opti $

package org.uet.dse.rtlplus.diagram.tggdiagram;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.tzi.use.gui.main.ModelBrowserSorting;
import org.tzi.use.gui.main.ModelBrowserSorting.SortChangeEvent;
import org.tzi.use.gui.main.ModelBrowserSorting.SortChangeListener;
import org.tzi.use.uml.mm.MAttribute;
import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.ocl.value.EnumValue;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MObjectState;
import org.tzi.use.uml.sys.MSystemState;
import org.uet.dse.rtlplus.diagram.DiagramOptions;
import org.uet.dse.rtlplus.diagram.NodeBase;

/**
 * Duc-Hanh Dang
 */
public class TggObjectNode extends NodeBase implements SortChangeListener {

	private DiagramOptions fOpt;
	private TggDiagramView fParent;
	private MObject fObject;
	private String fLabel;
	private List<MAttribute> fAttributes;
	private String[] fValues;

	private int fGroupId;// 1: SL; 2: CL; 3: TL; 4:SR; 5: CR; 6: TR (Nondeleting Objects)
							// -1: SL; -2: CL; -3: TL; (Deleting Object)

	protected Rectangle2D.Double nameRect = new Rectangle2D.Double();
	protected Rectangle2D.Double attributesRect = new Rectangle2D.Double();

	public TggObjectNode(MObject obj, int groupId, TggDiagramView parent, DiagramOptions opt) {
		fObject = obj;
		fGroupId = groupId;
		fParent = parent;
		fOpt = opt;
		// fOpt.setShowAttributes(true);
		fOpt.setShowAssocNames(false);
		MClass cls = obj.cls();
		fLabel = obj.name() + ":" + cls.name();// "(" + groupId + ") "
		List<MAttribute> allAttributes = cls.allAttributes();
		final int N = allAttributes.size();
		fValues = new String[N];

		fAttributes = ModelBrowserSorting.getInstance().sortAttributes(allAttributes);
		ModelBrowserSorting.getInstance().addSortChangeListener(this);

	}

	public MObject object() {
		return fObject;
	}

	public MClass cls() {
		return fObject.cls();
	}

	public String name() {
		return fObject.name();
	}

	public MSystemState getSystemStateLHS() {
		return fParent.getTggRule().getSystemStateLHS();
	}

	public MSystemState getSystemStateRHS() {
		return fParent.getTggRule().getSystemStateRHS();
	}

	public int getGroupId() {
		return fGroupId;
	}

	/**
	 * After the occurence of an event the attribute list is updated.
	 */
	public void stateChanged(SortChangeEvent e) {
		fAttributes = ModelBrowserSorting.getInstance().sortAttributes(fAttributes);
	}

	/**
	 * Sets the correct size of the width and height of this object node. This
	 * method needs to be called before actually drawing the node. (Width and height
	 * are needed from other methods before the nodes are drawn.)
	 */
	public void setRectangleSize(Graphics2D g) {
		FontMetrics fm = g.getFontMetrics();

		nameRect.width = fm.stringWidth(fLabel);
		nameRect.height = fm.getHeight();

		String value;
		MObjectState objState = fObject.state(this.getSystemStateRHS());
		for (int i = 0; i < fAttributes.size(); i++) {
			MAttribute attr = (MAttribute) fAttributes.get(i);
			Value val = (Value) objState.attributeValue(attr);

			if (val instanceof EnumValue) {
				value = "#" + ((EnumValue) val).value();
			} else {
				value = val.toString();
				if (value.matches("Undefined"))
					value = "";
			}

			fValues[i] = attr.name() + ((value.length() == 0) ? "" : "=" + value);

			attributesRect.width = Math.max(attributesRect.width, fm.stringWidth(fValues[i]));
		}

		attributesRect.height = fm.getHeight() * fAttributes.size() + 3;
		calculateBounds();
	}

	protected void calculateBounds() {
		double width = nameRect.width;
		double height = nameRect.height;

		if (fOpt.isShowAttributes()) {
			width = Math.max(width, attributesRect.width);
			height += attributesRect.height;
		}

		height += 4;
		width += 10;

		height = Math.max(height, getMinHeight());
		width = Math.max(width, getMinWidth());

		bounds.width = width;
		bounds.height = height;
	}

	public boolean isDeletable() {
		return true;
	}

	public String ident() {
		return "Object." + fObject.name();
	}

	public String identNodeEdge() {
		return "OjectLink." + fObject.name();
	}

	public boolean checkNewPositition(double x, double y) {
		// fGroupId
		// 1: SL; 2: CL; 3: TL; 4:SR; 5: CR; 6: TR (Nondeleting Objects)
		// -1: SL; -2: CL; -3: TL; (Deleting Object)
		// x = x - getWidth()/2;
		// y = y - getHeight()/2;
		boolean ok = true;
		switch (fGroupId) {
		case -1:
		case 1:
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinSL(x, y);
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinSL(x + getWidth(), y + getHeight());
			break;
		case -2:
		case 2:
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinCL(x, y);
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinCL(x + getWidth(), y + getHeight());
			break;
		case -3:
		case 3:
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinTL(x, y);
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinTL(x + getWidth(), y + getHeight());
			break;
		case 4:
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinSR(x, y);
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinSR(x + getWidth(), y + getHeight());
			break;
		case 5:
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinCR(x, y);
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinCR(x + getWidth(), y + getHeight());
			break;
		case 6:
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinTR(x, y);
			ok = ok && this.fParent.getTggDiagram().getTggSwimlane().isWithinTR(x + getWidth(), y + getHeight());
			break;
		default:
			ok = false;
		}
		return ok;
	}

	@Override
	protected void onDraw(Graphics2D g) {
		int x = (int) getX();
		int y = (int) getY();

		// calculateBounds();

		Polygon dimension = dimension();
		Rectangle2D bounds = getBounds();

		int labelWidth = g.getFontMetrics().stringWidth(fLabel);

		if (isSelected()) {
			g.setColor(fOpt.getNODE_SELECTED_COLOR());
		} else {
			g.setColor(fOpt.getNODE_COLOR());
		}
		g.fillPolygon(dimension);
		g.setColor(fOpt.getNODE_FRAME_COLOR());
		g.drawPolygon(dimension);

		if (fGroupId < 0) { // deleting nodes
			g.drawString("--", (int) x, (int) (y - 3));
		}

		if (fGroupId > 3) { // nondeleting nodes
			g.drawString("++", (int) (x), (int) (y - 3));
		}

		x = (int) bounds.getCenterX() - labelWidth / 2;
		y = (int) bounds.getY() + g.getFontMetrics().getAscent() + 2;
		g.setColor(fOpt.getNODE_LABEL_COLOR());
		g.drawString(fLabel, x, y);
		// underline label
		// g.drawLine( x, y + 1, x + labelWidth, y + 1 );

		if (fOpt.isShowAttributes()) {
			// compartment divider
			x = (int) getBounds().getCenterX();
			g.drawLine((int) bounds.getX(), y + 3, (int) bounds.getMaxX() - 1, y + 3);
			x -= (getWidth() - 10) / 2;
			y += 3;
			for (int i = 0; i < fAttributes.size(); i++) {
				y += g.getFontMetrics().getHeight();
				g.drawString(fValues[i], x, y);
			}
		}
	}

	@Override
	protected String getStoreType() {
		return "Object";
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
