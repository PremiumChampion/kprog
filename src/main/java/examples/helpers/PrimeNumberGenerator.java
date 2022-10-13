package examples.helpers;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple PrimeNumberGenerator. It uses a brute force approach to detect prime numbers in the
 * interval from 2 to the given limit.
 */
public class PrimeNumberGenerator {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PrimeNumberGenerator.class);

  private int limit;

  /**
   * Constructor.
   *
   * @param limit limit up to which the prime number shall be calculated.
   */
  public PrimeNumberGenerator(final int limit) {
    this.limit = limit;
  }

  /**
   * Calculates the prime numbers from 2 up to the given limit.
   *
   * @return list of Integer object which are prime numbers in the given interval
   */
  public List<Integer> primeNumbersByBruteForce() {
    List<Integer> primeNumbers = new LinkedList<>();
    for (int candidate = 2; candidate <= limit; candidate++) {
      if (isPrimeBruteForce(candidate)) {
        primeNumbers.add(candidate);
      }
    }
    return primeNumbers;
  }

  private boolean isPrimeBruteForce(final int candidate) {
    for (int loop = 2; loop < candidate; loop++) {
      if (candidate % loop == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Starts the demo program.
   *
   * @param args no command line args are expected
   */
  public static void main(String[] args) {
    int limit = 100000;
    PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator(limit);
    PrototypeStopWatch stopWatch = new PrototypeStopWatch();
    stopWatch.startNow();
    List<Integer> results = primeNumberGenerator.primeNumbersByBruteForce();
    Duration duration = stopWatch.stopNow();
    logger.info("Calculating {} prime numbers within the limit of {} in {} milliseconds.",
            results.size(), limit, duration.toMillis());
  }
}
