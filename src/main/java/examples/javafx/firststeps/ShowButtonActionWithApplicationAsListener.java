package examples.javafx.firststeps;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Demonstrates the usage of a handler as the application itself. Don't do it!
 */

public class ShowButtonActionWithApplicationAsListener extends Application
        implements EventHandler<ActionEvent> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ShowButtonActionWithApplicationAsListener.class);
  private Label label;

  @Override
  public void start(final Stage primaryStage) throws Exception {
    VBox root = new VBox();
    Button button = new Button("click me to show thread name!");
    button.setOnAction(this);
    label = new Label();
    root.getChildren().add(button);
    root.getChildren().add(label);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  @Override
  public void handle(final ActionEvent event) {
    logger.info("Current thread is {}.", Thread.currentThread());
    label.setText(Thread.currentThread().getName());
    event.consume();
  }
}

