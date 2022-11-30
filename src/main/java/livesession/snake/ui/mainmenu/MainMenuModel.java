package livesession.snake.ui.mainmenu;

import livesession.snake.ui.BaseSnakeUiModel;
import livesession.snake.ui.SnakeServiceViewModel;

public class MainMenuModel implements BaseSnakeUiModel {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MainMenuModel.class);
  private SnakeServiceViewModel snakeModel = new SnakeServiceViewModel();
  // start game
  private Runnable onStart = ()->{};
  // switch to configuration screen
  private Runnable onConfigure = ()->{};

  public SnakeServiceViewModel getSnakeModel() {
    return snakeModel;
  }

  public Runnable getOnStart() {
    return onStart;
  }

  public void setOnStart(Runnable onStart) {
    this.onStart = onStart;
  }

  public Runnable getOnConfigure() {
    return onConfigure;
  }

  public void setOnConfigure(Runnable onConfigure) {
    this.onConfigure = onConfigure;
  }
}
