package service.impl;

import dao.impl.FlightDao;
import dto.CriteriaDto;
import dto.FlightDto;
import model.Cities;
import model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FlightService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
    private final FlightDao flightDao;

    public FlightServiceImpl(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public void saveFlight(FlightDto flightDto) {
        try {
            Flight flightEntity = new Flight(flightDto.getDepartureTime(),
                    flightDto.getDestination(), flightDto.getInitialPoint(), flightDto.getNumberOfSeats());
            flightDao.save(flightEntity);

            if (flightEntity.getId() == 0) {
                logger.error("Flight failed, ID is zero");
            }

            flightDto.setId(flightEntity.getId());
        } catch (Exception e) {
            logger.error("Exception while saving flight", e);
        }
    }

    @Override
    public void cancelFlight(int flightId) {
        try {
            Flight flight = flightDao.findById(flightId);
            if (flight == null) {
                logger.error("Flight cannot be null", flightId);
            } else {
                flightDao.cancelFlight(flightId);
            }
        } catch (Exception e) {
            logger.error("Exception while canceling flight", e);
        }
    }

    @Override
    public List<FlightDto> findByInitialPoint(String initialPointin) {
        try {

            return flightDao.findByInitialPoint(initialPointin).stream().map(flight -> new FlightDto(flight.getId(),
                    flight.getInitialPoint(),
                    flight.getDestination(),
                    flight.getDepartureTime(),
                    flight.getAvailableSeats())).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Exception while finding flight", e);
        }
        return List.of();
    }

    @Override
    public List<FlightDto> getFlightsByCriteria(CriteriaDto criteria) {
        try {
            List<Flight> entities = flightDao.findAll();

            return entities.stream().filter(entity -> entity.getDestination().equals(criteria.getDestination())
                            && entity.getDepartureTime().equals(criteria.getTime()) &&
                            entity.getAvailableSeats() >= criteria.getSeats()).map(entity -> new FlightDto(entity.getId(), entity.getInitialPoint(),
                            entity.getDestination(), entity.getDepartureTime(), entity.getAvailableSeats()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Exception while getting flights", e);
        }
        return List.of();
    }

    @Override
    public List<FlightDto> getAllFlights() {
        try {
            return flightDao.findAll().stream().map(flight -> new FlightDto(flight.getId()
                    , flight.getInitialPoint(),
                    flight.getDestination(),
                    flight.getDepartureTime()
                    , flight.getAvailableSeats())).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Exception while getting flights", e);
        }
        return List.of();
    }

    @Override
    public FlightDto getFlightById(int id) {

        try {
            Flight flight = flightDao.findById(id);
            if (flight == null) {
                logger.error("Flight not found with id: {}", id);
                return null;
            }
            return new FlightDto(flight.getId(), flight.getInitialPoint(), flight.getDestination(), flight.getDepartureTime(), flight.getAvailableSeats());
        } catch (Exception e) {
            logger.error("Exception while getting flight by id", e);
            return null;
        }
    }

}
