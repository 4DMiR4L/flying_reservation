package model;

import java.util.List;
import java.util.Objects;

public class Booking {
    private int id;
    private List<String> passengersNames;
    private Flight flight;

    public Booking(int id, List<String> passengersNames, Flight flight) {
        this.id = id;
        this.passengersNames = passengersNames;
        this.flight = flight;
    }

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getPassengersNames() {
        return passengersNames;
    }

    public void setPassengersNames(List<String> passengersNames) {
        this.passengersNames = passengersNames;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Booking booking)) return false;
        return id == booking.id && Objects.equals(passengersNames, booking.passengersNames) && Objects.equals(flight, booking.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengersNames, flight);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passengersNames=" + passengersNames +
                ", flight=" + flight +
                '}';
    }
}
