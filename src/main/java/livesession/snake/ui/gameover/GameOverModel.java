package livesession.snake.ui.gameover;

import javafx.beans.property.IntegerProperty;
import livesession.snake.ui.BaseSnakeUiModel;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class GameOverViewModel.
 */
public class GameOverModel implements BaseSnakeUiModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GameOverModel.class);
  private SnakeServiceViewModel snakeModel = new SnakeServiceViewModel();
  private Runnable showMainMenuHandler = () -> {
  };
  private Runnable restartHandler = () -> {
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
   * Score of the current game.
   *
   * @return score of the current game as property.
   */
  public IntegerProperty getScore() {
    return getSnakeModel().scoreProperty();
  }

  /**
   * getter for main menu handler.
   *
   * @return main menu handler.
   */
  public Runnable getShowMainMenuHandler() {
    return showMainMenuHandler;
  }

  /**
   * setter for main menu handler.
   *
   * @param showMainMenuHandler new main menu handler.
   */
  public void setShowMainMenuHandler(Runnable showMainMenuHandler) {
    this.showMainMenuHandler = showMainMenuHandler;
  }

  /**
   * getter restart handler.
   *
   * @return current restart handler.
   */
  public Runnable getRestartHandler() {
    return restartHandler;
  }

  /**
   * setter for restart handler.
   *
   * @param restartHandler new restart handler.
   */
  public void setRestartHandler(Runnable restartHandler) {
    this.restartHandler = restartHandler;
  }
}
