package livesession.snake.ui.configure;

/**
 * Save configuration handler.
 */
public interface SaveConfigurationHandler {

  /**
   * action to perform when the configuration should be saved.
   *
   * @param speed        the speed to save.
   * @param size         the size to save.
   * @param numberOfFood the number of food items to save.
   */
  void save(int speed, int size, int numberOfFood);
}
