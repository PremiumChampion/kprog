package examples.javafx.firststeps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Demonstrates the usage of a handler as public class.
 */
public class ShowButtonActionWithPublicClass extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ShowButtonActionWithPublicClass.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    VBox root = new VBox();
    Label label = new Label();
    Button button = new Button("click me to show thread name!");
    button.setOnAction(new ButtonHandler(label));
    root.getChildren().add(button);
    root.getChildren().add(label);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}

