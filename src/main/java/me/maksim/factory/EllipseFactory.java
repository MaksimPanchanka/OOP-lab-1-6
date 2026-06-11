package me.maksim.factory;

import java.io.IOException;
import java.io.ObjectInputStream;

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
    public Shape readBinary(ObjectInputStream in) throws IOException {
        // Read center/starting points and both horizontal/vertical radii
        double x = in.readDouble();
        double y = in.readDouble();
        double radius1 = in.readDouble();
        double radius2 = in.readDouble();
        
        // Reconstruct and return the Ellipse object
        return new Ellipse(x, y, radius1, radius2);
    }

    @Override
    public String getShapeName() {
        return "Эллипс";
    }
}
