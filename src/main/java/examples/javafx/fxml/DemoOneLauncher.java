package examples.javafx.fxml;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Demonstrator to create a simple fxml based component and display it.
 */
public class DemoOneLauncher extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoOneLauncher.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    StackPane rootStackPane = new StackPane();
    FirstScreen firstScreen = new FirstScreen();
    rootStackPane.getChildren().add(firstScreen);

    primaryStage.setScene(new Scene(rootStackPane, 800, 600));
    primaryStage.setTitle("First simple Screen");
    primaryStage.show();
  }
}
