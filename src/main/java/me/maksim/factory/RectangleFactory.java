package me.maksim.factory;

import java.io.IOException;
import java.io.ObjectInputStream;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.Rectangle;

public class RectangleFactory implements ShapeFactory{

    @Override
    public Shape create(double x1, double y1, double x2, double y2){

        double minX = Math.min(x1, x2);
        double minY = Math.min(y1, y2);

        double width = Math.abs(x2-x1);
        double height = Math.abs(y2 - y1);
        return new Rectangle(minX, minY, width, height);
    }
    @Override
    public Shape readBinary(ObjectInputStream in) throws IOException {
        // Read coordinates and dimension properties
        double x = in.readDouble();
        double y = in.readDouble();
        double width = in.readDouble();
        double height = in.readDouble();
        
        // Reconstruct and return the Rectangle object
        return new Rectangle(x, y, width, height);
    }

    @Override
    public String getShapeName(){
        return "Прямоугольник";
    }
}
