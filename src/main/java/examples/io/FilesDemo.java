package examples.io;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demo to show some useful methods of the classes Files and FileStore.
 */
public class FilesDemo {

  public static final String XANADU_TXT = "demofiles/io/xanadu.txt";

  /**
   * main method. No args are expected.
   *
   * @param args args are not expected
   */
  public static void main(String[] args) {
    System.out.println("Readable? " + Files.isReadable(Paths.get(XANADU_TXT)));

    try {
      System.out.println("Hidden? " + Files.isHidden(Paths.get(XANADU_TXT)));
      System.out.println("Owner? " + Files.getOwner(Paths.get(XANADU_TXT),
              LinkOption.NOFOLLOW_LINKS));
      FileStore store = Files.getFileStore(Paths.get(XANADU_TXT));
      long total = store.getTotalSpace() / 1024 / 1024 / 1024;
      System.out.println("Storage overall in GB: " + total);
      long used = (store.getTotalSpace()
              - store.getUnallocatedSpace()) / 1024 / 1024 / 1024;
      System.out.println("Storage used in GB: " + used);
      long avail = store.getUsableSpace() / 1024 / 1024 / 1024;
      System.out.println("Storage available in GB: " + avail);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    Path path = Paths.get("filename.extension");
    try {
      // create an empty file
      Files.createFile(path);
      //Delete a file
      Files.delete(path);
      // alternative way to delete a file to prevent throwing an exception
      //Files.deleteIfExists(path);
    } catch (NoSuchFileException x) {
      System.err.format("%s: no such" + " file or directory%n", path);
    } catch (DirectoryNotEmptyException x) {
      System.err.format("%s not empty%n", path);
    } catch (IOException x) {
      // File permission problems are caught here.
      System.err.println(x);
    }


  }

}
