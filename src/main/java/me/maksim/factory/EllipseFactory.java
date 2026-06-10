package me.maksim.factory;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.Ellipse;

public class EllipseFactory implements ShapeFactory{
    
    @Override
    public Shape create(double x1, double y1, double x2, double y2) {
        //Calculate the center
        double centerX = (x1 + x2) / 2.0;
        double centerY = (y1 + y2) / 2.0;
        
        //Calculate the radius
        double radiusX = Math.abs(x2 - x1) / 2.0;
        double radiusY = Math.abs(y2 - y1) / 2.0;
        
        return new Ellipse(centerX, centerY, radiusX, radiusY);
    }

    @Override
    public String getShapeName() {
        return "Эллипс";
    }
}
