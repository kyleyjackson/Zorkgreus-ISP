package Zorkgreus;

import java.util.ArrayList;

public class Room {

    private String roomName;
    private ArrayList<Exit> exits;
    private ArrayList<String> descriptions;
    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setExits(ArrayList<Exit> exits) {
        this.exits = exits;
    }

     public void setDescriptions(ArrayList<String> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     */

    public Room(String description) {
        exits = new ArrayList<Exit>();
        descriptions = new ArrayList<String>();
    }

    public Room() {
        roomName = "DEFAULT ROOM";
        exits = new ArrayList<Exit>();
        descriptions = new ArrayList<String>();
    }

    public void addExit(Exit exit) throws Exception {
        exits.add(exit);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String roomDescription() {
        int i = (int)(Math.random() * descriptions.size());
        String description = descriptions.get(i);
        return "Room: " + roomName + "\n\n" + description;
    }
// got rid of long description and use math.random to randomize the description to pick one 

    /**
     * Return a string describing the room's exits, for example "Exits: north west
     * ".
     */
    private String exitString() {
        String returnString = "Exits: ";
        for (Exit exit : exits) {
            returnString += exit.getDirection() + " ";
        }

        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
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

    /*
     * private int getDirectionIndex(String direction) { int dirIndex = 0; for
     * (String dir : directions) { if (dir.equals(direction)) return dirIndex; else
     * dirIndex++; }
     * 
     * throw new IllegalArgumentException("Invalid Direction"); }
     */
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setDescription(String description) {
        descriptions = new ArrayList<String>();
    }

}
