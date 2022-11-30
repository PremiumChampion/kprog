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

  private Runnable discardHandler = ()->{};

  @Override
  public SnakeServiceViewModel getSnakeModel() {
    return snakeModel;
  }

  public SaveConfigurationHandler getSaveConfigurationHandler() {
    return saveConfigurationHandler;
  }

  public void setSaveConfigurationHandler(
      SaveConfigurationHandler saveConfigurationHandler) {
    this.saveConfigurationHandler = saveConfigurationHandler;
  }

  public Runnable getDiscardHandler() {
    return discardHandler;
  }

  public void setDiscardHandler(Runnable discardHandler) {
    this.discardHandler = discardHandler;
  }
}
