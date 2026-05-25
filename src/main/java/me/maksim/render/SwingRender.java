package me.maksim.render;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import me.maksim.models.impl.Rectangle;

public class SwingRender extends JPanel{
    private final List<Shape> shapes;

    public SwingRender(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enabling anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Shape shape : shapes) {
            drawShape(g2d, shape);
        }
    }

    private void drawShape(Graphics2D g2d, Shape shape) {
        if (shape instanceof Line l) {
            g2d.drawLine((int)l.getX(), (int)l.getY(), (int)l.getX2(), (int)l.getY2());
        } else if (shape instanceof Rectangle r) {
            g2d.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
        } else if (shape instanceof Circle c) {
            // Swing is drawn through a covering rectangle
            int d = (int)(c.getRadius1() * 2);
            g2d.drawOval((int)(c.getX() - c.getRadius1()), (int)(c.getY() - c.getRadius1()), d, d);
        } else if (shape instanceof Square s) {
            g2d.drawRect((int)s.getX(), (int)s.getY(), (int)s.getWidth(), (int)s.getWidth());
        } else if (shape instanceof Ellipse e) {
            g2d.drawOval((int)(e.getX() - e.getRadius1()), (int)(e.getY() - e.getRadius2()), 
                         (int)e.getRadius1() * 2, (int)e.getRadius2() * 2);
        } else if (shape instanceof Triangle t) {
            int[] xs = {(int)t.getX(), (int)t.getX2(), (int)t.getX3()};
            int[] ys = {(int)t.getY(), (int)t.getY2(), (int)t.getY3()};
            g2d.drawPolygon(xs, ys, 3);
        }
    }
}
