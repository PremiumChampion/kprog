package livesession.snake;

import java.util.StringJoiner;

import static livesession.snake.Board.MINIMAL_BOARD_SIZE;
import static livesession.snake.SnakeService.DEFAULT_NUMBER_OF_FOOD;
import static livesession.snake.SnakeService.DEFAULT_SIZE;
import static livesession.snake.SnakeService.DEFAULT_VELOCITY;

/**
 * Configures a snake game. This class is intended to be immutable.
 */
public class GameConfiguration {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameConfiguration.class);
  public static final GameConfiguration DEFAULT_GAME_CONFIGURATION = new GameConfiguration(
      DEFAULT_SIZE, DEFAULT_VELOCITY, DEFAULT_NUMBER_OF_FOOD);

  private final int size;
  private final int velocityInMilliSeconds;
  private final int numberOfFood;

  /**
   * Creates a new game configuration.
   *
   * @param size size of the board. The minimum size of a board can be found in the class Board.
   * @param velocityInMilliSeconds initial time in milliseconds between to wiggles of the snake
   * @param numberOfFood number of food items on the board when the game starts
   */
  public GameConfiguration(final int size, final int velocityInMilliSeconds,
                           final int numberOfFood) {
    this.size = size;
    this.velocityInMilliSeconds = velocityInMilliSeconds;
    this.numberOfFood = numberOfFood;
  }

  public int getSize() {
    return size;
  }

  public int getVelocityInMilliSeconds() {
    return velocityInMilliSeconds;
  }

  public int getNumberOfFood() {
    return numberOfFood;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GameConfiguration.class.getSimpleName() + "[", "]")
        .add("size=" + size)
        .add("velocityInMilliSeconds=" + velocityInMilliSeconds)
        .add("numberOfFood=" + numberOfFood)
        .toString();
  }
}
