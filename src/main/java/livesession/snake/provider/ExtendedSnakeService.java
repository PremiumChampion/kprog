package livesession.snake.provider;

import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.Reason;
import livesession.snake.SnakeService;

public interface ExtendedSnakeService extends SnakeService {
  Board getExternalBoard();

  void failed(Reason reason);

  void advanceSnake() throws IllegalPositionException;

  void addFood(Coordinate coordinate);

  void foodEaten(Coordinate coordinate);

  void updateScore(BoardState state);

  InternalBoard getInternalBoard();
}
