package examples.i18n;

import java.text.DateFormat;
import java.util.Locale;

/**
 * Demonstrates the number of different Locale configurations.
 */
public class ShowAvailableLocales {

  /**
   * main.
   *
   * @param args no args expected
   */
  public static void main(String[] args) {
    Locale[] list = DateFormat.getAvailableLocales();
    System.out.println("Number of Locales supported by DateFormat in ISO-Format: " + list.length);
    for (Locale locale : list) {
      System.out.println(locale.toString());
    }
    System.out.println("\n\nSame list now in a more readable format:");
    for (Locale locale : list) {
      System.out.println(locale.getDisplayName());
    }

    System.out.println("All in all, there are " + list.length + " different Locales.");
  }
}
