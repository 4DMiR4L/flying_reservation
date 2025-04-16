package dto;


import model.Cities;

import java.time.LocalDateTime;
import java.util.Objects;

public class FlightDto {
    private int id;
    private Cities initialPoint;
    private Cities destination;
    private LocalDateTime departureTime;
    private int numberOfSeats;

    public FlightDto() {
    }

    public FlightDto(int id, Cities initialPoint, Cities destination, LocalDateTime departureTime, int numberOfSeats) {
        this.id = id;
        this.initialPoint = initialPoint;
        this.destination = destination;
        this.departureTime = departureTime;
        this.numberOfSeats = numberOfSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cities getInitialPoint() {
        return initialPoint;
    }

    public void setInitialPoint(Cities initialPoint) {
        this.initialPoint = initialPoint;
    }

    public void setOrigin(String origin) {
        this.initialPoint = Cities.valueOf(origin.toUpperCase());
    }

    public Cities getDestination() {
        return destination;
    }

    public void setDestination(Cities destination) {
        this.destination = destination;
    }

    public void setDestination(String destination) {
        this.destination = Cities.valueOf(destination.toUpperCase());
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FlightDto flightDto)) return false;
        return id == flightDto.id && numberOfSeats == flightDto.numberOfSeats && initialPoint == flightDto.initialPoint && destination == flightDto.destination && Objects.equals(departureTime, flightDto.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initialPoint, destination, departureTime, numberOfSeats);
    }

    @Override
    public String toString() {
        return String.format("\n" + "{id=%d, initialPoint='%s', destination='%s', departureTime=%s, numberOfSeats=%d}\n", id, initialPoint, destination, departureTime, numberOfSeats);
    }

}
