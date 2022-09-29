package environment.testprograms;

import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Simple demo program to check if the javafx environment works.
 */
public class HelloWorldWithJavaFx extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(HelloWorldWithJavaFx.class);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    StackPane root = new StackPane();
    Image image =
        new Image(getClass().getResource("/images/javaFX-Handwritten.png").toExternalForm());
    ImageView imageView = new ImageView(image);
    root.getChildren().add(imageView);
    primaryStage.setTitle("Hello JavaFX World");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

  }
}
