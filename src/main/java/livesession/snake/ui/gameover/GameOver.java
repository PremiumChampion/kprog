package livesession.snake.ui.gameover;

import java.net.URL;
import java.util.Objects;
import livesession.snake.SnakeService;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class configuration class.
 */
public class GameOver extends BaseSnakeScreen<GameOverController> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameOver.class);

  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakeGameOverView.fxml"),
        "could not load SnakeGameOverView.fxml");
  }
}
