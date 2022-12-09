package livesession.snake.ui;

/**
 * Base UI controller with a specified model.
 */
public interface BaseSnakeController {

  /**
   * sets a new model for the controller.
   *
   * @param model the new model to set.
   */
  void bind(SnakeServiceViewModel model);

  /**
   * injects a screen loader for switching between screens.
   *
   * @param loader the loader to set.
   */
  void setScreenLoader(SnakeScreenLoader loader);
}
