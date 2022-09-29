package prog.ex01.exercise.palindrome;

/**
 * Helper class to detect palindromes. A palindrome is a sequence of characters (letters, numbers)
 * which reads the same forward as backward, ignoring blanks and special characters like hyphens
 * or exclamation marks and upper or lower case. An empty string is not a palindrome.
 * Examples are
 * <ul>
 * <li>A man, a plan, a canal – Panama</li>
 * <li>O Genie, der Herr ehre dein Ego!</li>
 * </ul>
 */
public interface PalindromeChecker {
  /**
   * Checks if the given line is a palindrome.
   *
   * @param line line to be checked
   * @return true, if the line contains a palindrome, false otherwise.
   */
  boolean isPalindrome(String line);

  /**
   * Transforms a given String into a char array while ignoring all chars which are neither a letter
   * nor a digit.
   *
   * @param line line to be normalized
   * @return char array only containing chars which are letters or digits
   */
  char[] normalizeLine(String line);
}
