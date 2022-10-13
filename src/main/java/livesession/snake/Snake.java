package livesession.snake;

import java.util.List;

/**
 * Models a snake.
 */
public interface Snake {
  /**
   * returns a list of coordinates of the snake position. In the first index is the snake head,
   * in the last one is the tail.
   *
   * @return list of coordinates where the snake is positioned
   */
  List<Coordinate> getPosition();

  /**
   * Direction the snake is heading to.
   *
   * @return direction the snake is heading to
   */
  Direction getDirection();
}
