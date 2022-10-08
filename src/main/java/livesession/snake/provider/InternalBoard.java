package livesession.snake.provider;

import livesession.snake.BoardState;
import livesession.snake.Coordinate;

public class InternalBoard extends BaseBoard {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(InternalBoard.class);

  private int score;

  public InternalBoard(final int size) {
    super(size);

    // TODO: Init board with GRASS and WALLs
  }

  public Coordinate getStartPosition() {
    return new Coordinate(size / 2, size / 2);
  }

  protected void addFood(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = BoardState.FOOD;
  }

  protected void removeFood(Coordinate coordinate){
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = BoardState.GRASS;
  }

  public void increaseScore() {
    score += 10;
  }
}
