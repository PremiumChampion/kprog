package examples.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestLoggingHelper {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestLoggingHelper.class);

  @Test
  public void testExtractCallingMethod() {
    String methodName = LoggingHelper.getMethod();
    assertEquals("testExtractCallingMethod", methodName);
  }
}
