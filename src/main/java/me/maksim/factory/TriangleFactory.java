package me.maksim.factory;

import java.io.IOException;
import java.io.ObjectInputStream;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.Triangle;

public class TriangleFactory implements ShapeFactory {

    @Override
    public Shape create(double x1, double y1, double x2, double y2) {
        //Define the boundaries (min/max are needed to draw in any direction)
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);

        //Vertex 1: in the middle of the top
        double tx1 = (minX + maxX) / 2.0;
        double ty1 = minY;
        
        //Vertex 2: left-bottom
        double tx2 = minX;
        double ty2 = maxY;
        
        //Vertex 3: Right-Low
        double tx3 = maxX;
        double ty3 = maxY;

        return new Triangle(tx1, ty1, tx2, ty2, tx3, ty3);
    }
    @Override
    public Shape readBinary(ObjectInputStream in) throws IOException {
        // Read all 6 coordinate variables mapping out the 3 separate vertices
        double x = in.readDouble();
        double y = in.readDouble();
        double x2 = in.readDouble();
        double y2 = in.readDouble();
        double x3 = in.readDouble();
        double y3 = in.readDouble();
        
        // Reconstruct and return the Triangle object
        return new Triangle(x, y, x2, y2, x3, y3);
    }

    @Override
    public String getShapeName() {
        return "Треугольник";
    }
}
