package livesession.snake.provider;

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;

/**
 * Simple and straight-forward implementation of the Board interface.
 */
public class BaseBoard implements Board {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BaseBoard.class);


  protected int size;
  protected BoardState[][] board;

  /**
   * Creates a board with the given size.
   * @param size size of the board to be created
   * @throws IllegalArgumentException if the size is smaller than the minimal board size.
   */
  public BaseBoard(final int size) {
    assertSizeIsGreaterThan(size, MINIMAL_BOARD_SIZE);
    this.size = size;
    board = new BoardState[size][size];
  }

  /**
   * Checks if the requested size of the board is valid.
   *
   * @param size requested size
   * @param minimalBoardSize official minimal size
   */
  protected void assertSizeIsGreaterThan(final int size, final int minimalBoardSize) {
    if (size < minimalBoardSize) {
      throw new IllegalArgumentException("Board size of " + size + " is smaller than the minimal "
          + "board size of " + minimalBoardSize);
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

  protected void assertPositionIsOnBoard(int row, int column) {
    // TODO implement it throwing an IllegalArgumentException with the position.
  }
}
