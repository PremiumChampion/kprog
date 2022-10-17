package prog.ex05.masterworker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PrimeFactorRunnable implements Runnable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PrimeFactorRunnable.class);

  private int numberToBeFactorized;
  private ConcurrentLinkedQueue<PrimeFactorResult> queue;

  public PrimeFactorRunnable(final int numberToBeFactorized,
                             final ConcurrentLinkedQueue<PrimeFactorResult> queue) {
    this.numberToBeFactorized = numberToBeFactorized;
    this.queue = queue;
  }

  PrimeFactorResult calculatePrimeFactors() {
    List<Integer> factors = new ArrayList<Integer>();
    int number = numberToBeFactorized;
    for (int loop = 2; loop <= number; loop++) {
      while (number % loop == 0) {
        factors.add(loop);
        number /= loop;
      }
    }

    logger.info("Finishing calculation for number {} with {} factors.", numberToBeFactorized, factors.size());
    return new PrimeFactorResult(numberToBeFactorized, factors);
  }

  @Override
  public void run() {
    PrimeFactorResult result = calculatePrimeFactors();
    queue.add(result);
  }
}
