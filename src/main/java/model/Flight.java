package model;

import java.util.Date;
import java.util.Objects;

public class Flight {
    private int id;
    private Date date;
    private String destination;
    private int availableSeats;

    public Flight() {
    }

    public Flight(int id, Date date, String destination, int availableSeats) {
        this.id = id;
        this.date = date;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
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
        return id == flight.id && availableSeats == flight.availableSeats && Objects.equals(date, flight.date) && Objects.equals(destination, flight.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, destination, availableSeats);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", date=" + date +
                ", destination='" + destination + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
