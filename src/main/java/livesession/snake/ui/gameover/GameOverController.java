package livesession.snake.ui.gameover;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import livesession.snake.ui.BaseSnakeUiController;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.nodes.SnakeBoard;

/**
 * class GameOverViewController.
 */
public class GameOverController implements BaseSnakeUiController<GameOverModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameOverController.class);
  public SnakeBoard gameBoard;
  public Label scoreLabel;
  public Button restartButton;
  public Button mainMenuButton;
  private GameOverModel model = new GameOverModel();

  /**
   * the current model of the controller.
   *
   * @return current model.
   */
  public GameOverModel getModel() {
    return model;
  }

  /**
   * sets a new model.
   *
   * @param model the new model.
   */
  public void setModel(GameOverModel model) {
    this.model = model;
  }

  /**
   * action to perform when show main menu is pressed.
   *
   * @param actionEvent event.
   */
  public void showMainMenu(ActionEvent actionEvent) {
    model.getShowMainMenuHandler().run();
  }

  /**
   * Get snake model.
   *
   * @return the snake model.
   */
  public SnakeServiceViewModel getSnakeModel() {
    return model.getSnakeModel();
  }

  /**
   * action to perform when restart button is pressed.
   *
   * @param actionEvent event.
   */
  public void restart(ActionEvent actionEvent) {
    model.getRestartHandler().run();
  }

  @Override
  public void bind() {
    scoreLabel.textProperty().bind(getModel().getScore().asString("Score: %d"));
  }
}
