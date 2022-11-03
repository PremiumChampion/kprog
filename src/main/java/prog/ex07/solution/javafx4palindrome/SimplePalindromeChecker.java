package prog.ex07.solution.javafx4palindrome;

import java.util.Arrays;
import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;

/**
 * Simple palindrome checker.
 */
public class SimplePalindromeChecker implements PalindromeChecker {

  @Override
  public boolean isPalindrome(final String line) {
    char[] normalised = this.normalizeLine(line);
    char[] reversed = this.normalizeLine(line);

    for (int i = 0; i < (reversed.length) / 2; i++) {
      char tmp = reversed[i];
      reversed[i] = reversed[reversed.length - 1 - i];
      reversed[reversed.length - 1 - i] = tmp;
    }

    return Arrays.equals(normalised, reversed);
  }

  @Override
  public char[] normalizeLine(String line) {
    return line.toLowerCase().replaceAll("[^\\p{LD}]", "").toCharArray();
  }

}
