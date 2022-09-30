package prog.ex03.exercise.printer.exceptions;

/**
 * Gets thrown if the user tries to print in color mode but the printer is no color printer.
 */
public class NoColorPrinterException extends Exception {

  public NoColorPrinterException(final String message) {
    super(message);
  }
}
