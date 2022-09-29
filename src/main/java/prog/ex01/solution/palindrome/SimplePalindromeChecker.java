package prog.ex01.solution.palindrome;

import prog.ex01.exercise.palindrome.PalindromeChecker;

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

    for (int i = 0; i < normalised.length; i++) {
      if (normalised[i] != reversed[i]) {
        return false;
      }
    }

    return true;
  }

  @Override
  public char[] normalizeLine(String line) {
    return line.toLowerCase().replaceAll("[^a-zA-Z0-9]", "").toCharArray();
  }

}
