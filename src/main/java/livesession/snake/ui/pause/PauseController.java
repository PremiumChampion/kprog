package livesession.snake.ui.pause;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import livesession.snake.ui.BaseSnakeController;
import livesession.snake.ui.SnakeScreenLoader;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.nodes.SnakeBoard;

/**
 * class PauseViewController.
 */
public class PauseController implements Initializable, BaseSnakeController {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      PauseController.class);
  public Label scoreLabel;
  public Button giveUpButton;
  public Button resumeButton;
  public SnakeBoard gameBoard;
  private final SnakeServiceViewModel model = new SnakeServiceViewModel();
  private SnakeScreenLoader screenLoader;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    scoreLabel.textProperty().bind(model.scoreProperty().asString("Score: %d"));
    gameBoard.bindBoard(model.boardProperty());
    gameBoard.bindHead(model.snakeHeadProperty());
  }

  /**
   * handler for the give up button.
   *
   * @param actionEvent event.
   */
  public void giveUp(ActionEvent actionEvent) {
    model.abort();
  }

  /**
   * handler for the resume button.
   *
   * @param actionEvent event.
   */
  public void resume(ActionEvent actionEvent) {
    model.resume();
  }


  @Override
  public void bind(SnakeServiceViewModel model) {
    this.model.bind(model);
  }

  @Override
  public void setScreenLoader(SnakeScreenLoader loader) {
    this.screenLoader = loader;
  }
}
