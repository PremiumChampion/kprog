package livesession.snake;

public class Coordinate {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Coordinate.class);
  private int row;
  private int column;

  public Coordinate(final int row, final int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }
}
