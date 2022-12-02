package livesession.snake.ui.gameover;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class configuration class.
 */
public class GameOver extends BaseSnakeScreen<GameOverController, GameOverModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameOver.class);

  @Override
  public void bind(SnakeServiceViewModel snakeModel) {
    super.bind(snakeModel);
    model.setShowMainMenuHandler(this::showMainMenu);
    model.setRestartHandler(this::restart);
  }

  /**
   * action to perform when main menu should be shown.
   */
  public void showMainMenu() {
    model.getSnakeModel().getService().reset();
  }

  /**
   * action to perform when game should be restarted.
   */
  public void restart() {
    model.getSnakeModel().getService().reset();
    model.getSnakeModel().getService().start();
  }

  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakeGameOverView.fxml"),
        "could not load SnakeGameOverView.fxml");
  }

  @Override
  protected Class<GameOverController> getControllerClass() {
    return GameOverController.class;
  }

  @Override
  protected Class<GameOverModel> getModelClass() {
    return GameOverModel.class;
  }
}
