package livesession.snake.ui.mainmenu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import livesession.snake.ui.BaseSnakeUiController;

public class MainMenuController implements Initializable, BaseSnakeUiController<MainMenuModel> {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MainMenuController.class);
  @FXML
  public Button startButton;
  @FXML
  public Button optionsButton;
  private MainMenuModel model = new MainMenuModel();


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("initialising");
  }

  public MainMenuModel getModel() {
    return model;
  }

  public void start(ActionEvent actionEvent) {
    this.model.getOnStart().run();
  }

  public void configure(ActionEvent actionEvent) {
    this.model.getOnConfigure().run();
  }

  @Override
  public void setModel(MainMenuModel model) {
    this.model = model;
  }

  @Override
  public void bind() {
    logger.info("binding");
  }
}
