package examples.ch14.addressbookv1t;

/**
 * This class holds an enumeration of all command words known
 * to the program.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class CommandWords {
  // a constant array that holds all valid command words
  private static final String[] validCommands = {"add", "search", "list", "help", "quit"};

  /**
   * Constructor for CommandWords.
   */
  public CommandWords() {
  }

  /**
   * Check whether a given String is a valid command word.
   *
   * @param string The string to be checked.
   * @return true if it is valid, false if it isn't.
   */
  public boolean isCommand(String string) {
    if (string != null) {
      for (int i = 0; i < validCommands.length; i++) {
        if (validCommands[i].equals(string)) {
          return true;
        }
      }
    }
    // if we get here, the string was not found in the commands
    return false;
  }

  /**
   * Print all valid commands to System.out.
   */
  public void showAll() {
    for (String command : validCommands) {
      System.out.print(command + "  ");
    }
    System.out.println();
  }
}
