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
public class PauseController implements Initializable , BaseSnakeUiController<PauseModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PauseController.class);
  public Label scoreLabel;
  public Button giveUpButton;
  public Button resumeButton;
  public SnakeBoard gameBoard;
  private PauseModel model = new PauseModel();
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("initialising");
  }

  public PauseModel getModel() {
    return model;
  }

  public SnakeServiceViewModel getSnakeModel(){
    return model.getSnakeModel();
  }
  public void giveUp(ActionEvent actionEvent) {
    getSnakeModel().getService().abort();
  }

  public void resume(ActionEvent actionEvent) {
    getSnakeModel().getService().resume();
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
