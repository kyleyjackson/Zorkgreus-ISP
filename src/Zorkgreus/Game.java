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
import Zorkgreus.Monsters.Monsters;
import Zorkgreus.NPC.*;
import Zorkgreus.Weapons.Weapons;

public class Game {

  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();

  /*------------------------------------Objects------------------------------------*/
  private Parser parser;
  private Room currentRoom;
  private Boss currentBoss;
  private Weapons currentWeapon;
  private Monsters currentMonster;
  private NPC currentNPC;
  private Player fred; // player to execute commands that deal with player

  /*------------------------------------ArrayLists------------------------------------*/
  private ArrayList<Boon> boons = new ArrayList<>(); // where initialized boons are stored.
  private ArrayList<Boon> temp = new ArrayList<>(); // stores boons temporarily for player selection.
  private ArrayList<Boon> myBoons = new ArrayList<>(); // active boons obtained by the player.
  private ArrayList<Monsters> monsters = new ArrayList<>(); // where initialized monsters are stored.
  private ArrayList<Weapons> weapons = new ArrayList<>(); // where initialized weapons are stored.
  private ArrayList<Item> items = new ArrayList<>(); // where initialized items are stored.
  private ArrayList<String> fightRooms = new ArrayList<>(); // keeps track of rooms where you've fought before.

  /*------------------------------------global ints------------------------------------*/
  private int bossCounter; // tracks # of bosses/minibosses beaten.
  private int recEnemyHit; //value of damage done by monster/boss most recent hit.
  private int recPlayerHit; //value of damage done by player's most recent hit.
  private int enemyHP; // enemy hp
  private int speAtkCounter; // used to make sure you cannot special attack too often

  /*------------------------------------global booleans------------------------------------*/
  private boolean generatedBoons; // determine if boons have been generated.
  private boolean boonSelected; // checks if the player has selected a boon.
  private boolean weaponSelected; // checks if a weapon has been selected.
  private boolean canProceed; // determines if player can move on to the next room.
  private boolean getCurrentRoom; //used for setting the healing from hydralite gold.
  private boolean setNPC = false; //determines if an NPC has been generated.
  private boolean isFighting = false; //checks if you're currently in combat.
  boolean isBoss = false; // checks if you're in combat with a boss.
  boolean isMonster = false; // checks if you're in combat with a monster.
  private boolean determineItems;
  private boolean monsterDrop;
  private boolean takeAll;
  boolean isEnraged = false; // checks if the boss is currently in a state of rage
  boolean isDead = false; // checks if you're dead

  /*------------------------------------global strings------------------------------------*/
  private String prevCommand = ""; // stores the previous command inputted by player.
  private String prevRoom; //stores the previous room.

  /*------------------------------------coloured font------------------------------------*/
  public static final String RED = "\033[1;91m";
  public static final String RESET = "\033[0m";
  public static final String WHITEBG = "\033[0;100m";
  public static final String CYAN = "\033[1;96m";

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    try {
      initRooms("src\\Zorkgreus\\data\\rooms.json");
      initBoons("src\\Zorkgreus\\data\\boons.json");
      initMonsters("src\\Zorkgreus\\data\\monsters.json");
      initWeapons("src\\Zorkgreus\\data\\weapons.json");
      initItems("src\\Zorkgreus\\data\\items.json");
      currentRoom = roomMap.get("Spawn Room");
      currentBoss = new DemolisionistSkeleton();
      fred = new Player(0, 0, 0);
      getCurrentRoom = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    parser = new Parser();
  }

  /**
   * Initializes the rooms.
   *
   * @param fileName rooms.json file
   */
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
      room.setRoomName(roomName);
      room.setRoomId(roomId);

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
   * Initalizes the boons.
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

      String god = (String) ((JSONObject) boonObj).get("god");
      String boonName = (String) ((JSONObject) boonObj).get("name");
      String colour = (String) ((JSONObject) boonObj).get("colour");
      String decorativeText = (String) ((JSONObject) boonObj).get("flavour");
      String stats = (String) ((JSONObject) boonObj).get("stats");
      int level = Math.toIntExact((Long) ((JSONObject) boonObj).get("level"));

      Boon boon = new Boon(god, boonName, colour, decorativeText, stats, level);
      boons.add(boon);
    }
  }

  /**
   * Initalizes the monsters.
   *
   * @param file monsters.json file
   */
  private void initMonsters(String file) throws Exception {
    Path path = Path.of(file);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonMonsters = (JSONArray) json.get("monsters");

    for (Object monsterObj : jsonMonsters) {
      String name = (String) ((JSONObject) monsterObj).get("Monster");
      int atk = Math.toIntExact((Long) ((JSONObject) monsterObj).get("attack"));
      int prio = Math.toIntExact((Long) ((JSONObject) monsterObj).get("priority"));
      int def = Math.toIntExact((Long) ((JSONObject) monsterObj).get("defense"));
      int hp = Math.toIntExact((Long) ((JSONObject) monsterObj).get("health"));
      int dodge = Math.toIntExact((Long) ((JSONObject) monsterObj).get("dodge"));
      String desc = (String) ((JSONObject) monsterObj).get("description");
      String location = (String) ((JSONObject) monsterObj).get("location"); // the room this monster is located in, matched with room id

      Monsters monster = new Monsters(name, atk, prio, def, hp, hp, dodge, desc, location);
      monsters.add(monster);
    }
  }

  /**
   * Initializes the weapons.
   *
   * @param file weapons.json file
   */
  private void initWeapons(String file) throws Exception {
    Path path = Path.of(file);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonWeapons = (JSONArray) json.get("weapons");

    for (Object weaponObj : jsonWeapons) {
      String weaponName = (String) ((JSONObject) weaponObj).get("Weapon");
      int prio = Math.toIntExact((Long) ((JSONObject) weaponObj).get("Priority"));
      int atk = Math.toIntExact((Long) ((JSONObject) weaponObj).get("Attack"));
      int def = Math.toIntExact((Long) ((JSONObject) weaponObj).get("Defense"));
      int speAtkDmg = Math.toIntExact((Long) ((JSONObject) weaponObj).get("SpecialAttackDamage"));
      int id = Math.toIntExact((Long) ((JSONObject) weaponObj).get("id"));
      String speAtkName = (String) ((JSONObject) weaponObj).get("SpecialAttackName");
      String desc = (String) ((JSONObject) weaponObj).get("Description");

      Weapons weapon = new Weapons(weaponName, prio, atk, def, speAtkDmg, id, speAtkName, desc);
      weapons.add(weapon);
    }
  }

  /**
   * Intializes the items.
   *
   * @param file items.json file
   */
  private void initItems(String file) throws Exception {
    Path path = Path.of(file);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonItems = (JSONArray) json.get("items");

    for (Object itemObj : jsonItems) {
      String name = (String) ((JSONObject) itemObj).get("name");
      String desc = (String) ((JSONObject) itemObj).get("description");
      int weight = Math.toIntExact((Long) ((JSONObject) itemObj).get("weight"));
      String startRoom = (String) ((JSONObject) itemObj).get("startingRoom");
      int gold = Math.toIntExact((Long) ((JSONObject) itemObj).get("gold"));

      Item item = new Item(name, desc, weight, startRoom, gold);
      items.add(item);

    }
  }

  /**
   * Main play routine. Loops until end of play.
   */
  public void play() {
    printWelcome();

    weaponSelection(null, true);

    boolean finished = false;
    boolean finishedFighting = false;
    while (!finished) {
      Command command;
      try {
        for (int i = 0; i < monsters.size(); i++) {
          if (monsters.get(i).getLocation().equals(currentRoom.getRoomId())) {
            currentMonster = monsters.get(i);
            break;
          }
        }
        if(currentRoom.getRoomName().indexOf("NPC") == -1 || currentRoom.getRoomName().indexOf("Shop") == -1)
          setNPC = false;
        setCurrentNPC();
        if (fred.getHydraliteGold()) {
          hydraliteGold();
        }
        if(currentRoom.getInventory().getCurrentWeight()>0)
          takeAll = false;
        if(currentRoom.getInventory().getCurrentWeight()==0)
          determineItems = true;
        predertimineItems();
        if(currentMonster!=null){
          if(!currentMonster.isAlive())
            monsterDrop = true;
            if(currentRoom.getInventory().getCurrentWeight()>30)
              monsterDrop = false;
        determineMonsterDrop();
        }

        if (!generatedBoons && onBoonScreen()) {
          temp = generateBoons(false);
          generatedBoons = true;
        }
        if (!(currentRoom.getRoomName().equals("Boon Room") || currentRoom.getRoomName().equals("Miniboss Room")
        || currentRoom.getRoomName().equals("Boss Room")))
          boonSelected = false;
        if (boonSelected)
          System.out.println("Please proceed to the next room.");

        command = parser.getCommand();

        if (isFighting) {
          finishedFighting = processFightCommand(command);
          if(finishedFighting) {
            System.out.println("You left combat.");
            isFighting = false;
            isBoss = false;
            isMonster = false;
            fightRooms.add(currentRoom.getRoomId());
            speAtkCounter = 0;
          }
          finishedFighting = false;
        } else if (!isFighting) {
          finished = processCommand(command);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Thank you for playing. Goodbye.");
  }

  /**
   * Print out the opening message for the player.
   */
  public void printWelcome() {
    System.out.println("");
    slowTextNoDelay(CYAN
        + "\n--------------------------------------------------WELCOME TO--------------------------------------------------\n",
        8);
    System.out.println("");
    slowTextNoDelay(RESET + RED
        + " ▄███████▄   ▄██████▄     ▄████████    ▄█   ▄█▄    ▄██████▄     ▄████████    ▄████████ ███    █▄     ▄████████  ",
        1);
    System.out.println("");
    slowTextNoDelay(RED
        + "██▀     ▄██ ███    ███   ███    ███   ███ ▄███▀   ███    ███   ███    ███   ███    ███ ███    ███   ███    ███ ",
        1);
    System.out.println("");
    slowTextNoDelay(RED
        + "      ▄███▀ ███    ███   ███    ███   ███▐██▀     ███    █▀    ███    ███   ███    █▀  ███    ███   ███    █▀  ",
        1);
    System.out.println("");
    slowTextNoDelay(RED
        + " ▀█▀▄███▀▄▄ ███    ███  ▄███▄▄▄▄██▀  ▄█████▀     ▄███         ▄███▄▄▄▄██▀  ▄███▄▄▄     ███    ███   ███        ",
        1);
    System.out.println("");
    slowTextNoDelay(RED
        + "  ▄███▀   ▀ ███    ███ ▀▀███▀▀▀▀▀   ▀▀█████▄    ▀▀███ ████▄  ▀▀███▀▀▀▀▀   ▀▀███▀▀▀     ███    ███ ▀███████████ ",
        1);
    System.out.println("");
    slowTextNoDelay(RED
        + "▄███▀       ███    ███ ▀███████████   ███▐██▄     ███    ███ ▀███████████   ███    █▄  ███    ███          ███ ",
        1);
    System.out.println("");
    slowTextNoDelay(RED
        + "███▄     ▄█ ███    ███   ███    ███   ███ ▀███▄   ███    ███   ███    ███   ███    ███ ███    ███    ▄█    ███ ",
        1);
    System.out.println("");
    slowTextNoDelay(RED
        + " ▀████████▀  ▀██████▀    ███    ███   ███   ▀█▀   ████████▀    ███    ███   ██████████ ████████▀   ▄████████▀  ",
        1);
    System.out.println("");
    System.out.println(
        RESET + CYAN
            + "\n--------------------------------------------------------------------------------------------------------------\n");
    slowText(RESET + "Zorkgreus is a knockoff of Hades :D", 25);
    System.out.println("");
    slowText("Type \"help\" if you need help.", 25);
    System.out.println("\n" + currentRoom.roomDescription());
  }

  public static void slowText(String message, int textRate) {
    // Print a char from the array, then sleep for 1/10 second
    for (int i = 0; i < message.length(); i++) {
      System.out.print(message.substring(i, i + 1));
      try {
        Thread.sleep(textRate);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void slowTextNoDelay(String message, int textRate) {
    // Print a char from the array, then sleep for 1/10 second
    for (int i = 0; i < message.length(); i++) {
      System.out.print(message.substring(i, i + 1));
      try {
        Thread.sleep(textRate);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
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
    if (commandWord.equals("help") && !(prevCommand.equals("bow") || prevCommand.equals("spear") 
    || prevCommand.equals("sword") || prevCommand.equals("shield"))) {
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
    } else if (commandWord.equals("east") || commandWord.equals("west") || commandWord.equals("north")
        || commandWord.equals("south")) {
      goRoom(command);
    } else if (commandWord.equals("look")) {
      System.out.println(currentRoom.roomDescription());
      currentRoom.getInventory().displayRoomInventory();
    } else if (commandWord.equals("display")) {
      if(!weaponSelected)
          System.out.println("Pick a weapon first!");
      else{
        if(command.hasSecondWord()){
          if(command.getSecondWord().equals("player")){
            System.out.println("Your stats: ");
            System.out.println("Weapon: " + currentWeapon.getName() + " | Special Attack: " + currentWeapon.getSpeAtkName() + " | Damage: " + currentWeapon.getSpeAtkDmg());
            System.out.println("HP: " + fred.getPlayerHP() + "/" + fred.getPlayerMaxHP());
            System.out.println("Attack: " + fred.getPlayerAtk());
            System.out.println("Priority: " + fred.getPlayerPrio());
            System.out.println("Defence: " + fred.getPlayerDef());
            System.out.println("Dodge Chance: " + fred.getPlayerDodgeChange());
            System.out.println("Gold: " + fred.getPlayerGold());
          } else if(command.getSecondWord().equals("boons")){
            formatMyBoons();
          } else if(command.getSecondWord().equals("enemy")){
            if(currentRoom.getRoomName().indexOf("Attack") > -1){
              currentMonster.monsterInfo();
            } else if(currentRoom.getRoomName().indexOf("oss") > -1){ //Check for oss instead of boss because indexOf is case-sensitive
              currentBoss.bossInfo();
            } else if(command.getSecondWord().equals("inventory")){
              fred.getInventory().displayPlayerInventory();
              fred.getInventory().displayWeight();
            } else {
              System.out.println("There's no enemy in the room!");
            }
          }
        } else
          System.out.println("What are you trying to display? (\"player\", \"enemy\", \"inventory\", \"boons\")");
      }
    } else if (commandWord.equals("take")) {
      attemptToTake(command);
    } else if (commandWord.equals("takeall")) {
      attemptToTake(command);
    } else if (commandWord.equals("drop")) {
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
        System.out.println("OMG YOU DISMEMBERED THE ENEMY...\n\n\n\n\n\njk lol");
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
        System.out.println("Sun Tzu once said: 'The supreme art of war is to subdue the enemy without fighting'. I don't think it's working.");
      }
      if (msg == 2) {
        System.out.println("Someone cutting onions?");
      }
    } else if (commandWord.equals("APCSP!") || commandWord.equals("APSCP")) {
      System.out.println("You really wanna fail computer science again?");
    } else if (commandWord.equals("boonlist") || commandWord.equals("myboons")) {
      formatMyBoons();
    } else if (commandWord.equals("b1") || commandWord.equals("b2") || commandWord.equals("b3")) {
      if (onBoonScreen() && !boonSelected) {
        if (commandWord.equals("b1")) {
          for (Boon b : myBoons) {
            b.levelUp(myBoons, temp, 0);
          }
          myBoons.add(temp.get(0)); // adds to the end of the myBoons ArrayList
          rawIncreases(temp.get(0));
          System.out.println("You selected Boon: " + temp.get(0).getBoonName());
          boonSelected = true;
        } else if (commandWord.equals("b2")) {
          for (Boon b : myBoons) {
            b.levelUp(myBoons, temp, 1);
          }
          myBoons.add(temp.get(1));
          rawIncreases(temp.get(1));
          System.out.println("You selected Boon: " + temp.get(1).getBoonName());
          boonSelected = true;
        } else if (commandWord.equals("b3")) {
          for (Boon b : myBoons) {
            b.levelUp(myBoons, temp, 2);
          }
          myBoons.add(temp.get(2));
          rawIncreases(temp.get(2));
          System.out.println("You selected Boon: " + temp.get(2).getBoonName());
          boonSelected = true;
        } else {
          System.out.println("Which boon do you wish to receive?");
        }
      } else {
        System.out.println("You can't select a boon right now!");
      }
    } else if (commandWord.equals("bow")) {
      if (weaponSelected)
        System.out.println("You've already selected a weapon. Please proceed to the next room.");
      else {
        if (command.hasSecondWord()) {
          if (command.getSecondWord().equals("help")) {
            System.out.println(weapons.get(0).getDescription() + "\n");
            System.out.println("Attack: " + weapons.get(0).getAtk() + "\n" + "Priority: " + weapons.get(0).getPriority()
                + "\n" + "Defense: " + weapons.get(0).getDef()
                + "\n" + "Special Attack: " + weapons.get(0).getSpeAtkName());
          } else if (command.getSecondWord().equals("confirm")) {
            currentWeapon = weaponSelection("bow", false);
            weaponSelected = true;
            fred = new Player(currentWeapon.getPriority(), currentWeapon.getAtk(), currentWeapon.getDef());
            System.out.println("You have selected the bow. Please proceed to the next room.");
          } else {
            System.out.println("Type \"help\" after the weapon to learn more about it.");
          }
        } else {
          System.out.println("Type \"help\" after the weapon to learn more about it.");
          System.out.println("Type \"confirm\" to confirm your selection.");
        }
      }
    } else if (commandWord.equals("spear")) {
      if (weaponSelected)
        System.out.println("You've already selected a weapon. Please proceed to the next room.");
      else {
        if (command.hasSecondWord()) {
          if (command.getSecondWord().equals("help")) {
            System.out.println(weapons.get(1).getDescription() + "\n");
            System.out.println("Attack: " + weapons.get(1).getAtk() + "\n" + "Priority: " + weapons.get(1).getPriority()
                + "\n" + "Defense: " + weapons.get(1).getDef()
                + "\n" + "Special Attack: " + weapons.get(1).getSpeAtkName());
          } else if (command.getSecondWord().equals("confirm")) {
            currentWeapon = weaponSelection("spear", false);
            weaponSelected = true;
            fred = new Player(currentWeapon.getPriority(), currentWeapon.getAtk(), currentWeapon.getDef());
            System.out.println("You have selected the spear. Please proceed to the next room.");
          } else {
            System.out.println("Type \"help\" after the weapon to learn more about it.");
          }
        } else {
          System.out.println("Type \"help\" after the weapon to learn more about it.");
          System.out.println("Type \"confirm\" to confirm your selection.");
        }
      }
    } else if (commandWord.equals("sword")) {
      if (weaponSelected)
        System.out.println("You've already selected a weapon. Please proceed to the next room.");
      else {
        if (command.hasSecondWord()) {
          if (command.getSecondWord().equals("help")) {
            System.out.println(weapons.get(2).getDescription() + "\n");
            System.out.println("Attack: " + weapons.get(2).getAtk() + "\n" + "Priority: " + weapons.get(2).getPriority()
                + "\n" + "Defense: " + weapons.get(2).getDef()
                + "\n" + "Special Attack: " + weapons.get(2).getSpeAtkName());
          } else if (command.getSecondWord().equals("confirm")) {
            currentWeapon = weaponSelection("sword", false);
            weaponSelected = true;
            fred = new Player(currentWeapon.getPriority(), currentWeapon.getAtk(), currentWeapon.getDef());
            System.out.println("You have selected the sword. Please proceed to the next room.");
          } else {
            System.out.println("Type \"help\" after the weapon to learn more about it.");
          }
        } else {
          System.out.println("Type \"help\" after the weapon to learn more about it.");
          System.out.println("Type \"confirm\" to confirm your selection.");
        }
      }
    } else if (commandWord.equals("shield")) {
      if (weaponSelected)
        System.out.println("You've already selected a weapon. Please proceed to the next room.");
      else {
        if (command.hasSecondWord()) {
          if (command.getSecondWord().equals("help")) {
            System.out.println(weapons.get(3).getDescription() + "\n");
            System.out.println("Attack: " + weapons.get(3).getAtk() + "\n" + "Priority: " + weapons.get(3).getPriority()
                + "\n" + "Defense: " + weapons.get(3).getDef()
                + "\n" + "Special Attack: " + weapons.get(3).getSpeAtkName());
          } else if (command.getSecondWord().equals("confirm")) {
            currentWeapon = weaponSelection("shield", false);
            weaponSelected = true;
            fred = new Player(currentWeapon.getPriority(), currentWeapon.getAtk(), currentWeapon.getDef());
            System.out.println("You have selected the shield. Please proceed to the next room.");
          } else {
            System.out.println("Type \"help\" after the weapon to learn more about it.");
          }
        } else {
          System.out.println("Type \"help\" after the weapon to learn more about it.");
          System.out.println("Type \"confirm\" to confirm your selection.");
        }
      }
    } else if(commandWord.equals("fight") || commandWord.equals("attack")) {
      if(fightRooms.contains(currentRoom.getRoomId()) || currentRoom.getRoomId().equals("Spawn Room") || currentRoom.getRoomId().equals("Boon Room") || currentRoom.getRoomId().equals("F1C1") || currentRoom.getRoomId().equals("Boom Room") || currentRoom.getRoomId().equals("F1 NPC Room") || currentRoom.getRoomId().equals("F1 Shop Room") || currentRoom.getRoomId().equals("F2 Starting Room") || currentRoom.getRoomId().equals("F2C2") || currentRoom.getRoomId().equals("F2 NPC Room") || currentRoom.getRoomId().equals("F2 Shop Room") || currentRoom.getRoomId().equals("F3 Starting Room") || currentRoom.getRoomId().equals("F3C3") || currentRoom.getRoomId().equals("F3 NPC Room") || currentRoom.getRoomId().equals("F3 Shop Room")) {
        int random = (int)(Math.random() * 4) + 1;
        if(random == 1) 
          System.out.println("Who are you gonna fight, the air?");
        else if(random == 2)
          System.out.println("Are you hallucinating?");
        else if(random == 3)
          System.out.println("What are you trying to fight?");
        else 
          System.out.println("Have you gone blind?");
      } else {
        if (currentRoom.getRoomName().indexOf("Boss Room") >= 0 || currentRoom.getRoomName().indexOf("Miniboss Room") >= 0) {
          isBoss = true;
          enemyHP = currentBoss.getHP();
        }else if (currentRoom.getRoomName().indexOf("Test Dummy Room") >= 0 || currentRoom.getRoomName().indexOf("Attack Room") >= 0) {
          isMonster = true;
          enemyHP = currentMonster.getHP();
        }
        isFighting = true;
        System.out.println("You've engaged in combat!");
      }
    } else if(commandWord.equals("normal") || commandWord.equals("special")){
      System.out.println("You can't use those without initiating a fight. (type \"fight\" or \"attack\"!");
    } else if (prevCommand != null) { // commands for the word entered the line before (i.e. instead of bow confirm, it'd be bow *break* confirm)
      if(prevCommand.equals("display")){
        if(!weaponSelected)
          System.out.println("Pick a weapon first!");
      else{
        if(commandWord.equals("player")){
          System.out.println("Your stats: ");
          System.out.println("Weapon: " + currentWeapon.getName());
          System.out.println("Special Attack: " + currentWeapon.getSpeAtkName() + " | " + currentWeapon.getSpeAtkDmg());
          System.out.println("HP: " + fred.getPlayerHP() + "/" + fred.getPlayerMaxHP());
          System.out.println("Attack: " + fred.getPlayerAtk());
          System.out.println("Priority: " + fred.getPlayerPrio());
          System.out.println("Defence: " + fred.getPlayerDef());
          System.out.println("Dodge Chance: " + fred.getPlayerDodgeChange());
          System.out.println("Gold: " + fred.getPlayerGold());
        } else if(commandWord.equals("boons")){
          formatMyBoons();
        } else if(commandWord.equals("enemy")){
          if(currentRoom.getRoomName().indexOf("Attack") > -1){
            currentMonster.monsterInfo();
          } else if(currentRoom.getRoomName().indexOf("oss") > -1){ //Check for oss instead of boss because indexOf is case-sensitive
            currentBoss.bossInfo();
          } else if(commandWord.equals("inventory")){
            fred.getInventory().displayPlayerInventory();
            fred.getInventory().displayWeight();
          } else {
            System.out.println("There's no enemy in the room!");
          }
        }
      }
      }else if (prevCommand.equals("bow")) {
        if (commandWord.equals("help")) {
          System.out.println(weapons.get(0).getDescription() + "\n");
          System.out.println("Attack: " + weapons.get(0).getAtk() + "\n" + "Priority: " + weapons.get(0).getPriority()
              + "\n" + "Defense: " + weapons.get(0).getDef()
              + "\n" + "Special Attack: " + weapons.get(0).getSpeAtkName() + " | "  + weapons.get(0).getSpeAtkDmg() + " damage");
        } else if (commandWord.equals("confirm")) {
          if (weaponSelected)
            System.out.println("You've already selected a weapon. Please proceed to the next room.");
          else {
            currentWeapon = weaponSelection("bow", false);
            weaponSelected = true;
            System.out.println("You have selected the bow. Please proceed to the next room.");
          }
        }
      } else if (prevCommand.equals("spear")) {
        if (commandWord.equals("help")) {
          System.out.println(weapons.get(1).getDescription() + "\n");
          System.out.println("Attack: " + weapons.get(1).getAtk() + "\n" + "Priority: " + weapons.get(1).getPriority()
              + "\n" + "Defense: " + weapons.get(1).getDef()
              + "\n" + "Special Attack: " + weapons.get(1).getSpeAtkName() + " | "  + weapons.get(1).getSpeAtkDmg() + " damage");
        } else if (commandWord.equals("confirm")) {
          if (weaponSelected)
            System.out.println(
                "You've already selected a weapon. Please proceed to the next room.");
          else {
            currentWeapon = weaponSelection("spear", false);
            weaponSelected = true;
            System.out.println("You have selected the spear. Please proceed to the next room.");
          }
        }
      } else if (prevCommand.equals("sword")) {
        if (commandWord.equals("help")) {
          System.out.println(weapons.get(2).getDescription() + "\n");
          System.out.println("Attack: " + weapons.get(2).getAtk() + "\n" + "Priority: " + weapons.get(2).getPriority()
              + "\n" + "Defense: " + weapons.get(2).getDef()
              + "\n" + "Special Attack: " + weapons.get(2).getSpeAtkName() + " | "  + weapons.get(2).getSpeAtkDmg() + " damage");
        } else if (commandWord.equals("confirm")) {
          if (weaponSelected)
            System.out.println(
                "You've already selected a weapon. Please proceed to the next room.");
          else {
            currentWeapon = weaponSelection("sword", false);
            weaponSelected = true;
            System.out.println("You have selected the sword. Please proceed to the next room.");
          }
        }
      } else if (prevCommand.equals("shield")) {
        if (commandWord.equals("help")) {
          System.out.println(weapons.get(3).getDescription() + "\n");
          System.out.println("Attack: " + weapons.get(3).getAtk() + "\n" + "Priority: " + weapons.get(3).getPriority()
              + "\n" + "Defense: " + weapons.get(3).getDef()
              + "\n" + "Special Attack: " + weapons.get(3).getSpeAtkName() + " | "  + weapons.get(3).getSpeAtkDmg() + " damage");
        } else if (commandWord.equals("confirm")) {
          if (weaponSelected)
            System.out.println(
                "You've already selected a weapon. Please proceed to the next room.");
          else {
            currentWeapon = weaponSelection("shield", false);
            weaponSelected = true;
            System.out.println("You have selected the shield. Please proceed to the next room.");
          }
        }
      } else
        System.out.println("That command doesn't work right now!");
    }else
      System.out.println("That command doesn't work right now!");
    prevCommand = command.getCommandWord();
    return false;
  }

  public boolean processFightCommand(Command command) {
      if (command.isUnknown()) {
        System.out.println("I don't know what you mean...");
        return false;
      }

      String commandWord = command.getCommandWord();

      boolean firstTurn = true; //first turn of combat
      boolean firstCrit = false;
      for(Boon b : myBoons){ //first strike
        if(b.getBoonName().equals("First Strike"))
          firstCrit = firstStrike();
      }

      if(commandWord.equals("special") || commandWord.equals("special attack")) {
        //*Damage the monster, check for HP, damage the player, check for HP
        if(fred.getPlayerPrio() > currentMonster.getPrio() || fred.getPlayerPrio() > currentBoss.getPrio()) {
          if(speAtkCounter > 0) { 
            System.out.println("You can't special attack yet! | " + speAtkCounter + " turn(s).");
            return false;
          }else{
            int dmg = fred.specialAttack(currentWeapon.getId());
            if(firstTurn && firstCrit)
              dmg *= 2;
            for(Boon b : myBoons){ //stormbreaker
              if(b.getBoonName().equals("Stormbreaker"))
                dmg += stormbreaker();
            }
            enemyHP -= dmg;
            System.out.println("You hit the " + currentMonster.getName() + " for " + dmg + " damage!");
            System.out.println();
            for(Boon b : myBoons){ //charm
              if(b.getBoonName().equals("Charm"))
                charm();
            }
            speAtkCounter += 3;
          }
          
          for(Boon b : myBoons){ //killing blow
            if(b.getBoonName().equals("Killing Blow"))
              killingBlow();
          }
          if(enemyHP < 1) {
            System.out.println("You won!");
            currentMonster.setHP(enemyHP);
            return true;
          } else if(isMonster == true) {
            int mdmg = currentMonster.monsterNormalAttack();
            recEnemyHit = mdmg;
            fred.addPlayerHP(-mdmg);
            for(Boon b : myBoons){ //heartbreaker & false weakness
              if(b.getBoonName().equals("Heartbreaker"))
                heartbreaker();
              if(b.getBoonName().equals("False Weakness"))
                falseWeakness();
            }
          }else {
            if(currentBoss.getHP() <= (currentBoss.getHP() / 3) && !isEnraged) {
              currentBoss.bossRage();
            }else {
              int bdmg = currentBoss.attack(currentBoss.getAtk());
              recEnemyHit = bdmg;
              fred.addPlayerHP(-bdmg);
              for(Boon b : myBoons){ //heartbreaker & false weakness
                if(b.getBoonName().equals("Heartbreaker"))
                  heartbreaker();
                if(b.getBoonName().equals("False Weakness"))
                  falseWeakness();
              }
            }
          }

          if(!fred.isAlive()) { //* Will implement dd later
            System.out.println("You died.");
            for(Boon b : myBoons){ //smite
              if(b.getBoonName().equals("Smite"))
                smite();
            }
            return true;
          }else {
            System.out.println("\n-------------------------------------------------------------------------\n");
            System.out.println("Your HP: " + fred.getPlayerHP() + " | Your Priority: " + fred.getPlayerPrio());
            if(isMonster == true)
              System.out.println("Enemy HP: " + enemyHP + " | Enemy Priority: " + currentMonster.getPrio());
            else 
              System.out.println("Enemy HP: " + enemyHP + " | Enemy Priority: " + currentBoss.getPrio());
            System.out.println("\n-------------------------------------------------------------------------\n");
            return false;
          }
        }else{
          if(fred.getPlayerPrio() == currentMonster.getPrio() || fred.getPlayerPrio() == currentBoss.getPrio()){
            for(Boon b : myBoons){ //death's dance & thundering fury
              if(b.getBoonName().equals("Thundering Fury"))
                thunderingFury();
            }
          if(speAtkCounter > 0) {
            System.out.println("You can't special attack yet! | " + speAtkCounter + " turn(s).");
            return false;
          }

          if(fred.getPlayerPrio() == currentMonster.getPrio() || fred.getPlayerPrio() == currentBoss.getPrio()){ //death's dance
            for(Boon b : myBoons){ //death's dance
              if(b.getBoonName().equals("Death's Dance"))
                deathsDance();
            }
          }

          if(isMonster == true) {
            int mdmg = currentMonster.monsterNormalAttack();
            recEnemyHit = mdmg;
            fred.addPlayerHP(-mdmg);
            for(Boon b : myBoons){ //heartbreaker & false weakness
              if(b.getBoonName().equals("Heartbreaker"))
                heartbreaker();
              if(b.getBoonName().equals("False Weakness"))
                falseWeakness();
            }
          } else {
            int bdmg = currentBoss.attack(currentBoss.getAtk());
            recEnemyHit = bdmg;
            fred.addPlayerHP(-bdmg);
            for(Boon b : myBoons){ //heartbreaker & false weakness
              if(b.getBoonName().equals("Heartbreaker"))
                heartbreaker();
              if(b.getBoonName().equals("False Weakness"))
                falseWeakness();
            }
          }

          if(!fred.isAlive()) {
            System.out.println("You died.");
            for(Boon b : myBoons){ //smite
              if(b.getBoonName().equals("Smite"))
                smite();
            }
            isDead = true;
            return true;
          }
          
          int dmg = fred.specialAttack(currentWeapon.getId());
          enemyHP -= dmg;
          System.out.println("You hit the " + currentMonster.getName() + " for " + dmg + " damage!");
          System.out.println();
          speAtkCounter += 3;

          for(Boon b : myBoons){ //killing blow
            if(b.getBoonName().equals("Killing Blow"))
              killingBlow();
          }
          if(enemyHP < 1) {
            System.out.println("You won!");
            currentMonster.setHP(0);
            return true;
          }else {
            System.out.println("\n-------------------------------------------------------------------------\n");
            System.out.println("Your HP: " + fred.getPlayerHP() + " | Your Priority: " + fred.getPlayerPrio());
            if(isMonster == true )
            System.out.println("Enemy HP: " + enemyHP + " | Enemy Priority: " + currentMonster.getPrio());
            else 
            System.out.println("Enemy HP: " + enemyHP + " | Enemy Priority: " + currentBoss.getPrio());
            System.out.println("\n-------------------------------------------------------------------------\n");
            return false;
          }
        }
      }
    } else if (commandWord.equals("normal") || commandWord.equals("normal attack")) {
      if(fred.getPlayerPrio() > currentMonster.getPrio() || fred.getPlayerPrio() > currentBoss.getPrio()) {
        int dmg = fred.normalAttack();
        if(firstTurn && firstCrit)
          dmg *= 2;
        for(Boon b : myBoons){ //precision strike & stormbreaker
          if(b.getBoonName().equals("Stormbreaker"))
            dmg += stormbreaker();
          if(b.getBoonName().equals("Precision Strike")){
            boolean crit = precisionStrike(); 
            if(crit)
              dmg *= 2;
          }
        }
        enemyHP -= dmg;
        recPlayerHit = dmg;
        
        System.out.println("You hit the " + currentMonster.getName() + " for " + dmg + " damage!");

        if ((speAtkCounter - 1) < 0)
          speAtkCounter = 0;
        else 
          speAtkCounter--;
        
        if(isMonster == true) {
          int mdmg = currentMonster.monsterNormalAttack();
          recEnemyHit = mdmg;
          fred.addPlayerHP(-mdmg);
          for(Boon b : myBoons){ //heartbreaker & false weakness
            if(b.getBoonName().equals("Heartbreaker"))
              heartbreaker();
            if(b.getBoonName().equals("False Weakness"))
              falseWeakness();
          }
        } else {
          int bdmg = currentBoss.attack(currentBoss.getAtk());
          recEnemyHit = bdmg;
          fred.addPlayerHP(-bdmg);
          for(Boon b : myBoons){ //heartbreaker & false weakness
            if(b.getBoonName().equals("Heartbreaker"))
              heartbreaker();
            if(b.getBoonName().equals("False Weakness"))
              falseWeakness();
          }
        }

        for(Boon b : myBoons){ //killing blow
          if(b.getBoonName().equals("Killing Blow"))
            killingBlow();
        }
        if(enemyHP < 1) {
          System.out.println("You won!");
          currentMonster.setHP(enemyHP);
          return true;
        } else if(!fred.isAlive()) { //* Will implement dd later
          System.out.println("You died.");
          for(Boon b : myBoons){ //smite
            if(b.getBoonName().equals("Smite"))
              smite();
          }
          isDead = true;
          return true;
        }else {
          System.out.println("\n-------------------------------------------------------------------------\n");
          System.out.println("Your HP: " + fred.getPlayerHP() + " | Your Priority: " + fred.getPlayerPrio());
          if(isMonster == true )
          System.out.println("Enemy HP: " + enemyHP + " | Enemy Priority: " + currentMonster.getPrio());
          else 
            System.out.println("Enemy HP: " + enemyHP + " | Enemy Priority: " + currentBoss.getPrio());
          System.out.println("\n-------------------------------------------------------------------------\n");
          return false;
        }
      }else {
        if(fred.getPlayerPrio() == currentMonster.getPrio() || fred.getPlayerPrio() == currentBoss.getPrio()){
          for(Boon b : myBoons){ //death's dance & thundering fury
          if(b.getBoonName().equals("Thundering Fury"))
            thunderingFury();
          if(b.getBoonName().equals("Death's Dance"))
            deathsDance();
          }
        }
        if(isMonster == true) {
          int mdmg = currentMonster.monsterNormalAttack();
          recEnemyHit = mdmg;
          fred.addPlayerHP(-mdmg);
          for(Boon b : myBoons){ //heartbreaker & false weakness
            if(b.getBoonName().equals("Heartbreaker"))
              heartbreaker();
            if(b.getBoonName().equals("False Weakness"))
              falseWeakness();
          }
        }else {
          int bdmg = currentBoss.attack(currentBoss.getAtk());
          recEnemyHit = bdmg;
          fred.addPlayerHP(-bdmg);
          for(Boon b : myBoons){ //heartbreaker & false weakness
            if(b.getBoonName().equals("Heartbreaker"))
              heartbreaker();
            if(b.getBoonName().equals("False Weakness"))
              falseWeakness();
          }
        }

        int dmg = fred.normalAttack();
        recPlayerHit = dmg;
        enemyHP -= dmg;
        System.out.println("You hit the " + currentMonster.getName() + " for " + dmg + " damage!");
    
        
        if ((speAtkCounter - 1) < 0)
          speAtkCounter = 0;
        else 
          speAtkCounter--;

        for(Boon b : myBoons){ //killing blow
          if(b.getBoonName().equals("Killing Blow"))
            killingBlow();
        }
        if(enemyHP < 1){
          System.out.println("You won!");
          currentMonster.setHP(0);
          return true;
        }

        if(!fred.isAlive()) {
          System.out.println("You died.");
          for(Boon b : myBoons){ //smite
            if(b.getBoonName().equals("Smite"))
              smite();
          }
          isDead = true;
          return true;
        } else {
          System.out.println("\n-------------------------------------------------------------------------\n");
          System.out.println("Your HP: " + fred.getPlayerHP() + " | Your Priority: " + fred.getPlayerPrio());
          if(isMonster == true)
          System.out.println("Enemy HP: " + enemyHP + " | Enemy Priority: " + currentMonster.getPrio());
          else 
          System.out.println("Enemy HP: " + enemyHP + " | Enemy Priority: " + currentBoss.getPrio());
          System.out.println("\n-------------------------------------------------------------------------\n");
          return false;
        }
      }
    } else if(commandWord.equals("flee") && currentRoom.getRoomId().equals("Test Dummy Room")) {
      System.out.println("You fled! But you won't be able to do it again...");
      return true;
    } else {
      System.out.println("You can't do that!");
    }

    firstTurn = false;
    firstCrit = false;

    prevCommand = command.getCommandWord();
    return false;
  }

  /**
   * Print out some help information. Here we print some stupid, cryptic message
   * and a list of the command words.
   */
  public void printHelp() {
    System.out.println("\n" + "Get out of hell: a daunting dask. Many have tried, none have succeeded.");
    System.out.println("You need to use all the tools at your disposal in order to escape.");
    System.out.println("Good luck. May the gods be on your side.");
    System.out.println();
    System.out.println("Your command words are:");
    parser.showCommands();
  }

  /**
   * Formats the myBoons ArrayList and displays them.
   */
  public void formatMyBoons() {
    if (myBoons.size() != 0) {
      System.out.println("\n" + "Your current boons are:");
      System.out.print("----------------------------------------------------------------------------------------------------------");
      for (int i = 0; i < myBoons.size(); i++) {
        System.out.println(myBoons.get(0).displayBoon());
      }
    } else {
      System.out.println("You don't have any boons yet!");
    }
  }

  /**
   * checks if the currentBoss has been defeated. Changes currentBoss to the next
   * miniboss/boss.
   *
   * @return T/F
   */
  public boolean currentBossDefeated() {
    if (!currentBoss.isAlive()) {
      if (bossCounter == 0) {
        currentBoss = new KingSkeleton();
        bossCounter++;
        generatedBoons = false;
        boonSelected = false;
      }
      if (bossCounter == 1) {
        currentBoss = new QueenSpider();
        bossCounter++;
        generatedBoons = false;
        boonSelected = false;
      }
      if (bossCounter == 2) {
        currentBoss = new Tarantula();
        bossCounter++;
        generatedBoons = false;
        boonSelected = false;
      }
      if (bossCounter == 3) {
        currentBoss = new TheAmalgamation();
        bossCounter++;
        generatedBoons = false;
        boonSelected = false;
      }
      if (bossCounter == 4) {
        currentBoss = new Thanatos();
      }
      return true;
    }
    return false;
  }

  public void setCurrentNPC() {
    if (currentRoom.getRoomName().equals("F1 NPC Room"))
      if(!setNPC){
        currentNPC = new Sisyphus(fred);
        setNPC = true;
      }
    else if (currentRoom.getRoomName().equals("F2 NPC Room")){
      if(!setNPC){
        currentNPC = new Eurydice();
        myBoons = currentNPC.displayChoices(fred, myBoons); //to alter the player's boons through Eurydice
        setNPC = true;
      }
    }
    else if (currentRoom.getRoomName().equals("F3 NPC Room"))
      if(!setNPC){
        currentNPC = new Patroclus(fred);
        setNPC = true;
      }
    else if (currentRoom.getRoomName().equals("F1 Shop Room") || currentRoom.getRoomName().equals("F2 Shop Room")
    || currentRoom.getRoomName().equals("F3 Shop Room")){
      if(!setNPC){
        temp = generateBoons(true); // special case to generate boon for selection in the shop.
        currentNPC = new Charon();
        myBoons = currentNPC.displayChoices(fred, temp, myBoons, items); //to alter the player's boons through Charon
        setNPC = true;
      }
    }
  }

  public void attemptToTake(Command command) {
    if(!command.hasSecondWord()){
      if(command.getCommandWord().toLowerCase().equals("takeall")){
    for(int i = currentRoom.getInventory().getItems().size()-1; i>=0; i--){
      fred.getInventory().addPlayerItem((currentRoom.getInventory().getItems().get(i)));
        currentRoom.getInventory().dropRoomItem(currentRoom.getInventory().getItems().get(i));
        takeAll = true;

    }
    }else if(command.getCommandWord().toLowerCase().equals("take")){
      System.out.println("Take what? Your options are:" );
      currentRoom.getInventory().displayRoomInventory();
    }
  }else{
    String secondWord = command.getSecondWord().toLowerCase();
      if(currentRoom.getInventory().inInventory(secondWord)){
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().toLowerCase().equals(secondWord)){
              currentRoom.getInventory().dropRoomItem(items.get(i));
              fred.getInventory().addPlayerItem(items.get(i));
            }
        }
      }else{
        System.out.println(secondWord + " is not in this rooms inventory. Your options are: ");
        currentRoom.getInventory().displayPlayerInventory();
      }
  }
  }

  /**
   * Check if you are able to receive a boon.
   *
   * @return T/F
   */
  public boolean onBoonScreen() {
    if (currentRoom.getRoomName().equals("Boon Room") || currentBossDefeated()) {
      return true;
    }
    return false;
  }

  /**
   * Try to go to one direction. If there is an exit, enter the new room,
   * otherwise print an error message.
   */
  public void goRoom(Command command) {
    boolean twoWords = false;
    // if the command doesn't have a second word AND it isn't a direction
    if (!command.hasSecondWord() && !(command.getCommandWord().equals("east") || command.getCommandWord().equals("west")
        || command.getCommandWord().equals("north") || command.getCommandWord().equals("south"))) {
      // if there is no second word, we don't know where to go...
      System.out.println("Go where?");
      return;
    }

    if(currentRoom.getRoomName().equals("Spawn Room")){
      if(weaponSelected)
        canProceed = true;
    } 
    else if(currentRoom.getRoomName().equals("Test Dummy Room"))
      canProceed = true;
    else if(currentRoom.getRoomName().equals("Boon Room") || currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")){
      if(boonSelected)
        canProceed = true; 
    } else {
      //if(!currentMonster.isAlive())
        //canProceed = true;
    }

    if (command.hasSecondWord())
      twoWords = true;

    String direction;

    // gets intended direction based on whether or not "go" was entered prior
    if (twoWords)
      direction = command.getSecondWord();
    else 
      direction = command.getCommandWord();

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);

    if (nextRoom == null)
      System.out.println("There is no door!");
    else {
      if(canProceed){
        currentRoom = nextRoom;
        System.out.println(currentRoom.roomDescription());
        canProceed = false;
      }
      else
        System.out.println(("The doors won't open just yet..."));
    }
  }

  /**
   * Plays a weapon selection intro for the first time, and allows the player to
   * select a weapon.
   *
   * @param weapon weapon that is selected through player commands.
   * @param playIntro boolean to determine if intro has been played.
   * @return weapon in weapons ArrayList, null if weapon does not equal a valid
   *         weapon.
   */
  public Weapons weaponSelection(String weapon, boolean playIntro) {
    if (playIntro) {
      System.out.println("\n" + "There are 4 weapons you may pick from: Bow, Spear, Sword, and Shield.");
      System.out.print("Each weapon has its own unique stats and special attack.");
      System.out.println(" Enter the name of the weapon followed by \"help\" to learn more about it." + "\n");
      System.out.println("Please select a weapon for your escape...");
    }
    if (weapon != null) {
      if (weapon.equals("bow"))
        return weapons.get(0);
      else if (weapon.equals("spear"))
        return weapons.get(1);
      else if (weapon.equals("sword"))
        return weapons.get(2);
      else if (weapon.equals("shield"))
        return weapons.get(3);
    }
    return null;
  }

  /**
   * finds the index in myBoons that shares the same boon name and returns the
   * index.
   *
   * @param name of boon being matched.
   * @return the index in myBoons, -1 if not found
   */
  private int getIndexByBoonName(String name) {
    for (int i = 0; i < myBoons.size(); i++) {
      Boon b = myBoons.get(i);
      if (boons != null && b.getBoonName().equals(name)) {
        return i;
      }
    }
    return -1; // this should never be returned; this function will only be called when we know a boon is in myBoons
  }

  /* Making combat here */

  /*public void fight(Player player, Weapons weapon, Command command, String commandWord) {
    int playerHP = player.getPlayerHP();

    if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")) {
      //Boss fight code 
      
      int bossHP = currentBoss.getHP();
        if (!command.hasSecondWord() || command.getCommandWord() == "attack") {
          System.out.println("Are you doing a normal or special attack?");
        }

        if(command.getSecondWord() == "normal" || command.getCommandWord() == "normal") {
          int dmg = weapon.normalAttack();
          bossHP -= dmg;
          System.out.println("You hit " + currentBoss.getName() + " for " + dmg + " damage!");
          if(!currentBoss.isAlive())
            System.out.println(currentBoss.getName() + " has died.");
        }else if(command.getSecondWord() == "special" || command.getCommandWord() == "special") {
          int dmg = weapon.specialAttack(weapon.getId());
          bossHP -= dmg;
          System.out.println("You hit " + currentBoss.getName() + " for " + dmg + " damage!");
          if(!currentBoss.isAlive())
            System.out.println(currentBoss.getName() + " has died.");
        }
    }else {
      int monsterHP = currentMonster.getHP();
        if (!command.hasSecondWord() || command.getCommandWord() == "attack") {
          System.out.println("Are you doing a normal or special attack?");
        }
  
        if (command.getSecondWord() == "normal" || command.getCommandWord() == "normal") {
          int dmg = weapon.normalAttack();
          monsterHP -= dmg;
          System.out.println("You hit the " + currentMonster.getName() + " for " + dmg + " damage!");
        } else if (command.getSecondWord() == "special" || command.getCommandWord() == "special") {
          int dmg = weapon.specialAttack(weapon.getId());
          monsterHP -= dmg;
          System.out.println("You hit the " + currentMonster.getName() + " for " + dmg + " damage!");
        }
  
        int dmg = currentMonster.monsterNormalAttack(); 
        playerHP -= dmg;
        System.out.println("The " + currentMonster.getName() + " hit you for " + dmg + " damage!");
        if(!player.isAlive())
          System.out.println("You died xd");
    }
  } */

  /**
   * Sees what kind of attack the player will perform.
   *
   * @param command
   */
  /*
   * public void attackType(Command command) {
   *
   *
   *
   * }
   */


  /*------------------------------------Boon Functionality------------------------------------*/

  /**
   * Randomly choose 3 boons of the same god for player to choose.
   *
   * @param atShop determines if we are in a shop room or not when generating.
   */
  public ArrayList<Boon> generateBoons(boolean atShop) {
    ArrayList<Boon> selection = new ArrayList<>();
    int num = (int) (Math.random() * boons.size());
    if (!atShop) {
      if (num <= 2) { // Ares
        selection.add(boons.get(0));
        selection.add(boons.get(1));
        selection.add(boons.get(2));
      } else if (num > 2 && num <= 5) { // Artemis
        selection.add(boons.get(3));
        selection.add(boons.get(4));
        selection.add(boons.get(5));
      } else if (num > 5 && num <= 8) { // Aphrodite
        selection.add(boons.get(6));
        selection.add(boons.get(7));
        selection.add(boons.get(8));
      } else if (num > 8 && num <= 11) { // Zeus
        selection.add(boons.get(9));
        selection.add(boons.get(10));
        selection.add(boons.get(11));
      } else if (num > 11 && num <= 14) { // Poseidon
        selection.add(boons.get(12));
        selection.add(boons.get(13));
        selection.add(boons.get(14));
      } else { // Athena
        selection.add(boons.get(15));
        selection.add(boons.get(16));
        selection.add(boons.get(17));
      }
      System.out.println("\n" + selection.get(0).getColour() + "Please select one of the boons by typing \"b[number]\":");
      System.out.print("----------------------------------------------------------------------------------------------------------");
      System.out.println(selection.get(0).displayBoon() + selection.get(1).displayBoon() + selection.get(2).displayBoon());
    } else {
      selection.add(boons.get(num)); // uses num from beginning of function
      selection.add(boons.get((int) (Math.random() * boons.size()))); // different random value
      selection.add(boons.get((int) (Math.random() * boons.size())));
    }
    return selection;
  }

  /**
   * Gain 5, 10, 15 (5, 5, 5) attack.
   */
  public void brutalStrength() {
    int level = myBoons.get(getIndexByBoonName("Brutal Strength")).getLevel();
    if (level == 1)
      fred.addPlayerAttack(5);
    else if (level == 2)
      fred.addPlayerAttack(5);
    else
      fred.addPlayerAttack(5);
  }

  /**
   * If player prio is equal to monster/boss prio, deal 4, 8, 12 damage.
   */
  public void deathsDance() {
    int level = myBoons.get(getIndexByBoonName("Death's Dance")).getLevel();
    if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")) {
      if(fred.getPlayerPrio() == currentBoss.getPrio()){
        if(level == 1){
          currentBoss.addBossHP(-4);
          System.out.println(currentBoss.getName() + " lost 4 HP from Death's Dance.");
        } else if(level == 2){
          currentBoss.addBossHP(-8);
          System.out.println(currentBoss.getName() + " lost 8 HP from Death's Dance.");
        } else {
          currentBoss.addBossHP(-12);
          System.out.println(currentBoss.getName() + " lost 12 HP from Death's Dance.");
        }
      }
    } else {
      if(fred.getPlayerPrio() == currentMonster.getPrio()){
        if(level == 1){
          currentMonster.addMonsterHP(-4);
          System.out.println("The " + currentMonster.getName() + " lost 4 HP from Death's Dance.");
        } else if(level == 2){
          currentMonster.addMonsterHP(-8);
          System.out.println("The " + currentMonster.getName() + " lost 8 HP from Death's Dance.");
        } else {
          currentMonster.addMonsterHP(-12);
          System.out.println("The " + currentMonster.getName() + " lost 12 HP from Death's Dance.");
        }      
      }
    }
  }

  /**
   * Execute enemies below 5, 10, 15% hp.
   */
  public void killingBlow() {
    int level = myBoons.get(getIndexByBoonName("Killing Blow")).getLevel();
    if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")) {
      if (level == 1) {
        if (currentBoss.getHP() < currentBoss.getHP() * 0.05)
          currentBoss.setHP(0);
          System.out.println(currentBoss.getName() + " was executed by Killing Blow.");
      } else if (level == 2) {
        if (currentBoss.getHP() < currentBoss.getHP() * 0.1)
          currentBoss.setHP(0);
          System.out.println(currentBoss.getName() + " was executed by Killing Blow.");
      } else {
        if (currentBoss.getHP() < currentBoss.getHP() * 0.15)
          currentBoss.setHP(0);
          System.out.println(currentBoss.getName() + " was executed by Killing Blow.");
      }
    } else {
      if (level == 1) {
        if (currentMonster.getHP() < currentMonster.getHP() * 0.05)
          currentMonster.setHP(0);
          System.out.println("The " + currentMonster.getName() + " was executed by Killing Blow.");
      } else if (level == 2) {
        if (currentMonster.getHP() < currentMonster.getHP() * 0.1)
          currentMonster.setHP(0);
          System.out.println("The " + currentMonster.getName() + " was executed by Killing Blow.");
      } else {
        if (currentMonster.getHP() < currentMonster.getHP() * 0.15)
          currentMonster.setHP(0);
          System.out.println("The " + currentMonster.getName() + " was executed by Killing Blow.");
      }
    }
  }

  /**
   * Gain 5, 6, 7 priority. If the bow is equipped, gain 5, 7, 9 (5, 2, 2) attack.
   */
  public void huntersEye() {
    int level = myBoons.get(getIndexByBoonName("Hunter's Eye")).getLevel();
    if (level == 1)
      fred.addPlayerPriority(5);
    else if (level == 2)
      fred.addPlayerPriority(1);
    else
      fred.addPlayerPriority(1);

    if (currentWeapon.getName().equals("Bow")) {
      if (level == 1)
        fred.addPlayerAttack(5);
      else if (level == 2)
        fred.addPlayerAttack(2);
      else
        fred.addPlayerAttack(2);
    }
  }

  /**
   * Determine if player's next attack will deal double dmg with Precision Strike.
   *
   * @return T/F
   */
  public boolean precisionStrike() {
    int level = myBoons.get(getIndexByBoonName("Precision Strike")).getLevel();
    int random = (int) (Math.random() * 100) + 1;
    if (level == 1) {
      if (random <= 10)
        return true;
    } else if (level == 2) {
      if (random <= 15)
        return true;
    } else {
      if (random <= 20)
        return true;
    }
    return false;
  }

  /**
   * Striking an enemy first gives you double damage on the attack.
   * 
   * @return T/F
   */
  public boolean firstStrike() {
    if(currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")){
      if(fred.getPlayerPrio() > currentBoss.getPrio())
        return true;
    } else {
      if(fred.getPlayerPrio() > currentMonster.getPrio())
        return true;
    }
    return false;
  }

  /**
   * Randomly selects a value to see if enemy takes 50% of damage dealt by their own attack.
   */
  public void heartbreaker() {
    boolean willCharm = false;
    int random = (int) (Math.random() * 10) + 1;
    if (random <= 1 * myBoons.get(getIndexByBoonName("Heartbreaker")).getLevel())
      willCharm = true;
    if (willCharm) {
      if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")){
        currentBoss.addBossHP((int)(-recEnemyHit * 0.5));
        System.out.println(currentBoss.getName() + " took " + (-recEnemyHit * 0.5) + " damage from Heartbreaker.");
      }
      else{
        currentMonster.addMonsterHP((int)(-recEnemyHit * 0.5));
        System.out.println("The " + currentMonster.getName() + " took " + (-recEnemyHit * 0.5) + " damage from Heartbreaker.");
      }
    }
  }

  /**
   * 25/50/75% chance to reduce prio to 4/2/0.
   */
  public void charm() {
    int level = myBoons.get(getIndexByBoonName("Charm")).getLevel();
    int random = (int) (Math.random() * 100) + 1;
    if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")) {
      if (level == 1) {
        if (random <= 25) {
          currentBoss.setBossPriority(4);
          System.out.println("You charmed " + currentBoss.getName() + ".");
        }
      } else if (level == 2) {
        if (random <= 50) {
          currentBoss.setBossPriority(2);
          System.out.println("You charmed " + currentBoss.getName() + ".");
        }
      } else {
        if (random <= 75) {
          currentBoss.setBossPriority(0);
          System.out.println("You charmed " + currentBoss.getName() + ".");
        }
      }
    } else {
      if (level == 1) {
        if (random <= 25) {
          currentBoss.setBossPriority(4);
          System.out.println("You charmed the " + currentMonster.getName() + ".");
        }
      } else if (level == 2) {
        if (random <= 50) {
          currentBoss.setBossPriority(2);
          System.out.println("You charmed the " + currentMonster.getName() + ".");
        }
      } else {
        if (random <= 75) {
          currentBoss.setBossPriority(0);
          System.out.println("You charmed the " + currentMonster.getName() + ".");
        }
      }
    }
  }

  /**
   * Reduces enemies' attack by 10, 15, 20%.
   */
  public void falseWeakness() {
    int level = myBoons.get(getIndexByBoonName("False Weakness")).getLevel();
    if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")) {
      if(level == 1){
        currentBoss.setBossAtk((int)(currentBoss.getAtk() * 0.9));
        System.out.println(currentBoss.getName() + " lost " + (int)(currentBoss.getAtk() * 0.9) + " from False Weakness.");
      } else if(level == 2){
        currentBoss.setBossAtk((int)(currentBoss.getAtk() * 0.85));
        System.out.println(currentBoss.getName() + " lost " + (int)(currentBoss.getAtk() * 0.85) + " from False Weakness.");
      } else { 
        currentBoss.setBossAtk((int)(currentBoss.getAtk() * 0.8));
        System.out.println(currentBoss.getName() + " lost " + (int)(currentBoss.getAtk() * 0.8) + " from False Weakness.");
      }
    } else {
      if(level == 1){
        currentMonster.setAtk((int)(currentMonster.getAtk() * 0.9));
        System.out.println("The" + currentMonster.getName() + " lost " + (int)(currentBoss.getAtk() * 0.9) + " from False Weakness.");
      } else if(level == 2){
        currentMonster.setAtk((int)(currentMonster.getAtk() * 0.85));
        System.out.println("The" + currentMonster.getName() + " lost " + (int)(currentBoss.getAtk() * 0.85) + " from False Weakness.");
      } else {
        currentMonster.setAtk((int)(currentMonster.getAtk() * 0.8));
        System.out.println("The" + currentMonster.getName() + " lost " + (int)(currentBoss.getAtk() * 0.8) + " from False Weakness.");
      }
    }
  }

  /**
   * Executes the enemy that deals lethal damage to the player. Deals less damage
   * to bosses.
   */
  public void smite() {
    if (!(fred.isAlive())) {
      if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")) {
        currentBoss.setHP((int) (currentBoss.getHP() - (currentBoss.getMaxHP() * 0.1))); // subtracts 10% max hp from current hp
        System.out.println(currentBoss.getName() + " took " + (int) (currentBoss.getHP() - (currentBoss.getMaxHP() * 0.1)) + " damage from Smite.");
      } else {
        currentMonster.setHP(0);
        System.out.println("The " + currentMonster.getName() + " was executed from Smite.");
      }
    }
  }

  /**
   * Remove 30, 40, 50% of the enemy's current defence if you and the enemy have equal attack prio.
   */
  public void thunderingFury() {
    int level = myBoons.get(getIndexByBoonName("Thundering Fury")).getLevel();
    boolean bossRoom = false;
    if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room"))
      bossRoom = true;
    if (bossRoom) {
      if (level == 1){
        currentBoss.addBossDef((int) (currentBoss.getDef() - (currentBoss.getDef() * 0.3)));
        System.out.println(currentBoss.getName() + " lost " + (int) (currentBoss.getDef() - (currentBoss.getDef() * 0.3)) + " defence from Thundering Fury.");
      }
      else if (level == 2){
        currentBoss.setBossDef((int) (currentBoss.getDef() - (currentBoss.getDef() * 0.4)));
        System.out.println(currentBoss.getName() + " lost " + (int) (currentBoss.getDef() - (currentBoss.getDef() * 0.4)) + " defence from Thundering Fury.");
      } else {
        currentBoss.setBossDef((int) (currentBoss.getDef() - (currentBoss.getDef() * 0.5)));
        System.out.println(currentBoss.getName() + " lost " + (int) (currentBoss.getDef() - (currentBoss.getDef() * 0.5)) + " defence from Thundering Fury.");
      }
    } else {
      if (level == 1){
        currentMonster.setDef((int) (currentMonster.getDef() - (currentMonster.getDef() * 0.3)));
        System.out.println("The " + currentMonster.getName() + " lost " + (int) (currentBoss.getDef() - (currentBoss.getDef() * 0.3)) + " defence from Thundering Fury.");
      } else if (level == 2){
        currentMonster.setDef((int) (currentMonster.getDef() - (currentMonster.getDef() * 0.4)));
        System.out.println("The " + currentMonster.getName() + " lost " + (int) (currentBoss.getDef() - (currentBoss.getDef() * 0.4)) + " defence from Thundering Fury.");
      } else{
        currentMonster.setDef((int) (currentMonster.getDef() - (currentMonster.getDef() * 0.5)));
        System.out.println("The " + currentMonster.getName() + " lost " + (int) (currentBoss.getDef() - (currentBoss.getDef() * 0.5)) + " defence from Thundering Fury.");
      }
    }
  }

  /**
   * Deal additional 2, 4, 6% max hp dmg to enemies.
   *
   * @return bonus damage dealt
   */
  public int stormbreaker() {
    int level = myBoons.get(getIndexByBoonName("Stormbreaker")).getLevel();
    if (currentRoom.getRoomName().equals("Miniboss Room") || currentRoom.getRoomName().equals("Boss Room")) {
      if (level == 1)
        return (int) (currentBoss.getMaxHP() * 0.02);
      else if (level == 2)
        return (int) (currentBoss.getMaxHP() * 0.04);
      else
        return (int) (currentBoss.getMaxHP() * 0.06);
    } else {
      if (level == 1)
        return (int) (currentMonster.getMaxHP() * 0.02);
      else if (level == 2)
        return (int) (currentMonster.getMaxHP() * 0.04);
      else
        return (int) (currentMonster.getMaxHP() * 0.06);
    }
  }

  /**
   * Restore 12.5, 25, 50% of all damage dealt as hp.
   */
  public void suckyWucky() {
    int level = myBoons.get(getIndexByBoonName("Sucky Wucky")).getLevel();
    int dmgToHP = 0;
    if(level == 1)
      dmgToHP = recPlayerHit * (int)0.125; // get damage done from weapon normal/special attack and multiply
    else if(level == 2)
      dmgToHP = recPlayerHit * (int)0.25;
    else 
      dmgToHP = recPlayerHit * (int)0.5;
    fred.addPlayerHP(dmgToHP);
  }

  /**
   * Heal player instantly +20, after every room +5, and increase max hp +2 if
   * room is cleared at full health.
   *
   * @param situation determines whether we call the instant heal(true) or small
   *                  heal/max hp increase(false).
   */
  public void vitality(boolean situation) {
    if (situation) 
      fred.addPlayerHP(20);
    else {
      if (canProceed) {
        if (fred.getPlayerHP() == fred.getPlayerMaxHP()) {
          fred.addPlayerMaxHP(2);
          fred.addPlayerHP(2);
        } else
          fred.addPlayerHP(5);
      }
    }
  }

  /**
   * Replenish the extra life.
   */
  public void highTide() {
    if (fred.getExtraLife())
      System.out.println("You already had one. But here, I guess?");
    if (!fred.getExtraLife())
      fred.setExtraLife(true);
    System.out.println("Your extra life has been replenished.");
  }

  /**
   * Gain 20, 25, 30 defence, doubled to 40, 50, 60 if you have a shield equipped.
   */
  public void fortify() {
    int level = myBoons.get(getIndexByBoonName("Fortify")).getLevel();
    if (currentWeapon.getName().equals("Shield")) {
      if (level == 1) 
        fred.addPlayerDefence(40);
      else if (level == 2) 
        fred.addPlayerDefence(10);
      else 
        fred.addPlayerDefence(10);
    } else {
      if (level == 1)
        fred.addPlayerDefence(20);
      else if (level == 2) 
        fred.addPlayerDefence(5);
      else
        fred.addPlayerDefence(5);
    }
  }

  /**
   * Gain a 4, 8, 12% chance to mitigate attack from enemy.
   */
  public boolean divineProtection() {
    int level = myBoons.get(getIndexByBoonName("Divine Protection")).getLevel();
    int random = (int) (Math.random() * 100) + 1;
    if(level == 1){
      if (random <= 4) 
        return true;
      else
        return false;
    }
    else if(level == 2){
      if(random <= 8)
        return true;
      else
        return false;
    }
    else{
      if(random <= 12)
        return true;
      else
        return false;
    }
  }

  /**
   * If player hp goes below 0, bring it back to 1.
   * 
   * @param hasCalled check if the boon has been used on the floor.
   */
  public void secondWind(boolean hasCalled) {
    if(!hasCalled){
      if (fred.getPlayerHP() <= 0)
        fred.setPlayerHP(1);
    }
  }

  /**
   * Takes a boon and sees if increases raw stats. If so, call it.
   * @param selection boon that is potentially being called
   */
  public void rawIncreases(Boon selection){
    if(selection.getBoonName().equals("Brutal Strength"))
      brutalStrength();
    else if(selection.getBoonName().equals("Hunter's Eye"))
      huntersEye();
    else if(selection.getBoonName().equals("Vitality"))
      vitality(true);
    else if(selection.getBoonName().equals("High Tide"))
      highTide();
    else if(selection.getBoonName().equals("Fortify"))
      fortify();
  }
  
  /*------------------------------------End of Boon Functionality------------------------------------*/
  
  public void sell() {

  }

  public void determineMonsterDrop(){
    if(monsterDrop){
    if(!currentMonster.isAlive()){
    String floor = currentRoom.getRoomName().substring(0, 2);
    int randomNum = (int)(Math.random()*100+1);
    int ans = (int)(Math.random()*100+1);
    int count = 0;
    if(currentRoom.getRoomName().indexOf("Attack")>=0){
      if(floor.equals("F1")){
        currentRoom.getInventory().addRoomItem(items.get(0));
        count++;
        if(randomNum<=ans+15&&randomNum>=ans-15){
          currentRoom.getInventory().addRoomItem(items.get(0));
          count++;
        }if(randomNum<=ans+5&&randomNum>=ans-5){
          currentRoom.getInventory().addRoomItem(items.get(0));
          count++;
        }
          System.out.println("The skeleton has dropped " + count + " bones. ");
      }else if(floor.equals("F2")){
        currentRoom.getInventory().addRoomItem(items.get(1));
        count++;
        if(randomNum<=ans+15&&randomNum>=ans-15){
          currentRoom.getInventory().addRoomItem(items.get(1));
          count++;
        }if(randomNum<=ans+5&&randomNum>=ans-5){
          currentRoom.getInventory().addRoomItem(items.get(1));
          count++;
        }
        System.out.println("The spider has dropped " + count + " legs. ");
      }else{
        currentRoom.getInventory().addRoomItem(items.get(2));
        count++;
        if(randomNum<=ans+10&&randomNum>=ans-10){
          currentRoom.getInventory().addRoomItem(items.get(2));
          count++;
        }if(randomNum<=ans+5&&randomNum>=ans-5){
          currentRoom.getInventory().addRoomItem(items.get(2));
          count++;
        }
        System.out.println("The fallen hero has dropped " + count + " urns. ");
      }
    }
  }
  }
}

  public void predertimineItems(){
  if(!takeAll){
    if(determineItems){
    for (int i = 0; i < 3; i++) {
      int numItem = (int)(Math.random()*items.size());
      if(numItem<=4)
        numItem+=5;
      if(numItem>=22&&numItem<=24)
        numItem-=3;
      if(numItem>=25)
        numItem-=6;
      currentRoom.getInventory().addRoomItem(items.get(numItem));
    }
    determineItems = false;
    }
  }
  }

  public void hydraliteGold() {
      if (getCurrentRoom) {
        prevRoom = currentRoom.getRoomName();
      }
      getCurrentRoom = false;
      if (!(prevRoom.equals(currentRoom.getRoomName()))) {
        int addHP = (int) (fred.getPlayerMaxHP() * 0.3);
        fred.addPlayerHP(addHP);
        System.out.println("You have gained " + addHP + " HP");
        prevRoom = currentRoom.getRoomName();
      }
  }

  public int defenseCalc(int dmg) {
    int defCalc = (fred.getPlayerDef() / 4) - 2;
  
    if((dmg - defCalc) < 0) {
      return 0;
    }else {
      return dmg - defCalc;
   }
  }

  public void priorityReduction() { //* Priority reduction after each attack!
    
  }
}