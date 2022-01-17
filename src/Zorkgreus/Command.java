package Zorkgreus;

public class Command {
  private String commandWord;
  private String secondWord;
  private String thirdWord;
  private String fourthWord;
  private String fifthWord;

  /**
   * Create a command object. First and second word must be supplied, but either
   * one (or both) can be null. The command word should be null to indicate that
   * this was a command that is not recognised by this game.
   */
  public Command(String firstWord, String secondWord, String thirdWord, String fourthWord, String fifthWord) {
    commandWord = firstWord;
    this.secondWord = secondWord;
    this.thirdWord = thirdWord;
    this.fourthWord = fourthWord;
    this.fifthWord = fifthWord;
  }

  /**
   * Return the command word (the first word) of this command. If the command was
   * not understood, the result is null.
   */
  public String getCommandWord() {
    return commandWord;
  }

  /**
   * Return the second word of this command. Returns null if there was no second
   * word.
   */
  public String getSecondWord() {
    return secondWord;
  }

  public String getThirdWord() {
    return thirdWord;
  }

  public String getFourthWord() {
    return fourthWord;
  }

  public String getFifthWord() {
    return fifthWord;
  }

  /**
   * Return true if this command was not understood.
   */
  public boolean isUnknown() {
    return (commandWord == null);
  }

  /**
   * Return true if the command has a second word.
   */
  public boolean hasSecondWord() {
    return (secondWord != null);
  }

  public boolean hasThirdWord() {
    return (thirdWord != null);
  }

  public boolean hasFourthWord() {
    return (fourthWord != null);
  }

  public boolean hasFifthWord() {
    return (fifthWord != null);
  }
}