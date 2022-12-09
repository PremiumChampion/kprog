package livesession.snake.ui.gameover;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import livesession.snake.SnakeService;
import livesession.snake.ui.BaseSnakeController;
import livesession.snake.ui.SnakeScreenLoader;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.nodes.SnakeBoard;

/**
 * class GameOverViewController.
 */
public class GameOverController implements BaseSnakeController, Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameOverController.class);
  public SnakeBoard gameBoard;
  public Label scoreLabel;
  public Button restartButton;
  public Button mainMenuButton;
  private SnakeServiceViewModel model = new SnakeServiceViewModel();
  private SnakeScreenLoader screenLoader;


  /**
   * action to perform when show main menu is pressed.
   *
   * @param actionEvent event.
   */
  public void showMainMenu(ActionEvent actionEvent) {
    model.getService().reset();
  }


  /**
   * action to perform when restart button is pressed.
   *
   * @param actionEvent event.
   */
  public void restart(ActionEvent actionEvent) {
    SnakeService service = model.getService();
    service.reset();
    service.start();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
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
}
