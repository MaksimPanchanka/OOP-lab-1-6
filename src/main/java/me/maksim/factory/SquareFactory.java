package me.maksim.factory;

import java.io.IOException;
import java.io.ObjectInputStream;

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
    public Shape readBinary(ObjectInputStream in) throws IOException {
        // Read the properties in the exact same order they were written in Square.writeBinary
        double x = in.readDouble();
        double y = in.readDouble();
        double side = in.readDouble();

        // Reconstruct and return the fully initialized Square object
        return new Square(x, y, side);
    }
    @Override
    public String getShapeName() {
        return "Квадрат";
    }
}