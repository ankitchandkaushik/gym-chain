package repository;

import exception.DuplicateEntityException;
import exception.UnknownEntityException;
import model.Clazz;
import model.Gym;

import java.util.HashMap;

public class GymRepository {
  private HashMap<String, Gym> gyms = new HashMap<String, Gym>();
//  ClazzRepository clazzRepository;

  public GymRepository() {

  }

  public void addGym(Gym gym) {
    if(gyms.containsKey(gym.getId())) {
      throw new DuplicateEntityException(gym.getId() + " already exists");
    }
    gyms.put(gym.getId(), gym);
    System.out.println("Added gym: " + gym.toString());
  }

  public Gym getGym(String id) {
    return gyms.get(id);
  }

  public boolean addClass(Clazz clazz) {

    gyms.get(clazz.getGymId()).addClass(clazz);
    return true;
  }

  public HashMap<String, Gym> getGyms() {
    return gyms;
  }
}
