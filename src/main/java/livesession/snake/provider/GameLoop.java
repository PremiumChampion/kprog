package livesession.snake.provider;

/**
 * Generic GameLoop interface.
 */
public interface GameLoop extends Runnable {
  @Override
  void run();

  void pauseGame();

  void resumeGame();

  void stopGame();
}
