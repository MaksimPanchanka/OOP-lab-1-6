package me.maksim.plugin.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Global registry managing available and currently activated stream-processing plugins.
 */
public class FileProcessorRegistry {
    private static final List<FileProcessorPlugin> availablePlugins = new ArrayList<>();
    private static final Map<FileProcessorPlugin, Boolean> activationStates = new HashMap<>();

    public static void registerPlugin(FileProcessorPlugin plugin) {
        availablePlugins.add(plugin);
        activationStates.put(plugin, false); // По умолчанию плагин выключен, пока не поставят галочку
        System.out.println("File processor registered: " + plugin.getPluginName());
    }

    public static List<FileProcessorPlugin> getAvailablePlugins() {
        return availablePlugins;
    }

    public static void setPluginActive(FileProcessorPlugin plugin, boolean active) {
        activationStates.put(plugin, active);
    }

    public static boolean isPluginActive(FileProcessorPlugin plugin) {
        return activationStates.getOrDefault(plugin, false);
    }
}