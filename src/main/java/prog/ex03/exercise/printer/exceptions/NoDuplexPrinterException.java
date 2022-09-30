package prog.ex03.exercise.printer.exceptions;

/**
 * Gets thrown if the user tries to print in duplex mode but the printer is no duplex printer.
 */
public class NoDuplexPrinterException extends Exception {

  public NoDuplexPrinterException(final String message) {
    super(message);
  }
}
