package model;

import java.util.UUID;

public class Slot {
  String id;
  String classId;
  String gymId;
  int startTime;
  int endTime;
  int available;

  public Slot(String gymId, String classId, int startTime, int endTime, int available) {
    this.id = UUID.randomUUID().toString();
    this.gymId = gymId;
    this.classId = classId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.available = available;
  }

  public int getAvailable() {
    return this.available;
  }
  public String getId() {
    return this.id;
  }

//  public void setId(String id) {
//    this.id = id;
//  }

  public String getClassId() {
    return classId;
  }

//  public void setClassId(String classId) {
//    this.classId = classId;
//  }

  public int getStartTime() {
    return startTime;
  }

  public void setStartTime(int startTime) {
    this.startTime = startTime;
  }

  public int getEndTime() {
    return endTime;
  }

  public void setEndTime(int endTime) {
    this.endTime = endTime;
  }

  public void setAvailable(int available) {
    this.available = available;
  }

  public String getGymId() {
    return gymId;
  }

//  public void setGymId(String gymId) {
//    this.gymId = gymId;
//  }
  //  public Slot(String classId, int startTime, int endTime, int size) {
//    this.id = UUID.randomUUID().toString();
//    this.classId = classId;
//    this.startTime = startTime;
//    this.endTime = endTime;
//    this.available = size;
//  }
}
