package me.maksim.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import me.maksim.factory.ShapeFactory;
import me.maksim.factory.ShapeFactoryRegistry;
import me.maksim.models.base.Shape;
import me.maksim.plugin.api.FileProcessorPlugin;
import me.maksim.plugin.api.FileProcessorRegistry;

public class BinarySerializationService {
    
    /**
     * Saves a list of shapes into a binary file, proxying data through activated stream filters.
     *
     * @param file   The target file to save data into.
     * @param shapes The list of shapes to serialize.
     * @throws IOException If any writing operation fails.
     */
    public static void saveShapes(File file, List<Shape> shapes) throws IOException {
        OutputStream out = new FileOutputStream(file);


        for (FileProcessorPlugin plugin : FileProcessorRegistry.getAvailablePlugins()) {
            if (FileProcessorRegistry.isPluginActive(plugin)) {
                out = plugin.wrapOutputStream(out);
                System.out.println("Data stream proxied through filter: " + plugin.getPluginName());
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
            // Твоя оригинальная логика записи
            oos.writeInt(shapes.size());

            for (Shape shape : shapes) {
                shape.writeBinary(oos);
            }
        }
    }

    /**
     * Loads and reconstructs a list of shapes from a binary file using reverse stream decoding.
     *
     * @param file The source binary file.
     * @return A list of fully initialized Shape objects.
     * @throws IOException            If any reading operation fails.
     * @throws ClassNotFoundException If matching data types cannot be aligned.
     */
    public static List<Shape> loadShapes(File file) throws IOException, ClassNotFoundException {
        List<Shape> loadedShapes = new ArrayList<>();

        InputStream in = new FileInputStream(file);

        List<FileProcessorPlugin> plugins = FileProcessorRegistry.getAvailablePlugins();
        for (int i = plugins.size() - 1; i >= 0; i--) {
            FileProcessorPlugin plugin = plugins.get(i);
            if (FileProcessorRegistry.isPluginActive(plugin)) {
                in = plugin.wrapInputStream(in);
                System.out.println("Data stream decoded through filter: " + plugin.getPluginName());
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            int count = ois.readInt();

            for (int i = 0; i < count; i++) {
                String shapeType = ois.readUTF();
                ShapeFactory factory = ShapeFactoryRegistry.getFactory(shapeType);

                if (factory != null) {
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