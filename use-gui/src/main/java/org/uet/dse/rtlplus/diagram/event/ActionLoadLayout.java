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

// $Id: ActionLoadLayout.java 2547 2011-09-27 15:49:16Z lhamann $

package org.uet.dse.rtlplus.diagram.event;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.tzi.use.config.Options;
import org.tzi.use.graph.DirectedGraph;
import org.tzi.use.gui.util.ExtFileFilter;
import org.uet.dse.rtlplus.diagram.DiagramOptions;
import org.uet.dse.rtlplus.diagram.DiagramView;
import org.uet.dse.rtlplus.diagram.graph.PersistHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Loads the current layout from a file.
 * 
 * @version $ProjectVersion: 0.393 $
 * @author Fabian Gutsche
 */
@SuppressWarnings("serial")
public class ActionLoadLayout extends AbstractAction {
	
    private String fTitle = "";
    private String fAppendix = "";
    private DiagramView fDiagram;
    
    private File lastFile = null;
    
    @SuppressWarnings("unchecked")
	public ActionLoadLayout( String title, String appendix, DiagramView diagram,
                             PrintWriter log, DiagramOptions opt, 
                             DirectedGraph graph ) {
        super("Load layout...");
        fTitle = title;
        fAppendix = appendix;
        
        fDiagram = diagram;
    }

    @SuppressWarnings("unchecked")
	public ActionLoadLayout( String title, String appendix,
                             DiagramView diagram, PrintWriter log,
                             DirectedGraph graph ) {
        super("Load layout...");
        fTitle = title;
        fAppendix = appendix;
        fDiagram = diagram;
    }
    
    public void actionPerformed(ActionEvent e) {
        // reuse chooser if possible
    	JFileChooser fileChooser;
    	
        fileChooser = new JFileChooser(Options.getLastDirectory().toString());
        
        ExtFileFilter filter = new ExtFileFilter( fAppendix, fTitle );
        fileChooser.setFileFilter(filter);
        //fileChooser.addChoosableFileFilter(filter);
        fileChooser.setDialogTitle("Load layout");
        
		if (lastFile != null && lastFile.exists()
				&& lastFile.getParent().equals(Options.getLastDirectory())) {
			fileChooser.setSelectedFile(lastFile);
		}
        
        int returnVal = fileChooser.showOpenDialog( fDiagram );
        if (returnVal != JFileChooser.APPROVE_OPTION)
            return;

        Options.setLastDirectory(fileChooser.getCurrentDirectory().toPath());
        lastFile = fileChooser.getSelectedFile();

        fDiagram.showAll();
                
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc;
        
        try {
        	docBuilder = fact.newDocumentBuilder();
			doc = docBuilder.parse(lastFile);
		} catch (ParserConfigurationException e1) {
			JOptionPane.showMessageDialog(fileChooser, e1.getMessage());
			return;
		} catch (SAXException e1) {
			JOptionPane.showMessageDialog(fileChooser, e1.getMessage());
			return;
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(fileChooser, e1.getMessage());
			return;
		}
		
		Element rootElement = (Element)doc.getDocumentElement();
		int version = 1;
		
		if (rootElement.hasAttribute("version"))
			version = Integer.valueOf(rootElement.getAttribute("version"));
		
		PersistHelper helper = new PersistHelper(null);
		Element layoutElement = (Element)rootElement.getElementsByTagName("diagramOptions").item(0);
		fDiagram.getOptions().loadOptions(helper, layoutElement, version);
		
		fDiagram.restorePlacementInfos(helper, rootElement, version);
        fDiagram.invalidateContent();
    }
 
}