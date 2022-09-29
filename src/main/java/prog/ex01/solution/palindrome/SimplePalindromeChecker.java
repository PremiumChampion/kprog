package prog.ex01.solution.palindrome;

import prog.ex01.exercise.palindrome.PalindromeChecker;

/**
 * Simple palindrome checker.
 */
public class SimplePalindromeChecker implements PalindromeChecker {

  @Override
  public boolean isPalindrome(final String line) {
    char[] reversed = line.toCharArray();

    for (int i = 0; i < (reversed.length) / 2; i++) {
      char tmp = reversed[i];
      reversed[i] = reversed[reversed.length - 1 - i];
      reversed[reversed.length - 1 - i] = tmp;
    }

    Boolean isPalindrome = true;
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) != reversed[i]) {
        isPalindrome = false;
      }
    }

    return isPalindrome;
  }

  @Override
  public char[] normalizeLine(String line) {
    line = line.replaceAll("[^a-zA-Z/d]", "");
    return line.toCharArray();
  }

  public static void main(String[] args) {
    System.out.println(new SimplePalindromeChecker().isPalindrome("HelloWorld"));
    System.out.println(new SimplePalindromeChecker().isPalindrome("Hello olleH"));
  }

}
