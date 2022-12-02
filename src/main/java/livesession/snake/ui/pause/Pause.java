package livesession.snake.ui.pause;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class PauseView.
 */
public class Pause extends BaseSnakeScreen<PauseController, PauseModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Pause.class);

  @Override
  public void bind(SnakeServiceViewModel thingToBind) {
    super.bind(thingToBind);
    model.setOnAbortGameAction(this::giveUp);
    model.setOnResumeGameAction(this::resume);
  }

  /**
   * action to perform when the user wants to quit the current game.
   */
  public void giveUp() {
    model.getSnakeModel().getService().abort();
  }

  /**
   * action to perform when the user wants to resume the current game.
   */
  public void resume() {
    model.getSnakeModel().getService().resume();
  }

  @Override
  protected URL getFxmlLocation() {
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
