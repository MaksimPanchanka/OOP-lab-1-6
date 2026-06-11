package me.maksim.ui;

import java.io.File;
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
import me.maksim.service.BinarySerializationService;

/**
 * Main window of the graphical editor application.
 * Handles user actions, custom rendering canvas container, and file I/O operations.
 */
public class EditorFrame extends JFrame {
    private String currentSelectedShape = "";
    
    // Global list containing all drawn shapes
    private final List<Shape> shapesList = new ArrayList<>();
    
    // Custom drawing area panel component reference
    private SwingRender canvas;
    
    // Initial mouse coordinates for drawing operations
    private int startX, startY;

    /**
     * Constructs the main editor window and initializes its components.
     */
    public EditorFrame() {
        // Initialize basic JFrame properties
        super("Лабораторная работа 3: Сериализация графических объектов");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600); // Expanded width slightly to fit new control layout smoothly
        this.setLayout(new BorderLayout());

        // Set default selected shape if any factories are registered
        if (!ShapeFactoryRegistry.getAvailableShapeNames().isEmpty()) {
            currentSelectedShape = ShapeFactoryRegistry.getAvailableShapeNames().get(0);
        }

        // Initialize user interface components (Note: Canvas init moved up so Toolbar can access it)
        initCanvas();
        initToolbar();
    }

    /**
     * Builds the toolbar split into shape selection and file control modules.
     */
    private void initToolbar() {
        JPanel mainToolbar = new JPanel(new BorderLayout());
        mainToolbar.setBackground(Color.LIGHT_GRAY);

        // Left section: Dynamic buttons generated from the Factory Registry
        JPanel shapeSelectionPanel = new JPanel();
        shapeSelectionPanel.setOpaque(false);
        for (String shapeName : ShapeFactoryRegistry.getAvailableShapeNames()) {
            JButton button = new JButton(shapeName);
            button.addActionListener(e -> {
                currentSelectedShape = shapeName;
                System.out.println("Selected shape changed to: " + currentSelectedShape);
            });
            shapeSelectionPanel.add(button);
        }

        // Right section: Static operational utilities (Save, Load, Clear operations)
        JPanel ioControlPanel = new JPanel();
        ioControlPanel.setOpaque(false);

        JButton saveButton = new JButton("Save Binary");
        saveButton.addActionListener(e -> executeSaveOperation());

        JButton loadButton = new JButton("Load Binary");
        loadButton.addActionListener(e -> executeLoadOperation());

        JButton clearButton = new JButton("Clear Canvas");
        clearButton.addActionListener(e -> {
            shapesList.clear();
            canvas.repaint();
            System.out.println("Canvas layout cleared successfully.");
        });

        ioControlPanel.add(saveButton);
        ioControlPanel.add(loadButton);
        ioControlPanel.add(clearButton);

        // Construct total layout frame architecture
        mainToolbar.add(shapeSelectionPanel, BorderLayout.WEST);
        mainToolbar.add(ioControlPanel, BorderLayout.EAST);
        
        this.add(mainToolbar, BorderLayout.NORTH);
    }

    /**
     * Initializes the custom drawing area and registers mouse listeners.
     */
    private void initCanvas() {
        canvas = new SwingRender(shapesList);
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

    /**
     * Handles the file choosing interface flow and executes binary file exporting.
     */
    private void executeSaveOperation() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Shape Repository");
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                BinarySerializationService.saveShapes(fileToSave, shapesList);
                JOptionPane.showMessageDialog(this, "File exported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Export Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Handles the file choosing interface flow and updates data models from binary inputs.
     */
    private void executeLoadOperation() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Shape Repository");
        
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            try {
                List<Shape> loaded = BinarySerializationService.loadShapes(fileToLoad);
                
                // Clear state database and copy over new parsed reference objects
                shapesList.clear();
                shapesList.addAll(loaded);
                
                // Repaint system canvas node
                canvas.repaint();
                JOptionPane.showMessageDialog(this, "File imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Import Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}