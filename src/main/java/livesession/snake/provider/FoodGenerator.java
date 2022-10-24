package livesession.snake.provider;

import java.util.Random;
import livesession.snake.Coordinate;

public class FoodGenerator {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FoodGenerator.class);

  private SimpleSnakeService service;
  private Random random;

  public FoodGenerator(final SimpleSnakeService service) {
    this.service = service;
    // Use a seed to make the series of "random" coordinates deterministic. Helps with tests.
    this.random = new Random(42);
  }

  Coordinate placeFood() {
    Coordinate coordinate = null;
    // TODO: place the food randomly.

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
