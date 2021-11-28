package Zorkgreus;

public class CommandWords {
  // a constant array that holds all valid command words (the numbers "1 2 3" represent the boons that they can select)
  private static final String validCommands[] = { "go", "quit", "help", "eat", "east", "west", "north", "south", 
  "attack", "normal", "special", "look", "take", "takeall", "jump", "run", "slap", "cry", "1", "2", "3", "one", "two", "three"};

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
      if (c.equals(aString.toUpperCase()))
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
}