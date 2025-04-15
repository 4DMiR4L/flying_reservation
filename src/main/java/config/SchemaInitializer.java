package config;

import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {

    public static void createTables() {
        String createFlights = """
            CREATE TABLE IF NOT EXISTS flights (
                id SERIAL PRIMARY KEY,
                initialPoint VARCHAR(64),
                destination VARCHAR(64),
                departure_time TIMESTAMP,
                free_seats INTEGER
            );
            """;

        String createPassengers = """
            CREATE TABLE IF NOT EXISTS passengers (
                id SERIAL PRIMARY KEY,
                full_name VARCHAR(64)
            );
            """;

        String createBookings = """
            CREATE TABLE IF NOT EXISTS bookings (
                id SERIAL PRIMARY KEY,
                flight_id INTEGER,
                FOREIGN KEY (flight_id) REFERENCES flights(id) ON DELETE CASCADE
            );
            """;

        String createBookingsPassengers = """
            CREATE TABLE IF NOT EXISTS bookings_passengers (
                booking_id INTEGER,
                passenger_id INTEGER,
                PRIMARY KEY (booking_id, passenger_id),
                FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE,
                FOREIGN KEY (passenger_id) REFERENCES passengers(id) ON DELETE CASCADE
            );
            """;

        try (Connection conn = DatabaseConfig.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(createFlights);
            stmt.execute(createPassengers);
            stmt.execute(createBookings);
            stmt.execute(createBookingsPassengers);
            System.out.println(" Tables created successfully.");
        } catch (SQLException e) {
            System.err.println(" Error while creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
