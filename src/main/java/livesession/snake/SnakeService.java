package livesession.snake;

public interface SnakeService {
  void reset();
  void start();
  void abort();
  void pause();
  void resume();
  void moveLeft();
  void moveRight();
  void addListener(SnakeListener listener);
  void removeListener(SnakeListener listener);
  void configure(GameConfiguration configuration) throws IllegalConfigurationException;
  Snake getSnake();
}
