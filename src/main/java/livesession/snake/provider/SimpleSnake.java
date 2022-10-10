package livesession.snake.provider;

import java.util.LinkedList;
import java.util.List;
import livesession.snake.Coordinate;
import livesession.snake.Direction;
import livesession.snake.Snake;

public class SimpleSnake implements Snake {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnake.class);
  private final InternalBoard board;
  private final ExtendedSnakeService service;
  private final LinkedList<Coordinate> position;
  private Direction direction;

  public SimpleSnake(final ExtendedSnakeService service) {
    this.service = service;
    board = service.getInternalBoard();
    position = new LinkedList<>();
    position.addFirst(board.getStartPosition());
    direction = Direction.EAST;
  }

  public Coordinate advance() throws IllegalPositionException {
    // TODO: advance the snake
    return null;
  }

  @Override
  public List<Coordinate> getPosition() {
    // TODO: create cloned data
    return position;
  }

  @Override
  public Direction getDirection() {
    return direction;
  }

  public void goLeft() {
    direction = direction.getLeft();
  }

  public void goRight() {
    direction = direction.getRight();
  }

}
