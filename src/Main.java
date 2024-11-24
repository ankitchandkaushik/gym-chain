import model.*;
import repository.BookingRepository;
import repository.ClazzRepository;
import repository.GymRepository;
import service.AdminService;
import service.BookingService;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    ClazzRepository clazzRepository = new ClazzRepository();
    GymRepository gymRepository = new GymRepository(clazzRepository);

    BookingRepository bookingRepository = new BookingRepository();
    BookingService bookingService = new BookingService(clazzRepository);
    AdminService adminService = new AdminService(gymRepository);
    Gym gym1 = new Gym();
    Clazz c1 = new Clazz(gym1.getId(), ClassType.PILATES);
    gym1.addClass(c1);

    Slot morningSlot = new Slot(c1.getId(), 6, 8, 10);
    Slot afterNoonSlot = new Slot(c1.getId(), 12, 14, 10);

    Slot eveningSlot = new Slot(c1.getId(), 18, 20, 10);
    adminService.addGym(gym1);


    clazzRepository.addSlot(morningSlot);
    clazzRepository.addSlot(afterNoonSlot);
    clazzRepository.addSlot(eveningSlot);

    List<Booking> bookingList = bookingService.searchClasses(ClassType.PILATES);

    bookingService.book(bookingList, 2, "1");





  }
}
