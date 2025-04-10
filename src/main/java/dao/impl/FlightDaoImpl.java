package dao.impl;

import model.Flight;

import java.util.List;

public interface FlightDaoImpl {
    void save(Flight entity);

    Flight findById(long id);

    List<Flight> findAll();

    void cancelFlight(long flightId);

    List<Flight> findByOrigin(String origin);
}
