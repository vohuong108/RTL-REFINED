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

// $Id: TggDiagramView.java 61 2008-04-11 11:52:15Z opti $
//hanhdd

package org.uet.dse.rtlplus.diagram.tggdiagram;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.io.StringWriter;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.main.ModelBrowser;
import org.tzi.use.gui.main.ModelBrowserSorting.SortChangeEvent;
import org.tzi.use.gui.main.ModelBrowserSorting.SortChangeListener;
import org.tzi.use.gui.views.PrintableView;
import org.tzi.use.gui.views.View;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.uet.dse.rtlplus.mm.MRule;
import org.uet.dse.rtlplus.mm.MTggRule;

/** 
 * @author Duc-Hanh Dang 
 */
@SuppressWarnings({"unused", "serial"})
public class TggDiagramView extends JPanel 
  implements View, PrintableView, SortChangeListener {
    /**
	 * @uml.property  name="fTggRule"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private MTggRule fTggRule;
    /**
	 * @uml.property  name="fMainWindow"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private MainWindow fMainWindow;
    /**
	 * @uml.property  name="fTggDiagram"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="fParent:org.tzi.use.gui.views.diagrams.tggDiagram.TggDiagram"
	 */
    private TggDiagram fTggDiagram;
    /**
	 * @uml.property  name="fTggSL_HtmlPane"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    JEditorPane fTggSL_HtmlPane;
    /**
	 * @uml.property  name="fTggSR_HtmlPane"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    JEditorPane fTggSR_HtmlPane;
    /**
	 * @uml.property  name="fTggCL_HtmlPane"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    JEditorPane fTggCL_HtmlPane;
    /**
	 * @uml.property  name="fTggCR_HtmlPane"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    JEditorPane fTggCR_HtmlPane;
    /**
	 * @uml.property  name="fTggTL_HtmlPane"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    JEditorPane fTggTL_HtmlPane;
    /**
	 * @uml.property  name="fTggTR_HtmlPane"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    JEditorPane fTggTR_HtmlPane;

    public TggDiagramView(MainWindow mainWindow, MTggRule tggRule) {
        fMainWindow = mainWindow;
        fTggRule = tggRule;
        setLayout(new BorderLayout());

        fTggDiagram = new TggDiagram(this, mainWindow.logWriter());
        
        //fTggSL_HtmlPane.setText(t)
        
        fTggSL_HtmlPane = new JEditorPane();
        fTggSL_HtmlPane.setEditable(false);
        fTggSL_HtmlPane.setContentType("text/html");        
        JScrollPane htmlViewSL = new JScrollPane(fTggSL_HtmlPane);
        
        fTggCL_HtmlPane = new JEditorPane();
        fTggCL_HtmlPane.setEditable(false);
        fTggCL_HtmlPane.setContentType("text/html");
        JScrollPane htmlViewCL = new JScrollPane(fTggCL_HtmlPane);
        
        fTggTL_HtmlPane = new JEditorPane();
        fTggTL_HtmlPane.setEditable(false);
        fTggTL_HtmlPane.setContentType("text/html");
        JScrollPane htmlViewTL = new JScrollPane(fTggTL_HtmlPane);
        
        JSplitPane splitPane_CTL = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		htmlViewCL, htmlViewTL);
        
        JSplitPane splitPane_SCTL = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		htmlViewSL, splitPane_CTL);
        
        splitPane_SCTL.setDividerLocation(250);
        splitPane_CTL.setDividerLocation(80);
        
//      Add the scroll panes to a split pane.
        JSplitPane splitPaneLHS = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
        											splitPane_SCTL, fTggDiagram);
        
        //Dimension minimumSize = new Dimension(600, 0);
        //splitPane_SCTL.setMinimumSize(minimumSize);
        //splitPane_SCTL.setPreferredSize(new Dimension(800, 80));
        
        splitPaneLHS.setDividerLocation(40);
        //splitPaneLHS.setPreferredSize(new Dimension(500, 200));        
        
        //RHS of the Tgg rule
        
        fTggSR_HtmlPane = new JEditorPane();
        fTggSR_HtmlPane.setEditable(false);
        fTggSR_HtmlPane.setContentType("text/html");        
        JScrollPane htmlViewSR = new JScrollPane(fTggSR_HtmlPane);
        
        fTggCR_HtmlPane = new JEditorPane();
        fTggCR_HtmlPane.setEditable(false);
        fTggCR_HtmlPane.setContentType("text/html");
        JScrollPane htmlViewCR = new JScrollPane(fTggCR_HtmlPane);
        
        fTggTR_HtmlPane = new JEditorPane();
        fTggTR_HtmlPane.setEditable(false);
        fTggTR_HtmlPane.setContentType("text/html");
        JScrollPane htmlViewTR = new JScrollPane(fTggTR_HtmlPane);
        
        JSplitPane splitPane_CTR = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		htmlViewCR, htmlViewTR);
        
        JSplitPane splitPane_SCTR = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		htmlViewSR, splitPane_CTR);
        
        splitPane_SCTR.setDividerLocation(250);
        splitPane_CTR.setDividerLocation(80);        
       
//      Add the scroll panes to a split pane.
        JSplitPane splitPaneTGG = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
        		splitPaneLHS, splitPane_SCTR);
        
        splitPaneTGG.setDividerLocation(220);
        splitPaneTGG.setPreferredSize(new Dimension(400, 260));
        
        add(splitPaneTGG, BorderLayout.CENTER);

        initState();
    }
    
    /**
     * Returns the model browser.
     */
    public ModelBrowser getModelBrowser() {
        return fMainWindow.getModelBrowser();
    }
    public MTggRule getTggRule(){
    	return fTggRule;
    }
    public TggDiagram getTggDiagram(){
    	return fTggDiagram;
    }
    /**
     * Determinds if this is the selected view.
     * @return <code>true</code> if it is the selected view, otherwise
     * <code>false</false>
     */
    public boolean isSelectedView() {
        if ( fMainWindow.getSelectedView() != null ) {
            return fMainWindow.getSelectedView().equals( this );
        } 
        return false;
    }
    
    
    /**
     * Does a full update of the view.
     */
    private void initState() {    	
    	MRule sourceRule = fTggRule.getSrcRule();
    	MRule targetRule = fTggRule.getTrgRule();
    	MRule corrRule = fTggRule.getCorrRule();
    	//SL -- groupId = -1; 
    	//this.addObjects(sourceRule.getDeletingObjects(),-1);
    	//CL -- groupId = -2;
    	//this.addObjects(corrRule.getDeletingObjects(),-2);
    	//TL -- groupId = -3;
    	//this.addObjects(targetRule.getDeletingObjects(),-3);
    	// SL -- groupId = 1; 
    	this.addObjects(sourceRule.getNonDeletingObjects(), 1); 
    	//CL -- groupId = 2;
    	this.addObjects(corrRule.getNonDeletingObjects(), 2);
    	//TL -- groupId = 3;
    	this.addObjects(targetRule.getNonDeletingObjects(), 3);
    	//SR -- groupId = 4;
    	this.addObjects(sourceRule.getNewObjects(), 4);
    	//CR -- groupId = 5; 
    	this.addObjects(corrRule.getNewObjects(), 5);
    	// TR -- groupId = 6; 
    	this.addObjects(targetRule.getNewObjects(), 6);
    	// Add links
    	//Deleting links -- groupId = -1; 
    	//this.addLinks(fTggRule.getDeletingLinks(), -1);
    	//NonDeleting links -- groupId = 0;
    	this.addLinks(fTggRule.getNonDeletingLinks(), 0);
    	//New Links -- groupId = 1;
    	this.addLinks(fTggRule.getNewLinks(),1);
    	
    	displayOCLConditions();
    	
        fTggDiagram.repaint();
    }    
    
    /*
     * Helper for initState()
     */
    
    public void addObjects(List<MObject> objects, int groupId){    	
	    for (MObject obj : objects) {
	        fTggDiagram.addObject(obj,groupId);
	    }  
    }
    
    /*
     * Helper for initState()
     */
    
    public void addLinks(List<MLink> links, int groupId){    	
	    for (MLink link : links) {
	        fTggDiagram.addLink(link,groupId);
	    }  
    }
    
    /*
     * Assign OCL conditions into HtmlPanes
     */
    
    void displayOCLConditions(){
    	List<String> conditions = fTggRule.getSrcRule().getPreconditions();
    	setTextHtmlPane(fTggSL_HtmlPane, conditions);
    	conditions = this.fTggRule.getSrcRule().getPostconditions();
    	setTextHtmlPane(fTggSR_HtmlPane, conditions);
    	
    	conditions = fTggRule.getCorrRule().getPreconditions();
    	setTextHtmlPane(fTggCL_HtmlPane, conditions);
    	conditions = fTggRule.getCorrRule().getPostconditions();
    	setTextHtmlPane(fTggCR_HtmlPane, conditions);
    	
    	conditions = fTggRule.getTrgRule().getPreconditions();
    	setTextHtmlPane(fTggTL_HtmlPane, conditions);
    	conditions = this.fTggRule.getTrgRule().getPostconditions();
    	setTextHtmlPane(fTggTR_HtmlPane, conditions);    	
    }
    /*
     * Helper for displayOCLConditions()
     */
    private void setTextHtmlPane(JEditorPane tggSL_HtmlPane, List<String> conditions) {
    	StringWriter sw = new StringWriter();
        sw.write("<html><head>");
        sw.write("<META content=\"text/html; charset=utf-8\">");
        sw.write("<style> <!-- body { font-family: sansserif; } --> </style>");
        sw.write("</head><body><font size=\"-1\">");  
        
        for (String st : conditions) {      	
        	st = st.replace(">", "&gt;");
        	st = st.replace("<", "&lt;");
        	sw.write(st);
        	sw.write("<br>");        	
        }
        
        sw.write("</body></html>");
        String spec = sw.toString();            
        tggSL_HtmlPane.setText(spec);
	}

	/**
     * Returns the set of all associations that exist between the
     * specified classes. 
     *
     * @return Set(MAssociation) 
     */
//    Set<MAssociation> getAllAssociationsBetweenClasses(Set<MClass> classes) {
//        return fTggRule.getModel().getAllAssociationsBetweenClasses(classes);
//    }

    /**
     * Returns true if there is a link of the specified association
     * connecting the given set of objects.
     */
    boolean hasLinkBetweenObjects(MAssociation assoc, MObject[] objects) {
       return fTggRule.getSystemStateRHS().hasLinkBetweenObjects(assoc, objects);
    }

    /**
     * Writes all objects participating in an association into a 
     * String seperated by a comma. 
     * @param assocName Name of the association.
     * @param objects Selected objects. 
     * @return The list of object names separated by a comma.
     */
	private String objectNameList(MObject[] objects ) {
        String txt ="";
        for (int i=0;i<objects.length;++i) {
            if (i>0) txt = txt + ",";
            MObject o = objects[i];
            txt = txt + o.name();
        }
        return txt;
    }

    public void printView(PageFormat pf) {
        fTggDiagram.printDiagram(pf, "Triple rule diagram" );
    }

	public void detachModel() {		
	}


	public void stateChanged(SortChangeEvent e) {
		
	}

	@Override
	public void export(Graphics2D g) {		
	}

	@Override
	public float getContentHeight() {
		return 0;
	}

	@Override
	public float getContentWidth() {
		return 0;
	}

}
