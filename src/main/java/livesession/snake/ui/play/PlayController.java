package livesession.snake.ui.play;


import java.util.HashMap;
import java.util.Map;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import livesession.snake.ui.BaseSnakeUiController;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.nodes.SnakeBoard;

/**
 * class PlayViewController.
 */
public class PlayController implements BaseSnakeUiController<PlayModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PlayController.class);
  private static final Map<KeyCode, Direction> keybindings = new HashMap<>();

  static {
    keybindings.put(KeyCode.A, Direction.LEFT);
    keybindings.put(KeyCode.D, Direction.RIGHT);
    keybindings.put(KeyCode.LEFT, Direction.LEFT);
    keybindings.put(KeyCode.RIGHT, Direction.RIGHT);
  }

  public Button pauseButton;
  public Button giveUpButton;
  public Label scoreLabel;
  public SnakeBoard gameBoard;
  private PlayModel model = new PlayModel();

  /**
   * get the snake model.
   *
   * @return the snake model.
   */
  public SnakeServiceViewModel getSnakeModel() {
    logger.debug("getSnakeModel");
    return model.getSnakeModel();
  }

  /**
   * handler for pause button.
   *
   * @param actionEvent event.
   */
  public void pause(ActionEvent actionEvent) {
    model.getOnPauseGameAction().run();
  }

  /**
   * handler for the giveUp button.
   *
   * @param actionEvent event.
   */
  public void giveUp(ActionEvent actionEvent) {
    model.getOnAbortGameAction().run();
  }

  @Override
  public void setModel(PlayModel model) {
    this.model = model;
  }

  @Override
  public void bind() {
    logger.info("binding");
    gameBoard.setSnakeModel(getSnakeModel());
    gameBoard.focusedProperty().addListener(this::focusChanged);
    gameBoard.requestFocus();
    gameBoard.setOnKeyPressed(this::keyPressed);
    scoreLabel.textProperty().bind(getSnakeModel().scoreProperty().asString("Score: %d"));
  }

  /**
   * handles a keypress event.
   *
   * @param keyEvent event.
   */
  private void keyPressed(KeyEvent keyEvent) {
    logger.info("keyPressed {}", keyEvent.getCode());
    Direction d = keybindings.get(keyEvent.getCode());

    if (d == Direction.LEFT) {
      getSnakeModel().getService().moveLeft();
    }
    if (d == Direction.RIGHT) {
      getSnakeModel().getService().moveRight();
    }
  }

  /**
   * focus changed event for the grid.
   *
   * @param observable grid focus property.
   */
  private void focusChanged(Observable observable) {
    if (!gameBoard.isFocused()) {
      gameBoard.requestFocus();
    }
  }

  /**
   * keybinding action.
   */
  enum Direction {
    /**
     * left.
     */
    LEFT,
    /**
     * right.
     */
    RIGHT;
  }
}
