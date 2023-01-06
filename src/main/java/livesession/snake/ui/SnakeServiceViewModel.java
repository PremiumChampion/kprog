package livesession.snake.ui;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import livesession.snake.Board;
import livesession.snake.Coordinate;
import livesession.snake.GameConfiguration;
import livesession.snake.GameState;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.Reason;
import livesession.snake.SnakeListener;
import livesession.snake.SnakeService;
import livesession.snake.provider.SimpleSnakeService;

/**
 * class SnakeServiceViewModel.
 */
public class SnakeServiceViewModel implements SnakeListener {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      SnakeServiceViewModel.class);
  private final ObjectProperty<SnakeService> service = new SimpleObjectProperty<>();
  private final ObjectProperty<Board> board = new SimpleObjectProperty<>();
  private final ObjectProperty<GameConfiguration> configuration = new SimpleObjectProperty<>(null);
  private final ObjectProperty<GameState> gameState = new SimpleObjectProperty<>(
      GameState.PREPARED);
  private final ObjectProperty<Reason> reason = new SimpleObjectProperty<>(null);
  private final ObjectProperty<Coordinate> snakeHead = new SimpleObjectProperty<>(
      new Coordinate(0, 0));
  private final IntegerProperty score = new SimpleIntegerProperty(0);

  /**
   * create a new snake model but skip binding event listener.
   */
  public SnakeServiceViewModel() {
    SimpleSnakeService simpleSnakeService = new SimpleSnakeService();
    service.setValue(simpleSnakeService);
    board.set(simpleSnakeService.getExternalBoard());
    configuration.set(simpleSnakeService.getConfiguration());
    getService().addListener(this);
  }

  @Override
  public void updateBoard(Board board) {
    if (!Platform.isFxApplicationThread()) {
      Platform.runLater(() -> updateBoard(board));
      return;
    }
    logger.debug("updateBoard");
    this.board.setValue(board);
    Coordinate snakeHead = service.get().getSnake().getPosition().get(0);
    this.snakeHead.setValue(snakeHead);
  }

  @Override
  public void newGameState(GameState state) {
    if (!Platform.isFxApplicationThread()) {
      Platform.runLater(() -> newGameState(state));
      return;
    }
    this.gameState.setValue(state);
  }

  @Override
  public void gameEnded(Reason reason) {
    if (!Platform.isFxApplicationThread()) {
      Platform.runLater(() -> gameEnded(reason));
      return;
    }
    this.reason.setValue(reason);
  }

  @Override
  public void updateScore(int score) {
    if (!Platform.isFxApplicationThread()) {
      Platform.runLater(() -> updateScore(score));
      return;
    }
    this.score.setValue(score);
  }

  public void abort() {
    getService().abort();
  }

  public void play() {
    getService().start();
  }

  public void pause() {
    getService().pause();
  }

  public void resume() {
    getService().resume();
  }

  public void left() {
    getService().moveLeft();
  }

  public void right() {
    getService().moveRight();
  }
  //region setter + getter

  /**
   * getter for the snake head.
   *
   * @return the coordinate of the snake head.
   */
  public Coordinate getSnakeHead() {
    return snakeHead.get();
  }

  /**
   * getter for the snake head property.
   *
   * @return snake head property.
   */
  public ObjectProperty<Coordinate> snakeHeadProperty() {
    return snakeHead;
  }

  /**
   * getter for the board.
   *
   * @return board.
   */
  public Board getBoard() {
    return board.get();
  }

  /**
   * getter for board property.
   *
   * @return board property.
   */
  public ObjectProperty<Board> boardProperty() {
    return board;
  }

  /**
   * getter game-state.
   *
   * @return gamestate.
   */
  public GameState getGameState() {
    return gameState.get();
  }

  /**
   * getter game-state.property.
   *
   * @return game-state-property.
   */
  public ObjectProperty<GameState> gameStateProperty() {
    return gameState;
  }

  /**
   * getter current score.
   *
   * @return current score.
   */
  public int getScore() {
    return score.get();
  }

  /**
   * getter current score property.
   *
   * @return score property.
   */
  public IntegerProperty scoreProperty() {
    return score;
  }

  /**
   * getter current reason.
   *
   * @return current reason.
   */
  public Reason getReason() {
    return reason.get();
  }

  /**
   * getter reason property.
   *
   * @return reason property.
   */
  public ObjectProperty<Reason> reasonProperty() {
    return reason;
  }

  /**
   * getter snake service.
   *
   * @return snake service.
   */
  public SnakeService getService() {
    return service.get();
  }

  /**
   * getter snake service property.
   *
   * @return snake service property.
   */
  public ObjectProperty<SnakeService> serviceProperty() {
    return service;
  }

  /**
   * getter configuration.
   *
   * @return configuration.
   */
  public GameConfiguration getConfiguration() {
    logger.info("getConfiguration");
    return configuration.get();
  }

  /**
   * Game configuration property.
   *
   * @return configuration property.
   */
  public ObjectProperty<GameConfiguration> configurationProperty() {
    return configuration;
  }
  //endregion

  /**
   * sets a new configuration.
   *
   * @param speed the new speed
   * @param size  the new size
   * @param food  the new food
   */
  public void setConfiguration(int speed, int size, int food) {
    if (!Platform.isFxApplicationThread()) {
      Platform.runLater(() -> {
        setConfiguration(speed, size, food);
      });
      return;
    }

    try {
      getService().configure(new GameConfiguration(size, speed, food));
      this.configuration.setValue(this.service.get().getConfiguration());
    } catch (IllegalConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * sets value to other and binds property bidirectional.
   *
   * @param other property with new value.
   */
  public void bindBidirectional(SnakeServiceViewModel other) {
    SnakeService simpleSnakeService = service.get();
    simpleSnakeService.removeListener(this);
    score.bindBidirectional(other.scoreProperty());
    board.bindBidirectional(other.boardProperty());
    gameState.bindBidirectional(other.gameStateProperty());
    reason.bindBidirectional(other.reasonProperty());
    service.bindBidirectional(other.serviceProperty());
    configuration.bindBidirectional(other.configurationProperty());
    snakeHead.bindBidirectional(other.snakeHeadProperty());
    simpleSnakeService = service.get();
    board.set(simpleSnakeService.getBoard());
    configuration.set(simpleSnakeService.getConfiguration());
  }

  /**
   * sets value to other and binds property.
   *
   * @param other property with new value.
   */
  public void bind(SnakeServiceViewModel other) {
    unbind();
    service.bind(other.serviceProperty());
    score.bind(other.scoreProperty());
    board.bind(other.boardProperty());
    gameState.bind(other.gameStateProperty());
    reason.bind(other.reasonProperty());
    configuration.bind(other.configurationProperty());
    snakeHead.bind(other.snakeHeadProperty());
  }

  /**
   * unbind unidirectional.
   */
  public void unbind() {
    service.get().removeListener(this);
    score.unbind();
    board.unbind();
    gameState.unbind();
    reason.unbind();
    service.unbind();
    configuration.unbind();
    snakeHead.unbind();
  }
}
