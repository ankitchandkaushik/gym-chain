package repository;
import exception.DuplicateEntityException;
import model.Booking;

import java.util.HashMap;

public class BookingRepository {

  HashMap<String, Booking> bookings = new HashMap<>();

  public HashMap<String, Booking> getBookings() {
    return bookings;
  }

  public boolean addBooking(Booking booking) {
    if(bookings.containsKey(booking.getId())) {
      throw new DuplicateEntityException(booking.getId() + " booking already exists");
    }
    bookings.put(booking.getId(), booking);
    return  true;
  }

  public Booking getBooking(String id) {
    return bookings.get(id);
  }

}
