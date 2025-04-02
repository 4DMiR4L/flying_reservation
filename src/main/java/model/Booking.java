package model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Booking {
    private int id;
    private List<String> passengersNames;
    private Flight flight;
    private Date bookingDate;



    public Booking() {
    }

    public Booking(int id, List<String> passengersNames, Flight flight, Date bookingDate) {
        this.id = id;
        this.passengersNames = passengersNames;
        this.flight = flight;
        this.bookingDate = bookingDate;
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Booking booking)) return false;
        return id == booking.id && Objects.equals(passengersNames, booking.passengersNames) && Objects.equals(flight, booking.flight) && Objects.equals(bookingDate, booking.bookingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengersNames, flight, bookingDate);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passengersNames=" + passengersNames +
                ", flight=" + flight +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
