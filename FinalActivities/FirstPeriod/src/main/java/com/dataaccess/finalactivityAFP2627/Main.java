package com.dataaccess.finalactivityAFP2627;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * Flights - Data Access Final Activity (First Period)
 * Author: Alejandro Ferrandiz Perez (AFP)
 */
public class Main {

    public static void main(String[] args) {
        AppLogger.info("Application started.");

        // Check the database on startup
        if (!DatabaseManager.loadProperties()) {
            System.err.println("ERROR: the connection data could not be read from db.properties.");
            System.exit(1);
        }
        if (!DatabaseManager.isAvailable()) {
            System.err.println("ERROR: the database service is not available. "
                    + "Check that PostgreSQL is running and the data in db.properties.");
            System.exit(1);
        }
        AppLogger.info("Database connection OK.");

        if (args.length == 0) {
            invalidCommand();
            return;
        }

        switch (args[0]) {
            case "-h":
            case "--help":
                showHelp();
                break;

            case "-a":
            case "--add":
                if (args.length == 2) {
                    addPassengers(args[1]);
                } else {
                    invalidCommand();
                }
                break;

            default:
                invalidCommand();
                break;
        }

        AppLogger.info("Application finished.");
    }

    // ---------------------------------------------------------------- HELP

    private static void showHelp() {
        System.out.println("Usage: flights [options]");
        System.out.println("Options:");
        System.out.println("    -h, --help: show this help");
        System.out.println("    -a, --add <filename.xml>: add the passengers in the XML file to the database.");
        System.out.println("    -f, --flights <origin> <destination> <date>: show the flights available from origin to destination");
        System.out.println("    -b, --buy <customerId> <flightId> <date>: buy a ticket for a flight in a specific date");
        System.out.println("    -o, --origins : show the origins of the flights");
        System.out.println("    -d, --destinations <origin>: show the destinations of the flights from origin");
    }

    private static void invalidCommand() {
        System.out.println("Command not recognized or malformed.");
        AppLogger.warning("Command not recognized or malformed.");
        showHelp();
    }

    // ---------------------------------------------------------------- ADD

    private static void addPassengers(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("ERROR: the file " + fileName + " does not exist.");
            AppLogger.error("Add: file not found " + fileName);
            return;
        }

        // 1. Read the XML file
        List<Passenger> passengers;
        try {
            passengers = XmlPassengerReader.read(file);
        } catch (Exception e) {
            System.out.println("ERROR: the XML file could not be read: " + e.getMessage());
            AppLogger.error("Add: error reading " + fileName + ": " + e.getMessage());
            return;
        }

        if (passengers.isEmpty()) {
            System.out.println("The file does not contain any passenger.");
            AppLogger.warning("Add: no passengers in " + fileName);
            return;
        }

        try {
            // 2. Check the format of the data
            List<String> errors = PassengerValidator.validate(passengers);

            // 3. Check that the passports are not already in the database
            for (Passenger p : passengers) {
                if (p.getPassportNo() != null && PassengerDAO.exists(p.getPassportNo())) {
                    errors.add("Passport " + p.getPassportNo() + " already exists in the database.");
                }
            }

            if (!errors.isEmpty()) {
                System.out.println("The file contains errors. No passenger has been added:");
                for (String error : errors) {
                    System.out.println("  - " + error);
                    AppLogger.warning("Add: " + error);
                }
                return;
            }

            // 4. Insert all the passengers in one transaction
            PassengerDAO.insertAll(passengers);
            System.out.println(passengers.size() + " passenger(s) added successfully.");
            AppLogger.info("Add: " + passengers.size() + " passenger(s) added from " + fileName);

        } catch (SQLException e) {
            System.out.println("ERROR: no passenger has been added (" + e.getMessage() + ")");
            AppLogger.error("Add: transaction rolled back: " + e.getMessage());
        }
    }
}
