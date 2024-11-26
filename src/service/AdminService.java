package service;
import exception.DuplicateEntityException;
import exception.UnknownEntityException;
import model.Clazz;
import model.Gym;
import model.Slot;
import repository.BookingRepository;
import repository.GymRepository;

import java.util.Optional;

public class AdminService {
  private GymRepository gymRepository ;
  private BookingRepository bookingRepository;
//  private ClazzRepository clazzRepository;
  public AdminService(GymRepository gymRepository, BookingRepository bookingRepository) {
    this.gymRepository = gymRepository;
    this.bookingRepository = bookingRepository;
  }

  public void addGym(Gym gym) {
    gymRepository.addGym(gym);
  }

  public void addClass(Clazz clazz) {

    if(clazz == null || clazz.getGymId() == null) {
      throw new UnknownEntityException("Cannot find entity");
    }
    if(!gymRepository.getGyms().containsKey(clazz.getGymId()) ) {
      throw new UnknownEntityException(clazz.getGymId() + " gym with this id not available");
    }
    if(!gymRepository.getGyms().get(clazz.getGymId()).getClasses().stream().anyMatch(c -> c.getId().equals(clazz.getId()))) {
      throw new DuplicateEntityException(clazz.getId());
    }
    gymRepository.addClass(clazz);
  }

  public void addSlot(Slot slot) {
    if(slot == null ) {
      throw new UnknownEntityException("Cannot find entity");
    }
    if(gymRepository.getGyms().containsKey(slot.getGymId())) {
      if(gymRepository.getGyms().get(slot.getGymId()).getClasses().stream().anyMatch(c -> c.getId().equals(slot.getClassId()))) {
        Clazz clazz = null;
        for(Clazz c: gymRepository.getGyms().get(slot.getGymId()).getClasses()) {
          if(c.getId().equals(slot.getClassId())) {
            c.addSlot(slot);
            return ;
          }
        }
      } else {
        throw new UnknownEntityException(slot.getClassId() + " class with this id not available");
      }
    } else {
      throw new UnknownEntityException(slot.getGymId() + " gym with this id not available");
    }
  }

  public void deleteGym(Gym gym) {
    if(gymRepository.getGyms().containsKey(gym.getId())) {
      System.out.println("Deleting gym " + gym);
      for(String bid : bookingRepository.getBookings().keySet()) {
        if(bookingRepository.getBookings().get(bid).getGymId().equals(gym.getId())) {
          bookingRepository.getBookings().remove(bid);
        }
      }
      gymRepository.getGyms().remove(gym.getId());

    } else {
      throw new UnknownEntityException("No gym with id " + gym.getId() + " exists");
    }
  }

  public void deleteClass(Clazz clazz) {
    for(String bid : bookingRepository.getBookings().keySet()) {
      if(bookingRepository.getBookings().get(bid).getClassId().equals(clazz.getId())) {
        bookingRepository.getBookings().remove(bid);
      }
    }
    for(Clazz c: gymRepository.getGyms().get(clazz.getGymId()).getClasses()) {
      if(clazz.getId().equals(c.getId())) {
        gymRepository.getGyms().get(clazz.getGymId()).getClasses().remove(c);
        break;
      }
    }
  }

  public void deleteSlot(Slot slot) {
    for(String bid: bookingRepository.getBookings().keySet()) {
      if(bookingRepository.getBookings().get(bid).getSlotId().equals(slot.getId())) {
        bookingRepository.getBookings().remove(bid);
      }
    }

    for(Clazz c: gymRepository.getGyms().get(slot.getGymId()).getClasses()) {
      for(Slot s: c.getTimings()) {
        if(s.getId().equals(slot.getId())) {
          c.getTimings().remove(slot);
        }
      }
    }

    // TODO cleanup bookings inside class if required

  }
}
