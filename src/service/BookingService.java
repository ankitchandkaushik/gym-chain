package service;

import exception.UnknownStateException;
import model.Booking;
import model.ClassType;
import model.Clazz;
import model.Slot;
import repository.ClazzRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
  private ClazzRepository clazzRepository;

  public BookingService(ClazzRepository clazzRepository) {
    this.clazzRepository = clazzRepository;
  }

  public List<Booking> searchClasses(ClassType type) {
    List<Booking> availableBookings = new ArrayList<>();
    for(String key : clazzRepository.classes.keySet()) {
      Clazz clazz = clazzRepository.classes.get(key);
      if(clazz.getType().equals(type)) {
        List<Slot> freeSlot =  (clazz.getTimings().stream().filter(slot -> slot.getAvailable() > 0).collect(Collectors.toList()));
        availableBookings.addAll(freeSlot.stream().map(Booking::new).collect(Collectors.toList()));
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
      boolean status = clazzRepository.book(booking);;
      if(status) {
        System.out.println("Successfully completed the booking");
      }
      return status;
    }
  }
}
