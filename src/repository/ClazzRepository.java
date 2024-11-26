//package repository;
//import exception.DuplicateEntityException;
//import exception.UnknownEntityException;
//import model.Booking;
//import model.Clazz;
//import model.Slot;
//
//import java.util.HashMap;
//import java.util.Objects;
//
//@Deprecated
//public class ClazzRepository {
//  public HashMap<String, Clazz> classes = new HashMap<>();
//
//  public HashMap<String, Clazz> getClasses() {
//    return classes;
//  }
//
//  private BookingRepository bookingRepository = new BookingRepository();
//  public boolean addClass(Clazz clazz) {
//    if(classes.containsKey(clazz.getId())) {
//      throw new DuplicateEntityException(clazz.getId()  + " already exists");
//    }
//    classes.put(clazz.getId(), clazz);
//    return true;
//  }
//
//  public Clazz getClass(String id) {
//    return classes.get(id);
//  }
//
//  public boolean addSlot(Slot s) {
//    if(s == null || !classes.containsKey(s.getClassId())) {
//      throw new UnknownEntityException(s.getClassId() + " classId does not exist");
//    }
//    for(Slot s1: classes.get(s.getClassId()).getTimings()) {
//      if(s1.getId().equals(s.getId())) {
//        throw new DuplicateEntityException(s.getId() + " slot already exists");
//      }
//    }
//    classes.get(s.getClassId()).addSlot(s);
//    return true;
//  }
//
//  public boolean book(Booking booking) {
//
//    if(booking == null || booking.getSlot() == null || booking.getUserId() == null) {
//      return false;
//    } else {
//      Clazz clazz = classes.get(booking.getSlot().getClassId());
//      for (Slot s : clazz.getTimings()) {
//        if (Objects.equals(s.getId(), booking.getSlot().getId()) && s.getAvailable() > 0) {
//          s.setAvailable(s.getAvailable()-1);
//          this.bookingRepository.addBooking(booking);
//          return true;
//        }
//      }
//    }
//
//    return false;
//
//  }
//}
