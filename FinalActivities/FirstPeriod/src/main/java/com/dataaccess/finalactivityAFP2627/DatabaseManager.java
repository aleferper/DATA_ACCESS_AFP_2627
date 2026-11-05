package com.dataaccess.finalactivityAFP2627;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Reads the connection data from db.properties and creates connections.
 */
public class DatabaseManager {

    private static String url;
    private static String user;
    private static String password;

    public static boolean loadProperties() {
        try (InputStream input = DatabaseManager.class.getResourceAsStream("/db.properties")) {
            if (input == null) {
                AppLogger.error("db.properties file not found.");
                return false;
            }
            Properties props = new Properties();
            props.load(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            return url != null && user != null && password != null;
        } catch (Exception e) {
            AppLogger.error("Error reading db.properties: " + e.getMessage());
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Checks that the database service is available.
     */
    public static boolean isAvailable() {
        try {
            Connection con = getConnection();
            con.close();
            return true;
        } catch (SQLException e) {
            AppLogger.error("Database not available: " + e.getMessage());
            return false;
        }
    }
}
