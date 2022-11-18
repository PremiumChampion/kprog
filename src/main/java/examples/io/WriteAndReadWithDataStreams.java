package examples.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Example for output and input with DataOutputStream and DataInputStream.
 * https://docs.oracle.com/javase/tutorial/essential/io/datastreams.html
 */
public class WriteAndReadWithDataStreams {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(WriteAndReadWithDataStreams.class);

  /**
   * Main method. No args expected.
   *
   * @param args no args expected.
   * @throws IOException if anything goes wrong in the IO sections.
   */
  public static void main(String[] args) throws IOException {
    String dataFile = "invoicedata";

    double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
    int[] units = {12, 8, 13, 29, 50};
    String[] descs = {"Java T-shirt", "Java Mug", "Duke Juggling Dolls",
        "Java Pin", "Java Key Chain"};
    try (DataOutputStream out =
                 new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));) {
      for (int i = 0; i < prices.length; i++) {
        out.writeDouble(prices[i]);
        out.writeInt(units[i]);
        out.writeUTF(descs[i]);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    double total = 0.0;
    try (DataInputStream in =
                 new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));) {
      double price;
      int unit;
      String desc;
      while (true) {
        price = in.readDouble();
        unit = in.readInt();
        desc = in.readUTF();
        System.out.format("You ordered %d units of %s at $%.2f %n", unit, desc, price);
        total += unit * price;
      }
    } catch (EOFException e) { // end of file
      System.out.format("For a TOTAL of: $%.2f%n", total);
    }
  }
}
