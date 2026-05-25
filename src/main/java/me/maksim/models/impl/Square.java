package me.maksim.models.impl;

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
}
