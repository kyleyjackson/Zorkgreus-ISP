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

  public boolean addItem(Item item) {
    if (item.getWeight() + currentWeight <= maxWeight)
      return items.add(item);
    else {
      System.out.println("There is no room to add the item.");
      return false;
    }
  }

  /**
   * Checks to see if the parameter matches any of the item names
   * @param item name of the item
   * @return true if matches
   */
  public boolean inInvetory(String item) {
    for (Item c : items) {
      if (c.getName().equals(item))
        return true;
    }
    return false;
  }

  public boolean dropItem(String item) {
    if (inInvetory(item)) {
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
}