package dao;

import config.DatabaseConfig;
import dao.impl.FlightDaoImpl;
import helper.LoggerHelper;
import model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class FlightDAO implements FlightDaoImpl {
    public FlightDAO() {
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

        }catch (SQLException e) {
            LoggerHelper.error("SQLException: " + e.getMessage());
        }
    }

    @Override
    public List<Flight> findByOrigin(String origin) {
        return List.of();
    }

    @Override
    public void cancelFlight(long flightId) {

    }

    @Override
    public List<Flight> findAll() {
        return List.of();
    }

    @Override
    public Flight findById(long id) {
        return null;
    }
}
