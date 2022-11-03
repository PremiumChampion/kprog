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

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnakeService.class);
  private GameConfiguration gameConfiguration;
  private InternalBoard board;
  private SimpleSnake snake;
  private SimpleGameLoop simpleGameLoop;
  private FoodGenerator foodGenerator;

  private GameState gameState;
  private int score;

  private List<SnakeListener> listeners;

  /**
   * Default constructor. The game uses then default values for configuration.
   * The default values are defined in the SnakeService interface.
   */
  public SimpleSnakeService() {
    // TODO: (DONE) What else to initialize?
    this.listeners = new ArrayList<>();
    this.gameConfiguration = GameConfiguration.DEFAULT_GAME_CONFIGURATION;
    this.init();
  }

  private void init() {
    this.board = new InternalBoard(this.gameConfiguration.getSize());
    this.snake = new SimpleSnake(this);
  }

  @Override
  public GameConfiguration getConfiguration() {
    return gameConfiguration;
  }

  @Override
  public void reset() {
    // TODO: (DONE) reset for a new game
    this.init();
  }

  // needs some exception when not configuration odr do we start with a default configuration?
  @Override
  public void start() {
    logger.debug("start:");
    simpleGameLoop = new SimpleGameLoop(this, gameConfiguration.getVelocityInMilliSeconds());
    gameState = GameState.RUNNING;
    notifyListeners((l) -> l.newGameState(gameState));
  }

  @Override
  public void pause() {
    logger.debug("pause:");
    simpleGameLoop.pauseGame();
    gameState = GameState.PAUSED;
    notifyListeners((SnakeListener listener) -> listener.newGameState(gameState));
  }

  @Override
  public void resume() {
    logger.debug("resume:");
    simpleGameLoop.resumeGame();
    gameState = GameState.RUNNING;
    notifyListeners((l) -> l.newGameState(gameState));
  }

  @Override
  public void abort() {
    logger.debug("abort:");
    simpleGameLoop.stopGame();
    gameState = GameState.ABORTED;
    notifyListeners((SnakeListener listener) -> listener.newGameState(gameState));
    notifyListeners((SnakeListener listener) -> listener.gameEnded(new Reason("Game aborted")));
  }

  @Override
  public void failed(Reason reason) {
    logger.debug("failed: " + reason);
    simpleGameLoop.stopGame();
    gameState = GameState.ABORTED;
    notifyListeners((SnakeListener listener) -> listener.newGameState(gameState));
    notifyListeners((l) -> l.gameEnded(reason));
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
    logger.debug("addListener: " + listener);
    if (listener == null) {
      return false;
    }

    if (listeners.contains(listener)) {
      return false;
    }

    listeners.add(listener);
    return true;
  }

  @Override
  public boolean removeListener(final SnakeListener listener) {
    logger.debug("removeListener: " + listener);
    return listeners.remove(listener);
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
    // TODO: (DONE) check and save the configuration info.
    if (configuration == null) {
      throw new IllegalConfigurationException(
          "expecting non null value for board configuration when configuring");
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

    this.gameConfiguration = configuration;
  }

  public void triggeredByGameLoop() {
    try {
      advanceSnake();
    } catch (IllegalPositionException e) {
      failed(new Reason(e.getCoordinate(), e.getState()));
    }
  }

  @Override
  public void advanceSnake() throws IllegalPositionException {
    logger.debug("advanceSnake:");
    Coordinate newPosition = snake.advance();
    notifyListeners((l) -> l.updateBoard(getExternalBoard()));
  }

  @Override
  public void addFood(final Coordinate coordinate) {
    logger.debug("addFood: " + coordinate);
    board.addFood(coordinate);
    notifyListeners((l) -> l.updateBoard(getExternalBoard()));
  }

  @Override
  public void foodEaten(final Coordinate coordinate) {
    logger.debug("foodEaten: " + coordinate);
    // TODO: what has to be done when one food has been eaten?
    foodGenerator.placeFood();
  }

  @Override
  public void updateScore(final BoardState state) {
    logger.debug("updateScore: " + state);
    switch (state) {
      case FOOD:
        score += 10;
        break;
      default:
        throw new IllegalArgumentException("Unknown state in updateScore: " + state);
    }
  }

  @Override
  public Snake getSnake() {
    return snake;
  }

  @Override
  public Board getExternalBoard() {
    ExternalBoard externalBoard = new ExternalBoard(board, snake);
    return externalBoard;
  }

  @Override
  public InternalBoard getInternalBoard() {
    return board;
  }

  public Board getBoard() {
    return getExternalBoard();
  }
}
