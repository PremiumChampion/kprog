package livesession.snake.provider;

/**
 * Simple implementation of the GameLoop interface for the game snake.
 */
public class SimpleGameLoop extends Thread implements GameLoop {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleGameLoop.class);

  /**
   * Constructor.
   *
   * @param service   ExtendedSnakeService to be notified every loop
   * @param sleepTime time between two notifications in milliseconds
   */
  public SimpleGameLoop(final ExtendedSnakeService service,
                        final int sleepTime) {
  }

  @Override
  public void run() {

  }

  @Override
  public void pauseGame() {

  }

  @Override
  public void resumeGame() {

  }

  @Override
  public void stopGame() {

  }
}
