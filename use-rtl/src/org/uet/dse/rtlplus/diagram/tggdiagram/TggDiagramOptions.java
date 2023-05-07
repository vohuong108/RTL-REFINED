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

// $Id: ObjDiagramOptions.java 61 2008-04-11 11:52:15Z opti $

package org.uet.dse.rtlplus.diagram.tggdiagram;

import java.awt.*;

import org.uet.dse.rtlplus.diagram.DiagramOptions;

/**
 * Contains all optional settings for the object diagram.
 *
 * @version $ProjectVersion: 0.393 $
 * @author Fabian Gutsche
 */
public final class TggDiagramOptions extends DiagramOptions {
    
    public TggDiagramOptions() {
        // color settings
        NODE_COLOR = new Color(0xe0, 0xe0, 0xe0);
        NODE_SELECTED_COLOR = Color.orange;
        NODE_FRAME_COLOR = Color.black;
        NODE_LABEL_COLOR = Color.black;
        DIAMONDNODE_COLOR = Color.white;
        DIAMONDNODE_FRAME_COLOR = Color.black;
        EDGE_COLOR = Color.red;
        EDGE_LABEL_COLOR = Color.darkGray;
        EDGE_SELECTED_COLOR = Color.orange;
        
        //LINEPANEL_STROKE = new BasicStroke(1.0f);
        //LINEPANEL_COLOR = Color.black;
    }

    public boolean isShowMutliplicities() { return false; }
    public void setShowMutliplicities( boolean showMutliplicities ) {}
    
}
