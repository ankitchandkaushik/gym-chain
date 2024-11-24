package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Gym {
  String id;
  List<Clazz> classes;

  public Gym() {
    this.id = UUID.randomUUID().toString();
    this.classes = new ArrayList<>();
  }
  public String getId(){
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Clazz> getClasses() {
    return classes;
  }

  public void setClasses(List<Clazz> classes) {
    this.classes = classes;
  }

  public boolean addClass(Clazz clazz) {
    return this.classes.add(clazz);
  }
}
