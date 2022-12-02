package examples.helpers;

/**
 * Simple helper to detect the name of the actual method where getMethod is called.
 */
public class LoggingHelper {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(LoggingHelper.class);

  /**
   * Returns name of method from where this method has been called.
   *
   * @return name of the calling method
   */
  public static String getMethod() {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    return stackTrace[2].getMethodName();
  }
}
