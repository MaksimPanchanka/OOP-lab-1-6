package me.maksim.models.impl;

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
}
