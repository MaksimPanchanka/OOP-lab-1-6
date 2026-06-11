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
public class Square extends Rectangle{

    public Square(double x, double y, double side){
        super(x, y, side, side);
    }
    @Override
    public void writeBinary(ObjectOutputStream out) throws IOException {
        // 1. Write the unique identifier of the shape class first
        out.writeUTF("Квадрат");
        
        // 2. Write basic coordinates from the parent class (Shape)
        out.writeDouble(getX());
        out.writeDouble(getY());
        
        // 3. Write specific property of the Square (which is width or height from Rectangle)
        out.writeDouble(getWidth());
    }
}
