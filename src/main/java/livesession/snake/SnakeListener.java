package livesession.snake;

public interface SnakeListener {
  void updateBoard(Board board);
  void newGameState(GameState state);
  void gameEnded(Reason reason);
  void updateScore(int score);

}
