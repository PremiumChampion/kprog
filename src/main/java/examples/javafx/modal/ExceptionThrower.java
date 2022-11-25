package examples.javafx.modal;

/**
 * Class which throws a configured exception as long as it has a constructor with a simple string.
 */
public class ExceptionThrower {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ExceptionThrower.class);

  private Class exceptionType;

  /**
   * Constructor.
   *
   * @param exceptionType Type of exception to be thrown. Should have a constructor using a
   *                      simple string.
   */
  public ExceptionThrower(final Class exceptionType) {
    this.exceptionType = exceptionType;
  }

  /**
   * Simulates the raise of an exception.
   *
   * @throws Exception exception of the type configured in the constructor.
   */
  public void simulateAnException() throws Exception {
    Exception exception = (Exception) exceptionType
        .getConstructor(String.class)
        .newInstance("OMG. This is exception karaoke. I think I am a "
            + exceptionType.getSimpleName() + ".");
    throw exception;
  }
}
