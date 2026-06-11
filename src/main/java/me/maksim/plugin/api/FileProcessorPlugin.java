package me.maksim.plugin.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Service Provider Interface (SPI) for dynamic file processing modules.
 * Allows plugins to intercept and wrap data streams during save/load operations.
 */
public interface FileProcessorPlugin {
    
    /**
     * @return The display name of the plugin inside the UI settings menu.
     */
    String getPluginName();

    /**
     * Wraps the standard output stream to inject custom processing (e.g., encryption, compression).
     *
     * @param out The original destination OutputStream
     * @return The processed/wrapped OutputStream
     * @throws IOException If stream initialization fails
     */
    OutputStream wrapOutputStream(OutputStream out) throws IOException;

    /**
     * Wraps the standard input stream to decode or reverse custom processing during data recovery.
     *
     * @param in The original source InputStream
     * @return The processed/wrapped InputStream
     * @throws IOException If stream initialization fails
     */
    InputStream wrapInputStream(InputStream in) throws IOException;
}