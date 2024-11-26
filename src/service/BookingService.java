package service;

import exception.UnknownStateException;
import model.Booking;
import model.ClassType;
import model.Clazz;
import model.Slot;
import repository.BookingRepository;
import repository.GymRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
//  private ClazzRepository clazzRepository;
  private GymRepository gymRepository;
  private BookingRepository bookingRepository;

  public BookingService(GymRepository gymRepository, BookingRepository bookingRepository) {
    this.gymRepository = gymRepository;
    this.bookingRepository = bookingRepository;
  }

  public List<Booking> searchClasses(ClassType type) {
    List<Booking> availableBookings = new ArrayList<>();
    for(String key : gymRepository.getGyms().keySet()) {
      for(Clazz c: gymRepository.getGyms().get(key).getClasses()) {
        if(c.getType().equals(type)) {
          List<Slot> freeSlot =  (c.getTimings().stream().filter(slot -> slot.getAvailable() > 0).collect(Collectors.toList()));
          availableBookings.addAll(freeSlot.stream().map(s -> {
            return new Booking(null, s.getId(), s.getClassId(), s.getGymId());
          }).collect(Collectors.toList()));
        }
      }

    }
    return availableBookings;
  }

  public boolean book(List<Booking> bookings, int idx, String usedId) {
    if(bookings == null || bookings.isEmpty() || idx <= 0 || idx > bookings.size()) {
      throw new UnknownStateException("Booking Cannot be done");
    } else {
      Booking booking =bookings.get(idx-1);
      booking.setUserId(usedId);
      boolean status = bookingRepository.addBooking(booking);
      Clazz clazz = gymRepository.getGyms().get(booking.getGymId()).getClasses().stream().filter(c -> c.getId().equals(booking.getClassId())).collect(
          Collectors.toList()).get(0);
      for(Slot s: clazz.getTimings()) {
        if(s.getId().equals(booking.getSlotId())) {
          s.setAvailable(s.getAvailable()-1);
        }
      }
      if(status) {
        System.out.println("Successfully completed the booking");
      }
      return status;
    }
  }
}
