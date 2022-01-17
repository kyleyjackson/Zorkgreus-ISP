package Zorkgreus;

public class Item{
  private String name;
  private String desc;
  private int weight;
  private String startRoom;
  private int gold;

  public Item(String name, String desc, int weight, String startRoom, int gold) {
    this.name = name;
    this.desc = desc;
    this.weight = weight;
    this.startRoom = startRoom;
    this.gold = gold;
  }

  /*accessor and mutator methods for item name */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /*accessor and mutator methods for item description */
  public String getDescription() {
    return desc;
  }

  public void setDescription(String desc) {
    this.desc = desc;
  }

  /*accessor and mutator methods for item weight */
  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  /*accessor and mutator methods for item startingRoom */
  public String getStartingRoom() {
    return startRoom;
  }

  public void setStartingRoom(String startRoom) {
    this.startRoom = startRoom;
  }
  /*accessor method for item gold */
  public int getGold(){
    return gold;
  }
}