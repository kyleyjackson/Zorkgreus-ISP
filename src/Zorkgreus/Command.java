package Zorkgreus;

public class Command {
  private String commandWord;
  private String secondWord;
  private String thirdWord;
  private String fourthWord;

  /**
   * Create a command object. First, second, third, and fourth word must be supplied, but either
   * ones (or all) can be null. The command word should be null to indicate that
   * this was a command that is not recognised by this game.
   */
  public Command(String firstWord, String secondWord, String thirdWord, String fourthWord) {
    commandWord = firstWord;
    this.secondWord = secondWord;
    this.thirdWord = thirdWord;
    this.fourthWord = fourthWord;
  }

  /*accessor methods for first, second, third, and fourth words. Returns null if there was no word respectively */
  public String getCommandWord() {
    return commandWord;
  }

  public String getSecondWord() {
    return secondWord;
  }

  public String getThirdWord() {
    return thirdWord;
  }
  
  public String getFourthWord() {
    return fourthWord;
  }

  /**
   * checks if there isn't a first word
   * @return true if there isn't a first word
   */
  public boolean isUnknown() {
    return (commandWord == null);
  }

  /*checks if there is a second, third, or third word. Returns true if there is */
  public boolean hasSecondWord() {
    return (secondWord != null);
  }

  public boolean hasThirdWord() {
    return (thirdWord != null);
  }

  public boolean hasFourthWord() {
    return (fourthWord != null);
  }
}