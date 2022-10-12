package livesession.snake;

/**
 * Configures a snake game. This class is intended to be immutable.
 */
public class GameConfiguration {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameConfiguration.class);

  private int size;
  private int velocityInMilliSeconds;
  private int numberOfFood;

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
}
