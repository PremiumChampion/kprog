package livesession.snake.ui.gameover;

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
 * class GameOverViewController.
 */
public class GameOverController implements Initializable, BaseSnakeUiController<GameOverModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameOverController.class);
  public SnakeBoard gameBoard;
  public Label scoreLabel;
  public Button restartButton;
  public Button mainMenuButton;
  private GameOverModel model = new GameOverModel();

  public GameOverModel getModel() {
    return model;
  }

  public void setModel(GameOverModel model) {
    this.model = model;
  }

  public void showMainMenu(ActionEvent actionEvent) {
    getSnakeModel().getService().reset();
  }
  public SnakeServiceViewModel getSnakeModel(){
    return model.getSnakeModel();
  }
  public void restart(ActionEvent actionEvent) {
    getSnakeModel().getService().reset();
    getSnakeModel().getService().start();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("initialising");
  }

  @Override
  public void bind() {
    logger.info("binding");
    scoreLabel.textProperty().bind(getModel().getScore().asString("Score: %d"));
  }
}
