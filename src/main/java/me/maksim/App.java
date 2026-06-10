package me.maksim;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.maksim.render.SwingRender;
import me.maksim.factory.CircleFactory;
import me.maksim.factory.EllipseFactory;
import me.maksim.factory.LineFactory;
import me.maksim.factory.RectangleFactory;
import me.maksim.factory.ShapeFactory;
import me.maksim.factory.ShapeFactoryRegistry;
import me.maksim.factory.SquareFactory;
import me.maksim.factory.TriangleFactory;
import me.maksim.models.base.Shape;


public class App {
    private static String currentSelectedShape = "";
    private static final List<Shape> shapesList = new ArrayList<>();
    private static int startX, startY;
    public static void main(String[] args) {
        ShapeFactoryRegistry.registerFactory(new CircleFactory());
        ShapeFactoryRegistry.registerFactory(new EllipseFactory());
        ShapeFactoryRegistry.registerFactory(new LineFactory());
        ShapeFactoryRegistry.registerFactory(new RectangleFactory());
        ShapeFactoryRegistry.registerFactory(new SquareFactory());
        ShapeFactoryRegistry.registerFactory(new TriangleFactory());



        // Set default selected shape if any factories are registered
        if (!ShapeFactoryRegistry.getAvailableShapeNames().isEmpty()) {
            currentSelectedShape = ShapeFactoryRegistry.getAvailableShapeNames().get(0);
        }

        // 2. Setting up the main window (JFrame)
        JFrame frame = new JFrame("Лабораторная работа 2: Графический редактор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // 3. Create the toolbar with dynamic buttons based on registered factories
        JPanel toolbar = new JPanel();
        toolbar.setBackground(Color.LIGHT_GRAY);

        for (String shapeName : ShapeFactoryRegistry.getAvailableShapeNames()) {
            JButton button = new JButton(shapeName);
            button.addActionListener(e -> {
                currentSelectedShape = shapeName;
                System.out.println("Selected shape changed to: " + currentSelectedShape);
            });
            toolbar.add(button);
        }
        frame.add(toolbar, BorderLayout.NORTH);

        // 4. Instantiate your custom SwingRender component, passing the shared shape list
        SwingRender canvas = new SwingRender(shapesList);
        canvas.setBackground(Color.WHITE);

        // 5. Add mouse listeners to capture user input for drawing shapes
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Save the starting point of the drag operation
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int endX = e.getX();
                int endY = e.getY();

                // Get the appropriate factory from the registry
                ShapeFactory factory = ShapeFactoryRegistry.getFactory(currentSelectedShape);
                
                if (factory != null) {
                    Shape newShape = factory.create(startX, startY, endX, endY);
                    shapesList.add(newShape);
                    // Request a redraw of the canvas area
                    canvas.repaint();
                }
            }
        });

        frame.add(canvas, BorderLayout.CENTER);

        // Make the window visible
        frame.setVisible(true);
    }
}
