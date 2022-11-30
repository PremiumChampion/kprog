package livesession.snake.ui.pause;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeComponent;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class PauseView.
 */
public class Pause extends BaseSnakeComponent<PauseController, PauseModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Pause.class);
  @Override
  public void bind(SnakeServiceViewModel thingToBind) {
    super.bind(thingToBind);
  }
  @Override
  protected URL getFXMLLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakePauseView.fxml"),
        "could not load SnakePauseView.fxml");
  }

  @Override
  protected Class<PauseController> getControllerClass() {
    return PauseController.class;
  }

  @Override
  protected Class<PauseModel> getModelClass() {
    return PauseModel.class;
  }
}
