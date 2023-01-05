package examples.i18n;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Demonstrates formatting of numbers and percentages with regard to the conventions of the
 * countries identified by a Locale.
 */
public class NumberCurrencyFormatDemo {

  /**
   * Main.
   *
   * @param args no args expected.
   */
  public static void main(String[] args) {

    Locale[] locales = {
        new Locale("en", "US"),
        new Locale("de", "DE"),
        new Locale("fr", "FR")
    };

    for (Locale locale : locales) {
      System.out.println();
      displayInteger(locale);
      displayNumber(locale);
      displayCurrency(locale);
      displayPercent(locale);
    }
  }

  /**
   * Prints an integer and a double with an integer formatter.
   *
   * @param currentLocale locale defining the formatter.
   */
  public static void displayInteger(Locale currentLocale) {

    Integer quantity = 123456;
    Double amount = 345987.246;

    NumberFormat numberFormatter = NumberFormat.getIntegerInstance(currentLocale);
    String quantityFormatted = numberFormatter.format(quantity);
    String amountFormatted = numberFormatter.format(amount);
    System.out.println(quantityFormatted + "   " + currentLocale.toString());
    System.out.println(amountFormatted + "   " + currentLocale);
  }

  /**
   * Prints an integer and a double with an number formatter.
   *
   * @param currentLocale locale defining the formatter.
   */
  public static void displayNumber(Locale currentLocale) {

    Integer quantity = 123456;
    Double amount = 345987.246;

    NumberFormat numberFormatter = NumberFormat.getNumberInstance(currentLocale);
    String quantityFormatted = numberFormatter.format(quantity);
    String amountFormatted = numberFormatter.format(amount);
    System.out.println(quantityFormatted + "   " + currentLocale.toString());
    System.out.println(amountFormatted + "   " + currentLocale);
  }

  /**
   * Prints a value with a currency formatter.
   *
   * @param currentLocale locale defining the formatter.
   */

  public static void displayCurrency(Locale currentLocale) {

    Double currency = 9876543.21;

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
    String currencyFormatted = currencyFormatter.format(currency);
    System.out.println(currencyFormatted + "   " + currentLocale.toString());
  }

  /**
   * Prints a double percentage as percent value using a percent formatter.
   *
   * @param currentLocale locale defining the formatter.
   */

  public static void displayPercent(Locale currentLocale) {

    Double percent = 0.75;

    NumberFormat percentFormatter = NumberFormat.getPercentInstance(currentLocale);
    String percentFormatted = percentFormatter.format(percent);
    System.out.println(percentFormatted + "   " + currentLocale.toString());
  }

}
