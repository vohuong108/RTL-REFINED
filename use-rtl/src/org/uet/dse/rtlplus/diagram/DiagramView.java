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

// $Id: DiagramView.java 2478 2011-09-10 09:03:06Z lhamann $

package org.uet.dse.rtlplus.diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.tzi.use.graph.DirectedGraph;
import org.tzi.use.gui.util.Selection;
import org.tzi.use.util.Log;
import org.uet.dse.rtlplus.diagram.event.ActionLoadLayout;
import org.uet.dse.rtlplus.diagram.event.ActionSaveLayout;
import org.uet.dse.rtlplus.diagram.graph.PersistHelper;
import org.uet.dse.rtlplus.diagram.tggdiagram.TggSpringLayout;
import org.uet.dse.rtlplus.diagram.tggdiagram.TggSwimlane;
import org.w3c.dom.Element;

/**
 * Combines everything that the class and object diagram have in common.
 * 
 * @version $ProjectVersion: 0.393 $
 * @author Fabian Gutsche
 */
@SuppressWarnings({ "unchecked", "serial" })
public abstract class DiagramView extends JPanel implements Printable {

	/**
	 * This graph contains all visible elements of a diagram
	 */
	protected DirectedGraph fGraph;

	protected PrintWriter fLog;

	protected Selection fNodeSelection;

	protected Selection fEdgeSelection;

	// needed for autolayout
	protected LayoutThread fLayoutThread;
	protected volatile TggSpringLayout fLayouter;

	protected TggSwimlane fTggSwimlane;

	protected final Object fLock = new Object();

	protected ActionLoadLayout fActionLoadLayout;

	protected ActionSaveLayout fActionSaveLayout;

	protected DiagramOptions fOpt;

	/**
	 * This value is read from the system properties file. It determines the minimum
	 * width of a class node
	 */
	protected int minClassNodeWidth;

	/**
	 * This value is read from the system properties file. It determines the minimum
	 * height of a class node
	 */
	protected int minClassNodeHeight;

	public DiagramView() {
		minClassNodeHeight = Integer.parseInt(System.getProperty("use.gui.view.classdiagram.class.minheight"));
		minClassNodeWidth = Integer.parseInt(System.getProperty("use.gui.view.classdiagram.class.minwidth"));
		setFont(Font.getFont("use.gui.view.objectdiagram", getFont()));
	}

	/**
	 * Determines if the auto layout of the diagram is on or off.
	 * 
	 * @return <code>true</code> if the auto layout is on, otherwise
	 *         <code>false</code>
	 */
	public boolean isDoAutoLayout() {
		return fOpt.isDoAutoLayout();
	}

	/**
	 * Draws the diagram.
	 */
	public void paintComponent(Graphics g) {
		synchronized (fLock) {
			drawDiagram(g);
		}
	}

	/**
	 * Determines if the popup menu of this diagram should be shown.
	 * 
	 * @param e
	 *            MouseEvent.
	 * @return <code>true</code> if the popup menu should be shown, otherwise
	 *         <code>false</code>.
	 */
	public abstract boolean maybeShowPopup(MouseEvent e);

	/**
	 * When the content of the diagram changes,e.g., after hiding or showing a node,
	 * additional calculations are needed, which are not required when "just"
	 * redrawing.
	 */
	private boolean isDiagramContentChanged = true;

	/**
	 * When the content of the diagram changes,e.g., after hiding or showing a node,
	 * additional calculations are needed, which are not required when "just"
	 * redrawing.
	 */
	public synchronized void invalidateContent() {
		this.isDiagramContentChanged = true;
		this.repaint();
	}

	/**
	 * Draws the diagram.
	 * 
	 * @param g
	 *            The diagram is drawn into this Graphics object.
	 */
	public synchronized void drawDiagram(Graphics g) {
		if (fOpt.isDoAntiAliasing())
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension d = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, d.width, d.height);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(192, 192, 192));
		if (fTggSwimlane.getSource_Border() != null) {
			g2d.draw(fTggSwimlane.getSource_Border());
		}
		g2d.setStroke(new BasicStroke(5.0f));
		g2d.draw(fTggSwimlane.getSource_Corr());
		g2d.draw(fTggSwimlane.getTarget_Corr());
		g2d.draw(fTggSwimlane.getTarget_Border());
		if (fTggSwimlane.getTopTggLine() != null) {
			g2d.draw(fTggSwimlane.getTopTggLine());
		}
		g2d.draw(fTggSwimlane.getMiddleTggLine());
		g2d.draw(fTggSwimlane.getBottomTggLine());
		if (fTggSwimlane.getBottomLine() != null) {
			g2d.draw(fTggSwimlane.getBottomLine());
		}
		g2d.setStroke(new BasicStroke());
		g2d.setColor(Color.black);
		if (this.fOpt.showGrid()) {
			drawGrid(g2d);
		}

		Iterator<EdgeBase> edgeIterator;
		// Calculates the min height of classifiers
		if (isDiagramContentChanged) {
			edgeIterator = fGraph.edgeIterator();
			while (edgeIterator.hasNext()) {
				EdgeBase e = edgeIterator.next();
				e.onFirstDraw(g2d);
			}
		}

		// set rectangle size for nodes
		// It is important to do this first, otherwise rolenames will
		// not be dynamically moved after displaying attributes! And
		// after hiding an object which is participating in a two link
		// association, the two links won't be displayed in the right
		// way.
		Iterator<NodeBase> nodeIterator = fGraph.iterator();
		double heightHint;
		while (nodeIterator.hasNext()) {
			NodeBase n = nodeIterator.next();
			n.setRectangleSize(g2d);
			// Set min height wrt. displayed qualifiers

			// These variables are used to set the offset only when more then
			// one special end, e.g., a qualifier are present.
			// Otherwise they should be centered (offset == 0)
			EdgeBase firstSpecial = null;
			int specialCounter = 0;
			boolean firstWasSource = true;

			if (isDiagramContentChanged) {
				n.setMinHeight(minClassNodeHeight);
				int minHeight = 0;
				Set<EdgeBase> myEdges = fGraph.allEdges(n);
				for (EdgeBase e : myEdges) {
					heightHint = 0;
					if (e.source().equals(n) && e.hasSpecialSource()) {
						if (specialCounter > 0) {
							e.setSpecialSourceYOffSet(minHeight + 4);
						} else {
							e.setSpecialSourceYOffSet(0);
							firstSpecial = e;
							firstWasSource = true;
						}

						heightHint = e.getSourceHeightHint();
						++specialCounter;
					}
					if (e.target().equals(n) && e.hasSpecialTarget()) {
						if (specialCounter > 0) {
							e.setSpecialTargetYOffSet(minHeight + 4);
						} else {
							firstSpecial = e;
							firstWasSource = false;
						}

						heightHint = e.getTargetHeightHint();
						++specialCounter;
					}

					if (specialCounter == 2) {
						if (firstWasSource) {
							firstSpecial.setSpecialSourceYOffSet(4);
						} else {
							firstSpecial.setSpecialTargetYOffSet(4);
						}
					}

					if (heightHint > 0)
						minHeight += heightHint + 4;
				}
				n.setMinHeight(Math.max(minHeight + 4, n.getMinHeight()));
				n.setRectangleSize(g2d);
			}
		}

		// draw edges
		// they need to be drawn first otherwise the association will
		// be drawn above the nodes
		edgeIterator = fGraph.edgeIterator();
		Set<EdgeBase> drawnEdges = new HashSet<EdgeBase>();
		Set<EdgeBase> edges = new HashSet<EdgeBase>();

		while (edgeIterator.hasNext()) {
			EdgeBase e = edgeIterator.next();
			if (!drawnEdges.contains(e)) {
				edges = e.checkForNewPositionAndDraw(fGraph, g2d);
				if (edges != null) {
					drawnEdges.addAll(edges);
				}
			}
		}

		double maxX = 0;
		double maxY = 0;

		// draw nodes
		nodeIterator = fGraph.iterator();
		while (nodeIterator.hasNext()) {
			NodeBase n = nodeIterator.next();
			n.draw(g2d);
			maxX = Math.max(maxX, n.getX() + n.getWidth());
			maxY = Math.max(maxY, n.getY() + n.getHeight());
		}

		Dimension newDimension = new Dimension((int) maxX + 5, (int) maxY + 5);
		if (!newDimension.equals(this.getPreferredSize())) {
			this.setPreferredSize(newDimension);
			this.revalidate();
		}

		isDiagramContentChanged = false;
	}

	/**
	 * @param g
	 */
	private void drawGrid(Graphics2D g) {
		Color old = g.getColor();
		g.setColor(Color.LIGHT_GRAY);

		Stroke oldStroke = g.getStroke();
		BasicStroke newStroke = new BasicStroke(1.0F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0F,
				new float[] { 5.0F, 5.0F }, 0.0F);

		g.setStroke(newStroke);

		// Vertical lines
		for (int x = 50; x < getSize().getWidth(); x += 50) {
			g.drawLine(x, 0, x, (int) getSize().getHeight());
			if (Log.isDebug())
				g.drawString(String.valueOf(x), x + 4, 10);
		}

		// Horizontal lines
		for (int y = 50; y < getSize().getHeight(); y += 50) {
			g.drawLine(0, y, (int) getSize().getWidth(), y);
			if (Log.isDebug())
				g.drawString(String.valueOf(y), 2, y + 12);
		}

		g.setColor(old);
		g.setStroke(oldStroke);
	}

	/**
	 * Returns the options of a specific diagram.
	 */
	public DiagramOptions getOptions() {
		return fOpt;
	}

	/**
	 * Returns the log panel.
	 */
	public PrintWriter getLog() {
		return fLog;
	}

	/**
	 * The graph of the diagram
	 * 
	 * @return
	 */
	public DirectedGraph getGraph() {
		return this.fGraph;
	}

	/**
	 * Prints the diagram. Implementation of Printable interface.
	 */
	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
		if (pi >= 1)
			return Printable.NO_SUCH_PAGE;

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		g2d.translate(pf.getImageableWidth() / 2, pf.getImageableHeight() / 2);

		Dimension d = getSize();
		double scale = Math.min(pf.getImageableWidth() / d.width, pf.getImageableHeight() / d.height);
		// fit to page
		if (scale < 1.0)
			g2d.scale(scale, scale);
		g2d.translate(-d.width / 2.0, -d.height / 2.0);

		Font f = Font.getFont("use.gui.print.diagramFont", getFont());
		g2d.setFont(f);

		drawDiagram(g2d);
		return Printable.PAGE_EXISTS;
	}

	/**
	 * Prints the diagram.
	 * 
	 * @param pf
	 * @param jobname
	 */
	public void printDiagram(PageFormat pf, String jobname) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setJobName(jobname);
		job.setPrintable(this, pf);

		if (job.printDialog()) {
			// Print the job if the user didn't cancel printing
			try {
				job.print();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Trys to find an edge on the given coordinates and adds a node on this edge if
	 * the mouse clickcount is 2.
	 * 
	 * @param x
	 *            X-coordinate.
	 * @param y
	 *            Y-coordinate.
	 * @param clickCount
	 *            The mouse click count.
	 */
	public void findEdge(int x, int y, int clickCount) {
		Iterator it = fGraph.edgeIterator();

		while (it.hasNext()) {
			EdgeBase e = (EdgeBase) it.next();
			e.occupiesThanAdd(x, y, clickCount);
			repaint();
		}
	}

	/**
	 * Finds node occupying the given position.
	 * 
	 * @return null if no such node could be found.
	 */
	public PlaceableNode findNode(int x, int y) {
		PlaceableNode res = null;
		Iterator nIter = fGraph.iterator();

		while (nIter.hasNext()) {
			NodeBase n = (NodeBase) nIter.next();

			if (n instanceof DiamondNode) {
				EdgeProperty an = ((DiamondNode) n).getAssocName();
				if (an != null && an.occupies(x, y)) {
					res = an;
				}
			}
			if (n.occupies(x, y)) {
				// Do not break here. We search in the same order
				// which is used for drawing. There may be another
				// node drawn on top of this node. That node should be
				// picked.
				res = n;
			}
		}

		if (res == null) {
			Iterator eIter = fGraph.edgeIterator();

			while (eIter.hasNext()) {
				EdgeBase e = (EdgeBase) eIter.next();
				res = e.getWayPoint(x, y);

				if (res != null)
					break;
			}
		}
		return res;
	}

	/**
	 * Returns a unified JPopUpMenu of the class- and objectdiagram.
	 */
	protected JPopupMenu unionOfPopUpMenu() {
		// context menu on right mouse click
		JPopupMenu popupMenu = new JPopupMenu();

		final JCheckBoxMenuItem cbAutoLayout = new JCheckBoxMenuItem("Auto-Layout");
		cbAutoLayout.setState(fOpt.isDoAutoLayout());
		cbAutoLayout.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				fOpt.setDoAutoLayout(ev.getStateChange() == ItemEvent.SELECTED);
			}
		});
		final JCheckBoxMenuItem cbAssocNames = new JCheckBoxMenuItem("Show association names");
		cbAssocNames.setState(fOpt.isShowAssocNames());
		cbAssocNames.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				fOpt.setShowAssocNames(ev.getStateChange() == ItemEvent.SELECTED);
				repaint();
			}
		});
		final JCheckBoxMenuItem cbRolenames = new JCheckBoxMenuItem("Show role names");
		cbRolenames.setState(fOpt.isShowRolenames());
		cbRolenames.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				fOpt.setShowRolenames(ev.getStateChange() == ItemEvent.SELECTED);
				repaint();
			}
		});
		final JCheckBoxMenuItem cbAttrValues = new JCheckBoxMenuItem("Show attributes"); // values");
		cbAttrValues.setState(fOpt.isShowAttributes());
		cbAttrValues.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				fOpt.setShowAttributes(ev.getStateChange() == ItemEvent.SELECTED);
				repaint();
			}
		});

		final JCheckBoxMenuItem cbAntiAliasing = new JCheckBoxMenuItem("Anti-aliasing");
		cbAntiAliasing.setState(fOpt.isDoAntiAliasing());
		cbAntiAliasing.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				fOpt.setDoAntiAliasing(ev.getStateChange() == ItemEvent.SELECTED);
				repaint();
			}
		});

		final JCheckBoxMenuItem cbShowGrid = new JCheckBoxMenuItem("Show grid");
		cbShowGrid.setState(fOpt.showGrid());
		cbShowGrid.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				fOpt.setShowGrid(ev.getStateChange() == ItemEvent.SELECTED);
				repaint();
			}
		});

		popupMenu.add(cbAssocNames);
		popupMenu.add(cbRolenames);
		popupMenu.add(cbAttrValues);
		popupMenu.addSeparator();
		popupMenu.add(cbAutoLayout);
		popupMenu.add(cbAntiAliasing);
		popupMenu.add(cbShowGrid);

		if (fGraph.size() > 0) {
			popupMenu.addSeparator();
			popupMenu.add(fActionLoadLayout);
			popupMenu.add(fActionSaveLayout);
		}

		return popupMenu;
	}

	/**
	 * Stops the auto layout thread.
	 */
	public void stopLayoutThread() {
		fLayoutThread.doLayout = false;
		fLayoutThread.interrupt();
	}

	/**
	 * Starts the auto layout thread.
	 */
	public void startLayoutThread() {
		fLayoutThread = new LayoutThread();
		fLayoutThread.doLayout = true;
		fLayoutThread.start();
	}

	/**
	 * This is called by the LayoutThread to generate a new layout. The layouter
	 * object can be reused as long as the graph and the size of the drawing area
	 * does not change.
	 */
	private synchronized void autoLayout() {
		if (fLayouter == null) {
			int w = getWidth();
			int h = getHeight();
			if (w == 0 || h == 0)
				return;
			fLayouter = new TggSpringLayout(fGraph, w, h, 80, 20);
			fLayouter.setEdgeLen(150);
		}
		fLayouter.layout();
		repaint();
	}

	class LayoutThread extends Thread {
		public boolean doLayout = true;

		public void run() {
			while (doLayout) {
				if (isDoAutoLayout()) {
					synchronized (fLock) {
						autoLayout();
					}
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

	/**
	 * Stores the placement info inside of the given element
	 * 
	 * @param rootElement
	 */
	public abstract void storePlacementInfos(PersistHelper helper, Element rootElement);

	/**
	 * @param rootElement
	 */
	public abstract void restorePlacementInfos(PersistHelper helper, Element rootElement, int version);

	/**
	 * Show all hidden elements again
	 */
	public abstract void showAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#removeNotify()
	 */
	@Override
	public void removeNotify() {
		super.removeNotify();
		this.stopLayoutThread();
	}
}
