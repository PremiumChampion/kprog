package examples.threads;

import examples.helpers.FormattingPrintStream;
import examples.helpers.PrototypeStopWatch;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * More elaborated counting example with threads.
 */
public class ElaboratedCountingThread extends Thread {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ElaboratedCountingThread.class);

  private final int maxCount;
  private final int numberLoops;
  FormattingPrintStream printStream;

  /**
   * Constructor of the {@link ElaboratedCountingThread}.
   *
   * @param maxCount Number up to which the code shall count in each loop
   * @param numberLoops Number of loops to count in each thread
   * @param printStream PrintStream to write the output in a formatted way
   */
  public ElaboratedCountingThread(final int maxCount, final int numberLoops,
                                  FormattingPrintStream printStream) {
    this.maxCount = maxCount;
    this.numberLoops = numberLoops;
    this.printStream = printStream;
  }

  /**
   * Starts the elaborated counting example using threads.
   *
   * @param args There are no command line arguments expected.
   */
  public static void main(String[] args) {
    FormattingPrintStream printStream = new FormattingPrintStream(System.err, 150);
    PrototypeStopWatch stopWatch = new PrototypeStopWatch();

    List<Thread> threadList = new ArrayList<>();
    stopWatch.startNow();
    for (int loop = 0; loop < 10; loop++) {
      ElaboratedCountingThread simpleCountingThread = new ElaboratedCountingThread(10000, 10,
              printStream);
      threadList.add(simpleCountingThread);
      simpleCountingThread.start();
      logger.info("Thread {} started.", loop);
    }

    for (Thread thread : threadList) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Duration duration = stopWatch.stopNow();

    logger.info("Time needed is " + duration.toMillis());
  }

  @Override
  public void run() {
    super.run();
    for (int loop = 0; loop < numberLoops; loop++) {
      countToMaxCount();
      logger.info("Loop {} done.", loop);
    }
    printStream.print(Thread.currentThread().getName() + " dies.  | ");
  }

  private void countToMaxCount() {
    int loop = 0;
    ;

    while (loop < maxCount) {
      loop++;
      if ((loop % 100) == 0) {
        printStream.print(Thread.currentThread().getName() + " : " + loop + " | ");
      }
    }
  }
}
