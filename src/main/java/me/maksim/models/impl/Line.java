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
public class Line extends Shape{
    private double x2;
    private double y2;

    public Line(double x, double y, double x2, double y2){
        super(x,y);
        this.x2 = x2;
        this.y2 = y2;
    }
}
