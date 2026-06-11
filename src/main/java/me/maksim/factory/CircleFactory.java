package me.maksim.factory;

import java.io.IOException;
import java.io.ObjectInputStream;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.Circle;

public class CircleFactory implements ShapeFactory{

    @Override
    public Shape create(double x1, double y1, double x2, double y2) {
        double radius = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return new Circle(x1, y1, radius);
    }
    @Override
    public Shape readBinary(ObjectInputStream in) throws IOException {
        // Read position coordinates and the single uniform radius value
        double x = in.readDouble();
        double y = in.readDouble();
        double radius = in.readDouble();
        
        // Reconstruct and return the Circle object
        return new Circle(x, y, radius);
    }
    
    @Override
    public String getShapeName() { return "Круг"; }
}
