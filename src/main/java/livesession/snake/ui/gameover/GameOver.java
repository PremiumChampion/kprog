package livesession.snake.ui.gameover;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeComponent;

/**
 * class GameOverView.
 */
public class GameOver extends BaseSnakeComponent<GameOverController,GameOverModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameOver.class);

  @Override
  protected URL getFXMLLocation() {
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
