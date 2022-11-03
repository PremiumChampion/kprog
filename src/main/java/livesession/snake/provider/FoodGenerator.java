package livesession.snake.provider;

import java.util.Random;
import livesession.snake.Coordinate;
import livesession.snake.Snake;

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
    Coordinate coordinate;
    // TODO: (DONE) place the food randomly.
    Snake s = service.getSnake();
    Random r = new Random();
    do {
      coordinate = new Coordinate(r.nextInt(service.getConfiguration().getSize()), r.nextInt(service.getConfiguration().getSize()));
    } while (s.getPosition().contains(coordinate));
    // TODO: end.
    service.addFood(coordinate);
    return coordinate;

  }

  private Coordinate getRandomCoordinate() {
    int size = service.getBoard().size();

    int row = random.nextInt(size);
    int column = random.nextInt(size);

    return new Coordinate(row, column);
  }
}
