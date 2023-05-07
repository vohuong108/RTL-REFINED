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

// $Id: ActionHideObjectDiagram.java 2535 2011-09-27 08:30:49Z lhamann $

package org.uet.dse.rtlplus.diagram.event;

import org.tzi.use.graph.DirectedGraph;
import org.tzi.use.gui.util.Selection;
import org.tzi.use.gui.views.diagrams.objectdiagram.NewObjectDiagram;
import org.tzi.use.uml.sys.MObject;
import org.uet.dse.rtlplus.diagram.EdgeBase;
import org.uet.dse.rtlplus.diagram.NodeBase;
import org.uet.dse.rtlplus.diagram.PlaceableNode;

import java.awt.event.ActionEvent;
import java.util.Set;

/**
 * Hides the selected objects.
 *
 * @version $ProjectVersion: 0.393 $
 * @author Fabian Gutsche
  */
@SuppressWarnings("serial")
public final class ActionHideObjectDiagram extends ActionHide<MObject> {
 
    public ActionHideObjectDiagram( String text, Set<MObject> nodesToHide,
                                    Selection<? extends PlaceableNode> nodeSelection,
                                    DirectedGraph<NodeBase, EdgeBase> graph, NewObjectDiagram diagram ) {
        super(text, diagram, nodeSelection);
        setNodes( nodesToHide );
        fGraph = graph;
    }

    /**
     * Easier access to the object diagram
     * @return
     */
    protected NewObjectDiagram getDiagram() {
    	return (NewObjectDiagram)diagram;
    }
    
    /**
     * Displays all hidden objects again. The objects have to be added
     * again, because they were deleted from the view before.
     */
    public void showAllHiddenElements() {
    	getDiagram().showAll();
        getDiagram().invalidateContent(true);
    }    

    /**
     * Hides all objects with there connecting links.
     */
    public void hideNodesAndEdges() {        
        getDiagram().hideElementsInDiagram( fNodesToHide );
        fNodeSelection.clear();
        getDiagram().invalidateContent(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        hideNodesAndEdges();
    }
    
    /*
     * Displays all hidden objects again. The objects have to be added
     * again, because they were deleted from the view before.
     */
    public void showHiddenElements(Set<MObject> hiddenElements) {
    	getDiagram().showObjects(hiddenElements);
    	getDiagram().invalidateContent(true);
    }
}