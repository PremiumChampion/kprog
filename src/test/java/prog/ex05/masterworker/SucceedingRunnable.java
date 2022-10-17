package prog.ex05.masterworker;

public class SucceedingRunnable implements Runnable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SucceedingRunnable.class);

  private final int sleepTime;

  public SucceedingRunnable(final int sleepTime) {
    this.sleepTime = sleepTime;
  }

  @Override
  public void run() {
    try {
      if (sleepTime > 0) {
        Thread.sleep(sleepTime);
      }
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    logger.info("Succeeding runnable finishes with success.");
  }
}
