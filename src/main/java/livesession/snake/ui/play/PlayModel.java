package livesession.snake.ui.play;

import livesession.snake.ui.BaseSnakeUiModel;
import livesession.snake.ui.SnakeServiceViewModel;

public class PlayModel implements BaseSnakeUiModel {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      PlayModel.class);
  private SnakeServiceViewModel snakeModel = new SnakeServiceViewModel();


  public SnakeServiceViewModel getSnakeModel() {
    return snakeModel;
  }
}
