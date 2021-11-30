package Zorkgreus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game {

  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();

  private Parser parser;
  private Room currentRoom;

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    try {
      initRooms("src\\Zorkgreus\\data\\rooms.json");
      currentRoom = roomMap.get("Bedroom");
      initBoons("src\\Zorkgreus\\data\\boons.json");
    } catch (Exception e) {
      e.printStackTrace();
    }
    parser = new Parser();
  }

  private void initRooms(String fileName) throws Exception {
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonRooms = (JSONArray) json.get("rooms");

    for (Object roomObj : jsonRooms) {
      Room room = new Room();
      String roomName = (String) ((JSONObject) roomObj).get("name");
      String roomId = (String) ((JSONObject) roomObj).get("id");
      String roomDescription = (String) ((JSONObject) roomObj).get("description");
      room.setDescription(roomDescription);
      room.setRoomName(roomName);

      JSONArray jsonExits = (JSONArray) ((JSONObject) roomObj).get("exits");
      ArrayList<Exit> exits = new ArrayList<Exit>();
      for (Object exitObj : jsonExits) {
        String direction = (String) ((JSONObject) exitObj).get("direction");
        String adjacentRoom = (String) ((JSONObject) exitObj).get("adjacentRoom");
        String keyId = (String) ((JSONObject) exitObj).get("keyId");
        Boolean isLocked = (Boolean) ((JSONObject) exitObj).get("isLocked");
        Boolean isOpen = (Boolean) ((JSONObject) exitObj).get("isOpen");
        Exit exit = new Exit(direction, adjacentRoom, isLocked, keyId, isOpen);
        exits.add(exit);
      }
      room.setExits(exits);
      roomMap.put(roomId, room);
    }
  }

  /**
   * Initiates the boons.
   * 
   * @param file boons.json file
   */
  private void initBoons(String file) throws Exception {
    Path path = Path.of(file);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonBoons = (JSONArray) json.get("boons");

    for (Object boonObj : jsonBoons) {
      Boon boon = new Boon();
      String godName = (String) ((JSONObject) boonObj).get("god");
      String boonName = (String) ((JSONObject) boonObj).get("name");
      String decorativeText = (String) ((JSONObject) boonObj).get("colour");
      String stat = (String) ((JSONObject) boonObj).get("stat");
      int level = Math.toIntExact((Long) ((JSONObject) boonObj).get("level"));
      boon.setGod(godName);
      boon.setBoonName(boonName);
      boon.setColour(decorativeText);
      boon.setStats(stat);
      boon.setLevel(level);
    }
  }

  /**
   * Main play routine. Loops until end of play.
   */
  public void play() {
    printWelcome();

    boolean finished = false;
    while (!finished) {
      Command command;
      try {
        command = parser.getCommand();
        finished = processCommand(command);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    System.out.println("Thank you for playing.  Good bye.");
  }

  /**
   * Print out the opening message for the player.
   */
  private void printWelcome() {
    System.out.println();
    System.out.println("Welcome to Zork!");
    System.out.println("Zork is a new, incredibly boring adventure game.");
    System.out.println("Type 'help' if you need help.");
    System.out.println();
    System.out.println(currentRoom.longDescription());
  }

  /**
   * Given a command, process (that is: execute) the command. If this command ends
   * the game, true is returned, otherwise false is returned.
   */
  private boolean processCommand(Command command) {
    if (command.isUnknown()) {
      System.out.println("I don't know what you mean...");
      return false;
    }

    String commandWord = command.getCommandWord();
    if (commandWord.equals("help")) {
      printHelp();
    } else if (commandWord.equals("go")) {
      goRoom(command);
    } else if (commandWord.equals("quit")) {
      if (command.hasSecondWord())
        System.out.println("Quit what?");
      else
        return true; // signal that we want to quit
    } else if (commandWord.equals("eat")) {
      System.out.println("Do they even have food in the underworld?");
    } else if (commandWord.equals("east")) {
      goRoom(command);
    } else if (commandWord.equals("west")) {
      goRoom(command);
    } else if (commandWord.equals("north")) {
      goRoom(command);
    } else if (commandWord.equals("south")) {
      goRoom(command);
    } else if (commandWord.equals("attack")) {
      attackType(command);
    } else if (commandWord.equals("normal")) {
      attackType(command);
    } else if (commandWord.equals("special")) {
      attackType(command);
    } else if (commandWord.equals("look")) {
      currentRoom.longDescription();
    } else if (commandWord.equals("take")) {
      attemptToTake(command);
    } else if (commandWord.equals("takeall")) {
      attemptToTake(command);
    } else if (commandWord.equals("jump")) {
      int msg = (int) (Math.random() * 3);
      if (msg == 0) {
        System.out.println("Woohoo!");
      }
      if (msg == 1) {
        System.out.println("Don't break the floor.");
      }
      if (msg == 2) {
        System.out.println("The enemies are just as confused as I am.");
      }
    } else if (commandWord.equals("run")) {
      int msg = (int) (Math.random() * 3);
      if (msg == 0) {
        System.out.println("You ran!... into a wall.");
      }
      if (msg == 1) {
        System.out.println("It's dangerous to go alone!");
      }
      if (msg == 2) {
        System.out.println("Getting tired yet?");
      }
    } else if (commandWord.equals("slap")) {
      int msg = (int) (Math.random() * 3);
      if (msg == 0) {
        System.out.println("I think you just made it angrier.");
      }
      if (msg == 1) {
        System.out.println("That did nothing...");
      }
      if (msg == 2) {
        System.out.println("Why?");
      }
    } else if (commandWord.equals("cry")) {
      int msg = (int) (Math.random() * 3);
      if (msg == 0) {
        System.out.println("The underworld feeds off your tears.");
      }
      if (msg == 1) {
        System.out.println(
            "Sun Tzu once said: 'The supreme art of war is to subdue the enemy without fighting'. I don't think it's working.");
      }
      if (msg == 2) {
        System.out.println("Someone cutting onions?");
      }
    } else if (commandWord.equals("1") || commandWord.equals("one")) {
      // check if player is on a boon screen
    } else if (commandWord.equals("2") || commandWord.equals("two")) {
      // check if player is on a boon screen
    } else if (commandWord.equals("3") || commandWord.equals("three")) {
      // check if player is on a boon screen
    }
    return false;
  }

  // implementations of user commands:

  /**
   * Print out some help information. Here we print some stupid, cryptic message
   * and a list of the command words.
   */
  private void printHelp() {
    System.out.println("Get out of hell: a daunting dask. Many have tried, none have succeeded.");
    System.out.println("You need to use all the tools at your disposal in order to escape.");
    System.out.println("In front of you are four weapons, the sword, bow, spear, and shield.");
    System.out.println("Good luck. May the gods be on your side.");
    System.out.println();
    System.out.println("Your command words are:");
    parser.showCommands();
  }

  private void attackType(Command command) {
    if (!command.hasSecondWord() || command.getCommandWord() == "attack") {
      System.out.println("Are you doing a normal or special attack?");
      return;
    }

    if (command.getSecondWord() == "normal" || command.getCommandWord() == "normal") {
      // normalAttack();
    } else if (command.getSecondWord() == "special" || command.getCommandWord() == "special") {
      // specialAttack();
    }
  }

  private void attemptToTake(Command command) {

  }

  /**
   * Try to go to one direction. If there is an exit, enter the new room,
   * otherwise print an error message.
   */
  private void goRoom(Command command) {
    if (!command.hasSecondWord() && (command.getCommandWord() != "east" || command.getCommandWord() != "west"
        || command.getCommandWord() != "north" || command.getCommandWord() != "south")) {
      // if there is no second word, we don't know where to go...
      System.out.println("Go where?");
      return;
    }

    String direction = command.getSecondWord();

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);

    if (nextRoom == null)
      System.out.println("There is no door!");
    else {
      currentRoom = nextRoom;
      System.out.println(currentRoom.longDescription());
    }
  }
}