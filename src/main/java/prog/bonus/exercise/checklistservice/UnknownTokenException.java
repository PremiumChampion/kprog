package prog.bonus.exercise.checklistservice;

/**
 * Thrown when the given token is not in the set of valid tokens.
 */
public class UnknownTokenException extends Exception {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(UnknownTokenException.class);

  public UnknownTokenException(final String message) {
    super(message);
  }
}
