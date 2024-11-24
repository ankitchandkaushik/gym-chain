package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Clazz {
  String id;
  String gymId;
  ClassType type;
  List<Booking> bookings;
  List<Slot> timings;

  public Clazz(String gymId, ClassType classType){
    this.id = UUID.randomUUID().toString();
    this.gymId = gymId;
    this.type = classType;
    this.bookings = new ArrayList<>();
    this.timings = new ArrayList<>();
  }

  public Clazz(String gymId, ClassType classType, List<Slot> classes){
    this.id = UUID.randomUUID().toString();
    this.gymId = gymId;
    this.type = classType;
    this.bookings = new ArrayList<>();
    this.timings = Objects.requireNonNullElseGet(classes, ArrayList::new);

  }

  public String getId() {
    return this.id;
  }

  public ClassType getType() {
    return this.type;
  }

  public List<Slot> getTimings() {
    return this.timings;
  }

  public void addSlot(Slot slot) {
    this.timings.add(slot);
  }



}