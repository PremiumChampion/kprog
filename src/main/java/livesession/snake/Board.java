package livesession.snake;

/**
 * Represents the board of the game snake.
 */
public interface Board {
  /**
   * Minimal board size. Smaller boards don't make any sense.
   */
  int MINIMAL_BOARD_SIZE = 4;

  /**
   * Returns the size of the board.
   *
   * @return size of the board.
   */
  int size();

  /**
   * Returns the BoardState at the given coordinate.
   *
   * @param coordinate requested coordinate
   * @return state at this coordinate
   * @throws IllegalArgumentException if the coordinate is either null or not on the board.
   */
  BoardState getStateFromPosition(Coordinate coordinate) throws IllegalArgumentException;

  /**
   * Returns the BoardState at the given coordinate.
   *
   * @param row requested row
   * @param column requested column
   * @return state at this coordinate
   * @throws IllegalArgumentException if the coordinate is either null or not on the board.
   */
  BoardState getStateFromPosition(int row, int column) throws IllegalArgumentException;
}
