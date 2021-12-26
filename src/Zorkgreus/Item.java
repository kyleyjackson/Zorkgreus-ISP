package Zorkgreus;

public class Item extends OpenableObject {
  private String id;
  private String name;
  private String desc;
  private int weight;
  private String startRoom;

  private boolean isOpenable; //debating usage

  public Item(String name, String desc, int weight, String startRoom) {
    this.name = name;
    this.desc = desc;
    this.weight = weight;
    this.startRoom = startRoom;
  }

  public void open() {
    if (!isOpenable)
      System.out.println("The " + name + " cannot be opened.");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return desc;
  }

  public void setDescription(String desc) {
    this.desc = desc;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public String getStartingRoom() {
    return startRoom;
  }

  public void setStartingRoom(String startRoom) {
    this.startRoom = startRoom;
  }

/*
  public boolean isOpenable() {
    return isOpenable;
  }

  public void setOpenable(boolean isOpenable) {
    this.isOpenable = isOpenable;
  }
*/
}