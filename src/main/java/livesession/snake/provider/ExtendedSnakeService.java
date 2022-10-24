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

  void triggeredByGameLoop();

  /**
   * Triggers the movement of the snake.
   *
   * @throws IllegalPositionException if the snake reaches an invalid position, e.g. WALL or SNAKE
   */
  void advanceSnake() throws IllegalPositionException;

  /**
   * Adds food to the given position.
   * @param coordinate coordinate to add food to.
   */
  void addFood(Coordinate coordinate);

  /**
   * Signals that the food has been eaten.
   *
   * @param coordinate coordinate where the food has been eaten.
   */
  void foodEaten(Coordinate coordinate);

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
