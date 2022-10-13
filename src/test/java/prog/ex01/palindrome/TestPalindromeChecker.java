package prog.ex01.palindrome;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex01.exercise.palindrome.PalindromeChecker;
import prog.ex01.solution.palindrome.SimplePalindromeChecker;

/**
 * tests palindromes.
 */
public class TestPalindromeChecker {

  PalindromeChecker palindromeChecker;

  @Before
  public void setup() {
    palindromeChecker = new SimplePalindromeChecker();
  }

  @Test
  public void testNormalizeWithBlanks() {
    char[] result = palindromeChecker.normalizeLine("this is a line with  blanks");
    assertNotNull(result);
    String expectedString = "thisisalinewithblanks";
    char[] expectedCharArray = expectedString.toCharArray();
    assertArrayEquals(expectedCharArray, result);
  }

  @Test
  public void testNormalizeWithSpecialCharacters() {
    char[] result = palindromeChecker.normalizeLine("this is a line, also with 2 special Chars!");
    assertNotNull(result);
    String expectedString = "thisisalinealsowith2specialchars";
    char[] expectedCharArray = expectedString.toCharArray();
    assertArrayEquals(expectedCharArray, result);
  }

  @Test
  public void testIsPalindrome01() {
    assertTrue(palindromeChecker.isPalindrome("abba"));
  }

  @Test
  public void testIsPalindrome02() {
    assertTrue(palindromeChecker.isPalindrome("O Genie, der Herr ehre dein Ego!"));
  }

  @Test
  public void testIsPalindrome03() {
    assertTrue(palindromeChecker.isPalindrome("A man, a plan, a canal – Panama"));
  }
  @Test
  public void testIsPalindrome04() {
    char[] result = palindromeChecker.normalizeLine("Кулина́р, храни́ лук");
    assertNotNull(result);
    String expectedString = "кулинархранилук";
    char[] expectedCharArray = expectedString.toCharArray();
    assertArrayEquals(expectedCharArray, result);
    assertTrue(palindromeChecker.isPalindrome("Кулина́р, храни́ лук"));
  }

}
