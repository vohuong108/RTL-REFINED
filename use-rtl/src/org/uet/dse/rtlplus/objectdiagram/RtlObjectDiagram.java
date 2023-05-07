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

// $Id: NewObjectDiagram.java 5880 2016-03-17 17:10:09Z fhilken $

package org.uet.dse.rtlplus.objectdiagram;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tzi.use.gui.util.PersistHelper;
import org.tzi.use.gui.views.diagrams.elements.PlaceableNode;
import org.tzi.use.gui.views.diagrams.elements.edges.BinaryAssociationClassOrObject;
import org.tzi.use.gui.views.diagrams.elements.edges.BinaryAssociationOrLinkEdge;
import org.tzi.use.gui.views.diagrams.elements.edges.EdgeBase;
import org.tzi.use.gui.views.diagrams.objectdiagram.NewObjectDiagram;
import org.tzi.use.gui.views.diagrams.objectdiagram.ObjDiagramOptions;
import org.tzi.use.gui.views.diagrams.objectdiagram.ObjectNode;
import org.tzi.use.uml.mm.MAssociation;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MLinkObject;
import org.tzi.use.uml.sys.MObject;
import org.uet.dse.rtlplus.mm.MRuleCollection;
import org.uet.dse.rtlplus.mm.MRuleCollection.Side;
import org.w3c.dom.Element;

import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

/**
 * A panel drawing UML object diagrams, for use in RTL
 * 
 * @author Duc-Hanh Dang
 */
@SuppressWarnings("serial")
public class RtlObjectDiagram extends NewObjectDiagram {
	
	private RtlObjectDiagramView fParent;
	
	protected RtlSwimlane swimlane;
		
	private Map<String, MRuleCollection.Side> classMap;
	public List<RtlObjectNode> srcObjectNodes;
	public List<RtlObjectNode> trgObjectNodes;
	public List<RtlObjectNode> corObjectNodes;
	
	public List<RtlObjectNode> getSrcObjectNodes() {
		return srcObjectNodes;
	}

	public List<RtlObjectNode> getTrgObjectNodes() {
		return trgObjectNodes;
	}

	public List<RtlObjectNode> getCorObjectNodes() {
		return corObjectNodes;
	}

	private RtlDiagramInputHandling inputHandling;
	
	/**
	 * Creates a new empty diagram.
	 */
	RtlObjectDiagram(RtlObjectDiagramView newObjectDiagramView, PrintWriter log) {
		this(newObjectDiagramView, log, new ObjDiagramOptions());
	}

	RtlObjectDiagram(RtlObjectDiagramView rtlObjectDiagramView, PrintWriter log, ObjDiagramOptions options) {
		super(rtlObjectDiagramView, log, options);
		this.getRandomNextPosition();
		fParent = rtlObjectDiagramView;
		this.classMap = rtlObjectDiagramView.getClassMap();
		srcObjectNodes = new ArrayList<>();
		trgObjectNodes = new ArrayList<>();
		corObjectNodes = new ArrayList<>();

		swimlane = new RtlSwimlane(rtlObjectDiagramView, 0, 300, 600, 900);
		inputHandling = new RtlDiagramInputHandling(fNodeSelection, fEdgeSelection, this, swimlane);

		fParent.removeKeyListener(super.inputHandling);
		fParent.addKeyListener(inputHandling);

		addMouseListener(inputHandling);
		
		startLayoutThread();
	}

	
	/**
	 * Adds an object to the diagram.
	 */
	public void addObject(MObject obj) {
		Side side = classMap.get(obj.cls().toString());
		swimlane.getRandomNextPosition(nextNodePosition, getWidth(), getHeight(), side);
		RtlObjectNode n = new RtlObjectNode(obj, classMap.get(obj.cls().name()), fParent, getOptions());
		n.setPosition(nextNodePosition);
		n.setMinWidth(minClassNodeWidth);
		n.setMinHeight(minClassNodeHeight);

		getRandomNextPosition();
		
		fGraph.add(n);
		visibleData.fObjectToNodeMap.put(obj, n);
		switch(side) {
		case SOURCE:
			srcObjectNodes.add(n);
			break;
		case TARGET:
			trgObjectNodes.add(n);
			break;
		case CORRELATION:
			corObjectNodes.add(n);
			break;
		default:
			break;
		}
		fLayouter = null;
	}

	/**
	 * Deletes an object from the diagram.
	 */
	public void deleteObject(MObject obj) {
		ObjectNode n;
		boolean isVisible;

		if (visibleData.fObjectToNodeMap.containsKey(obj)) {
			n = visibleData.fObjectToNodeMap.get(obj);
			isVisible = true;
		} else {
			n = hiddenData.fObjectToNodeMap.get(obj);
			isVisible = false;
		}

		if (n != null) {
			lastKnownNodePositions.put(obj, n.getPosition());
			if (isVisible) {
				fGraph.remove(n);
				visibleData.fObjectToNodeMap.remove(obj);
				fLayouter = null;
			} else {
				hiddenData.fObjectToNodeMap.remove(obj);
			}
			n.dispose();
		}
		
		Side side = classMap.get(obj.cls().toString());
		switch(side) {
		case SOURCE:
			srcObjectNodes.remove(n);
			break;
		case TARGET:
			trgObjectNodes.remove(n);
			break;
		case CORRELATION:
			corObjectNodes.remove(n);
			break;
		default:
			break;
		}
	}
	
	public void clearAllData() {
		fGraph.clear();
		visibleData.clear();
		hiddenData.clear();
	}



	public void hideLink(MLink link) {
		if (hiddenData.containsLink(link))
			return;

		if (link.linkEnds().size() == 2) {
			hideBinaryLink(link);
		} else {
			hideNAryLink(link);
		}

		if (link instanceof MLinkObject && visibleData.fObjectToNodeMap.containsKey((MLinkObject) link)) {
			hideObject((MObject) link);
		}
	}


	/**
	 * 
	 * Accepts a drag of a class from the ModelBrowser. A new object of this class
	 * will be created.
	 * 
	 * @param dtde
	 */
	public void dropObjectFromModelBrowser(DropTargetDropEvent dtde) {

		try {
			dtde.acceptDrop(DnDConstants.ACTION_MOVE);
			Transferable transferable = dtde.getTransferable();

			// we accept only Strings
			if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				String s = (String) transferable.getTransferData(DataFlavor.stringFlavor);

				if (s.startsWith("CLASS-")) {
					String clsName = s.substring(6);
					swimlane.getRandomNextPosition(nextNodePosition, getWidth(), getHeight(), classMap.get(clsName));
					fParent.createObject(clsName);
				}
			}
			dtde.dropComplete(true);
		} catch (IOException exception) {
			exception.printStackTrace();
			System.err.println("Exception" + exception.getMessage());
			dtde.dropComplete(false);
		} catch (UnsupportedFlavorException ufException) {
			ufException.printStackTrace();
			System.err.println("Exception" + ufException.getMessage());
			dtde.dropComplete(false);
		}
	}


	protected void storePlacementInfos(PersistHelper helper, Element root, boolean visible) {
		ObjectDiagramData data = (visible ? visibleData : hiddenData);

		for (ObjectNode n : data.fObjectToNodeMap.values()) {
			n.storePlacementInfo(helper, root, !visible);
		}

		for (PlaceableNode n : data.fNaryLinkToDiamondNodeMap.values()) {
			n.storePlacementInfo(helper, root, !visible);
		}

		for (EdgeBase e : data.fBinaryLinkToEdgeMap.values()) {
			e.storePlacementInfo(helper, root, !visible);
		}

		for (EdgeBase e : data.fLinkObjectToNodeEdge.values()) {
			e.storePlacementInfo(helper, root, !visible);
		}
		swimlane.storePlacementInfo(helper, root);
	}

	@Override
	public void restorePlacementInfos(PersistHelper helper, int version) {
		if (version < 12)
			return;

		Set<MObject> hiddenObjects = new HashSet<MObject>();
		AutoPilot ap = new AutoPilot(helper.getNav());

		// First restore edges to get possible new nodes, then nodes
		helper.getNav().push();
		
		try {
			// Restore edges
			ap.selectXPath("./edge[@type='BinaryEdge']");

			try {
				while (ap.evalXPath() != -1) {
					String name = helper.getElementStringValue("name");
					MAssociation assoc = fParent.system().model().getAssociation(name);
					String sourceObjectName = helper.getElementStringValue("source");
					String targetObjectName = helper.getElementStringValue("target");

					MObject sourceObject = fParent.system().state().objectByName(sourceObjectName);
					MObject targetObject = fParent.system().state().objectByName(targetObjectName);

					// Could be deleted
					if (assoc != null && sourceObject != null && targetObject != null) {
						MLink link;

						if (assoc.hasQualifiedEnds()) {
							String linkValue = helper.getElementStringValue("linkValue");
							link = getLinkByValue(assoc, Arrays.asList(sourceObject, targetObject), linkValue);
						} else {
							// No qualifier values are present.
							link = fParent.system().state().linkBetweenObjects(assoc,
									Arrays.asList(sourceObject, targetObject), Collections.<List<Value>>emptyList());
						}

						if (link != null) {
							BinaryAssociationOrLinkEdge edge = visibleData.fBinaryLinkToEdgeMap.get(link);
							edge.restorePlacementInfo(helper, version);
						}
					}
				}
			} catch (XPathEvalException e) {
				fLog.append(e.getMessage());
			} catch (NavException e) {
				fLog.append(e.getMessage());
			}
		} catch (XPathParseException e) {
			fLog.append(e.getMessage());
		}
		ap.resetXPath();
		helper.getNav().pop();

		helper.getNav().push();
		try {
			// Restore edges
			ap.selectXPath("./edge[@type='NodeEdge']");

			try {
				while (ap.evalXPath() != -1) {
					String name = helper.getElementStringValue("name");
					MAssociation assoc = fParent.system().model().getAssociation(name);
					String sourceObjectName = helper.getElementStringValue("source");
					String targetObjectName = helper.getElementStringValue("target");

					MObject sourceObject = fParent.system().state().objectByName(sourceObjectName);
					MObject targetObject = fParent.system().state().objectByName(targetObjectName);

					// Could be deleted
					if (assoc != null && sourceObject != null && targetObject != null) {
						MLink link;

						if (assoc.hasQualifiedEnds()) {
							String linkValue = helper.getElementStringValue("linkValue");
							link = getLinkByValue(assoc, Arrays.asList(sourceObject, targetObject), linkValue);
						} else {
							// No qualifier values are present.
							link = fParent.system().state().linkBetweenObjects(assoc,
									Arrays.asList(sourceObject, targetObject), Collections.<List<Value>>emptyList());
						}

						if (link != null) {
							BinaryAssociationClassOrObject edge = (BinaryAssociationClassOrObject) visibleData.fLinkObjectToNodeEdge
									.get(link);
							edge.restorePlacementInfo(helper, version);
						}
					}
				}
			} catch (XPathEvalException e) {
				fLog.append(e.getMessage());
			} catch (NavException e) {
				fLog.append(e.getMessage());
			}
		} catch (XPathParseException e) {
			fLog.append(e.getMessage());
		}
		helper.getNav().pop();
		ap.resetXPath();

		helper.getNav().push();
		try {
			ap.selectXPath("./node[@type='Object']");

			try {
				while (ap.evalXPath() != -1) {
					String name = helper.getElementStringValue("name");
					MObject obj = fParent.system().state().objectByName(name);
					// Could be deleted
					if (obj != null) {
						ObjectNode node = visibleData.fObjectToNodeMap.get(obj);
						node.restorePlacementInfo(helper, version);
						if (isHidden(helper, version))
							hiddenObjects.add(obj);
					}
				}
			} catch (XPathEvalException e) {
				fLog.append(e.getMessage());
			} catch (NavException e) {
				fLog.append(e.getMessage());
			}
		} catch (XPathParseException e) {
			fLog.append(e.getMessage());
		}

		helper.getNav().pop();
		ap.resetXPath();

		helper.getNav().push();
		try {
			// Restore diamond nodes
			ap.selectXPath("./node[@type='DiamondNode']");

			try {
				while (ap.evalXPath() != -1) {
					String name = helper.getElementStringValue("name");
					MAssociation assoc = fParent.system().model().getAssociation(name);

					// Renamed or deleted
					if (assoc == null)
						continue;

					// Get connected objects
					List<MObject> connectedObjects = new LinkedList<MObject>();
					if (!helper.toFirstChild("connectedNode"))
						break;

					String objectName = helper.getElementStringValue();
					MObject obj = fParent.system().state().objectByName(objectName);

					if (obj != null)
						connectedObjects.add(obj);

					while (helper.toNextSibling("connectedNode")) {
						objectName = helper.getElementStringValue();
						obj = fParent.system().state().objectByName(objectName);

						if (obj != null) {
							connectedObjects.add(obj);
						}
					}

					// Modified
					if (assoc.associationEnds().size() != connectedObjects.size())
						continue;

					// n-ary links cannot be qualified therefore an empty list for the qualifer
					// values is provided
					MLink link = fParent.system().state().linkBetweenObjects(assoc, connectedObjects,
							Collections.<List<Value>>emptyList());

					// Could be deleted
					if (link != null) {
						org.tzi.use.gui.views.diagrams.elements.DiamondNode node = visibleData.fNaryLinkToDiamondNodeMap.get(link);
						helper.toParent();
						node.restorePlacementInfo(helper, version);
					}
				}
			} catch (XPathEvalException e) {
				fLog.append(e.getMessage());
			} catch (NavException e) {
				fLog.append(e.getMessage());
			}
		} catch (XPathParseException e) {
			fLog.append(e.getMessage());
		}
		helper.getNav().pop();
		ap.resetXPath();
		
		// Restore swimlane info
		helper.getNav().push();
		try {
			ap.selectXPath("./rtlswimlane");
			while (ap.evalXPath() != -1) {
				int x1 = helper.getElementIntegerValue("X1");
				int x2 = helper.getElementIntegerValue("X2");
				int x3 = helper.getElementIntegerValue("X3");
				int x4 = helper.getElementIntegerValue("X4");
				swimlane.updateSwimlane(x1, x2, x3, x4);
			}
		} catch (XPathParseException e) {
			fLog.append(e.getMessage());
		} catch (NavException e) {
			fLog.append(e.getMessage());
		} catch (XPathEvalException e) {
			fLog.append(e.getMessage());
		}
		helper.getNav().pop();
		ap.resetXPath();
		// Hide elements
		hideElementsInDiagram(hiddenObjects);
	}

	public RtlSwimlane getSwimlane() {
		return this.swimlane;
	}

	public RtlObjectNode getObjectNode(MObject mObject) {
		RtlObjectNode node = (RtlObjectNode) this.visibleData.fObjectToNodeMap.get(mObject);
		if (node == null)
			return (RtlObjectNode) this.hiddenData.fObjectToNodeMap.get(mObject);
		else return node;
	}

	public void setClassMap(Map<String, Side> classMap2) {
		this.classMap = classMap2;
	}
}
