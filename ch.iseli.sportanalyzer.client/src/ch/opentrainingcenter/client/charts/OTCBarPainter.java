package ch.opentrainingcenter.client.charts;

import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;

import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.ui.RectangleEdge;

class OTCBarPainter extends StandardXYBarPainter {

    private static final long serialVersionUID = 1L;

    @Override
    public void paintBar(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base) {
        super.paintBar(g2, renderer, row, column, bar, base);
    }

    @Override
    public void paintBarShadow(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base, boolean pegShadow) {
        // nur nicht diese hässlichen shadows
    }

}