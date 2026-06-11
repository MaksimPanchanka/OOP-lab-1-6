package me.maksim.models.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.maksim.models.base.Shape;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Triangle extends Shape{
    private double x2, y2;
    private double x3, y3;

    public Triangle(double x, double y, double x2, double y2, double x3, double y3){
        super(x, y);
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public void writeBinary(ObjectOutputStream out) throws IOException {
        // 1. Write the unique identifier
        out.writeUTF("Треугольник");
        
        // 2. Write the first vertex (from Shape)
        out.writeDouble(getX());
        out.writeDouble(getY());
        
        // 3. Write the second and third vertices
        out.writeDouble(this.x2);
        out.writeDouble(this.y2);
        out.writeDouble(this.x3);
        out.writeDouble(this.y3);
    }
}
