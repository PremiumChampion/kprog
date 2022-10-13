package livesession.snake.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
  private InternalBoard internalBoard;

  @Before
  public void setup() {
    fakeBoard = new FakeInternalBoard(DEFAULT_SIZE);
    internalBoard = new InternalBoard(DEFAULT_SIZE);
    fakeService = new FakeSnakeService(fakeBoard);
    snake = new SimpleSnake(fakeService);
  }

  @Test @Ignore
  public void checkStartingPosition(){
    Coordinate startPosition = fakeBoard.getStartPosition();
    List<Coordinate> snakePosition = snake.getPosition();
    assertEquals(snakePosition.get(0), startPosition);
    assertEquals( Direction.EAST, snake.getDirection());
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

  @Test @Ignore
  public void checkAdvanceToWall() {
    // prepare wall position
    Coordinate startPosition = fakeBoard.getStartPosition();
    int snakeLength = snake.getPosition().size();
    Coordinate wallPosition = startPosition.getNeighbor(Direction.EAST);
    fakeBoard.setPosition(wallPosition, BoardState.WALL);

    // let the snake go one step (wiggle)
    try {
      Coordinate snakeHead = snake.advance();
      fail("Snake should hit the wall and throw an IllegalPositionException.");
    } catch (IllegalPositionException e) {
      assertEquals(wallPosition, e.getCoordinate());
      assertEquals(BoardState.WALL, e.getState());
    }
  }

  @Test @Ignore
  public void checkAdvanceToSnake() throws IllegalPositionException {
    // prepare food position
    Coordinate startPosition = fakeBoard.getStartPosition();
    int snakeLength = snake.getPosition().size();
    Coordinate foodPosition = startPosition.getNeighbor(Direction.EAST);
    fakeBoard.setPosition(foodPosition, BoardState.FOOD);

    // let the snake go one step (wiggle)
    Coordinate snakeHead = snake.advance();

    // Now lets turn the snake 180 degrees
    snake.goLeft();
    snake.goLeft();

    try {
      snake.advance();
      fail("Snake should hit itself and throw an IllegalPositionException.");
    } catch (IllegalPositionException e){
      assertEquals(startPosition, e.getCoordinate());
      assertEquals(BoardState.SNAKE, e.getState());
    }


  }
}
