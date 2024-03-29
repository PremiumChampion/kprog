package livesession.snake.provider;

import java.util.Random;
import livesession.snake.Coordinate;
import livesession.snake.Snake;

/**
 * Simple FoodGenerator class for the snake game.
 */
public class FoodGenerator {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      FoodGenerator.class);

  private final SimpleSnakeService service;
  private final Random random;

  /**
   * Constructor.
   *
   * @param service SnakeService the FoodGenerator is assigned to
   */
  public FoodGenerator(final SimpleSnakeService service) {
    this.service = service;
    // Use a seed to make the series of "random" coordinates deterministic. Helps with tests.
    this.random = new Random(42);
  }

  /**
   * place food.
   *
   * @return new food coordinate.
   */
  public Coordinate placeFood() {
    // TODO: (DONE) place the food randomly.
    Coordinate coordinate = new Coordinate(
        1 + this.random.nextInt(service.getConfiguration().getSize() - 2),
        1 + this.random.nextInt(service.getConfiguration().getSize() - 2));
    // TODO: end.
    return coordinate;
  }

  private Coordinate getRandomCoordinate() {
    int size = service.getBoard().size();

    int row = random.nextInt(size);
    int column = random.nextInt(size);

    return new Coordinate(row, column);
  }
}
