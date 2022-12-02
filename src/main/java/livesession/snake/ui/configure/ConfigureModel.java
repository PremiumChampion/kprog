package livesession.snake.ui.configure;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import livesession.snake.ui.BaseSnakeUiModel;
import livesession.snake.ui.SnakeServiceViewModel;

/**
 * class ConfigureViewModel.
 */
public class ConfigureModel implements BaseSnakeUiModel {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ConfigureModel.class);
  private SnakeServiceViewModel snakeModel = new SnakeServiceViewModel();

  private SaveConfigurationHandler saveConfigurationHandler = (int i0, int i1, int i2) -> {
  };

  private Runnable discardHandler = () -> {
  };

  @Override
  public SnakeServiceViewModel getSnakeModel() {
    return snakeModel;
  }

  /**
   * Returns the save handler.
   *
   * @return saveHandler.
   */
  public SaveConfigurationHandler getSaveConfigurationHandler() {
    return saveConfigurationHandler;
  }

  /**
   * Sets the save handler.
   *
   * @param saveConfigurationHandler new save handler.
   */
  public void setSaveConfigurationHandler(
      SaveConfigurationHandler saveConfigurationHandler) {
    this.saveConfigurationHandler = saveConfigurationHandler;
  }

  /**
   * Returns the discard handler.
   *
   * @return discardHandler.
   */
  public Runnable getDiscardHandler() {
    return discardHandler;
  }

  /**
   * Sets the discard handler.
   *
   * @param discardHandler new discard handler.
   */
  public void setDiscardHandler(Runnable discardHandler) {
    this.discardHandler = discardHandler;
  }
}
