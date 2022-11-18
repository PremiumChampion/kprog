package examples.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * This demo shows how to copy a text file using Character Streams.
 *
 * @author Conny Rabe, Martin Haag, Jörg Winckler
 */
public class MacToWindowsTextFileConverter {
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
    Charset macosCharset = StandardCharsets.UTF_8;
    Charset windowsCharset = StandardCharsets.ISO_8859_1;

    try (FileReader reader = new FileReader(fileNameFrom, macosCharset);
         FileWriter writer = new FileWriter(fileNameTo, windowsCharset);) {
      System.err.println(reader.getEncoding());
      int value; // value is an int, but reads a byte from -1 to 255
      while ((value = reader.read()) != -1) { // -1=EOF
        writer.write(value); // very slow. Faster version uses buffering.
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


