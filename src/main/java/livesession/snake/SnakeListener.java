package livesession.snake;

/**
 * Models the capabilities of a SnakeListener.
 */
public interface SnakeListener {
  /**
   * Informs the listener that the board has changed.
   *
   * @param board board with the actual position of GRASS, FOOD, WALL and SNAKE elements
   */
  void updateBoard(Board board);

  /**
   * Informs the listener that the game state has changed.
   *
   * @param state new state of the game
   */
  void newGameState(GameState state);

  /**
   * Informs the user that the game has ended.
   *
   * @param reason reason for termination
   */
  void gameEnded(Reason reason);

  /**
   * Informs the listener that the score has changed.
   *
   * @param score new score
   */
  void updateScore(int score);
}
