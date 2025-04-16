package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private int id;
    private LocalDateTime departureTime;
    private Cities initialPoint;
    private Cities destination;
    private int availableSeats;

    public Flight() {
    }

    public Flight(LocalDateTime date, Cities initialPoint, Cities destination, int availableSeats) {
        this.departureTime = date;
        this.initialPoint = initialPoint;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public Flight(int id, LocalDateTime date, Cities initialPoint, Cities destination, int availableSeats) {
        this.id = id;
        this.departureTime = date;
        this.initialPoint = initialPoint;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public Flight(int flightId, Cities cities, Cities initialPoint, LocalDateTime departureTime, int numOfSeats) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Cities getInitialPoint() {
        return initialPoint;
    }

    public void setInitialPoint(Cities initialPoint) {
        this.initialPoint = initialPoint;
    }

    public Cities getDestination() {
        return destination;
    }

    public void setDestination(Cities destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Flight flight)) return false;
        return id == flight.id && availableSeats == flight.availableSeats && Objects.equals(departureTime, flight.departureTime) && initialPoint == flight.initialPoint && destination == flight.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureTime, initialPoint, destination, availableSeats);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departureTime=" + departureTime +
                ", initialPoint=" + initialPoint +
                ", destination=" + destination +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
