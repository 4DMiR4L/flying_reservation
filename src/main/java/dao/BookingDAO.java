package dao;

import config.DatabaseConfig;
import dao.impl.BookingDaoImpl;
import model.Booking;
import model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO implements BookingDaoImpl {
    @Override
    public void save(Booking booking) {
        String sql = "INSERT INTO booking (booking_id, passengers_names, flight_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection()
             ; PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            String passengersNames =  String.join(",", booking.getPassengersNames());

            stmt.setInt(1, booking.getId());
            stmt.setString(2, passengersNames);
            stmt.setInt(3, booking.getFlight().getId());

            int rowsInserted = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Booking booking) {
        String sql = "UPDATE booking SET cancel = true WHERE booking_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getId());
            int rowsUpdated = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to cancel reservation", e);
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM booking";
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking_id"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }

    @Override
    public void getBookingById(int id) {
    String sql = "SELECT * FROM booking WHERE booking_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");

            } else {
                System.out.println("No room found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find room by ID", e);
        }
    }

}
