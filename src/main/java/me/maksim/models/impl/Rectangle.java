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
public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height){
        super(x, y);
        this.height = height;
        this.width = width;
    }
    @Override
    public void writeBinary(ObjectOutputStream out) throws IOException {
        // 1. Write the unique identifier
        out.writeUTF("Прямоугольник");
        
        // 2. Write coordinates
        out.writeDouble(getX());
        out.writeDouble(getY());
        
        // 3. Write dimensions
        out.writeDouble(this.width);
        out.writeDouble(this.height);
    }
}
