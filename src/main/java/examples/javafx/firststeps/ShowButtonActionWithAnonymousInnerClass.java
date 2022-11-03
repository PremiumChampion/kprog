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
 * Demonstrates the usage of a handler as an anonymous inner class.
 */
public class ShowButtonActionWithAnonymousInnerClass extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ShowButtonActionWithAnonymousInnerClass.class);
  private Label label;

  @Override
  public void start(final Stage primaryStage) throws Exception {
    VBox root = new VBox();
    Button button = new Button("click me to show thread name!");
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(final ActionEvent event) {
        logger.info("Current thread is {}.", Thread.currentThread());
        label.setText(Thread.currentThread().getName());
        event.consume();
      }
    });
    label = new Label();
    root.getChildren().add(button);
    root.getChildren().add(label);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

}

