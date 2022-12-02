package livesession.snake.provider;

import java.util.LinkedList;
import java.util.List;
import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.Direction;
import livesession.snake.Snake;

/**
 * Simple and straight-forward implementation of the Snake interface.
 */
public class SimpleSnake implements Snake {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnake.class);
  private final InternalBoard board;
  private final ExtendedSnakeService service;
  // The LinkedList allows direct access to the head (first) and tail (last) of the snake.
  private final LinkedList<Coordinate> position;
  private Direction direction;

  /**
   * Creates a snake. A snake has at the beginning a length of 1.
   *
   * @param service service as single source of truth to get the internal board and to notify on
   *                events during wiggling.
   */
  public SimpleSnake(final ExtendedSnakeService service) {
    this.service = service;
    board = service.getInternalBoard();
    position = new LinkedList<>();
    position.addFirst(board.getStartPosition());
    direction = Direction.EAST;
  }

  /**
   * Advances the snake in the direction the snake is oriented to.
   *
   * @return new position of the head of the snake
   * @throws IllegalPositionException if the position is not allowed
   */
  public Coordinate advance() throws IllegalPositionException {
    // TODO: (DONE) advance the snake
    Direction currentDirection = this.direction;

    int moveRow = 0;
    int moveCol = 0;

    if (currentDirection == Direction.NORTH) {
      moveRow = -1;
      moveCol = 0;
    }

    if (currentDirection == Direction.EAST) {
      moveRow = 0;
      moveCol = +1;
    }

    if (currentDirection == Direction.SOUTH) {
      moveRow = +1;
      moveCol = 0;
    }

    if (currentDirection == Direction.WEST) {
      moveRow = 0;
      moveCol = -1;
    }
    Coordinate currentHead = this.position.getFirst();

    Coordinate nextPosition = new Coordinate(currentHead.getRow() + moveRow,
        currentHead.getColumn() + moveCol);

    BoardState newPosBoardState = assertNewPositionIsPossible(nextPosition);

    this.position.addFirst(nextPosition);

    if (newPosBoardState != BoardState.FOOD) {
      this.position.removeLast();
    } else {
      this.service.foodEaten(nextPosition);
    }
    // TODO: end
    return nextPosition;
  }

  private BoardState assertNewPositionIsPossible(final Coordinate newHead)
      throws IllegalPositionException {
    // TODO: (DONE) Check if the position is valid
    switch (this.board.getStateFromPosition(newHead)) {
      case FOOD:
      case GRASS:
        // valid options
        break;
      case WALL:
      case SNAKE:
      default:
        throw new IllegalPositionException(newHead,
            this.board.getStateFromPosition(newHead));
    }

    if (this.position.contains(newHead)) {
      throw new IllegalPositionException(newHead,
          BoardState.SNAKE);
    }
    // TODO: end
    return this.board.getStateFromPosition(newHead);
  }

  @Override
  public List<Coordinate> getPosition() {
    return position;
  }

  @Override
  public Direction getDirection() {
    return direction;
  }

  /**
   * Changes the direction of the snake by turning left.
   */
  public void goLeft() {
    direction = direction.getLeft();
  }

  /**
   * Changes the direction of the snake by turning right.
   */
  public void goRight() {
    direction = direction.getRight();
  }
}
