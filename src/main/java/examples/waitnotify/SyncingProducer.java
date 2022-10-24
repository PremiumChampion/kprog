package examples.waitnotify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The ConsoleProducer interacts with the console. The user types in the number of elements to be
 * created and written into the buffer. The ConsoleProducer is a Thread and has to be started
 * outside this class.
 */
public class SyncingProducer extends Thread {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SyncingProducer.class);
  private final Buffer<Element> buffer;
  BufferedReader console;
  private boolean stillWorking;

  /**
   * Constructor.
   *
   * @param buffer buffer to add the generated elements to
   */
  public SyncingProducer(Buffer<Element> buffer) {
    this.buffer = buffer;
    stillWorking = true;
    console = new BufferedReader(new InputStreamReader(System.in));
  }

  @Override
  public void run() {
    int numberOfElements = 0;
    while (stillWorking) {
      // get the number of new elements to be created and pushed into the buffer
      try {
        System.out.println("How many elements should I create? Type in a positive number:");
        String line = console.readLine();
        numberOfElements = Integer.parseInt(line);
      } catch (NumberFormatException e) {
        System.out.println("A positive Number!!!");
      } catch (IOException e) {
        e.printStackTrace();
        stillWorking = false;
      }

      for (int loop = 0; loop < numberOfElements; loop++) {
        Element element = new Element(1000);
        boolean notInserted = true;
        do {
          try {
            buffer.add(element);
            logger.info("added Element {} to the queue.", element.getId());
            synchronized (buffer.syncWhenEmpty) {
              buffer.syncWhenEmpty.notifyAll();
            }
            notInserted = false;
          } catch (NoSpaceLeftException e) {
            logger.info("No space ...");
            waitForSpaceInBuffer();
          }
        } while (notInserted);
      }


    }
  }

  private void waitForSpaceInBuffer() {
    synchronized (buffer.syncWhenFull) {
      try {
        logger.info("Producer going to wait().");
        buffer.syncWhenFull.wait();
        logger.info("Producer is back.");
      } catch (InterruptedException e) {
        e.printStackTrace();
        stillWorking = false;
      }
    }
  }
}
