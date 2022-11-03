package prog.ex07.solution.javafx4palindrome;

import javafx.scene.layout.FlowPane;
import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;

/**
 * JavaFX component to wrap around a given PalindromeChecker.
 */
public class PalindromeCheckerGui extends FlowPane {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PalindromeCheckerGui.class);

  public PalindromeCheckerGui(PalindromeChecker palindromeChecker) {
  }
}
