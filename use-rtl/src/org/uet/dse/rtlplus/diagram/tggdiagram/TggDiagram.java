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

// $Id: TggDiagram.java 61 2008-04-11 11:52:15Z opti $

package org.uet.dse.rtlplus.diagram.tggdiagram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.xml.xpath.XPathConstants;

import org.tzi.use.config.Options;
import org.tzi.use.graph.DirectedGraphBase;
import org.tzi.use.gui.util.Selection;
import org.tzi.use.gui.xmlparser.LayoutTags;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.mm.MAttribute;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MLinkEnd;
import org.tzi.use.uml.sys.MLinkObject;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MObjectState;
import org.uet.dse.rtlplus.diagram.BinaryAssociationClassOrObject;
import org.uet.dse.rtlplus.diagram.BinaryAssociationOrLinkEdge;
import org.uet.dse.rtlplus.diagram.DiagramView;
import org.uet.dse.rtlplus.diagram.DiamondNode;
import org.uet.dse.rtlplus.diagram.EdgeBase;
import org.uet.dse.rtlplus.diagram.PlaceableNode;
import org.uet.dse.rtlplus.diagram.event.ActionLoadLayout;
import org.uet.dse.rtlplus.diagram.event.ActionSaveLayout;
import org.uet.dse.rtlplus.diagram.event.DiagramInputHandling;
import org.uet.dse.rtlplus.diagram.graph.PersistHelper;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//import org.tzi.use.gui.main.LogPanel;


/**
 * Duc-Hanh Dang
 */
@SuppressWarnings({"serial", "unchecked", "unused" })
public class TggDiagram extends DiagramView {
	
    private TggDiagramView fParent;
    private double fNextNodeX;
    private double fNextNodeY;

    private TggDiagramData tggDiagramData;

    public static class TggDiagramData {
		/**
		 *
		 */
		public Map<MObject, TggObjectNode> fObjectToNodeMap;
		/**
		 *
		 */
		public Map<MLink, BinaryAssociationOrLinkEdge> fBinaryLinkToEdgeMap;
		/**
		 *
		 */
		public Map<MLink, DiamondNode> fNaryLinkToDiamondNodeMap;
		/**
		 *
		 */
		public Map<MLink, List<EdgeBase>> fHalfLinkToEdgeMap;
		/**
		 *
		 */
		public Map<MLinkObject, EdgeBase> fLinkObjectToNodeEdge;

		/**
		 *
		 */
		public TggDiagramData() {
			fObjectToNodeMap = new HashMap<MObject, TggObjectNode>();
	        fBinaryLinkToEdgeMap = new HashMap<MLink, BinaryAssociationOrLinkEdge>();
	        fNaryLinkToDiamondNodeMap = new HashMap<MLink, DiamondNode>();
	        fHalfLinkToEdgeMap = new HashMap<MLink, List<EdgeBase>>();
	        fLinkObjectToNodeEdge = new HashMap<MLinkObject, EdgeBase>();
		}

		/**
		 * @param link
		 * @return
		 */
		public boolean containsLink(MLink link) {
			return fBinaryLinkToEdgeMap.containsKey(link)
					|| fNaryLinkToDiamondNodeMap.containsKey(link)
					|| fLinkObjectToNodeEdge.containsKey(link);
		}


		public TggObjectNode getObjectNode(MObject obj){
			return fObjectToNodeMap.get(obj);
		}
	}

    /**
     * Creates a new empty diagram.
     */
    TggDiagram(TggDiagramView parent, PrintWriter log) {
        fOpt = new TggDiagramOptions();
        fGraph = new DirectedGraphBase();
        tggDiagramData = new TggDiagramData();
        fParent = parent;
        fNodeSelection = new Selection();
        fEdgeSelection = new Selection();
        fLog = log;

        fActionSaveLayout = new ActionSaveLayout( "Triple rule diagram layout", "tlt", this);
        fActionLoadLayout = new ActionLoadLayout( "Triple rule diagram layout","tlt", this, fLog, fOpt, fGraph );

        fTggSwimlane = new TggSwimlane(parent, 300, 300, 500, 800, 200, 200, 400, 400);
        
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setPreferredSize( Options.fDiagramDimension );
        
        DiagramInputHandling inputHandling = 
                new DiagramInputHandling( fNodeSelection, fEdgeSelection, this, fTggSwimlane);
        
        addMouseListener(inputHandling);
        
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // need a new layouter to adapt to new window size
                fLayouter = null;
            }
        });
        
        startLayoutThread();
    }

   
    
    /**
     * Determinds if the auto layout of the diagram is on or off.
     * @return <code>true</code> if the auto layout is on, otherwise
     * <code>false</code>
     */
    public boolean isDoAutoLayout() {
        return fOpt.isDoAutoLayout();
    }
    

    /**
     * Get TggDiagramView fParent
     */
    public TggDiagramView getTggDiagramView(){
    	return fParent;
    }
    
    /**
     * Draws the diagram.
     */
    public void paintComponent(Graphics g) {
        synchronized (fLock) {
            Font f = Font.getFont("use.gui.view.objectdiagram", getFont());
            g.setFont(f);
            drawDiagram(g);            
        }
    }

    /**
     * Adds an object to the diagram.
     */
    public void addObject(MObject obj, int groupId) {
        /**
         * Find a random new position. getWidth and getheight return 0
         * if we are called on a new diagram.
         * if ( isDoAutoLayout() ) {
         * 		fNextNodeX = Math.random() * Math.max(100, getWidth());
         * 		fNextNodeY = Math.random() * Math.max(100, getHeight());
         * }
         * fGroupId
         * 		1: SL; 2: CL; 3: TL; 4:SR; 5: CR; 6: TR (Nondeleting Objects)
         * 		-1: SL; -2: CL; -3: TL; (Deleting Object)
         */
    	double objectDx = 150;//Math.max(getWidth(), getHeight());
    	double minX = fTggSwimlane.getMinX();
    	double minY = fTggSwimlane.getMinY();
    	switch(groupId){
	    	case -1: case 1: //SL
	    		fNextNodeX = randomLine(minX, fTggSwimlane.getSource_Corr().getX1(), objectDx);
	    		fNextNodeY = randomLine(fTggSwimlane.getMinY(), fTggSwimlane.getMiddleTggLine().getY1(), objectDx);
	    		break;
	    	case -2: case 2: //CL
	    		fNextNodeX = randomLine(fTggSwimlane.getSource_Corr().getX1(), fTggSwimlane.getTarget_Corr().getX1(), objectDx);
	    		fNextNodeY = randomLine(fTggSwimlane.getMinY(), fTggSwimlane.getMiddleTggLine().getY1(), objectDx);
	    		break;
	    	case -3: case 3: //TL
	    		fNextNodeX = randomLine(fTggSwimlane.getTarget_Corr().getX1(), fTggSwimlane.getTarget_Border().getX1(), objectDx);
	    		fNextNodeY = randomLine(fTggSwimlane.getMinY(), fTggSwimlane.getMiddleTggLine().getY1(), objectDx);
	    		break;
	    	case 4://SR
	    		fNextNodeX = randomLine(minX, fTggSwimlane.getSource_Corr().getX1(), objectDx);
	    		fNextNodeY = randomLine(fTggSwimlane.getMiddleTggLine().getY1(), fTggSwimlane.getBottomTggLine().getY1(), objectDx);    		
	    		break;
	    	case 5://CR
	    		fNextNodeX = randomLine(fTggSwimlane.getSource_Corr().getX1(), fTggSwimlane.getTarget_Corr().getX1(), objectDx);
	    		fNextNodeY = randomLine(fTggSwimlane.getMiddleTggLine().getY1(), fTggSwimlane.getBottomTggLine().getY1(), objectDx);
	    		break;
	    	case 6://TR
	    		fNextNodeX = randomLine(fTggSwimlane.getTarget_Corr().getX1(), fTggSwimlane.getTarget_Border().getX1(), objectDx);
	    		fNextNodeY = randomLine(fTggSwimlane.getMiddleTggLine().getY1(), fTggSwimlane.getBottomTggLine().getY1(), objectDx);
	    		break;
	    	default:    		
    	}
        TggObjectNode n = new TggObjectNode( obj, groupId, fParent, fOpt);
        n.setPosition( fNextNodeX, fNextNodeY );
        synchronized (fLock) {
            fGraph.add(n);
            tggDiagramData.fObjectToNodeMap.put(obj, n);
            fLayouter = null;
        }
    }
    
    private double randomLine(double a, double b, double length){
    	double res = a;
    	if (a>b){
    		res = b + Math.random()*(a-b-length);
    		if(res<b) res = b;
    	}
    	else{
    		res = a + Math.random()*(b-a-length);
    		if(res<a) res = a;
    	}    	
    	return res + length/2;
    }

    /**
     * Finds all nodes which are not selected.
     * @param selectedNodes Nodes which are selected at this point in the diagram.
     * @return A HashSet of the none selected objects in the diagram.
     */
    private Set getNoneSelectedNodes( Set selectedNodes ) {
        Set noneSelectedNodes = new HashSet();
        
        Iterator it = fGraph.iterator();
        while ( it.hasNext() ) {
            Object o = it.next();
            if ( o instanceof TggObjectNode ) {
                MObject obj = ((TggObjectNode) o).object();
                if ( !selectedNodes.contains( obj ) ) {
                    noneSelectedNodes.add( obj );
                }    
            }
        }
        return noneSelectedNodes;
    }   
    
    private int[] radixConversion(int number, int base, int maxDigits) {
        int[] res = new int[maxDigits];
        for (int i=0;i<maxDigits;++i) {
            res[i]=number % base;
            number=number / base;
        }
        return res;
    }

    private boolean isCompleteObjectCombination(int[] c, int base) {
        for(int i=0;i<base;++i) {
            boolean found=false;
            for(int j=0;j<c.length;++j) { 
                if (c[j]==i) found=true;
            }
            if (!found) return false;
        }
        return true;
    }
    
    public TggObjectNode getObjectNode(MObject mObject){
    	return (TggObjectNode)this.tggDiagramData.fObjectToNodeMap.get(mObject);
    }

	public TggSwimlane getTggSwimlane() {
		// TODO Auto-generated method stub
		return fTggSwimlane;
	}



	@Override
	public void showAll() {
		// TODO Auto-generated method stub
		
	}

	/**
     * Deletes an object from the diagram.
     */
    public void deleteObject(MObject obj) {
    	TggObjectNode n;
    	boolean isVisible;
    	
    	if (tggDiagramData.fObjectToNodeMap.containsKey(obj)) {
    		n = tggDiagramData.fObjectToNodeMap.get(obj);
    		isVisible = true;
    		fGraph.remove(n);
    	}
    }

    /**
     * Adds a link to the diagram.
     */
    public void addLink(MLink link, int groupId) {
    	if (link.linkEnds().size() == 2) {
    		addBinaryLink(link, groupId);
    	}
    }
    
    protected void addBinaryLink(MLink link, int groupId) {
    	MAssociation assoc = link.association();
	
		MLinkEnd linkEnd1 = link.linkEnd(assoc.associationEnds().get(0));
		MLinkEnd linkEnd2 = link.linkEnd(assoc.associationEnds().get(1));

        MObject obj1 = linkEnd1.object();
        MObject obj2 = linkEnd2.object();
        if (tggDiagramData.fObjectToNodeMap.get(obj1) == null) {
        	System.out.println(obj1.name() + " is null!");
        }
        if (tggDiagramData.fObjectToNodeMap.get(obj2) == null) {
        	System.out.println(obj2.name() + " is null");
        }
        // object link
        if (link instanceof MLinkObject) {
            BinaryAssociationClassOrObject e = 
                new BinaryAssociationClassOrObject(
                			 tggDiagramData.fObjectToNodeMap.get(obj1), 
                             tggDiagramData.fObjectToNodeMap.get(obj2),
                             linkEnd1.associationEnd(), 
                             linkEnd2.associationEnd(),
                             tggDiagramData.fObjectToNodeMap.get(link),
                             this, link, groupId );
            
            synchronized (fLock) {
                fGraph.addEdge(e);
                tggDiagramData.fLinkObjectToNodeEdge.put((MLinkObject)link, e);
                fLayouter = null;
            }
        } else {
            // binary link
			BinaryAssociationOrLinkEdge e = new BinaryAssociationOrLinkEdge(
					tggDiagramData.fObjectToNodeMap.get(obj1), tggDiagramData.fObjectToNodeMap.get(obj2),
					linkEnd1.associationEnd(), linkEnd2.associationEnd(),
					this, link, groupId);
			
            synchronized (fLock) {
                fGraph.addEdge(e);
                tggDiagramData.fBinaryLinkToEdgeMap.put(link, e);
                fLayouter = null;
            }
        }
    }

    /**
     * Removes a link from the diagram.
     */
    public void deleteLink(MLink link, boolean loadingLayout ) {
        if (link.linkEnds().size() == 2) {
            EdgeBase e = null;
            if (link instanceof MObject) { // MLinkObject ???
                e = tggDiagramData.fLinkObjectToNodeEdge.get(link);
                // TODO: this is just a tempory solution
                if (e == null) {
                    return;
                }
            } else {
                e = (BinaryAssociationOrLinkEdge) tggDiagramData.fBinaryLinkToEdgeMap.get(link);
            }
            
            if (e == null) {
                throw new RuntimeException( "no edge for link `" + link
                                            + "' in current state." );
            }

            synchronized (fLock) {
                fGraph.removeEdge(e);
                if (link instanceof MObject) {
                    tggDiagramData.fLinkObjectToNodeEdge.remove(link);
                } else {
                    tggDiagramData.fBinaryLinkToEdgeMap.remove(link);
                }
                fLayouter = null;
            }
        }
    }
	
	@Override
	public void storePlacementInfos(PersistHelper helper, Element root) {
		storePlacementInfos(helper, root, true);
	}
	
	protected void storePlacementInfos(PersistHelper helper, Element root, boolean visible) {
		
		fTggSwimlane.storePlacementInfo(helper, root);
		
		for (TggObjectNode n : tggDiagramData.fObjectToNodeMap.values()) {
			n.storePlacementInfo(helper, root, !visible);
		}
		
		
		for (EdgeBase e : tggDiagramData.fBinaryLinkToEdgeMap.values()) {
			e.storePlacementInfo(helper, root, !visible);
		}
		
		for (EdgeBase e : tggDiagramData.fLinkObjectToNodeEdge.values()) {
			e.storePlacementInfo(helper, root, !visible);
		}
	}

	
	protected boolean isHidden(PersistHelper helper, Element element, int version) {
		return helper.getElementBooleanValue(element, LayoutTags.HIDDEN);
	}



	@Override
	public boolean maybeShowPopup(MouseEvent e) {
		// TODO Auto-generated method stub
		boolean separatorNeeded = false;
		
        if (!e.isPopupTrigger())
            return false;

        // create the popup menu
        JPopupMenu popupMenu = unionOfPopUpMenu();

        // position for the popupMenu items 
        int pos = 0;

        if (separatorNeeded) {
            popupMenu.insert( new JSeparator(), pos++ );
            separatorNeeded = false;
        }

        popupMenu.show(e.getComponent(), e.getX(), e.getY());
        return true;
	}



	@Override
	public void restorePlacementInfos(PersistHelper helper,
			Element rootElement, int version) {
		// TODO Auto-generated method stub
		NodeList elements = (NodeList) helper.evaluateXPathSave(rootElement,
				"./" + LayoutTags.NODE + "[@type='Object']",
				XPathConstants.NODESET);
		for (int i = 0; i < elements.getLength(); ++i) {
			Element nodeElement = (Element)elements.item(i);			
			String name = helper.getElementStringValue(nodeElement, "name");
			MObject obj = fParent.getTggRule().getSystemStateRHS().system().state().objectByName(name);
			// Could be deleted
			if (obj != null) {
				TggObjectNode node = tggDiagramData.fObjectToNodeMap.get(obj);
				node.restorePlacementInfo(helper, nodeElement, version);
			}
		}

		// Restore edges
		elements = (NodeList) helper.evaluateXPathSave(rootElement, "./"
				+ LayoutTags.EDGE + "[@type='BinaryEdge']",
				XPathConstants.NODESET);

		for (int i = 0; i < elements.getLength(); ++i) {
			Element edgeElement = (Element)elements.item(i);

			String name = helper.getElementStringValue(edgeElement, "name");
			MAssociation assoc = fParent.getTggRule().getSystemStateRHS().system().model().getAssociation(name);
			String sourceObjectName = helper.getElementStringValue(edgeElement, "source");
			String targetObjectName = helper.getElementStringValue(edgeElement, "target");

			MObject sourceObject = fParent.getTggRule().getSystemStateRHS().system().state().objectByName(sourceObjectName);
			MObject targetObject = fParent.getTggRule().getSystemStateRHS().system().state().objectByName(targetObjectName);

			// Could be deleted
//			if (assoc != null && sourceObject != null && targetObject != null) {
//				MLink link;
//
//				if (assoc.hasQualifiedEnds()) {
//					String linkValue = helper.getElementStringValue(edgeElement, "linkValue");
//					link = getLinkByValue(assoc, Arrays.asList(sourceObject, targetObject), linkValue);
//				} else {
//					// No qualifier values are present. 
//					link = fParent
//							.getTggRule().getSystemState_RHS()
//							.system()
//							.state()
//							.linkBetweenObjects(assoc,
//									Arrays.asList(sourceObject, targetObject),
//									Collections.<List<Value>>emptyList());
//				}
//
//				if (link != null) {
//					BinaryAssociationOrLinkEdge edge = tggDiagramData.fBinaryLinkToEdgeMap.get(link);
//					edge.restorePlacementInfo(helper, edgeElement, version);
//				}
//			}
		}
		
		// restore Swimlane
		elements = (NodeList) helper.evaluateXPathSave(rootElement, "./"
				+ "swimlane",
				XPathConstants.NODESET);
		if(elements.getLength() > 0){
			Element edgeElement = (Element)elements.item(0);
			int x1, x2, x3, x4, y1, y2, y3, y4;
			x1 = helper.getElementIntegerValue(edgeElement, "X1");
			x2 = helper.getElementIntegerValue(edgeElement, "X2");
			x3 = helper.getElementIntegerValue(edgeElement, "X3");
			x4 = helper.getElementIntegerValue(edgeElement, "X4");
			y1 = helper.getElementIntegerValue(edgeElement, "Y1");
			y2 = helper.getElementIntegerValue(edgeElement, "Y2");
			y3 = helper.getElementIntegerValue(edgeElement, "Y3");
			y4 = helper.getElementIntegerValue(edgeElement, "Y4");
			
			fTggSwimlane.updateSwimlane(x1, x2, x3, x4, y1, y2, y3, y4);
		}
	}
	
	
	/**
     * Checks if the object info window should be displayed.
     * @param e MouseEvent
     */
    public void mayBeShowObjectInfo( MouseEvent e ) {
        if (fNodeSelection.size() == 1) {
            PlaceableNode node = (PlaceableNode) fNodeSelection.iterator().next();
            if (node instanceof TggObjectNode) {
                displayObjectInfo(((TggObjectNode) node).object(), e);
            }
        }
    }
    
    /**
     * Checks if the object info window should be disposed.
     */
    public void mayBeDisposeObjectInfo() {
        if (fObjectInfoWin != null) {
            fObjectInfoWin.setVisible(false);
            fObjectInfoWin.dispose();
            fObjectInfoWin = null;
        }
    }
    
    private JWindow fObjectInfoWin = null;

    private void displayObjectInfo(MObject obj, MouseEvent e) {
        if (fObjectInfoWin != null) {
            fObjectInfoWin.setVisible(false);
            fObjectInfoWin.dispose();
        }

        Box attrBox = Box.createVerticalBox();
        Box valueBox = Box.createVerticalBox();

        MObjectState objState = obj.state(fParent.getTggRule().getSystemStateRHS().system().state());
        Map<MAttribute, Value> attributeValueMap = objState.attributeValueMap();

        for (Map.Entry<MAttribute, Value> entry : attributeValueMap.entrySet()) {
            MAttribute attr = entry.getKey();
            Value v = entry.getValue();
            attrBox.add(new JLabel(attr.name() + " = "));
            valueBox.add(new JLabel(v.toString()));
        }

        fObjectInfoWin = new JWindow();
        JPanel contentPane = new JPanel();

        Border border = BorderFactory.createCompoundBorder(BorderFactory
                .createRaisedBevelBorder(), BorderFactory
                .createLoweredBevelBorder());
        contentPane.setBorder(border);
        Box b = Box.createHorizontalBox();
        b.add(attrBox);
        b.add(valueBox);
        contentPane.add(b);
        fObjectInfoWin.setContentPane(contentPane);
        Point p = e.getPoint();
        SwingUtilities.convertPointToScreen(p, (JComponent) e.getSource());

        fObjectInfoWin.setLocation(p);//e.getPoint());
        fObjectInfoWin.pack();
        fObjectInfoWin.setVisible(true);
    }
    
    
    protected MLink getLinkByValue(MAssociation assoc, List<MObject> objects, String linkValue) {
		Set<MLink> links = fParent.getTggRule().getSystemStateRHS().system().state().linkBetweenObjects(assoc, objects);
		if (links.size() == 1) {
			return links.iterator().next(); 
		} else {
			for (MLink aLink : links) {
				if (aLink.toString().equals(linkValue)) {
					return aLink;
				}
			}
		}
		return null;
	}
}
