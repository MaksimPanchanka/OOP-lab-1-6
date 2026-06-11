package me.maksim.factory;

import java.io.IOException;
import java.io.ObjectInputStream;

import me.maksim.models.base.Shape;

public interface ShapeFactory {
    
    Shape create(double x1, double y1, double x2, double y2);

    /**
     * Deserializes and reconstructs a shape from a binary stream.
     *
     * @param in The source object input stream
     * @return A fully initialized Shape object
     * @throws IOException If an I/O error occurs
     */
    Shape readBinary(ObjectInputStream in) throws IOException;
    String getShapeName();
}
