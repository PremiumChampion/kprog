package livesession.snake.provider;

import java.util.Arrays;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;

/**
 * Realizes the internal view on the snake board. It contains GRASS, FOOD and WALL elements <b>but
 * not the position of the snake itself</b>.
 */
public class InternalBoard extends BaseBoard {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(InternalBoard.class);

  /**
   * Creates an internal board with the given size.
   *
   * @param size size of the board including walls at the border of the board.
   */
  public InternalBoard(final int size) {
    super(size);

    // TODO: Init board with GRASS and WALLs
  }

  /**
   * Calculates the start position.
   *
   * @return coordinate with the start position
   */
  public Coordinate getStartPosition() {
    return new Coordinate(size / 2, size / 2);
  }

  /**
   * Adds food at the given coordinate.
   *
   * @param coordinate coordinate where the food is placed.
   */
  protected void addFood(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = BoardState.FOOD;
  }

  /**
   * Removes food at the given coordinate.
   *
   * @param coordinate coordinate where the food has to be removed.
   */
  protected void removeFood(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = BoardState.GRASS;
  }

}
