package examples.i18n.currency;


import java.text.NumberFormat;
import java.util.Locale;

/**
 * Demonstrates Locale-dependent formatting of amount of money.
 */
public class AmountOfMoney {

  /**
   * main.
   *
   * @param args no args expected
   */
  public static void main(String[] args) {
    NumberFormat germanNumberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    NumberFormat britishNumberFormat = NumberFormat.getCurrencyInstance(Locale.UK);
    System.out.println("Amount of money in de_DE : " + germanNumberFormat.format(12345.67));
    System.out.println("Amount of money in en_GB : " + britishNumberFormat.format(12345.67));
  }
}


