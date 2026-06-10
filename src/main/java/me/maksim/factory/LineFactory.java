package me.maksim.factory;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.Line;

public class LineFactory implements ShapeFactory {
    
    @Override
    public Shape create(double x1, double y1, double x2, double y2){
        return new Line(x1, y1, x2, y2);
    }

    @Override
    public String getShapeName(){
        return "Линия";
    }
}
