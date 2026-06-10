package me.maksim.factory;

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
    public String getShapeName(){
        return "Прямоугольник";
    }
}
