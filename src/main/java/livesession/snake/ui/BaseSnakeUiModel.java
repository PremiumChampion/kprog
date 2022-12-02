package livesession.snake.ui;

/**
 * base model for a ui component.
 */
public interface BaseSnakeUiModel {

  /**
   * the snake model of the controller.
   *
   * @return the new snake model.
   */
  SnakeServiceViewModel getSnakeModel();
}
