package livesession.snake.ui;

/**
 * Base UI controller with a specified model.
 *
 * @param <T> the specified model.
 */
public interface BaseSnakeUiController<T> extends Bindable {

  /**
   * sets a new model for the controller.
   *
   * @param model the new model to set.
   */
  void setModel(T model);
}
