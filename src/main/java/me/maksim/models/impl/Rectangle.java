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
public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height){
        super(x, y);
        this.height = height;
        this.width = width;
    }
}
