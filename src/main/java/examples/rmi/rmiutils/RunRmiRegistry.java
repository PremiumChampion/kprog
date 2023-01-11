package examples.rmi.rmiutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class to run a RMI Registry from within a project. With
 * this approach the Registry has all class files of the project in its
 * CLASSPATH. Thus there is no need to download class files from a codebase.
 *
 * It also allows to inspect the contents of the Registry object.
 */

public class RunRmiRegistry {
  private static final Logger logger = LoggerFactory.getLogger(RunRmiRegistry.class);
  private static final int PORT = 1099;

  /**
   * Main.
   *
   * @param args no args expected.
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    Registry registry = LocateRegistry.createRegistry(PORT);

    BufferedReader keyboard = new BufferedReader(new InputStreamReader(
            System.in));

    Remote remoteReference = null;

    while (true) {
      System.out.println("Your command, Master (list, end):");
      String line = keyboard.readLine();
      if ("list".equals(line)) {
        String[] list = registry.list();
        System.out.println("rmiregistry - list : " + list.length
                + " entries.");
        for (String string : list) {
          try {
            remoteReference = registry.lookup(string);
          } catch (NotBoundException e) {
            e.printStackTrace();
          }
          System.out.println("[" + string + "] " + remoteReference);
        }
        continue;
      }

      if ("end".equals(line)) {
        System.out.println("It was a pleasure to serve you, Master.");
        break;
      }
    }

  }
}
