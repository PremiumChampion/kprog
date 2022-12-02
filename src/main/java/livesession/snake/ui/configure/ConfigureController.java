package livesession.snake.ui.configure;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import livesession.snake.GameConfiguration;
import livesession.snake.ui.BaseSnakeUiController;

/**
 * controller for the configuration.
 */
public class ConfigureController implements BaseSnakeUiController<ConfigureModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ConfigureController.class);
  public Button discardButton;
  public Button saveButton;
  public Spinner<Integer> foodSpinner;
  public Spinner<Integer> sizeSpinner;
  public Spinner<Integer> gameSpeedSpinner;
  private ConfigureModel model = new ConfigureModel();

  @Override
  public void setModel(ConfigureModel model) {
    logger.debug("setModel");
    this.model = model;
  }

  /**
   * Handler for the UI event of the discard button.
   *
   * @param actionEvent event.
   */
  public void discard(ActionEvent actionEvent) {
    this.model.getDiscardHandler().run();
  }

  /**
   * Handler for the UI event of the save button.
   *
   * @param actionEvent event.
   */
  public void save(ActionEvent actionEvent) {
    this.model.getSaveConfigurationHandler()
        .save(gameSpeedSpinner.getValue(), sizeSpinner.getValue(), foodSpinner.getValue());
  }

  /**
   * requests the configuration from the set model and binds the UI elements.
   */
  void sync() {
    GameConfiguration configuration = model.getSnakeModel().getConfiguration();

    foodSpinner.setValueFactory(
        new IntegerSpinnerValueFactory(1, 10, configuration.getNumberOfFood(), 1));
    sizeSpinner.setValueFactory(new IntegerSpinnerValueFactory(4, 40, configuration.getSize(), 1));
    gameSpeedSpinner.setValueFactory(new IntegerSpinnerValueFactory(100, 2000,
        configuration.getVelocityInMilliSeconds(), 100));
    foodSpinner.getValueFactory().setValue(configuration.getNumberOfFood());
    sizeSpinner.getValueFactory().setValue(configuration.getSize());
    gameSpeedSpinner.getValueFactory().setValue(configuration.getVelocityInMilliSeconds());
  }

  @Override
  public void bind() {
    sync();
  }
}
