package livesession.snake;

public interface SnakeService {
  /**
   * Resets the game. The new game state is PREPARED.
   */
  void reset();

  /**
   * Starts the game. The new game state is RUNNING.
   */
  void start();

  /**
   * Aborts the game. The new game state is ABORTED.
   */
  void abort();

  /**
   * Pauses the game. The new game state is PAUSED.
   */
  void pause();

  /**
   * Resumes the game. the new game state is RUNNING.
   */
  void resume();

  /**
   * Changes the snake direction by turning left.
   */
  void moveLeft();

  /**
   * Changes the snake direction by turning right.
   */
  void moveRight();

  /**
   * Adds a listener for game updates. Each listener can only be added once.
   *
   * @param listener listener to be added
   * @return true if the listener could be added. Otherwise, false.
   */
  boolean addListener(SnakeListener listener);

  /**
   * Removes a listener from the list of listeners.
   *
   * @param listener listener to be removed.
   * @return true if the listener could be removed. Otherwise, false.
   */
  boolean removeListener(SnakeListener listener);
  void configure(GameConfiguration configuration) throws IllegalConfigurationException;
  Snake getSnake();
}
