package livesession.snake.ui;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import livesession.snake.Board;
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
  private boolean isMaster = false;
  private ObjectProperty<SnakeService> service = new SimpleObjectProperty<>();
  private ObjectProperty<Board> board = new SimpleObjectProperty<>();
  private ObjectProperty<GameConfiguration> configuration = new SimpleObjectProperty<>(null);
  private ObjectProperty<GameState> gameState = new SimpleObjectProperty<>(GameState.PREPARED);
  private ObjectProperty<Reason> reason = new SimpleObjectProperty<>(null);
  private IntegerProperty score = new SimpleIntegerProperty(0);

  /**
   * new snake service View Model.
   *
   * @param isMaster sets the model as master.
   */
  public SnakeServiceViewModel(boolean isMaster) {
    this.isMaster = isMaster;
    init();
    getService().addListener(this);
  }

  /**
   * create a new snake model but skip binding event listener.
   */
  public SnakeServiceViewModel() {
    init();
  }

  /**
   * initialise default values.
   */
  private void init() {
    SimpleSnakeService simpleSnakeService = new SimpleSnakeService();
    service.setValue(simpleSnakeService);
    board.set(simpleSnakeService.getExternalBoard());
    configuration.set(simpleSnakeService.getConfiguration());
  }

  @Override
  public void updateBoard(Board board) {
    if (!Platform.isFxApplicationThread()) {
      Platform.runLater(() -> updateBoard(board));
      return;
    }
    logger.debug("updateBoard");
    this.board.setValue(board);
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

  /**
   * set a new configuration.
   *
   * @param configuration the new configuration.
   * @throws IllegalConfigurationException illegal exception.
   */
  public void setConfiguration(GameConfiguration configuration)
      throws IllegalConfigurationException {
    if (!Platform.isFxApplicationThread()) {
      Platform.runLater(() -> {
        try {
          setConfiguration(configuration);
        } catch (IllegalConfigurationException e) {
          throw new RuntimeException(e);
        }
      });
      return;
    }
    getService().configure(configuration);
    this.configuration.setValue(this.service.get().getConfiguration());
  }

  /**
   * sets value to other and binds property bidirectional.
   *
   * @param other property with new value.
   */
  public void bindBidirectional(SnakeServiceViewModel other) {
    score.bindBidirectional(other.scoreProperty());
    board.bindBidirectional(other.boardProperty());
    gameState.bindBidirectional(other.gameStateProperty());
    reason.bindBidirectional(other.reasonProperty());
    service.bindBidirectional(other.serviceProperty());
    configuration.bindBidirectional(other.configurationProperty());
    SnakeService simpleSnakeService = service.get();
    board.set(simpleSnakeService.getBoard());
    configuration.set(simpleSnakeService.getConfiguration());
  }

  /**
   * unbind properties bidirectional.
   *
   * @param other other model.
   */
  public void unbindBidirectional(SnakeServiceViewModel other) {
    score.unbindBidirectional(other.scoreProperty());
    board.unbindBidirectional(other.boardProperty());
    gameState.unbindBidirectional(other.gameStateProperty());
    reason.unbindBidirectional(other.reasonProperty());
    service.unbindBidirectional(other.serviceProperty());
    configuration.unbindBidirectional(other.configurationProperty());
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
  }

  /**
   * unbind unidirectional.
   */
  public void unbind() {
    score.unbind();
    board.unbind();
    gameState.unbind();
    reason.unbind();
    service.unbind();
    configuration.unbind();
  }

}
