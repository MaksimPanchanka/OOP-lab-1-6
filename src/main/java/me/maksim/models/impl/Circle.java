package me.maksim.models.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Circle extends Ellipse {

    public Circle(double x, double y, double radius){
        super(x, y, radius, radius);
    }

    @Override
    public void writeBinary(ObjectOutputStream out) throws IOException {
        // 1. Write the unique identifier
        out.writeUTF("Круг");
        
        // 2. Write coordinates from Shape
        out.writeDouble(getX());
        out.writeDouble(getY());
        
        // 3. Write radius (stored as radius1 in Ellipse)
        out.writeDouble(getRadius1());
    }
}
