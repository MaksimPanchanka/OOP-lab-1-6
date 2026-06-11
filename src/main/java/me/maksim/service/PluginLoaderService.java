package me.maksim.service;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import me.maksim.factory.ShapeFactory;
import me.maksim.factory.ShapeFactoryRegistry;

/**
 * Service responsible for dynamically scanning a directory and loading external 
 * shape plug-ins into the application runtime using URLClassLoader.
 */
public class PluginLoaderService {

    private static final String PLUGINS_DIR = "plugins";

    /**
     * Scans the plugins folder, loads jar files, discovers ShapeFactory 
     * implementations, and registers them dynamically.
     */
    public static void loadPlugins() {
        File dir = new File(PLUGINS_DIR);
        
        // Create the directory if it doesn't exist yet
        if (!dir.exists()) {
            dir.mkdir();
            System.out.println("Plugins directory created. Drop plugin JARs there.");
            return;
        }

        // Filter to look only for .jar files
        File[] jarFiles = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".jar"));

        if (jarFiles == null || jarFiles.length == 0) {
            System.out.println("No plugins found in the plugins/ folder.");
            return;
        }

        List<URL> urls = new ArrayList<>();
        try {
            for (File jar : jarFiles) {
                urls.add(jar.toURI().toURL());
                System.out.println("Found plugin configuration target: " + jar.getName());
            }

            // Create a specialized ClassLoader pointing to our external JAR files
            URLClassLoader classLoader = new URLClassLoader(
                urls.toArray(new URL[0]), 
                PluginLoaderService.class.getClassLoader()
            );

            // ServiceLoader searches for implementations specified in META-INF/services
            ServiceLoader<ShapeFactory> serviceLoader = ServiceLoader.load(ShapeFactory.class, classLoader);

            for (ShapeFactory factory : serviceLoader) {
                // Dynamically register into our existing Lab 2/3 registry!
                ShapeFactoryRegistry.registerFactory(factory);
                System.out.println("Plugin module successfully activated: " + factory.getShapeName());
            }

        } catch (Exception e) {
            System.err.println("Failed to perform dynamic plugin extraction sequence.");
            e.printStackTrace();
        }
    }
}