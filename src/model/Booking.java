package model;

import java.util.UUID;

public class Booking {
  String id;
//  Slot slot;
  String userId;
  String slotId;
  String classId;
  String gymId;
  public Booking(String userId, String slotId, String classId, String gymId) {
    this.id = UUID.randomUUID().toString();
    this.userId = userId;
    this.slotId = slotId;
    this.classId = classId;
    this.gymId = gymId;
  }
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

//  public Slot getSlot() {
//    return slot;
//  }

//  public void setSlotId(Slot slot) {
//    this.slot = slot;
//  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getSlotId() {
    return slotId;
  }

  public void setSlotId(String slotId) {
    this.slotId = slotId;
  }

  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public String getGymId() {
    return gymId;
  }

  public void setGymId(String gymId) {
    this.gymId = gymId;
  }
}
