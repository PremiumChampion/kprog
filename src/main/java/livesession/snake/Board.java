package livesession.snake;

public interface Board {
  int MINIMAL_BOARD_SIZE = 4;

  int size();
  BoardState getStateFromPosition(Coordinate coordinate);
  BoardState getStateFromPosition(int row, int column);

}
