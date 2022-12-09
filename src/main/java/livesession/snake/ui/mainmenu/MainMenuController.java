package livesession.snake.ui.mainmenu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import livesession.snake.ui.BaseSnakeController;
import livesession.snake.ui.SnakeScreenLoader;
import livesession.snake.ui.SnakeServiceViewModel;
import livesession.snake.ui.configure.Configure;

/**
 * controller for the main menu.
 */
public class MainMenuController implements BaseSnakeController, Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MainMenuController.class);
  @FXML
  public Button startButton;
  @FXML
  public Button optionsButton;
  private SnakeServiceViewModel model = new SnakeServiceViewModel();
  private SnakeScreenLoader screenLoader;

  /**
   * handler for the on action start button.
   *
   * @param actionEvent event.
   */
  public void start(ActionEvent actionEvent) {
    model.play();
  }

  /**
   * handler for the configure button click.
   *
   * @param actionEvent event.
   */
  public void configure(ActionEvent actionEvent) {
    screenLoader.load(new Configure());
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // nothing to bind because of fxml
  }

  @Override
  public void bind(SnakeServiceViewModel model) {
    this.model.bind(model);
  }

  @Override
  public void setScreenLoader(SnakeScreenLoader loader) {
    this.screenLoader = loader;
  }
}
