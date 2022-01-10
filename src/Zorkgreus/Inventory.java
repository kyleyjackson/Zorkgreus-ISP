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

  public int getMaxWeight() {
    return maxWeight;
  }

  public int getCurrentWeight() {
    return currentWeight;
  }

  public void setMaxWeight(int extra) {
    maxWeight += extra;
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

  public boolean addItem(Item item) {
    if (item.getWeight() + currentWeight <= maxWeight)
      return items.add(item);
    else {
      System.out.println("There is no room to add the item.");
      return false;
    }
  }

  /**
   * checks to see if the parameter matches any of the item names
   *
   * @param item name of the item
   * @return true if matches
   */
  public boolean inInventory(String item) {
    for (Item c : items) {
      if (c.getName().equals(item))
        return true;
    }
    return false;
  }

  /**
   * attempts to drop an item
   *
   * @param item name of the item dropped
   * @return true if you drop the item
   */
  public boolean dropItem(String item) {
    if (inInventory(item)) {
      for (int i = 0; i < items.size(); i++) {
        Item toRemove = items.get(i);
        if (toRemove.getName().equals(item)) {
          items.remove(i);
          System.out.println(item + " has been removed from your inventory. ");
          return true;
        }
      }
    }
    System.out.println("Can't drop something you dont have. ");
    return false;
  }

  /**
   * Counts the number of a specified item in the player's inventory.
   * 
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

  /**
   * displays the name of all items in inventory
   */
  public void displayInventory() {
    for (Item item : items) {
      System.out.println(item.getName());
    }
  }

  public void setInventory(ArrayList<Item> items) {
    this.items = items;
  }
}