package repository;

import exception.DuplicateEntityException;
import model.Clazz;
import model.Gym;

import java.util.HashMap;

public class GymRepository {
  private HashMap<String, Gym> gyms = new HashMap<String, Gym>();
  ClazzRepository clazzRepository;

  public GymRepository(ClazzRepository clazzRepository) {
    this.clazzRepository = clazzRepository;
  }

  public void addGym(Gym gym) {
    if(gyms.containsKey(gym.getId())) {
      throw new DuplicateEntityException(gym.getId() + " already exists");
    }
    gyms.put(gym.getId(), gym);
    for(Clazz c: gym.getClasses()){
      clazzRepository.addClass(c);
    }
  }

  public Gym getGym(String id) {
    return gyms.get(id);
  }

  public boolean addClass(Clazz clazz, String gymId) {
    if(clazzRepository.addClass(clazz)) {
      return gyms.get(gymId).addClass(clazz);
    }
    return false;
  }

  public HashMap<String, Gym> getGyms() {
    return gyms;
  }
}
