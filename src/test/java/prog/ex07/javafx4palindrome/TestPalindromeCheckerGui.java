package prog.ex07.javafx4palindrome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static prog.ex07.exercise.javafx4palindrome.Constants.SUCCESS;

import java.util.List;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import prog.ex07.exercise.javafx4palindrome.Constants;
import prog.ex07.solution.javafx4palindrome.PalindromeCheckerGui;
import prog.ex07.solution.javafx4palindrome.SimplePalindromeChecker;

public class TestPalindromeCheckerGui extends ApplicationTest {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestPalindromeCheckerGui.class);
  private PalindromeCheckerGui gui;
  private Button button;
  private Label label;
  private TextField textField;

  private void initializeComponents() {
    List<Node> children = gui.getChildren();

    for (Node node : children) {
      if (node instanceof Button) {
        button = (Button) node;
      }
      if (node instanceof Label) {
        label = (Label) node;
      }
      if (node instanceof TextField) {
        textField = (TextField) node;
      }
    }
  }

  @Override
  public void start(final Stage stage) throws Exception {
    super.start(stage);
    gui = new PalindromeCheckerGui(new SimplePalindromeChecker());
    stage.setScene(new Scene(gui));
    stage.show();
    initializeComponents();
  }

  @Test @Ignore
  public void checkBaseClass() {
    Class superClass = gui.getClass().getSuperclass();
    assertTrue("Your class is no direct successor from the class FlowPane",superClass.getName().contains("FlowPane"));
  }

  @Test @Ignore
  public void checkChildren() {
    Button button = null;
    Label label = null;
    TextField textField = null;

    List<Node> children = gui.getChildren();

    for (Node node : children) {
      if (node instanceof Button) {
        button = (Button) node;
      }
      if (node instanceof Label) {
        label = (Label) node;
      }
      if (node instanceof TextField) {
        textField = (TextField) node;
      }
    }

    assertNotNull("The mandatory button component could not be found",button);
    assertNotNull("The mandatory label component could not be found",label);
    assertNotNull("The mandatory textField component could not be found",textField);
  }

  @Test @Ignore
  public void successfulEnteredPalindromeCheck() {
    Platform.runLater(() -> textField.setText("O Genie, der Herr ehre dein Ego!"));
    clickOn(button);
    assertEquals(
        String.format(
            "%s is a palindrome but no success message has been detected",
            textField.getText()), SUCCESS, label.getText());
  }

  @Test @Ignore
  public void failurePalindromeCheck() {
    Platform.runLater(() -> textField.setText("This is not a palindrome!"));
    clickOn(button);
    assertEquals(
        String.format(
            "%s is not a valid palindrome but no failure message has been detected",
            textField.getText()), Constants.FAILURE, label.getText());
  }

  @Test @Ignore
  public void checkWithEmptyTextField() {
    Platform.runLater(() -> textField.setText(""));
    clickOn(button);
    assertEquals(
        "An empty string is no palindrome", Constants.FAILURE, label.getText());
  }

  @Test @Ignore
  public void checkButtonText() {
    boolean hasText = button.getText().trim().length() > 0;
    assertTrue("The button should have a displayed text", hasText);
  }

  @Test @Ignore
  public void testEnterKeystroke() {
    Platform.runLater(() -> textField.setText("--"));
    Platform.runLater(() -> textField.setText("O Genie, der Herr ehre dein Ego!"));
    press(KeyCode.ENTER);
    assertEquals(
            "The label did not display the success message", SUCCESS, label.getText());
  }
}
