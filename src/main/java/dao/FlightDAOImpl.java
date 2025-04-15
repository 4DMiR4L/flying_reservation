package dao;

import config.DatabaseConfig;
import dao.impl.FlightDao;
import helper.LoggerHelper;
import model.Cities;
import model.Flight;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements FlightDao {
    public FlightDAOImpl() {
        super();
    }

    public void save(Flight flight) {
        String sql = "insert into flight values(?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {
            query.setString(1, flight.getInitialPoint().name().toUpperCase());
            query.setString(2, flight.getDestination().name().toUpperCase());
            query.setTimestamp(3, Timestamp.valueOf(flight.getDate()));
            query.setInt(4, flight.getAvailableSeats());
            query.executeUpdate();

        } catch (SQLException e) {
            LoggerHelper.log.error("SQLException: " + e.getMessage());
        }
    }

    @Override
    public List<Flight> findByInitialPoint(String initialPoint) {
        List<Flight> flightEntities = new ArrayList<>();
        String sql = "select * from flight where initial_point = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement query = conn.prepareStatement(sql);
             ResultSet resultSet = query.executeQuery()) {
            while (resultSet.next()) {
                int flightId = resultSet.getInt("id");
                String origin = resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                flightEntities.add(new Flight(flightId, Cities.fromString(origin), Cities.fromString(destination), departureTime, numOfSeats));
            }
        } catch (SQLException e) {
            LoggerHelper.log.error("Failed to find all flights: " + e.getMessage(), e);
        }
        return flightEntities;
    }

    @Override
    public void cancelFlight(int flightId) {
        String deleteBookingPassengerByFlightId = "DELETE FROM booking_passenger WHERE booking_id IN (SELECT id FROM booking WHERE flight_id = ?)";
        String deleteBookingsByFlightId = "DELETE FROM bookings WHERE flight_id = ?";
        String cancelFlightSql = "DELETE FROM flight WHERE flight_id = ?";

        try (Connection conn = DatabaseConfig.getConnection()) {
            try {
                try (PreparedStatement deleteBookingPassengerById = conn.prepareStatement(deleteBookingPassengerByFlightId)) {
                    deleteBookingPassengerById.setInt(1, flightId);
                    deleteBookingPassengerById.executeUpdate();
                } catch (SQLException e) {
                    LoggerHelper.log.error(" Delete booking passenger by flight id: " + e.getMessage());
                }

                try (PreparedStatement deleteBookingbyFlightid = conn.prepareStatement(deleteBookingsByFlightId)) {
                    deleteBookingbyFlightid.setInt(1, flightId);
                    deleteBookingbyFlightid.executeUpdate();
                } catch (SQLException e) {
                    LoggerHelper.log.error(" Delete bookings by flight id: " + e.getMessage());
                }

                try (PreparedStatement cancelFlight = conn.prepareStatement(cancelFlightSql)) {
                    cancelFlight.setInt(1, flightId);
                    cancelFlight.executeUpdate();
                } catch (SQLException e) {
                    LoggerHelper.log.error(" Cancel flight id: " + e.getMessage());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            LoggerHelper.log.error("SQLException: " + e.getMessage());
        }
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flight = new ArrayList<>();
        String sql = "select * from flight";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement query = conn.prepareStatement(sql);
             ResultSet resultSet = query.executeQuery()) {
            while (resultSet.next()) {
                int flightId = (int) resultSet.getLong("id");
                String origin = resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                flight.add(new Flight(flightId, Cities.fromString(origin), Cities.fromString(destination), departureTime, numOfSeats));
            }
        } catch (SQLException e) {
            LoggerHelper.log.error("Failed to find all flights: " + e.getMessage(), e);
        }
        return flight;
    }

    @Override
    public Flight findById(long id) {
        String sql = "select * from flight where id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                int flightId = resultSet.getInt("id");
                String initialPoint = resultSet.getString("initialPoint");
                String destination = resultSet.getString("destination");
                LocalDateTime departureTime = resultSet.getTimestamp("departureTime").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                return new Flight(flightId, Cities.fromString(initialPoint), Cities.fromString(destination), departureTime, numOfSeats);
            }
        } catch (SQLException e) {
            LoggerHelper.log.error("Failed to find flight by ID: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Flight> findByDestination(String destination) {
        List<Flight> flight = new ArrayList<>();
        String sql = "select * from flight where destination = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql)) {

            stmt.setString(1 , destination);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int flightId = resultSet.getInt("id");
                String initialPoint = resultSet.getString("initialPoint");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                flight.add(new Flight(flightId, Cities.fromString(initialPoint), Cities.fromString(destination), departureTime, numOfSeats));
            }
        } catch (SQLException e) {
           LoggerHelper.log.error("Failed to find flight by destination: " + e.getMessage(), e);
        }
        return flight;
    }
}
