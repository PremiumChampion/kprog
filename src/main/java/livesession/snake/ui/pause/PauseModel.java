package livesession.snake.ui.pause;

import livesession.snake.ui.BaseSnakeUiModel;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class PauseViewModel.
 */
public class PauseModel implements BaseSnakeUiModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PauseModel.class);
  private Runnable onAbortGameAction = () -> {
  };
  private Runnable onResumeGameAction = () -> {
  };
  private SnakeServiceViewModel snakeServiceViewModel = new SnakeServiceViewModel();

  @Override
  public SnakeServiceViewModel getSnakeModel() {
    return snakeServiceViewModel;
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
   * getter for the resume game action.
   *
   * @return resume game action.
   */
  public Runnable getOnResumeGameAction() {
    return onResumeGameAction;
  }

  /**
   * setter for the resume game action.
   *
   * @param onResumeGameAction new resume game action.
   */
  public void setOnResumeGameAction(Runnable onResumeGameAction) {
    this.onResumeGameAction = onResumeGameAction;
  }
}
