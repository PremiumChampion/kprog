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
  @Override
  public SnakeServiceViewModel getSnakeModel() {
    return snakeModel;
  }
  public IntegerProperty getScore(){
    return getSnakeModel().scoreProperty();
  }
}
