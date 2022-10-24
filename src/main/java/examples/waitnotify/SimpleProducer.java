package examples.waitnotify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The ConsoleProducer interacts with the console. The user types in the number of elements to be
 * created and written into the buffer. The ConsoleProducer is a Thread and has to be started
 * outside this class.
 */
public class SimpleProducer extends Thread {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleProducer.class);
  private final BlockingBuffer<Element> buffer;
  BufferedReader console;
  private boolean stillWorking;

  /**
   * Constructor.
   *
   * @param buffer buffer to add the generated elements to
   */
  public SimpleProducer(BlockingBuffer<Element> buffer) {
    this.buffer = buffer;
    stillWorking = true;
    console = new BufferedReader(new InputStreamReader(System.in));
  }

  @Override
  public void run() {
    int numberOfElements = 0;
    while (stillWorking) {
      // get the number of new elements to be created and pushed into the buffer
      numberOfElements = getNumberOfElements();

      for (int loop = 0; loop < numberOfElements; loop++) {
        try {
          Element element = new Element(1000);
          buffer.put(element);
          logger.info("added Element {} to the queue.", element.getId());
        } catch (InterruptedException e) {
          logger.error(e.getMessage());
          stillWorking = false;
        }
      }
    }
  }

  /**
   * Asks the user how many elements should be created and added to the buffer.
   *
   * @return number of elements to be created
   */
  private int getNumberOfElements() {
    int numberOfElements = 0;
    try {
      System.out.println("How many elements should I create? Type in a positive number:");
      String line = console.readLine();
      numberOfElements = Integer.parseInt(line);
    } catch (NumberFormatException e) {
      System.out.println("A positive Number!!!");
    } catch (IOException e) {
      logger.error(e.getMessage());
      stillWorking = false;
    }
    return numberOfElements;
  }
}
