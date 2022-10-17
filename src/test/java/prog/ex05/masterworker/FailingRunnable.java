package prog.ex05.masterworker;

public class FailingRunnable implements Runnable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FailingRunnable.class);

  private final RuntimeException runtimeException;
  private final int sleepTime;

  public FailingRunnable(final RuntimeException e, int sleepTime) {
    this.runtimeException = e;
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

    logger.info("FailingRunnable goes failing");
    throw runtimeException;
  }
}
