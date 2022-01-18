package Zorkgreus;

import java.util.ArrayList;

public class Room {

    private String roomName;
    private String id;
    private ArrayList<Exit> exits;
    private ArrayList<String> descriptions;
    private Inventory inventory;

    public Room(String description) {
        exits = new ArrayList<Exit>();
        descriptions = new ArrayList<String>();
    }

    public Room() {
        roomName = "DEFAULT ROOM";
        id = "F6R9";
        exits = new ArrayList<Exit>();
        descriptions = new ArrayList<String>();
        inventory = new Inventory(69420);
    }

    /*accessor and mutator methods for Exits */
    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setExits(ArrayList<Exit> exits) {
        this.exits = exits;
    }

    public void addExit(Exit exit) throws Exception {
        exits.add(exit);
    }

    /*accessor and mutator methods for room name */
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }  

    public void setDescriptions(ArrayList<String> descriptions) {
        this.descriptions = descriptions;
    }

    /*accessor and mutator methods for room ID */
    public String getRoomId() {
        return id;
    }

    public void setRoomId(String id) {
        this.id = id;
    }

    /*accessor and mutator methods for inventory */
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> items) {
    }

    /*Return the description of the room (the one that was defined in the constructor). */
    public String roomDescription() {
        int i = (int) (Math.random() * descriptions.size());
        String description = descriptions.get(i);
        return "Room: " + roomName + "\n\n" + description;
    }

    
    public void setDescription(String description) {
        descriptions = new ArrayList<String>();
    }

    /* Return a string describing the room's exits */
    private String exitString() {
        String returnString = "Exits: ";
        for (Exit exit : exits) {
            returnString += exit.getDirection() + " ";
        }

        return returnString;
    }

    /** Return the room that is reached if we go from this room in direction "direction". If there is no room in that direction, return null. */
    public Room nextRoom(String direction) {
        try {
            for (Exit exit : exits) {

                if (exit.getDirection().equalsIgnoreCase(direction)) {
                    String adjacentRoom = exit.getAdjacentRoom();

                    return Game.roomMap.get(adjacentRoom);
                }

            }
        } catch (IllegalArgumentException ex) {
            System.out.println(direction + " is not a valid direction.");
            return null;
        }

        System.out.println(direction + " is not a valid direction.");
        return null;
    }
}
