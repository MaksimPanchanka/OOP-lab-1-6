package me.maksim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.maksim.factory.ShapeFactory;
import me.maksim.factory.ShapeFactoryRegistry;
import me.maksim.models.base.Shape;

public class BinarySerializationService {
    /**
     * Saves a list of shapes into a binary file.
     *
     * @param file   The target file to save data into.
     * @param shapes The list of shapes to serialize.
     * @throws IOException If any writing operation fails.
     */
    public static void saveShapes(File file, List<Shape> shapes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            // 1. Write total number of shapes to know how many loops to execute during reading
            oos.writeInt(shapes.size());

            // 2. Delegate the binary write operational logic to each individual shape instance
            for (Shape shape : shapes) {
                shape.writeBinary(oos);
            }
        }
    }

    /**
     * Loads and reconstructs a list of shapes from a binary file.
     *
     * @param file The source binary file.
     * @return A list of fully initialized Shape objects.
     * @throws IOException            If any reading operation fails.
     * @throws ClassNotFoundException If matching data types cannot be aligned.
     */
    public static List<Shape> loadShapes(File file) throws IOException, ClassNotFoundException {
        List<Shape> loadedShapes = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // 1. Read the initial total count of objects packed in the file
            int count = ois.readInt();

            // 2. Loop through and rebuild each shape sequentially
            for (int i = 0; i < count; i++) {
                // Read the unique text identifier first (e.g., "Квадрат", "Линия")
                String shapeType = ois.readUTF();

                // Dynamic lookup: fetch the appropriate factory from the registry without if-else
                ShapeFactory factory = ShapeFactoryRegistry.getFactory(shapeType);

                if (factory != null) {
                    // Let the concrete factory parse the remaining double values and return the object
                    Shape shape = factory.readBinary(ois);
                    loadedShapes.add(shape);
                } else {
                    throw new IOException("Unknown shape identifier type encountered in binary file: " + shapeType);
                }
            }
        }

        return loadedShapes;
    }
}
