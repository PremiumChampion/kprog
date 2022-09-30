package prog.ex03.exercise.printer.exceptions;

/**
 * Gets thrown when a printer gets requested with a certain name which is not registered.
 */
public class PrinterNotRegisteredException extends Exception {

  public PrinterNotRegisteredException(final String message) {
    super(message);
  }
}
