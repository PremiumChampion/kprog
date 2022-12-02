package livesession.snake.provider;

import static livesession.snake.Board.MINIMAL_BOARD_SIZE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

/**
 * Simple and straight-forward implementation of the ExtendedSnakeService interface.
 */
public class SimpleSnakeService implements ExtendedSnakeService {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnakeService.class);
  private GameConfiguration gameConfiguration;
  private InternalBoard board;
  private SimpleSnake snake;
  private GameLoop simpleGameLoop;
  private FoodGenerator foodGenerator;

  private GameState gameState;
  private int score;

  private List<SnakeListener> listeners;

  /**
   * Default constructor. The game uses then default values for configuration. The default values
   * are defined in the SnakeService interface.
   */
  public SimpleSnakeService() {
    // TODO: (DONE) What else to initialize?
    this.listeners = new ArrayList<>();
    this.gameConfiguration = GameConfiguration.DEFAULT_GAME_CONFIGURATION;
    this.foodGenerator = new FoodGenerator(this);
    this.init();
    // TODO: end.
  }

  private void init() {
    this.gameState = GameState.PREPARED;
    this.board = new InternalBoard(this.gameConfiguration.getSize());
    this.snake = new SimpleSnake(this);
    this.score = 0;
    this.initFood();
    allPropertiesChanged();
  }

  private void allPropertiesChanged() {
    notifyListeners((l) -> {
      l.newGameState(gameState);
      l.updateScore(score);
      l.updateBoard(getExternalBoard());
      l.gameEnded(null);
    });
  }

  private void initFood() {
    for (int i = 0; i < this.gameConfiguration.getNumberOfFood(); i++) {
      Coordinate nextFoodCoordinate;
      do {
        nextFoodCoordinate = foodGenerator.placeFood();
      } while (this.getExternalBoard().getStateFromPosition(nextFoodCoordinate)
          != BoardState.GRASS);
      this.addFood(nextFoodCoordinate);
    }
  }

  @Override
  public GameConfiguration getConfiguration() {
    return gameConfiguration;
  }

  @Override
  public void reset() {
    logger.debug("reset:");
    // TODO: (DONE) reset for a new game
    this.gameState = GameState.PREPARED;
    this.init();
    // TODO: end.
  }

  @Override
  public void start() {
    logger.debug("start:");
    this.assertCorrectState(new GameState[]{GameState.PREPARED});

    simpleGameLoop = new SimpleGameLoop(this, gameConfiguration.getVelocityInMilliSeconds());
    gameState = GameState.RUNNING;
    notifyListeners((l) -> l.newGameState(gameState));
  }

  @Override
  public void pause() {
    logger.debug("pause:");
    this.assertCorrectState(new GameState[]{GameState.RUNNING});

    simpleGameLoop.pauseGame();
    gameState = GameState.PAUSED;
    notifyListeners((SnakeListener listener) -> listener.newGameState(gameState));
  }

  @Override
  public void resume() {
    logger.debug("resume:");
    this.assertCorrectState(new GameState[]{GameState.PAUSED});

    simpleGameLoop.resumeGame();
    gameState = GameState.RUNNING;
    notifyListeners((l) -> l.newGameState(gameState));
  }

  @Override
  public void abort() {
    logger.debug("abort:");
    this.assertCorrectState(new GameState[]{GameState.RUNNING, GameState.PAUSED});
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
    Iterator<SnakeListener> i = listeners.iterator();
    while (i.hasNext()) {
      consumer.accept(i.next());
    }
  }

  @Override
  public void configure(final GameConfiguration configuration)
      throws IllegalConfigurationException {
    this.assertCorrectState(new GameState[]{GameState.PREPARED, GameState.ABORTED});

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

    this.init();
    // TODO: end.
  }

  /**
   * do a step.
   */
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
    this.assertCorrectState(new GameState[]{GameState.RUNNING});

    Coordinate newPosition = snake.advance();
    notifyListeners((l) -> l.updateBoard(getExternalBoard()));
  }

  @Override
  public void addFood(final Coordinate coordinate) {
    logger.debug("addFood: " + coordinate);
    if (board.getStateFromPosition(coordinate).equals(BoardState.FOOD)) {
      throw new IllegalArgumentException("There is already food at this position: " + coordinate);
    }
    board.addFood(coordinate);
    notifyListeners((l) -> l.updateBoard(getExternalBoard()));
  }

  @Override
  public void foodEaten(final Coordinate coordinate) {
    logger.debug("foodEaten: " + coordinate);
    // TODO: (DONE) what has to be done when one food has been eaten?
    Coordinate nextFoodCoordinate;
    do {
      nextFoodCoordinate = foodGenerator.placeFood();
    } while (this.getExternalBoard().getStateFromPosition(nextFoodCoordinate) != BoardState.GRASS);
    this.addFood(nextFoodCoordinate);
    updateScore(BoardState.FOOD);
    this.board.removeFood(coordinate);
    notifyListeners((l) -> l.updateBoard(getExternalBoard()));
    // TODO: end.
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
    notifyListeners((l) -> l.updateScore(score));
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

  /**
   * checks if the current games state is one of the provided values.
   *
   * @param validStates gamestates that are valid when calling this method.
   */
  private void assertCorrectState(GameState[] validStates) {
    if (!Arrays.stream(validStates).anyMatch(state -> this.gameState == state)) {
      throw new IllegalStateException(
          String.format("The state was expected to be one of %s but is %s",
              Arrays.toString(validStates), this.gameState));
    }
  }
}
