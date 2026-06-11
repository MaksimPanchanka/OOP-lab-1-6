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
public class Ellipse extends Shape {
    private double radius1;
    private double radius2;

    public Ellipse(double x, double y, double radius1, double radius2){
        super(x, y);
        this.radius1 = radius1;
        this.radius2 = radius2;
    }
    @Override
    public void writeBinary(ObjectOutputStream out) throws IOException {
        // 1. Write the unique identifier of the shape class first
        out.writeUTF("Эллипс");
        
        // 2. Write basic coordinates from the parent class (Shape)
        out.writeDouble(getX());
        out.writeDouble(getY());
        
        // 3. Write specific properties of the Ellipse
        out.writeDouble(this.radius1);
        out.writeDouble(this.radius2);
    }
}
