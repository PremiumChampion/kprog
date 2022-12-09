package livesession.snake.ui.configure;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import livesession.snake.GameConfiguration;
import livesession.snake.ui.BaseSnakeController;
import livesession.snake.ui.SnakeScreenLoader;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.mainmenu.MainMenu;

/**
 * controller for the configuration.
 */
public class ConfigureController implements BaseSnakeController, Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ConfigureController.class);
  public Button discardButton;
  public Button saveButton;
  public Spinner<Integer> foodSpinner;
  public Spinner<Integer> sizeSpinner;
  public Spinner<Integer> gameSpeedSpinner;
  private SnakeServiceViewModel model = new SnakeServiceViewModel();
  private SnakeScreenLoader screenLoader;


  /**
   * Handler for the UI event of the discard button.
   *
   * @param actionEvent event.
   */
  public void discard(ActionEvent actionEvent) {
    screenLoader.load(new MainMenu());
  }

  /**
   * Handler for the UI event of the save button.
   *
   * @param actionEvent event.
   */
  public void save(ActionEvent actionEvent) {
    int speed = gameSpeedSpinner.getValue();
    int size = sizeSpinner.getValue();
    int food = foodSpinner.getValue();
    logger.info("onSave: speed:{} size:{} food:{}", speed, size, food);
    this.model.setConfiguration(speed, size, food);
    this.screenLoader.load(new MainMenu());
  }

  /**
   * requests the configuration from the set model and binds the UI elements.
   */
  void sync() {
    GameConfiguration configuration = model.getConfiguration();
    foodSpinner.getValueFactory().setValue(configuration.getNumberOfFood());
    sizeSpinner.getValueFactory().setValue(configuration.getSize());
    gameSpeedSpinner.getValueFactory().setValue(configuration.getVelocityInMilliSeconds());
  }


  @Override
  public void bind(SnakeServiceViewModel model) {
    this.model.bindBidirectional(model);
    sync();
  }

  @Override
  public void setScreenLoader(SnakeScreenLoader loader) {
    screenLoader = loader;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    foodSpinner.setValueFactory(
        new IntegerSpinnerValueFactory(1, 10, 1, 1));
    sizeSpinner.setValueFactory(
        new IntegerSpinnerValueFactory(4, 40, 4, 1));
    gameSpeedSpinner.setValueFactory(
        new IntegerSpinnerValueFactory(100, 2000, 100, 100));
    model.configurationProperty().addListener(change -> sync());
  }
}
