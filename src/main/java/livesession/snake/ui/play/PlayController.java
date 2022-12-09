package livesession.snake.ui.play;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import livesession.snake.ui.BaseSnakeController;
import livesession.snake.ui.SnakeScreenLoader;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.nodes.SnakeBoard;

/**
 * class PlayViewController.
 */
public class PlayController implements BaseSnakeController, Initializable {

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
  private final SnakeServiceViewModel model = new SnakeServiceViewModel();
  private SnakeScreenLoader screenLoader;

  /**
   * handler for pause button.
   *
   * @param actionEvent event.
   */
  public void pause(ActionEvent actionEvent) {
    model.pause();
  }

  /**
   * handler for the giveUp button.
   *
   * @param actionEvent event.
   */
  public void giveUp(ActionEvent actionEvent) {
    model.abort();
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
      model.left();
    }
    if (d == Direction.RIGHT) {
      model.right();
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    gameBoard.bindBoard(model.boardProperty());
    gameBoard.bindHead(model.snakeHeadProperty());
    gameBoard.focusedProperty().addListener(this::focusChanged);
    gameBoard.requestFocus();
    gameBoard.setOnKeyPressed(this::keyPressed);
    scoreLabel.textProperty().bind(model.scoreProperty().asString("Score: %d"));
  }

  @Override
  public void bind(SnakeServiceViewModel model) {
    this.model.bind(model);
  }

  @Override
  public void setScreenLoader(SnakeScreenLoader loader) {
    this.screenLoader = loader;
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
