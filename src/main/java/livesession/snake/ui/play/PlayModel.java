package livesession.snake.ui.play;

import livesession.snake.ui.BaseSnakeUiModel;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * play model.
 */
public class PlayModel implements BaseSnakeUiModel {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      PlayModel.class);
  private Runnable onAbortGameAction = () -> {
  };
  private Runnable onPauseGameAction = () -> {
  };
  private SnakeServiceViewModel snakeModel = new SnakeServiceViewModel();

  @Override
  public SnakeServiceViewModel getSnakeModel() {
    return snakeModel;
  }


  /**
   * getter for the abort game action.
   *
   * @return the abort game action.
   */
  public Runnable getOnAbortGameAction() {
    return onAbortGameAction;
  }

  /**
   * setter for the abort game action.
   *
   * @param onAbortGameAction the new abort game action.
   */
  public void setOnAbortGameAction(Runnable onAbortGameAction) {
    this.onAbortGameAction = onAbortGameAction;
  }

  /**
   * getter for the pause game action.
   *
   * @return pause game action.
   */
  public Runnable getOnPauseGameAction() {
    return onPauseGameAction;
  }

  /**
   * setter for the pause game action.
   *
   * @param onPauseGameAction new pause game action.
   */
  public void setOnPauseGameAction(Runnable onPauseGameAction) {
    this.onPauseGameAction = onPauseGameAction;
  }
}
