package com.dataaccess.finalactivityAFP2627;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PassengerDAO {

    /**
     * Checks if a passenger with this passport is already in the database.
     */
    public static boolean exists(String passportNo) throws SQLException {
        String sql = "SELECT passportno FROM passengers WHERE passportno = ?";

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, passportNo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Inserts all the passengers in one transaction: all of them or none.
     */
    public static void insertAll(List<Passenger> passengers) throws SQLException {
        String sql = "INSERT INTO passengers (passportno, firstname, lastname, address, phone) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseManager.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (Passenger p : passengers) {
                    ps.setString(1, p.getPassportNo());
                    ps.setString(2, p.getFirstName());
                    ps.setString(3, p.getLastName());
                    ps.setString(4, p.getAddress());
                    ps.setString(5, p.getPhone());
                    ps.executeUpdate();
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }
}
