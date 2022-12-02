package livesession.snake.ui.mainmenu;

import livesession.snake.ui.BaseSnakeUiModel;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * main menu model.
 */
public class MainMenuModel implements BaseSnakeUiModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MainMenuModel.class);
  private SnakeServiceViewModel snakeModel = new SnakeServiceViewModel();
  // start game
  private Runnable onStart = () -> {
  };
  // switch to configuration screen
  private Runnable onConfigure = () -> {
  };

  /**
   * getter for the snake model.
   *
   * @return the snake model.
   */
  public SnakeServiceViewModel getSnakeModel() {
    return snakeModel;
  }

  /**
   * getter for the on start handler.
   *
   * @return the start handler
   */
  public Runnable getOnStart() {
    return onStart;
  }

  /**
   * setter for the on start handler.
   *
   * @param onStart the start handler
   */
  public void setOnStart(Runnable onStart) {
    this.onStart = onStart;
  }

  /**
   * getter for the on configure handler.
   *
   * @return the on configure handler
   */
  public Runnable getOnConfigure() {
    return onConfigure;
  }

  /**
   * setter for the on configure handler.
   *
   * @param onConfigure to configure handler.
   */
  public void setOnConfigure(Runnable onConfigure) {
    this.onConfigure = onConfigure;
  }
}
