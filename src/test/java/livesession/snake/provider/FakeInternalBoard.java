package livesession.snake.provider;

import livesession.snake.BoardState;
import livesession.snake.Coordinate;

public class FakeInternalBoard extends InternalBoard {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FakeInternalBoard.class);

  public FakeInternalBoard(final int size) {
    super(size);
    fillWithGrass();
  }

  private void fillWithGrass() {
    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        board[row][column] = BoardState.GRASS;
      }
    }
  }

  public void setPosition(Coordinate coordinate, BoardState state) {
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = state;
  }
}
