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
public class Line extends Shape{
    private double x2;
    private double y2;

    public Line(double x, double y, double x2, double y2){
        super(x,y);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void writeBinary(ObjectOutputStream out) throws IOException {
        // 1. Write the unique identifier
        out.writeUTF("Линия");
        
        // 2. Write start coordinates from parent class
        out.writeDouble(getX());
        out.writeDouble(getY());
        
        // 3. Write end coordinates
        out.writeDouble(this.x2);
        out.writeDouble(this.y2);
    }
}
