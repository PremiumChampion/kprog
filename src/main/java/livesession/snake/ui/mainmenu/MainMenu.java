package livesession.snake.ui.mainmenu;

import java.net.URL;
import java.util.Objects;
import livesession.snake.ui.BaseSnakeScreen;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.configure.Configure;

/**
 * main menu.
 */
public class MainMenu extends BaseSnakeScreen<MainMenuController> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MainMenu.class);

  @Override
  protected URL getFxmlLocation() {
    return Objects.requireNonNull(
        getClass().getClassLoader().getResource("snake/SnakeMainMenu.fxml"),
        "could not load SnakeMainMenu.fxml");
  }
}
