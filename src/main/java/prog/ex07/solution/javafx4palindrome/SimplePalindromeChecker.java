package prog.ex07.solution.javafx4palindrome;

import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;

/**
 * Simple palindrome checker.
 */
public class SimplePalindromeChecker implements PalindromeChecker {
  @Override
  public boolean isPalindrome(final String line) {
    return false;
  }

  @Override
  public char[] normalizeLine(final String line) {
    return null;
  }
}
