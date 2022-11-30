package livesession.snake.ui.play;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import livesession.snake.Board;
import livesession.snake.provider.ExternalBoard;
import livesession.snake.ui.BaseSnakeUiController;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.nodes.SnakeBoard;

/**
 * class PlayViewController.
 */
public class PlayController implements Initializable, BaseSnakeUiController<PlayModel> {

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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("initialising");
  }

  public PlayModel getModel() {
    logger.debug("getModel");
    return model;
  }

  public SnakeServiceViewModel getSnakeModel() {
    logger.debug("getSnakeModel");
    return model.getSnakeModel();
  }

  public void pause(ActionEvent actionEvent) {
    logger.debug("pause: {}", getSnakeModel().getService());
    getSnakeModel().getService().pause();
  }

  public void giveUp(ActionEvent actionEvent) {
    logger.debug("giveUp: {}", getSnakeModel().getService());
    getSnakeModel().getService().abort();
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

  private void keyPressed(KeyEvent keyEvent) {
    logger.info("keyPressed {}",keyEvent.getCode());
    Direction d = keybindings.get(keyEvent.getCode());

    if(d == Direction.LEFT){
      getSnakeModel().getService().moveLeft();
    }
    if(d == Direction.RIGHT){
      getSnakeModel().getService().moveRight();
    }
  }

  private void focusChanged(Observable observable) {
    if (!gameBoard.isFocused()) {
      gameBoard.requestFocus();
    }
  }

  enum Direction {
    LEFT, RIGHT;
  }
}
