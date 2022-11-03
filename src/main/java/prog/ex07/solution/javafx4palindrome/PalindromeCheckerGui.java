package prog.ex07.solution.javafx4palindrome;

import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;

/**
 * JavaFX component to wrap around a given PalindromeChecker.
 */
public class PalindromeCheckerGui extends FlowPane {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PalindromeCheckerGui.class);
  public static final String SUCCESS = "SUCCESS";
  public static final String FAILURE = "FAILURE";
  private final PalindromeChecker palindromeChecker;
  private TextField inputField;
  private Button checkButton;
  private Label resultLabel;

  public PalindromeCheckerGui(PalindromeChecker palindromeChecker) {
    this.palindromeChecker = palindromeChecker;
    this.createInputField();
    this.createCheckButton();
    this.createResultLabel();
  }

  private void createInputField() {
    if (Objects.nonNull(this.inputField)) {
      return;
    }
    this.inputField = new TextField();
    this.inputField.onActionProperty().setValue(this::checkForPalindrome);
    this.getChildren().add(this.inputField);
  }

  private void createCheckButton() {
    if (Objects.nonNull(this.checkButton)) {
      return;
    }
    this.checkButton = new Button();
    this.checkButton.setText("Check palindrome");
    this.checkButton.onActionProperty().setValue(this::checkForPalindrome);
    this.getChildren().add(this.checkButton);
  }

  private void createResultLabel() {
    if (Objects.nonNull(this.resultLabel)) {
      return;
    }
    this.resultLabel = new Label();
    this.resultLabel.setText("");
    this.getChildren().add(this.resultLabel);
  }

  private void checkForPalindrome(ActionEvent event) {
    String inputToCheck = this.inputField.getText();
    boolean inputIsPalindrome = this.palindromeChecker.isPalindrome(inputToCheck);
    this.resultLabel.setText(inputIsPalindrome ? SUCCESS : FAILURE);
  }


}
