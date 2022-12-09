package livesession.snake.ui.pause;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeScreen;

/**
 * class PauseView.
 */
public class Pause extends BaseSnakeScreen<PauseController> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Pause.class);


  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakePauseView.fxml"),
        "could not load SnakePauseView.fxml");
  }
}
