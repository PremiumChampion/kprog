package livesession.snake;

/**
 * Directions a snake can wiggle.
 */
public enum Direction {
  NORTH, EAST, SOUTH, WEST;

  private Direction left;
  private Direction right;

  static {
    NORTH.left = WEST;
    NORTH.right = EAST;
    SOUTH.left = EAST;
    SOUTH.right = WEST;
    EAST.left = NORTH;
    EAST.right = SOUTH;
    WEST.left = SOUTH;
    WEST.right = NORTH;
  }

  /**
   * Returns the direction when the snake is turning left.
   *
   * @return new direction
   */
  public Direction getLeft() {
    return left;
  }

  /**
   * Returns the direction when the snake is turning right.
   *
   * @return new direction
   */
  public Direction getRight() {
    return right;
  }

  }
