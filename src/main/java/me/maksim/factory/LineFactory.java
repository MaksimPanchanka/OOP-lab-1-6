package me.maksim.factory;

import java.io.IOException;
import java.io.ObjectInputStream;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.Line;

public class LineFactory implements ShapeFactory {
    
    @Override
    public Shape create(double x1, double y1, double x2, double y2){
        return new Line(x1, y1, x2, y2);
    }
    @Override
    public Shape readBinary(ObjectInputStream in) throws IOException {
        // Read both the starting and ending points of the line segments
        double x = in.readDouble();
        double y = in.readDouble();
        double x2 = in.readDouble();
        double y2 = in.readDouble();
        
        // Reconstruct and return the Line object
        return new Line(x, y, x2, y2);
    }

    @Override
    public String getShapeName(){
        return "Линия";
    }
}
