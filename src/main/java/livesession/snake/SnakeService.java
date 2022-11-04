package livesession.snake;

public interface SnakeService {

  final int DEFAULT_SIZE = 20;
  final int DEFAULT_VELOCITY = 500;
  final int DEFAULT_NUMBER_OF_FOOD = 1;

  /**
   * Resets the game. The new game state is PREPARED. Reset means reset, no matter in which
   * GameState the game is.
   */
  void reset();

  /**
   * Starts the game if the GameState is PREPARED. The new game state is RUNNING.
   *
   * @throws IllegalStateException if the GameState was not PREPARED.
   */
  void start() throws IllegalStateException;

  /**
   * Aborts the game if the GameState is RUNNING or PAUSED. The new game state is ABORTED.
   *
   * @throws IllegalStateException is the GameState is PREPARED
   */
  void abort() throws IllegalStateException;

  /**
   * Pauses the game if the GameState is RUNNING. The new game state is PAUSED.
   *
   * @throws IllegalStateException is the GameState is not RUNNING
   */
  void pause() throws IllegalStateException;

  /**
   * Resumes the game if the GameState is PAUSED. the new game state is RUNNING.
   *
   * @throws IllegalStateException is the GameState is not PAUSED
   */
  void resume() throws IllegalStateException;

  /**
   * Changes the snake direction by turning left if the GameState is RUNNING.
   *
   * @throws IllegalStateException is the GameState is not appropriate
   */
  void moveLeft() throws IllegalStateException;

  /**
   * Changes the snake direction by turning right if the GameState is RUNNING.
   *
   * @throws IllegalStateException is the GameState is not appropriate
   */
  void moveRight() throws IllegalStateException;

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

  /**
   * Configures the next game if GameState is PREPARED or ABORTED.
   *
   * @param configuration contains the game configuration parameters
   * @throws IllegalConfigurationException if the values cannot be accepted due whatever reason
   * @throws IllegalStateException         if the game is not in the assumed GameState
   */
  void configure(GameConfiguration configuration) throws IllegalConfigurationException;

  /**
   * Returns the actual configuration.
   *
   * @return actual GameConfiguration
   */
  GameConfiguration getConfiguration();

  /**
   * Returns the snake.
   *
   * @return snake
   */
  Snake getSnake();

  /**
   * Returns the board including SNAKE, WALL, GRASS and FOOD.
   *
   * @return board with all elements
   */
  Board getBoard();
}
