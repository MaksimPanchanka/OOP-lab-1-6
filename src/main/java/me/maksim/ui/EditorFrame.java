package me.maksim.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.maksim.factory.ShapeFactory;
import me.maksim.factory.ShapeFactoryRegistry;
import me.maksim.models.base.Shape;
import me.maksim.render.SwingRender;

public class EditorFrame extends JFrame{
    private String currentSelectedShape = "";
    
    // Global list containing all drawn shapes
    private final List<Shape> shapesList = new ArrayList<>();
    
    // Initial mouse coordinates for drawing operations
    private int startX, startY;

    /**
     * Constructs the main editor window and initializes its components.
     */
    public EditorFrame() {
        // Initialize basic JFrame properties
        super("Лабораторная работа 2: Графический редактор");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        // Set default selected shape if any factories are registered
        if (!ShapeFactoryRegistry.getAvailableShapeNames().isEmpty()) {
            currentSelectedShape = ShapeFactoryRegistry.getAvailableShapeNames().get(0);
        }

        // Initialize user interface components
        initToolbar();
        initCanvas();
    }

    /**
     * Builds the toolbar with dynamic buttons based on registered factories.
     */
    private void initToolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setBackground(Color.LIGHT_GRAY);

        // Dynamically build buttons without hardcoding specific shape types
        for (String shapeName : ShapeFactoryRegistry.getAvailableShapeNames()) {
            JButton button = new JButton(shapeName);
            button.addActionListener(e -> {
                currentSelectedShape = shapeName;
                System.out.println("Selected shape changed to: " + currentSelectedShape);
            });
            toolbar.add(button);
        }
        this.add(toolbar, BorderLayout.NORTH);
    }

    /**
     * Initializes the custom drawing area and registers mouse listeners.
     */
    private void initCanvas() {
        SwingRender canvas = new SwingRender(shapesList);
        canvas.setBackground(Color.WHITE);

        // Add mouse listeners to capture user dragging gestures for drawing shapes
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Save the initial point where the mouse click occurred
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int endX = e.getX();
                int endY = e.getY();

                // Retrieve the matching factory from the registry using loose coupling
                ShapeFactory factory = ShapeFactoryRegistry.getFactory(currentSelectedShape);
                
                if (factory != null) {
                    // Create the specific shape object polymorphically
                    Shape newShape = factory.create(startX, startY, endX, endY);
                    shapesList.add(newShape);
                    
                    // Force the custom panel to refresh the screen
                    canvas.repaint();
                }
            }
        });

        this.add(canvas, BorderLayout.CENTER);
    }
    
}
