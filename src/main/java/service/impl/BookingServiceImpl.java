package service.impl;

import dto.BookingDto;
import model.Booking;
import service.BookingService;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    @Override
    public void saveBooking(BookingDto bookingDto) {

    }

    @Override
    public void cancelBooking(long bookingId) {

    }

    @Override
    public List<BookingDto> getALLBookings() {
        return List.of();
    }

    @Override
    public Booking findById(long bookingId) {
        return null;
    }

    @Override
    public List<BookingDto> getAllBookingsByPassenger(String passengerNames) {
        return List.of();
    }
}
