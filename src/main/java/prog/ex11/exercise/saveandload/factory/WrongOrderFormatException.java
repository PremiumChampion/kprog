package prog.ex11.exercise.saveandload.factory;

/**
 * Gets thrown when the format of the order object does not meet the specification.
 */
public class WrongOrderFormatException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(WrongOrderFormatException.class);

  public WrongOrderFormatException() {
  }

  public WrongOrderFormatException(final String message) {
    super(message);
  }

  public WrongOrderFormatException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public WrongOrderFormatException(final Throwable cause) {
    super(cause);
  }
}
