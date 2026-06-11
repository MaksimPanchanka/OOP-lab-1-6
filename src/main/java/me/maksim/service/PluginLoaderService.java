package me.maksim.service;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import me.maksim.factory.ShapeFactory;
import me.maksim.factory.ShapeFactoryRegistry;
import me.maksim.plugin.api.FileProcessorPlugin;
import me.maksim.plugin.api.FileProcessorRegistry;

/**
 * Service responsible for dynamically scanning a directory and loading external 
 * shape plug-ins and file processors into the application runtime using URLClassLoader.
 */
public class PluginLoaderService {

    private static final String PLUGINS_DIR = "plugins";

    /**
     * Scans the plugins folder, loads jar files, discovers implementations
     * for both ShapeFactory and FileProcessorPlugin, and registers them dynamically.
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

            // ==========================================
            // load factory
            // ==========================================
            ServiceLoader<ShapeFactory> shapeLoader = ServiceLoader.load(ShapeFactory.class, classLoader);
            for (ShapeFactory factory : shapeLoader) {
                ShapeFactoryRegistry.registerFactory(factory);
                System.out.println("Plugin module successfully activated: " + factory.getShapeName());
            }

            // ==========================================
            // load plugins
            // ==========================================
            ServiceLoader<FileProcessorPlugin> fileProcessorLoader = ServiceLoader.load(FileProcessorPlugin.class, classLoader);
            for (FileProcessorPlugin plugin : fileProcessorLoader) {
                FileProcessorRegistry.registerPlugin(plugin);
                System.out.println("File processor filter successfully activated: " + plugin.getPluginName());
            }

        } catch (Exception e) {
            System.err.println("Failed to perform dynamic plugin extraction sequence.");
            e.printStackTrace();
        }
    }
}