package model;

public class Booking {
  String id;
  Slot slot;
  String userId;
  public Booking(Slot slot) {
    this.slot = slot;
  }
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Slot getSlot() {
    return slot;
  }

  public void setSlotId(Slot slot) {
    this.slot = slot;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
