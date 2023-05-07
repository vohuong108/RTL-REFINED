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

// $Id: DiagramInputHandling.java 6289 2017-11-27 09:17:09Z andreask $

package org.uet.dse.rtlplus.objectdiagram;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Set;

import org.tzi.use.gui.util.Selection;
import org.tzi.use.gui.views.diagrams.SelectionBox;
import org.tzi.use.gui.views.diagrams.elements.CommentNode;
import org.tzi.use.gui.views.diagrams.elements.DiamondNode;
import org.tzi.use.gui.views.diagrams.elements.EdgeProperty;
import org.tzi.use.gui.views.diagrams.elements.PlaceableNode;
import org.tzi.use.gui.views.diagrams.elements.ResizeNode;
import org.tzi.use.gui.views.diagrams.elements.edges.EdgeBase;
import org.tzi.use.gui.views.diagrams.event.DiagramInputHandling;
import org.uet.dse.rtlplus.objectdiagram.RtlSwimlane.SwimlaneLine;

/**
 * Handles the mouse movements and keyboard inputs of the class and object
 * diagram.
 * 
 * @version $ProjectVersion: 0.393 $
 * @author Fabian Gutsche
 */
public final class RtlDiagramInputHandling extends DiagramInputHandling {

	private final RtlObjectDiagram fDiagram;
    private final Selection<PlaceableNode> fNodeSelection;
    private final Selection<EdgeBase> fEdgeSelection;

    private SelectionBox selectionRectangle = null;

	private enum DragMode {
		DRAG_NONE, DRAG_ITEMS, RESIZE_ITEM;
	}
    
    private ResizeNode resizeNode = null;
    	
    // needed for mouse handling
    
    private Point    fDragStart;
    private DragMode fDragMode;
    private boolean  fIsDragging = false;

	private RtlSwimlane swimlane;
	private SwimlaneLine currentLine;

	public RtlDiagramInputHandling(Selection<PlaceableNode> nodeSelection, Selection<EdgeBase> edgeSelection,
			RtlObjectDiagram diagram, RtlSwimlane swimlane) {
		super(nodeSelection, edgeSelection, diagram);
		fNodeSelection = nodeSelection;
		fEdgeSelection = edgeSelection;
		fDiagram = diagram;
		this.swimlane = swimlane;
	}

	/**
	 * event: on item not on item ------------------------------------------- b1:
	 * select item clear selection deselect others S-b1: extend selection -
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (fDiagram.maybeShowPopup(e))
			return;

		fDiagram.addMouseMotionListener(this);
		fDiagram.stopLayoutThread();
		int modifiers = e.getModifiersEx();

		// mouse over node?
		PlaceableNode pickedObjectNode = fDiagram.findNode(e.getX(), e.getY());
		// mouse over edge?
		EdgeBase pickedEdge = fDiagram.findEdge(e.getX(), e.getY());

		// double click on EdgeProperty than reposition.
		if (e.getClickCount() == 2 && modifiers == InputEvent.BUTTON1_DOWN_MASK) {
			if (pickedObjectNode instanceof EdgeProperty) {
				((EdgeProperty) pickedObjectNode).setToAutoPosition();
				fDiagram.repaint();
				return;
			} else if (pickedObjectNode instanceof ResizeNode) {
				((ResizeNode) pickedObjectNode).setToCalculatedSize();
				fDiagram.invalidateNode(((ResizeNode) pickedObjectNode).getNodeToResize());
				fDiagram.repaint();
				return;
			} else if (pickedObjectNode instanceof DiamondNode) {
				((DiamondNode) pickedObjectNode).resetPositionStrategy();
				fDiagram.invalidateNode(pickedObjectNode);
				fDiagram.repaint();
			} else if (pickedObjectNode instanceof CommentNode) {
				// Contextmenuitem for CommentNode
				((CommentNode) pickedObjectNode).setEditable();
				fDiagram.repaint();
				return;
			}
			// is there an edge place a node, which can be moved.
			if (pickedEdge != null) {
				fDiagram.addWayPoint(e.getX(), e.getY());
				return;
			}
		}

		switch (modifiers) {
		case InputEvent.BUTTON1_DOWN_MASK:
			if (pickedObjectNode != null) {
				// If this item is not currently selected, remove all
				// other items from the selection and only select this
				// item. If this item is currently selected, do
				// nothing (and also leave all other selected items
				// untouched). In any case this click may be used to
				// start dragging selected items.
				if (!(pickedObjectNode instanceof ResizeNode) && !fNodeSelection.isSelected(pickedObjectNode)) {
					// clear selection
					fNodeSelection.clear();
					fEdgeSelection.clear();
					// add this component as the only selected item
					fNodeSelection.add(pickedObjectNode);
					fDiagram.repaint();
				}
				// subsequent dragging events will move selected items
				if (pickedObjectNode instanceof ResizeNode) {
					resizeNode = (ResizeNode) pickedObjectNode;
					fDragMode = DragMode.RESIZE_ITEM;
				} else {
					fIsDragging = true;
					fDragMode = DragMode.DRAG_ITEMS;
					resizeNode = null;
				}
				currentLine = SwimlaneLine.NONE;
				fDragStart = e.getPoint();
			} else if (pickedEdge != null) {
				if (!fEdgeSelection.isSelected(pickedEdge)) {
					// clear selection
					fNodeSelection.clear();
					fEdgeSelection.clear();
					// add this component as the only selected item
					fEdgeSelection.add(pickedEdge);
					fDiagram.repaint();
					currentLine = SwimlaneLine.NONE;
				}
			} else {
				currentLine = swimlane.getLine(e.getX(), e.getY());
				if (currentLine != SwimlaneLine.NONE) {
					fNodeSelection.clear();
					fEdgeSelection.clear();
					fDragMode = DragMode.DRAG_ITEMS;
					fDragStart = e.getPoint();
				} else {
					// click in background, clear selection
					fNodeSelection.clear();
					fEdgeSelection.clear();

					// init selection rectangle
					selectionRectangle = fDiagram.createSelectionBox(e.getPoint());
					fDiagram.add(selectionRectangle);

					fDiagram.repaint();
				}

			}
			break;
		case InputEvent.SHIFT_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK:
			fDragMode = DragMode.DRAG_NONE;
			if (pickedObjectNode != null) {
				// add or remove this component to the selection
				if (fNodeSelection.isSelected(pickedObjectNode))
					fNodeSelection.remove(pickedObjectNode);
				else
					fNodeSelection.add(pickedObjectNode);

				fDiagram.repaint();
			} else if (pickedEdge != null) {
				// add or remove this component to the selection
				if (fEdgeSelection.isSelected(pickedEdge))
					fEdgeSelection.remove(pickedEdge);
				else
					fEdgeSelection.add(pickedEdge);

				fDiagram.repaint();
			} else {
				// additive selection rectangle
				selectionRectangle = fDiagram.createSelectionBox(e.getPoint());
				fDiagram.add(selectionRectangle);

				fDiagram.repaint();
			}
			break;
		case InputEvent.BUTTON2_DOWN_MASK:
			fDiagram.mayBeShowObjectInfo(e);
			break;
		default:
			// do nothing
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		fDiagram.removeMouseMotionListener(this);
		fDiagram.startLayoutThread();

		// handle mouse selection rectangle
		if (selectionRectangle != null) {
			Rectangle selectionBounds = selectionRectangle.getBounds();

			Set<PlaceableNode> nodesToSelect = fDiagram.findNodesInArea(selectionBounds);
			fNodeSelection.addAll(nodesToSelect);

			fDiagram.remove(selectionRectangle);
			selectionRectangle = null;
			fDiagram.repaint();
		}

		fDiagram.mayBeDisposeObjectInfo();

		if (fIsDragging) {
			e.getComponent().setCursor(Cursor.getDefaultCursor());
			fIsDragging = false;
			fDragMode = DragMode.DRAG_NONE;
		}

		fDiagram.maybeShowPopup(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (fDiagram.getStatusBar() != null)
			fDiagram.getStatusBar().clearMessage();
	}

	@Override
	public synchronized void mouseDragged(MouseEvent e) {

		// update selection rectangle
		if (selectionRectangle != null) {
			selectionRectangle.updateForCursorPosition(e.getPoint());
		}

		// ignore dragging events which we are not interested in
		if (fDragMode == DragMode.DRAG_NONE)
			return;

		// switch cursor at start of dragging if move
		if (!fIsDragging && fDragMode == DragMode.DRAG_ITEMS) {
			fDiagram.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		}

		fIsDragging = true;

		if (fDragMode == DragMode.DRAG_ITEMS) {
			Point p = e.getPoint();
			int dx = p.x - fDragStart.x;
			int dy = p.y - fDragStart.y;

			moveSelectedObjects(dx, dy);
			swimlane.moveSwimlane(currentLine, dx);
			fDragStart = p;
		}

		if (fDragMode == DragMode.RESIZE_ITEM) {
			resizeSelectedObjects(e.getPoint());
		}
		fDiagram.repaint();
	}

	/**
	 * 
	 * @param dx
	 * @param dy
	 */
	protected void resizeSelectedObjects(Point p) {
		// Just to be sure
		if (fNodeSelection.isEmpty())
			return;

		int dx = p.x - fDragStart.x;
		int dy = p.y - fDragStart.y;

		// If single selection we could have
		// started the dragging for resizing
		PlaceableNode theNode = fNodeSelection.iterator().next();

		if (resizeNode != null) {
			resizeNode.setDraggedPosition(dx, dy);
			fDiagram.invalidateNode(theNode);
			fDragStart = p;
			return;
		}
	}

	/**
	 * @param dx
	 * @param dy
	 */
	protected void moveSelectedObjects(int dx, int dy) {
		// move all selected components to new position,
		// or resize
		for (PlaceableNode sel : fNodeSelection) {
			if (sel instanceof RtlObjectNode) {
				if (((RtlObjectNode) sel).checkNewPositition(sel.getX() + dx, sel.getX() + sel.getWidth() + dx)) {
					sel.setDraggedPosition(dx, dy);
				}
			} else {
				sel.setDraggedPosition(dx, dy);
			}
			// WayPoints and other selectable nodes are not in the graph
			if (fDiagram.getGraph().contains(sel))
				fDiagram.invalidateNode(sel);
		}
		swimlane.moveSwimlane(currentLine, dx);	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (fDiagram.getStatusBar() != null)
			fDiagram.getStatusBar().showMessage("[x=" + e.getPoint().getX() + ", y=" + e.getPoint().getY() + "] ",
					BorderLayout.EAST);

		PlaceableNode targetNode = null;

		// We allow selected nodes to change the mouse cursor
		for (PlaceableNode n : fNodeSelection) {
			if (n.occupies(e.getX(), e.getY())) {
				targetNode = n;
			} else {
				targetNode = n.getRelatedNode(e.getX(), e.getY());
			}

			if (targetNode != null) {
				fDiagram.setCursor(targetNode.getCursor());
				return;
			}
		}

		fDiagram.setCursor(Cursor.getDefaultCursor());
	}
}
