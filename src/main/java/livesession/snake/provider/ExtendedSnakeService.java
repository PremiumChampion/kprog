package livesession.snake.provider;

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.Reason;
import livesession.snake.SnakeService;

/**
 * Models the capabilities of an internal SnakeService implementation. This interface supports
 * the creation of mock implementations for testing purposes.
 */
public interface ExtendedSnakeService extends SnakeService {
  /**
   * Returns the external board including the snake position. Ready to be visualized. <b>This board
   * doesn't  get updated</b>. To get an updated external board another call of this method is
   * needed.
   *
   * @return board with all elements: GRASS, FOOD, WALL and SNAKE.
   */
  Board getExternalBoard();

  /**
   * Ends the game.
   *
   * @param reason reason of failure
   */
  void failed(Reason reason);

  /**
   * Triggers the calculation of the next round. Only allowed in GameState RUNNING.
   *
   * @throws IllegalStateException is the GameState is not appropriate
   */
  void triggeredByGameLoop() throws IllegalStateException;

  /**
   * Triggers the movement of the snake. This method may only be called if the GameState is RUNNING.
   *
   * @throws IllegalPositionException if the snake reaches an invalid position, e.g. WALL or SNAKE
   * @throws IllegalStateException is the GameState is not appropriate
   */
  void advanceSnake() throws IllegalPositionException, IllegalStateException;

  /**
   * Adds food to the given position.
   *
   * @param coordinate coordinate to add food to.
   * @throws IllegalArgumentException if there is already food or the position is not allowed.
   */
  void addFood(Coordinate coordinate) throws IllegalArgumentException;

  /**
   * Signals that the food has been eaten.
   *
   * @param coordinate coordinate where the food has been eaten.
   * @throws IllegalArgumentException if there is no food at the given position
   */
  void foodEaten(Coordinate coordinate) throws IllegalArgumentException;

  /**
   * Changes the score on behalf of the reason, the BoardState.
   *
   * @param state reason for the change.
   */
  void updateScore(BoardState state);

  /**
   * Returns the internal board. The internal board gets updated regularly.
   *
   * @return InternalBoard object
   */
  InternalBoard getInternalBoard();
}
