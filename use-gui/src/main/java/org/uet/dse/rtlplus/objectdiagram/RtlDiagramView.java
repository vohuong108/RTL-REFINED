package org.uet.dse.rtlplus.objectdiagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.PrintWriter;

import org.tzi.use.gui.views.diagrams.DiagramOptions;
import org.tzi.use.gui.views.diagrams.DiagramViewWithObjectNode;

@SuppressWarnings("serial")
public abstract class RtlDiagramView extends DiagramViewWithObjectNode {

	protected RtlSwimlane swimlane;

	public RtlDiagramView(DiagramOptions opt, PrintWriter log) {
		super(opt, log);
	}

	public RtlSwimlane getSwimlane() {
		return swimlane;
	}

	@Override
	public void drawDiagram(Graphics g) {
		super.drawDiagram(g);
		synchronized (fGraph) {
			Graphics2D g2d = (Graphics2D) g;
			Stroke oldStroke = g2d.getStroke();
			Color oldColor = g2d.getColor();
			g2d.setColor(new Color(192, 192, 192));
			g2d.setStroke(new BasicStroke(5.0f));
			g2d.draw(swimlane.getLeftmostLine());
			g2d.draw(swimlane.getLeftLine());
			g2d.draw(swimlane.getRightLine());
			g2d.draw(swimlane.getRightmostLine());
			g2d.setStroke(oldStroke);
			g2d.setColor(oldColor);
		}
	}

}
