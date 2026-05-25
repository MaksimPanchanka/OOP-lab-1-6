package me.maksim.storage;

import java.util.ArrayList;
import java.util.List;

import me.maksim.models.base.Shape;

public class ShapeList {
    private final List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public List<Shape> getShapes() {
        return new ArrayList<>(shapes);
    }
}
