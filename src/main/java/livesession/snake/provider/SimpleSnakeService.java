package livesession.snake.provider;

import static livesession.snake.Board.MINIMAL_BOARD_SIZE;

import java.util.ArrayList;
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

public class SimpleSnakeService implements ExtendedSnakeService {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SimpleSnakeService.class);
  // why are these package public???
  private SimpleSnake snake;
  private GameLoop simpleGameLoop;
  private List<SnakeListener> listeners;
  private GameConfiguration configuration;
  private BaseBoard board;

  public SimpleSnakeService() {
    // TODO: What else to initialize?
    this.listeners = new ArrayList<>();
    this.configuration = GameConfiguration.DEFAULT_GAME_CONFIGURATION;
    this.init();
  }

  private void init() {
    this.board = new BaseBoard(this.configuration.getSize());
    this.snake = new SimpleSnake(this);
  }

  @Override
  public void reset() {
    // TODO: reset for a new game
    this.init();
  }

  // needs some exception when not configuration odr do we start with a default configuration?
  @Override
  public void start() {
    logger.debug("start:");
    simpleGameLoop = new SimpleGameLoop(this, configuration.getVelocityInMilliSeconds());
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
    if (this.listeners.contains(listener)) {
      return false;
    }
    this.listeners.add(listener);
    return true;
  }

  @Override
  public boolean removeListener(final SnakeListener listener) {
    if (!this.listeners.contains(listener)) {
      return false;
    }
    this.listeners.remove(listener);
    return true;
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
  public void configure(final GameConfiguration configuration)
      throws IllegalConfigurationException {
    // TODO: check and save the configuration info.
    if (configuration == null) {
      throw new IllegalConfigurationException(
          String.format("expected board configuration when configuring a board and not null"));
    }

    if (configuration.getVelocityInMilliSeconds() <= 0) {
      throw new IllegalConfigurationException(
          String.format("VelocityInMilliSeconds invalid: expected not 0 or negative but got: %s",
              configuration.getVelocityInMilliSeconds()));
    }
    if (configuration.getNumberOfFood() <= 0) {
      throw new IllegalConfigurationException(
          String.format("NumberOfFood invalid: expected not 0 or negative but got: %s",
              configuration.getVelocityInMilliSeconds()));
    }
    if (configuration.getSize() < MINIMAL_BOARD_SIZE) {
      throw new IllegalConfigurationException(
          String.format("Size invalid: expected number greater/equal to %s but got: %s",
              MINIMAL_BOARD_SIZE,
              configuration.getVelocityInMilliSeconds())
      );
    }

    this.configuration = configuration;
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
