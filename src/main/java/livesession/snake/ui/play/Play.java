package livesession.snake.ui.play;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class PlayView.
 */
public class Play extends BaseSnakeScreen<PlayController> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Play.class);


  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakePlayView.fxml"),
        "could not load SnakePlayView.fxml");
  }
}
