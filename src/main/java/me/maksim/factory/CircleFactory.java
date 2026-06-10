package me.maksim.factory;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.Circle;

public class CircleFactory implements ShapeFactory{

    @Override
    public Shape create(double x1, double y1, double x2, double y2) {
        double radius = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return new Circle(x1, y1, radius);
    }
    
    @Override
    public String getShapeName() { return "Круг"; }
}
