package livesession.snake.ui.mainmenu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import livesession.snake.ui.BaseSnakeUiController;

/**
 * controller for the main menu.
 */
public class MainMenuController implements BaseSnakeUiController<MainMenuModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MainMenuController.class);
  @FXML
  public Button startButton;
  @FXML
  public Button optionsButton;
  private MainMenuModel model = new MainMenuModel();

  /**
   * getter for the snake model.
   *
   * @return the snake model.
   */
  public MainMenuModel getModel() {
    return model;
  }

  /**
   * handler for the on action start button.
   *
   * @param actionEvent event.
   */
  public void start(ActionEvent actionEvent) {
    this.model.getOnStart().run();
  }

  /**
   * handler for the configure button click.
   *
   * @param actionEvent event.
   */
  public void configure(ActionEvent actionEvent) {
    this.model.getOnConfigure().run();
  }

  @Override
  public void setModel(MainMenuModel model) {
    this.model = model;
  }

  @Override
  public void bind() {
    // no ui elements to actively bind.
  }
}
