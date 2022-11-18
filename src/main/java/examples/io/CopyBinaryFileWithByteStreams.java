package examples.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This demo shows how to copy an arbitrary file using Byte Streams.
 *
 * @author Conny Rabe, Martin Haag, Jörg Winckler
 */
public class CopyBinaryFileWithByteStreams {
  /**
   * Main.
   *
   * @param args first parameter is filename of source file to be copied, second
   *             parameter is the filename of the target file
   * @throws IOException if any IO operation fails
   */
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.err.println("copy needs two filenames to work.");
      System.exit(0);
    }
    copyFromFileToFile(args[0], args[1]);
  }

  private static void copyFromFileToFile(String fileNameFrom, String fileNameTo) {
    try (FileInputStream reader = new FileInputStream(fileNameFrom);
         FileOutputStream writer = new FileOutputStream(fileNameTo);) {
      int value; // value is an int, but reads a byte from -1 to 255
      while ((value = reader.read()) != -1) { // -1=EOF
        writer.write(value); // very slow. Faster version uses buffering.
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


