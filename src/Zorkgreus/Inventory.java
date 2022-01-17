package Zorkgreus;

import java.util.ArrayList;

public class Inventory {
  private ArrayList<Item> items;
  private int maxWeight;
  private int currentWeight;

  public Inventory(int maxWeight) {
    this.items = new ArrayList<Item>();
    this.maxWeight = maxWeight;
    this.currentWeight = 0;
  }

  /*accessor and mutator methods for items array list */
  public ArrayList<Item> getItems(){
    return items;
  }

  public void setInventory(ArrayList<Item> items) {
    this.items = items;
  }

  /*accessor and mutator methods for maxWeight */
  public int getMaxWeight() {
    return maxWeight;
  }

  public void setMaxWeight(int extra) {
    maxWeight = extra;
  }

  /**
   * Adds to players' inventory's max weight.
   * @param n is the value added.
   */
  public void addMaxWeight(int n) { 
    maxWeight += n;
    if (n < 0)
        System.out.println("Your inventory's max weight decreased by " + n + "! Total: " + getMaxWeight());
    else   
        System.out.println("Your inventory's max weight increased by " + n + "! Total: " + getMaxWeight());
  }

  /*accessor method for currentWeight */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /*displays the current weight in comparison to the the max weight */
  public void displayWeight(){
    System.out.println("Current Weight: " + currentWeight + "/" + maxWeight);
  }

  /**
   * adds an items to the players inventory and increases the currentWeight
   * @param item the item being added
   * @return true if taken
   */
  public boolean addPlayerItem(Item item) {
    if (item.getWeight() + currentWeight <= maxWeight){
      currentWeight += item.getWeight();
      items.add(item);
      System.out.println(item.getName() + " has been added to your inventory. ");
      return true;
    }
    else {
      System.out.println("There is no room to add the item.");
      return false;
    }
  }

  /**
   * adds an item to the rooms inventory and increases the currentWeight
   * @param item the item being added
   */
  public void addRoomItem(Item item){
    if (item.getWeight() + currentWeight <= maxWeight){
      currentWeight += item.getWeight();
      items.add(item);
    }
    else {
      System.out.println("There is no room to add the item.");
    }
  }

  /**
   * checks to see if the parameter matches any of the item names
   * @param item name of the item
   * @return true if matches
   */
  public boolean inInventory(String item) {
    for (Item c : items) {
      if (c.getName().toLowerCase().equals(item.toLowerCase()))
        return true;
    }
    return false;
  }

  /**
   * attempts to drop an item from the players inventory and decreases currentWeight
   * @param item the item being dropped
   */
  public void dropPlayerItem(Item item) {
    if (inInventory(item.getName().toLowerCase())) {
          currentWeight -= item.getWeight();
          items.remove(item);
          System.out.println(item.getName() + " has been removed from your inventory. ");
        }else
    System.out.println("Can't drop something you dont have. ");
  }

  /**
   * attempts to drop an item from the rooms inventory and decreases currentWeight
   * @param item the item being dropped
   */
  public void dropRoomItem(Item item) {
    if (inInventory(item.getName().toLowerCase())) {
          currentWeight -= item.getWeight();
          items.remove(item);
        }else
    System.out.println("Can't drop something you dont have. ");
  }

  /**
   * attempts to drop an item from the players inventory and decreases currentWeight
   * @param item the name of the item being dropped
   */
  public void dropItem(String item) {
    if (inInventory(item)) {
      for (int i = 0; i < items.size(); i++) {
        Item toRemove = items.get(i);
        if (toRemove.getName().equals(item)) {
          currentWeight -= items.get(i).getWeight();
          items.remove(i);
          System.out.println(item + " has been removed from your inventory. ");
        }
      }
    }
    System.out.println("Can't drop something you dont have. ");
  }

  /**
   * Counts the number of a specified item in the player's inventory.
   * @param item name of the item being searched for.
   * @return # of times the item appears in the inventory
   */
  public int countItem(String item) {
    int count = 0;
    for(Item i : items){
      if(i.getName().equals(item))
        count++;
    }
    return count;
  }

  /* displays the name of all items in the players inventory */
  public void displayPlayerInventory() {
    System.out.println("Player Inventory: ");
    for (Item item : items) {
      System.out.println(item.getName() + " | Weight: " + item.getWeight());
    }
  }

  /*displays the name of all items in the rooms inventory */
  public void displayRoomInventory() {
    System.out.println("Room Inventory: ");
    for (Item item : items) {
      System.out.println(item.getName() + " | Weight: " + item.getWeight());
    }
  }
}