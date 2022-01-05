package Zorkgreus;

//import java.util.Scanner;

public class CommandWords {
  // a constant array that holds all valid command words (the numbers "1 2 3")
  // represent the boons that they can select)
  private static final String validCommands[] = { "go", "quit", "help", "eat", "east", "west", "north", "south",
      "attack", "normal", "special", "look", "take", "takeall", "drop", "gold", "jump", "run", "slap", "cry", "boon", "boonlist",
      "myboons", "APCSP!", "APSCP", "fight", "bow", "spear", "sword", "shield", "confirm", "b1", "b2", "b3", };

  /**
   * Constructor - initialise the command words.
   */
  public CommandWords() {
    // nothing to do at the moment...
  }

  /**
   * Check whether a given String is a valid command word. Return true if it is,
   * false if it isn't.
   **/
  public boolean isCommand(String aString) {
    for (String c : validCommands) {
      if (c.equals(aString))
        return true;
    }
    // if we get here, the string was not found in the commands
    return false;
  }

  /*
   * Print all valid commands to System.out.
   */
  public void showAll() {
    for (String c : validCommands) {
      System.out.print(c + "  ");
    }
    System.out.println();
  }
  
  public String[] getValidCommands(){
    return validCommands;
  }

  public int getCommandsLength(){
    return validCommands.length;
  }

}