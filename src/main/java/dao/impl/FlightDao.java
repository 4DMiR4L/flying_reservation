package dao.impl;

import model.Flight;

import java.util.List;

public interface FlightDao {
    void save(Flight entity);

    Flight findById(long id);

    List<Flight> findAll();

    void cancelFlight(int flightId);

    List<Flight> findByInitialPoint(String initialPoint);
}
