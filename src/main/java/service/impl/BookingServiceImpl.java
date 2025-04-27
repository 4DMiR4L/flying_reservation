package service.impl;

import dao.impl.BookingDao;
import dto.BookingDto;
import model.Booking;
import service.BookingService;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }


    @Override
    public void saveBooking(BookingDto bookingDto) {
        Booking booking = new Booking(bookingDto.getId(), bookingDto.getPassengerNames());
        bookingDao.save(booking);
        bookingDto.setId(booking.getId());
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
