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

// $Id: ActionHideClassDiagram.java 2410 2011-07-27 12:23:55Z opti $

package org.uet.dse.rtlplus.diagram.event;

import org.tzi.use.graph.DirectedGraph;
import org.tzi.use.gui.util.Selection;
import org.tzi.use.gui.views.diagrams.Selectable;
import org.tzi.use.gui.views.diagrams.classdiagram.ClassDiagram;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.mm.MClassifier;
import org.uet.dse.rtlplus.diagram.EdgeBase;
import org.uet.dse.rtlplus.diagram.NodeBase;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * Hides selected nodes and edges from a given diagram.
 *
 * @version $ProjectVersion: 0.393 $
 * @author Fabian Gutsche
  */
@SuppressWarnings("serial")
public final class ActionHideClassDiagram extends ActionHide<MClassifier> {
    public ActionHideClassDiagram( String text, Set<? extends MClassifier> nodesToHide,
                                   Selection<? extends Selectable> nodeSelection, DirectedGraph<NodeBase, EdgeBase> graph,
                                   ClassDiagram diagram ) {
        super( text, diagram, nodeSelection );
        setNodes( nodesToHide );
        fNodeSelection = nodeSelection;
        fGraph = graph;
    }

    protected ClassDiagram getDiagram() {
    	return (ClassDiagram)diagram;
    }
    
    public void showAllHiddenElements() {
        // add all hidden nodes
    	getDiagram().showAll();
        getDiagram().invalidateContent(true);
    }

    /**
     * Hides all nodes with their connecting edges.
     */
    public void hideNodesAndEdges() {
        getDiagram().hideElementsInDiagram( fNodesToHide, new HashSet<MAssociation>() );
        fNodeSelection.clear();
        getDiagram().invalidateContent(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        hideNodesAndEdges();
    }

    public void showHiddenElements(Set<MClassifier> hiddenElements) {
    	getDiagram().showElementsInDiagram(hiddenElements);
    	getDiagram().invalidateContent(true);
    }
}