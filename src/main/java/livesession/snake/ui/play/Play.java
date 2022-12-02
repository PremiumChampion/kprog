package livesession.snake.ui.play;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class PlayView.
 */
public class Play extends BaseSnakeScreen<PlayController, PlayModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Play.class);


  @Override
  public void bind(SnakeServiceViewModel thingToBind) {
    super.bind(thingToBind);
    model.setOnAbortGameAction(this::giveUp);
    model.setOnPauseGameAction(this::pause);
  }

  /**
   * handler for pause button.
   */
  public void pause() {
    model.getSnakeModel().getService().pause();
  }

  /**
   * handler for the giveUp button.
   */
  public void giveUp() {
    model.getSnakeModel().getService().abort();
  }

  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakePlayView.fxml"),
        "could not load SnakePlayView.fxml");
  }

  @Override
  protected Class<PlayController> getControllerClass() {
    return PlayController.class;
  }

  @Override
  protected Class<PlayModel> getModelClass() {
    return PlayModel.class;
  }

}
