package examples.ch14.addressbookv1t;

import java.util.Scanner;

/**
 * A class that reads input lines from the user.
 * Input is filtered via getCommand for valid commands.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Parser {
  // Hold all valid command words.
  private final CommandWords commands;
  private final Scanner reader;

  public Parser() {
    commands = new CommandWords();
    reader = new Scanner(System.in);
  }

  /**
   * Read the next command from the user.
   * The returned command will be valid.
   *
   * @return A valid command.
   */
  public String getCommand() {
    String command = null;
    do {
      // Print a prompt.
      System.out.print("> ");

      String word = reader.next();
      // Discard the rest of the line.
      readLine();
      if (commands.isCommand(word)) {
        command = word;
      } else {
        System.out.println("Unrecognized command: " + word);
        System.out.print("Valid commands are: ");
        commands.showAll();
      }
    } while (command == null);

    return command;
  }

  /**
   * Print out a list of valid command words.
   */
  public void showCommands() {
    commands.showAll();
  }

  /**
   * Returns A line of text from the user.
   *
   * @return line of text
   */
  public String readLine() {
    return reader.nextLine();
  }
}
