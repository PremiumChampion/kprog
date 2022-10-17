package prog.ex05.masterworker;

/**
 * This runnable sleeps some amount of time and then grabs the name of the executing thread to
 * pass it to a {@link ThreadNameCollector}.
 */
public class GrabbingThreadNameRunnable implements Runnable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(GrabbingThreadNameRunnable.class);

  private ThreadNameCollector collector;
  private long sleepingTime;

  public GrabbingThreadNameRunnable(final ThreadNameCollector collector, long sleepingTime) {
    this.collector = collector;
    this.sleepingTime = sleepingTime;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(sleepingTime);
      collector.addThreadName(Thread.currentThread().getName());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
