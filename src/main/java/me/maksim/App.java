package me.maksim;

import javax.swing.SwingUtilities;

import me.maksim.ui.EditorFrame;
import me.maksim.factory.CircleFactory;
import me.maksim.factory.EllipseFactory;
import me.maksim.factory.LineFactory;
import me.maksim.factory.RectangleFactory;
import me.maksim.factory.ShapeFactoryRegistry;
import me.maksim.factory.SquareFactory;
import me.maksim.factory.TriangleFactory;


public class App {
    public static void main(String[] args) {
       registerFactories();

        SwingUtilities.invokeLater(() -> {
            EditorFrame frame = new EditorFrame();
            frame.setVisible(true);
        });
    }

    /**
     * Registers all concrete shape factories into the central registry.
     */
    private static void registerFactories() {
        ShapeFactoryRegistry.registerFactory(new CircleFactory());
        ShapeFactoryRegistry.registerFactory(new EllipseFactory());
        ShapeFactoryRegistry.registerFactory(new LineFactory());
        ShapeFactoryRegistry.registerFactory(new RectangleFactory());
        ShapeFactoryRegistry.registerFactory(new SquareFactory());
        ShapeFactoryRegistry.registerFactory(new TriangleFactory());
    }
}
