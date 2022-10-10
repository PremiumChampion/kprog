package livesession.snake.provider;

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;

public class BaseBoard implements Board {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BaseBoard.class);

  protected int size;
  protected BoardState[][] board;

  public BaseBoard(final int size) {
    assertSizeIsGreaterThan(size, MINIMAL_BOARD_SIZE);
    this.size = size;
    board = new BoardState[size][size];
  }

  protected void assertSizeIsGreaterThan(final int size, final int minimalBoardsize) {
    if (size < minimalBoardsize) {
      throw new IllegalArgumentException("Board size of " + size + " is smaller than the minimal " +
          "board size of " + minimalBoardsize);
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public BoardState getStateFromPosition(final Coordinate coordinate) {
    return getStateFromPosition(coordinate.getRow(), coordinate.getColumn());
  }

  @Override
  public BoardState getStateFromPosition(final int row, final int column) {
    assertPositionIsOnBoard(row, column);
    return board[row][column];
  }

  protected void assertPositionIsOnBoard(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate.getRow(), coordinate.getColumn());
  }
  protected void assertPositionIsOnBoard(final int row, final int column) {
    // TODO implement it throwing an IllegalArgumentException with the position.
  }
}
