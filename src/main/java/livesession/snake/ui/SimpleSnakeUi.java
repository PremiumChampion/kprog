package livesession.snake.ui;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import livesession.snake.ui.mainmenu.MainMenu;

/**
 * simple snake application.
 */
public class SimpleSnakeUi extends Application {

  /**
   * main class.
   *
   * @param args arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    SnakeScreenLoader snakeScreenLoader = new SnakeScreenLoader();
    primaryStage.setScene(snakeScreenLoader.getScene());
    snakeScreenLoader.load(new MainMenu());
    primaryStage.setTitle("THE MOTHERFUCKING GOD DAMN BEST SNAKE");
    primaryStage.setAlwaysOnTop(true);
    primaryStage.show();
  }
}
