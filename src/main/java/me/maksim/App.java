package me.maksim;

import javax.swing.JFrame;

import me.maksim.models.impl.Circle;
import me.maksim.models.impl.Ellipse;
import me.maksim.models.impl.Line;
import me.maksim.models.impl.Rectangle;
import me.maksim.models.impl.Square;
import me.maksim.models.impl.Triangle;
import me.maksim.storage.ShapeList;
import me.maksim.render.SwingRender;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Java");

        ShapeList container = new ShapeList();

        container.addShape(new Line(20, 20, 60, 60));
        container.addShape(new Circle(40, 80, 20));
        container.addShape(new Rectangle(20, 20, 50, 30));
        container.addShape(new Ellipse(100, 100, 20, 10));
        container.addShape(new Square(200, 200, 45));
        container.addShape(new Triangle(90, 90, 125, 110, 89, 69));

        JFrame frame = new JFrame("Лабораторная работа: ООП Фигуры");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        SwingRender renderer = new SwingRender(container.getShapes());
        frame.add(renderer);

        frame.setVisible(true);
    }
}
