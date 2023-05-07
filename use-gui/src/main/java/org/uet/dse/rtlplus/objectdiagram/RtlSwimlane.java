package org.uet.dse.rtlplus.objectdiagram;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.tzi.use.gui.util.PersistHelper;
import org.uet.dse.rtlplus.mm.MRuleCollection.Side;
import org.w3c.dom.Element;

/**
 * This class stores information of lines presenting TGG rules
 * 
 * @author Duc-Hanh Dang
 */
public class RtlSwimlane {
	private RtlObjectDiagramView diagramView;

	private Line2D leftmostLine;
	private Line2D leftLine;
	private Line2D rightLine;
	private Line2D rightmostLine;
	private static final int Y2 = 1000;
	
	public static enum SwimlaneLine {NONE, LEFTMOST, LEFT, RIGHT, RIGHTMOST}

	public RtlSwimlane(RtlObjectDiagramView rtlObjectDiagramView, int x1, int x2, int x3, int x4) {
		diagramView = rtlObjectDiagramView;
		Point lineStart, lineEnd;
		// Leftmost line
		lineStart = new Point(x1, 0);
		lineEnd = new Point(x1, Y2);
		leftmostLine = new Line2D.Double(lineStart, lineEnd);
		// Line between source and corr
		lineStart = new Point(x2, 0);
		lineEnd = new Point(x2, Y2);
		leftLine = new Line2D.Double(lineStart, lineEnd);
		// Line between target and corr
		lineStart = new Point(x3, 0);
		lineEnd = new Point(x3, Y2);
		rightLine = new Line2D.Double(lineStart, lineEnd);
		// Rightmost line
		lineStart = new Point(x4, 0);
		lineEnd = new Point(x4, Y2);
		rightmostLine = new Line2D.Double(lineStart, lineEnd);
	}

	public Line2D getLeftmostLine() {
		return leftmostLine;
	}

	public Line2D getLeftLine() {
		return leftLine;
	}

	public Line2D getRightLine() {
		return rightLine;
	}

	public Line2D getRightmostLine() {
		return rightmostLine;
	}

	/**
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 * @return The line corresponding to point (x,y)
	 */
	public SwimlaneLine getLine(int x, int y) {
		if (leftmostLine.ptLineDist(x, y) <= 5)
			return SwimlaneLine.LEFTMOST;
		if (leftLine.ptLineDist(x, y) <= 5)
			return SwimlaneLine.LEFT;
		if (rightLine.ptLineDist(x, y) <= 5)
			return SwimlaneLine.RIGHT;
		if (rightmostLine.ptLineDist(x, y) <= 5)
			return SwimlaneLine.RIGHTMOST;
		return SwimlaneLine.NONE;
	}
	
	public void moveSwimlane(SwimlaneLine line, int dx) {
		if (dx != 0) {
			switch (line) {
			case LEFTMOST:
				if (dx > 0) {
					Point2D p1 = leftmostLine.getP1();
					Point2D p2 = leftmostLine.getP2();
					// Move line
					p1.setLocation(leftmostLine.getX1() + dx, leftmostLine.getY1());
					p2.setLocation(leftmostLine.getX2() + dx, leftmostLine.getY2());
					// Try to move objects
					List<RtlObjectNode> nodesToMove1 = diagramView.getDiagram().getSrcObjectNodes();
					if (!tryMoveLeftRightObjects(nodesToMove1, dx)) {
						p1.setLocation(leftmostLine.getX1() - dx, leftmostLine.getY1());
						p2.setLocation(leftmostLine.getX2() - dx, leftmostLine.getY2());
					}
					leftmostLine.setLine(p1, p2);
				} else {
					leftmostLine.setLine(leftmostLine.getX1() + dx, leftmostLine.getY1(),
							leftmostLine.getX2() + dx, leftmostLine.getY2());
				}
				break;
			case LEFT: {
				Point2D p1 = leftLine.getP1();
				Point2D p2 = leftLine.getP2();
				// Move line
				p1.setLocation(leftLine.getX1() + dx, leftLine.getY1());
				p2.setLocation(leftLine.getX2() + dx, leftLine.getY2());
				// Try to move objects
				List<RtlObjectNode> nodesToMove2 = (dx < 0) ? diagramView.getDiagram().getSrcObjectNodes()
						: diagramView.getDiagram().getCorObjectNodes();
				if (!tryMoveLeftRightObjects(nodesToMove2, dx)) {
					p1.setLocation(leftLine.getX1() - dx, leftLine.getY1());
					p2.setLocation(leftLine.getX2() - dx, leftLine.getY2());
				}
				leftLine.setLine(p1, p2);
			}
			break;
			case RIGHT: {
				Point2D p1 = rightLine.getP1();
				Point2D p2 = rightLine.getP2();
				// Move line
				p1.setLocation(rightLine.getX1() + dx, rightLine.getY1());
				p2.setLocation(rightLine.getX2() + dx, rightLine.getY2());
				// Try to move objects
				List<RtlObjectNode> nodesToMove3 = (dx < 0) ? diagramView.getDiagram().getCorObjectNodes()
						: diagramView.getDiagram().getTrgObjectNodes();
				if (!tryMoveLeftRightObjects(nodesToMove3, dx)) {
					p1.setLocation(rightLine.getX1() - dx, rightLine.getY1());
					p2.setLocation(rightLine.getX2() - dx, rightLine.getY2());
				}
				rightLine.setLine(p1, p2);
			}
			break;
			case RIGHTMOST:
				if (dx < 0) {
					Point2D p1 = rightmostLine.getP1();
					Point2D p2 = rightmostLine.getP2();
					// Move line
					p1.setLocation(rightmostLine.getX1() + dx, rightmostLine.getY1());
					p2.setLocation(rightmostLine.getX2() + dx, rightmostLine.getY2());
					// Try to move objects
					List<RtlObjectNode> nodesToMove = diagramView.getDiagram().getTrgObjectNodes();
					if (!tryMoveLeftRightObjects(nodesToMove, dx)) {
						p1.setLocation(rightmostLine.getX1() - dx, rightmostLine.getY1());
						p2.setLocation(rightmostLine.getX2() - dx, rightmostLine.getY2());
					}
					rightmostLine.setLine(p1, p2);
				} else {
					rightmostLine.setLine(rightmostLine.getX1() + dx, rightmostLine.getY1(),
							rightmostLine.getX2() + dx, rightmostLine.getY2());
				}
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Gets which side a point belongs to. Used when checking an object node's
	 * position.
	 * 
	 * @param x
	 *            The point's x coordinate
	 * @return A side (source, target, correlation, other)
	 */
	public Side getSide(double x) {
		if (x < leftmostLine.getX1())
			return Side.OTHER;
		if (x < leftLine.getX1())
			return Side.SOURCE;
		if (x < rightLine.getX1())
			return Side.CORRELATION;
		if (x < rightmostLine.getX1())
			return Side.TARGET;
		return Side.OTHER;
	}

	public boolean tryMoveLeftRightObjects(List<RtlObjectNode> nodes, int dx) {
		boolean res = true;
		List<RtlObjectNode> movedObjects = new ArrayList<>();
		for (RtlObjectNode node : nodes) {
			int gap = dx > 0 ? 10 : -10;
			if (!node.checkNewPositition(node.getX() - gap, node.getX() + node.getWidth() - gap)) {
				if (node.checkNewPositition(node.getX() + dx + gap, node.getX() + node.getWidth() + gap)) {
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
			for (RtlObjectNode node : movedObjects) {
				node.setPosition(node.getX() - dx, node.getY());
			}
		}
		return res;
	}

	public void updateSwimlane(int x1, int x2, int x3, int x4) {
		leftmostLine.setLine(x1, 0, x1, Y2);
		leftLine.setLine(x2, 0, x2, Y2);
		rightLine.setLine(x3, 0, x3, Y2);
		rightmostLine.setLine(x4, 0, x4, Y2);
	}

	// Save line position to restore
	public void storePlacementInfo(PersistHelper helper, Element parent) {
		Element swimlaneElement = helper.createChild(parent, "rtlswimlane");
		helper.appendChild(swimlaneElement, "X1", Integer.toString((int) leftmostLine.getX1()));
		helper.appendChild(swimlaneElement, "X2", Integer.toString((int) leftLine.getX1()));
		helper.appendChild(swimlaneElement, "X3", Integer.toString((int) rightLine.getX1()));
		helper.appendChild(swimlaneElement, "X4", Integer.toString((int) rightmostLine.getX1()));
	}

	/**
	 * Generates a random position inside the correct lane
	 * 
	 * @param pos
	 *            The position to modify
	 * @param w
	 *            Diagram width
	 * @param h
	 *            Diagram side
	 * @param side
	 *            Which lane the object belongs to
	 */
	public void getRandomNextPosition(Point2D.Double pos, int w, int h, Side side) {
		switch (side) {
		case SOURCE:
			pos.x = leftmostLine.getX1() + Math.random() * (leftLine.getX1() - leftmostLine.getX1() - 100);
			break;
		case CORRELATION:
			pos.x = leftLine.getX1() + Math.random() * (rightLine.getX1() - leftLine.getX1() - 100);
			break;
		case TARGET:
			pos.x = rightLine.getX1() + Math.random() * (rightmostLine.getX1() - rightLine.getX1() - 100);
			break;
		default:
			pos.x = Math.random() * Math.max(100, w - 100);
		}
		pos.y = Math.random() * Math.max(100, h - 100);
	}
}