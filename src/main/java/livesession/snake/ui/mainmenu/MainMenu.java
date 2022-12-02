package livesession.snake.ui.mainmenu;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.configure.Configure;

/**
 * main menu.
 */
public class MainMenu extends BaseSnakeScreen<MainMenuController, MainMenuModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MainMenu.class);


  @Override
  public void bind(SnakeServiceViewModel snakeModel) {
    super.bind(snakeModel);
    controller.getModel().setOnStart(this::onStart);
    controller.getModel().setOnConfigure(this::onConfigure);
  }

  /**
   * action to perform when game should start.
   */
  private void onStart() {
    model.getSnakeModel().serviceProperty().get().start();
  }

  /**
   * action to perform when game should be configured.
   */
  private void onConfigure() {
    sceneLoader.load(new Configure());
  }

  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakeMainMenu.fxml"),
        "could not load SnakeMainMenu.fxml");
  }

  @Override
  protected Class<MainMenuController> getControllerClass() {
    return MainMenuController.class;
  }

  @Override
  protected Class<MainMenuModel> getModelClass() {
    return MainMenuModel.class;
  }
}
