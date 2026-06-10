package me.maksim.factory;

import me.maksim.models.base.Shape;

public interface ShapeFactory {
    
    Shape create(double x1, double y1, double x2, double y2);

    String getShapeName();
}
