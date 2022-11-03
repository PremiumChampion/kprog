package prog.javafx.main;

import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;
import prog.ex07.solution.javafx4palindrome.PalindromeCheckerGui;
import prog.ex07.solution.javafx4palindrome.SimplePalindromeChecker;

/**
 * JavaFX UI to manage all javafx related exercises in one program using a TabPane.
 */
public class KprogGuiWithJavaFx extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(KprogGuiWithJavaFx.class);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    TabPane root = new TabPane();
    InputStream is = getClass().getClassLoader()
            .getResourceAsStream("images/here-advertisement.png");
    Image image = new Image(is);
    ImageView imageView = new ImageView(image);

    Tab welcomeTab = new Tab("Welcome", imageView);
    root.getTabs().add(welcomeTab);

    PalindromeChecker palindromeChecker = new SimplePalindromeChecker();
    PalindromeCheckerGui palindromeCheckerGui = new PalindromeCheckerGui(palindromeChecker);
    Tab palindromeTab = new Tab("PalindromeChecker", palindromeCheckerGui);
    root.getTabs().add(palindromeTab);
    primaryStage.setTitle("Hello JavaFX World");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

  }
}
