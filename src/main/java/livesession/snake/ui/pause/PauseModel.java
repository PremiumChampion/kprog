package livesession.snake.ui.pause;

import livesession.snake.ui.BaseSnakeUiModel;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class PauseViewModel.
 */
public class PauseModel implements BaseSnakeUiModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PauseModel.class);
  private SnakeServiceViewModel snakeServiceViewModel = new SnakeServiceViewModel();
  @Override
  public SnakeServiceViewModel getSnakeModel() {
    return snakeServiceViewModel;
  }
}
