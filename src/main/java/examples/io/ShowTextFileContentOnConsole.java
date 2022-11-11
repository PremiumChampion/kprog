package examples.io;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * This demo shows how to copy a text file using Character Streams.
 *
 * @author Conny Rabe, Martin Haag, Jörg Winckler
 */
public class ShowTextFileContentOnConsole {
  /**
   * Main.
   *
   * @param args first parameter is filename of source file to be copied, second
   *             parameter is the filename of the target file
   * @throws IOException if any IO operation fails
   */
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("command needs one file name to work.");
      System.exit(0);
    }
    catFileContentOntoConsole(args[0]);
  }

  private static void catFileContentOntoConsole(String fileNameFrom) {
    Charset macosCharset = StandardCharsets.UTF_8;
    Charset windowsCharset = StandardCharsets.ISO_8859_1;

    try (FileReader reader = new FileReader(fileNameFrom, windowsCharset);) {
      System.err.println(reader.getEncoding());
      StringBuilder stringBuilder = new StringBuilder();
      int value; // value is an int, but reads a byte from -1 to 255
      while ((value = reader.read()) != -1) { // -1=EOF
        stringBuilder.append((char) value);
      }
      System.err.println("--> " + stringBuilder.toString() + " <--");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


