package examples.executorservice;

/**
 * Class simulating long-running function calls.
 */
public class LongRunningFunctionCalls {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(LongRunningFunctionCalls.class);

  /**
   * Dummy method to simulate a long-running function call.
   *
   * @param x time in milliseconds to sleep during the simulation
   * @return arbitrary value
   */
  public static int foo(int x) {
    try {
      Thread.sleep(x);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return 1729;
  }

  /**
   * Dummy method to simulate a long-running function call.
   *
   * @param x time in milliseconds to sleep during the simulation
   * @return arbitrary value
   */
  public static int bar(int x) {
    try {
      Thread.sleep(x);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return 4711;
  }
}
