package livesession.snake;

public interface SnakeListener {
  void updateBoard(Board board);
  void newGameState(GameState state);
  void gameEnded(String reason);

}
