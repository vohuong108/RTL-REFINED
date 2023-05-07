package org.uet.dse.rtlplus.diagram.tggdiagram;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.tzi.use.uml.sys.MObject;
import org.uet.dse.rtlplus.diagram.graph.PersistHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class stores information of lines presenting TGG rules
 * 
 * @author Duc-Hanh Dang
 */
public class TggSwimlane {
	/**
	 * @uml.property name="fTggDiagramView"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	TggDiagramView fTggDiagramView;
	/**
	 * @uml.property name="fSource_Border"
	 */
	protected Line2D fSource_Border;
	/**
	 * @uml.property name="fSource_Corr"
	 */
	protected Line2D fSource_Corr;
	/**
	 * @uml.property name="fTarget_Corr"
	 */
	protected Line2D fTarget_Corr;
	/**
	 * @uml.property name="fTarget_Border"
	 */
	protected Line2D fTarget_Border;
	/**
	 * @uml.property name="fTopTggLine"
	 */
	protected Line2D fTopTggLine;
	/**
	 * @uml.property name="fMiddleTggLine"
	 */
	protected Line2D fMiddleTggLine;
	/**
	 * @uml.property name="fBottomTggLine"
	 */
	protected Line2D fBottomTggLine;
	/**
	 * @uml.property name="fBottomLine"
	 */
	protected Line2D fBottomLine;

	public TggSwimlane(TggDiagramView tggDiagramView, int x1, int x2, int x3, int x4, int y1, int y2, int y3, int y4) {
		fTggDiagramView = tggDiagramView;
		Point lineStart, lineEnd;
		// the source_Border line
		lineStart = new Point(x1, 0);
		lineEnd = new Point(x1, y4);
		fSource_Border = new Line2D.Double(lineStart, lineEnd);
		// the source_Corr line
		lineStart = new Point(x2, 0);
		lineEnd = new Point(x2, y4);
		fSource_Corr = new Line2D.Double(lineStart, lineEnd);
		// the target_corr line
		lineStart = new Point(x3, 0);
		lineEnd = new Point(x3, y4);
		fTarget_Corr = new Line2D.Double(lineStart, lineEnd);
		// the target_corr line
		lineStart = new Point(x4, 0);
		lineEnd = new Point(x4, y4);
		fTarget_Border = new Line2D.Double(lineStart, lineEnd);
		// the topTggLine line
		lineStart = new Point(0, y1);
		lineEnd = new Point(x4, y1);
		fTopTggLine = new Line2D.Double(lineStart, lineEnd);
		// the middleTggLine line
		lineStart = new Point(0, y2);
		lineEnd = new Point(x4, y2);
		fMiddleTggLine = new Line2D.Double(lineStart, lineEnd);
		// the bottomTggLine line
		lineStart = new Point(0, y3);
		lineEnd = new Point(x4, y3);
		fBottomTggLine = new Line2D.Double(lineStart, lineEnd);
		// the bottomLine line
		lineStart = new Point(0, y4);
		lineEnd = new Point(x4, y4);
		fBottomLine = new Line2D.Double(lineStart, lineEnd);

		if (x1 == x2)
			fSource_Border = null;
		if (y1 == y2)
			fTopTggLine = null;
		if (y3 == y4)
			fBottomLine = null;
	}

	public Line2D getSource_Border() {
		return this.fSource_Border;
	}

	public Line2D getSource_Corr() {
		return fSource_Corr;
	}

	public Line2D getTarget_Corr() {
		return fTarget_Corr;
	}

	public Line2D getTarget_Border() {
		return fTarget_Border;
	}

	public Line2D getTopTggLine() {
		return fTopTggLine;
	}

	public Line2D getMiddleTggLine() {
		return fMiddleTggLine;
	}

	public Line2D getBottomTggLine() {
		return fBottomTggLine;
	}

	public Line2D getBottomLine() {
		return fBottomLine;
	}

	// checking if a point is in the SL pattern of the triple rule
	public boolean isWithinSL(double x, double y) {
		boolean res = true;
		res = res && (getMinX() < x) && (x < fSource_Corr.getX1());
		res = res && (getMinY() < y) && (y < fMiddleTggLine.getY1());
		return res;
	}

	public boolean isWithinSR(double x, double y) {
		boolean res = true;
		res = res && (getMinX() < x) && (x < fSource_Corr.getX1());
		res = res && (fMiddleTggLine.getY1() < y) && (y < fBottomTggLine.getY1());
		return res;
	}

	public boolean isWithinCL(double x, double y) {
		boolean res = true;
		res = res && (fSource_Corr.getX1() < x) && (x < fTarget_Corr.getX1());
		res = res && (getMinY() < y) && (y < fMiddleTggLine.getY1());
		return res;
	}

	public boolean isWithinCR(double x, double y) {
		boolean res = true;
		res = res && (fSource_Corr.getX1() < x) && (x < fTarget_Corr.getX1());
		res = res && (fMiddleTggLine.getY1() < y) && (y < fBottomTggLine.getY1());
		return res;
	}

	public boolean isWithinTL(double x, double y) {
		boolean res = true;
		res = res && (fTarget_Corr.getX1() < x) && (x < fTarget_Border.getX1());
		res = res && (getMinY() < y) && (y < fMiddleTggLine.getY1());
		return res;
	}

	public boolean isWithinTR(double x, double y) {
		boolean res = true;
		res = res && (fTarget_Corr.getX1() < x) && (x < fTarget_Border.getX1());
		res = res && (fMiddleTggLine.getY1() < y) && (y < fBottomTggLine.getY1());
		return res;
	}

	public int getSwimlane(int x, int y) {
		// res = 1: fSource_Border;
		// res = 2: fSource_Corr;
		// res = 3: fTarget_Corr;
		// res = 4: fTarget_Border;
		// res = 11: fTopTggLine;
		// res = 12: fMiddleTggLine;
		// res = 13: fBottomTggLine;
		// res = 14: fBottomLine;

		int res = 0;

		if (fSource_Border != null) {
			if (fSource_Border.ptLineDist(x, y) <= 2) {
				res = 1;
				return res;
			}
		}

		if (fSource_Corr.ptLineDist(x, y) <= 2) {
			res = 2;
			return res;
		}

		if (fTarget_Corr.ptLineDist(x, y) <= 2) {
			res = 3;
			return res;
		}

		if (fTarget_Border.ptLineDist(x, y) <= 2) {
			res = 4;
			return res;
		}

		if (fTopTggLine != null) {
			if (fTopTggLine.ptLineDist(x, y) <= 2) {
				res = 10;
				return res;
			}
		}

		if (fMiddleTggLine.ptLineDist(x, y) <= 2) {
			res = 20;
			return res;
		}

		if (fBottomTggLine.ptLineDist(x, y) <= 2) {
			res = 30;
			return res;
		}

		if (fBottomLine != null) {
			if (fBottomLine.ptLineDist(x, y) <= 2) {
				res = 40;
				return res;
			}
		}

		// System.out.println(res);
		return res;
	}

	public void moveSwimlane(int draggingSwimlane, int dx, int dy) {
		switch (draggingSwimlane) {
		case 1: // fSource_Border;
			if (fSource_Border == null)
				break;
			Point2D p1 = fSource_Border.getP1();
			Point2D p2 = fSource_Border.getP2();
			if ((0 < p1.getX() + dx) && (p1.getX() + dx < fSource_Corr.getX1())) {
				p1.setLocation(p1.getX() + dx, p1.getY());
				p2.setLocation(p2.getX() + dx, p2.getY());
				if (dx > 0) {
					List<MObject> objects = this.fTggDiagramView.getTggRule().getSrcRule().getAllObjects();
					boolean ok = tryMoveLeftRightObjects(objects, dx);
					if (!ok) {
						p1.setLocation(p1.getX() - dx, p1.getY());
						p2.setLocation(p2.getX() - dx, p2.getY());
					}
				}
				fSource_Border.setLine(p1, p2);
			}

			break;
		case 2: // fSource_Corr;
			p1 = fSource_Corr.getP1();
			p2 = fSource_Corr.getP2();
			if ((getMinX() < p1.getX() + dx) && (p1.getX() + dx < fTarget_Corr.getX1())) {
				// try to move the swimlane, move objects, and rollback in case an error occurs
				p1.setLocation(p1.getX() + dx, p1.getY());
				p2.setLocation(p2.getX() + dx, p2.getY());
				List<MObject> objects;
				if (dx < 0) {
					objects = this.fTggDiagramView.getTggRule().getSrcRule().getAllObjects();
				} else {
					objects = this.fTggDiagramView.getTggRule().getCorrRule().getAllObjects();
				}
				boolean ok = tryMoveLeftRightObjects(objects, dx);
				if (!ok) {
					p1.setLocation(p1.getX() - dx, p1.getY());
					p2.setLocation(p2.getX() - dx, p2.getY());
				}
				fSource_Corr.setLine(p1, p2);
			}
			break;
		case 3: // fTarget_Corr;
			p1 = fTarget_Corr.getP1();
			p2 = fTarget_Corr.getP2();
			if ((fSource_Corr.getX1() < p1.getX() + dx) && (p1.getX() + dx < fTarget_Border.getX1())) {
				// try to move the swimlane, move objects, and rollback in case an error occurs
				p1.setLocation(p1.getX() + dx, p1.getY());
				p2.setLocation(p2.getX() + dx, p2.getY());
				List<MObject> objects;
				if (dx < 0) {
					objects = this.fTggDiagramView.getTggRule().getCorrRule().getAllObjects();
				} else {
					objects = this.fTggDiagramView.getTggRule().getTrgRule().getAllObjects();
				}
				boolean ok = tryMoveLeftRightObjects(objects, dx);
				if (!ok) {
					p1.setLocation(p1.getX() - dx, p1.getY());
					p2.setLocation(p2.getX() - dx, p2.getY());
				}
				fTarget_Corr.setLine(p1, p2);
			}
			break;
		case 4: // fTarget_Border;
			p1 = fTarget_Border.getP1();
			p2 = fTarget_Border.getP2();
			if (fTarget_Corr.getX1() < p1.getX() + dx) {
				// try to move the swimlane, move objects, and rollback in case an error occurs
				p1.setLocation(p1.getX() + dx, p1.getY());
				p2.setLocation(p2.getX() + dx, p2.getY());
				boolean ok = true;
				if (dx < 0) {
					List<MObject> objects = this.fTggDiagramView.getTggRule().getTrgRule().getAllObjects();
					ok = tryMoveLeftRightObjects(objects, dx);
					if (!ok) {
						p1.setLocation(p1.getX() - dx, p1.getY());
						p2.setLocation(p2.getX() - dx, p2.getY());
					}
				}
				fTarget_Border.setLine(p1, p2);
				if (ok) {
					if (fTopTggLine != null) {
						fTopTggLine.setLine(fTopTggLine.getX1(), fTopTggLine.getY1(), fTopTggLine.getX2() + dx,
								fTopTggLine.getY2());
					}
					fMiddleTggLine.setLine(fMiddleTggLine.getX1(), fMiddleTggLine.getY1(), fMiddleTggLine.getX2() + dx,
							fMiddleTggLine.getY2());
					fBottomTggLine.setLine(fBottomTggLine.getX1(), fBottomTggLine.getY1(), fBottomTggLine.getX2() + dx,
							fBottomTggLine.getY2());
					if (fBottomLine != null) {
						fBottomLine.setLine(fBottomLine.getX1(), fBottomLine.getY1(), fBottomLine.getX2() + dx,
								fBottomLine.getY2());
					}
				}
			}
			break;
		case 10: // fTopTggLine;
			if (fTopTggLine == null)
				break;
			p1 = fTopTggLine.getP1();
			p2 = fTopTggLine.getP2();
			if ((0 < p1.getY() + dy) && (p1.getY() + dy < fMiddleTggLine.getY1())) {
				// try to move the swimlane, move objects, and rollback in case an error occurs
				p1.setLocation(p1.getX(), p1.getY() + dy);
				p2.setLocation(p2.getX(), p2.getY() + dy);
				Set<MObject> objects = this.fTggDiagramView.getTggRule().getSystemStateLHS().allObjects();
				boolean ok = tryMoveUpDownObjects(objects, dy);
				if (!ok) {
					p1.setLocation(p1.getX(), p1.getY() - dy);
					p2.setLocation(p2.getX(), p2.getY() - dy);
				}
				fTopTggLine.setLine(p1, p2);
			}
			break;
		case 20: // fMiddleTggLine;
			p1 = fMiddleTggLine.getP1();
			p2 = fMiddleTggLine.getP2();
			if ((getMinY() < p1.getY() + dy) && (p1.getY() + dy < fBottomTggLine.getY1())) {
				// try to move the swimlane, move objects, and rollback in case an error occurs
				p1.setLocation(p1.getX(), p1.getY() + dy);
				p2.setLocation(p2.getX(), p2.getY() + dy);
				Set<MObject> objects;
				if (dy < 0) {
					objects = this.fTggDiagramView.getTggRule().getSystemStateLHS().allObjects();
				} else {
					objects = this.fTggDiagramView.getTggRule().getSystemStateRHS().allObjects();
				}
				boolean ok = tryMoveUpDownObjects(objects, dy);
				if (!ok) {
					p1.setLocation(p1.getX(), p1.getY() - dy);
					p2.setLocation(p2.getX(), p2.getY() - dy);
				}
				fMiddleTggLine.setLine(p1, p2);
			}
			break;
		case 30: // fBottomTggLine;
			p1 = fBottomTggLine.getP1();
			p2 = fBottomTggLine.getP2();
			if ((fMiddleTggLine.getY1() < p1.getY() + dy) && (p1.getY() + dy < getMaxY())) {
				// try to move the swimlane, move objects, and rollback in case an error occurs
				p1.setLocation(p1.getX(), p1.getY() + dy);
				p2.setLocation(p2.getX(), p2.getY() + dy);
				boolean ok = true;
				if (dy < 0) {
					Set<MObject> objects = this.fTggDiagramView.getTggRule().getSystemStateRHS().allObjects();
					ok = tryMoveUpDownObjects(objects, dy);
					if (!ok) {
						p1.setLocation(p1.getX(), p1.getY() - dy);
						p2.setLocation(p2.getX(), p2.getY() - dy);
					}
				}
				fBottomTggLine.setLine(p1, p2);
				if (ok && (fBottomLine == null)) {
					if (fSource_Border != null) {
						fSource_Border.setLine(fSource_Border.getX1(), fSource_Border.getY1(), fSource_Border.getX2(),
								fSource_Border.getY2() + dy);
					}
					fSource_Corr.setLine(fSource_Corr.getX1(), fSource_Corr.getY1(), fSource_Corr.getX2(),
							fSource_Corr.getY2() + dy);
					fTarget_Corr.setLine(fTarget_Corr.getX1(), fTarget_Corr.getY1(), fTarget_Corr.getX2(),
							fTarget_Corr.getY2() + dy);
					fTarget_Border.setLine(fTarget_Border.getX1(), fTarget_Border.getY1(), fTarget_Border.getX2(),
							fTarget_Border.getY2() + dy);
				}
			}
			break;
		case 40: // fBottomLine;
			if (fBottomLine == null)
				break;
			p1 = fBottomLine.getP1();
			p2 = fBottomLine.getP2();
			if (fBottomTggLine.getY1() < p1.getY() + dy) {
				p1.setLocation(p1.getX(), p1.getY() + dy);
				p2.setLocation(p2.getX(), p2.getY() + dy);
				fBottomLine.setLine(p1, p2);
				if (fSource_Border != null) {
					fSource_Border.setLine(fSource_Border.getX1(), fSource_Border.getY1(), fSource_Border.getX2(),
							fSource_Border.getY2() + dy);
				}
				fSource_Corr.setLine(fSource_Corr.getX1(), fSource_Corr.getY1(), fSource_Corr.getX2(),
						fSource_Corr.getY2() + dy);
				fTarget_Corr.setLine(fTarget_Corr.getX1(), fTarget_Corr.getY1(), fTarget_Corr.getX2(),
						fTarget_Corr.getY2() + dy);
				fTarget_Border.setLine(fTarget_Border.getX1(), fTarget_Border.getY1(), fTarget_Border.getX2(),
						fTarget_Border.getY2() + dy);
			}
			break;
		default:
		}

	}

	public boolean tryMoveLeftRightObjects(List<MObject> objects, int dx) {
		boolean res = true;
		List<TggObjectNode> movedObjects = new ArrayList<>();
		for (MObject obj : objects) {
			TggObjectNode node = fTggDiagramView.getTggDiagram().getObjectNode(obj);
			int gap = dx > 0 ? 10 : -10;
			if (!node.checkNewPositition(node.getX() - gap, node.getY())) {
				if (node.checkNewPositition(node.getX() + dx + gap, node.getY())) {
					node.setPosition(node.getX() + dx, node.getY());
					movedObjects.add(node);
				} else {
					res = false;
					break;
				}
			}
		}
		if (!res) {
			// rolling back
			for (TggObjectNode node : movedObjects) {
				node.setPosition(node.getX() - dx, node.getY());
			}
		}
		return res;
	}

	public boolean tryMoveUpDownObjects(Set<MObject> objects, int dy) {
		boolean res = true;
		List<TggObjectNode> movedObjects = new ArrayList<>();
		for (MObject obj : objects) {
			TggObjectNode node = fTggDiagramView.getTggDiagram().getObjectNode(obj);
			int gap = dy > 0 ? 10 : -10;
			if (!node.checkNewPositition(node.getX(), node.getY() - gap)) {
				if (node.checkNewPositition(node.getX(), node.getY() + dy)) {
					node.setPosition(node.getX(), node.getY() + dy);
					movedObjects.add(node);
				} else {
					res = false;
					break;
				}
			}
		}
		if (!res) {
			for (TggObjectNode node : movedObjects) {
				node.setPosition(node.getX(), node.getY() - dy);
			}
		}
		return res;
	}

	public void updateSwimlane(int x1, int x2, int x3, int x4, int y1, int y2, int y3, int y4) {
		if (fSource_Border != null) {
			fSource_Border.setLine(x1, 0, x1, y4);
		}
		fSource_Corr.setLine(x2, 0, x2, y4);
		fTarget_Corr.setLine(x3, 0, x3, y4);
		fTarget_Border.setLine(x4, 0, x4, y4);
		if (fTopTggLine != null) {
			fTopTggLine.setLine(0, y1, x4, y1);
		}
		fMiddleTggLine.setLine(0, y2, x4, y2);
		fBottomTggLine.setLine(0, y3, x4, y3);
		if (fBottomLine != null) {
			fBottomLine.setLine(0, y4, x4, y4);
		}
	}

	public double getMinX() {
		return fSource_Border == null ? 0 : fSource_Border.getX1();
	}

	public double getMinY() {
		return fTopTggLine == null ? 0 : fTopTggLine.getY1();
	}

	public double getMaxY() {
		return fBottomLine == null ? (fBottomTggLine.getY1() + 100) : fBottomLine.getY1();
	}

	public void storePlacementInfo(PersistHelper helper, Element parent) {
		Document doc = parent.getOwnerDocument();

		Element nodeElement = doc.createElement("swimlane");
		if (fSource_Border == null)
			helper.appendChild(nodeElement, "X1", String.valueOf((int) fSource_Corr.getX1()));
		else
			helper.appendChild(nodeElement, "X1", String.valueOf((int) fSource_Border.getX1()));
		helper.appendChild(nodeElement, "X2", String.valueOf((int) fSource_Corr.getX1()));
		helper.appendChild(nodeElement, "X3", String.valueOf((int) fTarget_Corr.getX1()));
		helper.appendChild(nodeElement, "X4", String.valueOf((int) fTarget_Border.getX1()));

		if (fTopTggLine == null)
			helper.appendChild(nodeElement, "Y1", String.valueOf((int) fMiddleTggLine.getY1()));
		else
			helper.appendChild(nodeElement, "Y1", String.valueOf((int) fTopTggLine.getY1()));
		helper.appendChild(nodeElement, "Y2", String.valueOf((int) fMiddleTggLine.getY1()));
		helper.appendChild(nodeElement, "Y3", String.valueOf((int) fBottomTggLine.getY1()));
		if (fBottomLine == null)
			helper.appendChild(nodeElement, "Y4", String.valueOf((int) fBottomTggLine.getY1()));
		else
			helper.appendChild(nodeElement, "Y4", String.valueOf((int) fBottomLine.getY1()));

		parent.appendChild(nodeElement);
	}
}