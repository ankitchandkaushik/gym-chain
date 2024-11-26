import model.*;
import repository.BookingRepository;
import repository.GymRepository;
import service.AdminService;
import service.BookingService;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to My gym chain");
//    ClazzRepository clazzRepository = new ClazzRepository();
    BookingRepository bookingRepository = new BookingRepository();
    GymRepository gymRepository = new GymRepository();

    BookingService bookingService = new BookingService(gymRepository, bookingRepository);
    AdminService adminService = new AdminService(gymRepository, bookingRepository);
    Gym gym1 = new Gym();
    Clazz c1 = new Clazz(gym1.getId(), ClassType.PILATES);
    gym1.addClass(c1);

    Slot morningSlot = new Slot(gym1.getId(),c1.getId(), 6, 8, 10);
    Slot afterNoonSlot = new Slot(gym1.getId(), c1.getId(), 12, 14, 10);

    Slot eveningSlot = new Slot(gym1.getId(), c1.getId(), 18, 20, 10);
    adminService.addGym(gym1);


    c1.addSlot(morningSlot);
    c1.addSlot(afterNoonSlot);
    c1.addSlot(eveningSlot);

    adminService.addClass(c1);

    List<Booking> bookingList = bookingService.searchClasses(ClassType.PILATES);
    System.out.println(bookingList);

    bookingService.book(bookingList, 2, "1");





  }
}
