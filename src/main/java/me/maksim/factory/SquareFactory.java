package me.maksim.factory;

import me.maksim.models.base.Shape;
import me.maksim.models.impl.Square;

public class SquareFactory implements ShapeFactory {

    @Override
    public Shape create(double x1, double y1, double x2, double y2) {
        double deltaX = Math.abs(x2 - x1);
        double deltaY = Math.abs(y2 - y1);

        double side = Math.max(deltaX, deltaY);

        double minX = (x2 >= x1) ? x1 : x1 - side;
        double minY = (y2 >= y1) ? y1 : y1 - side;

        return new Square(minX, minY, side);
    }

    @Override
    public String getShapeName() {
        return "Квадрат";
    }
}