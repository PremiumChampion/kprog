package examples.threads;

import examples.helpers.PrimeNumberGenerator;
import examples.helpers.PrototypeStopWatch;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Example to demonstrate multithreading using prime number calculation.
 */
public class PrimeCalculator implements Runnable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PrimeCalculator.class);

  private final int limit;

  /**
   * Constructor.
   *
   * @param limit limit up to which the prime numbers shall be calculated
   */
  public PrimeCalculator(final int limit) {
    this.limit = limit;
  }

  @Override
  public void run() {
    PrimeNumberGenerator generator = new PrimeNumberGenerator(limit);
    List<Integer>  primeList = generator.primeNumbersByBruteForce();
    logger.info(Thread.currentThread().getName() + " finishes with {} numbers for limit {}.",
            primeList.size(), limit);
  }

  /**
   * Main to run the example.
   *
   * @param args No args are expected.
   */
  public static void main(String[] args) {
    PrototypeStopWatch stopWatch = new PrototypeStopWatch();

    List<Thread> threadList = new ArrayList<>();
    stopWatch.startNow();
    for (int loop = 0; loop < 10; loop++) {
      Thread thread = new Thread(new PrimeCalculator(100000));
      threadList.add(thread);
      // Attention: with  "run()" it is a sequential action, with "start()" it is a parallel action.
      thread.start();
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

    logger.info("Time needed: {} milliseconds", duration.toMillis());

  }
}
