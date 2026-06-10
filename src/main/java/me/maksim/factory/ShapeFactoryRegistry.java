package me.maksim.factory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShapeFactoryRegistry {
    // LinkedHashMap keeps the order of shapes exactly as they are registered
    private static final Map<String, ShapeFactory> registry = new LinkedHashMap<>();

    /**
     * Registers a new factory in the system.
     * @param factory The factory to register.
     */
    public static void registerFactory(ShapeFactory factory) {
        registry.put(factory.getShapeName(), factory);
    }

    /**
     * Retrieves a factory by its UI display name.
     * @param shapeName The name of the shape.
     * @return The corresponding ShapeFactory, or null if not found.
     */
    public static ShapeFactory getFactory(String shapeName) {
        return registry.get(shapeName);
    }
    public static List<String> getAvailableShapeNames() {
        return new ArrayList<>(registry.keySet());
    }
}
