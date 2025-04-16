package service;

import dto.BookingDto;
import model.Booking;

import java.util.List;

public interface BookingService {
    void saveBooking(BookingDto bookingDto);

    void cancelBooking(long bookingId);

    List<BookingDto> getALLBookings();

    Booking findById(long bookingId);

    List<BookingDto> getAllBookingsByPassenger(String passengerNames);
}
