package livesession.snake.provider;

public interface GameLoop extends Runnable {
  @Override
  void run();

  void pauseGame();

  void resumeGame();

  void stopGame();
}
