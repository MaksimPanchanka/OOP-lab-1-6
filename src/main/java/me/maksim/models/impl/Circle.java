package me.maksim.models.impl;

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
}
