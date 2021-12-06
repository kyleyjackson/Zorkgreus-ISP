package Zorkgreus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Zorkgreus.Boss.*;

public class Game {

  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();

  private Parser parser;
  private Room currentRoom;
  private Boss currentBoss;

  private ArrayList<Boon> boons; //where all initialized boons are stored.
  private ArrayList<Boon> temp; //stores boons temporarily for player selection.
  private ArrayList<Boon> myBoons; //active boons obtained by the player.
  
  private int bossCounter; //tracks # of bosses/minibosses beaten.

  private boolean generatedBoons; //global boolean to determine if boons have been generated. 

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    try {
      initRooms("src\\Zorkgreus\\data\\rooms.json");
      initBoons("src\\Zorkgreus\\data\\boons.json");
      currentRoom = roomMap.get("Spawn Room");
      currentBoss = new demolisionistSkeletons();
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
     // String roomDescription = (String) ((JSONObject) roomObj).get("description");
      //room.setDescription(roomDescription);
      room.setRoomName(roomName);
      // make room description like exits using the array 

      JSONArray jsonDescriptions = (JSONArray) ((JSONObject) roomObj).get("descriptions");
      ArrayList<String> descriptions = new ArrayList<String>();
      for (Object descObj : jsonDescriptions) {
        String desc = (String) descObj;
        descriptions.add(desc);
      }
      room.setDescriptions(descriptions);

      JSONArray jsonExits = (JSONArray) ((JSONObject) roomObj).get("exits");
      ArrayList<Exit> exits = new ArrayList<Exit>();
      for (Object exitObj : jsonExits) {
        String direction = (String) ((JSONObject) exitObj).get("direction");
        String adjacentRoom = (String) ((JSONObject) exitObj).get("adjacentRoom");
        Exit exit = new Exit(direction, adjacentRoom);
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
    boons = new ArrayList<Boon>();
    Path path = Path.of(file);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonBoons = (JSONArray) json.get("boons");

    for (Object boonObj : jsonBoons) {
      Boon boon = new Boon();
      String godName = (String) ((JSONObject) boonObj).get("god");
      String boonName = (String) ((JSONObject) boonObj).get("name");
      String colour = (String) ((JSONObject) boonObj).get("colour");
      String decorativeText = (String) ((JSONObject) boonObj).get("flavour");
      String stat = (String) ((JSONObject) boonObj).get("stat");
      int level = Math.toIntExact((Long) ((JSONObject) boonObj).get("level"));
      boon.setGod(godName);
      boon.setBoonName(boonName);
      boon.setColour(colour);
      boon.setFlavour(decorativeText);
      boon.setStats(stat);
      boon.setLevel(level);
      boons.add(boon);
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
        if(!generatedBoons && onBoonScreen()){
          temp = generateBoons();
          generatedBoons = true;
        }
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
    System.out.println(currentRoom.roomDescription());
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
      currentRoom.roomDescription();
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
        System.out.println("OMG YOU DISMEMBERED THE ENEMY...\n\njk lol");
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
    } else if(commandWord.equals("boonlist") || commandWord.equals("myboons")){
      formatMyBoons();
    }
    else if (commandWord.equals("boon")){
      if(onBoonScreen()){
        if(command.hasSecondWord()){
          if(command.getSecondWord().equals("one") || command.getSecondWord().equals("1")){ 
            temp.get(0); //gets value of first boon
            myBoons.add(temp.get(0)); //adds to the end of the myBoons ArrayList
            System.out.println("You selected Boon: " + temp.get(0).getBoonName());
            currentRoom.forceRoom(); //forces the player to the next room
          }
          else if(command.getSecondWord().equals("two") || command.getSecondWord().equals("2")){
            temp.get(1);
            myBoons.add(temp.get(1));
            System.out.println("You selected Boon: " + temp.get(1).getBoonName());
            currentRoom.forceRoom();
          }
          else if(command.getSecondWord().equals("three") || command.getSecondWord().equals("3")){
            temp.get(2);
            myBoons.add(temp.get(1));
            System.out.println("You selected Boon: " + temp.get(2).getBoonName());
            currentRoom.forceRoom();
          }
        }
        else{
          System.out.println("Which boon do you wish to receive?");
        }
      }
      else{
        System.out.println("You can't select a boon right now!");
      }
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

  /**
   * Sees what kind of attack the player will perform.
   * @param command
   */
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

  /**
   * Formats the myBoons ArrayList and displays them.
   */
  private void formatMyBoons() {
    
  }

  /**
   * Randomly choose 3 boons of the same god for player to choose (excluding Chaos).
   */
  public ArrayList<Boon> generateBoons() {
    ArrayList<Boon> selection = new ArrayList<>();
    int num = (int)(Math.random() * (boons.size() - 6));
    if(num <= 2){ //Ares
      selection.add(boons.get(0));
      selection.add(boons.get(1));
      selection.add(boons.get(2));
    } else if(num > 2 && num <= 5){ //Artemis
      selection.add(boons.get(3));
      selection.add(boons.get(4));
      selection.add(boons.get(5));
    } else if(num > 5 && num <= 8){ //Aphrodite
      selection.add(boons.get(6));
      selection.add(boons.get(7));
      selection.add(boons.get(8));
    } else if(num > 8 && num <= 11){ //Zeus
      selection.add(boons.get(9));
      selection.add(boons.get(10));
      selection.add(boons.get(11));
    } else if(num > 11 && num <= 14){ //Poseidon
      selection.add(boons.get(12));
      selection.add(boons.get(13));
      selection.add(boons.get(14));
    } else { //Athena
      selection.add(boons.get(15));
      selection.add(boons.get(16));
      selection.add(boons.get(17));
    }
    System.out.println("\n" + selection.get(0).getColour() + "Please select one of the boons:");
    System.out.print("----------------------------------------------------------------------------------------------------------");
    System.out.println(selection.get(0).displayBoon() + selection.get(1).displayBoon() + selection.get(2).displayBoon());
    return selection;
  }

  /**
   * checks if the currentBoss has been defeated. Changes currentBoss to the next miniboss/boss.
   * @return T/F
   */
  private boolean currentBossDefeated(){
    if(currentBoss.isAlive()){
      if(bossCounter == 0){
        currentBoss = new kingSkeleton();
        bossCounter++;
        generatedBoons = false;
      }
      if(bossCounter == 1){
        currentBoss = new Tarantula();
        bossCounter++;
        generatedBoons = false;
      }
      if(bossCounter == 2){
        currentBoss = new queenSpider();
        bossCounter++;
        generatedBoons = false;
      }
      if(bossCounter == 3){
        //chained flesh
        bossCounter++;
        generatedBoons = false;
      }
      if(bossCounter == 4){
        //sealed and unsealed thanatos
      }
      return true;
    }
    return false;
  }

  private void attemptToTake(Command command) {

  }

  /**
   * Check if you are able to receive a boon.
   * @return T/F
   */
  private boolean onBoonScreen(){
    if(currentRoom.getRoomName().equals("Boon Room") || currentBossDefeated()){
      return true;
    }
    return false;
  }

  /**
   * Try to go to one direction. If there is an exit, enter the new room,
   * otherwise print an error message.
   */
  private void goRoom(Command command) {
    boolean twoWords = false;
    //if the command doesn't have a second word AND it isn't a direction
    if (!command.hasSecondWord() && !(command.getCommandWord().equals("east") || command.getCommandWord().equals("west")
        || command.getCommandWord().equals("north") || command.getCommandWord().equals("south"))) {
      // if there is no second word, we don't know where to go...
      System.out.println("Go where?");
      return;
    }

    if(command.hasSecondWord()){
      twoWords = true;
    }

    String direction;

    //gets intended direction based on whether or not "go" was entered prior
    if(twoWords){
      direction = command.getSecondWord();
    }
    else{
      direction = command.getCommandWord();
    }

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);

    if (nextRoom == null)
      System.out.println("There is no door!");
    else {
      currentRoom = nextRoom;
      System.out.println(currentRoom.roomDescription());
    }
  }
}