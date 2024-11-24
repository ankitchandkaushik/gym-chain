package service;
import exception.DuplicateEntityException;
import exception.UnknownEntityException;
import model.Clazz;
import model.Gym;
import repository.ClazzRepository;
import repository.GymRepository;
public class AdminService {
  private GymRepository gymRepository ;
  private ClazzRepository clazzRepository;
  public AdminService(GymRepository gymRepository) {
    this.gymRepository = gymRepository;
  }

  public void addGym(Gym gym) {
    gymRepository.addGym(gym);
  }

  public void addClass(Clazz clazz, String gymId) {

    if(clazz == null || gymId == null) {
      throw new UnknownEntityException("Cannot find entity");
    }
    if(!gymRepository.getGyms().containsKey(gymId) ) {
      throw new UnknownEntityException(gymId + " gym with this id not available");
    }
    if(!clazzRepository.getClasses().containsKey(clazz.getId())) {
      throw new DuplicateEntityException(clazz.getId());
    }
    gymRepository.addClass(clazz, gymId);
  }
}
