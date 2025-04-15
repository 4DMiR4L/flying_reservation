package dao.impl;

import model.Booking;

import java.util.List;

public interface BookingDao {
    void save(Booking booking);
    void delete(Booking booking);
    List<Booking> getAllBookings();
    void getBookingById(int id);
}
