package examples.javafx.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Demonstrator to create a simple fxml based component and display it.
 */
public class DemoTwoLauncher extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoTwoLauncher.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    VBox rootVerticalBox = new VBox();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/SecondScreen.fxml"));
    loader.setRoot(rootVerticalBox);
    loader.load();

    primaryStage.setScene(new Scene(rootVerticalBox, 800, 800));
    primaryStage.setTitle("Second simple Screen");
    primaryStage.show();
  }
}

