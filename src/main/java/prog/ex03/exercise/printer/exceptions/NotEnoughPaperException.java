package prog.ex03.exercise.printer.exceptions;

/**
 * Gets thrown when the printer does not have enough paper to print the print job.
 */
public class NotEnoughPaperException extends Exception {
  private int numberMissingPages;

  public NotEnoughPaperException(final String message, final int numberMissingPages) {
    super(message);
    this.numberMissingPages = numberMissingPages;
  }

  public int getNumberMissingPages() {
    return numberMissingPages;
  }
}
