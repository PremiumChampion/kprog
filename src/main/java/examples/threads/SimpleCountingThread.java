package examples.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple counting example to demonstrate multithreading.
 */
public class SimpleCountingThread extends Thread {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleCountingThread.class);

  private static final int COUNTER_LIMIT = 40;
  private static final int LOG_POINT = 10;

  /**
   * Starts the demo.
   *
   * @param args there are no command line arguments expected
   * @throws InterruptedException if one of the threads get interrupted
   */
  public static void main(String[] args) throws InterruptedException {
    final int maxThreads = 3;

    List<Thread> threadList = new ArrayList<>();

    for (int loop = 0; loop < maxThreads; loop++) {
      Thread tmpThread = new SimpleCountingThread();
      logger.info("Thread {} created.", tmpThread);
      threadList.add(tmpThread);
      tmpThread.start();
    }

    for (Thread thread : threadList) {
      thread.join();
    }
    logger.info("All threads terminated.");
  }

  @Override
  public void run() {
    try {
      countToMaxCount();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void countToMaxCount() throws InterruptedException {
    int loop = 0;

    while (loop < COUNTER_LIMIT) {
      Thread.sleep(50);
      loop++;
      if ((loop % LOG_POINT) == 0) {
        logger.info("Counting reached {}.", loop);
      }
    }
    logger.info("Thread " + Thread.currentThread() + " ends now.");
  }
}


