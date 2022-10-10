package livesession.snake.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.Direction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestSimpleSnake {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSimpleSnake.class);
  private static final int DEFAULT_SIZE = 11;

  FakeInternalBoard fakeBoard;
  FakeSnakeService fakeService;
  SimpleSnake snake;

  @Before
  public void setup() {
    fakeBoard = new FakeInternalBoard(DEFAULT_SIZE);
    fakeService = new FakeSnakeService(fakeBoard);
    snake = new SimpleSnake(fakeService);
  }

  @Test @Ignore
  public void checkStartingPosition(){
    Coordinate startPosition = fakeBoard.getStartPosition();
    List<Coordinate> snakePosition = snake.getPosition();
    assertEquals(snakePosition.get(0), startPosition);
    assertEquals(snake.getDirection(), Direction.EAST);
  }

  @Test @Ignore
  public void checkAdvanceOnGrass() throws IllegalPositionException {
    // prepare test data
    int snakeLength = snake.getPosition().size();
    Coordinate startPosition = fakeBoard.getStartPosition();
    Coordinate expectedNewPosition = startPosition.getNeighbor(Direction.EAST);

    // let the snake go one step (wiggle)
    Coordinate snakeHead = snake.advance();

    // check expected values
    assertEquals(expectedNewPosition, snakeHead);
    assertEquals(snakeLength, snake.getPosition().size());
  }

  @Test @Ignore
  public void checkAdvanceOnFood() throws IllegalPositionException {
    // prepare food position
    Coordinate startPosition = fakeBoard.getStartPosition();
    int snakeLength = snake.getPosition().size();
    Coordinate foodPosition = startPosition.getNeighbor(Direction.EAST);
    fakeBoard.setPosition(foodPosition, BoardState.FOOD);

    // let the snake go one step (wiggle)
    Coordinate snakeHead = snake.advance();

    // check expected values
    assertEquals(foodPosition, snakeHead);
    assertEquals(snakeLength+1, snake.getPosition().size());
    assertTrue(fakeService.isEaten());
  }
}
