package livesession.snake.ui.configure;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * Shows a configuration screen.
 */
public class Configure extends BaseSnakeScreen<ConfigureController> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Configure.class);

  @Override
  public void bind(SnakeServiceViewModel thingToBind) {
    model.bindBidirectional(thingToBind);
    controller.bind(thingToBind);
  }

  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakeConfigureView.fxml"),
        "could not load SnakeConfigureView.fxml");
  }
}
