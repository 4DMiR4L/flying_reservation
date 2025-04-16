package service;

import dto.CriteriaDto;
import dto.FlightDto;
import model.Cities;

import java.util.List;

public interface FlightService {

    void saveFlight(FlightDto flightDto);

    void cancelFlight(int flightId);

    List<FlightDto> findByInitialPoint(String initialPoinit);

    List<FlightDto> getFlightsByCriteria(CriteriaDto criteria);

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(int id);

}

