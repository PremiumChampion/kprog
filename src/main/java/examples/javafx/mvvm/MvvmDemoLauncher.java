package examples.javafx.mvvm;

import examples.javafx.fxml.FirstScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Demonstrates the MVVM concept in JavaFX.
 */
public class MvvmDemoLauncher extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MvvmDemoLauncher.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    // Create the Model, in this case, a service
    SimpleHighScoreService service = new SimpleHighScoreService();

    // add some demo values to show up in the list
    service.addHighScore(new HighScore("Peter", 23));
    service.addHighScore(new HighScore("Paul", 42));
    service.addHighScore(new HighScore("Mary", 4711));

    // Create the ViewModel
    HighScoreViewModel viewModel = new HighScoreViewModel(service);

    // Create the View
    HighScoreView view = new HighScoreView(viewModel);

    // Embed the View into the stage
    StackPane rootStackPane = new StackPane();
    rootStackPane.getChildren().add(view);
    primaryStage.setScene(new Scene(rootStackPane, 800, 600));
    primaryStage.setTitle("HighScoreGui");
    primaryStage.show();
  }
}
