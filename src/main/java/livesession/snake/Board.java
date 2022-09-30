package livesession.snake;

public interface Board {
  int size();
  BoardState getStateFromPosition(Coordinate coordinate);
  BoardState getStateFromPosition(int row, int column);

}
