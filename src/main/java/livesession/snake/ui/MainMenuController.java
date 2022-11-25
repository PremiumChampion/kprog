package livesession.snake.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainMenuController implements Initializable {

  @FXML
  public Button startButton;
  @FXML
  public Button optionsButton;
  private final MainMenuModel model;

  public MainMenuController() {
    model = new MainMenuModel();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  public MainMenuModel getModel() {
    return model;
  }
}
