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

package org.uet.dse.rtlplus.objectdiagram;

import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.main.ModelBrowserSorting.SortChangeEvent;
import org.tzi.use.gui.views.diagrams.objectdiagram.NewObjectDiagramView;
import org.tzi.use.gui.views.diagrams.objectdiagram.ObjDiagramOptions;
import org.tzi.use.uml.mm.MAssociationClass;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MLinkObject;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystem;
import org.tzi.use.uml.sys.events.AttributeAssignedEvent;
import org.tzi.use.uml.sys.events.LinkDeletedEvent;
import org.tzi.use.uml.sys.events.LinkInsertedEvent;
import org.tzi.use.uml.sys.events.ObjectCreatedEvent;
import org.tzi.use.uml.sys.events.ObjectDestroyedEvent;
import org.tzi.use.uml.sys.events.TransitionEvent;
import org.uet.dse.rtlplus.Main;
import org.uet.dse.rtlplus.mm.MRuleCollection.Side;

import com.google.common.eventbus.Subscribe;

/**
 * A graph showing an object diagram with objects and links in the current
 * system state.
 * 
 * @author Mark Richters
 */
@SuppressWarnings("serial")
public class RtlObjectDiagramView extends NewObjectDiagramView {

	private Map<String, Side> classMap;
	private Collection<MObject> matchedObjects = new ArrayList<>(0);

	protected RtlObjectDiagram fObjectDiagram;

	public RtlObjectDiagramView(MainWindow mainWindow, Map<String, Side> classMap, MSystem system) {
		super(mainWindow, system);
		this.classMap = classMap;
		fObjectDiagram.setClassMap(classMap);
		initState();
	}

	public void initDiagram(boolean loadDefaultLayout, ObjDiagramOptions fOpt) {
		if (fOpt == null)
			fObjectDiagram = new RtlObjectDiagram(this, fMainWindow.logWriter());
		else
			fObjectDiagram = new RtlObjectDiagram(this, fMainWindow.logWriter(), new ObjDiagramOptions(fOpt));		
		fObjectDiagram.setStatusBar(fMainWindow.statusBar());
		this.removeAll();
		add(new JScrollPane(fObjectDiagram));
	}
	
    /**
     * Does a full update of the view.
     */
    private void initState() {        
        for (MObject obj : fSystem.state().allObjects()) {
    		if(!obj.cls().name().equals("RuleCollection")) 
    			fObjectDiagram.addObject(obj);
        }

        for (MLink link : fSystem.state().allLinks()) {
            fObjectDiagram.addLink(link);
        }
        
        fObjectDiagram.initialize();
        viewcount++;
    }

	/**
	 * The managed diagram
	 * 
	 * @return
	 */
	public RtlObjectDiagram getDiagram() {
		return this.fObjectDiagram;
	}
	
	@Subscribe
	public void onMatchSelected(MatchSelectedEvent e) {
		for (MObject obj : matchedObjects) {
			RtlObjectNode node = fObjectDiagram.getObjectNode(obj);
			if (node != null)
				node.setMatched(false);
		}
		for (MObject obj : e.getMatchedObjects()) {
			RtlObjectNode node = fObjectDiagram.getObjectNode(obj);
			if (node != null)
				node.setMatched(true);
		}
		matchedObjects = e.getMatchedObjects();
		fObjectDiagram.invalidateContent(true);
	}
	
	@Subscribe
	public void onSystemReset(SystemResetEvent e) {
		matchedObjects.clear();
		fObjectDiagram.clearAllData();
		fObjectDiagram.invalidateContent(true);
		fObjectDiagram.repaint();
	}
	
    @Subscribe
    public void onTransition(TransitionEvent e) {
    	fObjectDiagram.updateObject(e.getSource());
    	fObjectDiagram.invalidateContent(true);
    }
    
    @Subscribe
    public void onObjectCeated(ObjectCreatedEvent e) {
    	if (e.getCreatedObject() instanceof MLink) {
    		return;
    	}
    	
    	fObjectDiagram.addObject(e.getCreatedObject());
    	fObjectDiagram.invalidateContent(true);
    }
    
    @Subscribe
    public void onObjectDestroyed(ObjectDestroyedEvent e) {
    	if (e.getDestroyedObject() instanceof MLink) {
    		return;
    	}
    	
    	fObjectDiagram.deleteObject(e.getDestroyedObject());
    	fObjectDiagram.invalidateContent(true);
    }
    
    @Subscribe
    public void onAttributeAssigned(AttributeAssignedEvent e) {
    	fObjectDiagram.updateObject(e.getObject());
    	fObjectDiagram.invalidateContent(true);
    }
    
    @Subscribe
    public void onLinkCeated(LinkInsertedEvent e) {
    	if (e.getAssociation() instanceof MAssociationClass) {
    		fObjectDiagram.addObject((MLinkObject)e.getLink());	
    	}
    	fObjectDiagram.addLink(e.getLink());
    	fObjectDiagram.invalidateContent(true);
    }
    
    @Subscribe
    public void onLinkDeleted(LinkDeletedEvent e) {
    	if (e.getAssociation() instanceof MAssociationClass) {
    		fObjectDiagram.deleteObject((MLinkObject)e.getLink());
    	}
    	
    	fObjectDiagram.deleteLink(e.getLink());
    	fObjectDiagram.invalidateContent(true);
    }
    
    /**
     * After the occurence of an event the view is updated.
     */
    @Override
	public void stateChanged( SortChangeEvent e ) {
        fObjectDiagram.invalidateContent(true);
    }

    @Override
	public void printView(PageFormat pf) {
        fObjectDiagram.printDiagram(pf, "Object diagram" );
    }

    @Override
	public void export( Graphics2D g ) {
    	JComponent toExport = fObjectDiagram;
        
    	boolean oldDb = toExport.isDoubleBuffered();
    	toExport.setDoubleBuffered(false);
    	toExport.paint(g);
    	toExport.setDoubleBuffered(oldDb);
    }

	@Override
	public float getContentHeight() {
		return fObjectDiagram.getPreferredSize().height;
	}

	@Override
	public float getContentWidth() {
		return fObjectDiagram.getPreferredSize().width;
	}

	public Map<String, Side> getClassMap() {
		return this.classMap;
	}
}
