package prog.ex01.palindrome;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex01.exercise.palindrome.PalindromeChecker;
import prog.ex01.solution.palindrome.SimplePalindromeChecker;

public class TestPalindromeChecker {
  PalindromeChecker palindromeChecker;

  @Before
  public void setup() {
    palindromeChecker = new SimplePalindromeChecker();
  }

  @Test @Ignore
  public void testNormalizeWithBlanks() {
    char[] result = palindromeChecker.normalizeLine("this is a line with  blanks");
     assertNotNull(result);
    String expectedString = "thisisalinewithblanks";
    char[] expectedCharArray = expectedString.toCharArray();
    assertArrayEquals(expectedCharArray, result);
  }

  @Test @Ignore
  public void testNormalizeWithSpecialCharacters() {
    char[] result = palindromeChecker.normalizeLine("this is a line, also with 2 special Chars!");
    assertNotNull(result);
    String expectedString = "thisisalinealsowith2specialchars";
    char[] expectedCharArray = expectedString.toCharArray();
    assertArrayEquals(expectedCharArray, result);
  }

  @Test @Ignore
  public void testIsPalindrome01() {
    assertTrue(palindromeChecker.isPalindrome("abba"));
  }

  @Test @Ignore
  public void testIsPalindrome02() {
    assertTrue(palindromeChecker.isPalindrome("O Genie, der Herr ehre dein Ego!"));
  }

  @Test @Ignore
  public void testIsPalindrome03() {
    assertTrue(palindromeChecker.isPalindrome("A man, a plan, a canal – Panama"));
  }

}
