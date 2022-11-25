package examples.javafx.modal;

import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import livesession.snake.IllegalConfigurationException;

/**
 * Demonstrates a simple modal dialog based on an exception.
 */
public class ModalDialogDemo extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ModalDialogDemo.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    Pane root = new FlowPane();
    ExceptionThrower thrower = new ExceptionThrower(IllegalArgumentException.class);
    Button infoButton = new Button("Info");
    infoButton.setOnAction((e) -> {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Pick a Choice!",
          ButtonType.NO, ButtonType.YES, ButtonType.CANCEL);
      alert.setHeaderText("Pick a choice!");
      alert.setContentText("Just booring information. Booooring! Can you hear me?");
      Optional<ButtonType> result = alert.showAndWait();
      System.out.println(result);
    });
    Button exceptionButton = new Button("Exception");
    exceptionButton.setOnAction((e) -> {
      try {
        thrower.simulateAnException();
      } catch (Exception ex) {
        ExceptionAlert alert = new ExceptionAlert(ex);
        alert.showAndWait();
      }
    });
    root.getChildren().addAll(infoButton, exceptionButton);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
