package livesession.snake;

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

  public Direction getLeft() {
    return left;
  }

  public Direction getRight() {
    return right;
  }

  }
