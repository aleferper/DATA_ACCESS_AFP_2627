package com.dataaccess.finalactivityAFP2627;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Writes the remarkable events of the application in the file flights.log
 */
public class AppLogger {

    private static final String LOG_FILE = "flights.log";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        write("INFO", message);
    }

    public static void warning(String message) {
        write("WARNING", message);
    }

    public static void error(String message) {
        write("ERROR", message);
    }

    /**
     * Adds a line at the end of the log file.
     */
    private static void write(String level, String message) {
        String date = LocalDateTime.now().format(FORMAT);
        // true = append mode, so old lines are not deleted
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("[" + date + "] " + level + ": " + message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Warning: the log file could not be written.");
        }
    }
}
