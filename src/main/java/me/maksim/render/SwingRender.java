package me.maksim.render;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class SwingRender extends JPanel {
    private final List<Shape> shapes;

    private static final Map<Class<? extends Shape>, BiConsumer<Graphics2D, Shape>> customRenderers = new HashMap<>();

    /**
     * Registration and rendering
     */
    public static <T extends Shape> void registerCustomRenderer(Class<T> shapeClass, BiConsumer<Graphics2D, T> renderer) {
        customRenderers.put(shapeClass, (g2d, shape) -> renderer.accept(g2d, shapeClass.cast(shape)));
        System.out.println("Registered custom visual renderer for node: " + shapeClass.getSimpleName());
    }

    public SwingRender(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Shape shape : shapes) {
            drawShape(g2d, shape);
        }
    }

    private void drawShape(Graphics2D g2d, Shape shape) {
        if (customRenderers.containsKey(shape.getClass())) {
            customRenderers.get(shape.getClass()).accept(g2d, shape);
            return;
        }

        if (shape instanceof Line l) {
            g2d.drawLine((int)l.getX(), (int)l.getY(), (int)l.getX2(), (int)l.getY2());
        } else if (shape instanceof me.maksim.models.impl.Rectangle r) {
            g2d.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
        } else if (shape instanceof Circle c) {
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