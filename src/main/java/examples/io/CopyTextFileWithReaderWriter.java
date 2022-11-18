package examples.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demonstrates copying of a text file.
 */
public class CopyTextFileWithReaderWriter {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CopyTextFileWithReaderWriter.class);

  /**
   * main method. No args are expected.
   *
   * @param args no args are expected
   */
  public static void main(String[] args) {
    Charset charset = StandardCharsets.ISO_8859_1;
    Path pathToSource = Paths.get("demofiles/io/xanadu.txt");
    Path pathToSink = Paths.get("demofiles/output/newFile.txt");
    try (BufferedReader reader = Files.newBufferedReader(pathToSource, charset);
         BufferedWriter writer = Files.newBufferedWriter(pathToSink, charset)) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }
}


