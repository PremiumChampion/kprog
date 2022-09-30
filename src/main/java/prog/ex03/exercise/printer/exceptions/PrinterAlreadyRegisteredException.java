package prog.ex03.exercise.printer.exceptions;

/**
 * Gets thrown when a printer with the same name is already registered.
 */
public class PrinterAlreadyRegisteredException extends Exception {

  public PrinterAlreadyRegisteredException(final String message) {
    super(message);
  }
}
