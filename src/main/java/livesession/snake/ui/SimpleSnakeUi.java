package livesession.snake.ui;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import livesession.snake.ui.mainmenu.MainMenu;

public class SimpleSnakeUi extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    SnakeScreenLoader snakeScreenLoader = new SnakeScreenLoader();
    primaryStage.setScene(snakeScreenLoader.getScene());
    snakeScreenLoader.load(new MainMenu());
    primaryStage.show();
  }

  @Override
  public void stop() throws Exception {
    super.stop();

  }
}
