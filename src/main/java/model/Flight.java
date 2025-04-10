package model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Flight {
    private int id;
    private LocalDateTime date;
    private Cities initialPoint;
    private Cities destination;
    private int availableSeats;

    public Flight() {
    }

    public Flight(int id, LocalDateTime date, Cities initialPoint, Cities destination, int availableSeats) {
        this.id = id;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
        return id == flight.id && availableSeats == flight.availableSeats && Objects.equals(date, flight.date) && initialPoint == flight.initialPoint && destination == flight.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, initialPoint, destination, availableSeats);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", date=" + date +
                ", initialPoint=" + initialPoint +
                ", destination=" + destination +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
