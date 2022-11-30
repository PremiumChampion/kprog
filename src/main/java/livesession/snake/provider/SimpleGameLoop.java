package livesession.snake.provider;

/**
 * Simple implementation of the GameLoop interface for the game snake.
 */
public class SimpleGameLoop extends Thread implements GameLoop {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleGameLoop.class);
  private final ExtendedSnakeService service;
  private final int sleepTime;
  private boolean shouldPause;
  private boolean shouldTerminate;

  /**
   * Constructor.
   *
   * @param service   ExtendedSnakeService to be notified every loop
   * @param sleepTime time between two notifications in milliseconds
   */
  public SimpleGameLoop(final ExtendedSnakeService service,
      final int sleepTime) {
    this.service = service;
    this.sleepTime = sleepTime;
    this.shouldPause = false;
    this.shouldTerminate = false;
    setDaemon(true);
    start();
  }

  @Override
  public void run() {
    while (!shouldTerminate) {
      try {
        sleep(sleepTime);
      } catch (InterruptedException e) {
        shouldTerminate = true;
        continue;
      }
      if (shouldPause) {
        try {
          synchronized (this) {
            wait();
          }
        } catch (InterruptedException e) {
          shouldTerminate = true;
          continue;
        }
      }

      if(shouldTerminate){
        return;
      }

      service.triggeredByGameLoop();
    }
  }

  @Override
  public void pauseGame() {
    shouldPause = true;
  }

  @Override
  public void resumeGame() {
    shouldPause = false;
    synchronized (this) {
      notify();
    }
  }

  @Override
  public void stopGame() {
    shouldTerminate = true;
    synchronized (this) {
      notify();
    }
  }
}
