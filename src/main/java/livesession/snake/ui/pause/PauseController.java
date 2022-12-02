package livesession.snake.ui.pause;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import livesession.snake.ui.BaseSnakeUiController;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.nodes.SnakeBoard;

/**
 * class PauseViewController.
 */
public class PauseController implements Initializable, BaseSnakeUiController<PauseModel> {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      PauseController.class);
  public Label scoreLabel;
  public Button giveUpButton;
  public Button resumeButton;
  public SnakeBoard gameBoard;
  private PauseModel model = new PauseModel();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("initialising");
  }

  /**
   * getter for the snake model.
   *
   * @return the snake model.
   */

  public SnakeServiceViewModel getSnakeModel() {
    return model.getSnakeModel();
  }

  /**
   * handler for the give up button.
   *
   * @param actionEvent event.
   */
  public void giveUp(ActionEvent actionEvent) {
    model.getOnAbortGameAction().run();
  }

  /**
   * handler for the resume button.
   *
   * @param actionEvent event.
   */
  public void resume(ActionEvent actionEvent) {
    model.getOnResumeGameAction().run();
  }

  @Override
  public void setModel(PauseModel model) {
    this.model = model;
  }

  @Override
  public void bind() {
    logger.info("binding");
    gameBoard.setSnakeModel(getSnakeModel());
    scoreLabel.textProperty().bind(getSnakeModel().scoreProperty().asString("Score: %d"));
  }
}
