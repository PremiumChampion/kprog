package livesession.snake.provider;

import java.util.List;
import java.util.function.Consumer;
import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.GameConfiguration;
import livesession.snake.GameState;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.Reason;
import livesession.snake.Snake;
import livesession.snake.SnakeListener;
import livesession.snake.SnakeService;

public class SimpleSnakeService implements ExtendedSnakeService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnakeService.class);

  SimpleSnake snake;
  GameLoop simpleGameLoop;
  private List<SnakeListener> listeners;
  private int velocityInMilliseconds;

  public SimpleSnakeService() {
    this.snake = new SimpleSnake(this);
    // TODO: What else to initialize?
  }

  @Override
  public void reset() {
    // TODO: reset for a new game
  }

  @Override
  public void start() {
    logger.debug("start:");
    simpleGameLoop = new SimpleGameLoop(this, velocityInMilliseconds);
    notifyListeners((l) -> l.newGameState(GameState.RUNNING));
  }

  @Override
  public void pause() {
    logger.debug("pause:");
    simpleGameLoop.pauseGame();
    notifyListeners((SnakeListener listener) -> listener.newGameState(GameState.PAUSED));
  }

  @Override
  public void resume() {
    logger.debug("resume:");
    simpleGameLoop.resumeGame();
    notifyListeners((SnakeListener listener) -> listener.newGameState(GameState.RUNNING));
  }

  @Override
  public void abort() {

  }

  @Override
  public void moveLeft() {
    logger.debug("moveLeft:");
    snake.goLeft();
  }

  @Override
  public void moveRight() {
    logger.debug("moveRight:");
    snake.goRight();
  }

  @Override
  public boolean addListener(final SnakeListener listener) {
    return false;
  }

  @Override
  public boolean removeListener(final SnakeListener listener) {
    return false;
  }

  /**
   * Notifies all listeners by executing the consumer accept method. The accept method
   * implementation is in our case a lambda expression.
   *
   * @param consumer consumer to be executed.
   */
  private void notifyListeners(Consumer<SnakeListener> consumer) {
    for (SnakeListener listener : listeners) {
      consumer.accept(listener);
    }
  }


  @Override
  public void configure(final GameConfiguration configuration) throws
      IllegalConfigurationException {
    // TODO: check and save the configuration info.
  }

  void triggeredByGameLoop() {
    try {
      advanceSnake();
    } catch (IllegalPositionException e) {
      failed(new Reason(e.getCoordinate(), e.getState()));
    }
  }

  @Override
  public Board getExternalBoard() {
    return null;
  }

  @Override
  public void failed(final Reason reason) {

  }

  public void advanceSnake() throws IllegalPositionException {
    snake.advance();
  }

  @Override
  public void addFood(final Coordinate coordinate) {

  }

  @Override
  public Snake getSnake() {
    return null;
  }

  @Override
  public Board getBoard() {
    return null;
  }

  public InternalBoard getInternalBoard() {
    return null;
  }

  @Override
  public void foodEaten(Coordinate coordinate) {

  }

  @Override
  public void updateScore(final BoardState state) {

  }
}
