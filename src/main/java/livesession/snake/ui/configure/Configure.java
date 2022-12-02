package livesession.snake.ui.configure;

import examples.javafx.modal.ExceptionAlert;
import java.net.URL;
import java.util.Objects;
import livesession.snake.GameConfiguration;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.mainmenu.MainMenu;

/**
 * Shows a configuration screen.
 */
public class Configure extends BaseSnakeScreen<ConfigureController, ConfigureModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Configure.class);

  @Override
  public void bind(SnakeServiceViewModel thingToBind) {
    model.getSnakeModel().bindBidirectional(thingToBind);
    model.setSaveConfigurationHandler(this::onSave);
    model.setDiscardHandler(this::onDiscard);
    controller.bind();
  }

  /**
   * Action to perform when the discard button is pressed.
   */
  private void onDiscard() {
    this.sceneLoader.load(new MainMenu());
  }

  private void onSave(int speed, int size, int food) {
    logger.info("onSave: speed:{} size:{} food:{}", speed, size, food);
    try {
      GameConfiguration configuration = new GameConfiguration(size, speed, food);
      this.model.getSnakeModel().setConfiguration(configuration);
      this.sceneLoader.load(new MainMenu());
    } catch (IllegalConfigurationException e) {
      new ExceptionAlert(e).show();
    }
  }

  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakeConfigureView.fxml"),
        "could not load SnakeConfigureView.fxml");
  }

  @Override
  protected Class<ConfigureController> getControllerClass() {
    return ConfigureController.class;
  }

  @Override
  protected Class<ConfigureModel> getModelClass() {
    return ConfigureModel.class;
  }
}
