package dao;

import config.DatabaseConfig;
import dao.impl.BookingDao;
import model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDao {
    @Override
    public void save(Booking booking) {
        String sql = "INSERT INTO booking (booking_id, passengers_names, flight_id , booking_date) VALUES (?, ?, ? ,?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String passengersNames = String.join(",", booking.getPassengersNames());

            stmt.setInt(1, booking.getId());
            stmt.setString(2, passengersNames);
            stmt.setInt(3, booking.getFlight().getId());
            stmt.setTimestamp(4, new Timestamp(booking.getBookingDate().getTime()));

            conn.setAutoCommit(false);

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
                rs.getInt("booking_id");


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

    public boolean isFlightExist(int flightId) throws SQLException {
        String sql = "SELECT * FROM booking WHERE booking_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public int createBooking(int flightId) {
        String sql = "INSERT INTO booking(flight_id) VALUES(?);";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, flightId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                conn.rollback();
                throw new SQLException("Booking creation failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addPassenger(String passengerName) throws SQLException {
        String sql = "INSERT INTO booking_passenger (passenger_name) VALUES (?);";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, passengerName);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Passenger creation failed, no ID obtained.");
            }
        }
    }

    public void addBookingPassengers(int bookingId, int passengerId) {
        String sql = "INSERT INTO booking_passenger(booking_id, passenger_id) VALUES(?,?);";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            stmt.setInt(2, passengerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getPassengerCountByBookingId(Connection conn, int bookingId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM booking_passenger WHERE booking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve passenger count for booking ID: " + bookingId);
            }
        }
    }


}
