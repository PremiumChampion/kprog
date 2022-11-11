package examples.io;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demo showing some useful methods of the classes Paths, Path and FileSystems.
 */
public class PathsDemo {

  /**
   * Main method. No args are expected.
   *
   * @param args no args are expected
   */
  public static void main(String[] args) {
    System.out.println("path operations:");
    Path p1 = Paths.get("/tmp/foo");
    System.out.println(p1.getParent().toString());
    Path p2 = Paths.get(URI.create("file:///Users/joe/FileTest.java"));
    //Die Paths.get Methode is a shortcut for the following code:
    Path p3 = FileSystems.getDefault().getPath("/users/sally");
    // The following example creates /u/joe/logs/foo.log under the assumption, that the home
    // directory is /u/joe (Linux) respective C:\joe\logs\foo.log (Windows)
    Path p5 = Paths.get(System.getProperty("user.home"), "logs", "foo.log");
    System.out.format("toString: %s%n", p5.toString());
    System.out.format("getFileName: %s%n", p5.getFileName());
    System.out.format("getName(0): %s%n", p5.getName(0));
    System.out.format("getNameCount: %d%n", p5.getNameCount());
    System.out.format("subpath(0,2): %s%n", p5.subpath(0, 2));
    System.out.format("getParent: %s%n", p5.getParent());
    System.out.format("getRoot: %s%n", p5.getRoot());
    // When a string representation for a browser is needed:
    //Result: file:///C:/Users/Martin/logs/foo.log for user Martin
    System.out.format("%s%n", p5.toUri());
  }

}
