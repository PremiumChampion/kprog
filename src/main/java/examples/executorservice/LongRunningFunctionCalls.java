package examples.executorservice;

public class LongRunningFunctionCalls {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(LongRunningFunctionCalls.class);

  public static int f(int x) {
    try {
      Thread.sleep(x);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return 1729;
  }

  public static int g(int x) {
    try {
      Thread.sleep(x);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return 4711;
  }
}
