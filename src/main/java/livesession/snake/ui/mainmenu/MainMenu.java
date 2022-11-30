package livesession.snake.ui.mainmenu;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeComponent;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.configure.Configure;

public class MainMenu extends BaseSnakeComponent<MainMenuController,MainMenuModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MainMenu.class);


  @Override
  public void bind(SnakeServiceViewModel snakeModel) {
    super.bind(snakeModel);
    controller.getModel().setOnStart(this::onStart);
    controller.getModel().setOnConfigure(this::onConfigure);
  }

  private void onStart() {
    model.getSnakeModel().serviceProperty().get().start();
  }

  private void onConfigure() {
    sceneLoader.load(new Configure());
  }

  protected URL getFXMLLocation() {
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
    return  MainMenuModel.class;
  }
}
