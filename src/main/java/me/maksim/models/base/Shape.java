package me.maksim.models.base;

import java.io.IOException;
import java.io.ObjectOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Shape {
    protected double x;
    protected double y;

    /**
     * Serializes the shape's specific properties into a binary stream.
     * Each subclass must override this to write its own fields.
     *
     * @param out The target object output stream
     * @throws IOException If an I/O error occurs during writing
     */
    public abstract void writeBinary(ObjectOutputStream out) throws IOException;
}
